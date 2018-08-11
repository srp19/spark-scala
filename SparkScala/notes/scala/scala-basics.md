#Note for Programming in Scala

<br>

##Chp.0 SBT & Scala Interpreter

1. Call scala interpreter by `sbt`.

	```scala
	// enter scala interpreter
	console

	// exit scala interpreter
	:quit

	// help info
	:help

	// exit sbt
	exit
	```

2. You can escape wrong input by pressing `enter` two times.

	```scala
	var oops =
    |
    |
	You typed two blank lines.  Starting a new command.
	```
<br>

##Chp.1 A Scalable Language

0. Scala is a **hybrid of functional and object-oriented** language. It is also **statically typed**.

	> ####[stackoverflow.com#Difference between strongly/statically typed language](http://stackoverflow.com/questions/2690544/what-is-the-difference-between-a-strongly-typed-language-and-a-statically-typed)

1. **Functional programming** has two main concepts:
 	- Function is a first-class value.
	- **Any function should map input values to output values, instead of changing input data in function scope**.

2. By above definition, Scala treat function as a value, and **its input parameters are declared as immutable `val` constant rather than muttable `var` variables**.

    ```scala
    // Because x is a val constant, so error will pop.
    def immutable(x: Int): Unit = {
      x = x + 1
      println(x)
    }
    // error: reassignment to val
    // x = x + 1
    ```
<br>

##Chp.2 First Step in Scala

0. Define variables by **constant `val` or variable `var`**. **Constant in Scala is immutable and unreassignable**.

1. Scala is able to figure out what types you leave off, it is called **type inference**.

	```scala
	// In scala, String is acutally implemented by:
	// java.lang.String
	val msg = "Greeting!"
	```

2. In Scala you specify a variable type by `:`. Function is first-class so **you can explicitly specify its type too**.

	```scala
	val msg: java.lang.String = "Hello"
	// msg: String = Hello

	// or use simple name String in Scala
	val msg: String = "Hello"

	def typed(x: Int, y: Int): Int = x
	```

3. **Function must explicitly provide result type when it is recursive**. If the function is not recursive, then scala compiler will be able to infer its result type.

    ```scala
    def recur(x: Int) = {
      if (x <= 10) recur(x + 1)
      else println(x)
    }
    // error: recursive method recur needs result type
    // if (x <= 10) recur(x + 1)
    //               ^

    def max(x: Int, y: Int) = if (x > y) x else y

    max(1,5)
    //res0: Int = 5
    ```

4. We can return `Unit` type as `void` in Java, or C. The `Unit` type indicates that function returns no interesting value.

	```scala
	def	hello(x: Int, y: Int): Unit = {
		// println return Unit type
		println("X: " + x + " Y: " + y);
	}

	hello(1, 2)
	// X: 1 Y: 2
	```

5. Same as Ruby, Scala use `i += 1` or `i = i + 1` instead of `i++` or `++i` in Java or C. That is because **the assignment operator is actually create a new value from the copies of `i` and integer literal `1`**.

6. Same as `String` literal or `Integer` literal, **for the function, we cane have function literal too**. If the function literal has only one parameter and consist of single statment which also takes single argument,  then we can rephrase it as `partial applied function`.

	```scala
	// Syntax of function literal
	(x: Int, y: Int) => function_body(x, y)

	args.foreach(arg => println(arg))

	// partial applied function
	args.foreach(println)
	```

7. The `for-in` loop:

	```scala
	// Syntax for arg in args
	for(arg <- args) function_body

	for(arg <- 0 to 1) println(arg)
	// 0
	// 1
	```
<br>

##Chp3. Next Step in Scala

1. Parameterize arrays with types:

	```scala
	val big = new java.math.BigInteger("12345")

	val greetings = new Array[String](3);
	greetings(0) = "Hello"
	greetings(1) = ", "
	greetings(2) = "World! \n"

	for(e <- 0 to 2) println(greetings(i))

	// or explicitly specify val type
	val greetings: Array[String] = new Array[String](3)
	```

2. **Above array declare as `val` constant is because you should not reassign array variable, but operate its elements**. Same idea as **Object** in Scala.

3. Scala access index by parentheses, not square brackets as in Java.

4. Same as Ruby, everything in Scala is object, so it can access some method by dot and parentheses.

	```scala
	(0).equals(2)
	// Boolean = false
	```

	**We can also ignore parentehses and dot if the receiver of method call is explicitly specified**. **White space is not required too**.

	```scala
	// receiver is 0 object
	0 to 2 === (0).to(2)

	// cannot call println by this way,
	// println has no explicit receiver
	println 10

	Console println 10
	```

5. **If a method takes only one parameter, you can call it without dot and parantheses**.

	```scala
	(0 to 2).equals(0.to(2))
	// Boolean = true
	```

6. If an Integer object call `+`, `-`, `*`, or `/` method (any arithmetic operators in Java) with dot, **be sure to wrap it by parentheses. Otherwise Scala may treat it as numerical type instead(`Double` or `Float`)**.

	```scala
	1.+(2)
	// warning: there were 1 deprecation warning(s);
	// re-run with -deprecation for details
	// Double = 3.0

	(1).+(2)
	// Int = 3
	```

7. When you apply parentheses surrounding one or more values to a variable, Scala will transform the code to call `apply` method instead. **This is general rule**.

	> apply is similar to this method in c# class. this method is used to retrieve by index. this has both get and set. 

	> this keyword get method is similar to apply.
	> this keyword set method is similar to scala update.

	> An application of an object to some arguments in parentheses will be transformed to an `apply` method call too. **That object must define `apply` method so it can be successfully compiled**.

	```scala
	val greetings = new Array[Int](3);
	greetings(0) = 1
	greetings(1) = 2
	greetings(2) = 3


	println(greetings(0))

	// Above println statement is equivalent to
	greetings.apply(0)
	```

8. Similarly, when an assignment comes from a variable which take parentheses and one or more arguments have been applied, then the compiler transform code to call `update` method. **This is the general rule too.**

	```scala
	val gret = new Array[Int](3)

	gret(0) = 1
	gret(1) = 2
	gret(2) = 3

	// the same as
	gret.update(0, 1)
	gret.update(1, 2)
	gret.update(2, 3)
	```

9. A more concise way to initialize an array:

	```scala
	val int_ary = Array(1, 2, 3)

	val str_ary = Array("String", "In", "Array")

	// verbose ver
	val str_ary = Array.apply("String", "In", "Array")
	```

	> What this code actually doing is calling **factory method `apply`, which creates and returns the new array**. **This `apply` is defined by companion object in `Array` class**.

10. `List` is an immutable sequence. `Array` is mutable sequence because its elements can be changed. **You can infer that array's each element as `var` and list's each element as `val`**.

	```scala
	val list = List(1,2,3,4,5)
	val strs = List("S", "t", "r", "i", "n", "g")
	```

	> Differs from `java.util.List` type, `scala.List` is always immutable. **Though its element cannot update value, but the element object can always changed its member field**.

	```scala
	class B {
		var a = 0
	}

	val ary = Array(new B(), new B())
	// ary: Array[B] = Array(B@5967072b, B@7b07c5e7)

	ary(0) = new B()
	ary
	// Array[B] = Array(B@75c4957a, B@7b07c5e7)

	val list = List(new B(), new B())
	// List[B] = List(B@72dcecd9, B@641d579)

	list(0).a = 5
	// object's field is mutable
	// list(0).a: Int = 5

	list(0) = new B()
	// immutable element, error pops
	// error: value update is not a member of List[B]
	```

11. List concatenation method `:::` returns a new list combined two lists.

	```scala
	val one = List(1)
	val two_three = List(2, 3)

	val all = one ::: two_three
	//List[Int] = List(1, 2, 3)
	```

12. List prepend method `::`

	```scala
	val two = List(2)
	val one_two = 1 :: two
	// List(1, 2)
	```

	> In the expression `1 :: two`, `::` is actually **right operand**. If a method is invoked as operand notation, such as `a * b`, then the method is invoke as **left operand** as in `a.*(b)`.
	> **If the method name ends in a colon `:`, then the method is invoked as right operand. So `1 :: two` is the same as `two.::(1)`**.

13. **`Nil` is a short hand of empty list**.

	```scala
	val con = 1 :: 2 :: 3 :: Nil
	// List[Int] = List(1, 2, 3)
	```
	> Because `::` is a right operand and defined in `Nil` or `List`, but not in `1` integer pbject. So we must put `Nil` at the end.


14. Scala rarely use `append` method is because **the time complexity grows linerly with size of list. Whereas `::` takes constant time**. Scala implement `List` as single pointer linked list. For prepend method `::`, it only needs to create new list node then point and reset head node. For `append` method, it has to iterate to tail node and point to new node.

	> ####[programmers.stackexchange.com#append vs prepend](http://programmers.stackexchange.com/questions/216714/why-appending-to-a-list-in-scala-should-have-on-time-complexity)
	> ####[docs.scala-lang.org#Collections performance characteristics](http://docs.scala-lang.org/overviews/collections/performance-characteristics.html)

15. **`Tuple` is an immutaple but allowed multiple types collection**.

	```scala
	val pair = (99, "Luftballons")
	// Tuple2

	// access first elemenet by _1
	println(pari._1)
	println(pari._2)
	```

	> `Tuple2` type is for the size 2 tuple, although conceptually you can create tuples of any length, **Scala only defined the length up to 22 `Tuple22` in library**.

16. **Tuple's access method is one-based(`_1 to _N`) instead of zero-based(`0 to n-1`) in array's index**. It is designed to distinguish ambiquity of syntax due to `apply` in array always return same type, but tuple may have different type in each element.

17. Scala provide 3 traits for the `Set` collection, two subtraits are `scala.collection.immutable.Set` and `scala.collection.mutable.Set`. By default scala returns immutable set when you invoke a `Set`. Immutable set cannot change element values.

	```scala
	val x = Set(1, 2, 3)
	// x: scala.collection.immutable.Set[Int] = Set(2, 3)

	import scala.collection.mutable.Set
	var x = Set(1, 2, 3)
	// x: scala.collection.mutable.Set[Int] = Set(2, 3)

	// explicitly initiation
	val x = scala.collection.immutable.Set(2, 3)
	// x: scala.collection.immutable.Set[Int] = Set(2, 3)
	```

18. Set is a collection with unique elements. join method `+=`. **For immutable set, that `set += 1` is actually as `set = set + 1` which reassign to a newly created set(so the `set` must be a `var`). For mutable set, that trait provides method `+=` which add element to that set.**

    ```scala
    var x = scala.collection.immutable.Set(1, 2)

    x.+(5)
    // returns new Set(1, 2, 5)
    // Set(1, 2, 5)

    (x.+(5)).equals(x)
    // Boolean = false

    x
    // Set(1, 2)

    x += 5
    // x = x + 5, x is a var
    // returns new Set(1, 2, 5)

    val y = scala.collection.mutable.Set(1, 2)
    (y += 5).equals(y.+(5))
    // Boolean = true
    ```

