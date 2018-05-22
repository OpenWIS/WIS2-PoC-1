#!/bin/bash
echo - Configuring Mosquitto
echo
PASSWDFILE=/etc/mosquitto/passwd

if [ -f $PASSWDFILE ]; then
    echo "converting password file"
    mosquitto_passwd -U $PASSWDFILE
fi

echo
echo - Configuration completed

/usr/sbin/mosquitto -c /mosquitto/config/mosquitto.conf

echo
echo - Starting Mosquitto

exec "$@"