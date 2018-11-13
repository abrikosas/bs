#!/usr/bin/env bash


SPARK_SERVICE=`ps -ef | grep spark | grep -v grep`
SPRING_SERVICE=`ps -ef | grep consumPersist| grep -v grep`
LOGS_DIRECTORY="/opt/streaming/LOGS"
DATE=`date "+%Y-%m-%d-%H_%M_%S"`


if [ ! -d "$LOGS_DIRECTORY" ]; then
    echo "Directory $LOGS_DIRECTORY doesn't exists. I will create it"
    mkdir $LOGS_DIRECTORY
fi

if [ "{$SPARK_SERVICE:-null}" = null ]; then
	echo "spark service not running - nothing to stop" >>  $LOGS_DIRECTORY/stop_$DATE.log
else 
	echo "spark service running trying to stop it" >>  $LOGS_DIRECTORY/stop_$DATE.log 
    for pid in `ps -ef | grep spark | grep -v grep |awk '{print $2}'` ; do kill -9 $pid ; echo $pid >>  $LOGS_DIRECTORY/stop_$DATE.log  ; done
fi


if [ "{$SPRING_SERVICE:-null}" = null ]; then
	echo "spring service not running - nothing to stop"
else 
	echo "spring service running trying to stop it"
    for pid in `ps -ef | grep  consumPersist | grep -v grep |awk '{print $2}'` ; do kill -9 $pid; done
fi

