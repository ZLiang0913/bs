package net.wanho.dao.impl;

import net.wanho.dao.UserDao;
import net.wanho.entity.TbUser;
import net.wanho.util.JDBCutil;

import java.util.List;

public class UserDaoImpl implements UserDao {
    JDBCutil jdbcutil = JDBCutil.getInstance();
    @Override
    public List<TbUser> getUsers(String where) {
        String sql = "select * from tb_user where 1=1 and "+where;
        System.out.println(sql);
        List<TbUser> tbUsers = jdbcutil.queryUser(sql);
        return tbUsers;
    }

    @Override
    public void addUser(TbUser user) {
        String sql = "insert into tb_user (username,password,phone,created,updated,source_type) VALUES(?,?,?,?,?,?)";
        System.out.println(sql);
        jdbcutil.update(sql, user.getUsername(),user.getPassword(),user.getPhone(),user.getCreated(),user.getUpdated(),user.getSourceType());
    }

    @Override
    public void delUser(TbUser user) {

    }

    @Override
    public void updateUser(TbUser user) {

    }
}
