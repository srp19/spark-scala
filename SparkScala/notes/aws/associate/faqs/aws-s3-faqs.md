### General

* Q: How much data can I store?
  The total volume of data and number of objects you can store are unlimited. Individual Amazon S3 objects can range in size from a minimum of 0 bytes to a maximum of 5 terabytes. The largest object that can be uploaded in a single PUT is 5 gigabytes. For objects larger than 100 megabytes, customers should consider using the Multipart Upload capability.

* Q: What storage classes does Amazon S3 offer?
  * Amazon S3 Standard for general-purpose storage of frequently accessed data
  * Amazon S3 Standard - Infrequent Access for long-lived
  * Amazon Glacier for long-term archive

  Reduced Redundancy Storage (RRS) is an Amazon S3 storage option that enables customers to reduce their costs by storing noncritical, reproducible data at lower levels of redundancy than Amazon S3’s standard storage.

* Q: How can I delete large numbers of objects?
  You can use Multi-Object Delete to delete large numbers of objects from Amazon S3. This feature allows you to send multiple object keys in a single request to speed up your deletes. Amazon does not charge you for using Multi-Object Delete.

* Q: How is Amazon S3 data organized?
  Amazon S3 is a simple key-based object store. When you store data, you assign a unique object key that can later be used to retrieve the data. Keys can be any string, and can be constructed to mimic hierarchical attributes.

* Q: How do I interface with Amazon S3?
  Amazon S3 provides a simple, standards-based REST web services interface that is designed to work with any Internet-development toolkit. 

* Q: How reliable is Amazon S3?
  S3 Standard is designed for 99.99% availability and Standard - IA is designed for 99.9% availability.

* Q: What data consistency model does Amazon S3 employ?
  Amazon S3 buckets in all Regions provide read-after-write consistency for PUTS of new objects and eventual consistency for overwrite PUTS and DELETES.

* Q: What happens if traffic from my application suddenly spikes?
  Amazon S3 was designed from the ground up to handle traffic for any Internet application. Pay-as-you-go pricing and unlimited capacity ensures that your incremental costs don’t change and that your service is not interrupted. Amazon S3’s massive scale enables us to spread load evenly, so that no individual application is affected by traffic spikes.

* Q: What is the BitTorrent™ protocol, and how do I use it with Amazon S3?
  BitTorrent is an open source Internet distribution protocol. Amazon S3’s bandwidth rates are inexpensive, but BitTorrent allows developers to further save on bandwidth costs for a popular piece of data by letting users download from Amazon and other users simultaneously. Any publicly available data in Amazon S3 can be downloaded via the BitTorrent protocol, in addition to the default client/server delivery mechanism. Simply add the ?torrent parameter at the end of your GET request in the REST API.

* Q: How can I Increase the number of Amazon S3 buckets that I can provision?
  By default, customers can provision up to 100 buckets per AWS account. However, you can increase your Amazon S3 bucket limit by visiting AWS Service Limits.

### Region

* Q: Where is my data stored?
  You specify a region when you create your Amazon S3 bucket. Within that region, your objects are redundantly stored on multiple devices across multiple facilities.

* Q: How do I decide which region to store my data in?
  There are several factors to consider based on your specific application. You may want to store your data in a region that…

    ...is near to your customers, your data centers, or your other AWS resources in order to reduce data access latencies.
    ...is remote from your other operations for geographic redundancy and disaster recovery purposes.
    ...enables you to address specific legal and regulatory requirements.
    ...allows you to reduce storage costs. You can choose a lower priced region to save money. For S3 pricing information, please visit the S3 pricing page.

### Security

* Q: How secure is my data?
  Amazon S3 is secure by default. Only the bucket and object owners originally have access to Amazon S3 resources they create. Amazon S3 supports user authentication to control access to data. You can use access control mechanisms such as bucket policies and Access Control Lists (ACLs) to selectively grant permissions to users and groups of users. You can securely upload/download your data to Amazon S3 via SSL endpoints using the HTTPS protocol. If you need extra security you can use the Server Side Encryption (SSE) option or the Server Side Encryption with Customer-Provided Keys (SSE-C) option to encrypt data stored-at-rest. Amazon S3 provides the encryption technology for both SSE and SSE-C. Alternatively you can use your own encryption libraries to encrypt data before storing it in Amazon S3.

