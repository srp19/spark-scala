package com.srp.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql.SQLContext
import org.apache.log4j._
import com.typesafe.config._

object WordCountBetterSorted {
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val basepath = ConfigFactory.load().getString("app.dsbasepath") 
     println(basepath)
    val sc = new SparkContext("local", "WordCountBetterSorted") 
    val sqlC = new SQLContext(sc)
    import sqlC.implicits._
    
    val input = sc.textFile(basepath + "/book.txt")
    
    val words = input.flatMap(x => x.split("\\W+"))
    
    words.toDF().show(2, false)
    val lowercaseWords = words.map(x => x.toLowerCase())
    
    // val countByValue = lowercaseWords.countByValue()
    
    val wordCounts = lowercaseWords.map(x => (x, 1))
                .reduceByKey( (x,y) => x + y )
                
    wordCounts.toDF().show(2)
                
    val wordCountsSorted = wordCounts.map( x => (x._2, x._1) )
                    .sortByKey(false)
                    
    for (result <- wordCountsSorted.take(10)) {
      val count = result._1
      val word = result._2
      println(s"$word: $count")
    }
  }
}
