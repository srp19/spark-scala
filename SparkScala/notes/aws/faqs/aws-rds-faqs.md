### General

Q: How many DB instances can I run with Amazon RDS?By default, customers are allowed to have up to a total of 40 Amazon RDS DB instances. Of those 40, up to 10 can be Oracle or SQL Server DB instances under the "License Included" model. All 40 can be used for Amazon Aurora, MySQL, MariaDB, PostgreSQL and Oracle or SQL Server under the "BYOL" model. If your application requires more DB instances, you can request additional DB instances via this request form.

Q: How many databases or schemas can I run within a DB instance?
-
  RDS for Amazon Aurora: No limit imposed by software
  RDS for MySQL: No limit imposed by software
  RDS for MariaDB: No limit imposed by software
  RDS for Oracle: 1 database per instance; no limit on number of schemas per database imposed by software
  RDS for SQL Server: 30 databases per instance
  RDS for PostgreSQL: No limit imposed by software

Q: What is a maintenance window? Will my DB instance be available during maintenance events?
-	Maintenance windows are 30 minutes in duration.
	
	The only maintenance events that require Amazon RDS to take your DB instance offline are scale compute operations (which generally take only a few minutes from start-to-finish) or required software patching. Required patching is automatically scheduled only for patches that are security and durability related. Such patching occurs infrequently (typically once every few months) and should seldom require more than a fraction of your maintenance window.

	Running your DB instance as a Multi-AZ deployment can further reduce the impact of a maintenance event.

Q: What should I do if my queries seem to be running slow?
-	For production databases we encourage you to enable Enhanced Monitoring, which provides access to over 50 CPU, memory, file system, and disk I/O metrics. You can enable these features on a per-instance basis and you can choose the granularity (all the way down to 1 second). High levels of CPU utilization can reduce query performance and in this case you may want to consider scaling your DB instance class. For more information on monitoring your DB instance, refer to the Amazon RDS User Guide.

	If you are using RDS for MySQL or MariaDB, you can access the slow query logs for your database to determine if there are slow-running SQL queries and, if so, the performance characteristics of each. You could set the "slow_query_log" DB Parameter and query the mysql.slow_log table to review the slow-running SQL queries. Please refer to the Amazon RDS User Guide to learn more.

	If you are using RDS for Oracle, you can use the Oracle trace file data to identify slow queries. For more information on accessing trace file data, please refer to Amazon RDS User Guide.

	If you are using RDS for SQL Server, you can use the client side SQL Server traces to identify slow queries.


### Database Engine Versions

Q: Which relational database engine versions does Amazon RDS support?
-  Amazon RDS for MySQL currently supports MySQL Community Edition 5.5, 5.6 and 5.7.
  Amazon RDS for MariaDB currently supports MariaDB Server 10.0 and 10.1.
  Amazon RDS for PostgreSQL currently supports PostgreSQL 9.3, 9.4, 9.5, and 9.6.
  Amazon RDS for Oracle currently supports Oracle Database 11gR2 and 12c.
  Amazon RDS for SQL Server currently supports Microsoft SQL Server 2008 R2,  2012, 2014 and 2016.

Q: Does Amazon RDS provide guidelines for support of new DB engine versions?	
  However, as a general guidance, we aim to support new engine versions within 5 months of their general availability.


Q: How do I control if and when the engine version of my DB instance is upgraded to new supported versions?
-  Amazon RDS strives to keep your database instance up to date by providing you newer versions of the supported database engines. After a new version of a database engine is released by the vendor or development organization, it is thoroughly tested by our database engineering team before it is made available in Amazon RDS.
   We recommend that you keep your database instance upgraded to the most current minor version as it will contain the latest security and functionality fixes. Unlike major version upgrades, minor version upgrades only include database changes that are backward-compatible with previous minor versions (of the same major version) of the database engine.
  If a new minor version does not contain fixes that would benefit RDS customers, we may choose not to make it available in RDS. Soon after a new minor version is available in RDS, we will set it to be the preferred minor version for new DB instances.
  To manually upgrade a database instance to a supported engine version, use the Modify DB Instance command on the AWS Management Console or the ModifyDBInstance API and set the DB Engine Version parameter to the desired version. By default, the upgrade will be applied or during your next maintenance window. You can also choose to upgrade immediately by selecting the Apply Immediately option in the console API.
  If we determine that a new engine minor version contains significant bug fixes compared to a previously released minor version, we will schedule automatic upgrades for DB instances which have the Auto Minor Version Upgrade setting to “Yes”. These upgrades will be scheduled to occur during customer-specified maintenance windows.
  We will announce scheduled upgrades on the Amazon RDS Forum and send customer e-mail notifications at least 30 days in advance. We schedule them so you can plan around them, because downtime is required to upgrade a DB engine version, even for Multi-AZ instances. If you wish to turn off automatic minor version upgrades, you can do so by setting the Auto Minor Version Upgrade setting to “No”.
  In the case of RDS for Oracle and RDS for SQL Server, if the upgrade to the next minor version requires a change to a different edition, then we may not schedule automatic upgrades even if you have enabled the Auto Minor Version Upgrade setting. The determination on whether to schedule automatic upgrades in such situations will be made on a case-by-case basis.
  Since major version upgrades involve some compatibility risk, they will not occur automatically and must be initiated by you (except in the case of major version deprecation, see below).

