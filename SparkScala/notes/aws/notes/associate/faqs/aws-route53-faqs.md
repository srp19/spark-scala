Q. What is Amazon Route 53?

Amazon Route 53 provides highly available and scalable Domain Name System (DNS), domain name registration, and health-checking web services. It is designed to give developers and businesses an extremely reliable and cost effective way to route end users to Internet applications by translating names like example.com into the numeric IP addresses, such as 192.0.2.1, that computers use to connect to each other. You can combine your DNS with health-checking services to route traffic to healthy endpoints or to independently monitor and/or alarm on endpoints. 

Q. How does Amazon Route 53 provide high availability and low latency?

Route 53 is built using AWS’s highly available and reliable infrastructure. The globally distributed nature of our DNS servers helps ensure a consistent ability to route your end users to your application by circumventing any internet or network related issues. Route 53 is designed to provide the level of dependability required by important applications. Using a global anycast network of DNS servers around the world, Route 53 is designed to automatically answer queries from the optimal location depending on network conditions. As a result, the service offers low query latency for your end users.

Q. What is the difference between a Domain and a Hosted Zone?

A domain is a general DNS concept. Domain names are easily recognizable names for numerically addressed Internet resources. For example, amazon.com is a domain. A hosted zone is an Amazon Route 53 concept. A hosted zone is analogous to a traditional DNS zone file; it represents a collection of records that can be managed together, belonging to a single parent domain name. All resource record sets within a hosted zone must have the hosted zone’s domain name as a suffix. 

Q. What types of access controls can I set for the management of my Domains on Amazon Route 53?

You can control management access to your Amazon Route 53 hosted zone by using the AWS Identity and Access Management (IAM) service. 

Q. When is my hosted zone charged?

Hosted zones are billed once when they are created and then on the first day of each month.



###     Domain Name System (DNS)

Q. Does Amazon Route 53 use an anycast network?

Yes. Anycast is a networking and routing technology that helps your end users’ DNS queries get answered from the optimal Route 53 location given network conditions. As a result, your users get high availability and improved performance with Route 53.

Anycast addressing uses a one-to-nearest association; datagrams are routed to a single member of a group of potential receivers that are all identified by the same destination address.

Broadcast addressing uses a one-to-many association; datagrams are routed from a single sender to multiple endpoints simultaneously in a single transmission. The network automatically replicates datagrams as needed for all network segments (links) that contain an eligible receiver.

Multicast addressing uses a one-to-unique many association; datagrams are routed from a single sender to multiple selected endpoints simultaneously in a single transmission.

Unicast addressing uses a one-to-one association between destination address and network endpoint: each destination address uniquely identifies a single receiver endpoint.

Geocast refers to the delivery of information to a group of destinations in a network identified by their geographical locations. It is a specialized form of multicast addressing used by some routing protocols for mobile ad hoc networks.


Q. Is there a limit to the number of hosted zones I can manage using Amazon Route 53?

Each Amazon Route 53 account is limited to a maximum of 500 hosted zones and 10,000 resource record sets per hosted zone. Complete our request for a higher limit and we will respond to your request within two business days.

Q. Can I create multiple hosted zones for the same domain name? 

Yes. Creating multiple hosted zones allows you to verify your DNS setting in a “test” environment, and then replicate those settings on a “production” hosted zone. For example, hosted zone Z1234 might be your test version of example.com, hosted on name servers ns-1, ns-2, ns-3, and ns-4. Similarly, hosted zone Z5678 might be your production version of example.com, hosted on ns-5, ns-6, ns-7, and ns-8. Since each hosted zone has a virtual set of name servers associated with that zone, Route 53 will answer DNS queries for example.com differently depending on which name server you send the DNS query to.

Q. Which DNS record types does Amazon Route 53 support? 

Amazon Route 53 currently supports the following DNS record types:

* A
* AAAA
* CNAME 
* MX
* PTR
* SOA
* TXT
* SRV (Service Locator)
* SPF
* NS
* NAPTR (name authority pointer records)
* Alias Record

