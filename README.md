#用户行为分析 hadoop

###目标
最常访问日志路径
IP记录
时间行为
终端统计
登录异常
最X商家 TOP10
最X用户 TOP10
最X城市

Hadoop的策略：
分而制之

step by step:
 log.gz --> hdfs upload --> hadoop mapreduce --> sqoop --> RDBMS
  -- VIEW


###1、准备用户行为历史数据

ex：192.168.1.232

###2、hadoop单机环境搭建

单机部署 ex：192.168.1.239

http://www.cnblogs.com/ee900222/p/hadoop_1.html

访问：http://192.168.1.239:8088/

###3、spring hadoop工程




###4、going on ...



###参考

#####海量数据挖掘之中移动流量运营系统[http://blog.csdn.net/sdksdk0/article/details/51691862]
#####Linux下安装Hadoop(2.7.1)详解及WordCount运行http://www.cnblogs.com/leesf456/p/4795507.html
#####spring-hadoop-samples[https://github.com/spring-projects/spring-hadoop-samples]
#####hadoop默认配置信息[http://www.aboutyun.com/thread-7513-1-1.html]
#####HDFS基本原理及数据存取实战http://blog.csdn.net/sdksdk0/article/details/51622547
#####Hadoop Streaming框架[http://www.cnblogs.com/luchen927/category/351962.html]
#####NativeLibraries错误的解决过程[http://blog.csdn.net/bamuta/article/details/13506843]
#####使用Storm实现实时大数据分析[http://qq85609655.iteye.com/blog/2035717]
    解释了HADOOP对数据处理的流程和缺点,由于map/reduce每次执行都需要遍历整个数据集，对于数据的实时计算并不合适，于是有了storm。

    Twitter已经用**Heron**替换了Storm.