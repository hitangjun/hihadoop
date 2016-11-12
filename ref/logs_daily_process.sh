#!/bin/sh

#get yesterday format string
yesterday=`date --date='1 days ago' +%Y_%m_%d`
#yesterday=$1

#upload logs to hdfs
hadoop fs -put /apache_logs/access_${yesterday}.log  /web_logs

#cleaning data
hadoop jar /apache_logs/cleaned.jar  /web_logs/access_${yesterday}.log  /web_cleaned/${yesterday}  1>/dev/null


#alter hive table and then add partition to existed table
hive -e "ALTER TABLE ex_logs ADD PARTITION(logdate='${yesterday}') LOCATION '/web_cleaned/${yesterday}';"

#create hive table everyday
hive -e "CREATE TABLE web_pv_${yesterday} AS SELECT COUNT(1) AS PV FROM ex_logs WHERE logdate='${yesterday}';"
hive -e "CREATE TABLE web_ip_${yesterday} AS SELECT COUNT(DISTINCT ip) AS IP FROM ex_logs WHERE logdate='${yesterday}';"
hive -e "CREATE TABLE web_jumper_${yesterday} AS SELECT COUNT(1) AS jumper FROM (SELECT COUNT(ip) AS times FROM ex_logs WHERE logdate='${yesterday}' GROUP BY ip HAVING times=1) e;"
hive -e "CREATE TABLE web_${yesterday} AS SELECT '${yesterday}', a.pv, b.ip, C.jumper FROM web_pv_${yesterday} a JOIN  JOIN web_ip_${yesterday} b ON 1=1 JOIN web_jumper_${yesterday} c ON 1=1;"

#delete hive tables
hive -e "drop table web_pv_${yesterday};"
hive -e "drop table web_ip_${yesterday};"
hive -e "drop table web_jumper_${yesterday};"


#sqoop export to mysql
sqoop export --connect jdbc:mysql://hadoop0:3306/ex_logs --username root --password 123456 --table web_logs_stat --fields-terminated-by '\001' --export-dir '/user/hive/warehouse/web_${yesterday}'

#delete hive tables
hive -e "drop table web_${yesterday};"