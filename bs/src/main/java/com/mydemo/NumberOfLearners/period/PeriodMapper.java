package com.mydemo.NumberOfLearners.period;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.mydemo.util.StringToDate;
/**
 * 哪些时间段的学习人数最多
 * 自定义mapper输出类型
 * LogMapper
 * 创建人:zhaoliang 
 * 时间：2018年5月2日-下午6:34:50 
 * @version 1.0.0
 *
 */
public class PeriodMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		//hours表中的数据：0
		//zt_stu_study_time_beihang表中的数据：7214545,2017-04-02 14:24:43.0,2017-04-02 15:28:17.0
		String line = value.toString();
		String[] data = line.split(",");
		if (data.length==1) {//来自hours表
			context.write(new IntWritable(Integer.parseInt(data[0])), new IntWritable(0));
			
		}else{//来自zt_stu_study_time_beihang表
			int startTime = Integer.parseInt(StringToDate.dateToString(StringToDate.turnDate(data[1],"yyyy-MM-dd HH:mm:ss"),"HH"));
			int endTime = Integer.parseInt(StringToDate.dateToString(StringToDate.turnDate(data[2],"yyyy-MM-dd HH:mm:ss"),"HH"));
			
			//问题：是否在同一天的情况
			//同一天
			//if(startTime<endTime) 遍历时间段：startTime-endTime
			for (int i = startTime; i <= endTime; i++) {
				context.write(new IntWritable(i), new IntWritable(1));
				
			}
			//非同一天
			//if(startTime>endTime) 遍历时间段：24*(天数)-(startTime-endTime)
			//if(startTime<endTime) 遍历时间段：24*(天数)+(endTime-startTime)
		}
	}
	
}
