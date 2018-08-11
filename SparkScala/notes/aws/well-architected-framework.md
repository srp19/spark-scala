### AWS Five Pillars of Well architected framework
* Security
* Reliability
* Performance Efficiency
* Cost Optimization
* Operational Excellence

### General Design Principiles to be followed in cloud
* Stop guessing your capacity needs
* Test systems at production scale
* Automate to make architectural experimentation easier.
* Allow for evolutionary Architecture.
* Data Driven architecture: In cloud you can collect data on how your architectural choices affect the behaviour of your workload. 
This lets you make fact based decisions on how to improve your workload.
* Improve through Game days: test how your architecture and processes perform by regularly scheduling gamedays to simulate events in production.

### Security Pillar
It includes the ability to protect information systems and assests while delivering business value through risk assessment and mitigation strategies.

#### Design Principles
* Apply security at all levels: Rather than running security appliances (eg. firewall) at the edge of your infrastructure, 
use firewalls and security controls on all of your resources (eg. loadbalancer, subnet, virtual server)
* Enable Traceability: Log and audit all actions and changes to your environment. 
* Implement a principle of least privilege: Ensure that authorization is appropriate for each interaction with your AWS resources and implement strong logical access controls directly on resources.
* Focus on securing your system:  With the AWS Shared Responsibility Model you can focus on securing your application, data, and operating
systems, while AWS provides secure infrastructure and services. 
* Automate Security Best Practises: Harden your OS. Create an entire trust zone architecture that is defined and managed in a template via revision control. Automate the response to both routine and
anomalous security events. 

#### Definition
These are five best practise areas for security in the cloud:
* Identity and access Management
* Detective Controls.
* Infrastructure Protection
* Data protection
* Incident response.

##### Identity and access Management

Identity and access management are key parts of an information security
program, ensuring that only authorized and authenticated users are able to
access your resources, and only in a manner that is intended. For example,
you’ll define principals (users, groups, services, and roles that take action in
your account), build out policies aligned with these principals, and implement
strong credential management. These privilege-management elements form the
core concepts of authentication and authorization.

Use AWS IAM to create users, roles, groups and policies to provide restricted access.

*Questions*

```
SEC 1. How are you protecting access to and use of the AWS
root account credentials?
SEC 2. How are you defining roles and responsibilities of
system users to control human access to the AWS
Management Console and API?
SEC 3. How are you limiting automated access to AWS
resources? (e.g., applications, scripts, and/or third-party
tools or services)
```

*Answer*
```
It is critical to keep root account credentials protected, and to this end AWS
recommends attaching MFA to the root account and locking the credentials with
the MFA in a physically secured location. The IAM service allows you to create
and manage other non-root user permissions, as well as establish access levels
to resources.
```

##### Detective Controls
You can use detective controls to identify a potential security incident. 

*Question*
```
SEC 4. How are you capturing and analyzing logs?
```

*Answer*
```
In AWS you can implement detective controls by processing logs, events and
monitoring that allows for auditing, automated analysis, and alarming. AWS
CloudTrail logs, AWS API calls, and Amazon CloudWatch provide monitoring of
metrics with alarming, and AWS Config provides configuration history. Service
level logs are also available, for example you can use Amazon Simple Storage
Service (S3) to log access requests. Finally Amazon Glacier provides a vault lock
feature to preserve mission-critical data with compliance controls designed to
support auditable long-term retention.

Do you have cloud trail turned on each region that your operating, because cloud trail is a region wise service. Are you using IDS and IPS tools, are you using any log management service such as loggly.

```

##### Infrastructure Protection
Infrastructure protection includes control methodologies, such as defense in
depth and multi-factor authentication, which are necessary to meet best
practices and industry or regulatory obligations

*Questions*
```
SEC 5. How are you enforcing network and host-level
boundary protection?
SEC 6. How are you leveraging AWS service level security
features?
SEC 7. How are you protecting the integrity of the operating
systems on your Amazon EC2 instances?
```