Q: Does Amazon RDS provide guidelines for deprecating database engine versions that are currently supported?
-  We intend to support major version releases (e.g., MySQL 5.6, PostgreSQL 9.6) for at least 3 years after they are initially supported by Amazon RDS.
  We intend to support minor versions (e.g., MySQL 5.6.21, PostgreSQL 9.6.1) for at least 1 year after they are initially supported by Amazon RDS.

Q: What happens when an RDS DB engine version is deprecated?
-  When a minor version of a database engine is deprecated in Amazon RDS, we schedule automatic upgrades for instances with the Auto Minor Version Upgrade setting to occur at least 30 days after announcing the deprecation on the forum and sending e-mail notifications to customers. We will also disable the creation of new instances for this version. After a minimum three month grace period after announcement, all instances still running the deprecated minor version will be scheduled for automatic upgrade to a supported minor version during the specified maintenance window.
  When a major version of database engine is deprecated in Amazon RDS, we will provide a minimum six month grace period after the announcement of a deprecation for you to initiate an upgrade to a supported major version. At the end of this grace period, an automatic upgrade will be applied to any instances still running the deprecated version during their scheduled maintenance windows.
  Once a major or minor database engine version is no longer supported in Amazon RDS, any DB instance restored from a DB snapshot created with the unsupported version will automatically and immediately be upgraded to a currently supported version.

Q: How will I be charged and billed for my use of Amazon RDS?
-   You pay only for what you use, and there are no minimum or setup fees. You are billed based on:

  DB instance hours – Based on the class (e.g. db.t2.micro, db.m4.large) of the DB instance consumed. Partial DB instance hours consumed are billed as full hours.
  Storage (per GB per month) – Storage capacity you have provisioned to your DB instance. If you scale your provisioned storage capacity within the month, your bill will be pro-rated.
  I/O requests per month – Total number of storage I/O requests you have (for Amazon RDS Magnetic Storage only)
  Provisioned IOPS per month – Provisioned IOPS rate, regardless of IOPS consumed (for Amazon RDS Provisioned IOPS (SSD) Storage only)
  Backup Storage – Backup storage is the storage associated with your automated database backups and any active database snapshots you have taken. Increasing your backup retention period or taking additional database snapshots increases the backup storage consumed by your database. Amazon RDS provides backup storage up to 100% of your provisioned database storage at no additional charge. For example, if you have 10GB-months of provisioned database storage, we will provide up to 10GB-months of backup storage at no additional charge. Based upon our experience as database administrators, the vast majority of databases require less raw storage for a backup than for the primary data set, meaning that most customers will never pay for backup storage. Backup storage is only free for active DB instances.
  Data transfer – Internet data transfer in and out of your DB instance.

Q: Why does additional backup storage cost more than allocated DB instance storage?
-   The storage provisioned to your DB instance for your primary data is located within a single Availability Zone. When your database is backed up, the backup data (including transactions logs) is geo-redundantly replicated across multiple Availability Zones to provide even greater levels of data durability. The price for backup storage beyond your free allocation reflects this extra replication that occurs to maximize the durability of your critical backups.

### Reserved Instances

Q: Will there always be reservations available for purchase?
-   Yes. reserved instances are purchased for the Region rather than for the Availability Zone. This means that even if capacity is limited in one Availability Zone, reservations can still be purchased in that Region and used in a different Availability Zone within that Region.

Q: How many reserved instances can I purchase?
-   You can purchase up to 40 reserved DB instances. If you wish to run more than 40 DB instances, please complete the Amazon RDS DB Instance request form.

