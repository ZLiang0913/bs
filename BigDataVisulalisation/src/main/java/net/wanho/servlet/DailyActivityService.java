package net.wanho.servlet;

import com.alibaba.fastjson.JSONObject;
import net.wanho.dao.HBaseDAO;
import net.wanho.dao.impl.HBaseDAOImp;
import net.wanho.entity.DailyActivity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DailyActivityService extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("近來了DailyActivityService");

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //创建DAO
        HBaseDAO hBaseDAO = new HBaseDAOImp();
        //从hbase里通过scan方式取数据
        List<DailyActivity> results = null;
        try {
            results = hBaseDAO.getScan("zt","DailyActivity","count",DailyActivity.class.getConstructor(String.class,String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //设置服务器响应时向JSP表示层传输数据的编码格式
        resp.setContentType("text/html; charset=utf-8");
        //JSON对象
        JSONObject jsonObject = new JSONObject();
        List<String> xlist = new ArrayList<>();
        List<String> ylist = new ArrayList<>();
        for (DailyActivity r:results){

            xlist.add(r.getUpdatedDate());
            ylist.add(r.getCount());
        }
        jsonObject.put("y",ylist);
        jsonObject.put("x",xlist);


        //将json返回到jsp
        resp.getWriter().write(jsonObject.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