*Answers*
```
In AWS, you can implement stateful and stateless packet inspection, either by
using AWS native technologies or by using partner products and services
available through the AWS Marketplace. You can also use Amazon Virtual
Private Cloud (VPC), to create a private, secured, and scalable environment in
which you can define your topology—including gateways, routing tables, and
public and/or private subnets.

AWS customers are able to
tailor, or harden, the configuration of an EC2 instance, and persist this
configuration to an immutable Amazon Machine Image (AMI). Then, whether
triggered by Auto Scaling or launched manually, all new virtual servers
(instances) launched with this AMI receive the hardened configuration. 
```

##### Data Protection

For example, data classification provides a way to categorize
organizational data based on levels of sensitivity and encryption protects data
by rendering it unintelligible to unauthorized access. 

In AWS, the following practices facilitate protection of data:
* AWS customers maintain full control over their data.
* AWS makes it easier for you to encrypt your data and manage keys, including regular key rotation, which can be easily automated natively by AWS or maintained by a customer.
* Detailed logging that contains important content, such as file access and changes, is available.
* AWS has designed storage systems for exceptional resiliency. As an example, Amazon Simple Storage Service (S3) is designed for 11 nines of
durability. (For example, if you store 10,000 objects with Amazon S3, you can on average expect to incur a loss of a single object once every
10,000,000 years.)
* Versioning, which can be part of a larger data lifecycle management process, can protect against accidental overwrites, deletes, and similar
harm.
* AWS never initiates the movement of data between regions. Content placed in a region will remain in that region unless the customer explicitly enable a feature or leverages a service that provides that
functionality.

*Questions*
```
SEC 8. How are you classifying your data?
SEC 9. How are you encrypting and protecting your data at
rest?
SEC 10. How are you managing keys?
SEC 11. How are you encrypting and protecting your data in
transit?
```

*Answers*
```
Before you begin to architect security practises across your environment basic data classification should be in place. You should classify
your data into segments such as publicly available, available to only members of your organization, available to only board members etc.
You should also implement a least privilege system so that people are only able to access what you need.
AWS provides multiple means for encryption of data at rest and in transit. We
build features into our products and services that make it easier to encrypt your
data. For example, we have implemented Server Side Encryption (SSE)
for Amazon S3 to make it easier for you to store your data in an encrypted form.
You can also arrange for the entire HTTPS encryption and decryption process
(generally known as SSL termination) to be handled by Elastic Load Balancing.
```

##### Incident Response

Even with extremely mature preventive and detective controls, organizations
should still put processes in place to respond to and mitigate the potential
impact of security incidents. The architecture of your workload will strongly
affect the ability of your teams to operate effectively during an incident to
isolate or contain systems and to restore operations to a known-good state.
Putting in place the tools and access ahead of a security incident, then routinely
practicing incident response will make sure the architecture is updated to
accommodate timely investigation and recovery.
In AWS, the following practices facilitate effective incident response:
* Detailed logging is available that contains important content, such as file access and changes.
* Events can be automatically processed and trigger scripts that automate run books through the use of AWS APIs.
* You can pre-provision tooling and a “clean room” using AWS CloudFormation. This allows you to carry out forensics in a safe, isolated environment.

*Questions*

```
SEC 12. How do you ensure you have the appropriate
incident response?
Ensure that you have a way to quickly grant access for your InfoSec team, and
automate the isolation of instances as well at the capturing of data and state for
forensics.
```

*Answers*
```
Ensure that you have a way to quickly grant access for your InfoSec team, and
automate the isolation of instances as well at the capturing of data and state for
forensics.
```

*Key AWS Services*

The AWS service that is essential to security is AWS Identity and Access
Management (IAM), which allows you to securely control access to AWS
services and resources for your users. The following services and features
support the four areas of security:
* Identity and access management: IAM enables you to securely control
access to AWS services and resources. Multi-factor authentication (MFA), adds
an extra layer of protection on top of your user name and password.
* Detective controls: AWS CloudTrail records AWS API calls, AWS Config
provides a detailed inventory of your AWS resources and configuration, and
Amazon CloudWatch is a monitoring service for AWS resources.
* Infrastructure protection: Amazon Virtual Private Cloud (VPC) lets you
provision a private, isolated section of the AWS Cloud where you can launch
AWS resources in a virtual network.
* Data protection: Services such as Elastic Load Balancing, Amazon Elastic Block Store (EBS), Amazon Simple Storage Service (S3), and Amazon Relational Database Service (RDS) include encryption capabilities to protect your data in transit and at rest. AWS Key Management Service (KMS) makes it easier for customers to create and control keys used for encryption.
* Incident response: IAM should be used to grant appropriate authorization to incident response teams. Amazon CloudFormation can be used to create a trusted environment for conducting investigations.

