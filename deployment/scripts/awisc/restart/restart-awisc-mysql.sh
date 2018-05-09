#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting the AWISC MySQL container
echo - ------------------------------------------------------------------------
echo && echo

sudo docker start openwis-awisc-mysql

echo - AWISC MySQL started
echo - ------------------------------------------------------------------------

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-awisc-mysql /bin/bash
