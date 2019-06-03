package com.mydemo.LearningStuday.two;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.mydemo.hbase.dao.HBaseDAO;
import com.mydemo.hbase.dao.imp.HBaseDAOImp;


public class LearningStudayReducer extends Reducer<Text, IntWritable, Text, NullWritable> {

	private HBaseDAO bHBaseDAO =new HBaseDAOImp();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,Context context) throws IOException, InterruptedException {
		//26577759_20170411 <77,25,86>
		
		int count = 0;
		for(IntWritable v:value){
			count += v.get();
		}
		
		//26577759_20170411,取得时间
		String[] result = key.toString().split("_");
		String userId =result[0];
		String dateValue =result[1];
		
		//updated_date,study_times
		//写入hdfs中
		context.write(new Text(userId+","+dateValue+","+count),NullWritable.get());
	}

}
