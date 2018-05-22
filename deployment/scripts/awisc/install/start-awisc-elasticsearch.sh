#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Starting the AWISC Elasticsearch
echo - ------------------------------------------------------------------------
echo

sudo docker start openwis-awisc-elasticsearch

echo - AWISC Elasticsearch started

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-elasticsearch-awisc /bin/bash
