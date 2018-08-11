#Apache Spark
-------------------------------------------------

Apache Spark is an open-source distributed general-purpose cluster computing framework with in-memory 
data processing engine that can do ETL, analytics, machine learning and graph processing on 
large volumes of data at rest (batch processing) or in motion (streaming processing) with rich concise 
high-level APIs for the programming languages Scala, Python, Java, R, and SQL.

http://data-flair.training/blogs/apache-spark-rdd-vs-dataframe-vs-dataset/

###Spark RDD APIs:
<collection or tuple with no schema or data type>
An RDD stands for Resilient Distributed Datasets. 
It is Read-only partition collection of records. RDD is the fundamental
data structure of Spark. 
It allows a programmer to perform in-memory computations on large
clusters in a fault-tolerant manner. Thus, speed up the task. 

###Spark Dataframe APIs:
<table in database or excel sheet with column names>
Unlike an RDD, data organized into named columns. 
For example a table in a relational database. 
It is an immutable distributed collection of data. DataFrame in Spark allows developers to impose a 
structure onto a distributed collection of data, allowing higher-level abstraction.

###Spark Dataset APIs:
<entity framework with type safe object oriented interface>
Datasets in Apache Spark are an extension of DataFrame API which provides type-safe, object-oriented programming interface.
Dataset takes advantage of Spark’s #Catalyst optimizer# by exposing expressions
and data fields to a query planner. Follow this link to learn Spark DataSet in
detail

<partition>
http://www.bigsynapse.com/spark-input-output
Resilient Distributed Datasets are collection of various data items that are so huge in size, that they cannot fit into a single node and have to be partitioned across various nodes. Spark automatically partitions RDDs and distributes the partitions across different nodes. A partition in spark is an atomic chunk of data (logical division of data) stored on a node in the cluster. Partitions are basic units of parallelism in Apache Spark. RDDs in Apache Spark are collection of partitions.
Creating a Partition in Spark

Here’s a simple example that creates a list of 10 integers with 3 partitions –

integer_RDD = sc.parallelize (range (10), 3)
Characteristics of Partitions in Apache Spark

Every machine in a spark cluster contains one or more partitions.
The number of partitions in spark are configurable and having too few or too many partitions is not good.
Partitions in Spark do not span multiple machines

-------------------------------------------------
#RDD 
-------------------------------------------------

Resilient Distributed DataSet
A RDD is a resilient and distributed collection of records. One could compare RDD to a
Scala collection (that sits on a single JVM) to its distributed variant (that sits on many JVMs, possibly on separate nodes in a cluster).

RDDs are distributed by design and to achieve even data distribution as well as leverage
data locality (in distributed systems like HDFS or Cassandra in which data is partitioned by
default), they are partitioned to a fixed number of partitions - logical chunks (parts) of data.
The logical division is for processing only and internally it is not divided whatsoever. Each
partition comprises of records.

"Partitions are the units of parallelism." You can control the number of partitions of a RDD
using repartition or coalesce transformations. Spark tries to be as close to data as possible
without wasting time to send data across network by means of RDD shuffling, and creates
as many partitions as required to follow the storage layout and thus optimize data access. It
leads to a one-to-one mapping between (physical) data in distributed data storage, e.g.
HDFS or Cassandra, and partitions.

RDDs support two kinds of operations:
	1. transformations - lazy operations that return another RDD.
		A transformation is a lazy operation on a RDD that returns another RDD, like map ,
		flatMap , filter , reduceByKey , join , cogroup , etc.
	2. actions - operations that trigger computation and return values.
		An action is an operation that triggers execution of RDD transformations and returns a value
		(to a Spark driver - the user program).
Creating RDD:

```scala
	val rdd = sc.parallelize(1 to 1000)
	val data = Seq.fill(10)(util.Random.nextInt)
	val rdd = sc.parallelize(data)
	val words = sc.textFile("README.md").flatMap(_.split("\\W+")).cache
```