### Reliability Pillar
It includes the ability of the system to recover from or sustain inrastructure and service failures, dynamically acquiring computing resources to meet demand and mitigate disruptions such as misconfigurations or transient network issues.

#### Design Principles

* Test recovery procedures: Cloud platform enables for testing recovery procedures by simulating failure scenarios through automation.
* Automatically recover from failures: By monitoring a system for key performance indicators (KPIs), you can trigger automation when a threshold is breached.
* Scale horizontally to increase aggregate system availability:
* Stop guessing capacity
* Manage change in automation: Changes to your infrastructure should be done using automation. The changes that need to be managed are changes to the automation.

#### Definition

Three best practises for reliability in the cloud:

* Foundation
* Change Management
* Failure Management

##### Foundation

One example of foundation is that you must have sufficient network bandwidth to your data center. This neglect can have a significant impact on the ability to deliver a reliable system. In an on-premises environment, these requirements can cause long lead times due to dependencies and therefore must be incorporated during initial planning.

The cloud is designed to be essentially limitless, so it is the responsibility of AWS to satisfy the requirement for sufficient
networking and compute capacity, while you are free to change resource size and allocation, such as the size of storage devices, on demand.

*Questions*
```
REL 1. How do you manage AWS service limits for your
accounts?
REL 2. How are you planning your network topology on
AWS?
```

*Answers*
```
AWS sets service limits (an upper limit on the number of each resource your
team can request) to protect you from accidently over-provisioning resources.
You will need to have governance and processes in place to monitor and change
these limits to meet your business needs. As you adopt the cloud, you may need
to plan integration with existing on-premises resources (a hybrid approach). A
hybrid model enables the gradual transition to an all-in cloud approach over 
Amazon Web Services – AWS Well-Architected Framework
Page 15
time, and therefore it’s important to have a design for how your AWS and onpremises
resources will interact as a network topology
```

##### Change Management

Being aware of how change affects a system allows you to plan proactively, and monitoring allows you to quickly identify trends that could lead to capacity issues or SLA breaches.

*Questions*
```
REL 3. How does your system adapt to changes in demand?
REL 4. How are you monitoring AWS resources?
REL 5. How are you executing change?
```

*Answers*
```
When you architect a system to automatically add and remove resources in response to changes in demand, this not only increases reliability but also ensures that business success does not become a burden. With monitoring in place, your team will be automatically alerted when KPIs deviate from expected norms. Automatic logging of changes to your environment allows you to audit and quickly identify actions that might have impacted reliability. Controls on change management ensure that you can enforce the rules that deliver the
reliability you need. 

Using AWS, you can monitor the behavior of a system and automate the response to KPIs, for example, adding additional servers as a system gains more users. You can control who has permission to make system changes and audit the history of these changes.
```

##### Failure Management

In any system of reasonable complexity it is expected that failures will occur, and it is generally of interest to know how to become aware of these failures, respond to them, and prevent them from happening again.

In AWS, we can take advantage of automation to react to monitoring data. For example, when a particular metric crosses a threshold, you can trigger an automated action to remedy the problem. Also, rather than trying to diagnose and fix a failed resource that is part of your production environment, you can replace it with a new one and carry out the analysis on the failed resource out of
band. Since the cloud enables you to stand up temporary versions of a whole system at low cost, you can use automated testing to verify full recovery processes.

*Questions*

```
REL 6. How are you backing up your data?
REL 7. How does your system withstand component
failures?
REL 8. How are you testing for resiliency?
REL 9. How are you planning for disaster recovery?
```

*Answers*
```
Regularly back up your data, and test your backup files, to ensure you can recover from both logical and physical errors. A key to managing failure is the frequent, and automated testing of systems to failure and through recovery (ideally on a regular schedule and also triggered after significant system changes). Actively track KPIs, such as the recovery time objective (RTO) and
recovery point objective (RPO), to assess a system’s resiliency (especially under failure-testing scenarios). Tracking KPIs will help you identify and mitigate single points of failure. The objective is to thoroughly test your system-recovery processes so that you are confident that you can recover all your data and continue to serve your customers, even in the face of sustained problems. Your
recovery processes should be as well exercised as your normal production processes
```

