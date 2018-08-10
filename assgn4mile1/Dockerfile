FROM gradle
USER root
RUN apt-get  -y update && wget https://bootstrap.pypa.io/get-pip.py && python get-pip.py && pip install awscli
COPY  . /home/gradle/src
WORKDIR /home/gradle/src
RUN ls && chmod +x run.sh
CMD ["sh","/home/gradle/src/run.sh"]