package com.mydemo.DailyActivity.two;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.mydemo.hbase.dao.HBaseDAO;
import com.mydemo.hbase.dao.imp.HBaseDAOImp;


public class StudyTimesReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private HBaseDAO bHBaseDAO =new HBaseDAOImp();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,Context context) throws IOException, InterruptedException {
		//20170320 <1,1,1>
		
		int count = 0;
		for(IntWritable v:value){
			count += v.get();
		}
		
		//20170320,3
		String result = key.toString();
		
		
		//create 'zt','DailyActivity'
		//插入hbase中
		bHBaseDAO.insert("zt", result, "DailyActivity", "count", String.valueOf(count));
		
		//updated_date,study_times
		//写入hdfs中
		context.write(new Text(result),new IntWritable(count));
	}

}