* Q: How can I control access to my data stored on Amazon S3?
  Customers may use four mechanisms for controlling access to Amazon S3 resources: Identity and Access Management (IAM) policies, bucket policies, Access Control Lists (ACLs) and query string authentication. 
  IAM enables organizations with multiple employees to create and manage multiple users under a single AWS account. With IAM policies, companies can grant IAM users fine-grained control to their Amazon S3 bucket or objects while also retaining full control over everything the users do. 
  With bucket policies, companies can define rules which apply broadly across all requests to their Amazon S3 resources, such as granting write privileges to a subset of Amazon S3 resources. Customers can also restrict access based on an aspect of the request, such as HTTP referrer and IP address. With ACLs, customers can grant specific permissions (i.e. READ, WRITE, FULL_CONTROL) to specific users for an individual bucket or object. With query string authentication, customers can create a URL to an Amazon S3 object which is only valid for a limited time. For more information on the various access control policies available in Amazon S3, please refer to the Access Control topic in the Amazon S3 Developer Guide.

* Q: Does Amazon S3 support data access auditing?
Yes, customers can optionally configure Amazon S3 buckets to create access log records for all requests made against it.

* Q: What options do I have for encrypting data stored on Amazon S3?
AWS SSE-S3,SSE-C,SSE-KMS or client library such as Amazon S3 encryption client.

SSE-S3 provides an integrated solution where Amazon handles key management and key protection using multiple layers of security. You should choose SSE-S3 if you prefer to have Amazon manage your keys.

SSE-C enables you to leverage Amazon S3 to perform the encryption and decryption of your objects while retaining control of the keys used to encrypt objects. With SSE-C, you don’t need to implement or use a client-side library to perform the encryption and decryption of objects you store in Amazon S3, but you do need to manage the keys that you send to Amazon S3 to encrypt and decrypt objects. Use SSE-C if you want to maintain your own encryption keys, but don’t want to implement or leverage a client-side encryption library.

SSE-KMS enables you to use AWS Key Management Service (AWS KMS) to manage your encryption keys. Using AWS KMS to manage your keys provides several additional benefits. With AWS KMS, there are separate permissions for the use of the master key, providing an additional layer of control as well as protection against unauthorized access to your objects stored in Amazon S3. AWS KMS provides an audit trail so you can see who used your key to access which object and when, as well as view failed attempts to access data from users without permission to decrypt the data. Also, AWS KMS provides additional security controls to support customer efforts to comply with PCI-DSS, HIPAA/HITECH, and FedRAMP industry requirements.

Using an encryption client library, such as the Amazon S3 Encryption Client, you retain control of the keys and complete the encryption and decryption of objects client-side using an encryption library of your choice. Some customers prefer full end-to-end control of the encryption and decryption of objects; that way, only encrypted objects are transmitted over the Internet to Amazon S3. Use a client-side library if you want to maintain control of your encryption keys, are able to implement or use a client-side encryption library, and need to have your objects encrypted before they are sent to Amazon S3 for storage.

* Q: How does Amazon protect SSE encryption keys?
 With SSE, every protected object is encrypted with a unique key. This object key is itself encrypted by a separate master key. A new master key is issued at least monthly. Encrypted data, encryption keys and master keys are stored and secured on separate hosts for multiple layers of protection.

* Q: What is an Amazon VPC Endpoint for Amazon S3?
  An Amazon VPC Endpoint for Amazon S3 is a logical entity within a VPC that allows connectivity only to S3. The VPC Endpoint routes requests to S3 and routes responses back to the VPC.

* Q: Can I allow a specific Amazon VPC Endpoint access to my Amazon S3 bucket?
  You can limit access to your bucket from a specific Amazon VPC Endpoint or a set of endpoints using Amazon S3 bucket policies. S3 bucket policies now support a condition, aws:sourceVpce, that you can use to restrict access.

