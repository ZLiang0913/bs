package com.mydemo.Index.three;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class IndexMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		
		
		//获取一行数据
		String line = value.toString();
		//分片
		String[] words = line.split(",");
		context.write(new Text(words[0]), new IntWritable(1));
		
		
		
		
	}
	

}
