FROM python
MAINTAINER HARSH ARYA
COPY . /app
WORKDIR /app
RUN mkdir static
RUN pip install -r requirements.txt
ENTRYPOINT ["python"]
CMD ["run.py"]