19. There also has two other subtraits which are `scala.collection.immutable.HashSet` and `scala.collection.mutable.HashSet`. Default is immutable hashset too.

	```scala
	import scala.collection.mutable.Map

	val map = Map[Int, String]()
	map += (1 -> "Hi")
	map += (2 -> "This")
	map += (3 -> "World")

	// For immutable Map
	val i_map = scala.collection.immutable.Map(
		1 -> "Hi", 2 -> "This", 3 -> "World"
	)

	// Declare as var to make it reassignable.
	import scala.collection.immutable.Map
	var i_map = Map(1 -> "Hi")
	i_map += (2 -> "This", 3 -> "World")

	i_map
	// Map(1 -> Hi, 2 -> This, 3 -> World)

	println(i_map(1))
	// Hi
	```

	> **Scala allows you invoke `->` on any object which returns a two element tuple with key and value**. This mechanism is called **implicit conversion**.

<br>

##Chp.4 Classes and Objects

1. In Java:

	|Modifier| World | Subclass| Same package | Self |
	|-----|----|-----|----|------|
	|public|x|x|x|x|
	|protected||x|x|x|
	|none (default)|||x|x|
	|private||||x|

	But in Scala, **makes members public is by not explicitly specifying access modifier**. It is different from Java. **Scala set public as default access level**.

2. Parameters in a method are `val` not `var`. It is immutable inside the method.

3. **You can leave curly braces off if method computes only one singe result expression(not statement)**.

	```scala
	def mth(x: Int): Unit = println(x)
	```

4. **If a method's result expression has side effect(mutation, reassignment), then its result type will be `Unit`**. We can leave off the **equal sign** and put only curly braces to **declare its result type to `Unit`**.

	```scala
	var x = 10
	def add(y: Int): Int = x += y
	// Type mismatch
	// found   : Unit
 	// required: Int

 	def add(y: Int): Unit = x += y

 	// declare its result type to Unit
 	def add2(y: Int) { x += y }

 	add(5)
 	add(5)

 	x
 	// 20
	```

5. If you declare method result type to `Unit`, **then every kinds of result type from expression/statement will be converted to `Unit` and its value lost**. For example:

	```scala
	def convertion() { "String" }

	convertion
	// prints nothing, Unit type returned

	def convertion(): String = { "String!" }

	convertion
	// String = String!
	```

6. The rules of **Semicolon Inference**:

	Semicolon will not apply if:

	1. If a line ends in a word that would not be a legal statment, such as `.` or infix operator.
	2. The next line begins with a word that cannot start a statment.
	3. The line ends with brackets, or parentehses because  that brackets cannot contain multiple statements.

	```scala
	// multiple statments in single line
	println("Hi"); println("This")

	// case 1
	x +
	y

	// case 2
	x =
	- 1

	// case 3
	(x
	+ 5
	)
	```

	> **Expression is a kind of statement, it produces result values.**. Call a `var`, `object`, `val`, `reassignment` are all expressions. **Declare a `var`, or `val` is not expressions(result type is `Unit`)**.

7. **Use keyword `object` to create singleton object**.

8. When a singleton object shares the same name as other class name, that singleton object is called class's **companion object** and that class is **companion class**.

9. **A class and its companion object can access each other's private members**.

10. In Scala, companion object is a similar idea of static method in Java.

11. **Defind a singleton object does not define a type. Only campanion class defines a type**.

12. Singleton object cannot use `new`, but it can extend from superclass or mixin traits, **which becomes to an instance of that superclass or traits, then able to called their instance methods**.

	```scala
	class G {
		def g() { println("instance method g") }
	};

	object G {
		def gg() { println("singleton method g") }
	}

	object V {}

	V.g
	// instance method g

	V.gg
	// cannot access G's singleton methods.
	// error: value gg is not a member of object V
	```

13. One difference between singlton object and class is that **singleton object cannot take parameters**, whereas class can. Each singleton object is implemented as an **synthetic class** referenced from a static variable. **So they have the same initialization semantics as Java statics**.

	> The name of synthetic class is the singleton object's name plus dollar sign: `object H` to `class H$`.

14. A singleton object does not share the name with other class is called **standalone object**.

15. To run a Scala program, you have to supply a **standalone singleton object with main method that takes one parameter `Array[String]` and has result type Unit**. Then name the file should have the same name as that standalone object by convention.

	```scala
	// Hello.scala
	object Helllo {
		def main(args: Array[String]) {
			println("Hello")
		}
	}
	```

16. Scala implicitly imports `java.lang` and `scala` package and all the members of singleton object `Predef` into every scala source file.

17. **A Scala sciprt file must end with a result expression**. Otherwise it is a Scala non-script file.

    ```scala
    // Script.scala have just single line
    println("Script file")
    ```
    Run scripts by scala REPL

    ```sh
    » scala Script.scala
    Script file.
    ```


18. `sbt` project structure, and its `compile`, and `run` commands. Use `sbt` to manage project. Use **scala REPL** to run scala scripts.

	> ####[www.scala-sbt.org#Hello.html](http://www.scala-sbt.org/release/tutorial/Hello.html)

19. **Scala provides a trait, `scala.App`(`scala.Appliction` trait has been deprecated since Scala 2.9)**, which save typing code for `main` method in your standalone object. The `App` trait actually declare `main` method. The code in curly braces is collected to **primary constructor** of singleton object. And is executed when the synthetic class is initialized.

	> ####[API: scala.App](http://www.scala-lang.org/api/current/index.html#scala.App)

	```scala
	// AppTrait.scala
	object StandaloneObject extends App {
  	for(arg <- args) println(arg)
	}
	```

	Then compile by `scalac` and run `scala`:

	```sh
	» scalac AppTrait.scala

	» ls
	StandaloneObject$$anonfun$1.class
	StandaloneObject$.class
	StandaloneObject$delayedInit$body.class
	StandaloneObject.class

	» scala StandaloneObject Hello World
	Hello
	World
	```

20. You can just run scala script by calling that standalone object:

	```scala
	// AppTrait.scala
	object StandaloneObject {
  	for(arg <- args) println(arg)
	}

	StandaloneObject
	```

	Then run **`scala` REPL(Read-Evaluate-Print Loop**).

	```sh
	» scala AppTrait.scala Hello World
	Hello
	World
	```

21. **Caveat** for `scala.App` or `scala.Application`:

	1. Use `scala.App` in single-threaded program, some JVM has restrictions of its threading model.
	2. Avoid overriden the `main` method in singleton object.
	3. `scala.Application` cannot access `args`, beware.

<br>

##Chp.5 Basic Types and Operations

1. Basic types:

	|Value Type|Range|
	|---|---|
	|Byte|-(2^7) to 2^7 - 1|
	|Short|-(2^15) to 2^15 - 1|
	|Int|-(2^31) to 2^31 - 1|
	|Long|-(2^63) to 2^63 - 1|
	|Char|16-bit unsigned Unicode char 0 to (2^16)-1|
	|String|Sequence of Chars|
	|Float|32-bit IEEE single-precision float|
	|Long|64-bit IEEE double-precision float|
	|Boolean|true or false|

	**`15 bits => (2^15 - 1)` because `0` holds one binary representation**.

2. **Literals**:

	```scala
	// hex literal => prefix 0x
	val hex = 0x10F

	// octal literal => prefix 0
	val oct = 035

	// long literal => suffix L or l
	val long = 45L

	// short literal => cast Short type
	val short: Short = 367

	// byte literal => cast Byte type
	val byte: Byte = 127

	// double literal => decimal with/no suffix d or D
	val double = 3.14159
	val double = 3.14159d

	// double with power of 10
	val double = 3.14E5
	val double = 3.14E5D
	// 3.14 * 10^5

	// float literal => suffix f or F to decimal
	val float = 3.14.159f
	```

3. Character literals:

	```scala
	// Char => single quote
	val char = 'a'
	// Char = A

	// Char => backslash with ASCII code value
	val char = '\101'
	// Char = A

	// Char => backslash with unicode value
	val char = '\u0041'
	// Char = A
	```

	**You can apply Unicde values to anywhere in Scala**:

	```scala

	val B\u0041\u0044 = 1
	// BAD: Int = 1

	v\u0061r B\u0061D = 1
	// BaD: Int = 1
	```

4. String Literals:

	|Special Literal|Meaning|
	|---|---|
	|\n | new line \u000A |
	|\b |backsapce \u0008 |
	|\t |tab \u0009|
	|\f |form feed \u000c|
	|\r |carriage return \u000d|
	|\" |double quote \u0022|
	|\' |single quote \u0027|
	|\\ |back slash \u005c|

	```scala
	// use char literal
	val hello = "hello"
	// hello

	val escapes = "\\\"\'"
	// \"'
	```

	Or use `"""` to make multiple lines Strings.

	```scala
	println("""Welcome to Multiple Lines:
					"Hello\"
					\World\""")
	```

	**To indent the leading space, use pipe `|` character then call `stripMargin`**.

	```scala
	println("""|Hello\
	           |World\""".stripMargin)
	```

5. **Symbol literals**: If you declare same symobl literal twice, it will refer to same symbol object.

	```scala
	// syntax: prefix `
	val s = `aSymbol
	```

6. `Operators are methods`, those operators may have serveral **overloaded** methods that take different parameter types.

	```scala
	1 + 2

	// overload to Long parameter type
	1 + 2L
	```

7. **Infix operators can concise the syntax as in 3.4**

	```scala
	"String".indexOf('s')

	"String" indexOf 'S'
	```

8. Prefix and postfix operators are **unary**, which means they only take one operand.

	The only prefix operators are `+`, `-`, `!`, `~`, you can call method `unar_` append these four operators to prepend it to receiver.

	```scala
	-2.0 == (2.0).unary_-
	+2.0
	~0xfff == (0xfff).unary_~
	!true
	```

	**`p.unary_*` cannot prepend `*` as `*p` because `*` is not the prefix operators, instead, it actually call customed `unary_*` method. Note that `*p` will be invoked as `(*).p` in Scala**.

9. **Postfix operators are methods that take no parameters**.

	```scala
	"String" toLowerCase
	```

10. To get IEEE 754 remainder, call `scala.math.IEEEremainder`.

	```scala
	math.IEEEremainder(11.0, 4.0)
	// Double = -1.0
	```

11. For 16 signed bits:

	```scala
	1 		// 0000000000000001
	32767	// 01111..........1
	-1		// 11111..........1
	-2		// 1111111111111110

	~1 == -2
	// true
	```

12. Operator precedence

	|Operator precedence(high to low)|
	|--------------------------------|
	| \* / % |
	| \+ \- |
	| \:   |
	| \= \! |
	| \< \> |
	| \&   |
	| \^   |
	| \|   |
	| \(all letters\) |
	| \(assignment operators\) ops all end in an equal sign.|

	If the methods end in `:` they are group **right to left**, otherwise group from **left to right**.

	```scala
	a ::: b ::: c == a ::: (b ::: c)

	a * b * c == (a * b) * c

	// assignment operatos
	==
	>=
	*=
	+=
	```

13. **RichWrapper** provides more methods, then basic types.

<br>

##Chp.6 Functional Objects

1. Class can have `class parameters`, **Scala compiler will grap class parameters then create a `priamry constructor` that takes the same two parameters**.

	```scala
	class Params(x: Int, y: Int) {
		val a = x
		val b = y
	}

	Console println (new Param(5, 10)).a
	// 5
	```

