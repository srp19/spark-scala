* General
	* Q: How do I load and store my systems with Amazon EC2?
		Amazon EC2 allows you to set up and configure everything about your instances from your operating system up to your applications. An Amazon Machine Image (AMI) is simply a packaged-up environment that includes all the necessary bits to set up and boot your instance. Your AMIs are your unit of deployment. You might have just one AMI or you might compose your system out of several building block AMIs (e.g., webservers, appservers, and databases). Amazon EC2 provides a number of tools to make creating an AMI easy. Once you create a custom AMI, you will need to bundle it. If you are bundling an image with a root device backed by Amazon EBS, you can simply use the bundle command in the AWS Management Console. If you are bundling an image with a boot partition on the instance store, then you will need to use the AMI Tools to upload it to Amazon S3. Amazon EC2 uses Amazon EBS and Amazon S3 to provide reliable, scalable storage of your AMIs so that we can boot them when you ask us to do so.

		Or, if you want, you don’t have to set up your own AMI from scratch. You can choose from a number of globally available AMIs that provide useful instances. For example, if you just want a simple Linux server, you can choose one of the standard Linux distribution AMIs.

	* Q: How many instances can I run in Amazon EC2?
		You are limited to running up to at total of 20 On-Demand instances across the instance family, purchasing 20 Reserved Instances, and requesting Spot Instances per your dynamic Spot limit per region.
		Limit increases are tied to the region they were requested for.

	* Q: Are there any limitations in sending email from EC2 instances?
		Yes. In order to maintain the quality of EC2 addresses for sending email, we enforce default limits on the amount of email that can be sent from EC2 accounts.

	* Q: Does Amazon EC2 use ECC memory?
		In our experience, ECC memory is necessary for server infrastructure, and all the hardware underlying Amazon EC2 uses ECC memory.
	
	* Q: If I have two instances in different availability zones, how will I be charged for regional data transfer?
		Each instance is charged for its data in and data out at corresponding Data Transfer rates. Therefore, if data is transferred between these two instances, it is charged at "Data Transfer Out from EC2 to Another AWS Region" for the first instance and at "Data Transfer In from Another AWS Region" for the second instance.

	* Q: How do I select the right instance type?
		Amazon EC2 instances are grouped into 5 families: General Purpose, Compute Optimized, Memory Optimized, GPU, and Storage Optimized instances. General Purpose Instances have memory to CPU ratios suitable for most general purpose applications and come with fixed performance (M4 and M3 instances) or burstable performance (T2); Compute Optimized instances (C4 and C3 instances) have proportionally more CPU resources than memory (RAM) and are well suited for scale out compute-intensive applications and High Performance Computing (HPC) workloads; Memory Optimized Instances (R3 and R4 instances) offer larger memory sizes for memory-intensive applications, including database and memory caching applications; GPU Compute instances (P2) take advantage of the parallel processing capabilities of NVIDIA Tesla GPUs for high performance parallel computing; GPU Graphics instances (G2) offer high-performance 3D graphics capabilities for applications using OpenGL and DirectX; Storage Optimized Instances include I3 and I2 instances that provide very high, low latency, I/O capacity using SSD-based local instance storage for I/O-intensive applications and D2, Dense-storage instances, that provide high storage density and sequential I/O performance for data warehousing, Hadoop and other data-intensive applications. When choosing instance types, you should consider the characteristics of your application with regards to resource utilization (i.e. CPU, Memory, Storage) and select the optimal instance family and instance size.
		Use Cases:
		
		* Accelerated Computing :
		  * D2: Massively Parallel Processing (MPP) data warehousing, MapReduce and Hadoop distributed computing, distributed file systems, network file systems, log or data-processing applications
		  * I3: NoSQL databases like Cassandra, MongoDB, Redis, in-memory databases such as Aerospike, scale out transactional databases, data warehousing, Elasticsearch, analytics workloads.
		  * F1: F1 instances offer customizable hardware acceleration with field programmable gate arrays (FPGAs). Genomics research, financial analytics, real-time video processing, big data search and analysis, and security.
		  * G2: G2 instances are optimized for graphics-intensive applications.3D application streaming, video encoding, and other server-side graphics workloads.
		  * P2: P2 instances are intended for general-purpose GPU compute applications. Machine learning, high performance databases, computational fluid dynamics, computational finance, seismic analysis, molecular modeling, genomics, rendering, and other server-side GPU compute workloads.
		
		* Memory Optimised
		  * X1: X1 Instances are optimized for large-scale, enterprise-class, in-memory applications and have the lowest price per GiB of RAM among Amazon EC2 instance types. We recommend X1 instances for running in-memory databases like SAP HANA, big data processing engines like Apache Spark or Presto, and high performance computing (HPC) applications. X1 instances are certified by SAP to run Business Warehouse on HANA (BW), Data Mart Solutions on HANA, Business Suite on HANA (SoH), and the next-generation Business Suite S/4HANA in a production environment on the AWS cloud.

		  * R4: R4 instances are optimized for memory-intensive applications and offer better price per GiB of RAM than R3.

	* Q: What is an “EC2 Compute Unit” and why did you introduce it?

		Transitioning to a utility computing model fundamentally changes how developers have been trained to think about CPU resources. Instead of purchasing or leasing a particular processor to use for several months or years, you are renting capacity by the hour. Because Amazon EC2 is built on commodity hardware, over time there may be several different types of physical hardware underlying EC2 instances. Our goal is to provide a consistent amount of CPU capacity no matter what the actual underlying hardware.
		
		Amazon EC2 uses a variety of measures to provide each instance with a consistent and predictable amount of CPU capacity. In order to make it easy for developers to compare CPU capacity between different instance types, we have defined an Amazon EC2 Compute Unit. The amount of CPU that is allocated to a particular instance is expressed in terms of these EC2 Compute Units. We use several benchmarks and tests to manage the consistency and predictability of the performance from an EC2 Compute Unit. The EC2 Compute Unit (ECU) provides the relative measure of the integer processing power of an Amazon EC2 instance. Over time, we may add or substitute measures that go into the definition of an EC2 Compute Unit, if we find metrics that will give you a clearer picture of compute capacity.

	* Elastic IP:
		By default, all accounts are limited to 5 Elastic IP addresses per region.

		* Q: How long does it take to remap an Elastic IP address?
		The remap process currently takes several minutes from when you instruct us to remap the Elastic IP until it fully propagates through our system.
		
		* Q: Can I configure the reverse DNS record for my Elastic IP address?
		
		Yes, you can configure the reverse DNS record of your Elastic IP address by filling out this form. Note that a corresponding forward DNS record pointing to that Elastic IP address must exist before we can create the reverse DNS record.

	* .arpa (ARPA - Address and Routing Parameter Area)
	
		The domain name arpa is a top-level domain (TLD) in the Domain Name System of the Internet. It is used exclusively for technical infrastructure purposes. While the name was originally the acronym for the Advanced Research Projects Agency (ARPA), the funding organization in the United States that developed one of the precursors of the Internet (ARPANET), it now stands for Address and Routing Parameter Area.

	* Q: How can I make sure that I am in the same Availability Zone as another developer?
		
		We do not currently support the ability to coordinate launches into the same Availability Zone across AWS developer accounts. One Availability Zone name (for example, us-east-1a) in two AWS customer accounts may relate to different physical Availability Zones.


	* Enhanced Networking

		* Q: What networking capabilities are included in this feature?
			We currently support enhanced networking capabilities using SR-IOV (Single Root I/O Virtualization). SR-IOV is a method of device virtualization that provides higher I/O performance and lower CPU utilization compared to traditional implementations. For supported Amazon EC2 instances, this feature provides higher packet per second (PPS) performance, lower inter-instance latencies, and very low network jitter.
	 
	 	* Q: Why should I use Enhanced Networking?
			If your applications benefit from high packet-per-second performance and/or low latency networking, Enhanced Networking will provide significantly improved performance, consistence of performance and scalability

		* Q: How can I enable Enhanced Networking on supported instances?
			In order to enable this feature, you must launch an HVM AMI with the appropriate drivers. R4, X1, I3, P2 and m4.16xlarge instances provide the Elastic Network Adapter (ENA) interface (which uses the “ena” Linux driver) for Enhanced Networking. C3, C4, R3, I2, M4 (except m4.16xlarge) and D2 instances use Intel® 82599g Virtual Function Interface (which uses the “ixgbevf” Linux driver). Amazon Linux AMI includes both of these drivers by default. For AMIs that do not contain these drivers, you will need to download and install the appropriate drivers based on the instance types you plan to use. You can use Linux or Windows instructions to enable Enhanced Networking in AMIs that do not include the SR-IOV driver by default. Enhanced Networking is only supported in Amazon VPC.

		* Q: Do I need to pay an additional fee to use Enhanced Networking?
			No, there is no additional fee for Enhanced Networking. To take advantage of Enhanced Networking you need to launch the appropriate AMI on a supported instance type in a VPC.

		* Q: Why is Enhanced Networking only supported in Amazon VPC?
			Amazon VPC allows us to deliver many advanced networking features to you that are not possible in EC2-Classic. Enhanced Networking is another example of a capability enabled by Amazon VPC.

		* Q: Which instance types support Enhanced Networking?
			Currently C3, C4, D2, I3, I2 M4, X1 and R3 instances support Enhanced Networking. X1, P2, I3, R4 and m4.16xlarge instances provide the Elastic Network Adapter (ENA) interface for Enhanced Networking. C3, C4, R3, I2, M4 (except m4.16xlarge) and D2 instances, use Intel® 82599 Virtual Function Interface.

	* Q. Which instance types offer NVMe instance storage?
		High I/O instances use NVMe based local instance storage to deliver very high, low latency, I/O capacity to applications, and are optimized for applications that require millions of IOPS. Like Cluster instances, High I/O instances can be clustered via cluster placement groups for high bandwidth networking.

		Non-volatile memory express, also known as NVMe or NVM Express, is a specification that allows a solid-state drive (SSD) to make effective use of a high-speed Peripheral Component Interconnect Express (PCIe) bus in a computer.


	* Amazon Elastic Block Storage (EBS)

		* Q: What is the EBS General Purpose (SSD) volume type?
			The EBS General Purpose (SSD) volumes are backed by the same technology found in EBS Provisioned IOPS (SSD) volumes. The EBS General Purpose (SSD) volume type is designed for 99.999% availability, and a broad range of use-cases such as boot volumes, small and medium size databases, and development and test environments. General Purpose (SSD) volumes deliver a ratio of 3 IOPS per GB, offer single digit millisecond latencies, and also have the ability to burst up to 3000 IOPS for short periods.

		* Q: Will I be able to access my EBS snapshots using the regular Amazon S3 APIs?
			No, EBS snapshots are only available through the Amazon EC2 APIs.

		* Q: Do volumes need to be un-mounted in order to take a snapshot? Does the snapshot need to complete before the volume can be used again?

			No, snapshots can be done in real time while the volume is attached and in use. However, snapshots only capture data that has been written to your Amazon EBS volume, which might exclude any data that has been locally cached by your application or OS. In order to ensure consistent snapshots on volumes attached to an instance, we recommend cleanly detaching the volume, issuing the snapshot command, and then reattaching the volume. For Amazon EBS volumes that serve as root devices, we recommend shutting down the machine to take a clean snapshot.

		* Q: Can users of my Amazon EBS shared snapshots change any of my data?
			Users who have permission to create volumes based on your shared snapshots will first make a copy of the snapshot into their account. Users can modify their own copies of the data, but the data on your original snapshot and any other volumes created by other users from your original snapshot will remain unmodified.

		* Q: Do you offer encryption on Amazon EBS volumes and snapshots?
			Yes. EBS offers seamless encryption of data volumes and snapshots. EBS encryption better enables you to meet security and encryption compliance requirements.

	* Amazon CloudWatch

	* Q: Will I lose the metrics data if I disable monitoring for an Amazon EC2 instance?
		You can retrieve metrics data for any Amazon EC2 instance up to 2 weeks from the time you started to monitor it. After 2 weeks, metrics data for an Amazon EC2 instance will not be available if monitoring was disabled for that Amazon EC2 instance. If you want to archive metrics beyond 2 weeks you can do so by calling mon-get-stats command from the command line and storing the results in Amazon S3 or Amazon SimpleDB.

	* Q: What happens if a scaling activity causes me to reach my Amazon EC2 limit of instances?
		Auto Scaling Service cannot scale past the Amazon EC2 limit of instances that you can run. If you need more Amazon EC2 instances, complete the Amazon EC2 instance request form.

	* Q: What happens to my Amazon EC2 instances if I delete my Auto Scaling Group?
		If you have an Auto Scaling group with running instances and you choose to delete the Auto Scaling group, the instances will be terminated and the Auto Scaling group will be deleted.


	* Q: When should I use the Classic Load Balancer and when should I use the Application Load Balancer?
		The Classic Load Balancer is ideal for simple load balancing of traffic across multiple EC2 instances, while the Application Load Balancer is ideal for applications needing advanced routing capabilities, microservices, and container-based architectures. Please visit Elastic Load Balancing for more information.

	* Q: What are the differences between Standard RIs and Convertible RIs?
		Standard RIs offer a significant discount on EC2 instance usage when you commit to a particular instance family. Convertible RIs offer you the option to change your instance configuration during the term, and still receive a discount on your EC2 usage.

	* Q: Do RIs provide a capacity reservation?

		Yes, when a Standard or Convertible RI is scoped to a specific Availability Zone (AZ), instance capacity matching the exact RI configuration is reserved for your use (these are referred to as “zonal RIs”). Zonal RIs give you additional confidence in your ability to launch instances when you need them.

		You can also choose to forego the capacity reservation and purchase Standard or Convertible RIs that are scoped to a region (referred to as “regional RIs”). Regional RIs automatically apply the discount to usage across Availability Zones and instance sizes in a region, making it easier for you to take advantage of the RI’s discounted rate.

	* Q: When should I purchase a zonal RI?
		If you want to take advantage of the capacity reservation, then you should buy an RI in a specific Availability Zone.
	
	* Q: When should I purchase a regional RI?
		If you do not require the capacity reservation, then you should buy a regional RI. Regional RIs provide AZ and instance size flexibility, which offers broader applicability of the RI’s discounted rate.

	* Q: What are Availability Zone and instance size flexibility?
		Availability Zone and instance size flexibility make it easier for you to take advantage of your regional RI’s discounted rate. Availability Zone flexibility applies your RI’s discounted rate to usage in any Availability Zone in a region, while instance size flexibility applies your RI’s discounted rate to usage of any size within an instance family. Let’s say you own an m4.2xlarge Linux/Unix regional RI with default tenancy in US East (N.Virginia). Then this RI’s discounted rate can automatically apply to two m4.xlarge instances in us-east-1a or four m4.large instances in us-east-1b.

	* Q: What types of RIs provide instance size flexibility?
		Linux/Unix regional RIs with the default tenancy provide instance size flexibility. Instance size flexibility is not available on RIs of other platforms such as Windows, Windows with SQL Standard, Windows with SQL Server Enterprise, Windows with SQL Server Web, RHEL, and SLES.

	* Q: I own zonal RIs how do I assign them to a region?
		You can assign your Standard zonal RIs to a region by modifying the scope of the RI from a specific Availability Zone to a region from the EC2 management console or by using the ModifyReservedInstances API. 

	* Q: How does instance size flexibility work?
		EC2 uses the scale shown below, to compare different sizes within an instance family. In the case of instance size flexibility on RIs, this scale is used to apply the discounted rate of RIs to the normalized usage of the instance family. For example, if you have an m4.2xlarge RI that is scoped to a region, then your discounted rate could apply towards the usage of 1 m4.2xlarge or 2 m4.xlarge instances.

	* Q: Can I change my RI during its term?
		Yes, you can modify the Availability Zone of the RI, change the scope of the RI from Availability Zone to region (and vice-versa), change the network platform from EC2-VPC to EC2-Classic (and vice versa) or modify instance sizes within the same instance family (on the Linux/Unix platform).

	* Q: Do RIs apply to Spot instances or instances running on a Dedicated Host?
		No, RIs do not apply to Spot instances or instances running on Dedicated Hosts. To lower the cost of using Dedicated Hosts, purchase Dedicated Host Reservations.

	* Q: How do RIs work with Consolidated Billing?
		Our system automatically optimizes which instances are charged at the discounted rate to ensure that the consolidated accounts always pay the lowest amount. If you own RIs that apply to an Availability Zone, then only the account which owns the RI will receive the capacity reservation. However, the discount will automatically apply to usage in any account across your consolidated billing family.

	* Q: Can I get a discount on RI purchases?
		Yes, EC2 provides tiered discounts on RI purchases. These discounts are determined based on the total list value (non-discounted price) for the active RIs you have per region. Your total list value is the sum of all expected payments for an RI within the term, including both the upfront and recurring hourly payments. The tier ranges and corresponding discounts are shown alongside.

	* Q: Do Convertible RIs qualify for Volume Discounts?
		No, however the value of each Convertible RI that you purchase contributes to your volume discount tier standing.

	* Convertible Reserved Instances
	
	* Q: What is a Convertible Reserved Instance?
		A Convertible Reserved Instance is a type of Reserved Instance with attributes that can be changed during the term.
	
	* Q: When should I purchase a Convertible Reserved Instance instead of a Standard Reserved Instance?
		The Convertible Reserved Instance is useful for customers who can commit to using EC2 instances for a three-year term in exchange for a significant discount on their EC2 usage, are uncertain about their instance needs in the future, or want to benefit from changes in price.

	* Q: When should I purchase a Convertible Reserved Instance instead of a Standard Reserved Instance?
		The Convertible Reserved Instance is useful for customers who can commit to using EC2 instances for a three-year term in exchange for a significant discount on their EC2 usage, are uncertain about their instance needs in the future, or want to benefit from changes in price.

	* Q: Can I exchange my Convertible Reserved Instance to benefit from a Convertible Reserved Instance matching a different instance type, operating system, tenancy, or payment option?
		Yes, you can select a new instance type, operating system, tenancy, or payment option when you exchange your Convertible Reserved Instances.

	* Q. Are there any features or services of Amazon Web Services that are not supported for use with Spot instances?
		Amazon DevPay is not supported for use with Spot instances.

	* Q. Can I use a Spot instance with a paid AMI for third-party software (such as IBM’s software packages)?
		Not at this time.	
	
	* Q. What is a Spot fleet?
		A Spot fleet allows you to automatically bid on and manage multiple Spot instances that provide the lowest price per unit of capacity for your cluster or application, like a batch processing job, a Hadoop workflow, or an HPC grid computing job. You can include the instance types that your application can use, and define a target capacity based on your application needs (in units including instances, vCPUs, memory, storage, or network throughput). Spot fleets enable you to launch and maintain the target capacity, and to automatically request resources to replace any that are disrupted or manually terminated. 

	* Q. Can I use Spot fleet with Elastic Load Balancing, Auto Scaling, or Elastic MapReduce?
		You can use Spot fleet with Auto Scaling. You cannot use Elastic Load Balancing or Elastic MapReduce to trigger Spot fleet requests.

	* Q. Does a Spot fleet request terminate Spot instances when they are no longer running in the lowest priced Spot pools and relaunch them in the lowest priced pools?
		No, Spot fleet requests do not automatically terminate and re-launch instances while they are running. However, if you terminate a Spot instance, Spot fleet will replenish it with a new Spot instance in the new lowest priced pool.

	* Q. Can I submit a multi-Availability Zone fleet request?
		Yes, visit the Spot Fleet Examples section of the Amazon EC2 User Guide to learn how to submit a multi-Availability Zone Spot fleet request.

	* Q. How does Spot fleet allocate resources across the various Spot instance pools specified in the launch specifications?
		The RequestSpotFleet API provides two allocation strategies: lowestPrice and diversified. The lowestPrice strategy allows you to provision your Spot fleet resources in instance pools that provide the lowest price per unit of capacity at the time of the request. The diversified strategy allows you to provision your Spot fleet resources across multiple Spot instance pools. This enables you to maintain your fleet’s target capacity and increase your application’s availability as Spot capacity fluctuates.
		
		Running your application’s resources across diverse Spot instance pools also allows you to further reduce your fleet’s operating costs over time. Visit the Amazon EC2 User Guide to learn more.

	* Q. Are Spot fleet requests guaranteed to be fulfilled?

		No. Spot fleet requests allow you to place multiple Spot instance bids simultaneously, and are subject to the same availability and prices as a single Spot instance request. For example, if no resources are available at your Spot fleet request bid price, we may be unable to fulfill your request partially or in full.

	* Q. Can I specify a different AMI for each instance type that I want to use?
		Yes, simply specify the AMI you’d like to use in each launch specification you provide in your Spot fleet request.

	* Q: Are Spot blocks (Fixed Duration Spot instances) ever interrupted?
		Spot blocks are designed not to be interrupted and will run continuously for the duration you select, independent of Spot market price. In rare situations, Spot blocks may be interrupted due to AWS capacity needs. In these cases, we will provide a two-minute warning before we terminate your instance (termination notice), and you will not be charged for the affected instance(s).

		*New Spot Block Model*
		
		In order to make EC2 an even better fit for this type of defined-duration workload, you can now launch Spot instances that will run continuously for a finite duration (1 to 6 hours). Pricing is based on the requested duration and the available capacity, and is typically 30% to 45% less than On-Demand, with an additional 5% off during non-peak hours for the region. Spot blocks and Spot instances are priced separately; you can view the current Spot pricing to learn more.

		You simply submit a Spot instance request and use the new BlockDuration parameter to specify the number of hours your want your instance(s) to run, along with the maximum price that you are willing to pay. When Spot instance capacity is available for the the requested duration, your instances will launch and run continuously for a flat hourly price. They will be terminated automatically at the end of the time block (you can also terminate them manually). This model is a good for situations where you have jobs that need to run continuously for up to 6 hours.

	* Q. How can I use the processor state control feature available on the c4.8xlarge instance?
			The c4.8xlarge instance type provides the ability for an operating system to control processor C-states and P-states. This feature is currently available only on Linux instances. You may want to change C-state or P-state settings to increase processor performance consistency, reduce latency, or tune your instance for a specific workload. By default, Amazon Linux provides the highest-performance configuration that is optimal for most customer workloads; however, if your application would benefit from lower latency at the cost of higher single- or dual-core frequencies, or from lower-frequency sustained performance as opposed to bursty Turbo Boost frequencies, then you should consider experimenting with the C-state or P-state configuration options that are available to these instances. For additional information on this feature, see the Amazon EC2 User Guide section on Processor State Control.

	* Q: What are Accelerated Computing Instances?
			Accelerated Computing Instance family is a family of instances which use hardware accelerators, or co-processors, to perform some functions, such as floating point number calculation and graphics processing, more efficiently than is possible in software running on CPUs. Amazon EC2 provides two types of Accelerated Computing Instances – GPU Compute Instances for general-purpose computing and GPU Graphics Instances for graphics intensive applications.

	* Q. How are G2 instances different from CG1 instances?
			CG1 instances use NVIDIA Tesla GPUs and are designed for general purpose GPU computing using the CUDA or OpenCL programming models. CG1 instances provide customers with high bandwidth 10 Gbps networking, double precision floating-point capabilities, and error-correcting code (ECC) memory, making them ideal for High Performance Computing (HPC) applications. G2 instances use NVIDIA GRID GPUs and provide a cost-effective, high-performance platform for graphics applications using DirectX or OpenGL. NVIDIA GRID GPUs also support NVIDIA’s fast capture and encode APIs. Example applications include video creation services, 3D visualizations, streaming graphics-intensive applications, and other server-side workloads requiring massive parallel processing power. In addition, Graphics instances can also be used for general purpose computing using CUDA or OpenCL, but are not recommended for network-intensive HPC applications.

	* Cluster Instances
		* Q. What is a Cluster Compute Instance?
		
			Cluster Compute Instances combine high compute resources with a high performance networking for High Performance Compute (HPC) applications and other demanding network-bound applications. Cluster Compute Instances provide similar functionality to other Amazon EC2 instances but have been specifically engineered to provide high performance networking.
		
			Amazon EC2 cluster placement group functionality allows users to group Cluster Compute Instances in clusters – allowing applications to get the low-latency network performance necessary for tightly-coupled node-to-node communication typical of many HPC applications. Cluster Compute Instances also provide significantly increased network throughput both within the Amazon EC2 environment and to the Internet. As a result, these instances are also well suited for customer applications that need to perform network-intensive operations.

		* Q. Does use of Cluster Compute and Cluster GPU Instances differ from other Amazon EC2 instance types?
			Cluster Compute and Cluster GPU Instances use differs from other Amazon EC2 instance types in two ways.
			First, Cluster Compute and Cluster GPU Instances use Hardware Virtual Machine (HVM) based virtualization and run only Amazon Machine Images (AMIs) based on HVM virtualization. Paravirtual Machine (PVM) based AMIs used with other Amazon EC2 instance types cannot be used with Cluster Compute or Cluster GPU Instances.
			Second, in order to fully benefit from the available low latency, full bisection bandwidth between instances, Cluster Compute and Cluster GPU Instances must be launched into a cluster placement group through the Amazon EC2 API or AWS Management Console.

		* Q. What is a cluster placement group?
			A cluster placement group is a logical entity that enables creating a cluster of instances by launching instances as part of a group. The cluster of instances then provides low latency, full bisection 10 Gigabit Ethernet bandwidth connectivity between instances in the group. Cluster placement groups are created through the Amazon EC2 API or AWS Management Console.

		* Q. Are there any ways to optimize the likelihood that I receive the full number of instances I request for my cluster via a cluster placement group?
			We recommend that you launch the minimum number of instances required to participate in a cluster in a single launch. For very large clusters, you should launch multiple placement groups, e.g. two placement groups of 128 instances, and combine them to create a larger, 256 instance cluster.

		* Q. Can Cluster GPU and Cluster Compute Instances be launched into a single cluster placement group?
			While it may be possible to launch different cluster instance types into a single placement group, at this time we only support homogenous placement groups.


	* High I/O Instances

		* Q. What is a High I/O instance?
			High I/O instances use NVMe based local instance storage to deliver very high, low latency, I/O capacity to applications, and are optimized for applications that require millions of IOPS. Like Cluster instances, High I/O instances can be clustered via cluster placement groups for high bandwidth networking.

		* Q. How many IOPS can i3.16.xlarge instances deliver?
			Using HVM AMIs, High I/O I3 instances can deliver up to 3.3 million IOPS measured at 100% random reads using 4KB block size, and up to 300,000 100% random write IOPs, measured at 4KB block sizes to applications across 8 x 1.9 TB NVMe devices.  

		* Q. What is the sequential throughput of i3 instances?
			The maximum sequential throughput, measured at 128K block sizes is 16 GB/s read throughput and 6.4 GB/s write throughput.

		* Q. What is the sequential throughput of hi1.4xlarge instances?
			Sequential throughput on all AMI types (Linux PV, Linux HVM and Windows) is approximately 2 GB/s read and 1.1 GB/s write.

		* Q. AWS has other database and Big Data offerings. When or why should I use High I/O instances?
			High I/O instances are ideal for applications that require access to millions of low latency IOPS, and can leverage data stores and architectures that manage data redundancy and availability. Example applications are:
		
		    NoSQL databases like Cassandra and MongoDB
    		In-memory databases like Aerospike
    		Elasticsearch and analytics workloads
    		OLTP systems
    	* Q. Do High I/O instances support TRIM?
			The TRIM command allows the operating system to inform SSDs which blocks of data are no longer considered in use and can be wiped internally. In the absence of TRIM, future write operations to the involved blocks can slow down significantly. I3 instances support TRIM.
	
	* Dense-storage Instances
		
		* Q. What is a Dense-storage Instance?
			Dense-storage instances are designed for workloads that require high sequential read and write access to very large data sets, such as Hadoop distributed computing, massively parallel processing data warehousing, and log processing applications. The Dense-storage instances offer the best price/GB-storage and price/disk-throughput across other EC2 instances.
    	* Q. What does your Amazon EC2 Service Level Agreement guarantee?
			Our SLA guarantees a Monthly Uptime Percentage of at least 99.95% for Amazon EC2 and Amazon EBS within a Region.

		* Q. How much disk throughput can Dense-storage instances deliver?
			The largest current generation of Dense-storage instances, d2.8xlarge, can deliver up to 3.5 GBps read and 3.1 GBps write disk throughput with a 2 MiB block size. To ensure the best disk throughput performance from your D2 instances on Linux, we recommend that you use the most recent version of the Amazon Linux AMI, or another Linux AMI with a kernel version of 3.8 or later that supports persistent grants - an extension to the Xen block ring protocol that significantly improves disk throughput and scalability.

		* Q. Do Dense-storage instances provide any failover mechanisms or redundancy?
			The primary data storage for Dense-storage instances is HDD-based instance storage. Like all instance storage, these storage volumes persist only for the life of the instance. Hence, we recommend that you build a degree of redundancy (e.g. RAID 1/5/6) or use file systems (e.g. HDFS and MapR-FS) that support redundancy and fault tolerance. You can also back up data periodically to more durable data storage solutions such as Amazon Simple Storage Service (S3) for additional data durability. Please refer to Amazon S3 for reference.
		* Q. How do Dense-storage instances differ from Amazon EBS?
			Amazon EBS offers simple, elastic, reliable (replicated), and persistent block level storage for Amazon EC2 while abstracting the details of the underlying storage media in use. Amazon EC2 instance storage provides directly attached non-persistent, high performance storage building blocks that can be used for a variety of storage applications. Dense-storage instances are specifically targeted at customers who want high sequential read/write access to large data sets on local storage, e.g. for Hadoop distributed computing and massively parallel processing data warehousing.


	* Memory Optimized Instances

		* Q. When should I use Memory-optimized instances?
		Memory-optimized instances offer large memory size for memory intensive applications including in-memory applications, in-memory databases, in-memory analytics solutions, High Performance Computing (HPC), scientific computing, and other memory-intensive applications.

		* Q. When should I use X1 instances?
		X1 instances are ideal for running in-memory databases like SAP HANA, big data processing engines like Apache Spark or Presto, and high performance computing (HPC) applications. X1 instances are certified by SAP to run production environments of the next-generation Business Suite S/4HANA, Business Suite on HANA (SoH), Business Warehouse on HANA (BW), and Data Mart Solutions on HANA on the AWS cloud.

		* Q. Do X1 instances enable CPU power management state control?
		Yes. You can configure C-states and P-states on both x1.32xlarge and x1.16xlarge. You can use C-states to enable higher turbo frequencies (as much as 3.1 Ghz with one or two core turbo). You can also use P-states to lower performance variability by pinning all cores at P1 or higher P states, which is similar to disabling Turbo, and running consistently at the base CPU clock speed.


	* F1 Instances

		* Q. What are FPGAs and why do I need them?

		FPGAs are programmable integrated circuits that you can configure using software. By using FPGAs you can accelerate your applications up to 30x when compared with servers that use CPUs alone. And, FPGAs are reprogrammable, so you get the flexibility to update and optimize your hardware acceleration without having to redesign the hardware.

		Q. What is Amazon EC2 F1?

		Amazon EC2 F1 is a new compute instance with programmable hardware you can use for application acceleration. The new F1 instance type provides a high performance, easy to access FPGA for developing and deploying custom hardware accelerations.


	* VM Import/Export
		* Q. What is VM Import/Export?
		VM Import/Export enables customers to import Virtual Machine (VM) images in order to create Amazon EC2 instances. Customers can also export previously imported EC2 instances to create VMs. Customers can use VM Import/Export to leverage their previous investments in building VMs by migrating their VMs to Amazon EC2.
