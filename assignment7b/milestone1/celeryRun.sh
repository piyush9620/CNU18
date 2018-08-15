aws s3 cp s3://cnu-2k18/harya/secrets.yaml ./secrets.yaml
celery -A fooddelivery worker -l info