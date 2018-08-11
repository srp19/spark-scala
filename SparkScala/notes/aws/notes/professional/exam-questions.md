## Cloudformation

#### You need to deploy an AWS stack in a repeatable manner across multiple environments. You have selected CloudFormation as the right tool to accomplish this, but have found that there is a resource type you need to create and model, but is unsupported by CloudFormation. How should you overcome this challenge?

  * Use a CloudFormation Custom Resource Template by selecting an API call to proxy for create, update, and delete actions. CloudFormation will use the AWS SDK, CLI, or API method of your choosing as the state transition function for the resource type you are modeling.
  * Submit a ticket to the AWS Forums. AWS extends CloudFormation Resource Types by releasing tooling to the AWS Labs organization on GitHub. Their response time is usually 1 day, and they complete requests within a week or two.
  * Instead of depending on CloudFormation, use Chef, Puppet, or Ansible to author Heat templates, which are declarative stack resource definitions that operate over the OpenStack hypervisor and cloud environment.
  * **Create a CloudFormation Custom Resource Type by implementing create, update, and delete functionality, either by subscribing a Custom Resource Provider to an SNS topic, or by implementing the logic in AWS Lambda.**

#### Your company needs to automate 3 layers of a large cloud deployment. You want to be able to track this deployment’s evolution as it changes over time, and carefully control any alterations. What is a good way to automate a stack to meet these requirements?

  * Use OpsWorks Stacks with three layers to model the layering in your stack.
  * **Use CloudFormation Nested Stack Templates, with three child stacks to represent the three logical layers of your cloud.** (CloudFormation allows source controlled, declarative templates as the basis for stack automation and Nested Stacks help achieve clean separation of layers while simultaneously providing a method to control all layers at once when needed)
  * Use AWS Config to declare a configuration set that AWS should roll out to your cloud.
  * Use Elastic Beanstalk Linked Applications, passing the important DNS entries between layers using the metadata interface.

#### You have been asked to de-risk deployments at your company. Specifically, the CEO is concerned about outages that occur because of accidental inconsistencies between Staging and Production, which sometimes cause unexpected behaviors in Production even when Staging tests pass. You already use Docker to get high consistency between Staging and Production for the application environment on your EC2 instances. How do you further de-risk the rest of the execution environment, since in AWS, there are many service components you may use beyond EC2 virtual machines?

  * **Develop models of your entire cloud system in CloudFormation. Use this model in Staging and Production to achieve greater parity.** (Only CloudFormation’s JSON Templates allow declarative version control of repeatedly deployable models of entire AWS clouds. Refer link)
  * Use AWS Config to force the Staging and Production stacks to have configuration parity. Any differences will be detected for you so you are aware of risks.
  * Use AMIs to ensure the whole machine, including the kernel of the virual machines, is consistent, since Docker uses Linux Container (LXC) technology, and we need to make sure the container environment is consistent.
  * Use AWS ECS and Docker clustering. This will make sure that the AMIs and machine sizes are the same across both environments.

## Elastic BeanStalk

#### Your team has a tomcat-based Java application you need to deploy into development, test and production environments. After some research, you opt to use Elastic Beanstalk due to its tight integration with your developer tools and RDS due to its ease of management. Your QA team lead points out that you need to roll a sanitized set of production data into your environment on a nightly basis. Similarly, other software teams in your org want access to that same restored data via their EC2 instances in your VPC .The optimal setup for persistence and security that meets the above requirements would be the following.

  * Create your RDS instance as part of your Elastic Beanstalk definition and alter its security group to allow access to it from hosts in your application subnets. (Not optimal for persistence as the RDS is associated with the Elastic Beanstalk lifecycle and would not live independently)
  * Create your RDS instance separately and add its IP address to your application’s DB connection strings in your code. Alter its security group to allow access to it from hosts within your VPC’s IP address block. (RDS is connected using DNS endpoint only)
  * **Create your RDS instance separately and pass its DNS name to your app’s DB connection string as an environment variable. Create a security group for client machines and add it as a valid source for DB traffic to the security group of the RDS instance itself.** (Security group allows instances to access the RDS with new instances launched without any changes)
  * Create your RDS instance separately and pass its DNS name to your DB connection string as an environment variable. Alter its security group to allow access to it from hosts in your application subnets. (Not optimal for security adding individual hosts)

