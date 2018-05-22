#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Creating AWISC MySQL Docker Container
echo - ------------------------------------------------------------------------
echo

echo - Creating Docker Container...
sudo rm -rf $(pwd)/../../../artifacts/awisc-mysql
mkdir $(pwd)/../../../artifacts/awisc-mysql
sudo docker create --name=openwis-awisc-mysql -p 3339:3306 -e MYSQL_ROOT_PASSWORD="123456" \
    --network openwis-docker-bridge \
    -v $(pwd)/../../../artifacts/awisc-mysql:/var/lib/mysql mysql/mysql-server:5.7 
echo


echo - AWISC MySQL Docker Container Created Successfully