*Key AWS Services*

The AWS service that is key to ensuring reliability is Amazon CloudWatch,
which monitors run-time metrics. Other services and features that support the
three areas of reliability are as follows:
* Foundations: AWS Identity and Access Management (IAM) enables you to securely control access to AWS services and resources. Amazon VPC lets you provision a private, isolated section of the AWS Cloud where you can launch AWS resources in a virtual network.
* Change management: AWS CloudTrail records AWS API calls for your account and delivers log files to you for auditing. AWS Config provides a detailed inventory of your AWS resources and configuration, and continuously records configuration changes.
* Failure management: AWS CloudFormation provides templates for the creation of AWS resources and provisions them in an orderly and predictable fashion.

### Performance Efficiency

The Performance Efficiency pillar focuses on the efficient use of computing resources to meet requirements and maintaining that efficiency as demand changes and technologies evolve.

#### Design Principles

In the cloud, there are a number of principles that can help you achieve performance efficiency:

* Democratize advanced technologies: Technologies that are difficult to implement can become easier to consume by pushing that knowledge
and complexity into the cloud vendor’s domain. Rather than having your IT team learn how to host and run a new technology, they can simply consume it as a service. For example, NoSQL databases, media transcoding, and machine learning are all technologies that require
expertise that is not evenly dispersed across the technical community. In the cloud, these technologies become services that your team can consume while focusing on product development rather than resource provisioning and management.

* Go global in minutes: Easily deploy your system in multiple regions around the world with just a few clicks. This allows you to provide lower latency and a better experience for your customers at minimal cost.

* Use serverless architectures: In the cloud, server-less architectures remove the need for you to run and maintain servers to carry out traditional compute activities. For example, storage services can act as static websites, removing the need for web servers; and event services can host your code for you. This not only removes the operational burden of managing these servers, but also can lower transactional costs because these managed services operate at cloud scale.

* Experiment more often: With virtual and automatable resources, you can quickly carry out comparative testing using different types of
instances, storage, or configurations.

* Mechanical sympathy: Use the technology approach that aligns best to what you are trying to achieve. For example consider data access
patterns when selecting database or storage approaches.

#### Definition

There are four best practice areas for Performance Efficiency in the cloud:
* Selection (compute, database, storage, network)
* Monitoring
* Review
* Tradeoff

##### Selection

*Questions*

```
PERF 1. How do you select the best performing architecture?
PERF 2. How do you select your compute solution?
PERF 3. How do you select your storage solution?
PERF 4. How do you select your database solution?
PERF 5. How do you select your network solution?
```

*Answers*

```
Your architecture will likely combine a number of different architectural
approaches (e.g., event driven, ETL, or pipeline). The implementation of your
architecture will use the AWS services that are specific to the optimization of
your architecture’s performance

Compute

In AWS, compute is available in three forms: instances, containers, and
functions:
* Instances are virtualized servers and, therefore, you can change their capabilities with the click of a button or an API call.
* Containers are a method of operating system virtualization that allow you to run an application and its dependencies in resource-isolated processes.
* Functions abstract the execution environment from the code you want to execute. For example, AWS Lambda allows you to execute code
without running an instance. 

Storage

The optimal storage solution for a particular system will vary based on the kind
of access method (block, file, or object), patterns of access (random or
sequential), throughput required, frequency of access (online, offline, archival),
frequency of update (WORM, dynamic), and availability and durability
constraints. Well-architected systems use multiple storage solutions and enable
different features to improve performance

Database

Selecting the wrong database solution and features for a system
can lead to lower performance efficiency.
In AWS, Amazon Relational Database Service (RDS) provides a fully managed
relational database. With Amazon RDS you can scale your database's compute
and storage resources, often with no downtime. Amazon DynamoDB is a fully
managed NoSQL database that provides single-digit millisecond latency at any
scale. Amazon Redshift is a managed petabyte-scale data warehouse that allows
you to change the number or type of nodes as your performance or capacity
needs change.

Network

In AWS, networking is virtualized and is available in a number of different types
and configurations. This makes it easier to match your networking methods
more closely with your needs. AWS offers product features (e.g., very high
network instance types, Amazon EBS optimized instances, Amazon S3 transfer
acceleration, dynamic Amazon CloudFront) to optimize network traffic. AWS
also offers networking features (e.g., Amazon Route53 latency routing, Amazon
VPC endpoints, and AWS Direct Connect) to reduce network distance or jitter.

When you select your network solution, you need to consider location. With
AWS you can choose to place resources close to where they will be used to
reduce distance. By taking advantage of regions, placement groups, and edge
locations you can significantly improve performance.

```

