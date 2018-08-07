docker volume create servervol
docker network create --driver=bridge --subnet=172.19.0.0/16 mynet
cd server && docker build -t assgn1bserver .
docker run -td -v servervol:/serverdata --network=mynet --ip="172.19.0.3"   assgn1bserver

