package com.mydemo.Index.two;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.mydemo.util.MyDate;

public class IndexMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		
		//20170314	1
		//获取一行数据
		String line = value.toString();
		//分片
		String[] words = line.split("\t");
		System.out.println(words.toString());
		
		//如果取的是zt_class文件数据
		// 154000,高等数学
		if(words.length==2){
			context.write(new IntWritable(MyDate.getWeek(words[0])), new IntWritable(Integer.parseInt(words[1])));
		}else {
			return;
		}
		
		
		
		
	}
	

}
