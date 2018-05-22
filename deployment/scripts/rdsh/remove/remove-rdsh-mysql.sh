#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Removing the RDSH MySQL Container
echo - ------------------------------------------------------------------------
echo

sudo docker rm openwis-rdsh-mysql -f -v

echo - RDSH MySQL Container removed

