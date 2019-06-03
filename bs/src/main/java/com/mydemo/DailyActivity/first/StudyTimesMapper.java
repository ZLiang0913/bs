package com.mydemo.DailyActivity.first;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StudyTimesMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context) {
		// 13696657,154000,Page,20170320
		//获取一行数据
		String line = value.toString();
		//分片
		String[] words = line.split(",");
		
		//数据清洗
		if(words.length!=4) return;
		
		//group by user_id,updated_time
		try {
			context.write(new Text(words[0]+"_"+words[3]), new IntWritable(1));
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}