Q. Does Amazon Route 53 support wildcard entries? If so, what record types support them?

Yes. To make it even easier for you to configure DNS settings for your domain, Amazon Route 53 supports wildcard entries for all record types. A wildcard entry is a record in a DNS zone that will match requests for any domain name based on the configuration you set. For example, a wildcard DNS record such as *.example.com will match queries for www.example.com and subdomain.example.com.

Q. How quickly will changes I make to my DNS settings on Amazon Route 53 propagate globally? 

Amazon Route 53 is designed to propagate updates you make to your DNS records to its world-wide network of authoritative DNS servers within 60 seconds under normal conditions. A change is successfully propagated world-wide when the API call returns an INSYNC status listing.

Note that caching DNS resolvers are outside the control of the Amazon Route 53 service and will cache your resource record sets according to their time to live (TTL). The INSYNC or PENDING status of a change refers only to the state of Route 53’s authoritative DNS servers.

Q. Can I see a history of my changes and other operations on my Route 53 resources?

Yes, via AWS CloudTrail you can record and log the API call history for Route 53. 

Q. Does Amazon Route 53 support IPv6?

Yes. Amazon Route 53 supports both forward (AAAA) and reverse (PTR) IPv6 records. The Amazon Route 53 service itself is also available over IPv6. Recursive DNS resolvers on IPv6 networks can use either IPv4 or IPv6 transport in order to submit DNS queries to Amazon Route 53. Amazon Route 53 health checks also support monitoring of endpoints using the IPv6 protocol.

Q. Can I point my zone apex (example.com versus www.example.com) at my Elastic Load Balancer, S3 bucket hosted website, BeanStalk or cloudfront Distrubution?
Yes, Using Alias Records, Queries to Alias records that are mapped to ELB load balancers, S3 or cloudfrot are free. These queries are listed as “Intra-AWS-DNS-Queries” on the Amazon Route 53 usage report.

Q. When using Geo DNS, do I need a "global" record? When would Route 53 return this record?

Yes, we strongly recommend that you configure a global record, to ensure that Route 53 can provide a response to DNS queries from all possible locations—even if you have created specific records for each continent, country, or state where you expect your end users will be located. Route 53 will return the value contained in your global record in the following cases:

    The DNS query comes from an IP address not recognized by Route 53’s Geo IP database.
    The DNS query comes from a location not included in any of the specific Geo DNS records you have created.

Q. Can I have a Geo DNS record for a continent and different Geo DNS records for countries within that continent? Or a Geo DNS record for a country and Geo DNS records for states within that country?

Yes, you can have Geo DNS records for overlapping geographic regions (e.g., a continent and countries within that continent, or a country and states within that country). For each end user’s location, Route 53 will return the most specific Geo DNS record that includes that location. In other words, for a given end user’s location, Route 53 will first return a state record; if no state record is found, Route 53 will return a country record; if no country record is found, Route 53 will return a continent record; and finally, if no continent record is found, Route 53 will return the global record.

Q. What is the difference between Latency Based Routing and Geo DNS? 

Geo DNS bases routing decisions on the geographic location of the requests. In some cases, geography is a good proxy for latency; but there are certainly situations where it is not. LatencyBased Routing utilizes latency measurements between viewer networks and AWS datacenters. These measurements are used to determine which endpoint to direct users toward.

If your goal is to minimize end-user latency, we recommend using Latency Based Routing. If you have compliance, localization requirements, or other use cases that require stable routing from a specific geography to a specific endpoint, we recommend using Geo DNS.

### DNS Traffic Flow

Q. What is Amazon Route 53 Traffic Flow?

Amazon Route 53 Traffic Flow is an easy-to-use and cost-effective global traffic management service. With Amazon Route 53 Traffic Flow, you can improve the performance and availability of your application for your end users by running multiple endpoints around the world, using Amazon Route 53 Traffic Flow to connect your users to the best endpoint based on latency, geography, and endpoint health. Amazon Route 53 Traffic Flow makes it easy for developers to create policies that route traffic based on the constraints they care most about, including latency, endpoint health, load, and geography. Customers can customize these templates or build policies from scratch using a simple visual policy builder in the AWS Management Console.