2. **You cannot access class parameter directly as object fields from outside, but you can only access their value  in its method definition of same object**. You can access it by **defining fields and assigning its value to class parameter**.

    ```scala
    class Params(x: Int, y: Int) {
      val a = x + y
    }

    (new Param(5, 10)).x
    // value x is not a member of Param

    (new Param(5, 10)).a
    // 15

    class Params(x: Int, y: Int) {
      def add(that: Params): Params = {
        new Params(x + that.x, y + that.y)
      }
    }

    // value x is not a member of Params
    // new Params(x + that.x, y + that.y)
    //                     ^

    class Params(x: Int, y: Int) {
      val that_x = x;
      val that_y = y;
      def add(that: Params): Params = {
        new Params(x + that.that_x, y + that.that_y)
      }
    }

    new Params(5, 10).add(new Params(5, 10)).that_x
    // 10
    ```

2. Scala will compile any code you place in the class body **which isn't part of a field or a method definition to primary constructor**.

	```scala
	class Part(x: Int, y: Int) {
		println("Create" + " x: " + x + " y: " + y)
	}

	new Part()
	// Create x: 5 y: 10
	```

3. Pros & Cons of immutable object:

	Pros:

	1. Readable.
	2. You can pass immutable freely, while passing mutable object require defensive copies.
	3. Two threads may corrupt mutable object, but there has no way to corrupt immutable object's state because its immutable.
	4. Immutable objects makes safe hash keys, such as `HashSet`'s key may not found if that mutable object mutate after it has been placed into `HashSet`.

	Cons:

	1. Copy an large immutable object is wasted, Scala provide alternative way to use mutable object instead.

4. **Override with `override` modifier**.

	```scala
	class Rational(n: Int, d: Int){
	// override default toString method
		override def toString = n + "/" + d
	}
	```

5. You can specify **precondition** by `require` to check class parameters' input value.

    ```scala
    class Rational(n: Int, d: Int){
      require(d!= 0)
      override def toString = n + "/" + d
    }

    new Rational(5, 6)
    // Rational = 5/6

    new Rational(5, 0)
    //	java.lang.IllegalArgumentException:
    // 	requirement failed
    //  at scala.Predef$.require(Predef.scala:207)
    ```

6. `this` refer to current instance, it is the same as Ruby's `self`.

7. You can provide **auxiliary constructor** `this(...)` which has different class parameters than the primary constructor. **Every auxiliary constructor must invoke another primary or auxiliary constructor in first statement**.

	```scala
	class Rational(n: Int, d: Int) {
		def this(n: String, d: String) = {
			this(5, 6)
			println("String for n: " + n + " d: " + d)
		}
	}

	new Rational("5", "6")
	// String for n: 5 d: 6
	```

	**The net effect is that every constructor will finally call to primary constructor**. **Then the primary constructor is the single point of entry of class**.

9. Private fields or methods are only callable inside that class, subclass cannot call them.

10. **Initializer is the statement that initialize a variable**. Scala initialize the fields first, then methods, if the field init by other method, then init that method first.

11. Operators(`*`, `+`, `-`) are methods, we can define it in a custom class.

	```scala
	class Sum(x: Int) {
		val value = x;
		def + (that: Sum) = {
			new Sum(x + that.value)
		}

		def | () = println(value)
	}
	```

12. Scala use **camal-case** and **alphanumeric identifier**. It starts with a **underscore, or letter**. The `$` is reserverd for Scala compiler. Avoding using trailing underscore:

	```scala
	// This will lead compiler trat as declare a name:_ variable.
	val name_: Int = 1

	// It proper define type to Int
	val name_ : Int = 1
	```

13. Though `val` is a constant. It still a variable, such as method's parameters are `val`s, but still can hold different values each time the method is called. **Constant** in scala is more permanent. It starts with an **uppercase letter** by convention.

	```scala
	val Constant = 10

	// java style
	val CONSTANT = 10
	```

14. `+`, `++`, `:::`, `<?>`, and `:->` are some example of operators. **Scala compiler will internally mangle operators to turn them into legal Java identifier ex: `:->` would be represented as `$colon$minus$greater` internally**. If you want to access this identifier from Java code, then you have to use this internal representation.

	> **Operators are 7-bit ASCII chars that are not digits and letters**.

15. Beware if you don't leave space between operator letters, then it will be treat as a single identifier:

	```scala
	// In Java, this count as 4 lexical symbols
	x <- y == x < - y

	// In Scala, <- count as single identifer.
	x <- y != x < - y

	// If you want to seperate <-, add space

	x < - y
	```