Q: Can I cancel a reservation?
-   No, you cannot cancel your reserved DB instance and the one-time payment (if applicable) is not refundable. You will continue to pay for every hour during your Reserved DB instance term regardless of your usage.

Q: What is the hardware configuration for Amazon RDS storage?
-   Amazon RDS uses EBS volumes for database and log storage. Depending on the size of storage requested, Amazon RDS automatically stripes across multiple EBS volumes to enhance IOPS performance. For MySQL and Oracle, for an existing DB instance, you may observe some I/O capacity improvement if you scale up your storage. You can scale the storage capacity allocated to your DB Instance using the AWS Management Console, the ModifyDBInstance API, or the modify-db-instance command.
   However, for SQL Server, because of the extensibility limitations of striped storage attached to a Windows Server environment, Amazon RDS does not currently support increasing storage.

Q: Will my DB instance remain available during scaling?
-   The storage capacity allocated to your DB Instance can be increased while maintaining DB Instance availability. However, when you decide to scale the compute resources available to your DB instance up or down, your database will be temporarily unavailable while the DB instance class is modified. This period of unavailability typically lasts only a few minutes, and will occur during the maintenance window for your DB Instance, unless you specify that the modification should be applied immediately.

Q: How can I scale my DB instance beyond the largest DB instance class and maximum storage capacity?
-   Amazon RDS supports a variety of DB instance classes and storage allocations to meet different application needs. If your application requires more compute resources than the largest DB instance class or more storage than the maximum allocation, you can implement partitioning, thereby spreading your data across multiple DB instances.

Q: What is Amazon RDS Provisioned IOPS (SSD) storage?
-   Amazon RDS Provisioned IOPS (SSD) Storage is an SSD-backed storage option designed to deliver fast, predictable, and consistent I/O performance. With Amazon RDS Provisioned IOPS (SSD) Storage, you specify an IOPS rate when creating a DB instance, and Amazon RDS provisions that IOPS rate for the lifetime of the DB instance. Amazon RDS Provisioned IOPS (SSD) Storage is optimized for I/O-intensive, transactional (OLTP) database workloads. For more details, please see the Amazon RDS User Guide.

Q: What is Amazon RDS magnetic storage?
-   Formerly known as Standard storage, Amazon RDS magnetic storage is useful for small database workloads where data is accessed less frequently.

Q: How do I choose among the Amazon RDS storage types?
- Choose the storage type most suited for your workload.
 High-performance OLTP workloads: Amazon RDS Provisioned IOPS (SSD) Storage
 Database workloads with moderate I/O requirements: Amazon RDS General Purpose (SSD) Storage
 Small database workloads with infrequent I/O: Amazon RDS Magnetic Storage

Q: Do I need to enable backups for my DB Instance or is it done automatically?
-  By default and at no additional charge, Amazon RDS enables automated backups of your DB Instance with a 1 day retention period. Free backup storage is limited to the size of your provisioned database and only applies to active DB Instances. For example, if you have 10GB-months of provisioned database storage, we will provide at most 10GB-months of backup storage at no additional charge. If you would like to extend your backup retention period beyond one day, you can do so using the CreateDBInstance API (when creating a new DB Instance) or ModifyDBInstance API (for an existing DB Instance). You can use these APIs to change the RetentionPeriod parameter from 1 to the desired number of days. For more information on automated backups, please refer to the Amazon RDS User Guide.

Q: What is a backup window and why do I need it? Is my database available during the backup window?
-	The preferred backup window is the user-defined period of time during which your DB Instance is backed up. Amazon RDS uses these periodic data backups in conjunction with your transaction logs to enable you to restore your DB Instance to any second during your retention period, up to the LatestRestorableTime (typically up to the last few minutes). During the backup window, storage I/O may be briefly suspended while the backup process initializes (typically under a few seconds) and you may experience a brief period of elevated latency. There is no I/O suspension for Multi-AZ DB deployments, since the backup is taken from the standby.

Q: What happens to my backups and DB Snapshots if I delete my DB Instance?
-  When you delete a DB Instance, you can create a final DB Snapshot upon deletion; if you do, you can use this DB Snapshot to restore the deleted DB Instance at a later date. Amazon RDS retains this final user-created DB Snapshot along with all other manually created DB Snapshots after the DB Instance is deleted. Refer to the pricing page for details of backup storage costs.
  Automated backups are deleted when the DB Instance is deleted. Only manually created DB Snapshots are retained after the DB Instance is deleted.

