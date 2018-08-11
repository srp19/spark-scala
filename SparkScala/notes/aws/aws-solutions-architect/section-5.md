## Domain 5 (worth 15% of the exam)

### Optimizing S3

* Optimizing for PUTS
  * Two different types of scenarios
    - Strong internet connection, with fast speeds (such as fibre).
    - Less reliable internet connection with inconsistent network performance.

  * How can we optimise?
    - for strong networks, we want to take advantage of the network itself and make network the bottleneck.
    - For weaker networks, we want to prevent large files having to restart their uploads.

  We can solve for both the scenarios by "Parallelizing for PUTS"

 * Parallelizing for PUTS
   - By dividing your files into small parts and then uploading those parts simultaneously, you are `parallelizing` your puts.
   - If one part fails, it can be restarted and there are few large restarts on an unreliable network.
   - Moves the bottleneck to the network itself, which can help increase the aggregate throughput.
   - 25-50 MB chunks on high bandwidth networks, around 10 MB on mobile networks.
   - Need to find a balance. Too many increases the connection overhead, too few doesn't give you any resiliency.

* Optimising for GETs
  * Use CloudFront:
    - Multiple Endpoints Globally
    - Low Latency
    - High Transfer Speeds Available
    - Caches Objects from S3.

  * CloudFront comes in two varieties:
    - RTMP
    - Web Distribution

* Parallelizing for GETs
  - Need to use `range-based` GETs to get multithreaded performance.
  1- Using the Range HTTP header in a GET request, you can retrieve a specific range of bytes in an object stored in Amazon S3.
  - Allows you to send Multiple GETs at once, hence parallelizing for GETs.
  - Compensates for unreliable network performance.
  - Maximizes bandwidth throughput.

* S3 is Lexicographical (Objects are stored in dictinoary or alphabetical order)
>Users generally store data in S3 bucket with a similiar prefix for a web or gaming application, with the application name or id as the prefix. Objects with same key prefix may be stored in the same disk arrays or on the same disk. AWS advises to introduce some randomness in the key names to spread accross data on different disk arrays and facilities to improve performance. 

### S3 Best Practises

#### Versioning Recap
* Allows you to protect your files from being accidently deleted or overwritten.
 - No Penalty on performance
* Every time you upload a new file, a new version is created automatically.
* Makes it easy to retrieve deleted versions.
* Makes it easy to rollback previous versions.
* Once you turn versioning on, you cannot turn it off - you can only suspend it.

#### Securing S3
* Bucket policies to restrict deletes
* You can use MFA for delete access.
* Requires both your security credentials, as well as code from an approved authentication device (such as Google Authenticator).
* Backup your bucket up to another bucket owned by another account using cross account access.

### Database Design Patterns (Read whitepaper AWS Storage Options)

* Take a note of anti patterns for the different technologies.

#### RDS

##### Multi-AZ vs Read Replica

* Multi-AZ
  * Used for DR
  * Synchronous Replication

* Read Replicas
  * Used for Scaling, not DR
  * Is Asynchronous Replication

##### Joins, Structured Data or ACID? Think RDS.

Atomicity: In a transaction involving two or more dicrete peices of information, either all of the pieces are committed or none are.

Consistency: A transaction either creates a new and a valid state of data, or if any failure occurs, returns all data to its state before the transaction was started.

Isolation: A transaction in process and not yet completed must remain isolated from any other transaction.

Durability: Committed data is saved by the system such that, even in the event of failure and system restart, the data is available in the correct state.

##### Where not to use RDS
* Index and query focussed data - Use Amazon DynamoDB
* BLOBs - Amazon RDS supports BLOBs eg audio,video,images but Amazon S3 is a better choice.
* Automated Scalability - Amazon DynamoDB
* Other Database Platforms such as Informix, Sybase, IBM DB2 - use Amazon EC2 instance.
* You need complete control - OS level control of DB server, with full root or Admin Login privileges.


#### Dynamo DB

##### DynamoDB use cases.
* Applications that need a flexible NoSQL database with low read and write latencies. and the ability to scale storage and throughput up or down as needed without downtime.
* Used for mobile apps, gaming, digital ad serving, live voting and audience interaction for live events, sensor networks, log ingestion, web session management, metadata storage for Amazon S3 Objects, access control for web based content, ecommerce shopping carts.
* Need to automatically scale your database, think DynamoDB.


##### Where not to use DynamoDB

* Should not be used to Store Blob Data.
* Large data with low I/O rate can be stored on S3. Amazon DynamoDB uses SSD Drives and is optimized for workloads with high I/O rate per GB stored.

## !!!!!!! Important

Read whitepaper AWS Storage Options

### Domain 5 Summary 