16. Scala also provide **literal identifier**, **so you can dynamically call the identifier in runtime**.

	```scala
	// syntax:  wrap by back ticks `

	`x`
	`<client>`
	`yield`

	// You can not directly call java thread's yield,
	// because Scala reserve this word.

	// call yield at runtime instad.
	Thread.`yield`()
	```

17. **Implicit conversion**, Scala allows you define customed conversion method with modifier `implicit`, it will be called when the conversion may occur by its method name.

	```scala
	// In Scala interpreter scope

	// method name decide when intToRational occur.
	scala> implicit def intToRational(x: Int) = new Rational(x, 1)

	scaka> val r = new Rational(3, 2)
	scala> 2 * r
	// Rational: 3
	```

	**The implicit conversion only works when it is in scope**. If you place convertion method definition to `Rational` class it won't work. Because when implicit conversion occurs in later, the method is not defined in current execution scope so there is no way to tell compiler automatically convert it in runtime.

<br>

##Chp.7 Built-in Control Structures

1. `if...else` condition:

	```scala
	println(if (!args.isEmpty) args[0] else "default.txt")

	val filename =
		if (!args.isEmpty)
			args[0]
		else
			"default.txt"
	```

2.	**Look for opportunities to use vals. They can make your code both easier to read and easier to refactor**.

3. `while` and `do...while` loop both are loops, not expressions, so the result type is always `Unit`. **`Unit` type has return value as `()`**.

	```scala
	println("Void, return Unit value") == ()
	// Boolean = true
	```

4. **Assignment** in Scala always return `Unit` type.

	```scala
	var line = ""
	// line = readLine() will return () Unit value.
	// Then () always not equals to "" in while loop.
	// This is not the sam as Java.
	while(line = readLine() != "")
		println("Read: " + line)
	```
5. `for` expression in collection, generator `file <- files` generate new `val` named `file` in each iteration. Scala compiler infer `file`'s type to `File` because `files` is `Array[File]`.

	```scala
	val fileHere = (new java.io.File('.')).listFiles

	for (file <- filesHere)
		println(file)
	```

6. Create range by `1 to 4`, or exclude upperbound by `1 until 4`.

	```scala
	1 to 4
	// scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4)

	1 until 4
	// scala.collection.immutable.Range = Range(1, 2, 3)
	```
7. `for` filtering:

	```scala
	val filesHere = (new java.io.File(".")).listFiles

	// add filtering if condition
	for (file <- filesHere if file.getName.endsWith(".scala"))
		println(file)
	```

	Above is equivalent to:

	```scala
	for (file <- filesHere)
		if (file.getName.endsWith(".scala"))
			println(file)
	```

8. You can include more filters, just keep adding `if` clauses.

	```scala
	for (file <- filesHere
		if file.isFile
		if file.getName.endsWith(".scala")
	) println(file)
	```

9. If you add multiple `<-` clauses, you will create nested iterations.

	```scala
	for (
		file <- filesHere
		if file.getName.endsWith(".scala");
		line <- fileLines(file)
		if line.trim.matches(pattern)
	) println(file +": "+ line.trim)
	```

10. **Mid-stream variable bindings**. Above example code repeatedly compute `line.trim` in two places. To avoid nontrivial computation, you can introduce mid-stream variable by `=` assignment to reduce the reduntant computation.

    ```scala
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches(pattern)
    } println(file +": "+ trimmed)
    ```

11. **We can return new collection by `yield`**. The return collection's type depends on its input collection type ex: `files` in below example. **The implicit conversion will occur**.

	> ####[alvinalexander.com#scala-for-loop-yield-examples-yield-tutorial](http://alvinalexander.com/scala/scala-for-loop-yield-examples-yield-tutorial)

	```scala
	// Syntax: for clauses yield body

	val new_files =
	for {file <- files if file.indexOf(".scala") != -1}
		yield { file + " file" }
	// Array[String] = Array(a.txt file, b.txt file)
	```

	**Such as `0 to 9` is a `Range` object and its collection type is `Vector` from its `IndexedSeq` traits**.

	> ####[docs.scala-lang.org#vectors](http://docs.scala-lang.org/overviews/collections/concrete-immutable-collection-classes.html#vectors)

12. `throw`, `try` and `catch` exceptions. **Exception object can initialize with message string to replace default message**. Use `finally` to ensure some executions before termination.

	```scala
	// throw exception
	if x != 10
		throw new IllegalArgumentException("R must be even.")

	import java.io.FileReader
	import java.io.FileNotFoundException
	import java.io.IOException

	// catch exception
	try {
		val x = new FileReader("input.txt")
	} catch {
		case ex: FileNotFoundException =>
			println(ex + " File Not Found")
		case ex: IOException
	}

	// add finally

	val x = new FileReader("input.txt")
	try {
		// Use file
	} catch {
		case ex: FileNotFoundException =>
			println(ex + " File Not Found")
		case ex: IOException =>
			println(ex + " IO error")
	} finally {
		// val x must declare outside of try-catch block
		x.close()
		println("File closed")
	}
	```

13. **Not like Java, Scala returns value for `try-catch` block**, As in Java, if a `finally` clause **(1) includes an explicit return statement, (2) or throws an exception, that return value or exception will overrule any previous one that originated in the `try` block or one of its `catch` clauses**.

	```scala
	// return 2, explicitly return occurs in finally block
	def f(): Int = try { return 1 } finally { return 2 }

	// return 1
	// because exception or throw does not occur.
	def f(): Int = try { 1 } finally { 2 }

	// using catch to return new URL
	import java.net.URL
	import java.net.MalformedURLException
	def urlFor(path: String) =
		try {
			new URL(path)
		} catch {
			case e: MalformedURLException =>
				new URL("http://www.scala-lang.org")
		}
	```

14. Scala use `match...case` which is the same as `switch...case` in Ruby. **Default case (wildcard case) marked as `_`**.

	```scala
	val firstArg = "default"

	firstArg match {
		case "salt" => println("pepper")
		case "chips" => println("salsa")
		case "eggs" => println("bacon")
		case _ => println("huh?")
	}
	```

	Scala `match...case` also result values.

	```scala
	val firstArg = "default"

	// result values returned to val res.
	val res = firstArg match {
  	case "salt" =>
    	println("pepper")
    	"pepper"
  	case "chips" =>
    	println("salsa")
    	"salsa"
  	case "eggs" =>
    	println("bacon")
    	"bacon"
  	case _ =>
    	println("huh?")
    	"huh?"
	}

	result
	// String = "huh?"
	```

15. Scala does not support `break` and `continue`. **Alternative way to approach this by `if...else` with explicitly return, or put a boolean variable in pre-condition**.

    ```scala
    int i = 0;                // This is Java
    boolean foundIt = false;
    while (i < args.length) {
      if (args[i].startsWith("-")) {
        i = i + 1;
        continue;
      }
      if (args[i].endsWith(".scala")) {
        foundIt = true;
        break;
      }
      i = i + 1;
    }

    var i = 0;
    var foundIt = false;
    while(i < args.length && !foundIt) {
      if (!args[i].startsWith("-") &&
      args[i].endsWith(".scala")) {
        foundIt = true
      }else{
        i = i + 1;
      }
    }
    ```

16. **We can try to get rid of the `vars` in above code by approaching a recursive function**.

	```scala
	def searchForm(i: Int): Int = {
		if (i > args.length) -1
		else if (args[i].startsWith("-")) searchForm(i + 1)
		else if (args[i].endsWith(".scala")) i
		else searchForm(i + 1)
	}

	val i = searchForm(0)
	```

17. If you really needs the `break`, it is in Scala's standard library. **Which is allowed to break the enclosing `breakable` block**.

    ```scala
    import scala.util.control.Breaks._
    import java.io._

    val in = new BufferedReader(new InputStreamReader(System.in))

    breakable {
      while (true) {
        println("? ")
        if (in.readLine() == "") break
      }
    }
    ```

	**The `Breaks` class implements break by throwing an exception that is caught by an enclosing application of the `breakable` method**. Therefore, the call to `break` does not need to be in the same method as the call to `breakable`.

18. **Scala compiler will not actually emit a new recursive function to increase stack frame**. Scala will try to optimize recursion if it is a tail recursion. **Scala jump back to the begining of function in same stack frame**.

	```scala
	// Tail call mth(x-1) + 1 will hold the stack frame
	// to compute result,
	// or for conditional check.
	// So this is not tail recurison
	def mth(x: Int): Int = {
		if (x == 0) println("END")
		else mth(x - 1) + 1
	}

	// Tail call mth(x-1) can reuse same stack frame.
	def mth(x: Int): Int = {
		if (x == 0) println("END")
		else mth(x - 1)
	}
	```

	1. The trait for tail recursion is that **no control must return to caller stack frame to decide return value before returning it**. In other words, **the last call in a function must be a function call instead of any other statement**.

		```scala
		// have arithmatic operation,
		// function call is not in tail position.
		def a(data) = a(data) + 1

		// have to check z,
		// function call is not in tail position.
		def b(data) = {
			val z = x(data)
			return z == 0 ? 1 : z
		}
		```

		**Variable assignment as last call will not be tail recursion, because it needs to turn the control back to caller for assignment**.

		```scala
		// val z = boom(x-1) is not tail recursion
		def boom(x: Int): Unit = {
			if (x == 0) throw new Exception("Bang!")
			else val z = boom(x-1)
		}
		```

		Start scala interpreter with tail recursion optimization, the callstack did not jump back.

        ```sh
        scala> boom(5)
        java.lang.Exception: Bang!
        at .boom(<console>:11)
        at .boom(<console>:12)
        at .boom(<console>:12)
        at .boom(<console>:12)
        at .boom(<console>:12)
        at .boom(<console>:12)
        ... 33 elided
        ```

19. Unlike `javascript` and `ruby`, conditional statement or loops will create new local scope. **Curly braces create new scope**. Variables cannot redefine in same scope.

	```scala
	val a = 2
	// Redefine val a, this won't compile
	val a = 2
	```

	**Java does not allow inner scope with same-named variables, but Scala does**. An inner variable is said `shadow` the outer variable.

	```scala
	var a = 2;
	{
		var a = 3
		println(a)
		// 3
	}
	println(a)
	// 2
	```

20. Interpreter allows you to redefine same variable. **Conceptually, the interpreter creates a new nested scope for each new `val` or `var` statement you type in**.

	```scala
	// In Scala interpreter
	scala> val a = 2
	// 2
	scala> val a = 3
	// 3
	scala> println(a)

	// Above is equivalent to
	val a = 2;
	{
		val a = 3;
		{
			println(a)
		}
	}
	```

	**By the way, semi-colon should placed in `var` definition, otherwise the statement won't end because 4.6.2's rule**.

<br>

##Chp.8 Functions and Closures

1. Scala allow inner function because function can be **first class**. So the class will not be polluted by tons of private methods. **Inner function also able to access variables of its outer function scope**.

	```scala
	object Lines {
		def processFile(filename: String, width: Int) {
			// local function processLine
			def	processLine(line: String) {
				if (line.length > width)
					println(filename + ": " + line)
			}
			val source = Source.fromFile(filename)
			for (line <- source.getLines())
				processLine(line)
		}
	}
	```

2. **Function literal** exist in source, whereas **function value** exist in object at runtime. **A function literal is compiled into a class that when instantiated at runtime is a function value**.

	```scala
	// function literal
	(x: Int) => x + 1

	// function values are objects, so
	// you can store them in variables.

	var increase = (x: Int) => x + 1
	```

	**Every function value is an instance of some class that extends one of serveral `FunctionN` traits**. N starts from 0. Each `FunctionN` trait has `apply` method used to invoke the function by parentheses.

	> ####[kwangyulseo.com#functions-are-objects-in-scala](http://kwangyulseo.com/2014/03/03/functions-are-objects-in-scala/)

2. **Function literal**. Last statment in function body will be result value.

	```scala
	// Syntax:
	// (params...) => { //code } or single line statement.

	val sum = (x: Int, y: Int) => {
		println("x: " + x)
		println("y: " + y)
		x + y
	}

	sum(5, 6)
	// 5
	// 6
	// Int = 11
	```

3. Short forms of function literals: **By `target typing`, if Scala can infer the type of parameters, then leave the type casting off. Also, we can reduce the parentheses if that function literal only have single parameter**.

	```scala
	val ary = Arra(1, 2, 3)

	ary.foreach((x: Int) => println(x))

	// short forms
	ary.foreach((x) => println(x))
	ary.foreach(x => println(x))
	```

4. **Use `_` underscore as placeholder for a single parameter, or parameter list in function literal**. If the parameters are all consist of underscores, **then you dont have to specify parameter list**.

	```scala
	someNumbers.foreach(_ > 0)
	// same as
	someNumbers.foreach(x => x > 0)
	```

	If you use multiple underscores as multiple parameters, **wrapped them with its type because compiler may not able to infer it**. Each underscore maps to the parameter by the order of parameter list.

	```scala
	// not enough info to infer two _'s type
	val f = _ + _

	val f = (_: Int) + (_: Int)

	f(5, 6)
	// Int = 11
	```

5. Because `val/var` assignment is running at runtime, so you must provide required function params to invoke that function as function values. Or use `_` to represent parameter list as **partial applied function**. **Partial applied function will defer the invokation until `_` paramters fully supplied**.

	```scala
	def s() { println("No args is fine.") }

	val d = s

	def sum(x: Int, y: Int) = { x + y }

	// failed, fail to pass params.
	val k = sum

	// use partial applied function
	val z = sum _
	```

	Or just prepresent as some parameters:

	```scala
	def sum(x: Int, y: Int) = { x + y }

	val t = sum(5, _)

	// supplied _ params
	t(6)
	// Int = 11
	```

	In above code, **Scala compiler generate a new function class whose `apply` method takes one parameter. once `t(6)` is called, then it called `apply` to invoke `sum` with fully supplied parameters**.

6. If you are writing a partial applied function **which leaves off all parameters by `_` and the types can be inferred**, then you can leave off the `_` too.

	```scala
	someNumbers.foreach(println _)

	// shorthand
	someNumbers.foreach(println)

	val x = sum
	// leads error because compiler cannot infer sum's arguments

	val x = sum _
	```

7. Javascript can directly assign function object to variables(actually Javascript hoist `undefined` value in parse phase, and assign to function object later in running phase).

    **Scala has functions(function literals, partial applied function, or `FunctionN` traits) and methods(`def`)**. Methods is parsed at compile phase, functions is executed at runtime. **That's why it needs partial applied function to wrap methods with wildcard underscore to convert a method to a function in runtime**.

	  > ####[jim-mcbeath.blogspot.tw#scala-functions-vs-methods.html](http://jim-mcbeath.blogspot.tw/2009/05/scala-functions-vs-methods.html)

    >####[stackoverflow.com#difference-between-method-and-function-in-scala](http://stackoverflow.com/questions/2529184/difference-between-method-and-function-in-scala#answer-2530007)

    The trailing underscore `sum _` is because **Scala is more related to Java imperative language, where a method that's not applied to all its arguments is considered an error**.

8. **Closure** in Scala is a function literal, function values(object), or partial applied function at runtime.

	A closure call outside variable is called **open term**, that variable is **free variable and not binding to that closure**.

	A colsure without any external variable is called **closed term**. the closure's local variables are called **bind variable**.

	```scala
	val lit = (x: Int) => x + more
	// compiler complain: not found: value more

	// declare free variable
	val more = 5

	val lit = (x: Int) => x + more
	// lit: Int => Int = <function1>

	// summation
	var sum = 0
	val sumNumbers = List(1,2,3,4,5,6,7)

	sumNumbers(sum += _)
	```

9. We can also defined a **method** which returns a **function value with open term**.

	```scala
	def makeIncrease(x: Int) = (y: Int) => x + y

	val inc5 = makeIncrease(5)

	val inc999 = makeIncrease(999)

	inc5(5)
	// 10

	inc999(1)
	// 1000
	```

	When you create `inc5`, a closure is created and **capture free variable x's value as 5**. When you apply those closures to arguments, the result depends on **how the free variable was defined when the closure was created**.

	You can capture any variable you like: `val`, `var`, or **parameters**.

10. **Repeated parameters**: append `*` to parameter type to declare that may have one or more repeated parmaters type. **That repeated parameters must be listed in the last of paramter list**.

	####Caveat: You cannot directly write a varargs function literal, so repeated paramters is impossible supply to a function literal. Only method can have that feature. You can first define a method then convert to partial applied function as a function literal as a workaround.

	```scala
	def echo(strs: String*) =
		for (str <- strs) println(str)

	echo("gi", "joe")
	// gi
	// joe
	```

11. **If you want to pass collection's each element to repeated parameters method, declare type `: _*` to tell compiler iterate each element as a parameter instead of just passing that collection**. Though the repeated parameter(`strs` in example) still represent as a collection in internal function scope.

	```scala
	def echo(strs: String*) = for(str <- strs) println(str)

	val list = List("John", "Cena", "is", "awesome")

	echo(list: _*)
	```

	**You can supply type for any object in scala**.

	```scala
	list: List[String]
	1: Int
	"X": String
	'C': Char
	```

12. We can pass named arguments by `argName = value` instead of passing them by its order in method definition.

	```scala
	def namedArgs(x: Int, h: String, y: Char) = println(x + h + y)

	namedArgs(x = 5, y = '!', h = "hello")
	// 5hello!
	```

13. Also, **you can supply default parameter value by `argName: Type = defaultValue`**.

	```scala
	def printTime(out: java.io.PrintStream = Console.out) = {
		out.println("time = " +
			System.currentTimeMillis())
	}
	```

14. Usually programming language does not support tail recursion optimization lead recursion slow because it does not jump back to the begining of function stack frame. **Scala comiler default enables tail recursion optimization**. **So time complexity of below imperative approach is almost the same** as functional one by simply jump back to the begining of the function.

	> **Check 7.18.1 for more info**.

	```scala
	// With tail recursion,
	// jump back to the begining of function
	def approX(guess: Double): Double =
		if (isGoodEnough(guess)) guess
		else approx(improve(guess))

	// loop just jump back to the begining of loop
	def approXLoop(guess: Double): Double = {
		var i = guess
		while(!isGoodEnough(i)){
			i = improve(i)
		}
		i
	}
	```

	**If you want Scala compiler don't optimize for tail recursion, start `scala` interpreter or `scalac` with `-g:notailcalls`**.

15. **Scala's tail recursion optimization is limited by Java JVM**. **Scala only optimizes directly recursive calls back to the same func- tion making the call**.

	1. If the recursion is indirect, then **no optimization is possible**.

		```scala
		def isEven(x: Int): Boolean =
  			if (x == 0) true else isOdd(x - 1)
  	def isOdd(x: Int): Boolean =
  			if (x == 0) false else isEven(x - 1)
		```

	2. **If final call goes to a function value, then it won't get tail-recursion optimization**.

        ```scala
        val funValue = nestedFun _
        def nestedFun(x: Int) {
          if (x != 0) { println(x); funValue(x - 1) }
        }
        ```

17. Extra example for placeholder and partial applied function:

	```scala
	def mth(fnc: (String, String) => String) = {
		// partial applied function
		// Here placeholder can represent as
		// a param list, or a parameter.
		fnc(_: String, _:String)
	}

	// placeholder syntax _ + _
	// to shorthand the function literal.
	val paf = mth(_ + _)

	// Above is the same as
	val paf = mth((a: String, b:String) = > a + b)

	paf
	// (String, String) => String = <function2>
	// partial applied function2

	paf("Hello", " World!")
	// Hello World!
	```

	Repeated paramters:

	```scala
	def mth(fnc: (String) => Unit, strs: String*){
		for (str <- strs) fnc(str)
	}

	val x = mth(println _, Array("Hello", "World", "!!!"): _*)
	// Hello
	// World
	// !!!
	```

<br>

##Chp.9 Control Abstraction

1. function literal reduction:

	```scala
	def filesMatching(query: String,
  		matcher: (String, String) => Boolean) = {
  		for (file <- filesHere; if matcher(file.getName, query))
    		yield file
	}

	def filesEnding(query: String) =
		filesMatching(query, _.endsWith(_))
	```

	In above case, `_.endsWith(_)` is **not a closure because it does not capture any free variables**. Only two bound variables represented by underscore.

    ```scala
    private def filesMatching(matcher: String => Boolean) =
      for (file <- filesHere; if matcher(file.getName))
        yield file

    def filesEnding(query: String) =
      filesMatching(_.endsWith(query))
    ```

	In above code, `_.endsWith(query)` with one free variable `query`, and one bounded variable represent by underscore. **This is a closure** and Scala supports it so we can reduce previous example to above code.

2. Scala's `exists` API is a control abstraction, it is a special-purpose looping.

	```scala
	def containsOdd(nums: List[Int]) = nums.exists(_ % 2 == 1)

	// or self-implementation
	def containsOdd(nums: List[Int]): Boolean = {
		var exists = false
		for (num <- nums)
			if (num % 2 == 1) exists = true
		exists
	}
	```
3. **Curry**: A **curried** function is **applied to multiple argument lists, instead of just one**. The similar way to achieve this is return a function literals or transform method to function object.

	```scala
	// Curried method
	def sum(x: Int)(y: Int) = x + y

	sum(5)(6)
	// 11

	// Similar way, but not the same as above code
	def sum(x: Int) = (y: Int) => x + y

	sum(6)(5)
	// 11
	```

	We can set placeholder to either one parameter list. **There has slightly difference when we assign the last parameter**.

	```scala
	// curried function
	def sum(x: Int)(y: Int) = x + y

	val x = sum(_: Int)(6)

	x(5)
	// 11

	val x = sum(6)_
	// val x = sum(6) _

	x(5)
	// 11

	// function literal version
	def sum(x: Int) = (y: Int) => x + y

	val x = sum(6)_
	// error: _ must follow method; cannot follow Int => Int

	val x = sum(6)(_)
	// or val x = sum(6)
	```

4. In any method invocation in Scala in **which you’re passing in exactly one argument, you can opt to use curly braces to surround the argument instead of parentheses**.

5. The purpose of above ability to substitute curly braces for parentheses for passing in one argument **is to enable client programmers to write function literals between curly braces. This can make a method call feel more like a control abstraction**.

    ```scala
    def withPrintWriter(file: File, op: PrintWriter => Unit) {
      val writer = new PrintWriter(file)
      try {
        op(writer)
      } finally {
        writer.close()
      }
    }

    // client code
    withPrintWriter(new File("date.txt"),
      writer => writer.println(new java.util.Date))

    // Replace above code by curly braces approach

    // use curried function to workaround single parameter   restriction
    def withPrintWriter(file: File)(op: PrintWriter => Unit) {
      val writer = new PrintWriter(file)
      try {
        op(writer)
      } finally {
        writer.close()
      }
    }

    // Then client developer write as
    val file = new File("date.txt")
    withPrintWriter(file) {
      writer => writer.println(new java.util.Date)
    }
    ```

6. **By-name parameters**: if a function literal parameter takes no parameter, then we can shorthand to **by-name parameter**.

	```scala
	var assertionsEnabled = true
	def myAssert(predicate: () => Boolean) =
		if (assertionsEnabled && !predicate())
			throw new AssertionError

	// must call with ()
	myAssert(() => 5 > 3)

	myAssert(5 > 3)
	// Won’t work, because missing () =>

	// by-name parameter - predicate: => Boolean
	def byNameAssert(predicate: => Boolean) =
    if (assertionsEnabled && !predicate)
      throw new AssertionError

	byNameAssert(5 > 3)
	```

	We can just pass `5 > 3` as boolean result, the problem is, **that is not lazy evaluation so may cause unpredictable error before enter the function scope**.

      ```scala
      // predicate is not function value,
      // will not put off evaluation until runtime.
      def boolAssert(predicate: Boolean) =
        if (assertionsEnabled && !predicate)
          throw new AssertionError

      var assertionsEnabled = false
      boolAssert(x / 0 == 0)
      // java.lang.ArithmeticException: / by zero

      byNameAssert(x / 0 == 0)
      // prints nothing,
      // assertionsEnabled && !predicate
      // short circuit because assertionsEnabled is false
      ```

<br>

##Chp.10 Composition and Inheritance

1. Terminology distinguishes between **declarations** and **definitions**. Class `Element` declares the abstract method contents, **but currently defines no concrete methods**.

2. Unlike Java, no abstract modifier is necessary (or allowed) on method declarations. Methods that do have an implementation are called **concrete**.

	```scala
	abstract class Element {
		// abstract method
		def contents: Array[String]
	}
	```

	**An abstract class cannot instantiate and is subclassed by other class which should supply medthod definition**.

3. **Defining parameterless methods**: If methods defined with empty parentheses, we called it **empty-paren methods**. And declare it without parameter list.

	```scala
	abstrac class Element {
		def contents(): Array[String]

		// empty-paren methods
		def width: Int = contents.length
		def height: Int = if (height == 0) 0 else contents(0).length
	}
	```

	This convention supports the **uniform access principle**, it says that **client code should not be affected by a decision to implement an attribute as a field or method**.

	By above empty-paren methods, you can also declare them as variables.

	```scala
	// fields, instead of member methods
	val width: Int = contents.length
	val height: Int = if (height == 0) 0 else contents(0).length
	```

	**The two pairs of definitions are completely equivalent from a client’s point of view**. The only difference is that **field accesses might be slightly faster than method invocations, because the field values are pre-computed when the class is initialized, instead of being computed on each method call.**

4. It is recommended to still write the empty parentheses when **the invoked method represents more than a property of its receiver object**.

	For instance, empty parentheses are appropriate if the method **performs I/O**, or **writes reassignable variables (vars)**, or **reads vars other than the receiver’s fields**, either directly or indirectly by using mutable objects.

    ```scala
    "hello".length  // no () because no side-effect
    println()       // better to not drop the (), I/O
    ```

5. Exetending class has several effects:

	1. It makes subclass inherit all non-private members from super class.
	2. It makes the type of subclass to a subtype of superclass.
	3. If you leave out `extends` in a class, Scala extends `scala.AnyRef` which is the same as `java.lang.Object` in java for you.

	```scala
	class ArrayElement(conts: Array[String]) extends Element {
		def contents: Array[String] = conts
	}
	```

6. **Subtyping** means that a value of the subclass can be used wherever a value of the superclass is required.

	```scala
	val e: Element = new ArrayElement(Array("hello"))
	```

7. You could change the implementation in subclass **from a method to a field** without having to modify the abstract method definition of contents in superclass.

	```scala
	abstract class Element {
  		def contents: Array[String]
  		def height: Int = contents.length
  		def width: Int = if (height == 0) 0 else contents(0).length
	}

	// change from method to field
	class ArrayElement(conts: Array[String]) extends Element {
		val contents: Array[String] = conts
	}
	```

	Therefore Scala **forbid to define a field and method with the same name in the same class, whereas it is allowed in Java**.

	```scala
	class WontCompile {
		private var f = 0 // Won’t compile, because a field
		def f = 1         // and method have the same name
	}
	```

	Generally, **Scala has just two namespaces for definitions in place of Java’s four**. Java’s four namespaces are **fields, methods, types, and packages**. By contrast, Scala’s two namespaces are:

    - values (fields, methods, packages, and singleton objects)
    - types (class and trait names)

	By above approach, Scala is able to override a val to method, which Java cannot.

8. We can shorthand field definition by parametric fields.

	```scala
	class ArrayElement(val contents: Array[String]) extends Element
	// shorthand of below code

	class ArrayElement(x123: Array[String]) extends Element {
		val contents: Array[String] = x123
	}
	```

	That field could be either `val` or `var`. It is possible to add modifiers such as `private`, `protected`, or `override` to these parametric fields, just as you can do for any other class member.

    ```scala
    class Cat { val dangerous = false }
    // shorthand of next code
    class Tiger(
      override val dangerous: Boolean,
      private var age: Int) extends Cat


    class Tiger(param1: Boolean, param2: Int) extends Cat {
      override val dangerous = param1
      private var age = param2
    }
    ```

9. We can invoke superclass constructors:

	```scala
	class ArrayElement(x123: Array[String]) extends Element {
		val contents: Array[String] = x123
	}

	class LineElement(s: String) extends ArrayElement(Array(s)) {
		override def width = s.length
		override def height = 1
	}
	// now has an Array[String] field/method content.
	```

10. Scala requires `override` modifier for all members that override a **concrete member in a parent class**. Which means the abstract member overriden can safely ignore this modifier.

11. Scala also have **dynamic binding** feature, its overriden methods depends on what instantiated instance during runtime, not the type of the variables.

    ```scala
    abstract class Element {
      def demo() { println("Element's implementation invoked") }
	  }
    class ArrayElement extends Element {
    	override def demo() { ... }
	  }

    class LineElement extends ArrayElement {
  		override def demo() { ... }
    }

    // UniformElement inherits Element’s demo
	   class UniformElement extends Element


    val ae = new ArrayElement
    ae.demo
    // override message

    // dynamic binding to UniformElement,
    // which does not override Element's demo method.
    val ue = new UniformElement
    ue.demo
    // Element's implementation invoked
    ```

	**You cannot call a variable or method which is not declared in its class or inherited/mixed from other class/trait. Fields and methods, which are defined in superclass and overriden in subclass, are dynamic binding to instance's method.**

	```scala
	class Z {}
	class B extends Z { val x = 5 }

	val b: Z = new B
	b.x
	//error: value x is not a member of Z

	class A extends B {}

	val a: B = new A
	a.x
	// 5
	// value x is a member of B
	```

	**Methods and variables, which are `singleton`, `private` and `final`, are resolved using static binding. Because `private` and `final` methods are not allowed overriden, and singleton method only provide staic methods to its type, not the instance.**

    ```scala
    class A {
      def g = println("A mth g")
      private val x = "A private x"
    }

    class B extends A {
      def mth = println("B normal mth")
      private def pmth = println("B private mth")
      val b = "value b"
      private val pb = "private value b"
      final val fb = "final value b"
    }

    // b is a Type A instance,
    // accessable vars/methods must be defined in class A
    val b: A = new B

    b.pmth
    // error: value pmth is not a member of A

    b.x
    // error: value x in class A cannot be accessed in A
    // private value x not explode to outside

    b.mth
    // error: value mth is not a member of A

    // b is now a Type B instance
    val b: B = new B

    // get inherited method g from super class A
    b.g
    // A mth g

    b.mth
    // B normal mth
    ```

	Java's dynamic binding vs static binding:

	> ####[javarevisited.blogspot#what-is-static-and-dynamic-binding-in](http://javarevisited.blogspot.tw/2012/03/what-is-static-and-dynamic-binding-in.html)


12. Add `final` modifier to restrict that members(methods/fields) are overriden.

	```scala
	class B {
		final val x = 5
		final var y = 6
	}

	class G(override var y: Int = 6) extends B
	// error: overriding variable y in class B of type Int;
 	// variable z cannot override final member

	class G(override val x: Int = 6) extends B
	// error: overriding value x in class B of type Int(5);
 	// value x cannot override final member
	```

13. Using inheritance to consider the strong related `is-a` relationship. Otherwise pick composition instead.

14. Create a factory object by **companion singelton object**.

	```scala
	// import singleton's factory method elem
	import Element.elem
	abstract class Element {
		// composition Array object
		def contents: Array[String]

		def above(that: Element): Element =
      elem(this.contents ++ that.contents)

    def beside(that: Element): Element =
      elem(
        for (
          (line1, line2) <- this.contents zip that.contents
        ) yield line1 + line2
      )

	}
	// Array(1, 2, 3) zip Array("a", "b")
	// evaluate to Array((1, "a"), (2, "b"))

	// another scala file
	object Element {
		def elem(contents: Array[String]): Element =
			new ArrayElement(contents)
		def elem(chr: Char, width: Int, height: Int): Element =
			new UniformElement(chr, width, height)
		def elem(line: String): Element =
			new LineElement(line)
	}
	```

<br>

##Chp.11 Scala’s Hierarchy

1. Scala hierarchy: `scala.Any` has two subclass `scala.AnyRef` and `scala.AnyVal`. `scala.Nothing` is subtype of every builtin class.

	Those classes must have 7 methods.

	```scala
	final def ==(that: Any): Boolean
  final def !=(that: Any): Boolean
  def equals(that: Any): Boolean
  def ##: Int
  def hashCode: Int
  def toString: String
	```

2. Nine value classes: `Double`, `Int`, `Float`, `Long`, `Byte`, `Short`, `Char`, `Unit`, and `Boolean`.

3. You cannot create instance of these classes because they are all defined to both `abstract` and `final`.

4. `Unit` has single instance value which is written `()`.

5. All value classes are subtype of `scala.Any` but does not subclass to each other. Instead they are **implicit conversions between different value class type.** Ex: `scala.Int` is widened to `scala.Long` when required.

6.  `min`, `max`, and `until` are defined in `scala.RichInt` class but not in `scala.Int` class. However, we can invoke those methods in `Int` object which implicitly converts to `scala.RichInt`.

7. `scala.AnyRef` in fact is just an alias of `java.lang.Object`. You can use them interchangeably in Scala program on Java platform.

8. **Boxing** in Java, is a `int` wrapped by `Integer` class. **For value types in Scala, `==` is the natural equality(not about reference id). For reference types other than Java's boxed numeric types, `==` is an alias of `equals` method**. Though you can override it, such as `==` in Scala has override to the natural eqality.

	```scala
	"asdf" == "asdf"
	// true, don't have to use equals method
 	```

9. `scala.AnyRef` defines a `eq` method which cannot be overriden and use the reference equality.

	```scala
	"asdf" eq "asdf"
	// true
	// because string literal will only have one copy in memory
	// That's for optimization in Java.

	val x = new String("asdf")
	val y = new String("asdf")

	x eq y
	// false, two different references.
	```

10. **class `scala.Null` is a bottom type of any `scala.AnyRef` classes**. It is not compatible to value classes.


11. `scala.Nothing` is a type that has no value. **One use of `Nothing` is that it signals abnormal termination**. So don't have return value for it.

	```scala
	def error(msg: String): Nothing =
		throw new RuntimeException(msg)
	```

	**`Nothing` is a bottom type of `AnyRef` and `AnyVal` classes**. It can be used like this:

	```scala
	def divide(x: Int, y: Int): Int =
		if (y != 0) x / y
		else error("can't divide by zero")
	```

<br>

##Chp. 12 Traits

1. **Trait** can mixing either by `extends` or `with` keyword.

	```scala
	trait Power {
		def	square(value: Int) = {
			value * value
		}
	}

	class Values(val value: Int = 5) extends Power {}

	val traitType: Power = new Values()
	// traitType: Power = Values@2d69fc5
	```

2. **Trait** also have its type, in above example, type is `Power` and is a subtype of `scala.AnyRef` because it does not explicitly extend any class.

3. You can do anything in a trait definition that you can do in a class definition, and the syntax looks exactly the same, except 2 differences:

	1. **A trait cannot have any “class” parameters**.
	2. **In classes, `super` calls are statically bound, in traits, they are dynamically bound**.

	If you write `super.toString` in a class, you know exactly which method implementation will be invoked. **When you write the same thing in a trait, however, the method implementation to invoke for the `super` call is undefined when you define the trait**.

4. Trait cannot have class parameters, but can supply with **type parameters**.

	```scala
	// Scala Ordered trait
	class Rational(n: Int, d: Int) extends Ordered[Rational] {
		// ...
		def compare(that: Rational) =
			(this.numer * that.denom) - (that.numer * this.denom)
	}
	```

5. Traits with `abstract override` and `extends` modifiers:

	```scala
	class Rational(n: Int, d: Int) {}

	abstract class IntQueue {
		def get(): Int
		def put(x: Int)
	}

	import scala.collection.mutable.ArrayBuffer
	class BasicIntQueue extends IntQueue {
		private val buf = new ArrayBuffer[Int]
		def get() = buf.remove(0)
		def put(x: Int) { buf += x }
	}

	trait Doubling extends IntQueue {
		abstract override def put(x: Int){ super.put(2 * x) }
	}
	```

	The `Doubling` trait:

	1. **It declares a superclass, `IntQueue`. This declaration means that the trait can only be mixed into a class that also extends `IntQueue`**. Thus, you can mix `Doubling` into `BasicIntQueue`, but not into `Rational`.

	2. The trait has a `super `call on a method declared `abstract`. Such calls are illegal for normal classes, because they will certainly fail at run time.

		Since `super` calls in a trait are dynamically bound, the `super` call in trait `Doubling` will work so long as the **trait is mixed in after another trait or class that gives a concrete definition to the method**.

		**This arrangement is to tell the compiler you are doing this on purpose, you must mark such methods as `abstract override`. This combination of modifiers is only allowed for members of traits, not classes**, and it means that the trait must be mixed into some class that has a concrete definition of the method in question.

        ```scala
        class MyQueue extends BasicIntQueue with Doubling

        val queue = new MyQueue

        queue.put(10)

        queue.get
        // 20
        ```

6. We can also mixed in trait when initialize a instance.

	```scala
	// BasicIntQueue class and Doubling trait are defined.
	val queue = new BasicIntQueue with Doubling

	queue.put(10)

	queue.get
	// 20
	```

7. **Traits as stackable modifications**:

	The order of mixins is significant. Roughly speaking, **traits further to the right take effect first then to left**.

	```scala
	trait Incrementing extends IntQueue {
		abstract override def put(x: Int) { super.put(x + 1) }
	}

	trait Filtering extends IntQueue {
		abstract override def put(x: Int) {
			if (x >= 0) super.put(x)
		}
	}

	al queue = (new BasicIntQueue
                   with Incrementing with Filtering)

 	queue.put(-1); queue.put(0); queue.put(1)
 	queue.get()
 	// Int = 1

 	queue.get()
 	// Int = 2
	```

8. **In traditional multiple inheritance, the `super` cannot call as stackable modifications**. If the rule is the last supeclass(`Filtering`) wins, then its `super` call will not stack to another super class(`Incrementing`).

9. In trait, the method called is determined by a **linearization** of the classes and traits that are mixed into a class.

	**In any linearization, a class is always linearized before all of its superclasses and mixed in traits**. That method must be all override until extends class/trait.

	```scala
	class Animal
  trait Furry extends Animal
  trait HasLegs extends Animal
  trait FourLegged extends HasLegs
  class Cat extends Animal with Furry with FourLegged
	```

	Linearization(order of method call):

    | Type      | Linearization |
    |:----------|:------------- |
    | Animal    | Animal, AnyRef, Any |
    | Furry     | Furry, Animal, AnyRef, Any |
    |FourLegged | FourLegged, HasLegs, Animal, AnyRef, Any |
    |HasLegs    | HasLegs, Animal, AnyRef, Any |
    |Cat        | Cat, FourLegged, HasLegs, Furry, Animal, AnyRef, Any |

	Example:

	```scala
	class Animal {
  	def put(str: String) = { println("Animal"); println(str) }
	}

	trait Furry extends Animal {
		abstract override def put(str: String) = {
			super.put("Furry")
			println(str)
		}
	}

	trait HasLegs extends Animal {
		abstract override def put(str: String) = {
  		super.put("HasLegs")
  		println(str)
  	}
	}

	trait FourLegged extends HasLegs {
		abstract override def put(str: String) = {
 			super.put("FourLegged")
 			println(str)
 		}
	}

	class Cat extends Animal with Furry with FourLegged {
		override def put(str: String) = {
			super.put("Cat")
			println(str)
		}
	}

	val c = new Cat
	c.put("End")

	// Animal
	// Furry
	// HasLegs
	// FourLegged
	// Cat
	// End
	```

	The order is, **start from subclass through traits by right to left mixed-in order, then finally enter to superclass and its' inheritance chain**.

10. To trait or not to trait?

	1. **If the behavior will not be reused, then make it a concrete `class`**. It is not reusable behavior after all.

	2. **If it might be reused in multiple, unrelated classes, make it a `trait`**. Only traits can be mixed into different parts of the class hierarchy.
 3. **If you want to inherit from it in Java code, use an `abstract class`**. Since traits with code do not have a close Java analog.

 		As one exception, **a Scala trait with only `abstract` members translates directly to a Java `interface`**, so you should feel free to define such traits even if you expect Java code to inherit from it.
 	4. **If you plan to distribute it in compiled form, and you expect outside groups to write classes inheriting from it, you might lean towards using an `abstract class`**.
	5. **If efficiency is very important, lean towards using a `class`**. Most Java
runtimes make a virtual method invocation of a class member a faster operation than an interface method invocation. **Traits get compiled to interfaces and therefore may pay a slight performance overhead**.
	6. **If you still do not know, after considering the above, then start by making it as a `trait`**. You can always change it later, and in general using a trait keeps more options open.

<br>

##Chp.13 Packages and Imports

Put curly braces to create a scope.

```scala
package bobsrockets.com
```

Several patterns:

```scala
// 1.

