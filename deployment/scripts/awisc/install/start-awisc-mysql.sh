#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Starting the AWISC MySQL container
echo - ------------------------------------------------------------------------
echo

sudo docker start openwis-awisc-mysql

echo
echo - Configuring Docker Container...
echo

sudo docker cp ../injected/awisc-docker-injected-mysql.sh openwis-awisc-mysql:/ && \
    sudo docker cp ../injected/awisc-docker-injected-mysql-configuration-script openwis-awisc-mysql:/ && \
    sudo docker exec openwis-awisc-mysql /awisc-docker-injected-mysql.sh
echo

echo - AWISC MySQL started

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-awisc-mysql /bin/bash