#### Your must architect the migration of a web application to AWS. The application consists of Linux web servers running a custom web server. You are required to save the logs generated from the application to a durable location. What options could you select to migrate the application to AWS? (Choose 2)

  * Create an AWS Elastic Beanstalk application using the custom web server platform. Specify the web server executable and the application project and source files. Enable log file rotation to Amazon Simple Storage Service (S3). (EB does not work with Custom server executable)
  * Create Dockerfile for the application. Create an AWS OpsWorks stack consisting of a custom layer. Create custom recipes to install Docker and to deploy your Docker container using the Dockerfile. Create custom recipes to install and configure the application to publish the logs to Amazon CloudWatch Logs (although this is one of the option, the last sentence mentions configure the application to push the logs to S3, which would need changes to application as it needs to use SDK or CLI) 
  * Create Dockerfile for the application. Create an AWS OpsWorks stack consisting of a Docker layer that uses the Dockerfile. Create custom recipes to install and configure Amazon Kinesis to publish the logs into Amazon CloudWatch. (Kinesis not needed)
  * **Create a Dockerfile for the application. Create an AWS Elastic Beanstalk application using the Docker platform and the Dockerfile. Enable logging the Docker configuration to automatically publish the application logs. Enable log file rotation to Amazon S3. (Use Docker configuration with awslogs and EB with Docker)**
  * **Use VM import/Export to import a virtual machine image of the server into AWS as an AMI. Create an Amazon Elastic Compute Cloud (EC2) instance from AMI, and install and configure the Amazon CloudWatch Logs agent. Create a new AMI from the instance. Create an AWS Elastic Beanstalk application using the AMI platform and the new AMI. (Use VM Import/Export to create AMI and CloudWatch logs agent to log)**

## EMR

#### You require the ability to analyze a large amount of data, which is stored on Amazon S3 using Amazon Elastic Map Reduce. You are using the cc2.8xlarge instance type, who’s CPUs are mostly idle during processing. Which of the below would be the most cost efficient way to reduce the runtime of the job? [PROFESSIONAL]
  * Create smaller files on Amazon S3.
  * Add additional cc2.8xlarge instances by introducing a task group.
  * **Use smaller instances that have higher aggregate I/O performance.**
  * Create fewer, larger files on Amazon S3.

#### A customer’s nightly EMR job processes a single 2-TB data file stored on Amazon Simple Storage Service (S3). The Amazon Elastic Map Reduce (EMR) job runs on two On-Demand core nodes and three On-Demand task nodes. Which of the following may help reduce the EMR job completion time? Choose 2 answers

  * Use three Spot Instances rather than three On-Demand instances for the task nodes.
  * **Change the input split size in the MapReduce job configuration.**
  * Use a bootstrap action to present the S3 bucket as a local filesystem.
  * Launch the core nodes and task nodes within an Amazon Virtual Cloud.
  * **Adjust the number of simultaneous mapper tasks.**

#### Your department creates regular analytics reports from your company’s log files. All log data is collected in Amazon S3 and processed by daily Amazon Elastic Map Reduce (EMR) jobs that generate daily PDF reports and aggregated tables in CSV format for an Amazon Redshift data warehouse. Your CFO requests that you optimize the cost structure for this system. Which of the following alternatives will lower costs without compromising average performance of the system or data integrity for the raw data?

  * Use reduced redundancy storage (RRS) for PDF and CSV data in Amazon S3. Add Spot instances to Amazon EMR jobs. Use Reserved Instances for Amazon Redshift. (Only Spot instances impacts performance)
  * **Use reduced redundancy storage (RRS) for all data in S3. Use a combination of Spot instances and Reserved Instances for Amazon EMR jobs. Use Reserved instances for Amazon Redshift** (Combination of the Spot and reserved with guarantee performance and help reduce cost. Also, RRS would reduce cost and guarantee data integrity, which is different from data durability)
  * Use reduced redundancy storage (RRS) for all data in Amazon S3. Add Spot Instances to Amazon EMR jobs. 
  * Use Reserved Instances for Amazon Redshift (Only Spot instances impacts performance)
  * Use reduced redundancy storage (RRS) for PDF and CSV data in S3. Add Spot Instances to EMR jobs. Use Spot Instances for Amazon Redshift. (Spot instances impacts performance and Spot instance not available for Redshift)

