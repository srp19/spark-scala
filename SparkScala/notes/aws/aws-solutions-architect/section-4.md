## Domain 4 (worth 10% of the exam)

### VPC Refresher

* What is a VPC
* How to build your own VPC
* How to make a subnet public
* How to make a subnet private
* What a NAT is
* What a route table is
* Subnets can communicate with each other by default.

### Build Own Custom VPC

* If the VPC tenancy is selected as dedicated, then the EC2 instances provisioned under the VPC would be provisioned on a dedicated servers. However dedicated is a lot more expensive.
* A route table is automatically created when we created a VPC.
* Only one internel gateway per VPC. 

### NAT LAB

* Disable source destination check.
* Provision the nat instance in public subnet and attach the NAT instance to the default or private route table.

### VPC peering refresher

* Transitive Peering is not supported.
* You can create VPC peering connection between your own VPCs, or with a VPC in another AWS account within a single region.
* AWS uses existing infrastructure of a VPC to create a VPC peering connection; it is neither a gateway nor a VPN connection, and does not rely on a separate piece of physical hardware. There is no single point of failure for communication or a bandwidth bottleneck.
* Soft Limit of 50 VPC peers per VPC, can be increased to 125 by request.
* Cannot create VPC peering between VPC that have overlapping CIDR blocks.
* A placement group can span peered VPCs; however you won't get full bandwidth between instances in peered VPCs.
* A security group in one VPC can be referenced by another security group in the peered VPC.
* Private DNS values cannot be resolved between instances in the peered VPCs.

### Direct Connect (Read Documentation and FAQ's)

AWS Direct Connect makes it easy to establish a dedicated network connection from your premises to AWS. Using AWS Direct Connect, you can establish private connectivity between AWS and your datacenter, office or colocation environment, which in many cases can reduce network costs, increase bandwidth throughput and provide a more consistent network experience than internet based connections.

AWS Direct Connect lets you establish a dedicated network connection between your network and one of AWS Direct Connect locations using the industry standard 802.1 q VLANs, this dedicated network can be partitioned into multiple network interfaces also called as VIFs.

This allows you to use the same connection to access public resources such as objects stored in S3 and resources in Amazon VPC while maintaining separation between public and private environments. 

VIFs (Virtual Interfaces) can be either Public or Private. Public VIFs can be used to connect to EC2 instances with Public IPS, S3 endpoints, Dnyamodb etc. Private VIFs can be used to connect to EC2 instances or resources in a VPC. Each Direct Connect to VPC connection would need a separate VIF. So if you have 3 VPCs in your AWS account you would need 3 VIFs to connect from your DirectConnect Location.  

#### Direct Connect Benefits

* Reduce costs when using large volumes of traffic
* Increases reliability
* Increases bandwidth

#### How is Direct Connect different from VPN?

VPN connections are good solution for immediate need, have low to modest bandwidth requirements and can tolerate the inherent variability in the internet based connectivity.

AWS Direct Connect does not involve the internet. Instead it uses dedicated, private network between your internet and Amazon VPC.

Direct Connect Connections are available in:
- 10 Gbps
- 1 Gbps
- Sub 1 Gbps can be purchased through AWS Direct Connect Partners.
- Uses Ethernet VLAN trunking (802.1Q).
- This dedicated connection can be partitioned into multiple virtual Interfaces (VIFs).
- Its allows public connections to EC2 or S3 using Public IP addresses.
- It allows private connections to VPC using internal IP addresses.

Direct connect is not fault tolerant. If you need redudancy, you can use a site to site VPN and set it up using a BGP to failover automatically from Direct Connect to Site to Site VPN.

![Direct Connect Skeleton Diagram](section-4-1.jpg)

![Direct Connect Detailed Diagram](section-4-2.jpg)

#### CGW vs VPG

When using a VPN you need an anchor on each side of that connection. A Customer Gateway is the anchor on your side and it can be physical or a software appliance.

The anchor on the AWS side of the VPN connection is called a Virtual Private Gateway.

#### Key Points to Remember

- If you are accessing public services using https endpoints (think DynamoDB, S3) then use public VIFs.
- If you are accessing VPCs using private IP address ranges, the use private VIFs.
- Direct Connect itself is not redundant. You can add redundancy by having 2 connections (2 routers, 2 direct connects), or by having a site-to-site VPN in place.
- Layer 2 connections are not supported.

### HPC and Enhanced Networking.

High Performance compute is generally used by many different industries, such as the pharmaceutical or automative industries. HPC typically involves:
- Batch processing with large and compute intensive workloads
- Demands high performance CPU, Network and Storage.
- Usually Jumbo frames are required.

Jumbo Framers are ethernet frames with more than 1500 bytes of payload. A jumbo frame can carry upto 9000 bytes of payload.

Shared file systems such as Lustre and NFS use Jumbo Frames frequently. HPC applications use a lot of disk I/O and need access to a shared file system. this makes jumbo frames critical.

#### SR-IOV

The use of Jumbo frames is supported on AWS through enhanced networking. Enhanced networking is available using single-root I/O virtualization on supported instance types:
- C4
- C3
- M4
- I2
- R3
- D2

SR-IOV is supported on HVM VMs, not PV.

#### Placement Groups