Q: What is a DB Subnet Group and why do I need one?
-  A DB Subnet Group is a collection of subnets that you may want to designate for your RDS DB Instances in a VPC. Each DB Subnet Group should have at least one subnet for every Availability Zone in a given Region. When creating a DB Instance in VPC, you will need to select a DB Subnet Group. Amazon RDS then uses that DB Subnet Group and your preferred Availability Zone to select a subnet and an IP address within that subnet. Amazon RDS creates and associates an Elastic Network Interface to your DB Instance with that IP address.
  Please note that, we strongly recommend you use the DNS Name to connect to your DB Instance as the underlying IP address can change (e.g., during a failover).
  For Multi-AZ deployments, defining a subnet for all Availability Zones in a Region will allow Amazon RDS to create a new standby in another Availability Zone should the need arise. You need to do this even for Single-AZ deployments, just in case you want to convert them to Multi-AZ deployments at some point.

Q: Can I move my existing DB instances outside VPC into my VPC?
-  If your DB instance is not in a VPC, you can use the AWS Management Console to easily move your DB instance into a VPC. See the Amazon RDS User Guide for more details. You can also take a snapshot of your DB Instance outside VPC and restore it to VPC by specifying the DB Subnet Group you want to use. Alternatively, you can perform a “Restore to Point in Time” operation as well.

Q: Can I move my existing DB instances outside VPC into my VPC?
-  If your DB instance is not in a VPC, you can use the AWS Management Console to easily move your DB instance into a VPC. See the Amazon RDS User Guide for more details. You can also take a snapshot of your DB Instance outside VPC and restore it to VPC by specifying the DB Subnet Group you want to use. Alternatively, you can perform a “Restore to Point in Time” operation as well.

Q: Can I move my existing DB instances from inside VPC to outside VPC?
-  Migration of DB Instances from inside to outside VPC is not supported. For security reasons, a DB Snapshot of a DB Instance inside VPC cannot be restored to outside VPC. The same is true with “Restore to Point in Time” functionality. 

Q: Can I change the DB Subnet Group of my DB Instance?
-  An existing DB Subnet Group can be updated to add more subnets, either for existing Availability Zones or for new Availability Zones added since the creation of the DB Instance. Removing subnets from an existing DB Subnet Group can cause unavailability for instances if they are running in a particular AZ that gets removed from the subnet group.

Q: What privileges are granted to the master user for my DB Instance?
-  For MySQL, the default privileges for the master user include: create, drop, references, event, alter, delete, index, insert, select, update, create temporary tables, lock tables, trigger, create view, show view, alter routine, create routine, execute, trigger, create user, process, show databases, grant option.
  For Oracle, the master user is granted the "dba" role. The master user inherits most of the privileges associated with the role. Please refer to the Amazon RDS User Guide for the list of restricted privileges and the corresponding alternatives to perform administrative tasks that may require these privileges.

  For SQL Server, a user that creates a database is granted the "db_owner" role. Please refer to the Amazon RDS User Guide for the list of restricted privileges and the corresponding alternatives to perform administrative tasks that may require these privileges.

Q: Can I encrypt connections between my application and my DB Instance using SSL?
-  Yes, this option is currently supported for the MySQL, MariaDB, SQL Server, PostgreSQL, and Oracle engines.
  Amazon RDS generates an SSL certificate for each DB Instance. Once an encrypted connection is established, data transferred between the DB Instance and your application will be encrypted during transfer.
  While SSL offers security benefits, be aware that SSL encryption is a compute-intensive operation and will increase the latency of your database connection. SSL support within Amazon RDS is for encrypting the connection between your application and your DB Instance; it should not be relied on for authenticating the DB Instance itself

Q: How do I control the actions that my systems and users can take on specific RDS resources?
-  You can control the actions that your AWS IAM users and groups can take on RDS resources. You do this by referencing the RDS resources in the AWS IAM policies that you apply to your users and groups. RDS resources that can be referenced in an AWS IAM policy includes DB Instances, DB Snapshots, Read Replicas, DB Security Groups, DB Option Groups, DB Parameter Groups, Event Subscriptions and DB Subnet Groups. In addition, you can tag these resources to add additional metadata to your resources. By using tagging, you can categorize your resources (e.g. "Development" DB Instances, "Production" DB Instances, "Test" DB Instances etc), and write AWS IAM policies that list the permissions (i.e. actions) that can taken on resources with the same tags. For more information, refer to Managing Access to Your Amazon RDS Resources and Databases and Tagging Amazon RDS Resources

