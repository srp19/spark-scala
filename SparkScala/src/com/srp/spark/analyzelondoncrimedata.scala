package com.srp.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.log4j._
import com.typesafe.config.ConfigFactory

object analyzelondoncrimedata {
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val basePath = ConfigFactory.load().getString("app.sgs")

    val spark = SparkSession
      .builder
      .appName("Analyzing London crime data1")
      .master("local[*]")
      .config("spark.sql.warehouse.dir", "file:///C:/temp")
      .getOrCreate();
    val data = spark.read.format("csv").option("header", "true").load(basePath + "/london_crime_by_lsoa.csv")
    
    data.limit(5).show()
    
    val data1 = data.drop("lsoa_code")
    
    val total_boroughs = data1.select("borough").distinct()
    
    total_boroughs.show()
    
  }
}
