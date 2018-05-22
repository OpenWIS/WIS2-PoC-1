#!/bin/bash
echo - Finalizing FTPD configuration
echo

apt-get install -y netcat

( echo "openwis" ; echo "openwis" ) | pure-pw useradd openwis -f /etc/pure-ftpd/passwd/pureftpd.passwd -m -u ftpuser -d /home/ftpusers/openwis


echo
echo - Waiting for FTPD to start...

while ! nc -z localhost 21; do
  echo - Waiting...
  sleep 1
done

echo
echo - FTPD starterd
echo

echo
echo - Configuration completed
exit