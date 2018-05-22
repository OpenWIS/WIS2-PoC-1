#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Creating RDSH MySQL Docker Container
echo - ------------------------------------------------------------------------
echo

echo - Creating Docker Container...
sudo rm -rf $(pwd)/../../../artifacts/rdsh-mysql
mkdir $(pwd)/../../../artifacts/rdsh-mysql
sudo docker create --name=openwis-rdsh-mysql -p 3338:3306 -e MYSQL_ROOT_PASSWORD="123456" \
    --network openwis-docker-bridge \
    -v $(pwd)/../../../artifacts/rdsh-mysql:/var/lib/mysql mysql/mysql-server:5.7 
echo


echo - RDSH MySQL Docker Container Created Successfully
