## Domain 3 (worth 10% of the exam)

### Cloudformation (Read FAQ's very important)

[Cloudformation Detailed Doc](aws-cloudformation-doc.md)


### Elastic Beanstalk

Elastic beanstalk automatically handles deployment details of capacity provisioning, load balancing, auto-scaling, and application health monitoring once the code is uploaded to Beanstalk. Elastic Beanstalk is a provisioning engine.
AWS Beanstalk is desinged to support multiple running environments. Elastic Benastalk also tracks and stores application versions over time, so an existing environment can be easily rolled back to a prior version or a new environment can be launched using an older version.

#### Elastic Beanstalk supports the following languages:
	* Package Builder
	* Apache with PHP
	* Apache with Python
	* Apache Tomcat for Java Applications
	* NGinx or Apache for Nodejs applications
	* Docker
	* Ruby
	* Go
	* Java SE
	* .NET
	* PHP
	* Python

- Elastic Beanstalk can automatically provision an RDS instance. The connectivity information to the DB instance is exposed to your application by environment variables.
- Beanstalk can be configured to be fault tolarent within a single region with multiple AZ's
- Elastic Beanstalk Security
	- By default you application is available publicly at myapp.elasticbeanstalk.com
	- It integrates with VPC.
	- Fully Supports IAM
	- Code is stored in S3
	- Only the changes from git repository are replicated.
	- Amazon Linux AMI and Windows 2008 R2 are supported with Beanstalk.

#### AWS Opsworks

AWS Opsworks is an application management service that helps automate operational tasks like code deployment, software configurations, package installations, database setups and server scaling using chef.
OpsWorks includes automation to scale your application based on time and load.

Chef turns infrastructure into code. With Chef, you can automate how you build, deploy and manage your infrastructure. Your infrastructure becomes as versionable, testable and repeatable as application code.

Chef Server stores your recipes as well as other configuration data. The chef client is installed on each virtual server, container or networking device you manage - we'll call these nodes. The client periodically polls the chef server. If anything on the node is out of date, the client brings it up to date.

#### AWS OpsWorks Lab

In Opsworks we have stacks and layers. Here a stack can be your production stack, test and dev stack etc. Basically a stack is a collection of Layers.

Layer can be a blue print for a set of EC2 instances, an elb or an RDS database. Layers sit within stacks.

After you attach an elastic load balancer to a layer, OpsWorks removes any currently registered instances and then manages the load balancer for you. If you subsequently use the ELB console or API to modify the configuration, the changes will not be permanent.

There are 3 stacks provided by OpsWorks. 
* Sample Stack: Sample stack with Nodejs app configured
* Chef 12 Stack: You can use your own chef cook books or community cook books. 
* Chef 11 Stack: You can use AWS built in cook books for applications and deployments.		 

##### Time based and Load based instances

OpsWorks automatically starts and stops time based instances based on a specified schedule.
OpsWorks automatically starts and stops load based instances in response to CPU, memory and application load changes across all instances in a layer.


##### Apps

An app represents a code stored in a repository and that you want to install on the application server instances. When you deploy the app OpsWorks downloads the code from the repository to the specified server instance. You can deploy the code without having to ssh inside the instance.







