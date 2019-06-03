package net.wanho.dao;

import java.util.List;

import net.wanho.entity.TbUser;


public interface UserDao {
	public List<TbUser> getUsers(String where);
	public void addUser(TbUser user);
	public void delUser(TbUser user);
	public void updateUser(TbUser user);
}
