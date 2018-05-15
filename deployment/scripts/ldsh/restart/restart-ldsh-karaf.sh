#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Restarting LDSH Karaf...
echo
#sudo docker start openwis-ldsh-karaf -a -i && \
sudo docker start openwis-ldsh-karaf && \
  sudo docker cp ../injected/ldsh-docker-injected-karaf.sh openwis-ldsh-karaf:/opt/karaf && \
  sudo docker cp ../injected/ldsh-docker-injected-karaf-deployment-script openwis-ldsh-karaf:/opt/karaf && \
  sudo docker exec openwis-ldsh-karaf /opt/karaf/ldsh-docker-injected-karaf.sh
echo

echo - ------------------------------------------------------------------------
echo - Karaf Started


echo - Tailing log
echo - ------------------------------------------------------------------------

# Use the command below on the host machine, if you want to open a shell into the docker container
# sudo docker exec -i -t openwis-ldsh-karaf /bin/bash
