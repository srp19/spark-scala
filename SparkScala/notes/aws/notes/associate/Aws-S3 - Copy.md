# AWS S3

## S3 Overview
### S3 is an object based storage, and not block based. You cannot mount a file system or database on S3. You can only store flat files.
### Files can be from 1 byte to 5 TB.
### Files are stored inside buckets. A bucket can be thought of a directory.
### S3 has an universal name space which means a bucket that you create should have a unique name for all the accounts created in S3. This is similar to how domain names work.
### There is unlimited storage. Amazon keeps a buffer for amount of data space required to store data on S3. As the data grows aws add more disk arrays
### S3 has durability of 99.999999999 % and availability of 99.99 %. Which means if you were to put 100 billion files on S3 you are guaranteed to keep 99,999,999,999 of those files
### S3 provides tiered storage option
### S3 provides lifecycle Management
### S3 provides file versioning (Which means if some one accidently deletes files on S3 we can restore them if versioning is switched on)
### S3 allows data encryption
### Secures data using Access Control Lists and Bucker Policies.
### Everytime we upload on object to S3 it is private by default.
## S3 is a key value store
* Key is file name.
* Vale is the data and is made up of sequence of bytes.
* Version Id (Important for versioning)

## S3 Consistency
* Read after write consistency for puts of new objects. Which means we can read a new object (file) as soon as it has been uploaded on S3
* Eventual consistency : This means whenever we update or delete an object (file) it can take some time to propogate which may result in delayed retrival.

## S3 Storage tiers
* S3 Normal tier : durability of 99.999999999 % and availability of 99.99 %, stored redundantly accross multiple devices and multiple facilites and is designed to sustain the loss of 2 facilities concurrently.
* S3 : IA (Infrequently accessed) : durability of 99.999999999 % and availability of 99.9 %. For data that is accessed less frequently, but requires rapid access when needed. Lower than S3 but you are charged a retrieval fee. An example could be an application which need to read data every 1,2,3 months with a very low retrieval time.
* Reduced Redundancy Storage : Designed to provide 99.99% durability and 99.99% availability of objects over a given year. Eg You have a website where you upload photos and those photos are converted to thumbnails and displayed on the websites. These thumbnails can be easily regenerated. RRS is used if we can afford to lose some data.
* Glacier : It is very cheap, but used for archival only. It takes 3-5 hours to restore from glacier. Glacier stores data for as low as $0.01 per Gb per month.

## S3 Lecture 16

### S3 Static hosting
* We can host a static website on S3. It should have only static content and no server side scripts. It is infinitely scalable, amazon handles all the load balancing for us.

### S3 Logging
* Enable logging to track requests to your bucket objects.

### S3 Events
* If some one uploads a file to S3 we can trigger and  event. It can be a SQS or an SNS event or a lambda fuction.
    eg. Lambda might go and process the file or move it to another location or update a database. SNS would to used to trigger an email.

### S3 Versioning 
### Life Cycle Rules
### Cross region replication: If we upload a file we can replicate the file into another region in an another bucket. It works only if versioning is turned on.
### Tags: We can add tags to our bucket which can help in billing.
### Requester Pays: When some body with and aws account tries to access the bucket, they pay for the request which means if requester pays is turned on we cannot have anonymous access on the bukcet.

## S3 Lecture 17 (Version Control Lab)
### Versioning
* Once versioning is turned on it cannot be disabled it can only be suspended
* If versioning is turned on multiple versions would add up to the space used on S3. Eg. If a file has two versions of which one is 300 KB and other is 400 KB, the total space used on S3 would be 700 KB. From a arhitectural and cost perspective this should always be kept in mind. Versioning is always going to increase the cost.
* In versioning all versions of the object are available. (including all writes and even if you delete an object. Even a deleted object can be restored.)
* It integrates with life cycle rules.
* Versioning's MFA delete capability, which uses multi-factor authentication can be used to provide an additional layer of security.
* Versioning is not a very good use case for large files.

### Cross Region replicaton
* Cross region replication requires versioning enabled on both source and destination region buckets. Existing objects will not be replicated, it replicates only future uploads of every object to another bucket.

## S3 Lecture 18 (Life cycle management and Glacier)

