package com.mydemo.LearningStuday.first;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class LearningStudayMain {

	public static void main(String[] args) throws Exception {
		String[] path = {"hdfs://192.168.203.111:9000/sqoop/zt/zt_stu_study_time_beihang/part-m-00000",
        "hdfs://192.168.203.111:9000/sqoop/result/LearningStuday/output1"};
		System.setProperty("hadoop.home.dir", "C:/hadoop");
		
		Configuration config = new Configuration();
		//生成job实例，用户对整个分布式运算的配置
		Job job = Job.getInstance(config);
		job.setJarByClass(LearningStudayMain.class);
		
		//指定在本任务里，map和reduce文件是什么
		job.setMapperClass(LearningStudayMapper.class);
		//job.setReducerClass(LoginNumReducer.class);
		
		//设置mapper输出的类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		//设置reducer输出的类型/最后输出的键值类型
//		job.setOutputKeyClass(Text.class);
//		job.setOutputValueClass(NullWritable.class);
		
		//要处理的数据来源和产生结果存放的路径
		FileInputFormat.addInputPath(job, new Path(path[0]));
		FileOutputFormat.setOutputPath(job, new Path(path[1]));
		
		//处理MapReduce运算
		System.exit(job.waitForCompletion(true)?0:1);

	}

}
