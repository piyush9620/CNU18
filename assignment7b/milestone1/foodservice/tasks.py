# Create your tasks here
from __future__ import absolute_import, unicode_literals
from celery import shared_task,chord,group
import boto3
import botocore
import requests
import os
from .models import Restaurant,Image

BUCKET_NAME = 'cnu-2k18'

@shared_task
def add(x, y):
    return x + y


@shared_task
def mul(x, y):
    return x * y


@shared_task
def xsum(numbers):
    return sum(numbers)


@shared_task()
def fetchImages(restaurant_id):
      # replace with your bucket name
    KEY = 'images.txt'  # replace with your object key
    print("fetching from aws")
    s3 = boto3.resource('s3')
    file_path = './'+str(restaurant_id)+'images.txt'
    try:
        s3.Bucket(BUCKET_NAME).download_file(KEY, file_path)
    except botocore.exceptions.ClientError as e:
        pass
    with open(file_path,"r") as f:
        content = f.readlines();
    g = group( fetchImage.s(restaurant_id, content[i]) for i in range(0, 10))
    callback = updateRestaurantStatus.s(restaurant_id)
    c = chord(g)(callback)


@shared_task()
def fetchImage(restaurant_id,download_url):
    r = requests.get(download_url)
    filename = str(restaurant_id)+"/"+download_url.strip().split("portraits/")[-1]
    filepath = "./"+filename
    s3_path = 'harya/restaurant_images/'+ filename
    if not os.path.exists(os.path.dirname(filepath)):
        try:
            os.makedirs(os.path.dirname(filepath))
        except OSError as exc:  # Guard against race condition
            print(exc)
    with open(filename, 'wb') as f:
        f.write(r.content)
    s3 = boto3.resource('s3')
    s3.Bucket(BUCKET_NAME).upload_file(filepath, s3_path)
    return s3_path


@shared_task()
def updateRestaurantStatus(input,restaurant_id):
    print(input)
    print("restaurant_id", restaurant_id)
    restaurant = Restaurant.objects.get(ID=restaurant_id)
    print(restaurant)
    for result in input:
        image = Image(restaurant=restaurant,s3_path = result)
        image.save()
    restaurant.images_added = True
    restaurant.save()

