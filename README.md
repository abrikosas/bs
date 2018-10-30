# bs
1. start streaming socket: tail -f /tmp/test | nc -lk 4445
2. start read from socket and write to kafka: spark-submit --class FromSocketToKafka bs/socketToKafka/target/billiardstream-1.0-SNAPSHOT-jar-with-dependencies.jar
3.
