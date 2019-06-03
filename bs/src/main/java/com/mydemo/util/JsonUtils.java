package com.mydemo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JsonUtils {
	
	/**
     * 读取json文件
     */
    public static String readJsonFile(String path){
        String laststrJson = "";
        BufferedReader reader;  
        try {  
            reader = new BufferedReader(new FileReader(new File(path)));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                laststrJson = laststrJson + tempString;
                line++;
            }
            reader.close();  
        } catch (IOException e1) {
            e1.printStackTrace();  
        }
        return laststrJson;
    }

    /**
     * 写出json文件
     */
    public static void writeJsonFile(String newJsonString, String path){
        try {
            FileWriter fw = new FileWriter(path);
            PrintWriter out = new PrintWriter(fw);  
            out.write(newJsonString);  
            out.println();  
            fw.close();  
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		
		String path = "d://file.json";
		String jsonStr = JsonUtils.readJsonFile(path);  

		JSONArray array = JSON.parseArray(jsonStr);
		System.out.println("old:"+array);
		
		
		//jsonArray.add(jsonArray2);
		array.add(4);
		array.add(4);
		array.add(4);
		System.out.println("new:"+array);
		System.out.println(array.toString());
		JsonUtils.writeJsonFile(array.toString(), path);
		

	}

}