- Logical grouping of instances whithin the same availability Zone.
- Enables applications to participate in low latency, 10 Gbps network.
- Placement groups are recommended for applications that benefit from low latency, high throughput or both.
- for lowest latency and highest packet per second network performance choose an instance type that supports enhanced networking.
- Placement Groups dont span availability zones.
- Placement Groups can span subnets in same AZ.
- Existing instances cannot be moved into Placement Groups.
- Provision placement Group for peak load. you may not be able to add instances later.
- Try to use hoogenous instance types.

### ELBs

#### Classic Load Balancer

- You can launch a classic load balancer either in a single or multiple AZ's, but an application ELB has to be launched in Multipe AZ's
- You can create a classic load balancer without a public IP address to serve as an internal load balancer.
- Supports SSL Offloading
- Sticky Sessions
- Support both IPV4 and IPV6
- Supports Layer 4 and Layer 7 loadbalancing
 - You can load balance http/https applications and use layer 7 features, such as X-Forwarded and sticky sessions. You can use strict layer 4 load balancing for applications that rely on TCP protocol.
- Operational Monitoring and Logging available.
- You can use Cloud trail to log all api calls to the ELB

#### Application Load Balancer

- Can route a request to a service behind your ALB based on the content of the request.
- Supports Host based routing: You can route a request based on the host field of the Http header.
- Path based routing: Can route client request based on the url path of the http header.
- Has containerized Application support (integrates with ECS). 
> That allows you to configure an application loadbalancer to loadbalance containers accross multiple ports on a single EC2 instance. Basically use ECS and it allows you to specify a dynamic port in ECS task definition and then it gives the container an unused port when it is scheduled on an EC2 instance and the EC2 schedular automatically adds the task to ALB by using this port.
The Classic Load Balancer requires that you statically map port numbers on a container instance. You cannot run multiple copies of a task on the same instance because the ports would conflict. An Application Load Balancer allows dynamic port mapping. You can have multiple tasks from a single service on the same container instance.
  > - IMP ECS concepts: Amazon EC2 Container Service (Amazon ECS) is a shared state, optimistic concurrency system that provides flexible scheduling capabilities for your tasks and containers. The Amazon ECS schedulers leverage the same cluster state information provided by the Amazon ECS API to make appropriate placement decisions.
 >> - Task Definition: A task definition is like a blue print for your application. Every time you launch a task in Amazon ECS, you specify a task definition so the service knows which Docker image to use for containers, how many containers to use in the task, and the resource allocation for each container. What (if any) ports from the container are mapped to the host container instance.
 >> - Service : A service launches and maintains a specified number of copies of the task definition in your cluster. The Amazon ECS sample application is a web-based "Hello World" style application that is meant to run indefinitely, so by running it as a service, it will restart if the task becomes unhealthy or unexpectedly stops.
 >> - Service Schedular: Amazon ECS provides a service scheduler (for long-running tasks and applications), the ability to run tasks manually (for batch jobs or single run tasks), with Amazon ECS placing tasks on your cluster for you, and the ability to run tasks on the container instance that you specify, so that you can integrate with custom or third-party schedulers or place a task manually on a specific container instance.
>> - **Note:** The task definition must be set to use host port 0. Because a random port from the instance's ephemeral port range is used, make sure that the security group and NACL settings of the load balancer and the backend instances allow traffic from the load balancer to the instances over the ephemeral port range.

- You can use ALB to configure service autoscaling with ECS
- HTTP/2 Support : Http version 2 supports multiplexing multiple requests over a single tcp connection, which means multiple request can be served over a single connection.
- WebSockets Support : Allows server to exchange message with end users, without the end user having to poll the server for an update. WebSocket is a computer communications protocol, providing full-duplex communication channels over a single TCP connection.
- Native IPV6 Support
- Sticky Sessions
- Health Checks
- High Availability (Works with only multi AZ setup)
- Security Features.
- Integrates with WAF (Web Application Firewall)
- Layer 7 Loadbalancing.
- Delete Protection
- Request Tracing - custom identifier "X-Amzn-Trace-Id" Http header on all requests. You can track requests as they reach or pass through multiple endpoints in your VPC.

#### Classic Load Balancer Tips

* Know which ports ELBs supports
 - [EC2-VPC] 1-65535
 - [EC2-Classic] 25,80,443,465,587,1024-65535
* Cannot assign an EIP to Elastic Load balancer
* IPV4 and IPV6 supported.
* You can load balance to the "Zone Apex" of your domain name.
* You can log all api calls to ELB by turning on cloudtrail.

#### Read FAQs

### Scaling NATs

Bottlenecks can occur when you have a single NAT instance that has too much traffic passing through it. There are several approaches to reducing bottlenecks:

- Scale up
- Choose an instance family that supports enhanced networking.
- Scale out: Only one subnet can route to a NAT at a particular time so add an additional NAT & subnet and migrate half your workloads to the new subnet.
- HA for NATs: You can create HA for NAT instances, but each subnet can only route to 1 NAT at a time. You can failover a subnet to another NAT

Blog on how to do HA for NAT.

https://aws.amazon.com/articles/2781451301784570

- SSL Offloading
- Operational Monitoring
- Logging
- You can use Cloud trail to log all api calls to the ALB

### Domain 4 Wrapup.

Revision for all the above mentioned points.

(Read FAQ's on Direct Connect and VPCs)






