docker volume create clientvol
cd client && docker build -t assgn1client .
docker run -it -v clientvol:/clientdata --network=mynet -e "SERVER_HOST=172.19.0.3" -e "SERVER_PORT=5000" assgn1bclient
