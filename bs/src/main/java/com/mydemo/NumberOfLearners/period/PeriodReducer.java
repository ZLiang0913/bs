package com.mydemo.NumberOfLearners.period;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mydemo.hbase.dao.HBaseDAO;
import com.mydemo.hbase.dao.imp.HBaseDAOImp;
import com.mydemo.util.JsonUtils;
import com.mydemo.util.StringToDate;

public class PeriodReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
	
	
	
	//private HBaseDAO bHBaseDAO =new HBaseDAOImp();
	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> value,Context context) throws IOException, InterruptedException {
		
		
		//<0,1,1,1,0>
		int count=0;
		for (IntWritable v : value) {
			count+=v.get();
		}

		String path = "d://file.json";
		String jsonStr = JsonUtils.readJsonFile(path);  

		JSONArray array = JSON.parseArray(jsonStr);
		
		//jsonArray.add(jsonArray2);
		array.add(count);
		
		JsonUtils.writeJsonFile(array.toString(), path);
		
		context.write(key, new IntWritable(count));
	}

}
