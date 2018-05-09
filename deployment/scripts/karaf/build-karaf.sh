#!/bin/bash
echo - ------------------------------------------------------------------------
echo - Creating Karaf Docker Image
echo - ------------------------------------------------------------------------
echo && echo

echo - ------------------------------------------------------------------------
echo - Performing clean-up...
rm -rf ../../artifacts/karaf
mkdir ../../artifacts/karaf
echo - Done.
echo

echo - ------------------------------------------------------------------------
echo - Assembling Docker Image...
echo
pwd
cp karaf.dockerfile ../../artifacts/karaf && cd ../../artifacts/karaf
sudo docker build --no-cache -f karaf.dockerfile -t openwis-karaf .

echo
echo - Karaf Docker Image Created Successfully
echo - ------------------------------------------------------------------------