package bobsrockets {
  package navigation {
    // In package bobsrockets.navigation
    class Navigator
    package tests {
      // In package bobsrockets.navigation.tests
      class NavigatorSuite
    }
} }
```

Concise access to classes and packages:

```scala
// 2.

package bobsrockets {
  package navigation {
    class Navigator {
      // No need to say bobsrockets.navigation.StarMap
      val map = new StarMap
    }
    class StarMap
  }
  class Ship {
    // No need to say bobsrockets.navigation.Navigator
    val nav = new navigation.Navigator
  }
  package fleets {
    class Fleet {
      // No need to say bobsrockets.Ship
      def addShip() { new Ship }
    }
  }
}
```

Symbols in enclosing packages not automatically available:

```scala
// 3.

package bobsrockets {
  class Ship
}

package bobsrockets.fleets {
  class Fleet {
    // Doesn’t compile! Ship is not in scope.
    def addShip() { new Ship }
  }
}
```

Accessing hidden package names via `_root_`:

```scala
// 4.

// In file launch.scala
package launch {
  class Booster3
}
 // In file bobsrockets.scala
package bobsrockets {
  package navigation {
    package launch {
      class Booster1
    }
    class MissionControl {
      val booster1 = new launch.Booster1
      val booster2 = new bobsrockets.launch.Booster2
      val booster3 = new _root_.launch.Booster3
    }
  }
  package launch {
    class Booster2
  }
}
```

1. A class can be accessed from within its own package without needing a prefix.

2. A package itself can be accessed **from its containing package without needing a prefix**.

3. When using the curly-braces packaging syntax, all names accessible in scopes outside the packaging are also available inside it ex: `def addShip() { new Ship }` in code snippet 2.

4. This kind of access is only available if you explicitly nest the packagings. If you stick to one package per file, then —like in Java— **the only names available will be the ones defined in the current package.** Ex: code snippet 3.

5. If nesting packages with braces shifts your code uncomfortably to the right, you can also use multiple package clauses without the braces:

    ```scala
    package bobsrockets
    package fleets
    class Fleet {
      // Doesn’t compile! Ship is not in scope.
      def addShip() { new Ship }
    }
    ```

6. Scala provides a package named `_root_` that is outside any package a user can write. Put another way, **every top-level package you can write is treated as a member of package `_root_`**. So `_root_.launch.Booster3` designates the outermost booster class.

<br>

### 13.3 Imports

```scala
// easy access to Fruit
import bobsdelights.Fruit
// easy access to all members of bobsdelights
import bobsdelights._
// easy access to all members of Fruits
import bobsdelights.Fruits._
```

Imports in Scala can appear anywhere, not just at the beginning of a compilation unit. **Also, they can refer to arbitrary values**.

```scala
def showFruit(fruit: Fruit) {
    import fruit._
    println(name +"s are "+ color)
}
```

The subsequent `println` statement can refer to `name` and `color` directly. These two references are equivalent to `fruit.name` and `fruit.color`. In Scala, **imports**:

**May apeer anywhere.**
**May refer to objects (singleton or regular) in addition to         packages.**
<br>
**Let you rename and hide some of the imported members.**
<br>
<br>

Another way Scala’s imports are flexible is that they can import packages themselves, not just their non-package members:
```scala
import java.util.regex
class AStarB {
    // Accesses java.util.regex.Pattern
    val pat = regex.Pattern.compile("a*b")
}
```

Imports in Scala can also rename or hide members:

```scala
import Fruits.{Apple, Orange}
// This imports just members Apple and Orange from object Fruits.

