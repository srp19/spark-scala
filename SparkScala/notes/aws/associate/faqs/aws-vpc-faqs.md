### General

Q. What are the components of Amazon VPC?

Amazon VPC comprises a variety of objects that will be familiar to customers with existing networks:

    A Virtual Private Cloud (VPC): A logically isolated virtual network in the AWS cloud. You define a VPC’s IP address space from a range you select.
    Subnet: A segment of a VPC’s IP address range where you can place groups of isolated resources.
    Internet Gateway: The Amazon VPC side of a connection to the public Internet.
    NAT Gateway: A highly available, managed Network Address Translation (NAT) service for your resources in a private subnet to access the Internet.
    Hardware VPN Connection: A hardware-based VPN connection between your Amazon VPC and your datacenter, home network, or co-location facility.
    Virtual Private Gateway: The Amazon VPC side of a VPN connection.
    Customer Gateway: Your side of a VPN connection.
    Router: Routers interconnect subnets and direct traffic between Internet gateways, virtual private gateways, NAT gateways, and subnets.
    Peering Connection: A peering connection enables you to route traffic via private IP addresses between two peered VPCs.
    VPC Endpoint: Enables Amazon S3 access from within your VPC without using an Internet gateway or NAT, and allows you to control the access using VPC endpoint policies.
    Egress-only Internet Gateway: A stateful gateway to provide egress only access for IPv6 traffic from the VPC to the Internet


Connectivity

Q. What are the connectivity options for my VPC?

You may connect your VPC to:

    The Internet (via an Internet gateway)
    Your corporate data center using a Hardware VPN connection (via the virtual private gateway)
    Both the Internet and your corporate data center (utilizing both an Internet gateway and a virtual private gateway)
    Other AWS services (via Internet gateway, NAT, virtual private gateway, or VPC endpoints)
    Other VPCs (via VPC peering connections)


Q. How do instances in a VPC access the Internet?

You can use public IP addresses, including Elastic IP addresses (EIPs), to give instances in the VPC the ability to both directly communicate outbound to the Internet and to receive unsolicited inbound traffic from the Internet (e.g., web servers).  You can also use the solutions in the next question.

Q. How does a hardware VPN connection work with Amazon VPC?

A hardware VPN connection connects your VPC to your datacenter. Amazon supports Internet Protocol security (IPsec) VPN connections. Data transferred between your VPC and datacenter routes over an encrypted VPN connection to help maintain the confidentiality and integrity of data in transit. An Internet gateway is not required to establish a hardware VPN connection.

Q. What is IPsec?

IPsec is a protocol suite for securing Internet Protocol (IP) communications by authenticating and encrypting each IP packet of a data stream. 




Q. Which customer gateway devices can I use to connect to Amazon VPC?

There are two types of VPN connections that you can create: statically-routed VPN connections and dynamically-routed VPN connections. Customer gateway devices supporting statically-routed VPN connections must be able to:

    Establish IKE Security Association using Pre-Shared Keys
    Establish IPsec Security Associations in Tunnel mode
    Utilize the AES 128-bit or 256-bit encryption function
    Utilize the SHA-1 or SHA-2 (256) hashing function
    Utilize Diffie-Hellman (DH) Perfect Forward Secrecy in "Group 2" mode, or one of the additional DH groups we support
    Perform packet fragmentation prior to encryption

In addition to the above capabilities, devices supporting dynamically-routed VPN connections must be able to:

    Establish Border Gateway Protocol (BGP) peerings
    Bind tunnels to logical interfaces (route-based VPN)
    Utilize IPsec Dead Peer Detection


Q. Which Diffie-Hellman Groups do you support?

We support the following Diffie-Hellman (DH) groups in Phase1 and Phase2.

    Phase1 DH groups 2, 14-18, 22, 23, 24
    Phase2 DH groups 2, 5, 14-18, 22, 23, 24

Q. Can I NAT my CGW behind a router or firewall?

Yes, you will need to enable NAT-T and open UDP port 4500 on your NAT device.

Q. What IP address do I use for my CGW address?

You will use the public IP address of your NAT device.

Q. How does my connection decide to use NAT-T?

If your device has NAT-T enabled on the tunnel, AWS will use it by default. You will need to open UDP port 4500 or else the tunnel will not establish.

