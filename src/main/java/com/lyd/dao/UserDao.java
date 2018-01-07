package com.lyd.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.lyd.pojo.User;
import com.lyd.utils.ThreadLocalTool;

public class UserDao {
	
	public User login(String username) {
		
		QueryRunner queryRunner= new QueryRunner(ThreadLocalTool.getDataSource());
		
		String sql="select * from users where username =?";
		User user=null;
		try {
		     user=queryRunner.query(sql, new BeanHandler<User>(User.class),username);
		     
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 	return user;
	}
	
}
