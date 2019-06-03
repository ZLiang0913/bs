package com.mydemo.LearningStuday.three;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LearningStudayMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		// 7964333,20170401,136
		// 11995893,20170401,6
		//获取一行数据
		String line = value.toString();
		//分片
		String[] words = line.split(",");
		
		//数据清洗
		if (words.length!=3) {
			return;
		}
		
		// GROUP BY date_value
		// 220170401,<136,6>
		context.write(new Text(words[1]), new IntWritable(Integer.parseInt(words[2])));
		
			
		
		
		
		
	}
	

}
