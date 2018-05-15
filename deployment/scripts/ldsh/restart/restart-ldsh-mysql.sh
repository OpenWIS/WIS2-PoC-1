#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting the LDSH MySQL container
echo - ------------------------------------------------------------------------
echo && echo

sudo docker start openwis-ldsh-mysql

echo - LDSH MySQL started
echo - ------------------------------------------------------------------------

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-ldsh-mysql /bin/bash
