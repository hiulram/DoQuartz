package com.lyd.pojo;

import java.io.Serializable;

public class Server implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 214673429802241740L;

	private Integer id;
	
	private String ip;
	
	private Integer num;

	public Server() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Server(Integer id, String ip, Integer num) {
		super();
		this.id = id;
		this.ip = ip;
		this.num = num;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "Server [id=" + id + ", ip=" + ip + ", num=" + num + "]";
	}
	
	
}
