import java.util

import org.apache.kafka.clients.producer._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

//https://forums.databricks.com/questions/2798/how-to-write-to-kafka-from-streaming-application.html





object FromSocketToKafka extends Serializable {


  def main(args: Array[String]): Unit = {


    val topicName = args(0)
    val host = args(1)
    val port = args(2).toInt



    val conf: SparkConf = new SparkConf().setAppName("Biliardas").setMaster("local[4]")
    val sc = new SparkContext(conf)

    val ssc = new StreamingContext(sc, Seconds(2))

    val lines = ssc.socketTextStream(host, port)

    lines.foreachRDD( rdd => {
      rdd.foreachPartition( partition => {
        val props = new util.HashMap[String, Object]()
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
        val producer = new KafkaProducer[String, String](props)
        partition.foreach( record => {
          val data = record.toString
          print(data)
          val message = new ProducerRecord[String, String](topicName, null, data)
          producer.send(message)
        })
      })
    })
    ssc.start()
    ssc.awaitTermination()
  }
}
