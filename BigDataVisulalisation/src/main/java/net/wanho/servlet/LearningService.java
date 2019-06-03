package net.wanho.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.wanho.dao.HBaseDAO;
import net.wanho.dao.impl.HBaseDAOImp;
import net.wanho.entity.LearningNumber;
import net.wanho.entity.LearningStuday;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LearningService extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("近來了LearningService");

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //创建DAO
        HBaseDAO hBaseDAO = new HBaseDAOImp();
        //从hbase里通过scan方式取数据
        List<LearningStuday> results = null;
        List<LearningNumber> result = null;

        try {
            results = hBaseDAO.getScan("zt","LearningStuday","avg_studay_time",LearningStuday.class.getConstructor(String.class,String.class));
            result = hBaseDAO.getScan("zt","LearningNumber","count",LearningNumber.class.getConstructor(String.class,String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置服务器响应时向JSP表示层传输数据的编码格式
        resp.setContentType("text/html; charset=utf-8");
        //JSON对象
        JSONObject json = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONArray dateValue= new JSONArray();
        JSONArray avgStudyTime= new JSONArray();
        for (LearningStuday r:results){
            System.out.println(r.getDateValue()+","+r.getAvgStudyTime());
            dateValue.add(r.getDateValue());
            avgStudyTime.add(r.getAvgStudyTime());
        }
        jsonObject1.put("dateValue",dateValue);
        jsonObject1.put("avgStudyTime",avgStudyTime);
        json.put("LearningStuday",jsonObject1);

        JSONObject jsonObject2 = new JSONObject();
        JSONArray updatedDate= new JSONArray();
        JSONArray studyTimes= new JSONArray();
        for (LearningNumber r:result){
            System.out.println(r.getUpdatedDate()+","+r.getStudyTimes());
            updatedDate.add(r.getUpdatedDate());
            studyTimes.add(r.getStudyTimes());
        }
        jsonObject2.put("updatedDate",updatedDate);
        jsonObject2.put("studyTimes",studyTimes);
        json.put("LearningNumber",jsonObject2);



        //将json返回到jsp
        resp.getWriter().write(json.toJSONString());


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
