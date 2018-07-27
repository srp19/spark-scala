package com.srp.spark
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import org.apache.spark.sql
object RatingsCounter2 {
  def main(args: Array[String]) {
    System.out.println("Ratings Counter 2");
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "RatingsCounter")
    val lines = sc.textFile("../ml-100k/u.data")
    val ratings = lines.map(x => x.toString().split("\t")(2))
    val results = ratings.countByValue()
    val sortedResults = results.toSeq.sortBy(_._1)
    sortedResults.foreach(println)
  }
}
