#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting the LDSH FTPD
echo - ------------------------------------------------------------------------
echo

sudo docker start openwis-ldsh-ftpd

echo - LDSH FTPD started

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-ftpd-ldsh /bin/bash
