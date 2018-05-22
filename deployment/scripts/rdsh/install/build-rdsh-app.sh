#!/bin/bash
if [ $# -eq 0 ]
then
    echo - ------------------------------------------------------------------------
    echo - Building RDSH for default URL
    echo - ------------------------------------------------------------------------
    echo & echo
    cd ../../../../
    pwd
    mvn -Dmaven.repo.local=deployment/artifacts/rdsh clean install -P rdsh
else
    echo - ------------------------------------------------------------------------
    echo - Building RDSH for:  $1
    echo - ------------------------------------------------------------------------
    echo & echo
    cd ../../../../
    mvn -Dmaven.repo.local=deployment/artifacts/rdsh clean install -P rdsh -Dui.base.href=$1
fi


