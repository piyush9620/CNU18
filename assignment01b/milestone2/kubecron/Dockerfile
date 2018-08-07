FROM python:3.5-slim
MAINTAINER HARSH ARYA
COPY . /app
WORKDIR /app
RUN pip install -r requirements.txt
RUN mkdir /root/.kube && cp config /root/.kube/config
ENTRYPOINT ["python"]
CMD ["run.py"]

