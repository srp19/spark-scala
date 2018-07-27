package com.srp.scala

import com.typesafe.config.ConfigFactory

object basicIO {
  def main(args: Array[String]) {
    import scala.io.Source

    val basePath = ConfigFactory.load().getString("app.dg")

    val orderItems = Source.fromFile(basePath + "/data/retail_db/order_items/part-00000").getLines.toList
    orderItems(0)
    orderItems.take(10).foreach(println)
    println("list size : " + orderItems.size.toString())

    val orderItemsFilter = orderItems.filter(orderItem => orderItem.split(",")(1).toInt == 2)
    val orderItemsMap = orderItemsFilter.map(orderItem => orderItem.split(",")(4).toFloat)

    println("order items map list size : " + orderItemsMap.size.toString())
    println("order items map")
    
    orderItemsMap.take(10).foreach(println)
    
    println()
    println("sum function")
    
    println(orderItemsMap.sum)

    println("reduce")
    
    println(orderItemsMap.reduce((total, orderItemSubtotal) => total + orderItemSubtotal))
    
    println("reduce _")

    println(orderItemsMap.reduce(_ + _))
    
    
    orderItemsMap.reduce(_ + _)
    
    
  }
}