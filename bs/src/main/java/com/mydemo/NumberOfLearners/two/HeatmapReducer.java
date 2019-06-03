package com.mydemo.NumberOfLearners.two;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.alibaba.fastjson.JSONArray;
import com.mydemo.hbase.dao.HBaseDAO;
import com.mydemo.hbase.dao.imp.HBaseDAOImp;
import com.mydemo.util.JsonUtils;
import com.mydemo.util.StringToDate;

public class HeatmapReducer extends Reducer<Text, IntWritable, Text, NullWritable> {
	
	
	
	//private HBaseDAO bHBaseDAO =new HBaseDAOImp();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,Context context) throws IOException, InterruptedException {
		String[] split = key.toString().split(",");
		
		//1,23 <2,3>
		int count=0;
		for (IntWritable v : value) {
			count+=v.get();
		}
		
		//获取key
		
		
		String path = "d://file.json";
		String jsonStr = JsonUtils.readJsonFile(path);  

		//原始json数据
		JSONArray jsonArray = JSONArray.parseArray(jsonStr);
		
		
		JSONArray jsonArray2 = new JSONArray();
		jsonArray2.add(Integer.parseInt(split[0]));
		jsonArray2.add(Integer.parseInt(split[1]));
		jsonArray2.add(count);
		
		//处理过的json数据
		jsonArray.add(jsonArray2);
		JsonUtils.writeJsonFile(jsonArray.toString(), path);
		
		context.write(new Text(key.toString()+","+count), NullWritable.get());
	}

}
