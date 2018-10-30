# sparkhbaseoncluster

spark-submit -v  --master yarn --deploy-mode  cluster --class HBaseAttackStream target/sparkstreamhbase-1.0-SNAPSHOT-jar-with-dependencies.jar 19095 localhost test:attacksv3 af 1 /tmp/outstream

/**
  * * @param args(0)        - port
  * * @param args(1)        - host
  * * @param args(2)        - hbase table name
  * * @param args(3)        - column family name
  * * @param args(4)        - window size
  * * @param args(5)        - mapreduce output
  */
  
  
  
