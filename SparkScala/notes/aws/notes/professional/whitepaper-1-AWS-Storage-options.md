## AWS Storage Services White paper.

### AWS S3

* You can read, write and delete objects containing from 1 bytes to 5 TB data.
* S3 is highly scalable allowing concurrent reads or write to data by many separate clients or application threads.
* Amazon S3 offers a range of storage classes designed for different use cases
including the following:
  * Amazon S3 Standard, for general-purpose storage of frequently accessed data
  * Amazon S3 Standard-Infrequent Access (Standard-IA), for long-lived, but less
    frequently accessed data
  * Amazon Glacier, for low-cost archival data

##### Usage patterns

There are 4 usage patterns for Amazon S3.

* AWS S3 is used to store and distribute static web content and media
  Elasticity of Amazon S3 makes it particularly well suited for hosting web content that requires bandwidth for addressing extreme demand spikes. Also, because no storage provisioning is required, Amazon S3 works well for fastgrowing websites hosting data-intensive, user-generated content, such as video and
  photo-sharing sites.
* Used to host entire static websites. Highly available and scalable.
* Third, Amazon S3 is used as a data store for computation and large-scale analytics, such as financial transaction analysis, clickstream analytics, and media transcoding. Because of the horizontal scalability of Amazon S3, you can access your data from multiple computing nodes concurrently without being
constrained by a single connection.
* Finally, Amazon S3 is often used as a highly durable, scalable, and secure
solution for backup and archiving of critical data.

##### Anti Patterns (Where not to use S3)
* File system
* Structured data with Query
* Rapidly changing data
* Archival Data
* Dynamic Website Hosting.

##### Performance

* Amazon S3 is built to scale storage, requests, and numbers of users to support an extremely large number of web-scale applications.
* S3 offers multipart upload to upload an object as a set of parts. Thus providing for parallel uploads and better throughput.
* To speed up access to relevant data, many developers pair Amazon S3 with a search engine such as Amazon CloudSearch or a database such as Amazon DynamoDB or Amazon RDS. Where meta-data about the object is stored in the search engine or repository.
* Amazon S3 Transfer Acceleration enables fast, easy, and secure transfer of files over long distances between your client and your Amazon S3 bucket. It leverages Amazon CloudFront globally distributed edge locations to route traffic to your Amazon S3 bucket over an Amazon-optimized network path.
* S3 is built for synchronously storing your data across both multiple devices and multiple facilities within your selected geographical region.
* Error correction is built-in, and there are no single points of failure. Amazon S3 is designed to sustain the concurrent loss of data in two facilities.

##### Security

* Access policies for finegrained control
* Data in transit is protected by using SSL or client side encryption
* Data at rest can be protected by using SSE (Server side encryption), S3 encrypts the data before writing it to disks in data centres.
* You can use versioning to preserve, retrieve, and restore every version of every object stored in your Amazon S3 bucket.
* Access logging to track object requests
* You can Enable MFA for deletes.
* you can use the Amazon S3 notification feature to receive notifications when certain events happen in your bucket. Currently, Amazon S3 can publish events when an object is uploaded or when an object is deleted.

##### Cost Model

With Amazon S3, you pay only for the storage you actually use. There is no minimum fee and no setup cost. Amazon S3 Standard has three pricing components: storage (per GB per month), data transfer in or out (per GB per month), and requests (per thousand requests per month).

There are Data Transfer IN and OUT fees if you enable Amazon S3 Transfer Acceleration on a bucket and the transfer performance is faster than regular Amazon S3 transfer. If we determine that Transfer Acceleration is not likely to be faster than a regular Amazon S3 transfer of the same object to the same destination, we will not charge for that use of Transfer Acceleration for that transfer, and may bypass the Transfer Acceleration system for that upload.

### Amazon Glacier

You store data in Amazon Glacier as archives.
Retrieving archives from Amazon Glacier requires the initiation of a job. You organize your archives in vaults.

##### Usage Patterns

These use cases include archiving offsite enterprise information, media assets, and research and scientific data, and also performing digital preservation and magnetic tape replacement.

##### Anti Patterns

* Rapidly Changing Data
* Immediate Access

##### Performance

* Amazon Glacier retrieval jobs typically complete in 3 to 5 hours.
* You can even perform range retrievals.

##### Durability and Availability

