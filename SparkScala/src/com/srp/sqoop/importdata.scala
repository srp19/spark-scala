package com.srp.sqoop

object importdata {
  def main(args: Array[String])
  {
    println("importing data to HDFS using sqoop")
    
    /*
    _______________________________________
    #connect to mysql
    
    mysql -u retail_user -h ms.itversity.com -p
    
    _______________________________________
    #list databases
    sqoop list-databases \
      --connect jdbc:mysql://ms.itversity.com:3306 \
      --username retail_user \
      --password itversity
    
    sqoop list-tables \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
      --username retail_user \
      --password itversity
    
    _______________________________________
    #eval
    sqoop eval \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
      --username retail_user \
      --password itversity \
      --query "SELECT * FROM order_items LIMIT 10"

    sqoop eval \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
      --username retail_user \
      --password itversity \
      --query "INSERT INTO orders VALUES (100000, '2017-10-31 00:00:00.0', 100000, 'DUMMY')"
    
    sqoop eval \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_export \
      --username retail_user \
      --password itversity \
      --query "CREATE TABLE dummy (i INT)"
    
    sqoop eval \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_export \
      --username retail_user \
      --password itversity \
      --query "INSERT INTO dummy VALUES (1)"
    
    sqoop eval \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_export \
      --username retail_user \
      --password itversity \
      --query "SELECT * FROM dummy"
      
    _______________________________________
    #simple import
      
    sqoop import \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
      --username retail_user \
      --password itversity \
      --table order_items \
      --warehouse-dir /user/dgadiraju/sqoop_import/retail_db
      
     sqoop import \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
      --username retail_user \
      --password itversity \
      --table order_items \
      --target-dir /user/dgadiraju/sqoop_import/retail_db/order_items
  
  	_______________________________________
  	#execution life cycle
  	
  		Here is the execution lifecycle of Sqoop.

      Connect to the source database and get metadata
      Generate java file with metadata and compile to a jar file
      Apply boundaryvalsquery to apply split logic, default 4
      Use split boundaries to issue queries against the source database
      Each thread will have a different connection to issue the query
      Each thread will get a mutually exclusive subset of the data
      Data will be written to HDFS in a separate file per thread
      
      sqoop import \
        --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
        --username retail_user \
        --password itversity \
        --table order_items \
        --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
        --num-mappers 1 \
        --delete-target-dir
      
       sqoop import \
        --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
        --username retail_user \
        --password itversity \
        --table order_items \
        --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
        --num-mappers 1 \
        --append
  	
  	_______________________________________
  	#managing directories
  	
      By default sqoop import fails if target directory already exists
      Directory can be overwritten by using –delete-target-dir
      Data can be appended to existing directories by saying –append
      sqoop import \
        --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
        --username retail_user \
        --password itversity \
        --table order_items \
        --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
        --num-mappers 1 \
        --delete-target-dir
      
       sqoop import \
        --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
        --username retail_user \
        --password itversity \
        --table order_items \
        --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
        --num-mappers 1 \
        --append
        
    _______________________________________  
    #importing data, using splity by
    
    	Importing Data
      Number of mappers
      Splitting using a custom column
      Splitting using non-numeric column
      Let us explore how we can customize split logic
      
      By default number of mappers is 4, it can be changed with –num-mappers
      Split logic will be applied to a primary key if exists
      If primary key does not exist and if we use a number of mappers more than 1,
       then sqoop import will fail
      At that time we can use –split-by to split on a non-key column or explicitly 
      set –num-mappers to 1 or use –auto-reset-to-one-mapper
      If the primary key column or the column specified in split-by clause 
      is non numeric type, then we need to use this additional argument 
      -Dorg.apache.sqoop.splitter.allow_text_splitter=true
      Using split-by
      For performance, reason choose a column which is indexed 
      as part of the split-by clause
      If there are null values in the column, corresponding
       records from the table will be ignored
      Data in the split-by column need not be unique, 
      but if there are duplicates then there can be a skew in the data
      while importing (which means some files might be relatively bigger compared to other files)
      sqoop import \
        --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
        --username retail_user \
        --password itversity \
        --table order_items_nopk \
        --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
        --split-by order_item_order_id
        
      #Splitting on text field
      sqoop import \
        -Dorg.apache.sqoop.splitter.allow_text_splitter=true \
        --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
        --username retail_user \
        --password itversity \
        --table orders \
        --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
        --split-by order_status
  	
  	_______________________________________
  	#auto reset to one mapper
  	
    	Auto Reset to One Mapper
      If a table does not have a primary key defined and the 
      --split-by <col> is not provided, then import will fail unless 
      the number of mappers is explicitly set to one with the 
      --num-mappers 1 option or the --autoreset-to-one-mapper option is used. 
      The option --autoreset-to-one-mapper is typically used with the 
      import-all-tables tool to automatically handle tables without 
      a primary key in a schema.
      
      sqoop import \
       --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
       --username retail_user \
       --password itversity \
       --table order_items_nopk \
       --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
       --autoreset-to-one-mapper
      We can check using below command
      
      hadoop fs -ls /user/dgadiraju/sqoop_import/retail_db/order_items_nopk
  	
  	_______________________________________
  	#different file formats
  		
  		While importing data using Sqoop we can save data into different file formats. 
  		We can also specify compression codecs while importing the data.

      File formats
        Text file (default)
        Sequence file
        Avro file
        Parquet file
        
      sqoop import \
        --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
        --username retail_user \
        --password itversity \
        --table order_items \
        --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
        --num-mappers 2 \
        --as-sequencefile
  	_______________________________________
  	#export
  	
		sqoop export \ 
      --connect jdbc:mysql://ms.itversity.com:3306/retail_export \ 
      --username retail_user \ 
      --password itversity \ 
      --export-dir /apps/hive/warehouse/dgadiraju_sqoop_import.db/daily_revenue \
      --table daily_revenue_demo \
      --columns order_date,revenue \
      --input-fields-terminated-by "\001" \
      --num-mappers 1
        
     sqoop export \ 
      --connect jdbc:mysql://ms.itversity.com:3306/retail_export \ 
      --username retail_user \ 
      --password itversity \ 
      --export-dir /apps/hive/warehouse/dgadiraju_sqoop_import.db/daily_revenue \
      --table daily_revenue_demo \
      --columns order_date,revenue \
      --input-fields-terminated-by "\001" \
      --num-mappers 1
      
    _______________________________________
    #compression
   
    Compression algorithms
    Gzip
    Deflate
    Snappy
    Others
    Compression
    Following are the steps to use compression.
    
    Go to /etc/hadoop/conf and check core-site.xml for supported compression codecs
    Use –compress to enable compression
    If compression codec is not specified, it will use gzip by default
    Compression algorithm can be specified using compression-codec
    To uncompress the file use the command gunzip.
    sqoop import \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
      --username retail_user \
      --password itversity \
      --table order_items \
      --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
      --num-mappers 2 \
      --as-textfile \
      --compress \
      --compression-codec org.apache.hadoop.io.compress.GzipCodec
    
    sqoop import \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
      --username retail_user \
      --password itversity \
      --table order_items \
      --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
      --num-mappers 2 \
      --as-textfile \
      --compress \
      --compression-codec org.apache.hadoop.io.compress.SnappyCodec
    
    _______________________________________
    #boundary value
    What is Boundary Value Query in Sqoop
    Sqoop runs it mapper task by executing the 
    SQL like SELECT * FROM table WHERE id >= low AND id < high. 
    Sqoop uses query select minimum value for splitting, a maximum value for 
    splitting to find out boundaries for creating splits. 
    This Sqoop operation is known as Boundary Value Query.
    
    sqoop import \
    --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
    --username retail_user \
    --password itversity \
    --table order_items \
    --warehouse-dir /user/dgadiraju/sqoop_import/reatil_db \
    --boundary-query 'select min(order_item_id), max(order_item_id) from order_items where order_item_id > 99999'
    sqoop import \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
      --username retail_user \
      --password itversity \
      --table order_items \
      --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
      --boundary-query 'select 100000, 172198'
    
    _______________________________________
    #transformation and filtering
    
    Transformations and filtering in Sqoop.
  
    Boundary Query
    Transformations and filtering
    Columns
    Query
    sqoop import \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
      --username retail_user \
      --password itversity \
      --table order_items \
      --columns order_item_order_id,order_item_id,order_item_subtotal \
      --warehouse-dir /user/dgadiraju/sqoop_import/retail_db \
      --num-mappers 2
    view rawsqoop-import-using-columns.sh hosted with ❤ by GitHub
    sqoop import \
      --connect jdbc:mysql://ms.itversity.com:3306/retail_db \
      --username retail_user \
      --password itversity \
      --target-dir /user/dgadiraju/sqoop_import/retail_db/orders_with_revenue \
      --num-mappers 2 \
      --query "select o.*, sum(oi.order_item_subtotal) order_revenue from orders o join order_items oi on o.order_id = oi.order_item_order_id and \$CONDITIONS group by o.order_id, o.order_date, o.order_customer_id, o.order_status" \
      --split-by order_id
   
    _______________________________________
    #Exporting Data
    Update and Upsert/Merge
    
    create table daily_revenue (
     order_date varchar(30) primary key,
     revenue float
    );
    
    sqoop export \
    --connect jdbc:mysql://ms.itversity.com:3306/retail_export \
    --username retail_user \
    --password itversity \
    --export-dir /apps/hive/warehouse/dgadiraju_sqoop_import.db/daily_revenue \
    --table daily_revenue \
    --input-fields-terminated-by "\001" \
    --num-mappers 1
    Sqoop Export – Update
    
    insert into table daily_revenue
     select order_date, sum(order_item_subtotal) daily_revenue
     from orders join order_items on
     order_id = order_item_order_id
     where order_date like '2013-07%'
     group by order_date;
    
    Update the table
    update daily_revenue set revenue = 0;
    
    sqoop export \
    --connect jdbc:mysql://ms.itversity.com:3306/retail_export \
    --username retail_user \
    --password itversity \
    --export-dir /apps/hive/warehouse/dgadiraju_sqoop_import.db/daily_revenue \
    --table daily_revenue \
    --update-key order_date \
    --input-fields-terminated-by "\001" \
    --num-mappers 1
    
    Upsert the table
    
    sqoop export \
    --connect jdbc:mysql://ms.itversity.com:3306/retail_export \
    --username retail_user \
    --password itversity \
    --export-dir /apps/hive/warehouse/dgadiraju_sqoop_import.db/daily_revenue \
    --table daily_revenue \
    --update-key order_date \
    --update-mode allowinsert \
    --input-fields-terminated-by "\001" \
    --num-mappers 1
    
    _______________________________________
    #Stage tables
      
      Launch Hive and Insert the data into the table
      
      insert into table daily_revenue
       select order_date, sum(order_item_subtotal) daily_revenue
       from orders join order_items on
       order_id = order_item_order_id
       where order_date > '2013-08'
       group by order_date;
      
      sqoop export \ 
      --connect jdbc:mysql://ms.itversity.com:3306/ retail_export \
      --username retail_user \ 
      --password itversity \ 
      --export-dir /apps/hive/warehouse/dgadiraju_sqoop_import.db/daily_revenue \ --table daily_revenue \ --input-fields-terminated-by "\001"
      
      Creating stage table
      
      create table daily_revenue_stage (
      order_date varchar(30) primary key,
      revenue float
      );
      
      Insert values into the table
      
      insert into daily_revenue values ("2014-07-01 00:00:00.0", 0);
      
      Run the stage table
      
      sqoop export \
      --connect jdbc:mysql://ms.itversity.com:3306/ retail_export \
      --username retail_user \
      --password itversity \
      --export-dir /apps/hive/warehouse/dgadiraju_sqoop_import.db/daily_revenue \ --table daily_revenue \
      --staging-table daily_revenue_stage \
      --input-fields-terminated-by "\001"
    
    */
  }
}