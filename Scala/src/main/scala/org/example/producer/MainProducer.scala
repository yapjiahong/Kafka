package org.example.producer

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object MainProducer {

  def main(args: Array[String]): Unit = {
    println("length of args: " + args.length)
    if (args.length == 2) {
      val topic: String = args.apply(0)
      val bootStrapServers: String = args.apply(1)
      writeToKafka(topic, bootStrapServers)
    }
    else
      println("{topic} {bootstrap servers}")
  }

  def writeToKafka(topic: String, bootStrapServers:String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", bootStrapServers)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)

    var flag:Boolean = true
    while(flag){
      print("> ")
      val input = scala.io.StdIn.readLine()
      val record = new ProducerRecord(topic, input+"_key", input+"_value")
      producer.send(record)
      if (input == "stop") flag = false
    }
    producer.close()
  }

}