* Q: How durable is Amazon S3?
  Amazon S3 Standard and Standard - IA are designed to provide 99.999999999% durability of objects over a given year. This durability level corresponds to an average annual expected loss of 0.000000001% of objects. For example, if you store 10,000 objects with Amazon S3, you can on average expect to incur a loss of a single object once every 10,000,000 years. In addition, Amazon S3 is designed to sustain the concurrent loss of data in two facilities.

* Q: How is Amazon S3 designed to achieve 99.999999999% durability?

  Amazon S3 Standard and Standard - IA redundantly stores your objects on multiple devices across multiple facilities in an Amazon S3 Region. The service is designed to sustain concurrent device failures by quickly detecting and repairing any lost redundancy. When processing a request to store data, the service will redundantly store your object across multiple facilities before returning SUCCESS. Amazon S3 also regularly verifies the integrity of your data using checksums.

* Q: What checksums does Amazon S3 employ to detect data corruption?
  Amazon S3 uses a combination of Content-MD5 checksums and cyclic redundancy checks (CRCs) to detect data corruption. Amazon S3 performs these checksums on data at rest and repairs any corruption using redundant data. In addition, the service calculates checksums on all network traffic to detect corruption of data packets when storing or retrieving data.

* Q: Can I setup a trash, recycle bin, or rollback window on my Amazon S3 objects to recover from deletes and overwrites?
  You can use Lifecycle rules along with Versioning to implement a rollback window for your Amazon S3 objects. For example, with your versioning-enabled bucket, you can set up a rule that archives all of your previous versions to the lower-cost Glacier storage class and deletes them after 100 days, giving you a 100 day window to roll back any changes on your data while lowering your storage costs.

* Q: How can I ensure maximum protection of my preserved versions?
  Versioning’s MFA Delete capability, which uses multi-factor authentication, can be used to provide an additional layer of security. By default, all requests to your Amazon S3 bucket require your AWS account credentials. If you enable Versioning with MFA Delete on your Amazon S3 bucket, two forms of authentication are required to permanently delete a version of an object: your AWS account credentials and a valid six-digit code and serial number from an authentication device in your physical possession. To learn more about enabling Versioning with MFA Delete, including how to purchase and activate an authentication device, please refer to the Amazon S3 Technical Documentation.

### S3 Standard - Infrequent Access

* Q: What is S3 Standard - Infrequent Access?
  Amazon S3 Standard - Infrequent Access (Standard - IA) is an Amazon S3 storage class for data that is accessed less frequently, but requires rapid access when needed. Standard - IA offers the high durability, throughput, and low latency of Amazon S3 Standard, with a low per GB storage price and per GB retrieval fee. This combination of low cost and high performance make Standard - IA ideal for long-term storage, backups, and as a data store for disaster recovery. The Standard - IA storage class is set at the object level and can exist in the same bucket as Standard, allowing you to use lifecycle policies to automatically transition objects between storage classes without any application changes.

* Q: Why would I choose to use Standard - IA?
  Standard - IA is ideal for data that is accessed less frequently, but requires rapid access when needed. Standard - IA is ideally suited for long-term file storage, older data from sync and share, backup data, and disaster recovery files.

* Q: What performance does S3 Standard - Infrequent Access offer?
  S3 Standard - Infrequent Access provide the same performance as S3 Standard storage.

* Q: How durable is Standard - IA?
  S3 Standard - IA is designed for the same 99.999999999% durability as Standard and Amazon Glacier. Standard - IA is designed for 99.9% availability, and carries a service level agreement providing service credits if availability is less than our service commitment in any billing cycle.

* Q: How available is Standard - IA?
  Designed for 99.9% availability, Standard - IA has a thinner front end that provides nine one-hundredths of a percent less availability than S3 Standard. Standard - IA carries a service level agreement providing service credits if availability is less than our service commitment in any billing cycle.

* Q: How do I get my data into Standard - IA?
  There are two ways to get data into Standard – IA from within S3. You can directly PUT into Standard – IA by specifying STANDARD_IA in the x-amz-storage-class header. You can also set lifecycle policies to transition objects from Standard to Standard - IA.

* Q: Are my Standard - IA objects backed with the Amazon S3 Service Level Agreement?
  Yes, Standard - IA is backed with the Amazon S3 Service Level Agreement, and customers are eligible for service credits if availability is less than our service commitment in any billing cycle.

