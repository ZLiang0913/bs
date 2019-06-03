package net.wanho.util;

import net.wanho.entity.TbUser;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class JDBCutil {
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/pinyougoudb?characterEncoding=utf-8&autoReconnect=true";
	private static final String USERNAME="root";
	private static final String PASSWORD="root";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static JDBCutil jdbCutil=null;
	private JDBCutil() {}
	public static JDBCutil getInstance() {
		if(jdbCutil==null) {
			jdbCutil=new JDBCutil();
		}
		return jdbCutil;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public int update(String sql,Object...objects) {
		
		Connection connection=null;
		PreparedStatement ps=null;
		int num=0;
		try {
			connection=getConnection();
			ps=connection.prepareStatement(sql);
			for(int i=0;i<objects.length;i++)
				ps.setObject(i+1, objects[i]);
			System.out.println(sql);
			num=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	//查询用户
	public    List<TbUser>     queryUser(String  sql, Object...object){
		List<TbUser>  list=new   ArrayList<TbUser>();
		Connection  con=null;
		PreparedStatement  pstat=null;
		ResultSet   rs=null;
		try {
			con=getConnection();
			pstat=con.prepareStatement(sql);
			for (int i = 0; i < object.length; i++) {
				pstat.setObject(i+1, object[i]);
			}
			rs=pstat.executeQuery();
			while(rs.next()){
				Long  id=(Long)rs.getObject("id");
				String  username=(String)rs.getObject("username");
				String  password=(String)rs.getObject("password");
				String  phone=(String)rs.getObject("phone");


				TbUser    user=new   TbUser();
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setPhone(phone);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(con, pstat, rs);
		}
		return list;
	}

	public void close(Connection con,PreparedStatement ps,ResultSet rs) {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		if (ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}			

}
