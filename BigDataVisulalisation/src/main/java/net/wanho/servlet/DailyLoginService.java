package net.wanho.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.wanho.dao.HBaseDAO;
import net.wanho.dao.impl.HBaseDAOImp;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DailyLoginService extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("近來了DailyLoginService");
        String id = "2017"+req.getParameter("id");

        System.out.println(id);
        //创建DAO
        HBaseDAO hBaseDAO = new HBaseDAOImp();
        //从hbase里通过scan方式取数据
        //List<DailyLogin> results = null;
        List<Result> results = null;
        try {
            //results = hBaseDAO.getScan("zt","DailyLogin","count",DailyLogin.class.getConstructor(String.class,String.class));
             results = hBaseDAO.getRows("zt", id, "DailyLogin", new String[]{"count"});
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置服务器响应时向JSP表示层传输数据的编码格式
        resp.setContentType("text/html; charset=utf-8");
        //JSON对象
        JSONArray jsonArray = new JSONArray();
        for (Result r:results){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("loginDate",Bytes.toString(r.getRow()));
            jsonObject.put("count",Bytes.toString(r.getValue("DailyLogin".getBytes(),"count".getBytes())));
            jsonArray.add(jsonObject);
        }



        //将json返回到jsp
        resp.getWriter().write(jsonArray.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
