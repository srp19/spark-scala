## Fault Tolerant Architectures.

#### AMI

The template that you use to define your service instances is called an Amazon Machine Image (AMI).

Being able to quickly launch replacement instances based on an AMI that you define is a critical first step towards fault tolerance.

#### EBS (Elastic Block Store)

Amazon Elastic Block Store (Amazon EBS) provides block level storage volumes for use with Amazon EC2 instances.

EBS volumes store data redundantly, making them more durable than a typical hard drive. The annual failure rate (AFR) for an EBS volume is 0.1% and 0.5%, compared to 4% for a commodity hard drive.

If the Amazon EC2 instance fails and needs to be replaced, the Amazon EBS volume can simply be attached to the new Amazon EC2 instance.

Snapshots are stored for high-durability in Amazon S3.

#### Elastic IP Addresses

An elastic IP address can be detached from a failed instance and then mapped to a replacement instance within a very short time frame.
	
#### Failures Can Be Useful

Though often not readily admitted, the reality is that most software systems will degrade over time. This is due in part to any or all of the following reasons:
  1. Software will leak memory and/or resources. This includes software that you have written, as well as software that you depend on (e.g., application frameworks, operating systems, and device drivers).
  2. File systems will fragment over time and impact performance.
  3. Hardware (particularly storage) devices will physically degrade over time.

An application that takes full advantage of the AWS platform can be refreshed periodically with new server instances.

#### AutoScaling

The concept of automatically provisioning and scaling compute resources is a crucial aspect of any well-engineered, fault-tolerant application running on the Amazon Web Services platform.

For example, ‘N + 1 redundancy4’ is a very popular strategy for ensuring a resource (e.g., a database) is always available. ‘N + 1’ dictates that there should be N+1 resources operational, when N resources are sufficient to handle the anticipated load.


By using Auto Scaling, you can (and should) regularly turn your instances over to ensure that any leaks or degradation do not impact your application – you can literally set expiry dates on your server instances to ensure they remain ‘fresh.’

#### Elastic Load Balancing

Elastic Load Balancing is an AWS product that distributes incoming traffic to your application across several Amazon EC2 instances. When you use Elastic Load Balancing, you are given a DNS host name – any requests sent to this host name are delegated to a pool of Amazon EC2 instances.


#### Regions and Availability Zones

Another key element to achieving greater fault tolerance is to distribute your application geographically. If a single Amazon Web Services datacenter fails for any reason, you can protect your application by running it simultaneously in a geographically distant datacenter.

#### Building Multi-AZ Architectures to Achieve High Availability

This multi-site solution is highly available, and by design will cope with individual component or even Availability Zone failures.


Elastic IP Addresses play a critical role in the design of a fault-tolerant application spanning multiple Availability Zones. The failover mechanism can easily re-route the IP address (and hence the incoming traffic) away from a failed instance or zone to a replacement instance.

#### Reserved Instances

With Reserved Instances, you literally reserve computing capacity in the Amazon Web Services cloud. Doing so enables you to take advantage of a lower price, but more importantly in the context of fault tolerance, it will maximize your chances of getting the computing capacity you need.

#### Fault-Tolerant Building Blocks

- Amazon Simple Queue Service:
Amazon Simple Queue Service (SQS) is a highly reliable distributed messaging system that can serve as the backbone of your fault-tolerant application.

- Amazon Simple Storage Service

Amazon Simple Storage Service (Amazon S3) is a deceptively simple web service that provides highly durable, fault-tolerant data storage. Amazon Web Services is responsible for maintaining availability and fault-tolerance; you simply pay for the storage that you use.

- Amazon SimpleDB

Amazon SimpleDB is a fault-tolerant and durable structured data storage solution. 
Data stored in Amazon SimpleDB is stored redundantly without single points of failures