package com.srp.scala

import com.typesafe.config.ConfigFactory

object tuples {
  def main(args: Array[String])
  {
    import scala.io.Source

    val basePath = ConfigFactory.load().getString("app.dg")
        
    val orderItems = Source.fromFile(basePath + "/data/retail_db/order_items/part-00000").getLines.toList
    orderItems.take(10).foreach(println)
    val t = (1,1,957,1,299.98,299.98)
    t._1
    print(t)
  }
}