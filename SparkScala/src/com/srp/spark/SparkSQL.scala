package com.srp.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.log4j._
import com.typesafe.config.ConfigFactory

object SparkSQL {
  case class Person(ID:Int, name:String, age:Int, numFriends:Int)
  
  def mapper(line:String): Person = {
    val fields = line.split(',')  
    val person:Person = Person(fields(0).toInt, fields(1), 
        fields(2).toInt, fields(3).toInt)
    return person
  }
  
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val bpath = ConfigFactory.load().getString("app.dsbasepath")
    println(bpath)
    
    val spark = SparkSession
      .builder
      .appName("SparkSQL")
      .master("local[*]")
      .config("spark.sql.warehouse.dir", "file:///C:/temp") 
      .getOrCreate()
    val lines = spark.sparkContext.textFile(bpath + "/fakefriends.csv")
    val people = lines.map(mapper)
    import spark.implicits._
    val schemaPeople = people.toDS
    schemaPeople.printSchema()
    schemaPeople.createOrReplaceTempView("people")
    val teenagers = spark
    .sql("SELECT * FROM people WHERE age >= 13 AND age <= 19")
    val results = teenagers.collect()
    results.foreach(println)
    spark.stop()
  }
}
