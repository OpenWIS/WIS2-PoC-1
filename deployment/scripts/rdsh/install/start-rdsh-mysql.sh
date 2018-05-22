#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Starting the RDSH MySQL container
echo - ------------------------------------------------------------------------
echo

sudo docker start openwis-rdsh-mysql

echo
echo - Configuring Docker Container...
echo

sudo docker cp ../injected/rdsh-docker-injected-mysql.sh openwis-rdsh-mysql:/ && \
    sudo docker cp ../injected/rdsh-docker-injected-mysql-configuration-script openwis-rdsh-mysql:/ && \
    sudo docker exec openwis-rdsh-mysql /rdsh-docker-injected-mysql.sh

echo - RDSH MySQL started

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-rdsh-mysql /bin/bash
