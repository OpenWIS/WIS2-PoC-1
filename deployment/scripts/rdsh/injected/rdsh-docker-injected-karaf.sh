#!/bin/bash
cd /opt/karaf
bin/stop
bin/start clean &

echo - Waiting for karaf to start...
echo

while ! nc -z localhost 8101; do
  echo - Waiting...
  sleep 1
done

echo
echo - Waiting for MySQL to start...
echo

while ! nc -z openwis-rdsh-mysql 3306; do
  echo - Waiting...
  sleep 1
done

echo
echo - Waiting for Mosquitto to start...
echo

while ! nc -z openwis-rdsh-mosquitto 1883; do
  echo - Waiting...
  sleep 1
done

echo
echo - Executing deployment script
echo

bin/client -f rdsh-docker-injected-karaf-deployment-script

echo
echo - Deployment completed
