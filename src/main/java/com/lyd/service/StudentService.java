package com.lyd.service;
/**
 * 
 * @author Young
 * @description   学生模块的业务层
 * @date   2018年1月4日 上午12:41:26 
 *
 */

import com.lyd.dao.StudentDao;
import com.lyd.pojo.PageBean;
import com.lyd.pojo.Student;
import com.lyd.utils.ThreadLocalTool;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {
	/**
	 * 
	 * @return  指纹编号做key  value 为该编号对应的 学生
	 */
	public Map<String, Student> selectAllStudents() {
		StudentDao dao = new StudentDao();
		List<Student> allStudents = dao.getAllStudents();
		if (allStudents != null && allStudents.size() != 0) {
			Map<String, Student> map = new HashMap<>();

			for (Student student : allStudents) {
				
				map.put(student.getFinger(), student);
				
			}
			return  map;
		}
		    return null;
	}
	/**
	 * 根据id 删除学生
	 * @param  id
	 */
	public void deleteStudentById(Long id) {
		StudentDao dao=new StudentDao();
		try {
			ThreadLocalTool.startTransation();
			
			dao.deleteStudentById(id);
			
			ThreadLocalTool.commitTransationAndClose();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			ThreadLocalTool.roolBack1AndClose();
		}
	}
	/**
	 * 
	 * @param telephone 电话号码
	 * @param id        学生id
	 */
	public void updateTelephoneById(String telephone,Long id) {
		StudentDao dao=new StudentDao();
		try {
			ThreadLocalTool.startTransation();
			dao.updateTelephoneById(telephone, id);
			ThreadLocalTool.commitTransationAndClose();
		} catch (SQLException e) {
			e.printStackTrace();
		ThreadLocalTool.roolBack1AndClose();
		}
		
	}
	/**
	 * 批量插入学生
	 * @param list
	 */
	public void insertStudentByBatch(List<Student> list) {
		StudentDao dao=new StudentDao();
		try {
			ThreadLocalTool.startTransation();
			dao.insertStudentByBatch(list);
			ThreadLocalTool.commitTransationAndClose();
		} catch (SQLException e) {
			e.printStackTrace();
			ThreadLocalTool.roolBack1AndClose();
		}
	}
	/**
	 * 对学生进行模糊查询 根据姓名
	 * @param pname
	 * @return
	 */
	public List<Student>  queryStudnetByName(String pname) {
		StudentDao dao=new StudentDao();
		try {
			List<Student> students = dao.queryStudentByName(pname);
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 用来做分页的核心方法
	 * @param pageBean
	 * @return
	 * @throws SQLException 
	 */
	public PageBean getStudents(PageBean pageBean) throws SQLException {
		StudentDao dao=new StudentDao();
		//总数据数
		
		Integer count=dao.getCountsOfStudents(pageBean);
		
		//总页数  设置也页面大小为 12
		Integer totalPage=count%12==0?count/12:(count/12+1);
		
		Integer curPage =pageBean.getCurPage()==null?1:pageBean.getCurPage();
		
		int start =(curPage-1)*12;
		
		
		List<Student> list=dao.getStudentsPages(start,12,pageBean.getPname(),pageBean.getDept());
		
		PageBean page=new PageBean();
				 page.setCount(count);
				 page.setCurPage(curPage);
				 page.setDept(pageBean.getDept());
				 page.setPname(pageBean.getPname());
				 page.setList(list);
				 page.setTotalPage(totalPage);
				 
				 
		  return page;
	}
	public Student getStudentById(Integer id) {
		
		StudentDao dao=new StudentDao();
		
		Student student=dao.getStudentById(id);
		
		return student;
	}
	/**
	 * 添加或更新学生
	 * @param student
	 */
	public void updateOrSave(Student student) {
		StudentDao dao=new StudentDao();
		Long id=student.getId();
		try {
			ThreadLocalTool.startTransation();
			if(id!=null) {
				dao.updateStudent(student);
			}else {
				dao.insertStudent(student);
			}
			ThreadLocalTool.commitTransationAndClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			ThreadLocalTool.roolBack1AndClose();
		}
	}
	/**
	 * 
	 * @param deptName
	 */
	public void deleteByDept(String deptName) {
		StudentDao dao=new StudentDao();
		try {
			ThreadLocalTool.startTransation();
			
			dao.deleteByDept(deptName);
			
			ThreadLocalTool.commitTransationAndClose();
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			ThreadLocalTool.roolBack1AndClose();
		}
	}
	/**
	 * 增加电话号码
	 * @param list
	 */
	public void updateStudents(List<Student> list) {
		StudentDao dao=new StudentDao();
				   try {
					   ThreadLocalTool.startTransation();
					dao.updateStudents(list);
						ThreadLocalTool.commitTransationAndClose();
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				    ThreadLocalTool.roolBack1AndClose();
				}
	}
}
