package com.mydemo.LearningStuday.three;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.mydemo.hbase.dao.HBaseDAO;
import com.mydemo.hbase.dao.imp.HBaseDAOImp;


public class LearningStudayReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private HBaseDAO bHBaseDAO =new HBaseDAOImp();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,Context context) throws IOException, InterruptedException {
		//20170401,<136,6>
		int count = 0;
		int size = 0;
		for(IntWritable v:value){
			count += v.get();
			size++;
		}
		
		int result = (int)Math.floor(count/size);
		//插入hbase中
		bHBaseDAO.insert("zt", key.toString(), "LearningStuday", "avg_studay_time", String.valueOf(result));
		
		//date_value, floor(AVG(sum_study_time))
		//写入hdfs中
		context.write(new Text(key),new IntWritable(result));
	}

}