* Q: How will my latency and throughput performance be impacted as a result of using Standard - IA?
  You should expect the same latency and throughput performance as Amazon S3 Standard when using Standard - IA.

* Q: How am I charged for using Standard - IA?
  Please see the Amazon S3 pricing page for general information about Standard - IA pricing.

* Q. What charges will I incur if I change storage class of an object from Standard-IA to Standard with a copy request?
  You will incur charges for an Standard-IA copy request and a Standard-IA data retrieval.

* Q: Is there a minimum duration for Standard - IA?
  Standard - IA is designed for long-lived, but infrequently accessed data that is retained for months or years. Data that is deleted from Standard - IA within 30 days will be charged for a full 30 days. Please see the Amazon S3 pricing page for information about Standard - IA pricing.

* Q: Is there a minimum object size for Standard - IA?
  Standard - IA is designed for larger objects and has a minimum object size of 128KB. Objects smaller than 128KB in size will incur storage charges as if the object were 128KB. For example, a 6KB object in S3 Standard - IA will incur S3 Standard - IA storage charges for 6KB and an additional minimum object size fee equivalent to 122KB at the S3 Standard - IA storage price. Please see the Amazon S3 pricing page for information about Standard - IA pricing.

* Q: Can I tier objects from Standard - IA to Amazon Glacier?
  Yes. In addition to using lifecycle policies to migrate objects from Standard to Standard - IA, you can also set up lifecycle policies to tier objects from Standard - IA to Amazon Glacier.

* Amazon Glacier

* Q: How can I store my data using the Amazon Glacier option?
  You can use Lifecycle rules to automatically archive sets of Amazon S3 objects to Amazon Glacier based on lifetime. Use the Amazon S3 Management Console, the AWS SDKs or the Amazon S3 APIs to define rules for archival. Rules specify a prefix and time period. The prefix (e.g. “logs/”) identifies the object(s) subject to the rule. The time period specifies either the number of days from object creation date (e.g. 180 days) or the specified date after which the object(s) should be archived. Any Amazon S3 Standard or Amazon S3 Standard - IA objects which have names beginning with the specified prefix and which have aged past the specified time period are archived to Amazon Glacier. To retrieve Amazon S3 data stored in Amazon Glacier, initiate a retrieval job via the Amazon S3 APIs or Management Console. Once the job is complete, you can access your data through an Amazon S3 GET object request.

* Q: How can I retrieve my objects that are archived in Amazon Glacier?
  To retrieve Amazon S3 data stored in Amazon Glacier, initiate a retrieval request using the Amazon S3 APIs or the Amazon S3 Management Console. The retrieval request creates a temporary copy of your data in RRS while leaving the archived data intact in Amazon Glacier. You can specify the amount of time in days for which the temporary copy is stored in RRS. You can then access your temporary copy from RRS through an Amazon S3 GET request on the archived object.

* Q: How long will it take to retrieve my objects archived in Amazon Glacier?
  When processing a retrieval job, Amazon S3 first retrieves the requested data from Amazon Glacier, and then creates a temporary copy of the requested data in RRS (which typically takes on the order of a few minutes). The access time of your request depends on the retrieval option you choose: Expedited, Standard, or Bulk retrievals. For all but the largest objects (250MB+), data accessed using Expedited retrievals are typically made available within 1 – 5 minutes. Objects retrieved using Standard retrievals typically complete between 3 – 5 hours. Lastly, Bulk retrievals typically complete within 5 – 12 hours. 

* Q: How much data can I retrieve for free?
  You can retrieve 10 GB of your Amazon Glacier data per month for free. The free tier allowance can be used at any time during the month and applies to Standard retrievals

* Q: How am I charged for deleting objects from Amazon Glacier that are less than 3 months old?
  Amazon Glacier is designed for use cases where data is retained for months, years, or decades. Deleting data that is archived to Amazon Glacier is free if the objects being deleted have been archived in Amazon Glacier for three months or longer. If an object archived in Amazon Glacier is deleted or overwritten within three months of being archived then there will be an early deletion fee. This fee is prorated. If you delete 1GB of data 1 month after uploading it, you will be charged an early deletion fee for 2 months of Amazon Glacier storage. If you delete 1 GB after 2 months, you will be charged for 1 month of Amazon Glacier storage.