Q. What tools are available to me to help troubleshoot my Hardware VPN configuration?

The DescribeVPNConnection API displays the status of the VPN connection, including the state ("up"/"down") of each VPN tunnel and corresponding error messages if either tunnel is "down". This information is also displayed in the AWS Management Console.

Q. How do I connect a VPC to my corporate datacenter?

Establishing a hardware VPN connection between your existing network and Amazon VPC allows you to interact with Amazon EC2 instances within a VPC as if they were within your existing network. AWS does not perform network address translation (NAT) on Amazon EC2 instances within a VPC accessed via a hardware VPN connection.

Q. How many IPsec security associations can be established concurrently per tunnel?

The AWS VPN service is a route-based solution, so when using a route-based configuration you will not run into SA limitations. If, however, you are using a policy-based solution you will need to limit to a single SA, as the service is a route-based solution.

Q. What IP address ranges are assigned to a default VPC?

Default VPCs are assigned a CIDR range of 172.31.0.0/16. Default subnets within a default VPC are assigned /20 netblocks within the VPC CIDR range. 

Q. Can I advertise my VPC public IP address range to the Internet and route the traffic through my datacenter, via the hardware VPN, and to my VPC?

Yes, you can route traffic via the hardware VPN connection and advertise the address range from your home network.

Q. How large of a VPC can I create?

Currently, Amazon VPC supports VPCs between /28 (in CIDR notation) and /16 in size for IPv4. The IP address range of your VPC should not overlap with the IP address ranges of your existing network.

For IPv6, the VPC is a fixed size of /56 (in CIDR notation). A VPC can have both IPv4 and IPv6 CIDR blocks associated to it.

Q. Can I change a VPC's size?

No. To change the size of a VPC you must terminate your existing VPC and create a new one.

Q. How many subnets can I create per VPC?

Currently you can create 200 subnets per VPC. If you would like to create more, please submit a case at the support center.

Q. Is there a limit on how large or small a subnet can be?

The minimum size of a subnet is a /28 (or 14 IP addresses.) for IPv4. Subnets cannot be larger than the VPC in which they are created.

For IPv6, the subnet size is fixed to be a /64. Only one IPv6 CIDR block can be allocated to a subnet.


Q. Can I change the private IP addresses of an Amazon EC2 instance while it is running and/or stopped within a VPC?

Primary private IP addresses are retained for the instance's or interface's lifetime. Secondary private IP addresses can be assigned, unassigned, or moved between interfaces or instances at any time.

Q. If an Amazon EC2 instance is stopped within a VPC, can I launch another instance with the same IP address in the same VPC?

No. An IP address assigned to a running instance can only be used again by another instance once that original running instance is in a “terminated” state. 


Q. Can I assign IP addresses for multiple instances simultaneously?

No. You can specify the IP address of one instance at a time when launching the instance. 

Q. Can I assign any IP address to an instance?

You can assign any IP address to your instance as long as it is:

    Part of the associated subnet's IP address range
    Not reserved by Amazon for IP networking purposes
    Not currently assigned to another interface

Q. Can I assign multiple IP addresses to an instance?

Yes. You can assign one or more secondary private IP addresses to an Elastic Network Interface or an EC2 instance in Amazon VPC. The number of secondary private IP addresses you can assign depends on the instance type. See the EC2 User Guide for more information on the number of secondary private IP addresses that can be assigned per instance type.



Q. Can I assign one or more Elastic IP (EIP) addresses to VPC-based Amazon EC2 instances?

Yes, however, the EIP addresses will only be reachable from the Internet (not over the VPN connection). Each EIP address must be associated with a unique private IP address on the instance. EIP addresses should only be used on instances in subnets configured to route their traffic directly to the Internet gateway. EIPs cannot be used on instances in subnets configured to use a NAT gateway or a NAT instance to access the Internet.  This is applicable only for IPv4. Amazon VPCs do not support EIPs for IPv6 at this time. 


### Routing & Topology



Q. What does an Amazon VPC router do?

An Amazon VPC router enables Amazon EC2 instances within subnets to communicate with Amazon EC2 instances in other subnets within the same VPC. The VPC router also enables subnets, Internet gateways, and virtual private gateways to communicate with each other. Network usage data is not available from the router; however, you can obtain network usage statistics from your instances using Amazon CloudWatch.