Unlike traditional systems, which can require laborious data verification and manual repair, Amazon Glacier performs regular, systematic data integrity checks and is built to be automatically self-healing.

##### Scalability and Elasticity

A single archive is limited to 40 TB in size. There's no limit to the total amount of data you can store in the service.

##### Security

* Access based on IAM
* Amazon Glacier uses server-side encryption to encrypt all data at rest. Amazon Glacier handles key management and key protection for you by using one of the strongest block ciphers available, 256-bit Advanced Encryption Standard (AES-256).
* Amazon Glacier allows you to lock vaults where long-term records retention is mandated by regulations or compliance rules.
* you might specify controls such as “undeletable records” or “timebased data retention” in a Vault Lock policy and then lock the policy from future edits. After it’s locked, the policy becomes immutable, and Amazon Glacier enforces the prescribed controls to help achieve your compliance objectives.
* Amazon Glacier is integrated with AWS CloudTrail.

### Amazon EFS

* simple, scalable, elastic,highly available, and highly durable network file system as a service to EC2
instances.
* Supports NFS v4 and v4.1
* It is also highly available and highly durable because it stores data and metadata across multiple Availability Zones in a Region.
* You don’t need to provision storage in advance and there is no minimum fee or setup cost—you simply pay for what you use.
* Each file system is accessed by EC2 instances via mount targets, which are created per Availability Zone.
* Traffic flow between Amazon EFS and EC2 instances is controlled using security groups associated with the EC2 instance and the EFS mount targets.
* Access to EFS file system objects (files and directories) is controlled using standard Unix-style read/write/execute permissions based on user and group IDs.

##### Usage Patterns

Amazon EFS is designed to meet the needs of multi-threaded applications and applications that concurrently access data from multiple EC2 instances and that require substantial levels of aggregate throughput and input/output operations per second (IOPS).

Amazon EFS ideal for growing datasets consisting of larger files that need both high performance and
multi-client access.

Amazon EFS supports highly parallelized workloads and is designed to meet the performance needs of big data and analytics, media processing, content management, web serving, and home directories.

##### Anti Patterns

* Archival Data
* Relational Database Storage
* Temporary Storage

##### Performance

Amazon EFS file systems are distributed across an unconstrained number of storage servers, enabling file systems to grow elastically to petabyte-scale and allowing massively parallel access from EC2 instances within a Region.

There are two different performance modes available for Amazon EFS:
* General Purpose and Max I/O.
  General Purpose performance mode is the default mode and is appropriate for most file systems.
  Max I/O performance mode is recommended if Amazon EFS workload will exceed 7,000 file operations per second per file system.

EFS works on baseline and credit throughput system as in EBS. A file system can drive throughput continuously at its baseline rate.It accumulates credits during periods of inactivity or when
throughput is below its baseline rate which are utilized during bursts.

Burst Credit Balance Metric is Available in AWS Cloudwatch.

If your application can handle asynchronous writes to your file system, and you’re able to trade off consistency for speed, enabling asynchronous writes may improve performance. 

##### Durability and Availability

Amazon EFS is designed to be as highly durable and available as Amazon S3.

##### Scalability and Elasticity
Amazon EFS automatically scales your file system storage capacity up or down as you add or remove files without disrupting your applications.

##### Security

There are three levels of access control to consider when planning your EFS file system security: IAM permissions for API calls; security groups for EC2 instances and mount targets; and Network File System-level users, groups, and permissions.

### Amazon EBS

* EBS volumes provide durable block-level storage for use with EC2 instances.
* Amazon EBS volumes are network-attached.
* EBS also provides the ability to create point-in-time snapshots of volumes, which are stored in Amazon S3.
* Sizes for EBS volumes range from 1 GiB to 16 TiB, depending on the volume type.

##### Usage Patterns

* Rapidly Changing Data
* Storage for Database or Filesystem.
* These options are divided into two major categories:
  SSD: for transactional workloads such as database and boot volumes.
  HDD: storage for throughput-intensive workloads such as big data, data warehouse, and log processing

##### Anti Patterns

* Temporary Storage
* Multi Instance Storage
* Highly durable storage
* Static data or web content

##### Performance

You can attach and stripe data across multiple volumes of any type to increase the I/O performance available to your Amazon EC2 applications.

![EBS Performance](wp-1.jpg)

