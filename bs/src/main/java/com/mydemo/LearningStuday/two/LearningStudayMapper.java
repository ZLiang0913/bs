package com.mydemo.LearningStuday.two;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LearningStudayMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		// 26577759,20170406,77
		// 26577759,20170408,25
		// 26577759,20170411,86
		//获取一行数据
		String line = value.toString();
		//分片
		String[] words = line.split(",");
		
		//GROUP BY user_id, date_value 
		// 26577759_20170411,86
		context.write(new Text(words[0]+"_"+words[1]), new IntWritable(Integer.parseInt(words[2])));
		
			
		
		
		
		
	}
	

}
