package com.srp.scala

object class1 {

  def main(args: Array[String])
  {
    println("hello")
    new Order(1, "test", 2, "test2")
  }
  
}

case class Order(orderId: Int, orderDate: String, orderCustomerId: Int, orderStatus: String) {
  println("inside customer")
}
