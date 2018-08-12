## Domain 5 -  (worth 10% of the exam)

### EBS Volumes

* EBS volumes can be changed on the fly (except for magnetic standard).
* Best practice is to stop the EBS volume and then change the type.
* You can change the volume types by taking a snapshot and then using a snapshot to create a new volume.
* If you change a volume on the fly you should wait for 6 hours before making any other change.
* You can scale EBS volumes up only
* Volumes must be in the same AZ as the EC2 istances.