General Purpose SSD (gp2):

  These volumes have a throughput limit range of 128 MiB/second for volumes less than or equal to 170 GiB; for volumes over 170 GiB, this limit increases at the rate of 768 KiB/second per GiB to a maximum of 160 MiB/second(at 214 GiB and larger).

Provisioned IOPS SSD (io1):
  
  You specify an IOPS rate when creating a volume, and then Amazon EBS delivers within 10 percent of the provisioned IOPS performance 99.9 percent of the time over a given year, when attached to an EBS-optimized instance.

  The maximum ration of provisioned IOPS to requested volume size (in GB) is 50:1. 100 GB volume can be provisioned with upto 5000 IOPS.

  The throughput limit of io1 volumes is 256 KiB for each IOPS provisioned, upto a maximum of 320 Mib/s (at 1280 IOPS)

  For best per I/O latency exprience, it is recommended that you provision and IOPS to GiB ratio greater than 2:1

Throughput Optimized HDD (st1):
  
  Ideal for throughput intensive workloads with large datasets and large I/O sizes.
  These volumes deliver performance in terms of throughput, measured in MiB/s, and include the ability to burst up to 250MiB/s per TiB, with a baseline throughput of 40MiB/s per TiB and a maximum throughput of 500MiB/s per volume.

  The st1 volumes are designed to deliver the expected throughput performance 99 percent of the time and has enough I/O credits to support a full-volume scan at the burst rate.     

Cold HDD (st1)

sc1 volumes provide a burst model and can burst up to 80 MiB/s per TiB, with a baseline throughput of 12 MiB/s per TiB and a maximum throughput of 250 MB/s per volume.


EBS-optimized instances deliver dedicated throughput between Amazon EC2 and Amazon EBS, with speeds between 500 Mbps and 10,000 Mbps depending on the instance type.

You can stripe your data across multiple similarly provisioned EBS volumes using RAID 0 (disk
striping) or logical volume manager software, thus aggregating available IOPS, total volume throughput, and total volume size.


##### Durability and Availability

* EBS volume data is replicated across multiple servers in a single Availability Zone to be highly available and reliable.
* EBS snapshots are incremental, point-in-time backups, containing only the data blocks changed since the last snapshot.
* EBS volumes are designed for an annual failure rate (AFR) of between 0.1 and 0.2 percent.
* A snapshot of a 	volume, however, is available across all of the Availability Zones within a Region.

##### Security

* IAM enables access control for your EBS volumes, allowing you to specify who can access which EBS volumes.
* EBS encryption enables data-at-rest and data-in-motion security.

##### Cost Model

* with Amazon EBS you pay only for what you provision, in increments down to 1 GB.
* For Amazon EBS snapshots, you are charged only for storage actually used (consumed).

### Amazon EC2 instance Storage

* Also called as Ephemeral drives.
* provide temporary block-level storage for many EC2 instance types. This storage consists of a preconfigured and pre-attached block of disk storage on the same physical server that hosts the EC2 instance for which the block provides storage.
* instance types, such as the micro instances (t1, t2) and the Compute-optimized c4 instances, use EBS storage only with no instance storage provided.
* AWS offers two EC2 instance families that are purposely built for storage-centric
workloads.
  * Storage Optimized (i2)
  * Dense Storage (d2)

![D2 and I2](wp-2.jpg)

##### Usage Patterns

* High I/O instances (the i2 family) provide instance store volumes backed by SSD and are ideally suited for many high-performance database workloads. Example applications include NoSQL databases like Cassandra and MongoDB, clustered databases, and online transaction processing (OLTP) systems
* High storage instances (the d2 family) support much higher storage density per EC2 instance and are ideally suited for applications that benefit from high sequential I/O performance across very large datasets. Example applications include data warehouses, Hadoop/MapReduce storage nodes, and parallel file systems.
* Unlike EBS volumes, instance store volumes cannot be detached or attached to another instance.
  Note that applications using instance storage for persistent data generally provide data durability through replication, or by periodically copying data to durable storage.

##### Anti Patterns

* Persistent Storage
* Relational Database Storage
* Shared Storage
* Snapshots

##### Performance

Because the EC2 instance virtual machine and the local instance store volumes are located on the same physical server, interaction with this storage is very fast, particularly for sequential access. To increase aggregate IOPS, or to improve sequential disk throughput, multiple instance store volumes can be grouped together using RAID 0 (disk striping) software. Because the bandwidth of the disks is not limited by
the network, aggregate sequential throughput for multiple instance volumes can be higher than for the same number of EBS volumes.

