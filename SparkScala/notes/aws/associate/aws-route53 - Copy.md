# DNS 101

## Section 6 Lecture 47 (DNS 101)

### IPV4 vs IPV6:
* IPV4 is a 32 bit field and has over 4 billion different addresses. (4,294,967,296 to be precise)
* IPV6 was created to solve the depletion issue and has an address space of 128 bits which in theory is 340,282,366,920,938,463,463,374,607,431,768,211,456 addresses
or 340 undecillion addresses
* Support for IPV6 inside aws isn't very good at the moment.

### Top level domains:

* .com, .edu, .org, .gov are called top level domains . Other such as .gov.uk the second word .gov is the second level domain.
* These top level domains are controlled and maintained by Internet Assigned Numbers Authority (IANA) in a root zone database which is essentially a database of all available top level domains.
You can view this database by visiting
http://www.iana.org/domains/root/db

### Domain Registrars:

Because all of the names in a given domain name have to be unique there needs to be a way to organise this so that the domains names aren't duplicated. 
This is where the domain registrars come in. A registrar is an authority who can assign domain names directly under one or more top level domains.
These domain names are registered with InterNic a service of ICANN, which enforces uniqueness of domain names across the internet. 
Each domain name becomes registered in a central database known as WhoIS database. 
Popular domain registrars include GoDaddy.com, 123-reg.co.uk etc.

### SOA Records:

A start of aurhtority record stores information about:
* The name of the server that supplied the data for the Zone. (Find detailed description)
* The administrator of the Zone. this is where you can get the contact details from. So if you want to know who owns a domain name 
and you want their email address the SOA record would contain this.
* The current version of the data file
* The number of seconds a secondary name server should wait before checking for updates.
* The number of seconds a secondary name server should wait before retrying a failed zone transfer.
* The maximum number of seconds the secondary name server can use data before it must either be refreshed or expire.
* The default number of seconds for the time-to-live file on resource records.

### NS Records:
NS records stand for Name Server Records and are used by TOP level domain servers to direct traffic to the content 
DNS servers which contains the authroritative DNS record.

### Alias Records:
Alias records are used to map resource record sets in your hosted zone to Elastic Load Balancers, Cloud Front Distributions, 
or S3 buckets that are configured as websited.

Key difference: A cname cannot be used for naked domain names (Zone Apex). You can't have a cname for http://acloud.guru, 
it must be wither an A record or an Alias.

### Exam Tips:

* ELB's donot have a predefined ipv4 addresses, you resolve them using a dns name.
* Understand the difference between an Alias record and a CNAME record
* Given the choice, always choose an Alias rocord over a CNAME because when ever a query hits route 53 for name resolution you are going to be chared if you use a cname record but not if you use an Alias record. So 9 out of 10 time you should use Alias records.

## Section 6, Lecture 48 (Route53 - Lab)

Lab for binding an zone apex record such a aforce.guru with ELB dns name.
* Create a domain aforce.guru on Godaddy.
* Create a hosted zone on Route53 with name aforce.guru and mark it as a public hosted zone. You will get 4 name servers and an SOA record.
* Create an instance, install apache, create a load banalncer and add the instance behind a load balancer
* Add the name servers to the registered domain in godaddy.
* Add an alias record for the zone apex domain acloud.guru which should be an apex reocord. Select the load balancer dns name which appears in the text field below the Apex record. It should start with dualstack prefix. Keep the routing policy as simple.
* You can now browse your domain using aforce.guru

## Section 6, Lecture 49 (Route53 - Routing Policies)

* Simple
* Weighted
* Latency
* Failover
* Geo Location

- Simple: there's no intelligence buit in it at all and it is the default routing policy.

- Weighted: Weighted routing policies split your traffic according to the weights assigned. Suppose you want to send 20 percent of traffic to your infrasructure in US-EAST and 80 percent to US-West you can do it using wiighted routing policy. Which means we have a dns query from a user, it hits route 53, and route53 decides where to send the traffic. This technique can be used with different ALB's, regions etc. There could be a use case where you want to slowly migrate your traffic to a new website. 

For using weighted routing policy, you can add 2 records for website cname pointing to two different elbs. Select the routing policy as weighted and assign weights. You have the option to assign numbers between 0-255. How ever when you assign weights to both the records say 3 to the firstone and 1 to the seconds one, Route53 will add up both the weights in this case the sum is 4. So it will send 75% of traffic to the elb with weight 3 and 25% to the elb with weight 1.

- Latency: Latency based routing allows you to route your traffic based on the lowest network latency for your end user (ie the region which will give them the fastest response time). To use latency-based routing you create a latency resource record set for the Amazon EC2 (or ELB) resource in each region that hosts your website. When amazon Route53 receives a query for your site, it selects the latency resource record set for the region that gives the region the lowest latency. Route53 then responds with the value associated with that resource record set.


- Failover: Faiover Routing policies are used when you want to create an active/passive setup. For eg. you may want your primary site to be in US-West 1 and your secondary DR site in US-East-1. Route 53 will monitor the health of your site using health check. The health check monitors the health of your end points. For eg. if you have an availability zone outage and few of our instances are that availability zone, route53 will failover to your disaster recovery.

Route 53 will monitor the health of your site using healthchecks. You can also configure alarms for healthchecks.
Create one health check for your main production ELB on which the domain resolves and other for the domain itself.
Configure alarms for both.
If your main production setup is in london and the DR site is in Sydney, Once the health checks are configured map them against the elbs in different regions say an elb in sydney and elb in london with failover routing policy. As the london elb is primary one, while selecting the Failover record set type, check the primary box for this record. At the end you have to map the health check configured earlier. Keep in mind that you should map the health check created for the elb and not the one created for the domain with this Route 53 record or else you would be given an error message. In the same way map the domain record with DR elb in sydney, select the routing policy as Failover and Faliover Record type as Secondry. Leave rest all, as it is and you have configured a failover Setup. 

- Geo location Routing: Geo location routing lets you choose where your traffic will be sent based on the geographic location of your users (ie the location from which the DNS queries originate). For eg you might want all your queries from Europe to be routed to a fleet of EC2 instances that are specifically configured for your European Customers and all prices are displayed in Euros.


## Section 6, Lecture 51 (Weighted Routing Policy Lab)
Exam Tips

## Section 6, Lecture 50 (Route53 - Summary)
Exam Tips
