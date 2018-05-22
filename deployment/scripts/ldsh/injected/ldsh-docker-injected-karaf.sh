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

while ! nc -z openwis-ldsh-mysql 3306; do
  echo - Waiting...
  sleep 1
done

echo
echo - Waiting for FTPD to start...
echo

while ! nc -z openwis-ldsh-ftpd 21; do
  echo - Waiting...
  sleep 1
done

echo
echo - Executing deployment script
echo

bin/client -f ldsh-docker-injected-karaf-deployment-script

echo
echo - Deployment completed
