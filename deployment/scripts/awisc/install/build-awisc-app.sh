#!/bin/bash
if [ $# -eq 0 ]
then
    echo - ------------------------------------------------------------------------
    echo - Building AWISC for default URL
    echo - ------------------------------------------------------------------------
    echo & echo
    cd ../../../
    pwd
    mvn -Dmaven.repo.local=deployment/artifacts/awisc clean install -P awisc
else
    echo - ------------------------------------------------------------------------
    echo - Building AWISC for:  $1
    echo - ------------------------------------------------------------------------
    echo & echo
    cd ../../../
    mvn -Dmaven.repo.local=deployment/artifacts/awisc clean install -P awisc -Dui.base.href=$1
fi


