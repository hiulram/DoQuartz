package com.lyd.pojo;

import java.io.Serializable;

public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 733869110988340189L;

	private Integer id;
	
	private String pname;
	
	private String telephone;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(Integer id, String pname, String telephone) {
		super();
		this.id = id;
		this.pname = pname;
		this.telephone = telephone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", pname=" + pname + ", telephone=" + telephone + "]";
	}
	
	
}
