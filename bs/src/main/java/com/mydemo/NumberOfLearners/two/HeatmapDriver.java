package com.mydemo.NumberOfLearners.two;


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
 * 热力图
 * @author ZLiang
 *
 */
public class HeatmapDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		String[] path = { "hdfs://192.168.203.111:9000/sqoop/result/heatmap/output2/part-r-00000",
		"hdfs://192.168.203.111:9000/sqoop/result/heatmap/output3" };
		System.setProperty("hadoop.home.dir", "c:/hadoop");
		
		Configuration conf = new Configuration();
		// 生成job实例，用户对整个分布式运算的配置
		Job job = Job.getInstance(conf);
		job.setJarByClass(HeatmapDriver.class);
		
		//
		job.setMapperClass(HeatmapMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//
		job.setReducerClass(HeatmapReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		
		// 要处理的数据来源和产生结果存放的路径
		FileInputFormat.addInputPath(job, new Path(path[0]));
		FileOutputFormat.setOutputPath(job, new Path(path[1]));
		
		System.exit(job.waitForCompletion(true)?0:1);
		

	}

}