##### Review

When architecting solutions, there is a finite set of options that you can choose
from. However, over time new technologies and approaches become available
that could improve the performance of your architecture.
Using AWS, you can take advantage of our continual innovation, which is driven
by customer need. We release new regions, edge location, services, and features
regularly. Any of these could positively improve the performance efficiency of
your architecture.

*Questions*
```
PERF 6. How do you ensure that you continue to have the most
appropriate resource type as new resource types and features
are introduced?
```
Understanding where your architecture is performance constrained will allow
you to look out for releases that could alleviate that constraint.

##### Monitoring

Once you have implemented your architecture you will need to monitor its performance so that you can remediate any issues before your customers are aware.

*Questions*
```
PERF 7. How do you monitor your resources post-launch to
ensure they are performing as expected?
```

*Answers*
```
Using AWS, Amazon CloudWatch provides the ability to monitor and send notification alarms, and you can use automation to work around performance issues by triggering actions through Amazon Kinesis, Amazon Simple Queue
Service (SQS), and AWS Lambda.

Ensuring that you do not see too many false positives, or are overwhelmed with data, is key to having an effective monitoring solution. Automated triggers avoid human error and can reduce the time to fix problems. Plan for “game days” where simulations are conducted in the production environment, to test your alarming solution and ensure that it correctly recognizes issues.
```

##### Tradeoffs

When you architect solutions, think about trade-offs so you can select an optimal approach. Depending on your situation you could trade consistency, durability, and space versus time or latency, to deliver higher performance.

*Questions*
```
PERF 8. How do you use tradeoffs to improve performance?
```

*Answers*
```
Using AWS, you can go global in minutes and deploy resources in multiple locations across the globe to be closer to your end users. You can also dynamically add read-only replicas to information stores such as database systems to reduce the load on the primary database. AWS also offers caching solutions such as Amazon ElastiCache, which provides an in-memory data store or cache, and Amazon CloudFront, which caches copies of your static content closer to end-users. 
```

*Key AWS Services*

The key AWS service for performance efficiency is Amazon CloudWatch, which
monitors your resources and systems, providing visibility into your overall
performance and operational health. The following services are important in the
areas of performance efficiency:
* Selection:
 * Compute: Auto Scaling is key to ensuring that you have enough instances to meet demand and maintain responsiveness.
 * Storage: Amazon EBS provides a wide range of storage options (such as SSD and provisioned input/output operations per second (PIOPS)) that allow you to optimize for your use case. Amazon S3 provides serverless content delivery and Amazon S3 Transfer Acceleration enables fast, easy,and secure transfers of files over long distances.
 * Database: Amazon RDS provides a wide range of database features (such as provisioned IOPS and read replicas) that allow you to optimize for your use case. Amazon DynamoDB provides single-digit millisecond latency at any scale.
 * Network: Amazon Route 53 provides latency-based routing. Amazon VPC endpoints and Direct Connect can reduce network distance or jitter.
* Review: The AWS Blog and the What’s New section on the AWS website are resources for learning about newly launched features and services.
* Monitoring: Amazon CloudWatch provides metrics, alarms, and notifications that you can integrate with your existing monitoring solution, and that you can use with AWS Lambda to trigger actions.
* Tradeoff: Amazon ElastiCache, Amazon CloudFront, and AWS Snowball are services that allow you to improve performance. Read replicas in Amazon RDS can allow you to scale read-heavy workloads

### Cost Optimization

The Cost Optimization pillar includes the continual process of refinement and improvement of a system over its entire lifecycle. 

