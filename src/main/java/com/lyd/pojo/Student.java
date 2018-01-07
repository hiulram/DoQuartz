package com.lyd.pojo;

import java.io.Serializable;

/**
 * 
 * @author Young
 * @description 学生类 对应数据库中的student表
 * @date 2018年1月4日 上午12:34:30
 *
 */
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4145103236926117949L;

	private Long id;

	private String dept;

	private String pname;

	private String finger;

	private String telephone;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Long id, String dept, String pname, String finger, String telephone) {
		super();
		this.id = id;
		this.dept = dept;
		this.pname = pname;
		this.finger = finger;
		this.telephone = telephone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getFinger() {
		return finger;
	}
	public void setFinger(String finger) {
		this.finger = finger;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", dept=" + dept + ", pname=" + pname + ", finger=" + finger + ", telephone="
				+ telephone + "]";
	}
	
}
