package com.lyd.pojo;

import java.io.Serializable;
	/**
	 * 
	 * @author Young
	 * @description  用户实体类 
	 * @date   2018年1月3日 上午11:20:09 
	 *
	 */
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6656971210202362686L;

	private Integer id;
	
	private String username;
	
	private String password;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	
}
