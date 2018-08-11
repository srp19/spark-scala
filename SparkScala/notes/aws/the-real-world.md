# The real world creating a fault tolerant wordpress site

## Section 10 Lecture 79 (Setting up our environment)
## Section 10 Lecture 80 (Setting up EC2)
## Section 10 Lecture 81 (Automation and setting up our AMI)
## Section 10 Lecture 82 (Configuring Autoscaling and Load Testing)
 * use stress software to stress test instances and trigger autoscaling. 
  * It lets you put stress on your:
   *. CPU
   *. RAM
   *. Disk Drive (only supports write tests).
   
   ```
   apt-get install stress
   ```
## Section 10 Lecture 83 (Cloudformation - Create a fault tolerant wordpress site using cloudformation)
* Turn your entire infrastructure into code using cloudformation
* You can have different versions of your infrastructure and you can keep imporving
* Elastic Load balancers cost you money and dont forget to delete them.
* Here we are making a fault tolerant wordpress site using cloudformation template. Our template is going to provision Web servers, 
Web Servers are going to run their own bootstrap scripts, they are going to install apache, php and mysql etc. This is all included in the 
cloudformation template. For all of us as system admins or solutions architect just need to keep this template up to date and we can also roll it 
out for customers all over the world. Suppose you have an online store and you have architected it really well, if the region in which you 
are hosting it goes down you can create infrastructure into another region without much hassle using this template.

## Section 10 Lecture 84 (Want to be a real solutions architect? You need to know cloudformation)
Read CloudFormation by Fernando Honig, He is the head of cloud Engineering at Cognito IQ. Go for the Acloud Guru cloud formation course.


