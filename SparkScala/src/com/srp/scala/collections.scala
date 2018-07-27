package com.srp.scala

object collections {
  def main(args: Array[String])
  {
    println("collections")
    
    val list = List(1,2,3)
    list.foreach(println)
    
    val array = Array(1,2,3)
    array.foreach(println)
    
    val set = Set(1,2,3)
    set.foreach(println)
    
    val map = Map("a"->1,"b"->2,"c"->3)
    map.foreach(println)
   
    /*
      Collections
      
      Scala collections are categorized into 3 types
      
      Seq
      Set
      Map
      
	      Seq
          Sequence have length
          Elements in Sequence can be accessed using prefix
          eg: scala.Array, scala.collection.immutable.List etc
          Classes are divided into Seq classes and Buffer classes
          Vector and List are considered as Seq classes and they are more frequently used
          Array is special type of collection analogous to Java Array
          We will get into the details of Array and List
          Array is mutable while List is immutable
          Seq have 2 sub traits – IndexedSeq and LinearSeq
				Set
          Set is iterable which contain unique elements (no duplicates)
          As duplicates are not allowed, there is no length (Seq have both length and index)
          Even though we can access data using index, it might not return same value always
          By default Set is immutable, but there is mutable one as well
          Let us see how we can use mutable and immutable Set in a demo
          If the Set contain elements for which implicit Ordering defined, we can use SortedSet to sort the data
        Map
          Map is Iterable of Key and Value pairs
          Each element can be defined as key -> value or (key, value)
          We will look into few collection classes such as Array,List,Set and Map.
                
     */
  }
}