import Fruits.{Apple => McIntosh, Orange}
// This imports the two members Apple and Orange from object Fruits.
// and rename Apple object to McIntosh

import Fruits.{Apple => McIntosh, _}

import Fruits.{Pear => _, _}
// This imports all members of Fruits except Pear.
```

**`<original-name> => _` excludes `<original-name>` from the names that are imported**.

<br>

### 13.4 Implicit Imports

```scala
import java.lang._ // everything in the java.lang package
import scala._     // everything in the scala package
import Predef._    // everything in the Predef object
```

Because scala is imported implicitly, you can write `List` instead of `scala.List`, for instance.

An important rule - **Later imports overshadow earlier ones**. So if `scala` and `java` package both have `StringBuilder` **then `StringBuilder` will refer to `Scala.StringBuilder` instead**.

<br>

### 13.5 Access Modifier

Members of packages, classes, or objects can be labeled with the access modifiers `private` and `protected`.

A member labeled `private` is **visible only inside the class or object that contains the member definition**. In Scala, **this rule applies also for inner classes**. This treatment is more consistent, but differs from Java.

```scala
class Outer {
    class Inner {
        private def f() { println("f") }
        class InnerMost {
            f() // OK }
        }
    }
    (new Inner).f() // error: f is not accessible
}
```

Access to `protected` members is also a bit more restrictive than in Java. In Scala, a protected member is **only accessible from subclasses of the class in which the member is defined in same package**. In **Java such accesses are also possible from other classes in the same package**.

```scala
package p {
    class Super {
        protected def f() { println("f") }
    }
    class Sub extends Super {
        f()
    }
    class Other {
        (new Super).f()  // error: f is not accessible
    }
}
```

http://stackoverflow.com/questions/2632247/scala-giving-me-illegal-start-of-definition

**Every member not labeled `private` or `protected` is `public`. There is no explicit modifier for public members**.

A modifier of the form `private[X]` or `protected[X]` means that access is `private` or `protected` up to X, where X designates some enclosing package, class or singleton object.

```scala
package bobsrockets

