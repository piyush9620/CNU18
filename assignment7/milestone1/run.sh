aws s3 cp s3://cnu-2k18/harya/secrets.yaml ./secrets.yaml
python manage.py makemigrations
python manage.py migrate
python manage.py runserver 0.0.0.0:80
