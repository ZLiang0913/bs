package com.mydemo.NumberOfLearners.first;

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
public class HeatmapMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		//数据：7214545,2017-04-02 14:24:43.0,2017-04-02 15:28:17.0
		String line = value.toString();
		String[] arrays = line.split(",");
		List<String> days=new ArrayList<>();
		List<String> hours=new ArrayList<>();
		for (int i = 1; i < arrays.length; i++) {
			 String[] split = arrays[i].split(" ");
			 days.add(split[0]);
			 hours.add(split[1].substring(0, 2));
		}
	
//		2017-04-02
//		14
//		2017-04-02
//		15
		if (days.get(0).equals(days.get(1))) {//先判断是不是在同一天
			if (hours.get(0).equals(hours.get(1))) {//在同一个小时之内
				context.write(new Text(days.get(0)), new Text(hours.get(0)+"*"+1));
				//context.write(new Text(days.get(0)+","+hours.get(0)+"*"+1), NullWritable.get());
			}else{
				for (int i = Integer.parseInt(hours.get(0)); i <= Integer.parseInt(hours.get(1)); i++) {
					context.write(new Text(days.get(0)), new Text(String.format("%02d", i)+"*"+1));
					//context.write(new Text(days.get(0)+","+String.format("%02d", i)+"*"+1), NullWritable.get());
				}
			}
			
		}else{//不在同一天的时间，排除时间相同的情况
			for (int i = Integer.parseInt(hours.get(0)); i <= Integer.parseInt(hours.get(1))+24; i++) {
				//i<24说明在前一天，取前一天的日期
				if(i<24){
					context.write(new Text(days.get(0)), new Text(String.format("%02d", i)+"*"+1));
					//context.write(new Text(days.get(0)+","+String.format("%02d", i)+"*"+1), NullWritable.get());
					
				}else{
					context.write(new Text(days.get(1)), new Text(String.format("%02d", i%24)+"*"+1));
					//context.write(new Text(days.get(1)+","+String.format("%02d", i%24)+"*"+1), NullWritable.get());
				}
			}
		}
	}
	
}
