## Architecting for cloud whitepaper.

AWS Cloud offer the following benefits over on premise hardware infrastructure:

* IT Assets Become Programmable Resources
* Global, Available, and Unlimited Capacity
* Higher Level Managed Services
* Security Built In

### Design Principles

#### Scalability
  * Scaling Vertically
  * Scaling Horizontally
    Application architectures and considerations to scale horizontally:
      
      * Stateless Applications: A stateless application is an application that needs no knowledge of previous interactions and stores no session information.

        How to distribute load to multiple nodes?

        >> -Push Model
        Load can be distributed using ELB (loadbalancer) or through implementation of a DNS round robin using route 53.      	
      - Pull Model
        Asynchronous event-driven workloads do not require a load balancing solution because you can implement a pull model instead. In a pull model, tasks that need to be performed or data that need to be processed could be stored as messages in a queue using Amazon Simple Queue Service (Amazon SQS) or as a streaming data solution like Amazon Kinesis. Multiple compute nodes can then pull and consume those messages, processing them in a distributed fashion.

      * Stateless Components:
        - Use DynamoDb to store user session information instead of storing it on the servers.
        - If you need to store large files use shared storage such as EFS, S3.
        - Amazon Simple workflow service can be utilized to centrally store execution history and make workloads stateless.

      * Stateful Components
        Inevitably, there will be layers of your architecture that you won’t turn into stateless components.

        You might still be able to scale those components horizontally by distributing load to multiple nodes with “session affinity.” In this model, you bind all the transactions of a session to a specific compute resource. You should be aware of the limitations of this model. Existing sessions do not directly benefit from the introduction of newly launched compute nodes.

        How to implement session affinity?

        >> For HTTP/S traffic, session affinity can be achieved through the “sticky sessions” feature of ELB4. Elastic Load Balancing will attempt to use the same server for that user for the duration of the session.
        Another option, if you control the code that runs on the client, is to use client-side load balancing. This adds extra complexity but can be useful in scenarios where a load balancer does not meet your requirements. For example you might be using a protocol not supported by ELB or you might need full control on how users are assigned to servers (e.g., in a gaming scenario you might need to make sure game participants are matched and connect to the same server). In this model, the clients need a way of discovering valid server endpoints to directly connect to. You can use DNS for that, or you can build a simple discovery API to provide that information to the software running on the client. In the absence of a load balancer, the health checking mechanism will also need to be implemented on the client side. You should design your client logic so that when server unavailability is detected, devices reconnect to another server with little disruption for the application.  

      * Distributed Processing:
        By dividing a task and its data into many small fragments of work, you can execute each of them in any of a larger set of available compute resources.

        How to implement distributed processing?

        >> Offline batch jobs can be horizontally scaled by using a distributed data processing engine like Apache Hadoop. On AWS, you can use the Amazon Elastic MapReduce (Amazon EMR) service to run Hadoop workloads on top of a fleet of EC2 instances without the operational complexity. For real-time processing of streaming data, Amazon Kinesis partitions data in multiple shards that can then be consumed by multiple Amazon EC2 or AWS Lambda resources to achieve scalability.

#### Disposable Resources Instead of Fixed Servers
	
A server, once launched, is never updated throughout its lifetime. Instead, when there is a problem or a need for an update the server is replaced with a new one that has the latest configuration. In this way, resources are always in a consistent (and tested) state and rollbacks become easier to perform.


##### Instantiating Compute Resources        

It is important that you make this an automated and repeatable process that avoids long lead times and is not prone to human error. There are a few approaches on how to achieve an automated and repeatable process.

* Bootstrapping
>> You can use user data scripts and cloud-init6 directives or AWS OpsWorks lifecycle events7 to automatically set up new EC2 instances. You can use simple scripts, configuration management tools like Chef or Puppet. AWS OpsWorks natively supports Chef recipes or Bash/PowerShell scripts. In addition, through custom scripts and the AWS APIs, or through the use of AWS CloudFormation support for AWS Lambda-backed custom resources8, it is possible to write provisioning logic that acts on almost any AWS resource.

* Golden Images
golden image: a snapshot of a particular state of that resource. An Amazon AMI is a golden image when used in autoscaling launch configuration, where all the softwares and configurations are pre baked.
Golden images are most commonly used when launching new EC2 instances, they can also be applied to resources like Amazon RDS databases or Amazon EBS volumes.

Another option is to use Docker Images.

* Hybrid

It is possible to use a combination of the two approaches, where some parts of the configuration are captured in a golden image, while others are configured dynamically through a bootstrapping action.

eg. Elastic Beanstalk.

##### Infrastructure as code

AWS CloudFormation templates give developers and systems administrators an easy way to create and manage a collection of related AWS resources, and provision and update them in an orderly and predictable fashion.

#### Automation

When deploying on AWS there is a lot of opportunity for automation, so that you improve both your system’s stability and the efficiency of your organization:

* AWS Elastic Beanstalk is the fastest and simplest way to get an application up and running on AWS.
* Amazon EC2 Auto recovery: You can create an Amazon CloudWatch alarm that monitors an Amazon EC2 instance and automatically recovers it if it becomes impaired. A recovered instance is identical to the original instance, including the instance ID, private IP addresses, Elastic IP addresses, and all instance metadata. the instance is migrated through an instance reboot, and any data that is in-memory is lost.
* Auto Scaling
* Amazon CloudWatch Alarms
* Amazon CloudWatch Events16: The CloudWatch service delivers a near real-time stream of system events that describe changes in AWS resources. Using simple rules that you can set up in a couple of minutes, you can easily route each type of event to one or more targets: AWS Lambda functions, Amazon Kinesis streams, Amazon SNS topics, etc.
* AWS OpsWorks Lifecycle events: AWS OpsWorks supports continuous configuration through lifecycle events that automatically update your instances’ configuration to adapt to environment changes.
* AWS Lambda Scheduled events: These events allow you to create a Lambda function and direct AWS Lambda to execute it on a regular schedule.

