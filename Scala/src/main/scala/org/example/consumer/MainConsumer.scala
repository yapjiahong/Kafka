package org.example.consumer

import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig

import scala.collection.JavaConverters._
//import scala.collection.JavaConverters

import org.apache.kafka.clients.consumer.KafkaConsumer

object MainConsumer {
  def main(args: Array[String]): Unit = {
    println("length of args: " + args.length)
    if (args.length == 2) {
      val topic: String = args.apply(0)
      val bootStrapServers: String = args.apply(1)
      consumeFromKafka(topic, bootStrapServers)
    }
    else
      println("{topic} {bootstrap servers}")
  }

  def consumeFromKafka(topic: String, bootStrapServers: String) = {
    val props = new Properties()
    props.put("bootstrap.servers", bootStrapServers)
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
    props.put("group.id", "cloudera_mirrormaker")
    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
    consumer.subscribe(util.Arrays.asList(topic))
    while (true) {
      val record = consumer.poll(1000).asScala
      for (data <- record) {
        print("key: " + data.key() + "\t")
        println("value: " + data.value())
      }
    }
  }
}
