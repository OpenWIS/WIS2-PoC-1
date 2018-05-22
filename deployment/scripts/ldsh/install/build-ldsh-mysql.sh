#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Creating LDSH MySQL Docker Container
echo - ------------------------------------------------------------------------
echo

echo - Creating Docker Container...
sudo rm -rf $(pwd)/../../../artifacts/ldsh-mysql
mkdir $(pwd)/../../../artifacts/ldsh-mysql
sudo docker create --name=openwis-ldsh-mysql -p 3337:3306 -e MYSQL_ROOT_PASSWORD="123456" \
    --network openwis-docker-bridge \
    -v $(pwd)/../../../artifacts/ldsh-mysql:/var/lib/mysql mysql/mysql-server:5.7 
echo


echo - LDSH MySQL Docker Container Created Successfully
