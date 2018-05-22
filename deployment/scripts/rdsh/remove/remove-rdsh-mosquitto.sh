#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Removing the RDSH Mosquitto
echo - ------------------------------------------------------------------------
echo

sudo docker rm openwis-rdsh-mosquitto -f -v && sudo docker rmi openwis-mosquitto -f

echo - RDSH Mosquitto removed

