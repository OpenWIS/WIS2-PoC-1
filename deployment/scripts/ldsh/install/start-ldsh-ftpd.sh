#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Starting the LDSH FTPD
echo - ------------------------------------------------------------------------
echo

sudo docker start openwis-ldsh-ftpd

echo
echo - Configuring Docker Container...
echo

sudo docker cp ../injected/ldsh-docker-injected-ftpd.sh openwis-ldsh-ftpd:/ && \
    sudo docker exec openwis-ldsh-ftpd /ldsh-docker-injected-ftpd.sh

echo - LDSH FTPD started

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-ldsh-ftpd /bin/bash
