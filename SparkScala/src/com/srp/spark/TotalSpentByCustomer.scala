package com.srp.spark
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
object TotalSpentByCustomer {
  def extractCustomerPricePairs(line: String) = {
    val fields = line.split(",")
    (fields(0).toInt, fields(2).toFloat)
  }
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "TotalSpentByCustomer")   
    val input = sc.textFile("../customer-orders.csv")
    val mappedInput = input.map(extractCustomerPricePairs)
    val totalByCustomer = mappedInput.reduceByKey( (x,y) => x + y )
    val results = totalByCustomer.collect()
    results.foreach(println)
  }
}
