package net.wanho.springsecurity;

import com.aliyuncs.exceptions.ClientException;
import net.wanho.dao.UserDao;
import net.wanho.dao.impl.UserDaoImpl;
import net.wanho.entity.Result;
import net.wanho.entity.TbUser;
import net.wanho.util.PhoneFormatCheckUtils;
import net.wanho.util.SmsDemo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {
	String phonecode="";
	@RequestMapping("/findLoginUser")
	public String findLoginUser(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("认证登录名："+name);
		return name;
	}

	/**
	 * 增加
	 * @param user
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbUser user, String smscode){
		boolean checkSmsCode = checkSmsCode(user.getPhone(), smscode);
		if(checkSmsCode==false){
			return new Result(false, "验证码输入错误！");
		}



		UserDao userDao = new UserDaoImpl();

		try {
			List<TbUser> users = userDao.getUsers("username='" + user.getUsername()+"'");
			if(users.size()>0){//用户已存在
				return new Result(false, "用户已存在");
			}
			user.setCreated(new Date());//用户注册时间
			user.setUpdated(new Date());//修改时间
			user.setSourceType("1");//注册来源，PC
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));//密码md5加密
			userDao.addUser(user);

			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}

	/**
	 * 发送短信验证码
	 * @param phone
	 * @return
	 */
	@RequestMapping("/sendCode")
	public Result sendCode(String phone){
		//判断手机号格式
		if(!PhoneFormatCheckUtils.isPhoneLegal(phone)){
			return new Result(false, "手机号格式不正确");
		}
		try {
			createSmsCode(phone);//生成验证码
			return new Result(true, "验证码发送成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(true, "验证码发送失败");
		}

	}

	public void createSmsCode(final String phone) {
		//1.生成6位随机数
		final String code = (long)(Math.random()*1000000)+"";
		System.out.println("验证码："+code);
		//2.将验证码存入全局变量
		phonecode = code;
		//将生成的验证码发送到阿里大鱼的服务
		try {
			SmsDemo.sendSms(phone,phonecode);
		} catch (ClientException e) {
			e.printStackTrace();
		}


	}


	public boolean checkSmsCode(String phone, String code) {
		String smscode = phonecode;
		if(smscode==null){
			return false;
		}
		if(!smscode.equals(code)){
			return false;
		}
		return true;
	}

}
