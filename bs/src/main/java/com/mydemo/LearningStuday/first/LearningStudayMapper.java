package com.mydemo.LearningStuday.first;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import com.mydemo.util.StringToDate;;

public class LearningStudayMapper extends Mapper<LongWritable, Text, Text, NullWritable>{

	@Override
	protected void map(LongWritable key, Text value,Context context) {
		// 7214545,2017-04-02 14:24:43.0,2017-04-02 15:28:17.0
		// 7214545,2017-04-08 16:00:32.0,2017-04-08 16:18:59.0
		//获取一行数据
		String line = value.toString();
		//分片
		String[] words = line.split(",");
		
		String userId;
		String visitTime;
		Long studyTime;
		//数据处理
		//user_id, date_format(visit_time, '%Y%m%d') AS date_value ,timestampdiff(minute,visit_time,last_visit_time) AS study_time
		if(words.length!=3) {
			return;
		}else {
			userId = words[0];
			visitTime = StringToDate.dateToString(StringToDate.turnDate(words[1],"yyyy-MM-dd HH:mm:ss"),"yyyyMMdd");
			studyTime = StringToDate.getDateDiff(StringToDate.turnDate(words[2],"yyyy-MM-dd HH:mm:ss"),StringToDate.turnDate(words[1],"yyyy-MM-dd HH:mm:ss"));
			
		}
		
		//group by login_date
		try {
			context.write(new Text(userId+","+visitTime+","+studyTime), NullWritable.get());
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}