#### Design Principles
* Adopt a consumption model
* Benefit from economies of Scale
* Stop spending money on Data center operations
* Analyze and attribute expenditure
* Use managed services to reduce cost of Ownership

There are four best practise areas for cost optimization in cloud
* Cost effective resources
* Match Supply and demand
* Expenditure Awareness
* Optimizing over time

##### Cost-Effective Resources
Using the appropriate instances and resources for your system is key to cost
savings. For example, a reporting process might take five hours to run on a
smaller server, but a larger server that is twice as expensive can do it in one
hour. Both jobs give you the same outcome, but the smaller server will incur
more cost over time. 

*Questions*

```
COST 1. Are you considering cost when you select AWS
services for your solution?
COST 2. Have you sized your resources to meet your cost
targets?
COST 3. Have you selected the appropriate pricing model to
meet your cost targets?
```

*Answers*

```
By using tools such as AWS Trusted Advisor to regularly review your AWS
usage, you can actively monitor your utilization and adjust your deployments
accordingly
```

##### Matching Supply and Demand 

*Questions*

```
COST 4. How do you make sure your capacity matches but
does not substantially exceed what you need?

```

*Answers*
```
When architecting to match supply against demand, you will want to actively think about the patterns of usage and the time it takes to provision new resources. In AWS, you can automatically provision resources to match demand. Auto Scaling and demand, buffer, and time based approaches allow you to add and remove resources as needed.
```

##### Expenditure Awareness

*Questions*
```
COST 5. Did you consider data-transfer charges when
designing your architecture?
COST 6. How are you monitoring usage and spending?
COST 7. Do you decommission resources that you no longer
need or stop resources that are temporarily not needed?
COST 8. What access controls and procedures do you have in
place to govern AWS usage?
```

*Answers*
```
You can use cost allocation tags to categorize and track your AWS costs. When
you apply tags to your AWS resources (such as Amazon EC2 instances or  Amazon S3 buckets), AWS generates a cost allocation report with your usage and costs aggregated by your tags. You can apply tags that represent business categories (such as cost centers, system names, or owners) to organize your costs across multiple services. Combining tagged resources with entity lifecycle tracking (employees, projects) makes it possible to identify orphaned resources or projects that are no longer generating value to the business and should be decommissioned. You can set up billing alerts to notify you of predicted overspending, and the AWS Simple Monthly Calculator allows you to calculate your data transfer costs. 
```

##### Optimizing Over Time

As AWS releases new services and features, it is a best practice to review your
existing architectural decisions to ensure they continue to be the most costeffective.
As your requirements change, be aggressive in decommissioning
resources and entire services, or systems that you no longer require. 

*Questions*
```
COST 9. How do you manage and/or consider the adoption of new services?
```

*Answers*
```
By regularly reviewing your deployments, it is often possible to utilize new AWS services to lower your costs. Also, assess how newer services can help save you money. For example, AWS RDS for Aurora could help you reduce costs for relational databases.
```

*Key AWS Services*

The key AWS feature that supports cost optimization is cost allocation tags,
which help you to understand the costs of a system. The following services and
features are important in the four areas of cost optimization:
* Cost-effective resources: You can use Reserved Instances and prepaid capacity to reduce your cost. AWS Trusted Advisor can be used to inspect your AWS environment and find opportunities to save money.
* Matching supply and demand: Auto Scaling allows you to add or remove resources to match demand without overspending.
* Expenditure awareness: Amazon CloudWatch alarms and Amazon Simple Notification Service (SNS) notifications will warn you if you go over, or are forecasted to go over, your budgeted amount.
* Optimizing over time: The AWS Blog and the What’s New section on the AWS website are resources for learning about newly launched features and services. AWS Trusted Advisor inspects your AWS environment and finds opportunities to save money by eliminating unused or idle resources or committing to Reserved Instance capacity. 

### Operational Excellence Pillar

The Operational Excellence pillar includes operational practices and procedures used to manage production workloads. This includes how planned changes are executed, as well as responses to unexpected operational events.
Change execution and responses should be automated.

#### Design Principles

* Perform operations with code.
* Align operations processes to business objectives.
* Make regular, small, incremental changes.
* Test for responses to unexpected events.
* Learn from operational events and failures.
* Keep operations procedures current.

#### Definition

* Preparation
* Operations
* Responses




