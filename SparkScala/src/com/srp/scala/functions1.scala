package com.srp.scala

object functions1 {
    def main(args: Array[String]) {
    
      println("Hello")
      
      sum(i => i, 2, 5)
      
      sum(i => i*i, 2, 5)
      
      sum(i => i*i*i, 2, 5)
    
    }
    
    def sum(func: Int => Int, lb: Int, ub: Int)
    {
        var total = 0
        for(element <- lb to ub)
        {
          total += func(element)
        }
        println("total : " + total.toString())
        total
    }
}