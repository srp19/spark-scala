## Domain 4 -  (worth 10% of the exam)

### Root Access to AWS Services

Only following services provide you with complete admin or root access.

* EC2
* BeanStalk
* Ops works
* Elastic MapReduce

### Elastic Load Balancer Configurations

#### Sticky sessions on a Load Balancer

* Duration Based Session Stickiness:
  Loadbalancer itself creates the cookie. When load balancer receives a request, it first checks to see whether the cookie is present in the request. If there's no cookie, the load balancer chooses an application instance based on the existing ELB algorithm and add a new cookie in the response. 

  If the application instance fails or becomes unhealthy, the load balancer stops routing requests to that instance, instead chooses a new instance basaed on the new ELB algorithm. The request is routed to the new instance as if there's no cookie and the session is no longer sticky.

  Even if the old instance comes up, the session is tied the new instance till the cookie expires.

### Pre-warming for ELB.

AWS can pre-configure the load balancer to have appropriate level of capacity based on expected traffic.
This is used in certain scenarios when flash traffic is expected, or incase where load test cannot be configured to gradually increase traffic.

This can be done by contacting AWS staff prior to the expected event. You will need to know the following:
- Start and end dates of your expected flash traffic.
- The expected request rate per second.
- The total size of the typical request/response that you will be experiencing



* Application-Controlled Session Stickiness:

The load balancer uses a special cookie to associate the session with the original server that handled the request, but follows the lifetime of the application generated cookie corresponding to the cookie name specified in the policy configuration.
The loadbalancer only inserts a new stickiness cookie only if the application response includes a new application cookie.


