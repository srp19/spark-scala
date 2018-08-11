## Domain 8

### VMware Migrations:

* vCenter has a plugin that enables you to migrate VMware VMs to Amazon EC2 and manage AWS resources from within vCenter.

Use Cases Include
- Migrate VMware VMs to Amazon EC2
- Reach New Geographies from vCenter
- Self-Service AWS Portal within vCenter
- Leverage vCenter Experience while getting started with AWS.

### Storage Gateway

Storage gateway can be used migrate existing production environment/vms to cloud. AWS Storage gateway VM will take snaphots of vmdk files or root device volumes, then its going to replicate these snapshots to aws s3 over https and store them as objects. These snapshots can be mounted as EBS volumes and attached to EC2 instances.

Your snapshots should be consistent. The best way to do this is to take your VM offline and then do the snapshot.  


### Data Pipeline

Exam Tips - Data Pipeline

* AWS Data pipeline is a webservice that helps you reliably process and move data between different AWS compute and storage services, as well as on-premise data sources at specified intervals.
* It can be integrated with On premise environments
* Can be scheduled
* Data pipeline will provision and terminate resources as and when required.
* A lot of its functionality is replaced by Lambda.

Pipeline: A pipeline is the name of the container that contains the datanodes,activities,preconditions and schedules required in order to move your data from one location to another. A pipeline can run either on an EC2 instance or an EMR instance.
AWS data pipeline supplies a Task Runer package that can be installed on your on-premise hosts. This package continuously polls the AWS Data pipeline for work to perform. 

A pipeline consists of:
  - Datanode: A datanode is the end destination for your data. A data node can reference a specific Amazon S3 path. AWS data pipeline supports an expression language that makes it easy to reference data which is generated on regular basis.
  - Activity: An activity is an action that AWS Data pipeline initiates on your behalf as a part of a pipeline. Example activities are EMR or Hive jobs, copies, SQL queries, or command-line scripts. You can specify your own custom activities using the ShellCommandActivity.
  - Precondition: A precondition is a readiness check that can be optionally associated with a datasource or activity. IF a data source has precondition check, then that check must complete successfully before any activities consuming the data are launched. If the activity has a precondition then the precondition check must complete successfully before the activity is run. 
  - Schedule: Schedule defines when your pipeline activities run and the frequency with which the service expects your data to be available.