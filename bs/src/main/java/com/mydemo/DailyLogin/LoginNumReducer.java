package com.mydemo.DailyLogin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.mydemo.hbase.dao.HBaseDAO;
import com.mydemo.hbase.dao.imp.HBaseDAOImp;

public class LoginNumReducer extends Reducer<Text, IntWritable, Text, NullWritable> {
	
	private HBaseDAO bHBaseDAO =new HBaseDAOImp();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,
			Reducer<Text, IntWritable, Text, NullWritable>.Context context) throws IOException, InterruptedException {
		//20170401 <1,1,1>
		
		int count = 0;
		for(IntWritable v:value){
			count += v.get();
		}
		
//		disable '表名'
//		alter '表明', NAME => '列名'
//		enable '表名'
		//插入hbase中
		bHBaseDAO.insert("zt", key.toString(), "DailyLogin", "count", String.valueOf(count));
		
		//login_date,count
		context.write(new Text(key+","+String.valueOf(count)),NullWritable.get());
	}

}
