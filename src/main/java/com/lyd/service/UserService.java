package com.lyd.service;

import com.lyd.dao.UserDao;
import com.lyd.pojo.User;

public class UserService {
	
	/**
	 * 
	 * @param userFalse 页面传过来的 User
	 * @return
	 */
   public User login(User userFalse) {
	 UserDao dao=new UserDao();
	 
	 User user=dao.login(userFalse.getpassword());
	 
	 	if(user!=null&&user.getpassword().equals(userFalse.getpassword())) {
	 		return user;
	 	}
	 		return null;
	   
   }
}
