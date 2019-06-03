package com.mydemo.NumberOfLearners.first;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.mydemo.hbase.dao.HBaseDAO;
import com.mydemo.hbase.dao.imp.HBaseDAOImp;
import com.mydemo.util.StringToDate;

public class HeatmapReducer extends Reducer<Text, Text, Text, NullWritable> {
	
	
	
	//private HBaseDAO bHBaseDAO =new HBaseDAOImp();
	@Override
	protected void reduce(Text key, Iterable<Text> value,Context context) throws IOException, InterruptedException {
		Map<String, Integer> map = new HashMap<>();
		map.put("星期六", 0);
		map.put("星期五", 1);
		map.put("星期四", 2);
		map.put("星期三", 3);
		map.put("星期二", 4);
		map.put("星期一", 5);
		map.put("星期日", 6);
		
		//2017-03-31 <23*1,23*1>
		Map<Integer, Integer> hour = new HashMap<>();
		
		for(Text v:value){
			String[] split = v.toString().split("\\*");
			if (hour.containsKey(Integer.parseInt(split[0]))) {
				hour.put(Integer.parseInt(split[0]), Integer.parseInt(split[1])+1);
			}else{
				hour.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			}
		}
		
		//将时间转成星期
		String week = StringToDate.dateToWeek(key.toString(), "yyyy-MM-dd");
		
		for (Integer h : hour.keySet()) {
			context.write(new Text(map.get(week)+","+h+","+hour.get(h)),NullWritable.get());
		}
		
//		disable '表名'
//		alter '表明', NAME => '列名'
//		enable '表名'
		//插入hbase中
		//bHBaseDAO.insert("zt", key.toString(), "LearningNumber", "count", String.valueOf(count));
		
		//login_date,count
		
	}

}
