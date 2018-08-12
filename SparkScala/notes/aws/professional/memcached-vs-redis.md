Every node within a cluster is the same instance type and runs the same cache engine. Each cache node has its own Domain Name Service (DNS) name and port.

The Memcached engine supports Auto Discovery—the ability for client programs to automatically identify all of the nodes in a cache cluster, and to initiate and maintain connections to all of these nodes. With Auto Discovery, your application does not need to manually connect to individual nodes; instead, your application connects to a configuration endpoint. The configuration endpoint DNS entry contains the CNAME entries for each of the cache node endpoints. Thus, by connecting to the configuration endpoint, your application immediately knows about all of the nodes in the cluster and can connect to all of them. You don't need to hard code the individual cache node endpoints in your application.

### ElastiCache Shards (Redis):

A Redis shard (called a node group in the API and CLI) is a grouping of 1–6 related nodes. A Redis (cluster mode disabled) cluster always has one shard. A Redis (cluster mode enabled) cluster can have from 1–15 shards.

A multiple node shard implements replication by have one read/write primary node and 1–5 replica nodes. For more information

Each node in a shard has the same compute, storage and memory specifications.

### ElastiCache Clusters

A Redis cluster is a logical grouping of one or more ElastiCache Shards (Redis). Data is partitioned across the shards in a Redis (cluster mode enabled) cluster.

A Memcached cluster is a logical grouping of one or more ElastiCache Nodes. Data is partitioned across the nodes in a Memcached cluster.

#### Typical Cluster Configurations

Memcached supports up to 100 nodes per customer per region with each cluster having 1–20 nodes. You can partition your data across the nodes in a Memcached cluster.

A Redis cluster contains 1–15 shards (in the API, called node groups), each of which is a partition of your data. Redis (cluster mode disabled) always has just one shard.

### Memcached Clusters

When you run the Memcached engine, clusters can be made up of 1–20 nodes. You can partition your database across the nodes. Your application reads and writes to each node's endpoint.

When you partition your data across memcached nodes, we recommend using consistent hashing. 



## Choosing an Engine: Memcached, Redis (cluster mode disabled), or Redis (cluster mode enabled)

##### Choose Memcached if the following apply to your situation:

* You need the simplest model possible.
* You need to run large nodes with multiple cores or threads.
* You need the ability to scale out/in, adding and removing nodes as demand on your system increases and decreases.
* You need to cache objects, such as a database.

##### Choose a version of ElastiCache for Redis if the following apply to your situation: