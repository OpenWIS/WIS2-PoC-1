#!/bin/bash
echo - Ensuring Docker network exists...

sudo docker network create --driver bridge openwis-docker-bridge || true

echo - Network openwis-docker-bridge ready
