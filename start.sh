#!/usr/bin/env bash 

# Title:         start stream and persist to Kafka & MariaDB
# Description:   one java program reads from socket and writes to kafka. Another one reads from Kafka and writes to Mariadb. 
#                This scipt starts these two java programs
# Author:        Algirdas Klimavicius
# Date:          2018-11-12
# Version:       1.0.0




DIRECTORY="/opt/streaming/bs"
LOGS_DIRECTORY="/opt/streaming/LOGS"
TOPIC="surikatos"
STREAM_HOST=localhost
STREAM_PORT=4445
DATE=`date "+%Y-%m-%d-%H_%M_%S"`




SPARK_SERVICE="FromSocketToKafka"
SPRING_SERVICE="buildstuff"

if [ ! -d "$DIRECTORY" ]; then
    echo "directory $DIRECTORY doesn't exists"
    exit 1
fi

if [ ! -d "$LOGS_DIRECTORY" ]; then
    echo "Directory $LOGS_DIRECTORY doesn't exists. I will create it"
    mkdir $LOGS_DIRECTORY
fi


echo "changing directory to $DIRECTORY"
cd $DIRECTORY


if ps ax | grep -v grep | grep $SPARK_SERVICE > /dev/null
then
    echo "$SPARK_SERVICE service running, everything is fine"
else
    echo "$SPARK_SERVICE is not running"
        nohup /usr/local/spark/bin/spark-submit --class FromSocketToKafka socketToKafka/target/billiardstream-1.0-SNAPSHOT-jar-with-dependencies.jar $TOPIC $STREAM_HOST $STREAM_PORT > $LOGS_DIRECTORY/stream_$DATE.log 2>&1 &
fi


if ps ax | grep -v grep | grep $SPRING_SERVICE > /dev/null
then
	 echo "$SPRING_SERVICE service running, everything is fine"
else 
        echo "$SPRING_SERVICE is not running"
	nohup /usr/bin/java -jar consumPersist/target/buildstuff-0.0.1-SNAPSHOT.jar --spring.config.location=application.properties > $LOGS_DIRECTORY/persist_$DATE.log 2>&1 &
fi
