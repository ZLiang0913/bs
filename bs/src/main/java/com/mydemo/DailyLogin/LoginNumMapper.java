package com.mydemo.DailyLogin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LoginNumMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context) {
		// 26575679,20170401,2017-04-01 00:01:34.0
		//获取一行数据
		String line = value.toString();
		//分片
		String[] words = line.split(",");
		
		//数据清洗
		if(words.length!=3) return;
		
		//group by login_date
		try {
			context.write(new Text(words[1]), new IntWritable(1));
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}
