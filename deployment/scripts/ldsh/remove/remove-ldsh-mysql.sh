#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Removing the LDSH MySQL Container
echo - ------------------------------------------------------------------------
echo

sudo docker rm openwis-ldsh-mysql -f -v

echo - LDSH MySQL Container removed

