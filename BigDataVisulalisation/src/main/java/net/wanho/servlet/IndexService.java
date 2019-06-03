package net.wanho.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.wanho.dao.HBaseDAO;
import net.wanho.dao.impl.HBaseDAOImp;
import net.wanho.entity.Index;
import net.wanho.util.Common;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IndexService extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("近來了");

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //创建DAO
        HBaseDAO hBaseDAO = new HBaseDAOImp();
        List<Index> results = null;
        //从hbase里通过scan方式取数据
        try {
            results = hBaseDAO.getScan("zt_index", "info", "num", Index.class.getConstructor(String.class, String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        int course=0;//课程数
        int courseSum =0;
        int student=0;//学生数
        Map<String,Integer> courseMap = new HashMap<>();
        Map<String,Integer> weekMap = new HashMap<>();

        for (Index r:results){
            System.out.println(r.getNum()+","+r.getRowkey());
            String rowkey = r.getRowkey();
            if (rowkey.contains("*")){
                student = Integer.parseInt(r.getNum());
            }else if (!Common.isInteger(rowkey)){
                course++;
                courseSum+=Integer.parseInt(r.getNum());
                courseMap.put(rowkey,Integer.parseInt(r.getNum()));
            }else if (Common.isInteger(rowkey)){
                weekMap.put(rowkey,Integer.parseInt(r.getNum()));
            }
        }

        //JSON对象
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseNum",course);
        jsonObject.put("studentNum",student);
        jsonObject.put("courseSum",courseSum);
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        //course数组   [{courseName:"计算机网络",rate:33%},courseName:"高等数学",rate:43%}]
        JSONArray courseArray = new JSONArray();
        for(String key:courseMap.keySet()){
            JSONObject obj = new JSONObject();
            float num= (float)courseMap.get(key)/courseSum;
            String rate = df.format(num*100)+"%";
            obj.put("courseName",key);
            obj.put("rate",rate);

            courseArray.add(obj);
        }
        jsonObject.put("course",courseArray);

        //week   [{weekNum:12,activityNum:18},courseName:22,rate:54}]
        JSONArray weekArray = new JSONArray();
        for(String key:weekMap.keySet()){
            JSONObject obj = new JSONObject();
            obj.put("weekNum",key);
            obj.put("activityNum",String.valueOf(weekMap.get(key)));
            weekArray.add(obj);
        }
        jsonObject.put("week",weekArray);


        //将json返回到jsp
        resp.getWriter().write(jsonObject.toString());


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