Q. What is the difference between a traffic policy and a policy record?

A traffic policy is the set of rules that you define to route end users’ requests to one of your application’s endpoints. You can create a traffic policy using the visual policy builder in the Amazon Route 53 Traffic Flow section of the Amazon Route 53 console. You can also create traffic policies as JSON-formatted text files and upload these policies using the Route 53 API, the AWS CLI, or the various AWS SDKs.

By itself, a traffic policy doesn’t affect how end users are routed to your application, because it isn’t yet associated with your application’s DNS name (such as www.example.com). To start using Amazon Route 53 Traffic Flow to route traffic to your application using the traffic policy you’ve created, you create a policy record which associates the traffic policy with the appropriate DNS name within an Amazon Route 53 hosted zone that you own. For example, if you want to use a traffic policy that you’ve named my-first-traffic-policy to manage traffic for your application at www.example.com, you will create a policy record for www.example.com within your hosted zone example.com and choose my-first-traffic-policy as the traffic policy.

Policy records are visible in both the Amazon Route 53 Traffic Flow and Amazon Route 53 Hosted Zone sections of the Amazon Route 53 console.

Q. Can I use the same policy to manage routing for more than one DNS name?

Yes. You can reuse a policy to manage more than one DNS name in one of two ways. First, you can create additional policy records using the policy. Note that there is an additional charge for using this method, because you are billed for each policy record that you create.

The second method is to create one policy record using the policy, and then for each additional DNS name that you want to manage using the policy, you create a standard CNAME record pointing at the DNS name of the policy record that you created. 

Q. Can I create an Alias record pointing to a DNS name that is managed by a traffic policy?

No, it is not possible to create an Alias record pointing to a DNS name that is being managed by a traffic policy.

### Private DNS

Q. Can I use Amazon Route 53 to manage my organization’s private IP addresses?

Yes, you can manage private IP addresses within Virtual Private Clouds (VPCs) using Amazon Route 53’s Private DNS feature. With Private DNS, you can create a private hosted zone, and Route 53 will only return these records when queried from within the VPC(s) that you have associated with your private hosted zone.

Q. Can I still use Private DNS if I’m not using VPC?

No

Q. Can I associate VPCs and private hosted zones that I created under different AWS accounts?

Yes, you can associate VPCs belonging to different accounts with a single hosted zone. 

Q. Will Private DNS work across AWS regions?

Yes. DNS answers will be available within every VPC that you associate with the private hosted zone. Note that you will need to ensure that the VPCs in each region have connectivity with each other in order for resources in one region to be able to reach resources in another region.

Q. Can I use Private DNS to block domains and DNS names that I don’t want to be reached from within my VPC?

Yes, you can block domains and specific DNS names by creating these names in one or more Private DNS hosted zones and pointing these names to your own server (or another location that you manage).

### Health Checks & DNS Failover

Q. What is DNS Failover?

DNS Failover consists of two components: health checks and failover. Health checks are automated requests sent over the Internet to your application to verify that your application is reachable, available, and functional. You can configure the health checks to be similar to the typical requests made by your users, such as requesting a web page from a specific URL. With DNS failover, Route 53 only returns answers for resources that are healthy and reachable from the outside world, so that your end users are routed away from a failed or unhealthy part of your application.

Q. Does DNS Failover support Elastic Load Balancers (ELBs) as endpoints?

Yes, you can configure DNS Failover for Elastic Load Balancers (ELBs). To enable DNS Failover for an ELB endpoint, create an Alias record pointing to the ELB and set the “Evaluate Target Health” parameter to true. Route 53 creates and manages the health checks for your ELB automatically. You do not need to create your own Route 53 health check of the ELB. You also do not need to associate your resource record set for the ELB with your own health check, because Route 53 automatically associates it with the health checks that Route 53 manages on your behalf. The ELB health check will also inherit the health of your backend instances behind that ELB. 

