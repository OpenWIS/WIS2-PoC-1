#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Starting the LDSH MySQL container
echo - ------------------------------------------------------------------------
echo

sudo docker start openwis-ldsh-mysql

echo
echo - Configuring Docker Container...
echo

sudo docker cp ../injected/ldsh-docker-injected-mysql.sh openwis-ldsh-mysql:/ && \
    sudo docker cp ../injected/ldsh-docker-injected-mysql-configuration-script openwis-ldsh-mysql:/ && \
    sudo docker exec openwis-ldsh-mysql /ldsh-docker-injected-mysql.sh
echo

echo - LDSH MySQL started

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-ldsh-mysql /bin/bash
