package com.mydemo.LearningNumber;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LearningNumberMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context) {
		// 13696657,154000,Page,20170320
		// 13696657,154000,Page,20170320
		//获取一行数据
		String line = value.toString();
		//分片
		String[] words = line.split(",");
		
		//数据清洗
		if(words.length!=4) return;
		
		//group by updated_date
		try {
			context.write(new Text(words[3]), new IntWritable(1));
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}
