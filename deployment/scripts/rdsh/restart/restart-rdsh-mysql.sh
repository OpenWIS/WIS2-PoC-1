#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting the RDSH MySQL container
echo - ------------------------------------------------------------------------
echo

sudo docker start openwis-rdsh-mysql

echo - RDSH MySQL started

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-rdsh-mysql /bin/bash