### Lifecycle management lab
* Lifecycle management can be used in conjunction with versioning. We can have either versioning turned on or off.
* It can be applied to current and previous versions.
* Following actions can now be done:
 * Transition to the Standard Infrequent Access Storage Class (file size should be more than 128Kb and 30 days after the creation of the object.)
 * Archive to glacier storage class (30 days after IA, so 60 days after the object was created. If not moved to IA, object can be moved to Glavier after 1 day.)
 * Premanently delete.
 * We can't move to RRS tier using life cycle management policies.

## S3 Lecture 19 (CDN)
* Edge Location : This is the location where content will be cached. This is separate to an AWS Region/AZ. There could an edge location without an region. Currently there are 50 AWS edge locations in the world.
* Origin : This is the origin of all files that CDN will distribute. This can be either an S3 bucket, an EC2 instance, an Elactic load balancer or Route 53. Cloud Front also works seemlessly with any non aws origin server which stores the original definite versions of the files.
* Distribution : This is the name given to a CDN which consists of a collection of Edge locations.
* Cloud Front key Terminology:
 * Web Distribution - Typlically used for websites
 * RTMP - Used for Media Streaming, in particular for Adobe Flash
* Edge locations are not just READ only, you can write to them too.(ie put an object on to them). That object will be put back to the actual origin server.
* Objects are cached for life of TTL (Time to Live)
* you can clear cached objects but you will be charged for them.

## S3 Lecture 20 (CloudFront Lab - Creating a CDN)
* We can have multiple origin in the same distribution. And origin id is used to distinguish between multiple origins.
* CDN configuration options:
 * Restrict Viewer Access (Using signed urls or signed cookies): Imagine a scenario where you are a training company. And you have released a whole bunch of training videos which we want to distribute to employees all around the world. We need to basically secure those videos so that only authorized employees can access them and not just everyone on the internet. The way to do that is by restricting viewer access by using signed urls or signed cookies.
 * AWS WAF Web ACL: Yo can use a web application firewall to secure your cloudfront distribution.
 * Alternative Domain Names: User friendly cdn names.
* Default root Object: Should be used if you are using cloud front to host your website.
* Behaviours: Behaviours are basically to use multiple origins and configure path patterns for them. We can specify in path patterns to get all pdf files from bucket A and all jpeg files from bucket b.
* Error Pages: We can create custome error pages when people are trying to access objects and they are getting an error
* Geo Restriction: We can also do a geography based restriction. We can choose which countries to whitelist and which ones to blacklist. CDN will not distribute content in the blacklisted countries.

## S3 Lecture 21 (Security and Encryption)
* We can setup access control to our buckets using the following:
 * Bucket Policies
 * Access control Lists
### Encryption
Types of Encryption for S3 Modes
* In Transit to S3 : When you are uploading file from S3 to your bucket. 
  * It is using SSL/TLS or HTTPS
* At Rest on S3: There are 4 different type of encrypton
  * Server Side Encryption: There are 3 different menthods under server side encryption
    * S3 Managed Keys - *SSE-S3*: This is where each object is encrypted using unique key, employing strong multi factor encryption. And then as a safegaurd Amazon itself encrypts the key with a Master key and they regularly rotate that master key monthly. Amazon will handle all the keys, it uses AES 256 i.e advanced encryption standards 256 bit to encrypt your data.
    * AWS Key Management Service, Managed Keys - *SSE-KMS*: This is very similar to SSE-S3, but it comes with a few additional benefits as well as some additional charges. There are separate permissions for use of an Envelope key. An Envelope key is the one that protects your data encryption key and it basically provides addtional security to objects from unauthorized access. The other main advantage of SSE-KEMS is that it provides you with an order trail of when your keys were used and who actually is using it and gives an additonal level of transperency of who is decrypting what and when.
    * Server Side Encryption with customer provided keys - SSE-C: There is where you manage the encryption keys and Amazon manages the encryption as it writes to disc and decryption when you access your objects. But the actual management of the keys is done by you.
  * Client Side Encryption: This is where you effectively encrypt your data on client side and upload it to S3.
 
