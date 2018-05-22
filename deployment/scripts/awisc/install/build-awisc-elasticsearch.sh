#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Creating AWISC Elasticsearch Docker Container
echo - ------------------------------------------------------------------------
echo

echo - Creating Docker Container...
sudo docker run --name=openwis-awisc-elasticsearch -p 9200:9200 -e "http.host=0.0.0.0" -e "transport.host=127.0.0.1" \
    -e "xpack.security.enabled=false" --network openwis-docker-bridge \
    -d docker.elastic.co/elasticsearch/elasticsearch:5.6.7
echo

echo - Stopping Docker Container...
echo
sudo docker stop openwis-awisc-elasticsearch


echo - AWISC Elasticsearch Docker Container Created Successfully
