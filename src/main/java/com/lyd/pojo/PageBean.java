package com.lyd.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Young
 * @description 用来做分页 加 条件查询
 * @date 2018年1月5日 上午12:43:14
 *
 */
public class PageBean implements Serializable {

	/**
     * 
	 */
	private static final long serialVersionUID = 365180146022291357L;

	private String pname;

	private Integer curPage;

	private String dept;

	private List<Student> list;

	private Integer count;

	private Integer totalPage;

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageBean(String pname, Integer curPage, String dept, List<Student> list, Integer count, Integer totalPage) {
		super();
		this.pname = pname;
		this.curPage = curPage;
		this.dept = dept;
		this.list = list;
		this.count = count;
		this.totalPage = totalPage;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public List<Student> getList() {
		return list;
	}

	public void setList(List<Student> list) {
		this.list = list;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "PageBean [pname=" + pname + ", curPage=" + curPage + ", dept=" + dept + ", list=" + list + ", count="
				+ count + ", totalPage=" + totalPage + "]";
	}

}