Transformations:
Transformations are functions that take a RDD as the input and produce one
or many RDDs as the output. They do not change the input RDD (since RDDs are
immutable and hence cannot be modified), but always produce one or more new RDDs by
applying the computations they represent.
By applying transformations you incrementally build a #RDD lineage# with all the parent RDDs
of the final RDD(s).

	Parent RDD 1 => Parent RDD 2 => Parent RDD 3 => RDD

Transformations are lazy, i.e. are not executed immediately. Only after calling an action are
transformations executed.
After executing a transformation, the result RDD(s) will always be different from their parents
and can be smaller (e.g. filter , count , distinct , sample ), bigger (e.g. flatMap ,
union , cartesian ) or the same size (e.g. map ).

```scala
	val file = sc.textFile("README.md")
	val allWords = file.flatMap(_.split("\\W+"))
	val words = allWords.filter(!_.isEmpty)
	val pairs = words.map((_,1))
	val reducedByKey = pairs.reduceByKey(_ + _)
	val top10words = reducedByKey.takeOrdered(10)(Ordering[Int].reverse.on(_._2))
```

	- Two types of transformations:
		1. Narrow
			Narrow transformations are the result of map , filter and such that is from the data from
			a single partition only, i.e. it is self-sustained.
			An output RDD has partitions with records that originate from a single partition in the parent
			RDD. Only a limited subset of partitions used to calculate the result.
			Spark groups narrow transformations as a stage which is called pipelining.
		2. Wide
			Wide transformations are the result of groupByKey and reduceByKey . The data required to
			compute the records in a single partition may reside in many partitions of the parent RDD.
			All of the tuples with the same key must end up in the same partition, processed by the
			same task. To satisfy these operations, Spark must execute RDD shuffle, which transfers
			data across cluster and results in a new stage with a new set of partitions.
		3. map
			Using an external key-value store (like HBase, Redis, Cassandra) and performing
			lookups/updates inside of your mappers (creating a connection within a mapPartitions code
			block to avoid the connection setup/teardown overhead) might be a better solution.

Actions:
Actions are RDD operations that produce non-RDD values. They materialize a value in a
Spark program. In other words, a RDD operation that returns a value of any type but
RDD[T] is an action.

Simply put, an action evaluates the RDD lineage graph.
You can think of actions as a valve and until action is fired, the data to be processed is not
even in the pipes, i.e. transformations. Only actions can materialize the entire processing
pipeline with real data.
Actions are one of two ways to send data from executors to the driver (the other being
accumulators).

RDD Lineage:
A RDD Lineage Graph (aka RDD operator graph) is a graph of all the parent RDDs of a
RDD. It is built as a result of applying transformations to the RDD and creates a logical
execution plan.

```python
	val r00 = sc.parallelize(0 to 9)
	val r01 = sc.parallelize(0 to 90 by 10)
	val r10 = r00 cartesian r01
	val r11 = r00.map(n => (n, n))
	val r12 = r00 zip r01
	val r13 = r01.keyBy(_ / 20)
	val r20 = Seq(r11, r12, r13).foldLeft(r10)(_ union _)
```

A RDD lineage graph is hence a graph of what transformations need to be executed after an
action has been called.

Logical Execution Plan:
Logical Execution Plan starts with the earliest RDDs (those with no dependencies on other
RDDs or reference cached data) and ends with the RDD that produces the result of the
action that has been called to execute.

Partitions and Partitioning:
!A partition (aka split) is a logical chunk of a large distributed data set.
Preferred way to set up the number of partitions for an RDD is to directly pass it as the
second input parameter in the call like rdd = sc.textFile("hdfs://… /file.txt", 400) , where
400 is the number of partitions. In this case, the partitioning makes for 400 splits that would
be done by the Hadoop’s TextInputFormat , not Spark and it would work much faster. It’s
also that the code spawns 400 concurrent tasks to try to load file.txt directly into 400 partitions.
It will only work as described for uncompressed files.