### Event Notification

* Q: What are Amazon S3 event notifications?
  Amazon S3 event notifications can be sent in response to actions in Amazon S3 like PUTs, POSTs, COPYs, or DELETEs. Notification messages can be sent through either Amazon SNS, Amazon SQS, or directly to AWS Lambda.

### Storage Management

#### S3 Object Tagging

* Q. What are S3 Object Tags?
	S3 Object Tags are key-value pairs applied to S3 objects which can be created, updated or deleted at any time during the lifetime of the object. With these, you’ll have the ability to create Identity and Access Management (IAM) policies, setup S3 Lifecycle policies, and customize storage metrics. These object-level tags can then manage transitions between storage classes and expire objects in the background.

* Q. How do I apply Object Tags to my objects?
	You can add tags to new objects when you upload them or you can add them to existing objects. Up to ten tags can be added to each S3 object and you can use either the AWS Management Console, the REST API, the AWS CLI, or the AWS SDKs to add object tags.

* Q. Why should I use Object Tags?
	Object Tags are a new tool you can use to enable simple management of your S3 storage. With the ability to create, update, and delete tags at any time during the lifetime of your object, your storage can adapt to the needs of your business. These tags allow you to control access to objects tagged with specific key-value pairs, allowing you to further secure confidential data for only a select group or user. Object tags can also be used to label objects that belong to a specific project or business unit, which could be used in conjunction with lifecycle policies to manage transitions to the S3 Standard – Infrequent Access and Glacier storage tiers.

* Q. How can I update the Object Tags on my objects?
	Object Tags can be changed at any time during the lifetime of your S3 object, you can use either the AWS Management Console, the REST API, the AWS CLI, or the AWS SDKs to change your object tags. Note that all changes to tags outside of the AWS Management Console are made to the full tag set. If you have five tags attached to a particular object and want to add a sixth, you need to include the original five tags in that request.

* Q. Will my Object Tags be replicated if I use Cross-Region Replication?
	Object Tags can be replicated across regions using Cross-Region Replication. For more information about setting up Cross-Region Replication, please visit How to Set Up Cross-Region Replication in the Amazon S3 Developer Guide.
	For customers with Cross-Region Replication already enabled, new permissions are required in order for tags to replicate. For more information on the policies required, please visit How to Set Up Cross-Region Replication in the Amazon S3 Developer Guide.

* Q. How much do Object Tags cost?
	Object Tags are priced at $0.01 per 10,000 tags per month. The requests associated with adding and updating Object Tags are priced the same as existing request prices, please see the Amazon S3 pricing page for more information.

#### S3 Analytics - Storage Class Analysis

* Q. What is S3 Analytics – Storage Class Analysis?
	With storage class analysis, you can analyze storage access patterns and transition the right data to the right storage class. This new S3 Analytics feature automatically identifies infrequent access patterns to help you transition storage to Standard-IA. You can configure a storage class analysis policy to monitor an entire bucket, a prefix, or object tag. Once an infrequent access pattern is observed, you can easily create a new lifecycle age policy based on the results. Storage class analysis also provides daily visualizations of your storage usage on the AWS Management Console that you can export to a S3 bucket to analyze using business intelligence tools of your choice such as Amazon QuickSight.

* Q. How do I get started with S3 Analytics – Storage Class Analysis?
	You can use the AWS Management Console or the S3 PUT Bucket Analytics API to configure a Storage Class Analysis policy to identify infrequently accessed storage that can be transitioned to Standard-IA or archived to Glacier. You can navigate to the “Management” tab in the S3 Console to manage S3 Analytics, S3 Inventory, and S3 CloudWatch metrics.

* Q. How often is the Storage Class Analysis updated?
	Storage Class Analysis is updated on a daily basis on the S3 Management Console. Additionally, you can configure S3 Analytics to export your daily storage class analysis to a S3 bucket of your choice.

#### S3 Inventory

* Q. What is S3 Inventory?
	You can simplify and speed up business workflows and big data jobs using S3 Inventory which provides a scheduled alternative to Amazon S3’s synchronous List API. S3 Inventory provides a CSV (Comma Separated Values) flat-file output of your objects and their corresponding metadata on a daily or weekly basis for an S3 bucket or a shared prefix.