#### A research scientist is planning for the one-time launch of an Elastic MapReduce cluster and is encouraged by her manager to minimize the costs. The cluster is designed to ingest 200TB of genomics data with a total of 100 Amazon EC2 instances and is expected to run for around four hours. The resulting data set must be stored temporarily until archived into an Amazon RDS Oracle instance. Which option will help save the most money while meeting requirements?
Store ingest and output files in Amazon S3. Deploy on-demand for the master and core nodes and spot for the task nodes.
  * **Store ingest and output files in Amazon S3. Deploy on-demand for the master and core nodes and spot for the task nodes.** 
  * Optimize by deploying a combination of on-demand, RI and spot-pricing models for the master, core and task nodes. Store ingest and output files in Amazon S3 with a lifecycle policy that archives them to Amazon Glacier. (Master and Core must be RI or On Demand. Cannot be Spot)
  * Store the ingest files in Amazon S3 RRS and store the output files in S3. Deploy Reserved Instances for the master and core nodes and on-demand for the task nodes. (Need better durability for ingest file. Spot instances can be used for task nodes for cost saving. RI will not provide cost saving in this case)
  * Deploy on-demand master, core and task nodes and store ingest and output files in Amazon S3 RRS (Input should be in S3 standard, as re-ingesting the input data might end up being more costly then holding the data for limited time in standard S3)

#### Your company sells consumer devices and needs to record the first activation of all sold devices. Devices are not activated until the information is written on a persistent database. Activation data is very important for your company and must be analyzed daily with a MapReduce job. The execution time of the data analysis process must be less than three hours per day. Devices are usually sold evenly during the year, but when a new device model is out, there is a predictable peak in activation’s, that is, for a few days there are 10 times or even 100 times more activation’s than in average day. Which of the following databases and analysis framework would you implement to better optimize costs and performance for this workload?

  * Amazon RDS and Amazon Elastic MapReduce with Spot instances.
  * Amazon DynamoDB and Amazon Elastic MapReduce with Spot instances.
  * Amazon RDS and Amazon Elastic MapReduce with Reserved instances.
  * **Amazon DynamoDB and Amazon Elastic MapReduce with Reserved instances**

## Directory Services

#### The majority of your Infrastructure is on premises and you have a small footprint on AWS. Your company has decided to roll out a new application that is heavily dependent on low latency connectivity to LDAP for authentication. Your security policy requires minimal changes to the company’s existing application user management processes. What option would you implement to successfully launch this application?

  * Create a second, independent LDAP server in AWS for your application to use for authentication (independent would not work for authentication as its a separate copy)
  * Establish a VPN connection so your applications can authenticate against your existing on-premises LDAP servers (not a low latency solution)
  * **Establish a VPN connection between your data center and AWS create a LDAP replica on AWS and configure your application to use the LDAP replica for authentication (RODCs low latency and minimal setup)**
  * Create a second LDAP domain on AWS establish a VPN connection to establish a trust relationship between your new and existing domains and use the new domain for authentication (Not minimal effort)

#### A company is preparing to give AWS Management Console access to developers Company policy mandates identity federation and role-based access control. Roles are currently assigned using groups in the corporate Active Directory. What combination of the following will give developers access to the AWS console? (Select 2) Choose 2 answers

  * **AWS Directory Service AD Connector** (for Corporate Active directory)
  * AWS Directory Service Simple AD
  * AWS Identity and Access Management groups
  * **AWS identity and Access Management roles**
  * AWS identity and Access Management users

