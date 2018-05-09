#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Removing the AWISC MySQL Container
echo - ------------------------------------------------------------------------
echo

sudo docker rm openwis-awisc-mysql -f -v

echo - AWISC MySQL Container removed
echo - ------------------------------------------------------------------------