Q: I wish to perform security analysis or operational troubleshooting on my RDS deployment. Can I get a history of all 	RDS API calls made on my account?
-  Yes. AWS CloudTrail is a web service that records AWS API calls for your account and delivers log files to you. The AWS API call history produced by CloudTrail enables security analysis, resource change tracking, and compliance auditing.

### Multi-AZ Deployments and Read Replicas

Multi-AZ deployments utilize synchronous replication, making database writes concurrently on both the primary and standby so that the standby will be up-to-date in the event a failover occurs. While our technological implementation for Multi-AZ DB Instances maximizes data durability in failure scenarios, it precludes the standby from being accessed directly or used for read operations. The fault tolerance offered by Multi-AZ deployments make them a natural fit for production environments.

Q: What does it mean to run a DB instance as a Multi-AZ deployment?
-  When you create or modify your DB instance to run as a Multi-AZ deployment, Amazon RDS automatically provisions and maintains a synchronous “standby” replica in a different Availability Zone. Updates to your DB Instance are synchronously replicated across Availability Zones to the standby in order to keep both in sync and protect your latest database updates against DB instance failure. During certain types of planned maintenance, or in the unlikely event of DB instance failure or Availability Zone failure, Amazon RDS will automatically failover to the standby so that you can resume database writes and reads as soon as the standby is promoted. Since the name record for your DB instance remains the same, your application can resume database operation without the need for manual administrative intervention. With Multi-AZ deployments, replication is transparent: you do not interact directly with the standby, and it cannot be used to serve read traffic. More information about Multi-AZ deployments is in the Amazon RDS User Guide.

Q: What are the benefits of a Multi-AZ deployment?
-  The chief benefits of running your DB instance as a Multi-AZ deployment are enhanced database durability and availability. The increased availability and fault tolerance offered by Multi-AZ deployments make them a natural fit for production environments.
  Running your DB instance as a Multi-AZ deployment safeguards your data in the unlikely event of a DB instance component failure or loss of availability in one Availability Zone. For example, if a storage volume on your primary fails, Amazon RDS automatically initiates a failover to the standby, where all of your database updates are intact. This provides additional data durability relative to standard deployments in a single AZ, where a user-initiated restore operation would be required and updates that occurred after the latest restorable time (typically within the last five minutes) would not be available.
  You also benefit from enhanced database availability when running your DB instance as a Multi-AZ deployment. If an Availability Zone failure or DB instance failure occurs, your availability impact is limited to the time automatic failover takes to complete. The availability benefits of Multi-AZ also extend to planned maintenance. For example, with automated backups, I/O activity is no longer suspended on your primary during your preferred backup window, since backups are taken from the standby. In the case of patching or DB instance class scaling, these operations occur first on the standby, prior to automatic fail over. As a result, your availability impact is limited to the time required for automatic failover to complete.
  Another implied benefit of running your DB instance as a Multi-AZ deployment is that DB instance failover is automatic and requires no administration. In an Amazon RDS context, this means you are not required to monitor DB instance events and initiate manual DB instance recovery (via the RestoreDBInstanceToPointInTime or RestoreDBInstanceFromSnapshot APIs) in the event of an Availability Zone failure or DB instance failure.

Q: Are there any performance implications of running my DB instance as a Multi-AZ deployments?
-  You may observe elevated latencies relative to a standard DB instance deployment in a single Availability Zone as a result of the synchronous data replication performed on your behalf.

Q: What happens when I convert my RDS instance from Single-AZ to Multi-AZ?
-  For the RDS MySQL, MariaDB, PostgreSQL and Oracle database engines, when you elect to convert your RDS instance from Single-AZ to Multi-AZ, the following happens:
  A snapshot of your primary instance is taken
  A new standby instance is created in a different Availability Zone, from the snapshot
  Synchronous replication is configured between primary and standby instances
  As such, there should be no downtime incurred when an instance is converted from Single-AZ to Multi-AZ.

Q: What events would cause Amazon RDS to initiate a failover to the standby replica?
-	Amazon RDS detects and automatically recovers from the most common failure scenarios for Multi-AZ deployments so that you can resume database operations as quickly as possible without administrative intervention. Amazon RDS automatically performs a failover in the event of any of the following:
  * Loss of availability in primary Availability Zone
  * Loss of network connectivity to primary
  * Compute unit failure on primary
  * Storage failure on primary
