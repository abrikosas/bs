# bs
# Introduction

Hardware which is tracking movement of the white billiard ball writing coordinates and other information to JSON file. Latest changes of the file are written to the socket. Scala program reads data from socket by using spark streaming library and writes to kafka topic. Next step is Spring boot program which alows easy consume from Kafka , parsing JSON and writing data to Maria DB . Information from the database is used for rendering white ball trajectory on the screen .


# Prerequisits

mvn,spark-submit, java 1.8, Kafka, MariaDB

# How to start full chain

1. start streaming socket: 
```
tail -f /tmp/test | nc -lk 4445
```


2. start read from socket and write to kafka: 
```
spark-submit --class FromSocketToKafka bs/socketToKafka/target/billiardstream-1.0-SNAPSHOT-jar-with-dependencies.jar kafkaTopic streaminghost port
```

3. start persisting from kafka to relational DB: 
```
java -jar buildstuff-0.0.1-SNAPSHOT.jar --spring.config.location=application.properties
```
