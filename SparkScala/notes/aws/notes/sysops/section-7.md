## Domain 7 - Security (worth 10% of the exam)

### Building IAM Policies - Lab

Created a custom policy for S3 Get and List Objects.

### Using IAM roles with EC2

Attach the previously created policy to an IAM role. Attache the role to an EC2 instance.

Now you can attach a role to an EC2 instance any time, every after the instance is created as opposed to only during provisioning as was the case earlier. You would be able to run commands on the EC2 instance as per the policy permits.

### S3 ClI & Regions.

Use --region flag while using S3 commands. Good practise.

### Multifactor Authentication on AWS - Lab

Multifactor authentication is used as an additional security measure to ensure that your root account is not compromised.

### STS Service

Same as Professional.

### AWS Shared Security Model.

#### Storage Decommissioning

When the storage device has reached the end of its useful life. AWS procedures include a decommissioning process that is designed to prevent customer data from being exposed to unauthorized individuals.
AWS used techniques detailed in DOD 5220.22-M (National Industrial Security Program Operating Manual) or NIST 800-88 ("Guidelines for Media Sanitization") to destroy data as part of decommissioning process. 

#### Network Monitoring and Protection

* DDOS
* IP Spoofing
  AWS-controlled host based firewall infrastructure will not permit an instance to send traffic with a source IP or mac address other than its own.
  Unauthorized port scans by Amazon EC2 customers are a violation of the AWS Acceptable use policy. You must request a vulnerability scan in advance.
* Port Scanning
* Packet Sniffing by other tenants.

#### AWS Credentials

- Passwords
- Multi-Factor Authentication
- Access Keys
- Key Pairs
- X.509 Certificates.
  * Digitally signed SOAP requests to AWS APIs
  * SSL server certificates for HTTPS.

  X.509 certificates are currently used only with S3 for creating Signed URLs which are sent to the user to download objects from S3.

- AWS Trusted Advisor: Makes recommendations to save money, improve system performance, or close security gaps. It provides alerts on several of the security misconfigurations.

- Instance Isolation
Different Instances running on the same physical machine are isolated from each other via the Xen hypervisor. In addition the AWS firewall resides within the hypervisor layer, between the physical network interface and instances virtual network interface 