Q. What DNS record types can I associate with Route 53 health checks?

You can associate any record type supported by Route 53 except SOA and NS records.

Q. How many consecutive health check observations does an endpoint need to fail to be considered “failed”?

The default is a threshold of three health check observations: when an endpoint has failed three consecutive observations, Route 53 will consider it failed. However, Route 53 will continue to perform health check observations on the endpoint and will resume sending traffic to it once it passes three consecutive observations. You can change this threshold to any value between 1 and 10 observations.

Q. How can I measure the performance of my application’s endpoints using Amazon Route 53?

Amazon Route 53 health checks include an optional latency measurement feature which provides data on how long it takes your endpoint to respond to a request. When you enable the latency measurement feature, the Amazon Route 53 health check will generate additional Amazon CloudWatch metrics showing the time required for Amazon Route 53’s health checkers to establish a connection and to begin receiving data. Amazon Route 53 provides a separate set of latency metrics for each AWS region where Amazon Route 53 health checks are conducted.

Q. How can I be notified if one of my endpoints starts failing its health check?

Because each Route 53 health check publishes its results as a CloudWatch metric, you can configure the full range of CloudWatch notifications and automated actions which can be triggered when the health check value changes beyond a threshold that you specify. First, in either the Route 53 or CloudWatch console, configure a CloudWatch alarm on the health check metric. Then add a notification action and specify the email or SNS topic that you want to publish your notification to.



Q. I’m using DNS Failover with Elastic Load Balancers (ELBs) as endpoints. How can I see the status of these endpoints?

The recommended method for setting up DNS Failover with ELB endpoints is to use Alias records with the "Evaluate Target Health" option. Because you don't create your own health checks for ELB endpoints when using this option, there are no specific CloudWatch metrics generated by Route 53 for these endpoints.

You can get metrics on the health of your load balancer in two ways. First, Elastic Load Balancing publishes metrics that indicate the health of the load balancer and the number of healthy instances behind it. For details on configuring CloudWatch metrics for ELB, consult the ELB developer guide. Second, you can create your own health check against the CNAME provided by the ELB, e.g. elb-example-123456678.us-west-2.elb.amazonaws.com. You won’t use this health check for DNS Failover itself (because the “Evaluate Target Health” option provides DNS Failover for you), but you can view the CloudWatch metrics for this health check and create alarms to be notified if the health check fails.

Q. For Alias records pointing to Amazon S3 Website buckets, what is being health checked when I set Evaluate Target Health to “true”?

Amazon Route 53 performs health checks of the Amazon S3 service itself in each AWS region. When you enable Evaluate Target Health on an Alias record pointing to an Amazon S3 Website bucket, Amazon Route 53 will take into account the health of the Amazon S3 service in the AWS region where your bucket is located. Amazon Route 53 does not check whether a specific bucket exists or contains valid website content; Amazon Route 53 will only fail over to another location if the Amazon S3 service itself is unavailable in the AWS region where your bucket is located.
### Domain Name Registration

Q. Can I register domain names with Amazon Route 53?

Yes.

Q. Does Route 53 offer privacy protection for domain names I have registered?

Yes, Route 53 provides privacy protection at no additional charge. The privacy protection hides your phone number, email address, and physical address. Your first and last name will be hidden if the TLD registry and registrar allow it. When you enable privacy protection, a Whois query for the domain will contain the registrar’s mailing address in place of your physical address, and the registrar’s name in place of your name (if allowed). Your email address will be a registrar-generated forwarding email address that third parties may use if they wish to contact you. Domain names registered by companies or organizations are eligible for privacy protection if the TLD registry and registrar allow it.

Q. What is Whois? Why is my information shown in Whois?

Whois is a publicly available database for domain names that lists the contact information and the name servers that are associated with a domain name. Anyone can access the Whois database by using the WHOIS command, which is widely available. It's included in many operating systems, and it's also available as a web application on many websites. The Internet Corporation for Assigned Names and Numbers (ICANN) requires that all domain names have publicly available contact information in case someone needs to get in contact with the domain name holder.






