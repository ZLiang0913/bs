package com.mydemo.Index.first;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class IndexMapper extends Mapper<LongWritable, Text, Text, Text>{

	@Override
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		
		
		//获取一行数据
		String line = value.toString();
		//分片
		String[] words = line.split(",");
		
		//如果取的是zt_class文件数据
		// 154000,高等数学
		if(words.length==2){
			context.write(new Text(words[0]), new Text("*"+words[1]));
		}else if (words.length==4) {
			//如果取的是zt_stu_studay_schedule文件数据
			//13696657,154000,Page,20170320
			context.write(new Text(words[1]), new Text("1"));
		}
		
		
		
		
	}
	

}
