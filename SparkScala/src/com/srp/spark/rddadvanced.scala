package com.srp.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.log4j._
import com.typesafe.config.ConfigFactory

object rddadvanced {
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val basepath = ConfigFactory.load().getString("app.dsbasepath")
    println(basepath)
    val sc = new SparkContext("local[*]", "Learn1");

    val arr = "Spark The Definitive Guide : Big Data Processing Made Simple".split(" ");
    val words = sc.parallelize(arr, 2)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val map = words.map(x => (x.toLowerCase(), 1))

    map.toDF().show()

    val pairrdd = words.map(x => (x, 1))
    pairrdd.toDF().show()

    val keys = words.keyBy(x => x.toSeq(0).toString())
    keys.flatMapValues(x => x.toUpperCase()).collect() //.foreach(println)

    keys.lookup("S").foreach(println)

    val dchars = words.flatMap(x => x.toLowerCase.toSeq).distinct().collect()

    import scala.util.Random

    val samplemap = dchars.map(c => (c, new Random().nextDouble())).toMap

    val p1rdd = sc.parallelize(arr, 2)
    val pmaprdd = p1rdd.map(x => (x, 1))
    val pkvrdd = p1rdd.keyBy(x => x.toLowerCase.toSeq(0))
    val pkv1rdd = pkvrdd.flatMapValues(x => x.toUpperCase)
    println("pkv1rdd")
    pkv1rdd.collect().foreach(println);
    val pdchars = words.flatMap(x => x.toLowerCase().toSeq).distinct().collect()
    val pdcharsmap = pdchars.map(x => (x, new Random().nextDouble())).toMap

  }
} 
