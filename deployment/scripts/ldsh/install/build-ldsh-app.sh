#!/bin/bash
if [ $# -eq 0 ]
then
    echo - ------------------------------------------------------------------------
    echo - Building LDSH for default URL
    echo - ------------------------------------------------------------------------
    echo & echo
    cd ../../../../
    pwd
    mvn -Dmaven.repo.local=deployment/artifacts/ldsh clean install -P ldsh
else
    echo - ------------------------------------------------------------------------
    echo - Building LDSH for:  $1
    echo - ------------------------------------------------------------------------
    echo & echo
    cd ../../../../
    mvn -Dmaven.repo.local=deployment/artifacts/ldsh clean install -P ldsh -Dui.base.href=$1
fi


