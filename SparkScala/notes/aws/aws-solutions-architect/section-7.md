## Domain 7: Scalability and Elasticity (worth 15% of the exam)
(Compulsary Read Cloudfront FAQ's or Developer guide)

### Cloudfront

Amazon cloudfront can be used to deliver your entire website, including dynamic, static, streaming, and interactive content using global network of edge locations.

* Two Types of Distributions:
  * Web Distributions
  * RMTP Distributions

#### Geo Restriction
  * Geo restriction and Geo blocking lets you choose the countries in which you want to restrict access to your content.
  * Countries that are blacklisted would get a 403 access denied message.
  * You can also create custom error pages.

#### Support For
  * CloudFront supports put,post,patch,delete,options,head and get requests.
  * It doesn't cache the responses to put,post,patch and delete requests - these requests are proxied back to the origin server.

#### SSL
You can use either HTTP or HTTPS with cloudfront. With SSL you can use the default cloudfront URL or your own custom url with your own SSL certificate.
* Dedicated IP custom SSL: Allocates dedicated IP addresses to serve your SSL content at each cloudfront edge location. Very Expensive $600 USD per certificate per month. However will support older browsers
* SNI Custom SSL: Relies on SNI extention of the Transport layer security protocol, which allows multiple domains to serve ssl traffic over the same IP address by including the hostname viewers are trying to connect to. Older browsers won't support it.

#### CNAMEs are supported
* You can have 10 cnames aliases to each distribution.
* Cloudfront also supports wildcard cnames

#### Invalidation Requests

* Delete the file from the origin. The object on the cloudfron will be deleted when it reaches its expiration period defined in the object header.
* You can use invalidation api to remove the object from all cloudfront edge locations incase the object is to be removed before its speficied expiration time.

#### Zone Apex Support

* Using route 53 you can configure an Alias record that lets you map your apex record to the cloudfront distribution.
* Route 53 doesn't charge you for the Alias record queries that are mapped to a cloudfront distribution.

#### Dynamic content support.

* Supports delivery of dynamic content that is customized or personalized using HTTP cookies. To use this feature you can specify whether you want cloudfront to forward some or all of your cookies to your custom origin server. Amazon cloudfront then considers the forwarded cookie values when identifying a unique object in its cache.
* Cookie values can be logged in access logs.

### Memcached vs Redis

#### Use Memcached if
* You want the simplest model possible
* You need to run large nodes with multiple cores or threads
* You need the ability to scale out or scale in as demand on your system increases or decreases.
* You want to shard your data across multiple nodes.
* You need to cache objects such as a database.

#### Use Redis if
* Persisent key value storage.
* If you need automatic Failover such as Multi A-Z required.
* If you require one or more read replicas.
* You need to sort and rank in memory data sets.
* You need complex data type support such as hashes,list,sets and strings.
* You need backup and restore capabilities.
* You want publish and subscribe (pub/sub) capabilities - the client being informed of the events on the server.

### Kinesis Streams

Amazon Kinesis Streams enables you to build custom applications that process or analyze data for specialized needs. You can add any type of data such as click streams, application log data and social media to amazon kinesis streams from hundreds of thousands of sources. Within seconds the data will be available for your amazon kinesis application to read and process from the stream. 

#### Knesis Key Concepts

* Data Producers
* Shards
* Records	
  * Sequence Number
  * Partition Key
  * Data itself (blob)
* Data consumers.

#### Data Producers.

* Amazon Kinesis Streams API
  * Put Record (Single Record)
  * Put Records (Multiple Records)

* Amazon Kinesis Producer Library (KPL)
  * On Github. By using KPL, customers do not need to develop the same logic every time they create a new application for data ingestion.

* Amazon Kinesis Agent
  * Prebuilt Java Application you can install on you Linux devices.

#### What is a Shard?

A shard is simply the unit of measurement of data when refering to Kinesis.

One shard provides a capacity of 1 MB/sec data input and 2 MB/sec data output. One shard can support upto 1000 put records per sec. You will specify the number of shards when you create a stream. For example, you can create a stream with two shards. This stream has a throughput of 4 MB/sec data input and 8 MB/sec data output and allows upto 2000 puts per second. You can dynamically add or remove shards from your stream as your data throughput changes via resharding.

#### What is a partition Key?

Essentially partiton key specifies to which shard the data belongs to.
A partiton key is specified by the application putting data into the stream.

#### What is a sequence number?

Each data record has a unique sequence number. A sequence number is assiged by streams after you write to the stream with client.putRecords or client.putRecord.

#### Blobs

Data blobl is the data that your data producers add to the stream. The maximum size of a datablob after base64 decoding is 1 MB.

- Data is stored for 24 hours by default within streams and can be increased upto 7 days.
- Use S3, Redshift etc to store processed data for longer term. Kinesis streams is not persistent storage.

#### Data Consumers: Data Consumers are the kinesis Stream Applications which consume and process data stored into Kinesis Shards. 

### SNS mobile push.

You send push notification  messages to both mobile device and desktops using one of the following supported push notification services:

* Amazon Device messaging (ADM)
* Apple push notification service (APNS) for both iOS and Mac OS X
* Baidu Cloud Push (Baidu)
* Google Cloud Messaging for Android (GCM)
* Microsoft Push Notification Service for Windows Phone (MPNS)
* Windows push notification Services.(WNS)

#### Steps

* Request credentials from Mobile Platforms
* Request Token from mobile Platforms
* Create Platform Application Object
* Create Platform Endpoint Object
* Publish Message to mobile Endpoint
