package com.hitangjun.hihadoop.sample.userlog;

/**
 * @author JohnTang
 * @date 2016/11/10
 */

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.Iterator;

public class UserLogCity {

    public static class UserLogCityMapper extends MapReduceBase implements Mapper {
        private IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(Object o, Object o2, OutputCollector outputCollector, Reporter reporter) throws IOException {
            UserLog ulog = new UserLog(o2.toString());
            if (StringUtils.isNotEmpty(ulog.getCity())) {
                word.set(ulog.getCity());
                outputCollector.collect(word, one);
            }
        }
    }

    public static class UserLogCityReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        @Override
        public void reduce(Text text, Iterator<IntWritable> iterator, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
            int sum = 0;
            while (iterator.hasNext()) {
                sum += iterator.next().get();
            }
            result.set(sum);
            outputCollector.collect(text, result);
        }
    }


    public static void main(String[] args) throws Exception {
        System.setProperty("hadoop.home.dir", "D:\\work\\hadoop-2.7.0");
        System.setProperty("HADOOP_USER_NAME", "root");

        String input = "hdfs://hadoophost:9000/sample/input";
        String output = "hdfs://hadoophost:9000/sample/output/city";

        JobConf conf = new JobConf(UserLogCity.class);
        conf.setJobName("UserLogCity");

        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(IntWritable.class);

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(UserLogCityMapper.class);
        conf.setCombinerClass(UserLogCityReducer.class);
        conf.setReducerClass(UserLogCityReducer.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(input));
        FileOutputFormat.setOutputPath(conf, new Path(output));

        JobClient.runJob(conf);
        System.exit(0);
    }
}
