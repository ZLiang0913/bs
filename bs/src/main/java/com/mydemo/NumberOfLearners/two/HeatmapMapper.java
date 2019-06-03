package com.mydemo.NumberOfLearners.two;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * 
 * 自定义mapper输出类型
 * LogMapper
 * 创建人:zhaoliang 
 * 时间：2018年5月2日-下午6:34:50 
 * @version 1.0.0
 *
 */
public class HeatmapMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		//数据：1,23,2
		String line = value.toString();
		String[] data = line.split(",");
		context.write(new Text(data[0]+","+data[1]), new IntWritable(Integer.parseInt(data[2])));
	}
	
}
