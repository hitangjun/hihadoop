#用户行为分析 hadoop-sample

hadoop环境配置

hdfs 操作

简单的map reduce

map对压缩文件的处理

数据清洗中的多因子去重？ --存在分布式锁的问题

Hive -- 基于Hadoop的数据仓库分析工具

http://www.cnblogs.com/hanganglin/p/4175247.html

hive 启动问题记录 及解决方法

http://blog.csdn.net/jim110/article/details/44907745

大数据分析的一点思路：
http://lxw1234.com/archives/2015/12/588.htm

```
--HiveSQL
user default;
create table user_log(date1 string,uuid string,url string,referer string,phone string,clientType string,appType string,clientFlag string,ua string,version string,channel string,ip string,province string,city string,model string,resolution string,system string) row format delimited fields terminated by '|';
--导入数据
hadoop fs -put /logdata//input '/user/hive/warehouse/user_log';
--分析数据
select ip,count(1) cnt  from user_log group by ip order by cnt desc limit 20;
select city,count(1) cnt  from user_log where substring(date1,0,8)>='20160822' and substring(date1,0,8)<='20160830' group by city order by cnt desc limit 20;
select day,count(1) from (select distinct substring(date1,0,8) day, phone  from user_log) a group by day;

select "2015","PV",count(*) from dms.tracklog_5min
where day>='2015' and day<'2016'
union all
select "201505","UV",count(*) from (
select  cookieid from dms.tracklog_5min
where day>='2015' and day<'2016'  group by cookieid ) a
union all
select "2015","IP",count(*) from (
select  ip from dms.tracklog_5min
where day>='2015' and day<'2016'  group by ip ) a
union all
select "2015","LOGIN",count(*) from (
select  userid from dms.tracklog_5min
where day>='2015' and day<'2016' group by userid) b;

--每日活跃用户数
--每日活跃设备数
--每日独立IP数
select day,count(1) cnt from
 (select substring(date1,0,8) day,url
 from user_log ) a
 group by day

union all
select day,count(1) cnt  from
 (select substring(date1,0,8) day,uuid
 from user_log group by substring(date1,0,8),uuid) b
 group by day

union all
select day,count(1) cnt  from
 (select substring(date1,0,8) day,phone
 from user_log group by substring(date1,0,8),phone) c
 group by day

union all
select day,count(1) cnt  from
 (select substring(date1,0,8) day,ip
 from user_log group by substring(date1,0,8),ip) d
 group by day;
```

Hive  尽可能用group by 而不是distinct


