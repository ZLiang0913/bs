package com.mydemo.NumberOfLearners.period;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
/**
 * 条形图
 * 哪些时间段的学习人数最多
 * @author ZLiang
 *
 */
public class PeriodDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		String[] path = { "hdfs://192.168.203.111:9000/sqoop/zt/hours/part-m-00000","hdfs://192.168.203.111:9000/sqoop/zt/zt_stu_study_time_beihang/part-m-00000",
		"hdfs://192.168.203.111:9000/sqoop/result/period/output" };
		System.setProperty("hadoop.home.dir", "c:/hadoop");
		
		Configuration conf = new Configuration();
		// 生成job实例，用户对整个分布式运算的配置
		Job job = Job.getInstance(conf);
		job.setJarByClass(PeriodDriver.class);
		
		//
		job.setMapperClass(PeriodMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//
		job.setReducerClass(PeriodReducer.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		
		// 要处理的数据来源和产生结果存放的路径
		
		FileInputFormat.addInputPath(job, new Path(path[0]));
		FileInputFormat.addInputPath(job, new Path(path[1]));
		FileOutputFormat.setOutputPath(job, new Path(path[2]));
		
		System.exit(job.waitForCompletion(true)?0:1);
		

	}

}
