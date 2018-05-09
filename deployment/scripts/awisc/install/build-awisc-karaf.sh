#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Creating AWISC Karaf Docker Container
echo - ------------------------------------------------------------------------
echo && echo

echo - ------------------------------------------------------------------------
echo - Assembling Docker Container...
echo
sudo docker create -v $(pwd)/../../artifacts/awisc:/root/.m2/repository \
    -v $(pwd)/../../logs/awisc:/opt/karaf/data/log -p 8183:8181 \
    --link openwis-awisc-mysql --link openwis-awisc-elasticsearch \
    --name openwis-awisc-karaf openwis-karaf


echo - AWISC Karaf Docker Container Created Successfully
echo - ------------------------------------------------------------------------
