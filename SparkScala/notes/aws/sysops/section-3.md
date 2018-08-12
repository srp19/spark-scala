## Domain 3 -  (worth 10% of the exam)

### Elasticity & Scalability

- Elasticity allows you to stretch out and retract back your infrastructure based on the demand.
- Scalability is used to talk about building out the infrastructure to meet your demands long term. Scalability is used over longer time periods such as weeks, days, months and years.

#### AWS Services - Scalability vs Elasticity

* EC2
  - Scalability - Increase instance sizes a required using reserved instances
  - Elasticity - Increase the number of EC2 instances, based on autoscaling.

* DynamoDB
  - Scalability - Unlimited amount of storage
  - Elasticity - Increase additonal IOPS for additonal spikes in traffic. Decrease the IOPS after the spike.

* RDS
  - Scalability - Increase instance size, eg from small to medium
  - Elasticity - Not very elastic, can't scale RDS based on Demand.  

### Multi-AZ and Read Replicas
When you are creating a read replica check whether a snapshot is required.

### Troubleshooting problems with autoscaling

* Associated Key Pair does not Exist
* Security Group does not exist
* Auto Scaling Config is not working correctly
* Autoscaling group not found
* Instance type specified is not supported in the AZ
* AZ is no longer supported
* Invalid EBS device Mapping
* Autoscaling Service is not enabled on your account.
* Attempting to attach an EBS block device to an instance store AMI.




