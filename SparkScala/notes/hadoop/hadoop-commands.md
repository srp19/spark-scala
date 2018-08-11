#hadoop fs commands

	hadoop fs -ls

#properties files
	/etc/hadoop/conf/core-site.xml
	/etc/hadoop/conf/hdfs-site.xml

#name node port 
	50070

#view core-site and hdfs-site
	cd /etc/hadoop/conf
  ls -ltr core-site.xml hdfs-site.xml
  view core-site.xml
  #fs.defaultFS : name name ip address
  view hdfs-site.xml
  #dfs.blocksize

#move to home directory ~
	cd

#list hadoop files in directory
	hadoop fs -ls /user/srp19
	
#create user space in hortonworks sandbox
	sudo -u hdfs hadoop fs -mkdir /user/root; 
	sudo -u hdfs hadoop fs -chown -R /user/root

#bash commands
	ls -ltr /data/crime
	du -sh /data/crime
		4GB
	
#copy to hdfs
	hadoop fs -copyFromLocal /data/crime /user/srp19/.

#recursively get all files in folder
	hadoop fs -ls -R /user/srp19/crime
	
#get size of folder
	hadoop fs -du -s -h /user/srp19/crime

#to check how files are stored in hdfs
	hdfs fsck /user/srp19/crime -files -blocks -locations

#copy retail_d
	hadoop fs -copyFromLocal /data/retail_db /user/srp19

#yarn
		cd /etc/hadoop/conf
		vi yarn-site.xml
		:q!
		cd /etc/spark/conf