When using textFile with compressed files ( file.txt.gz not file.txt or similar), Spark
disables splitting that makes for an "RDD with only 1 partition" (as reads against gzipped files
cannot be parallelized). In this case, to change the number of partitions you should do repartitioning.
<Some operations, e.g. map , flatMap , filter , don’t preserve partitioning.>
map , flatMap , filter operations apply a function to every partition.

https://www.dezyre.com/article/how-data-partitioning-in-spark-helps-achieve-more-parallelism/297

-------------------------------------------------
#Spark SQL
-------------------------------------------------

Spark SQL is a Spark module for structured data processing. Unlike the basic Spark RDD API, the 
interfaces provided by Spark SQL provide Spark with more information about the structure of both
the data and the computation being performed. 
Internally, Spark SQL uses this extra information to perform extra optimizations. 
There are several ways to interact with Spark SQL including SQL and the Dataset API. 
When computing a result the same execution engine is used, independent of which API/language
you are using to express the computation. 
This unification means that developers can easily switch back and forth between different APIs 
based on which provides the most natural way to express a given transformation.

-------------------------------------------------
#Spark Streaming
-------------------------------------------------
Spark Streaming:
• Extension of the core Spark API that enables scalable, high-throughput, 
fault-tolerant stream processing of live data streams
• Data can be ingested from many sources like Kafka, Flume, Twitter, ZeroMQ, Kinesis, or TCP
sockets
• Data can further be processed using MLlib, Graph processing or high level functions like map, reduce, join, window etc
• Processed data can be pushed out to file systems, databases and live dashboards

-------------------------------------------------
#DStream — Discretized Stream
-------------------------------------------------
Discretized Stream is a sequence of Resilient Distributed Databases that represent a stream of data.
DStreams can be created from various sources like Apache Kafka, HDFS, and Apache Flume. DStreams have two operations –

Transformations that produce a new DStream.
Output operations that write data to an external system.

Discretized Stream (DStream) is the fundamental concept of Spark Streaming. It is
basically a stream of RDDs with elements being the data received from input streams for
batch (possibly extended in scope by windowed or stateful operators).
There is no notion of input and output dstreams. DStreams are all instances of DStream
abstract class (see DStream Contract in this document). You may however correctly assume
that all dstreams are input. And it happens to be so until you register a dstream that marks it
as output.

-------------------------------------------------
#Structued streaming
-------------------------------------------------

Structured Streaming — Streaming Datasets
Structured Streaming is a new computation model introduced in Spark 2.0.0 for building
end-to-end streaming applications termed as continuous applications. Structured
streaming offers a high-level declarative streaming API built on top of Datasets (inside Spark
SQL’s engine) for continuous incremental execution of structured queries.

Structured streaming is an attempt to unify streaming, interactive, and batch queries that
paves the way for continuous applications like continuous aggregations using groupBy
operator or continuous windowed aggregations using groupBy operator with window function.

-------------------------------------------------
#Spark MLlib
-------------------------------------------------

Spark MLlib, Machine Learning Library
• Goal is to make practical Machine Learning scalable and easy.
• Includes common machine Learning algorithms like classification, regression, clustering,
collaborative filtering.
• Provides utilities for feature extraction, transformation, dimensionality reduction and selection.
• Provides tools for constructing Machine Learning pipelines, evaluating and tuning them.
• Supports persistence of models and pipelines.
• Includes convenient utilities for linear algebra, statistics, data handling etc.


!Linear Regression
https://www.youtube.com/watch?v=CtKeHnfK5uA
<http://www.bmc.com/blogs/sgd-linear-regression-example-apache-spark/>
http://cs229.stanford.edu/proj2013/229final.pdf

Used smart meter data and weather data to predict energy demand in the future. 
Linear Regression. 

Linear Regression is a model to find relationship between two variables by setting linear equation
to observed data. 
One variable is explanatory variable and other one is dependent variable. 
Dependent variables can be dentoes as y and independent variables can be denoted as x. 

