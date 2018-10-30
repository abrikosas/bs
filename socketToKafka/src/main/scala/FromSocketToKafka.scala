import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer._
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.SAXException


/**
  * * @param args(0)        - port
  * * @param args(1)        - host
  * * @param args(2)        - window size
  * * @param args(3)        - mapreduce output
  */



//https://forums.databricks.com/questions/2798/how-to-write-to-kafka-from-streaming-application.html

object FromSocketToKafka extends Serializable {


  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setAppName("Biliardas").setMaster("local[4]")
    val sc = new SparkContext(conf)

    val ssc = new StreamingContext(sc, Seconds(2))

    val lines = ssc.socketTextStream("127.0.0.1", 4445)

    lines.foreachRDD( rdd => {
      rdd.foreachPartition( partition => {
        val kafkaOpTopic = "test"
        val props = new util.HashMap[String, Object]()
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
        val producer = new KafkaProducer[String, String](props)
        partition.foreach( record => {
          val data = record.toString
          print(data)
          val message = new ProducerRecord[String, String](kafkaOpTopic, null, data)
          producer.send(message)
        })
      })
    })
    ssc.start()
    ssc.awaitTermination()
  }
}