Q. Can I modify the VPC route tables?

Yes. You can create route rules to specify which subnets are routed to the Internet gateway, the virtual private gateway, or other instances.

Q. Does Amazon VPC support multicast or broadcast?

No.

### Security & Filtering

Q. How do I secure Amazon EC2 instances running within my VPC?

Amazon EC2 security groups can be used to help secure instances within an Amazon VPC. Security groups in a VPC enable you to specify both inbound and outbound network traffic that is allowed to or from each Amazon EC2 instance. Traffic which is not explicitly allowed to or from an instance is automatically denied.

In addition to security groups, network traffic entering and exiting each subnet can be allowed or denied via network Access Control Lists (ACLs).

Q. Can Amazon EC2 instances within a VPC communicate with Amazon EC2 instances not within a VPC?

Yes. If an Internet gateway has been configured, Amazon VPC traffic bound for Amazon EC2 instances not within a VPC traverses the Internet gateway and then enters the public AWS network to reach the EC2 instance. If an Internet gateway has not been configured, or if the instance is in a subnet configured to route through the virtual private gateway, the traffic traverses the VPN connection, egresses from your datacenter, and then re-enters the public AWS network.

Q. Can Amazon EC2 instances within a VPC in one region communicate with Amazon EC2 instances within a VPC in another region?

Yes, they can communicate using public IP addresses, NAT gateway, NAT instances, VPN connections, or Direct Connect connections.  

Q. Can Amazon EC2 instances within a VPC communicate with Amazon S3?

Yes. There are multiple options for your resources within a VPC to communicate with Amazon S3. You can use VPC Endpoint for S3, which makes sure all traffic remains within Amazon's network and enables you to apply additional access policies to your Amazon S3 traffic. You can use an Internet gateway to enable Internet access from your VPC and instances in the VPC can communicate with Amazon S3. You can also make all traffic to Amazon S3 traverse the Direct Connect or VPN connection, egress from your datacenter, and then re-enter the public AWS network.

Q. Why can’t I ping the router, or my default gateway, that connects my subnets?

Ping (ICMP Echo Request and Echo Reply) requests to the router in your VPC is not supported. Ping between Amazon EC2 instances within VPC is supported as long as your operating system's firewalls, VPC security groups, and network ACLs permit such traffic.

Q. Can I monitor the network traffic in my VPC?

Yes. You can use the Amazon VPC Flow Logs feature to monitor the network traffic in your VPC.

Q. Am I charged for network bandwidth between instances in different subnets?

If the instances reside in subnets in different Availability Zones, you will be charged $0.01 per GB for data transfer.

Q. How many Amazon EC2 instances can I use within a VPC?

You can run any number of Amazon EC2 instances within a VPC, so long as your VPC is appropriately sized to have an IP address assigned to each instance. You are initially limited to launching 20 Amazon EC2 instances at any one time and a maximum VPC size of /16 (65,536 IPs). If you would like to increase these limits, please complete the following form.


Q: Can I boot an Amazon EC2 instance from an Amazon EBS volume within Amazon VPC?

Yes, however, an instance launched in a VPC using an Amazon EBS-backed AMI maintains the same IP address when stopped and restarted. This is in contrast to similar instances launched outside a VPC, which get a new IP address. The IP addresses for any stopped instances in a subnet are considered unavailable.


### Default VPCs

Q. How many default VPCs can I have?

You can have one default VPC in each AWS region where your Supported Platforms attribute is set to "EC2-VPC".

Q. How many default subnets are in a default VPC?

One default subnet is created for each Availability Zone in your default VPC.

Q. I have an existing EC2-Classic account. Can I get a default VPC?

The simplest way to get a default VPC is to create a new account in a region that is enabled for default VPCs, or use an existing account in a region you've never been to before, as long as the Supported Platforms attribute for that account in that region is set to "EC2-VPC".

Q. I really want a default VPC for my existing EC2 account. Is that possible?