package navigation {
    private[bobsrockets] class Navigator {
        protected[navigation] def useStarChart() {}
        class LegOfJourney {
            private[Navigator] val distance = 100
        }
        private[this] var speed = 200
    }
}
package launch {
    import navigation._
    object Vehicle {
        private[launch] val guide = new Navigator
    }
}
```

|modifier|scope|
|----|----|
|no access modifier    | public access   |
|private[bobsrockets]|  access within outer package |
|protected[navigation]  | same as package visibility in Java|
|private[Navigator]  | same as private in Java |
|private[LegOfJourney]   | same as private in Scala |
|private[this]   | access only from same object|


<br>

##### Visibility and Companion Objects

In Scala there are no static members; instead you can have a companion object that contains members that exist only once.

Scala’s access rules **privilege companion objects and classes when it comes to `private` or `protected` accesses**.

```scala
class Rocket {
    import Rocket.fuel
    private def canGoHomeAgain = fuel > 20
}

object Rocket {
    private def fuel = 10
    def chooseStrategy(rocket: Rocket) {
      if (rocket.canGoHomeAgain)
        goHome()
      else
        pickAStar()
    }
    def goHome() {}
    def pickAStar() {}
}
```

By contrast, **a protected member in a companion object makes no sense, as singleton objects don’t have any subclasses**.


<br>

### 13.6 Package objects

Each package is allowed to have **one** package object. Any definitions placed in a package object are considered members of the package itself.

```scala
// In file bobsdelights/package.scala
package object bobsdelights {
    def showFruit(fruit: Fruit) {
      import fruit._
      println(name +"s are "+ color)
    }
}
// In file PrintMenu.scala
package printmenu
  import bobsdelights.Fruits
  import bobsdelights.showFruit
  object PrintMenu {
    def main(args: Array[String]) {
      for (fruit <- Fruits.menu) {
        showFruit(fruit)
      }
  }
}
```

Package objects are frequently used to hold package-wide **type aliases** and **implicit conversions**.

Package objects are compiled to class files named `package.class` that are the located in the directory of the package that they augment. It’s useful to keep the same convention for source files.

So you would typically put the source file of the `package object bobsdelights` into a file named `package.scala` **that resides in the `bobsdelights` directory**.

<br>

##Chp.14 Assertions and Unit Testing

SKIPPED

<br>

##Chp.15 Case Classes and Pattern Matching

```scala
abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr
```

`case` class help several things:

1. It adds a factory method with the name of the class. So you dont have to create object `Object("x")` instead of `new Object("x")`

    ```scala
    val v = Var("x")
    // instead of new Var("x")
    ```

2. All arguments in the parameter list of a case class implicitly get a `val` prefix, so they are maintained as fields:

    ```scala
    val v = Var("x")
    v.name
    // x
    ```

3. The compiler adds **natural** implementations of methods `toString`, `hashCode`, and `equals` to your class.

4. The compiler adds a `copy` method to your class for making modified copies. This method is useful for **making a new instance of the class that is the same as another one except that one or two attributes are different**.

    ```scala
    val op = BinOp("+", Number(1), v)
    //  op: BinOp = BinOp(+,Number(1.0),Var(x))

    op.copy(operator = "-")
    // res4: BinOp = BinOp(-,Number(1.0),Var(x))
    ```

<br>

##### Pattern Matching

`selector match { alternatives }` instead of Java's `switch (selector) { alternatives }`

Assume we have 3 patterns in out input:

```scala
UnOp("-", UnOp("-", e)) => e // Double negation
BinOp("+", e, Number(0)) => e // Adding zero
BinOp("*", e, Number(1)) => e // Multiplying by one
```

Then we can write a function:

```scala
def simplifyTop(expr: Expr): Expr = expr match {
  case UnOp("-", UnOp("-", e))  => e   // Double negation
  case BinOp("+", e, Number(0)) => e   // Adding zero
  case BinOp("*", e, Number(1)) => e   // Multiplying by one
  case _ => expr
}
```

A match expression is evaluated by trying each of the patterns **in the order they are written**. The first pattern that matches is selected, and the part following the arrow is selected and executed.

A **constant pattern** like `+` or `1` matches values that are equal to the constant with respect to `==`.

A **variable pattern** like `e` matches every value.

The **wildcard pattern (`_`)** also matches every value, **but it does not introduce a variable name to refer to that value**.

A **constructor pattern** looks like `UnOp("-", e)`. This pattern matches all values of **type `UnOp` whose first argument matches  `-` and whose second argument matches `e`**.

Scala's `match` has different behaviour like:

1. `match` is an expression in Scala, it always results in a value.
2. Scala’s alternative expressions **never fall through into the next case**.
3. If none of the patterns match, an exception named `MatchError` is thrown.

**A pattern match with an empty default case**:

```scala
expr match {
    case BinOp(op, left, right) =>
      println(expr +" is a binary operation")
    case _ =>
}
```

<br>

##### Kinds of Patterns

Wildcard patterns match ant objects:

```scala
expr match {
  case BinOp(op, left, right) =>
    println(expr +" is a binary operation")
  case _ =>
}
```

Constant patterns matches only itself. **Any literal may be used as a constant**. Also, **any `val` or singleton object can be used as a constant**:

```scala
def describe(x: Any) = x match {
  case 5 => "five"
  case true => "truth"
  case "hello" => "hi!"
  case Nil => "the empty list"
  case _ => "something else"
}
```

A variable pattern matches any object, just like a wildcard. Unlike a wildcard, **Scala binds the variable to whatever the object is**:

```scala
expr match {
  case 0 => "zero"
  case somethingElse => "not zero: "+ somethingElse
}
```

<br>

##### Variable or constant?

Scala uses a simple lexical rule: a simple name **starting with a lowercase letter is taken to be a pattern variable**; all other references are taken to be **constants**.  

```scala
import math.{E, Pi}

