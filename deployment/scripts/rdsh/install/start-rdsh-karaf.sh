#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Starting the RDSH application
echo - ------------------------------------------------------------------------
echo

echo - Starting RDSH Karaf...
echo

sudo docker start openwis-rdsh-karaf && \
  sudo docker cp ../injected/rdsh-docker-injected-karaf.sh openwis-rdsh-karaf:/opt/karaf && \
  sudo docker cp ../injected/rdsh-docker-injected-karaf-deployment-script openwis-rdsh-karaf:/opt/karaf && \
  sudo docker exec openwis-rdsh-karaf /opt/karaf/rdsh-docker-injected-karaf.sh
echo

echo - Karaf Started


# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-rdsh-karaf /bin/bash
