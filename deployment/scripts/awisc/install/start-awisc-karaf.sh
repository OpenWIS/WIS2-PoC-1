#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Starting the Authoritative WIS Catalogue application
echo - ------------------------------------------------------------------------
echo

echo - Starting AWISC Karaf...
echo
#sudo docker start openwis-awisc-karaf -a -i && \
sudo docker start openwis-awisc-karaf && \
  sudo docker cp ../injected/awisc-docker-injected-karaf.sh openwis-awisc-karaf:/opt/karaf && \
  sudo docker cp ../injected/awisc-docker-injected-karaf-deployment-script openwis-awisc-karaf:/opt/karaf && \
  sudo docker cp ../injected/awisc-docker-injected-karaf-deployment-script-2 openwis-awisc-karaf:/opt/karaf && \
  sudo docker exec openwis-awisc-karaf /opt/karaf/awisc-docker-injected-karaf.sh
echo

echo - Karaf Started

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-awisc-karaf /bin/bash