#### An Enterprise customer is starting their migration to the cloud, their main reason for migrating is agility, and they want to make their internal Microsoft Active Directory available to any applications running on AWS; this is so internal users only have to remember one set of credentials and as a central point of user control for leavers and joiners. How could they make their Active Directory secure, and highly available, with minimal on-premises infrastructure changes, in the most cost and time-efficient way? Choose the most appropriate.

  * Using Amazon Elastic Compute Cloud (EC2), they would create a DMZ using a security group; within the security group they could provision two smaller amazon EC2 instances that are running Openswan for resilient IPSEC tunnels, and two larger instance that are domain controllers; they would use multiple Availability Zones (Whats Openswan? Refer Implementation)
  * Using VPC, they could create an extension to their data center and make use of resilient hardware IPSEC tunnels; they could then have two domain controller instances that are joined to their existing domain and reside within different subnets, in different Availability Zones (highly available with 2 AZ’s, secure with VPN connection and minimal changes)
  * Within the customer’s existing infrastructure, they could provision new hardware to run Active Directory Federation Services; this would present Active Directory as a SAML2 endpoint on the internet; any new application on AWS could be written to authenticate using SAML2 (not minimal on-premises hardware changes)
  * The customer could create a stand-alone VPC with its own Active Directory Domain Controllers; two domain controller instances could be configured, one in each Availability Zone; new applications would authenticate with those domain controllers (not a central location, but a copy)

#### A company needs to deploy virtual desktops to its customers in a virtual private cloud, leveraging existing security controls. Which set of AWS services and features will meet the company’s requirements?
  * Virtual Private Network connection. AWS Directory Services, and ClassicLink (ClassicLink allows you to link an EC2-Classic instance to a VPC in your account, within the same region)
  * **Virtual Private Network connection. AWS Directory Services, and Amazon Workspaces (WorkSpaces for Virtual desktops, and AWS Directory Services to authenticate to an existing on-premises AD through VPN)**
  * AWS Directory Service, Amazon Workspaces, and AWS Identity and Access Management (AD service needs a VPN connection to interact with an On-premise AD directory)
  * Amazon Elastic Compute Cloud, and AWS Identity and Access Management (Need WorkSpaces for virtual desktops)

## STS

#### An AWS customer is deploying a web application that is composed of a front-end running on Amazon EC2 and of confidential data that is stored on Amazon S3. The customer security policy that all access operations to this sensitive data must be authenticated and authorized by a centralized access management system that is operated by a separate security team. In addition, the web application team that owns and administers the EC2 web front-end instances is prohibited from having any ability to access the data that circumvents this centralized access management system. Which of the following configurations will support these requirements:

  * Encrypt the data on Amazon S3 using a CloudHSM that is operated by the separate security team. Configure the web application to integrate with the CloudHSM for decrypting approved data access operations for trusted end-users. (S3 doesn’t integrate directly with CloudHSM, also there is no centralized access management system control)
  * **Configure the web application to authenticate end-users against the centralized access management system. Have the web application provision trusted users STS tokens entitling the download of approved data directly from Amazon S3 (Controlled access and admins cannot access the data as it needs authentication)**
  * Have the separate security team create and IAM role that is entitled to access the data on Amazon S3. Have the web application team provision their instances with this role while denying their IAM users access to the data on Amazon S3 (Web team would have access to the data)
  * Configure the web application to authenticate end-users against the centralized access management system using SAML. Have the end-users authenticate to IAM using their SAML token and download the approved data directly from S3. (not the way SAML auth works and not sure if the centralized access management system is SAML complaint)

## OpsWorks

#### Your mission is to create a lights-out datacenter environment, and you plan to use AWS OpsWorks to accomplish this. First you created a stack and added an App Server layer with an instance running in it. Next you added an application to the instance, and now you need to deploy a MySQL RDS database instance. Which of the following answers accurately describe how to add a backend database server to an OpsWorks stack? Choose 3 answers 

  * **Add a new database layer and then add recipes to the deploy actions of the database and App Server layers.**
  * Use OpsWorks’ “Clone Stack” feature to create a second RDS stack in another Availability Zone for redundancy in the event of a failure in the Primary AZ. To switch to the secondary RDS instance, set the [:database] attributes to values that are appropriate for your server which you can do by using custom JSON.
  * **The variables that characterize the RDS database connection—host, user, and so on—are set using the corresponding values from the deploy JSON’s [:deploy][:app_name][:database] attributes.**
  * Cookbook attributes are stored in a repository, so OpsWorks requires that the “password”: “your_password” attribute for the RDS instance must be encrypted using at least a 256-bit key.
  * **Set up the connection between the app server and the RDS layer by using a custom recipe. The recipe configures the app server as required, typically by creating a configuration file. The recipe gets the connection data such as the host and database name from a set of attributes in the stack configuration and deployment JSON that AWS OpsWorks installs on every instance.** 