Yes, however, we can only enable an existing account for a default VPC if you have no EC2-Classic resources for that account in that region. Additionally, you must terminate all non-VPC provisioned Elastic Load Balancers, Amazon RDS, Amazon ElastiCache, and Amazon Redshift resources in that region. After your account has been configured for a default VPC, all future resource launches, including instances launched via Auto Scaling, will be placed in your default VPC. To request your existing account be setup with a default VPC, contact AWS Support. We will review your request and your existing AWS services and EC2-Classic presence to determine if you are eligible for a default VPC.

### Elastic Network Interfaces



Q. Can I attach or detach one or more network interfaces to an EC2 instance while it’s running?

Yes.

Q. Can I have more than two network interfaces attached to my EC2 instance?

The total number of network interfaces that can be attached to an EC2 instance depends on the instance type. 



Q. Can I attach a network interface in one Availability Zone to an instance in another Availability Zone?

Network interfaces can only be attached to instances residing in the same Availability Zone.

Q. Can I attach a network interface in one VPC to an instance in another VPC?

Network interfaces can only be attached to instances in the same VPC as the interface. 


Q. Will I get charged for an Elastic IP Address that is associated to a network interface but the network interface isn’t attached to a running instance?

Yes. 

Q. Can I detach the primary interface (eth0) on my EC2 instance?

No. You can attach and detach secondary interfaces (eth1-ethn) on an EC2 instance, but you can’t detach the eth0 interface. 


### Peering Connections

Q. How much do VPC peering connections cost?

There is no charge for creating VPC peering connections, however, data transfer across peering connections is charged.

Q. Can I use AWS Direct Connect or hardware VPN connections to access VPCs I’m peered with?

No. “Edge to Edge routing” isn’t supported in Amazon VPC. 

Q. If I peer VPC A to VPC B and I peer VPC B to VPC C, does that mean VPCs A and C are peered?

No. Transitive peering relationships are not supported.

Q. What if my peering connection goes down?

AWS uses the existing infrastructure of a VPC to create a VPC peering connection; it is neither a gateway nor a VPN connection, and does not rely on a separate piece of physical hardware. There is no single point of failure for communication or a bandwidth bottleneck.

Q. Are there any bandwidth limitations for peering connections?

Bandwidth between instances in peered VPCs is no different than bandwidth between instances in the same VPC. Note: A placement group can span peered VPCs; however, you will not get full-bisection bandwidth between instances in peered VPCs. 

### ClassicLink

Q. What is ClassicLink?

Amazon Virtual Private Cloud (VPC) ClassicLink allows EC2 instances in the EC2-Classic platform to communicate with instances in a VPC using private IP addresses. To use ClassicLink, enable it for a VPC in your account, and associate a Security Group from that VPC with an instance in EC2-Classic. All the rules of your VPC Security Group will apply to communications between instances in EC2-Classic and instances in the VPC. 

Q. How do I use ClassicLink?

In order to use ClassicLink, you first need to enable at least one VPC in your account for ClassicLink. Then you associate a Security Group from the VPC with the desired EC2-Classic instance. The EC2-Classic instance is now linked to the VPC and is a member of the selected Security Group in the VPC. Your EC2-Classic instance cannot be linked to more than one VPC at the same time.



Q. Does the EC2-Classic instance become a member of the VPC?

The EC2-Classic instance does not become a member of the VPC. It becomes a member of the VPC Security Group that was associated with the instance. All the rules and references to the VPC Security Group apply to communication between instances in EC2-Classic instance and resources within the VPC.



Q. Are there any VPCs for which I cannot enable ClassicLink?

Yes. ClassicLink cannot be enabled for a VPC that has a Classless Inter-Domain Routing (CIDR) that is within the 10.0.0.0/8 range, with the exception of 10.0.0.0/16 and 10.1.0.0/16. In addition, ClassicLink cannot be enabled for any VPC that has a route table entry pointing to the 10.0.0.0/8 CIDR space to a target other than "local".

Q. Can traffic from an EC2-Classic instance travel through the Amazon VPC and egress through the Internet gateway, virtual private gateway, or to peered VPCs?

Traffic from an EC2-Classic instance can only be routed to private IP addresses within the VPC. They will not be routed to any destinations outside the VPC, including Internet gateway, virtual private gateway, or peered VPC destinations.

Q. Will ClassicLink settings on my EC2-Classic instance persist through stop/start cycles?