## S3 Lecture 22 (Storage Gateway)
* AWS storage gateway is a service that connects your on premise software appliance with cloud based storage to provide seamless and secure integration between an organizations on-premises IT environment and AWS's storage infrastructure. The service enables you to securely store data to the AWS cloud for scalable and cost effective storage.
* Storage gateway is something that sits between an on premise data center and a cloud storage service such as AWS S3 or Glacier and asynchronously backups up your data to either S3 or glacier. It is installed as a VM on any of the hosts in your datacenter and configured to send data to S3.

[Storage Gateway](storage-gateway.png)

* AWS Storage gateway software 	appliance is available for download as a virtual Machine image that you install on your host in your datacenter. Storage Gateway supports either VMware ESXi or Microsoft Hyper-V. Once you have installed your gateway and associated it with your AWS account through the activation process, you can use the AWS management console to create the storage gateway option that is right for you.
* There are three different types of Storage Gateways:
 * Gateway Stored Volumes:
 	This is where we keep our entire data set on site. Storage Gateway then backups this data up asynchronously to Amazon S3. Gwteway stored volumes provide durable and inexpensive off-site backups that you can recover locally or from Amazon EC2.
 * Gateway Cached Volumes:
 	Only your most frequently accessed data is stored locally. Your entire data set is stored to S3. Yo dont have to go out and buy large SAN arrays for your office data center, so you can get significant cost savings. If you lose internet connectivity however, you will not be able to access all of your data.
 * Gateway Virtual Tape Libraries:
 	This is where you can have a limtiless collection of virtual tapes. Each virtual tape can be stored in a Virtual Tape library backed by Amazon S3 or a Virtual Tape Shelf backed by Amazon Glacier.	The Virtual Tape library exposes an industry standard iSCSI interface which provides your backup application with online access to virtual tapes. Supported by NetBackup, Backup Exec, Veam etc.

 Storage Gateway connets either over the internet or by using direct connect. If you are worried about internet or direct connect going down and require access to the whole data set you should use Gateway stored volume where the whole data set is stored locally + backed up on S3. If you do not worry about temporary disconnect from the older data either due internet or direct connect dropping out, you can use Gateway cached Volume as all the data is stored on S3 and only recent data is stored locally and also beacuse you wont have to buy large SAN arrays which would make it more cost effective. Finally if you want to replace your physical tape infrastructure with something that is cost effective and you don't want to worry about where you are storing your tapes eiher in a fire proof safe or an iron mountain you can use AWS Gateway Virtual Tape Libraries.

## S3 Lecture 23 (Import Export)

* Import Export Disc: Basically whenever you want to transfer large amounts of data to AWS storage infrastructure you can choose to skip internet, load your data to a disc and send it to Amazon. Amazon would load the data from disc to S3 and return the disc back to you. Consider a case where you have 100 GB of data and an internet speed of 2 MBPS, it would take anwhere between 50-80 days to transfer the data. Amazon would transfer it within a day and return the disc back.

![Import-export-disc](import-export-disc.png)

* Snowball: Snowball is a petabyte-scale data transfer solution that uses secure appliances to transfer data in and out of aws. Using snowball addresses common problems with large scale data transfers including high network costs, longer transfer times and security concerns. 50 TB per snowball currently. Snowball uses multiple layers of security designed to protect your data including tamper-resistant enclosures, 256 bit encryption, and an industry standard Trusted Platform Modules (TPM) designed to ensure both security and full chain of custody of your data.

![Snowball](snowball.png)

![Snowball Vs import Export](snowball-vs-import.png)

### Exam Tips
* Import/Export Disk
 * Import to S3
 * Import to EBS
 * Import to Glacier
 * Export from S3
* Import/Export Snowball
 * Import to S3
 * Export to S3

## S3 Lecture 24 (S3 Transfer Acceleration)

* S3 Transfer utilization uses cloudfront edge network to accelerate your uploads to S3. Instead of uploading directly to S3 bucket, you can use a distinct url to upload directly to an edge location which will then transfer that file to S3. You will get a distinct url to upload to. Amazon have optimised this over their backbone network to accelerate the transfer process.

To use this go to your S3 bucket and click on properties. At the bottom of it you will see transfer acceleration. Click on enable and you would get an endpoint which you could use to accelerate your uploads to S3.

## S3 Lecture 25 (Storage Summary Lecture)

Revision of All S3 previous lectures. 
