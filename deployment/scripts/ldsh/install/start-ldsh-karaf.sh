#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Starting the LDSH application
echo - ------------------------------------------------------------------------
echo

echo - Starting LDSH Karaf...
echo
sudo docker start openwis-ldsh-karaf && \
  sudo docker cp ../injected/ldsh-docker-injected-karaf.sh openwis-ldsh-karaf:/opt/karaf && \
  sudo docker cp ../injected/ldsh-docker-injected-karaf-deployment-script openwis-ldsh-karaf:/opt/karaf && \
  sudo docker exec openwis-ldsh-karaf /opt/karaf/ldsh-docker-injected-karaf.sh
echo

echo - Karaf Started

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-ldsh-karaf /bin/bash
