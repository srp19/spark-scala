package com.srp.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.log4j._
import com.typesafe.config.ConfigFactory   

object learn1 {
   def main(args: Array[String]) {
     Logger.getLogger("org").setLevel(Level.ERROR)
     val basepath = ConfigFactory.load().getString("app.dsbasepath") 
     println(basepath)
     val sc = new SparkContext("local[*]","Learn1");
     val lines = sc.textFile(basepath + "/fakefriends.csv", 2)
     val rdd = lines.map(x=> {
        val fields = x.split(",")
        val age = fields(2).toInt
        val numFriends = fields(3).toInt
        (age, numFriends)
     })
     val sqlContext = new SQLContext(sc)
     import sqlContext.implicits._
     val df = rdd.toDF()
     df.show();
     println(df.count())
     }
   } 
