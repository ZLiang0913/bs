package com.mydemo.DailyActivity.two;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StudyTimesMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		// 12995905,20170315,2
		//获取一行数据
		String line = value.toString();
		//分片
		String[] words = line.split(",");
		
		//数据清洗,必须study_times>=3
		if(Integer.parseInt(words[2])<3) 
			return;
		
		//group by user_id,updated_time
		context.write(new Text(words[1]), new IntWritable(1));
		
			
		
		
		
		
	}
	

}
