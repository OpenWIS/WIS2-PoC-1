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

while ! nc -z openwis-awisc-mysql 3306; do
  echo - Waiting...
  sleep 1
done

echo
echo - Waiting for Elasticsearch to start...
echo

while ! nc -z openwis-awisc-elasticsearch 9200; do
  echo - Waiting...
  sleep 1
done

echo
echo - Executing deployment script
echo

bin/client -f awisc-docker-injected-karaf-deployment-script && \
  sleep 40 && \
  bin/client -f awisc-docker-injected-karaf-deployment-script-2

echo
echo - Deployment completed