* Q. Can S3 Inventory improve the performance for my big data jobs and business workflow applications?
	Yes, S3 Inventory can be used as a ready-made input into a big data job or workflow application instead of the synchronous S3 LIST API, saving the time and compute resources it takes to call and process the LIST API response.

#### S3 CloudWatch Metrics

* Q. How do I get started with S3 CloudWatch Metrics?
	You can use the AWS Management Console to enable the generation of 1-minute CloudWatch metrics for your S3 bucket or configure filters for the metrics using a prefix or object tag. Alternately, you can call the S3 PUT Bucket Metrics API to enable and configure publication of S3 storage metrics. Storage metrics will be available in CloudWatch within 15 minutes of being enabled.

* Q. What alarms can I set on my storage metrics?
	You can use CloudWatch to set thresholds on any of the storage metrics counts, timers, or rates and fire an action when the threshold is breached. For example, you can set a threshold on the percentage of 4xx Error Responses and when at least 3 data points are above the threshold fire a CloudWatch alarm to alert a Dev Ops engineer.

* Q. How am I charged for using S3 CloudWatch Metrics?
	S3 CloudWatch Metrics are priced as custom metrics for Amazon CloudWatch.

#### Lifecycle Management Policies

* Q. What is Lifecycle Management?
	S3 Lifecycle management provides the ability to define the lifecycle of your object with a predefined policy and reduce your cost of storage. You can set lifecycle transition policy to automatically migrate Amazon S3 objects to Standard - Infrequent Access (Standard - IA) and/or Amazon Glacier based on the age of the data. You can also set lifecycle expiration policies to automatically remove objects based on the age of the object. You can set a policy for multipart upload expiration, which expires incomplete multipart upload based on the age of the upload.

* Q. What can I do with Lifecycle Management Policies?
	As data matures, it can become less critical, less valuable and/or subject to compliance requirements. Amazon S3 includes an extensive library of policies that help you automate data migration processes. For example, you can set infrequently accessed objects to move into lower cost storage tier (like Standard-Infrequent Access) after a period of time. After another period, it can be moved into Amazon Glacier for archive and compliance, and eventually deleted. These rules can invisibly lower storage costs and simplify management efforts, and may be leveraged across the Amazon family of storage services. These policies also include good stewardship practices to remove objects and attributes that are no longer needed to manage cost and optimize performance.

* Q: How can I use Amazon S3’s lifecycle policy to help lower my Amazon S3 storage costs?
	Lifecycle policies apply to both existing and new S3 objects, helping you optimize storage and maximize cost savings for all current data and any new data placed in S3 without time-consuming manual data review and migration. Within a lifecycle rule, the prefix field identifies the objects subject to the rule.
	After an Object Expiration rule is added, the rule is applied to objects that already exist in the bucket as well as new objects added to the bucket. Once objects are past their expiration date, they are identified and queued for removal. 

* Cross-Region Replication
* Q: How do I enable CRR?
	CRR is a bucket-level configuration. You enable a CRR configuration on your source bucket by specifying a destination bucket in a different region for replication. You can use either the AWS Management Console, the REST API, the AWS CLI, or the AWS SDKs to enable CRR.
* Q: Can I use CRR with lifecycle rules?
	Yes, you can configure separate lifecycle rules on the source and destination buckets. For example, you can configure a lifecycle rule to migrate data from Standard to Standard - IA on the destination bucket or configure a lifecycle rule to archive data into Amazon Glacier.

* Amazon S3 Transfer Acceleration
* Q. What is Transfer Acceleration?
	Amazon S3 Transfer Acceleration enables fast, easy, and secure transfers of files over long distances between your client and your Amazon S3 bucket. Transfer Acceleration leverages Amazon CloudFront’s globally distributed AWS Edge Locations. As data arrives at an AWS Edge Location, data is routed to your Amazon S3 bucket over an optimized network path.