val pi = math.Pi

E match {
  case pi => "strange math? Pi = "+ pi
}
// res12: java.lang.String = strange math? Pi = 2.718281828459045
```

Since `pi` is a variable pattern, it will match all inputs, **and so no cases following it can be reached**:

```scala
E match {
  case pi => "strange math? Pi = "+ pi
  case _ => "OK"
}
//error: unreachable code
//    case _ => "OK"
```

First, if the constant is a field of some object, you can prefix it with a qualifier. For instance, `pi` is a variable pattern, but `this.pi` or `obj.pi` are constants even though they start with lowercase letters.

If that does not work (because `pi` is a local variable, say), you can alternatively enclose the variable name **in back ticks**:

```scala
import math.{E, Pi}

val pi = math.Pi

E match {
  // use pi's value as constant pattern
  case `pi` => "strange math? Pi = "+ pi
  case _ => "OK"
}
// OK
```

Earlier on, in Section 6.10, you saw that **back-tick syntax** can also be used to treat a keyword as an ordinary identifier, e.g., writing ```Thread.`yield`()``` treats `yield` as an identifier rather than a keyword.

Constructor patterns supports **deep matches**, below example is one line long yet checks three levels deep:

```scala
expr match {
  case BinOp("+", e, Number(0)) => println("a deep match")
  case _ =>
}
```

<br>

##### Sequence patterns

Supply with `_*` to match arbitrary length `List`:

```scala
expr match {
  case List(0, _*) => println("found it")
  case _ =>
}
```

<br>

##### Tuple patterns

```scala
def tupleDemo(expr: Any) =
  expr match {
    case (a, b, c)  =>  println("matched "+ a + b + c)
    case _ =>
  }
```

<br>

##### Typed patterns

You can use a typed pattern as a convenient replacement for type tests and type casts.

```scala
def generalSize(x: Any) = x match {
  case s: String => s.length
  case m: Map[_, _] => m.size
  case _ => -1
}

generalSize("abc")
// Int = 3

generalSize(Map(1 -> 'a', 2 -> 'b'))
// Int = 2

generalSize(math.Pi)
// Int = -1
```

Note that, even though `s` and `x` refer to the same value, the **type of `x` is `Any`, but the type of `s` is `String`**. So you could not write `x.length`, because the type `Any` does not have a `length` member.

To test whether an expression `expr` has type `String`, say, you write: ```expr.isInstanceOf[String]```.

To cast the same expression to type String, you use: ```expr.asInstanceOf[String]```.

As you will have noted by now, **writing type tests and casts is rather verbose in Scala. That’s intentional, because it is not encouraged practice**. You are usually better off using a **pattern match** with a typed pattern.


<br>

##### Type erasure

```scala
def isIntIntMap(x: Any) = x match {
  case m: Map[Int, Int] => true
  case _ => false
}

// warning: there were unchecked warnings; re-run with
//  -unchecked for details

isIntIntMap(Map(1 -> 1))
// Boolean = true

isIntIntMap(Map("abc" -> "abc"))
// Boolean = true
```

Scala uses the **erasure model of generics**, just like Java does. This means that no information about type arguments is maintained at runtime.

Consequently, there is no way to determine at runtime whether a given `Map` object has been created with `Int` types.

Because `isIntIntMap(Map("abc" -> "abc"))` may surprise you, so Scala will warn you that unchecked, but erased type.

**The only exception to the erasure rule is arrays, because they are handled specially in Java as well as in Scala**.

```scala
def isStringArray(x: Any) = x match {
  case a: Array[String] => "yes"
  case _ => "no"
}

val as = Array("abc")
//  as: Array[java.lang.String] = Array(abc)
```

<br>

##### Variable binding

An at sign (`@`), and then the pattern. This gives you a variable-binding pattern.

```scala
expr match {
  case UnOp("abs", e @ UnOp("abs", _)) => e
  case _ =>
}

// A pattern with a variable binding (via the @ sign).
```

<br>

##### Pattern Guard

```scala
def simplifyAdd(e: Expr) = e match {
  case BinOp("+", x, x) => BinOp("*", x, Number(2))
  case _ => e
}
// x is already defined as value x ...
```

Scala restricts patterns to be linear: a pattern variable may only appear once in a pattern.

But we can re-formulate the match with pattern guard `x == y`:

```scala
def simplifyAdd(e: Expr) = e match {
  case BinOp("+", x, y) if x == y =>
    BinOp("*", x, Number(2))
  case _ => e
}
// simplifyAdd: (e: Expr)Expr
```

A pattern guard comes after a pattern and starts with an `if`. The guard can be an arbitrary boolean expression. If a pattern guard is present, **the match succeeds only if the guard evaluates to `true`**.

**it is important that the catch-all cases come after the more specific simplification rules. If you wrote them in the other order, then the catch-all case would be run in favor of the more specific rules. In many cases, the compiler will even complain if you try.

<br>

###15.5 Sealed Classes

Whenever you write a pattern match, you need to make sure you have covered all of the possible cases. How can you ever feel safe that you covered all the cases?

In fact, you can enlist the help of the Scala compiler in detecting missing combinations of patterns in a match expression. In general, this is impossible in Scala, because new case classes can be defined at any time and in arbitrary compilation units.

The alternative is to make the superclass of your case classes **sealed**. **A `sealed` class cannot have any new subclasses added except the ones in the same file**.

This is very useful for pattern matching, because it means you only need to worry about the subclasses you already know about.

```scala
sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_)    => "a variable"
}

//  warning: match is not exhaustive!
//  missing combination           UnOp
//  missing combination          BinOp
```

Such a warning tells you that there’s a risk your code might produce a `MatchError` exception because some possible patterns (`UnOp`, `BinOp`) are not handled.

Now you may add wildcard pattern but not ideal because you think it should never entered:

```scala
def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
  case _ => throw new RuntimeException // Should not happen
}
```

A more lightweight alternative is **to add an `@unchecked` annotation to the selector expression of the match**:

```scala
def describe(e: Expr): String = (e: @unchecked) match {
  case Number(_) => "a number"
  case Var(_)    => "a variable"
}
```

The `@unchecked` annotation has a special meaning for pattern matching. If a match’s selector expression carries this annotation, exhaustivity checking for the patterns that follow will be suppressed.

<br>

### 15.6 The Option type

Optional values are produced by some of the standard operations on Scala’s collections, such as `Map#get()` then you can deal it with pattern matching:

```scala
val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

def show(x: Option[String]) = x match {
  case Some(s) => s
  case None => "?"
}
```

Use `Option` type mitigate `NullPointerException may result at runtime.` error prone as in Java.

<br>

### 15.7 Patterns everywhere

Any time you define a `val` or a `var`, you can use a pattern instead of a simple identifier.

```scala
val myTuple = (123, "abc")
//myTuple: (Int, java.lang.String) = (123,abc)

val (number, string) = myTuple
// number: Int = 123
// string: java.lang.String = abc
```

This helps you to deconstruct it with a pattern:

```scala
val exp = new BinOp("*", Number(5), Number(1))
// exp: BinOp = BinOp(*,Number(5.0),Number(1.0))

val BinOp(op, left, right) = exp
//  op: String = *
//  left: Expr = Number(5.0)
//  right: Expr = Number(1.0)
```

<br>

##### Case sequences as partial functions

A sequence of cases in curly braces can be used anywhere a function literal can be used.

Instead of having a single entry point and list of parameters, a case sequence has multiple entry points, each with their own list of parameters. **Each case is an entry point to the function, and the parameters are specified with the pattern. The body of each entry point is the right-hand side of the case**.

```scala
val withDefault: Option[Int] => Int = {
  case Some(x) => x
  case None => 0
}

withDefault(Some(10))
// Int = 10

withDefault(None)
// Int = 0
````

**NOTE**: a sequence of cases gives you **a partial function**. If you apply such a function on a value it does not support, it will generate a `run-time exception`.

```scala
val second: List[Int] => Int = {
  case x :: y :: _ => y
}

// warning: match is not exhaustive!
//  missing combination  Nil

second(List())
//  scala.MatchError: List()
```

If you want to check whether a partial function is defined, you must first tell the compiler that you know you are working with partial functions.

The type that only includes partial functions from lists of integers to integers is written `PartialFunction[List[Int], Int]`.

Partial functions have a method `isDefinedAt`, **which can be used to test whether the function is defined at a particular value**.

```scala
val second: PartialFunction[List[Int], Int] = {
  case x :: y :: _ => y
}

second.isDefinedAt(List(5,6,7))
// Boolean = true

second.isDefinedAt(List())
// Boolean = false
```

In fact, such an expression gets translated by the Scala compiler to a partial function by translating the patterns twice, **once for the implementation of the real function, and once to test whether the function is defined or not**.

```scala
// For instance, the function literal { case x :: y :: _ => y }

new PartialFunction[List[Int], Int] {
  def apply(xs: List[Int]) = xs match {
    case x :: y :: _ => y
  }
  def isDefinedAt(xs: List[Int]) = xs match {
    case x :: y :: _ => true
    case _ => false
  }
}
```

This translation takes effect whenever the declared type of a function literal is `PartialFunction`. **If the declared type is just `Function1`, or is missing, the function literal is instead translated to a complete function**.

<br>

##### Patterns in `for` Expressions

```scala
for ((country, city) <- capitals)
  println("The capital of "+ country +" is "+ city)

// The capital of France is Paris
// The capital of Japan is Tokyo
````

It is equally possible that a pattern might not match a generated value, below example will not show `None` value.

```scala
val results = List(Some("apple"), None, Some("orange"))
// List[Option[java.lang.String]] = List(Some(apple), None, Some(orange))

for (Some(fruit) <- results) println(fruit)

// apple
// orange
```

<br>

##Chp.16 List
