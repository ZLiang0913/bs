package com.mydemo.Index.first;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.mydemo.hbase.dao.HBaseDAO;
import com.mydemo.hbase.dao.imp.HBaseDAOImp;

public class IndexReducer extends Reducer<Text, Text, Text, NullWritable> {

	private HBaseDAO bHBaseDAO =new HBaseDAOImp();
	
	@Override
	protected void reduce(Text key, Iterable<Text> value,Context context) throws IOException, InterruptedException {
		//154000 <*高等数学,1,1,1>
		
		int count = 0;
		String courseName = null;
		for(Text v:value){
			String str = v.toString();
			if(str.contains("*")){
				courseName = str.substring(str.indexOf("*")+1);
			}else{
				count+= Integer.parseInt(str);
			}
		}
		
		
//		disable '表名'
//		alter '表明', NAME => '列名'
//		enable '表名'
		//插入hbase中
		bHBaseDAO.insert("zt_index", courseName, "info", "num", String.valueOf(count));
		
		//login_date,count
		context.write(new Text(courseName+","+String.valueOf(count)),NullWritable.get());
	}

}