#### A web-startup runs its very successful social news application on Amazon EC2 with an Elastic Load Balancer, an Auto-Scaling group of Java/Tomcat application-servers, and DynamoDB as data store. The main web application best runs on m2.xlarge instances since it is highly memory- bound. Each new deployment requires semi-automated creation and testing of a new AMI for the application servers which takes quite a while and is therefore only done once per week. Recently, a new chat feature has been implemented in node.js and waits to be integrated in the architecture. First tests show that the new component is CPU bound Because the company has some experience with using Chef, they decided to streamline the deployment process and use AWS OpsWorks as an application life cycle tool to simplify management of the application and reduce the deployment cycles. What configuration in AWS OpsWorks is necessary to integrate the new chat module in the most cost-efficient and flexible way?

  * Create one AWS Ops Works stack, create one AWS Ops Works layer, create one custom recipe
  * Create one AWS Ops Works stack, create two AWS Ops Works layers create one custom recipe (Single environment stack, two layers for java and node.js application using built-in recipes and custom recipe for DynamoDB connectivity only as other configuration. Refer link)
  * Create two AWS Ops Works stacks, create two AWS Ops Works layers create one custom recipe
  * Create two AWS Ops Works stacks, create two AWS Ops Works layers create two custom recipe

#### You company runs a complex customer relations management system that consists of around 10 different software components all backed by the same Amazon Relational Database (RDS) database. You adopted AWS OpsWorks to simplify management and deployment of that application and created an AWS OpsWorks stack with layers for each of the individual components. An internal security policy requires that all instances should run on the latest Amazon Linux AMI and that instances must be replaced within one month after the latest Amazon Linux AMI has been released. AMI replacements should be done without incurring application downtime or capacity problems. You decide to write a script to be run as soon as a new Amazon Linux AMI is released. Which solutions support the security policy and meet your requirements? Choose 2 answers

  * Assign a custom recipe to each layer, which replaces the underlying AMI. Use AWS OpsWorks life-cycle events to incrementally execute this custom recipe and update the instances with the new AMI.
  * **Create a new stack and layers with identical configuration, add instances with the latest Amazon Linux AMI specified as a custom AMI to the new layer, switch DNS to the new stack, and tear down the old stack.** (Blue-Green Deployment)
  * Identify all Amazon Elastic Compute Cloud (EC2) instances of your AWS OpsWorks stack, stop each instance, replace the AMI ID property with the ID of the latest Amazon Linux AMI ID, and restart the instance. To avoid downtime, make sure not more than one instance is stopped at the same time.
  * Specify the latest Amazon Linux AMI as a custom AMI at the stack level, terminate instances of the stack and let AWS OpsWorks launch new instances with the new AMI. (Will lead to downtime)
  * **Add new instances with the latest Amazon Linux AMI specified as a custom AMI to all AWS OpsWorks layers of your stack, and terminate the old ones.**

## Storage Gateway

#### You’re running an application on-premises due to its dependency on non-x86 hardware and want to use AWS for data backup. Your backup application is only able to write to POSIX-compatible block-based storage. You have 140TB of data and would like to mount it as a single folder on your file server. Users must be able to access portions of this data while the backups are taking place. What backup solution would be most appropriate for this use case?

  * Use Storage Gateway and configure it to use Gateway Cached volumes.
  * Configure your backup software to use S3 as the target for your data backups.
  * Configure your backup software to use Glacier as the target for your data backups
  * **Use Storage Gateway and configure it to use Gateway Stored volumes** (Data is hosted on the On-premise server as well. The requirement for 140TB is for file server On-Premise more to confuse and not in AWS. Just need a backup solution hence stored instead of cached volumes) 