The ClassicLink connection will not persist through stop/start cycles of the EC2-Classic instance. The EC2-Classic instance will need to be linked back to a VPC after it is stopped and started. However, the ClassicLink connection will persist through instance reboot cycles.



Q: Does ClassicLink allow EC2-Classic Security Group rules to reference VPC Security Groups, or vice versa?

ClassicLink does not allow EC2-Classic Security Group rules to reference VPC Security Groups, or vice versa.


Q. How many VPCs, subnets, Elastic IP addresses, Internet gateways, customer gateways, virtual private gateways, and VPN connections can I create?

You can have:
 * Five Amazon VPCs per AWS account per region
 * Two hundred subnets per Amazon VPC
 * Five Amazon VPC Elastic IP addresses per AWS account per region
 * One Internet gateway per VPC
 * Five virtual private gateways per AWS account per region
 * Fifty customer gateways per AWS account per region
 * Ten IPsec VPN Connections per virtual private gateway.
    

|Characteristic|EC2-Classic|Default VPC|Nondefault VPC|
|--- |--- |--- |--- |
|Public IPv4 address (from Amazon's public IP address pool)|Your instance receives a public IPv4 address.|Your instance launched in a default subnet receives a public IPv4 address by default, unless you specify otherwise during launch, or you modify the subnet's public IPv4 address attribute.|Your instance doesn't receive a public IPv4 address by default, unless you specify otherwise during launch, or you modify the subnet's public IPv4 address attribute.|
|Private IPv4 address|Your instance receives a private IPv4 address from the EC2-Classic range each time it's started.|Your instance receives a static private IPv4 address from the address range of your default VPC.|Your instance receives a static private IPv4 address from the address range of your VPC.|
|Multiple private IPv4 addresses|We select a single private IP address for your instance; multiple IP addresses are not supported.|You can assign multiple private IPv4 addresses to your instance.|You can assign multiple private IPv4 addresses to your instance.|
|Elastic IP address (IPv4)|An Elastic IP is disassociated from your instance when you stop it.|An Elastic IP remains associated with your instance when you stop it.|An Elastic IP remains associated with your instance when you stop it.|
|DNS hostnames|DNS hostnames are enabled by default.|DNS hostnames are enabled by default.|DNS hostnames are disabled by default.|
|Security group|A security group can reference security groups that belong to other AWS accounts.  You can create up to 500 security groups in each region.|A security group can reference security groups for your VPC only.  You can create up to 100 security groups per VPC.|A security group can reference security groups for your VPC only.  You can create up to 100 security groups per VPC.|
|Security group association|You can assign an unlimited number of security groups to an instance when you launch it.  You can't change the security groups of your running instance. You can either modify the rules of the assigned security groups, or replace the instance with a new one (create an AMI from the instance, launch a new instance from this AMI with the security groups that you need, disassociate any Elastic IP address from the original instance and associate it with the new instance, and then terminate the original instance).|You can assign up to 5 security groups to an instance. You can assign security groups to your instance when you launch it and while it's running.|You can assign up to 5 security groups to an instance. You can assign security groups to your instance when you launch it and while it's running.|
|Security group rules|You can add rules for inbound traffic only. You can add up to 100 rules to a security group.|You can add rules for inbound and outbound traffic.  You can add up to 50 rules to a security group.|You can add rules for inbound and outbound traffic.  You can add up to 50 rules to a security group.|
|Tenancy|Your instance runs on shared hardware.|You can run your instance on shared hardware or single-tenant hardware.|You can run your instance on shared hardware or single-tenant hardware.|
|Accessing the Internet|Your instance can access the Internet. Your instance automatically receives a public IP address, and can access the Internet directly through the AWS network edge.|By default, your instance can access the Internet. Your instance receives a public IP address by default. An Internet gateway is attached to your default VPC, and your default subnet has a route to the Internet gateway.|By default, your instance cannot access the Internet. Your instance doesn't receive a public IP address by default. Your VPC may have an Internet gateway, depending on how it was created.|
|IPv6 addressing|IPv6 addressing is not supported. You cannot assign IPv6 addresses to your instances.|You can optionally associate an IPv6 CIDR block with your VPC, and assign IPv6 addresses to instances in your VPC.|You can optionally associate an IPv6 CIDR block with your VPC, and assign IPv6 addresses to instances in your VPC.|

    