* Q. How do I get started with Transfer Acceleration?
	It's easy to get started with Transfer Acceleration. First, enable Transfer Acceleration on an S3 bucket using the Amazon S3 console, the Amazon S3 API, or the AWS CLI. After Transfer Acceleration is enabled, you can point your Amazon S3 PUT and GET requests to the s3-accelerate endpoint domain name. Your data transfer application must use one of the following two types of endpoints to access the bucket for faster data transfer: <bucketname>.s3-accelerate.amazonaws.com or <bucketname>.s3-accelerate.dualstack.amazonaws.com for the “dual-stack” endpoint. If you want to use standard data transfer, you can continue to use the regular endpoints.

* Q. How fast is Transfer Acceleration?
	Transfer Acceleration helps you fully utilize your bandwidth, minimize the effect of distance on throughput, and is designed to ensure consistently fast data transfer to Amazon S3 regardless of your client’s location. Acceleration primarily depends on your available bandwidth, the distance between the source and destination, and packet loss rates on the network path. Generally, you will see more acceleration when the source is farther from the destination, when there is more available bandwidth, and/or when the object size is bigger.
	One customer measured a 50% reduction in their average time to ingest 300 MB files from a global user base spread across the US, Europe, and parts of Asia to a bucket in the Asia Pacific (Sydney) region. Another customer observed cases where performance improved in excess of 500% for users in South East Asia and Australia uploading 250 MB files (in parts of 50MB) to an S3 bucket in the US East (N. Virginia) region.

* Q. Who should use Transfer Acceleration?
	Transfer Acceleration is designed to optimize transfer speeds from across the world into S3 buckets. If you are uploading to a centralized bucket from geographically dispersed locations, or if you regularly transfer GBs or TBs of data across continents, you may save hours or days of data transfer time.

* Q. How should I choose between Transfer Acceleration and Amazon CloudFront’s PUT/POST?
	Transfer Acceleration optimizes the TCP protocol and adds additional intelligence between the client and the S3 bucket, making Transfer Acceleration a better choice if a higher throughput is desired. If you have objects that are smaller than 1GB or if the data set is less than 1GB in size, you should consider using Amazon CloudFront's PUT/POST commands for optimal performance.

* Q. How should I choose between Transfer Acceleration and AWS Snowball?
	The AWS Import/Export Snowball is ideal for customers moving large batches of data at once. The AWS Snowball has a typical 5-7 days turnaround time. As a rule of thumb, Transfer Acceleration over a fully-utilized 1 Gbps line can transfer up to 75 TBs in the same time. In general, if it will take more than a week to transfer over the Internet, or there are recurring transfer jobs and there is more than 25Mbps of available bandwidth, Transfer Acceleration is a good option. Another option is to use both: perform initial heavy lift moves with an AWS Snowball (or series of AWS Snowballs) and then transfer incremental ongoing changes with Transfer Acceleration.

* Q. Can Transfer Acceleration complement AWS Direct Connect?
	AWS Direct Connect is a good choice for customers with a private networking requirement or have access to AWS Direct Connect exchanges. Transfer Acceleration is best for submitting data from distributed client locations over the public Internet, or where variable network conditions make throughput poor. Some AWS Direct Connect customers use Transfer Acceleration to help with remote office transfers, where they may suffer from poor Internet performance.

### Amazon S3 and IPV6
* Q. How do I get started with IPv6 on Amazon S3?
	You can get started by pointing your application to Amazon S3’s new “dual-stack” endpoint, which supports access over both IPv4 and IPv6. 

* Q. If I point to Amazon S3's "dual-stack" endpoint, will I still be able to access Amazon S3's APIs over IPv4?
	Yes, you can continue to access Amazon S3 APIs using both IPv6 and IPv4 addresses when connecting to the Amazon S3 “dual-stack” endpoints. You will need to configure your client to prefer IPv4 addresses, which can be an application-level or host-level configuration option for many application runtime languages. Please consult the documentation for the language you are using for your runtime platform for the specific configuration option that prefers IPv4 connections.

* Q. Will existing VPC Endpoints continue to work if I point to Amazon S3's "dual-stack" endpoint?
	Yes, you can continue using VPC Endpoint to access Amazon S3 over IPv4. If you use the dual-stack endpoint in an IPv4-only VPC, the VPC instances will drop the AAAA record and always access Amazon S3 over IPv4.
