package com.mydemo.DailyActivity.first;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StudyTimesReducer extends Reducer<Text, IntWritable, Text, NullWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,
			Reducer<Text, IntWritable, Text, NullWritable>.Context context) throws IOException, InterruptedException {
		//13696657_20170320 <1,1,1>
		
		int count = 0;
		for(IntWritable v:value){
			count += v.get();
		}
		
		//拆分key
		String[] words = key.toString().split("_");
		
		//13696657,20170320,3
		String result = words[0];
		for (int i = 1; i < words.length; i++) {
			result += ","+words[i];
			if (i==words.length-1) {
				result += ","+count;
			}
		}
		//user_id,updated_date,study_times
		context.write(new Text(result),NullWritable.get());
	}

}
