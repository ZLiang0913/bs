package net.wanho.servlet;

import com.alibaba.fastjson.JSONObject;
import net.wanho.dao.HBaseDAO;
import net.wanho.dao.impl.HBaseDAOImp;
import net.wanho.entity.DailyLogin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyLoginAllService extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("近來了DailyLoginAllService");

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //创建DAO
        HBaseDAO hBaseDAO = new HBaseDAOImp();
        //从hbase里通过scan方式取数据
        List<DailyLogin> results = null;
        try {
            results = hBaseDAO.getScan("zt","DailyLogin","count",DailyLogin.class.getConstructor(String.class,String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //设置服务器响应时向JSP表示层传输数据的编码格式
        resp.setContentType("text/html; charset=utf-8");
        //JSON对象
        JSONObject jsonObject = new JSONObject();
        List<String> xlist = new ArrayList<>();
        List<String> ylist = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for (DailyLogin r:results){
            String value  = r.getLoginDate().substring(4,6);
            if (map.containsKey(value)){
                if (r.getCount()!=null){
                    map.put(value,map.get(value)+Integer.parseInt(r.getCount()));
                }
            }else{
                if (r.getCount()==null){
                    //map.put(value,0); 如：03,0，前台可以不打印影响效果展示
                }else { //如果count的值为null
                    map.put(value,Integer.parseInt(r.getCount()));

                }
            }
            /*xlist.add();
            ylist.add(r.getCount());
            //得到单个数据：20170401 550
            System.out.println(r.getLoginDate()+","+r.getCount());*/
        }

        //map遍历
        for (String k:map.keySet()) {
            System.out.println(k+","+map.get(k));
            xlist.add(k);
            ylist.add(map.get(k).toString());
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