The i2, r3, and hi1 instance types use direct-attached SSD backing that provides maximum performance at launch time without prewarming.

Additionally, r3 and i2 instance store-backed volumes support the TRIM command on Linux instances. For these volumes, you can use TRIM to notify the SSD controller whenever you no longer need data that you've written. This notification lets the controller free space, which can reduce write amplification
and increase performance.

##### Scalability and Elasticity

The number and storage capacity of Amazon EC2 local instance store volumes are fixed and defined by the instance type. Although you can’t increase or decrease the number of instance store volumes on a single EC2 instance, this storage is still scalable and elastic; you can scale the total amount of instance store up or
down by increasing or decreasing the number of running EC2 instances.

##### Security

IAM based access to EC2 instances


### AWS Storage Gateway

##### Usage Patterns

Corporate File Sharing, disaster recovery, primary backup service, mirroring data to cloud based compute resources and then later archiving it to amazon glacier.

##### Performance

* It depends on speed and configuration of your underlying local disks, the network bandwidth between
your iSCSI initiator and gateway VM, the amount of local storage allocated to the
gateway VM, and the bandwidth between the gateway VM and Amazon S3.
AWS Storage Gateway only uploads data that has changed, which minimizes the amount of data sent over the
Internet.

##### Durability and Availability

AWS Storage Gateway durably stores your on-premises application data by uploading it to Amazon S3 or Amazon Glacier.

They also perform regular, systematic data integrity checks and are built to be automatically self-healing.

##### Scalability and Elasticity

In both gateway-cached and gateway-stored volume configurations, AWS Storage Gateway stores data in Amazon S3, which has been designed to offer a very high level of scalability and elasticity automatically.

##### Security

IAM helps you provide security in controlling access to AWS Storage Gateway.

The AWS Storage Gateway encrypts all data in transit to and from AWS by using SSL. All volume and snapshot data stored in AWS using gateway-stored or gateway-cached volumes and all virtual tape data stored in AWS using a gateway-VTL is encrypted at rest using AES-256, a secure symmetric-key encryption standard using 256-bit encryption keys. Storage Gateway supports authentication between your gateway and iSCSI initiators by using Challenge-Handshake Authentication Protocol (CHAP).

### AWS Snowball

##### Usage Pattern:

Snowball is ideal for transferring anywhere from terabytes to many petabytes of data in and out of the AWS Cloud securely. This is especially beneficial in cases where you don’t want to make expensive upgrades to your network infrastructure or in areas where high-speed Internet connections are not available or cost prohibitive. In general, if loading your data over the Internet would take a week or more, you should consider using Snowball.

Common use cases include cloud migration, disaster recovery, data center decommission, and content distribution.

##### Performance

The Snowball appliance is purpose-built for efficient data storage and transfer, including a high-speed, 10 Gbps network connection designed to minimize data transfer times, allowing you to transfer up to 80 TB of data from your data source to the appliance in 2.5 days, plus shipping time.

You can use the Snowball client to estimate the time it takes to transfer your data.

##### Security:

* AWS KMS to protect encryption keys
* Integrates with IAM
* Snowball is physically secured by using an industry- standard Trusted Platform Module (TPM) that uses a dedicated processor designed to detect any unauthorized modifications to the hardware, firmware, or software. 
* Snowball is included in the AWS HIPAA compliance program so you can use Snowball to transfer large amounts of Protected Health Information (PHI) data into and out of AWS.

### Amazon CloudFront

Amazon CloudFront is a content-delivery web service that speeds up the distribution of your website’s dynamic, static, and streaming content by making it available from a global network of edge locations.

##### Durability and Availability

Because a CDN is an edge cache, Amazon CloudFront does not provide durable storage.

Origin requests from the edge locations to AWS origin servers (for example, Amazon EC2, Amazon S3, and so on) are carried over network paths that Amazon constantly monitors and optimizes for both availability and performance. This edge network provides increased reliability and availability because there is no
longer a central point of failure. Copies of your files are now held in edge locations around the world.

##### Scalability and Elasticity

The service automatically responds as demand spikes and fluctuates for your content, without any intervention from you.

##### Security

* It integrates with IAM.
* Amazon CloudFront integrates with Amazon CloudWatch metrics so that you can
monitor your website or application