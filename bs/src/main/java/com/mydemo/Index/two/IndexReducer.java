package com.mydemo.Index.two;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.mydemo.hbase.dao.HBaseDAO;
import com.mydemo.hbase.dao.imp.HBaseDAOImp;

public class IndexReducer extends Reducer<IntWritable, IntWritable, Text, NullWritable> {

	private HBaseDAO bHBaseDAO =new HBaseDAOImp();
	
	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> value,Context context) throws IOException, InterruptedException {
		//01 <1,6,1>
		
		int count = 0;
		for(IntWritable v:value){
			count+= v.get();
		}
		
		
//		disable '表名'
//		alter '表明', NAME => '列名'
//		enable '表名'
		//插入hbase中
		bHBaseDAO.insert("zt_index", key.toString(), "info", "num", String.valueOf(count));
		
		//login_date,count
		context.write(new Text(key.toString()+","+String.valueOf(count)),NullWritable.get());
	}

}