#### A customer has a single 3-TB volume on-premises that is used to hold a large repository of images and print layout files. This repository is growing at 500 GB a year and must be presented as a single logical volume. The customer is becoming increasingly constrained with their local storage capacity and wants an off-site backup of this data, while maintaining low-latency access to their frequently accessed data. Which AWS Storage Gateway configuration meets the customer requirements?

  * **Gateway-Cached volumes with snapshots scheduled to Amazon S3**
  * Gateway-Stored volumes with snapshots scheduled to Amazon S3
  * Gateway-Virtual Tape Library with snapshots to Amazon S3
  * Gateway-Virtual Tape Library with snapshots to Amazon Glacier

#### You have a proprietary data store on-premises that must be backed up daily by dumping the data store contents to a single compressed 50GB file and sending the file to AWS. Your SLAs state that any dump file backed up within the past 7 days can be retrieved within 2 hours. Your compliance department has stated that all data must be held indefinitely. The time required to restore the data store from a backup is approximately 1 hour. Your on-premise network connection is capable of sustaining 1gbps to AWS. Which backup methods to AWS would be most cost-effective while still meeting all of your requirements?

  * Send the daily backup files to Glacier immediately after being generated (will not meet the RTO)
  * Transfer the daily backup files to an EBS volume in AWS and take daily snapshots of the volume (Not cost effective)
  * **Transfer the daily backup files to S3 and use appropriate bucket lifecycle policies to send to Glacier (Store in S3 for seven days and then archive to Glacier)**
  * Host the backup files on a Storage Gateway with Gateway-Cached Volumes and take daily snapshots (Not Cost effective as local storage as well as S3 storage)

#### A customer implemented AWS Storage Gateway with a gateway-cached volume at their main office. An event takes the link between the main and branch office offline. Which methods will enable the branch office to access their data? Choose 3 answers

  * Use a HTTPS GET to the Amazon S3 bucket where the files are located (gateway volumes are only accessible from the AWS Storage Gateway and cannot be directly accessed using Amazon S3 APIs)
  * Restore by implementing a lifecycle policy on the Amazon S3 bucket.
  * Make an Amazon Glacier Restore API call to load the files into another Amazon S3 bucket within four to six hours.
  * **Launch a new AWS Storage Gateway instance AMI in Amazon EC2, and restore from a gateway snapshot**
  * **Create an Amazon EBS volume from a gateway snapshot, and mount it to an Amazon EC2 instance.**
  * **Launch an AWS Storage Gateway virtual iSCSI device at the branch office, and restore from a gateway snapshot**

## AWS Data Pipeline

#### An International company has deployed a multi-tier web application that relies on DynamoDB in a single region. For regulatory reasons they need disaster recovery capability in a separate region with a Recovery Time Objective of 2 hours and a Recovery Point Objective of 24 hours. They should synchronize their data on a regular basis and be able to provision the web application rapidly using CloudFormation. The objective is to minimize changes to the existing web application, control the throughput of DynamoDB used for the synchronization of data and synchronize only the modified elements. Which design would you choose to meet these requirements?

  * **Use AWS data Pipeline to schedule a DynamoDB cross region copy once a day. Create a ‘Lastupdated’ attribute in your DynamoDB table that would represent the timestamp of the last update and use it as a filter.** [Refer Blog Post](https://aws.amazon.com/blogs/aws/copy-dynamodb-data-between-regions-using-the-aws-data-pipeline/)  
  * Use EMR and write a custom script to retrieve data from DynamoDB in the current region using a SCAN operation and push it to DynamoDB in the second region. (No Schedule and throughput control)
  * Use AWS data Pipeline to schedule an export of the DynamoDB table to S3 in the current region once a day then schedule another task immediately after it that will import data from S3 to DynamoDB in the other region. (With AWS Data pipeline the data can be copied directly to other DynamoDB table)
  * Send each item into an SQS queue in the second region; use an auto-scaling group behind the SQS queue to replay the write in the second region. (Not Automated to replay the write)