X <=> Y

Future predition over time 

Ad Revenues over time. 

LINE OF BEST FIT

Y = MX + B

B is the Intercept goes up or down
M is slope




Spark ML Pipelines
------------------------------------

Provide high level API that help users create and tune practical machine learning pipelines.
Allows to combine multiple machine learning algorithms and utilities into a single pipeline
Key concepts in the Pipeline API:
• DataFrame: Is the ML dataset and can hold a variety of data type
• Transformer: Is an algorithm which can transform one DataFrame into another DataFrame.
• Estimator: Is an algorithm which can be fit on a DataFrame to produce a Transformer.
• Pipeline: A Pipeline chains multiple Transformers and Estimators together to specify an ML workflow.
• Parameter: This API allows specifying parameters on all Transformers and Estimators


Spark MLlib is a module (a library / an extension) of Apache Spark to provide distributed
machine learning algorithms on top of Spark’s RDD abstraction. Its goal is to simplify the
development and usage of large scale machine learning.

You can find the following types of machine learning algorithms in MLlib:
Classification
Regression
Recommendation
Feature extraction and selection

LinearRegression:
LinearRegression is an example of Predictor (indirectly through the specialized Regressor
private abstract class), and hence a Estimator , that represents the linear regression
algorithm in Machine Learning.
LinearRegression belongs to org.apache.spark.ml.regression package.


QA
----
What is Future and Promise?
(Task Task completion source)

A Future is a placeholder object for a value that may not yet exist. Generally, the value of the Future is supplied concurrently and can subsequently be used. Composing concurrent tasks in this way tends to result in faster, asynchronous, non-blocking parallel code.


https://www.youtube.com/watch?v=CtKeHnfK5uA
I'll be talking about linear  regression linear regression attempts to  model the relationship between two  variables by setting a linear equation  to the observed data one variable is  considered to be an explanatory variable  while the other is considered to be a  dependent variable please don't forget  to subscribe and click the bell icon for  more videos on machine learning so the  dependent variable it's a variable whose  values we want to explain or forecast  the independent or explanatory variable  is a variable that explains the other  variables and the values are independent  dependent variables can be denoted as Y  so you can imagine a child always asking  why is it dependent on these parents and  then you can imagine the X as your  ex-boyfriend or girlfriend who is  independent because they don't need you  or depend on you a good way of  remembering anyways linear regression  can be used in one of two ways to  establish if there's a relation between  two variables or see if there is a  statistically significant relationship  between the two variables so for example  you want to see how an increase in some  tax has an effect on how many cigarette  packs are consumed how many sleep hours  versus discourse see what's the  correlation between experience for  salaries pokeymon versus urban density  and Hospital area versus house price the  second application is to forecast new  observations we can use what we already  know to focus unobserved values here are  some examples of the way that linear  regression can be applied so say is the  return on investment of participants  over time as well as stock price over  time or to predict the price of the coin  over there so you can think of linear  regression as the line of this set so  the line of best fit can be represented  by the linear equation y equals a plus  BX  or y equals MX plus B or 

y equals B sub  set 0 plus B sub set 1 times X it mostly  learned us in school 

so B is intercept  if you increase this variable the  intercept moves up or down along the y  axis and M is the slope or gradient if  it changes then the line rotates along  the intercept so theta is actually a  series of x and y observations as shown  on the scatter plot they don't follow a  straight line however they do follow a  linear pattern hence the term linear  regression assuming that you already  have the best fit line we can calculate  the error term epsilon also known as the  residual and this is the term that we  would like to minimize along all the  points
#Y = MX + B

y = mx + b
= sales = some function of (advertising expenses ) + constant_value (b)
= (number of ads purchased (m))* (average age cost of ad ( x)) + b.

# E IS RESIDUAL 


!org.apache.spark.mllib.regression.LinearRegressionWithSGD



