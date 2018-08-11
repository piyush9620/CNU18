import boto3
import botocore
import concurrent.futures
import urllib.request
import logging
import urllib.request
import os

log = logging.getLogger(__name__)
logging.basicConfig(level=logging.INFO)

def log_download(func):
    def wrapper(*args, **kwargs):
        func_str = func.__name__
        log.info("Downloading "+args[0])
        data = func(*args, **kwargs)
        log.info("Downloaded "+args[0])
        return data
    return wrapper


def read_file():
    file  = open("images.txt")
    while True:
        data = file.readline()
        if not data:
            break
        yield data


@log_download
def download(url):
    urllib.request.urlretrieve(url, "files/"+url.strip().split("=")[-1]+".jpg")

BUCKET_NAME = 'cnu-2k18' # replace with your bucket name
KEY = 'images.txt' # replace with your object key

s3 = boto3.resource('s3')

try:
    s3.Bucket(BUCKET_NAME).download_file(KEY, 'images.txt')
except botocore.exceptions.ClientError as e:
    if e.response['Error']['Code'] == "404":
        log.error("The object does not exist.")
    else:
        raise

# We can use a with statement to ensure threads are cleaned up promptly
with concurrent.futures.ThreadPoolExecutor(max_workers=50) as executor:
    # Start the load operations and mark each future with its URL
    future_to_url = {executor.submit(download, url):url for url in read_file()}
    for future in concurrent.futures.as_completed(future_to_url):
        url = future_to_url[future]
        try:
            data = future.result()
        except Exception as exc:
            log.error('%r generated an exception: %s' % (url, exc))