Note: When operations such as DB instance scaling or system upgrades like OS patching are initiated for Multi-AZ deployments, for enhanced availability, they are applied first on the standby prior to an automatic failover. As a result, your availability impact is limited only to the time required for automatic failover to complete. Note that Amazon RDS Multi-AZ deployments do not failover automatically in response to database operations such as long running queries, deadlocks or database corruption errors.

Q: What happens during Multi-AZ failover and how long does it take?
- When failing over, Amazon RDS simply flips the canonical name record (CNAME) for your DB instance to point at the standby, which is in turn promoted to become the new primary.
 Failovers, as defined by the interval between the detection of the failure on the primary and the resumption of transactions on the standby, typically complete within one to two minutes.

Q: Can I initiate a “forced failover” for my Multi-AZ DB instance deployment?
- Amazon RDS will automatically failover without user intervention under a variety of failure conditions. In addition, Amazon RDS provides an option to initiate a failover when rebooting your instance. You can access this feature via the AWS Management Console or when using the RebootDBInstance API call.

Q: Do Amazon RDS Read Replicas support synchronous replication?
- No. Read Replicas in Amazon RDS for MySQL, MariaDB and PostgreSQL are implemented using those engines' native asynchronous replication.

Q: Can I make my Amazon RDS Read Replicas themselves Multi-AZ?
- Amazon RDS for MySQL, MariaDB and PostgreSQL do not presently support this.
 Amazon RDS for PostgreSQL: Read Replicas of Read Replicas are not currently supported.

Q: Can my Read Replicas only accept database read operations?
- Read Replicas are designed to serve read traffic. However, there may be use cases where advanced users wish to complete Data Definition Language (DDL) SQL statements against a Read Replica. Examples might include adding a database index to a Read Replica that is used for business reporting, without adding the same index to the corresponding source DB Instance.
 Amazon RDS for MySQL can be configured to permit DDL SQL statements against a Read Replica. If you wish to enable operations other than reads for a given Read Replica,  modify the active DB Parameter Group for the Read Replica, setting the “read_only” parameter to “0.”
 Amazon RDS for PostgreSQL does not currently support the execution of DDL SQL statements against a Read Replica.

Q: Will my Read Replica be kept up-to-date with its source DB Instance?
- Updates to a source DB Instance will automatically be replicated to any associated Read Replicas. However, with supported engines' asynchronous replication technology, a Read Replica can fall behind its source DB Instance for a variety of reasons. Typical reasons include:
Write I/O volume to the source DB Instance exceeds the rate at which changes can be applied to the Read Replica (this problem is particularly likely to arise if the compute capacity of a Read Replica is less than the source DB Instance)
  Complex or long-running transactions to the source DB Instance hold up replication to the Read Replica
  Network partitions or latency between the source DB Instance and a Read Replica

Q: I scaled the compute and/or storage capacity of my source DB Instance. Should I scale the resources for associated Read Replica(s) as well?
-  For replication to work effectively, we recommend that Read Replicas have as much or more compute and storage resources as their respective source DB Instances. Otherwise replication lag is likely to increase or your Read Replica may run out of space to store replicated updates.

Q: How do I integrate Enhanced Monitoring with my tool that I currently use?
-  RDS Enhanced Monitoring provides a set of metrics formed as JSON payloads which are delivered into your CloudWatch Logs account. The JSON payloads are delivered at the granularity last configured for the RDS instance.
   There are two ways you can consume the metrics via a third-party dashboard or application. Monitoring tools can use CloudWatch Logs Subscriptions to set up a near real time feed for the metrics. Alternatively, you can use filters in CloudWatch Logs to bridge metrics across to CloudWatch to and integrate your application with CloudWatch.
   
   
|Feature|PostgreSQL|MySQL|MariaDB|
|--- |--- |--- |--- |
|Maximum Read Replicas allowed per source DB Instance|5|5|5|
|Replication method|Asynchronous Physical|Asynchronous Logical|Asynchronous Logical|
|Must automatic backups be enabled for Read Replica support?|Yes|Yes|Yes|
|Engine versions for which Read Replicas are available|9.3.5 or later|5.5 or later|10.0 or later|
|Promotion of Read Replica to a new standalone DB Instance|Supported|Supported|Supported|
|Creation of Indexes on Read Replica|Currently not supported|Supported|Supported|
|Creation of Backups of Read Replicas|Currently not supported|Supported|Supported|
|Chaining of Read Replicas (i.e., Read Replicas of Read Replicas)|Currently not supported|Supported|Supported|
|Cross-Region Read Replicas|Supported|Supported|Supported|