#### Loose Coupling

Interdependencies—a change or a failure in one component should not cascade to other components.

##### Well-Defined Interfaces

A way to reduce interdependencies in a system is to allow the various components to interact with each other only through specific, technology-agnostic interfaces (e.g., RESTful APIs).

>> Amazon API Gateway is a fully managed service that makes it easy for developers to create, publish, maintain, monitor, and secure APIs at any scale. It handles all the tasks involved in accepting and processing up to hundreds of thousands of concurrent API calls, including traffic management, authorization and access control, monitoring, and API version management.

##### Service Discovery

How to implement service discovery?

For an Amazon EC2 hosted service a simple way to achieve service discovery is through the Elastic Load Balancing service. Because each load balancer gets its own hostname you now have the ability to consume a service through a stable endpoint. This can be combined with DNS and private Amazon Route53 zones, so that even the particular load balancer’s endpoint can be abstracted and modified at any point in time.

##### Asynchronous Integration

Examples of asynchronous integration

- A front end application inserts jobs in a queue system like Amazon SQS. A back-end system retrieves those jobs and processes them at its own pace. 
- An API generates events and pushes them into Amazon Kinesis streams. A back-end application processes these events in batches to create aggregated time-series data stored in a database. 
- Multiple heterogeneous systems use Amazon SWF to communicate the flow of work between them without directly interacting with each other. 
- AWS Lambda functions can consume events from a variety of AWS sources (e.g., Amazon DynamoDB update streams, Amazon S3 event notifications, etc.). In this case, you don’t even need to worry about implementing a queuing or other asynchronous integration method because the service handles this for you.

##### Graceful Failure

>> Graceful failure in practice A request that fails can be retried with an exponential backoff and Jitter strategy19 or it could be stored in a queue for later processing. For front-end interfaces, it might be possible to provide alternative or cached content instead of failing completely when, for example, your database server becomes unavailable. The Amazon Route 53 DNS failover feature also gives you the ability to monitor your website and automatically route your visitors to a backup site if your primary site becomes unavailable. You can host your backup site as a static website on Amazon S3 or as a separate dynamic environment.

#### Services, Not Servers

AWS offers a broad set of compute, storage, database, analytics, application, and deployment services that help organizations move faster and lower IT costs.

##### Managed Services

All about AWS Services

##### Serverless Architectures

You can upload your code to the AWS Lambda compute service and the service can run the code on your behalf using AWS infrastructure. With AWS Lambda, you are charged for every 100ms your code executes and the number of times your code is triggered. By using Amazon API Gateway, you can develop virtually infinitely scalable synchronous APIs powered by AWS Lambda.


#### Databases

##### Relational Databases

* Scalability - Known
	Relational database workloads that need to scale their write capacity beyond the constraints of a single DB instance require a different approach called data partitioning or sharding. With this model, data is split across multiple database schemas each running in its own autonomous primary DB instance.
* High Availability
	Known
* Anti Patterns
	Known

##### NoSQL Databases

Amazon DynamoDB is a fast and flexible NoSQL database23 service for applications that need consistent, single-digit millisecond latency at any scale. It is a fully managed cloud database and supports both document and key-value store models.	

* Scalability: NoSQL database engines will typically perform data partitioning and replication to scale both the reads and the writes in a horizontal fashion.

* High Availability: Known

* Anti-Patterns: Known

##### Data Warehouse

* Scalability
Amazon Redshift achieves efficient storage and optimum query performance through a combination of massively parallel processing (MPP), columnar data storage, and targeted data compression encoding schemes. It is particularly suited to analytic and reporting workloads against very large data sets. The Amazon Redshift MPP architecture enables you to increase performance by increasing the number of nodes in your data warehouse cluster.

* Availability

Amazon Redshift has multiple features that enhance the reliability of your data warehouse cluster. We recommend that you deploy production workloads in multi-node clusters in which data written to a node is automatically replicated to other nodes within the cluster. Data is also continuously backed up to Amazon S3. Amazon Redshift continuously monitors the health of the cluster and automatically re-replicates data from failed drives and replaces nodes as necessary.

* Anti Patterns

  High concurrency workload that generally involves reading and writing all of the columns for a small number of records.

##### Search

Applications that require sophisticated search functionality will typically outgrow the capabilities of relational or NoSQL databases. You have the choice between Amazon CloudSearch and Amazon Elasticsearch Service (Amazon ES).

  * Amazon CloudSearch is a managed service and will scale automatically
  * Amazon ES offers an opensource API and gives you more more control over the configuration details.

  It is often used for log analytics, real-time application monitoring, and click stream analytics.

* Scalability
  Amazon CloudSearch and Amazon ES use data partitioning and replication to scale horizontally.

* High Availability
  Data can be stored redundantly across availability zones.

#### Removing Single points of failure

This section discusses high availability design patterns

##### Introducing Redundancy

Single points of failure can be removed by introducing redundancy. Two types of redundancy can be configured:

1) In standby redundancy: Uses the failover process where functionality is recovered in a secondary resource. During failover the resources remains unavailable until launch.
2) Active Redundancy: Requests are distributed across multiple redundant compute resources, does not involve dead time.

##### Detect Failure

You can use services like ELB and Amazon Route53 to configure health checks and mask failure by routing traffic to healthy endpoints. In addition, Auto Scaling can be configured to automatically replace unhealthy nodes. You can also replace unhealthy nodes using the Amazon EC2 auto-recovery28 feature or services such as AWS OpsWorks and AWS Elastic Beanstalk. 	
