package com.lyd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.StringUtils;

import com.lyd.pojo.PageBean;
import com.lyd.pojo.Student;
import com.lyd.utils.ThreadLocalTool;

/**
 * 
 * @author Young
 * @description 学生业务模块的持久层
 * @date 2018年1月4日 上午12:37:30
 *
 */

 //如何写sql 统计出 所有姓氏 对应的人数呢？
//SELECT SUBSTRING(pname,1,1) AS 姓氏,COUNT(*) AS 次数 FROM student GROUP BY SUBSTRING(pname,1,1);
public class StudentDao {

	/**
	 * 
	 * @return 返回一个封装了所有学生的集合
	 */
	public List<Student> getAllStudents() {

		QueryRunner query = new QueryRunner(ThreadLocalTool.getDataSource());

		String sql = "select * from student ";

		try {
			List<Student> students = query.query(sql, new BeanListHandler<>(Student.class));
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据指定 id 删除学生
	 * 
	 * @param id
	 *            学生id
	 * @throws SQLException
	 */
	public void deleteStudentById(Long id) throws SQLException {
		QueryRunner query = new QueryRunner();
		Connection conn = ThreadLocalTool.getConByThread();
		String sql = "delete from student where id= ?";
		query.update(conn, sql, id);
	}
	/**
	 * 根据id 修改指定学生的电话号码
	 * @throws SQLException 
	 */
	public void updateTelephoneById(String telephone,Long id) throws SQLException {
		QueryRunner query=new QueryRunner();
		Connection conn=ThreadLocalTool.getConByThread();
		String sql="update student set telephone=? where id = ? ";
		query.update(conn, sql, telephone,id);
	}
	/**
	 * 批量插入学生
	 * @param list  封装了学生的集合
	 * @throws SQLException 
	 */
	public void insertStudentByBatch(List<Student> list) throws SQLException {
		Connection connection=ThreadLocalTool.getConByThread();
		
		String sql="INSERT INTO student(dept,pname,finger)VALUES(?,?,?);";
		
		PreparedStatement statement=connection.prepareStatement(sql);
				  for (Student student : list) {
					   statement.setString(1,student.getDept());
					   statement.setString(2,student.getPname());
					   statement.setString(3,student.getFinger());
					   statement.addBatch();
				    }
				  	   statement.executeBatch();
	}
	/**
	 * 
	 * @param pname 模糊查询所用到的姓名
	 * @return  返回根据模糊查询所得到的Student集合
	 * @throws SQLException
	 */
	public List<Student> queryStudentByName(String pname) throws SQLException{
		QueryRunner query=new QueryRunner(ThreadLocalTool.getDataSource());
		String sql="select * from student where pname like ?";
		List<Student> students = query.query(sql,new BeanListHandler<>(Student.class),"%"+pname+"%");
		return  students;
	}
	/**
	 * 
	 * @return
	 * 根据班级名称查询学生
	 */
	public List<Student> queryStudentByDeptName(String dept){
		
		 return null;
	}
	/**
	 * 条件查询 数据的总个数,难点在于如何拼接动态sql 像mybatis  那样
	 * @param pageBean
	 * @return
	 * @throws SQLException 
	 * SELECT COUNT(s.id) FROM (SELECT * FROM student WHERE pname LIKE '%李%') s;
	 */
	public Integer getCountsOfStudents(PageBean pageBean) throws SQLException {
		if(pageBean==null) {
			return 0;
		}
		String pname=pageBean.getPname();
		
		String dept=pageBean.getDept();
		//改用 Object[]arr 数据来为占位符 设置参数 
		//占位符
		List<String> list=new ArrayList<>();
		
		String part1="select count(id) from student ";
		
		String part2=null;
				
		
			if(StringUtils.isNotBlank(pname)&&StringUtils.isBlank(dept)) {
			   part2="where pname like ? " ;
			   list.add("%"+pname+"%");
			}
			if(StringUtils.isNotBlank(dept)&&StringUtils.isBlank(pname)) {
			   part2="where dept like ?";
			   list.add("%"+dept+"%");
			}
			if(StringUtils.isNotBlank(pname)&&StringUtils.isNotBlank(dept)) {
			   part2=" where pname like ? and dept like ?" ;
			   list.add("%"+pname+"%");
			   list.add("%"+dept+"%");
			}
		
		//拼sql
		String sql=part2!=null?part1+part2:part1;
		
		System.out.println(sql);
		QueryRunner query=new QueryRunner(ThreadLocalTool.getDataSource());
		
		Long query2 =null;
		
		if(part2==null) {
			query2= query.query(sql, new ScalarHandler<Long>());
		}else {
			//坑      传参只能传数组 
			Object[]arr=new Object[list.size()];
			query2= query.query(sql, new ScalarHandler<Long>(),list.toArray(arr));
		}
		return query2.intValue();
	}
	/**
	 * 条件查询加分页
	 * @param start
	 * @param i
	 * @param pname
	 * @param dept
	 * @return
	 */
	public List<Student> getStudentsPages(int start, int pageSize, String pname, String dept) {
		String part1="SELECT * FROM student ";
		
		String part2=null;
		
		
		
		    List<Object> list=new ArrayList<>();
		
			if(StringUtils.isNotBlank(pname)&&StringUtils.isBlank(dept)) {
			   part2="where pname like ? " ;
			   list.add("%"+pname+"%");
			}
			if(StringUtils.isNotBlank(dept)&&StringUtils.isBlank(pname)) {
			   part2="where dept like ?";
			   list.add("%"+dept+"%");
			}
			if(StringUtils.isNotBlank(pname)&&StringUtils.isNotBlank(dept)) {
			   part2=" where pname like ? and dept like ?" ;
			   list.add("%"+pname+"%");
			   list.add("%"+dept+"%");
			}
			//拼sql
			String sql=part2!=null?part1+part2:part1;
			
			String part3=" limit ? ,? ";
			
			list.add(start);
			
			list.add(pageSize);
			
			sql+=part3;
			
			System.out.println(sql);
			
			Object[] object= new  Object[list.size()];
			
			QueryRunner query=new QueryRunner(ThreadLocalTool.getDataSource());
			
			try {
				List<Student> query2 = query.query(sql, new BeanListHandler<>(Student.class),list.toArray(object));
					return query2;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					return null;
	}

	public Student getStudentById(Integer id) {
		
		String sql="select * from student where id = ?";
		
		QueryRunner query=new QueryRunner(ThreadLocalTool.getDataSource());
		
		try {
			Student query2 = query.query(sql, new BeanHandler<>(Student.class),id);
					return query2;
		} catch (SQLException e) {
			e.printStackTrace();
		}
					return null;
	}
	/**
	 * 插入学生
	 * @param student
	 * @throws SQLException 
	 */
	public void insertStudent(Student student) throws SQLException {
		String sql="INSERT INTO student(dept,pname,finger,telephone)VALUE (?,?,?,?)";
		
		QueryRunner query=new QueryRunner();
		
		Connection con=ThreadLocalTool.getConByThread();
		
		query.update(con,sql,student.getDept(),student.getPname(),student.getFinger(),student.getTelephone());
	}
	/**
	 * 修改学生
	 * @param student
	 * @throws SQLException 
	 */
	public void updateStudent(Student student) throws SQLException {
		
		String sql="UPDATE student SET dept= ? ,pname =? ,finger =? ,telephone =? WHERE id = ? ";
		
		QueryRunner query=new QueryRunner();
		
		Connection con=ThreadLocalTool.getConByThread();
		
		query.update(con, sql,student.getDept(),student.getPname(),student.getFinger(),
				student.getTelephone(),student.getId());
		
	}
	/**
	 * 根据班级名称删除
	 * @param deptName
	 * @throws SQLException 
	 */
	public void deleteByDept(String deptName) throws SQLException {
		String sql="delete from student where dept like ?";
		
		QueryRunner query=new QueryRunner();
		
		Connection con=ThreadLocalTool.getConByThread();
		
		query.update(con, sql,"%"+deptName+"%");
	}
	
	public void updateStudents(List<Student> list) throws SQLException {
		Connection connection=ThreadLocalTool.getConByThread();
		String sql="update Student set telephone =? where pname =?";
		PreparedStatement statement=connection.prepareStatement(sql);
				for (Student Student : list) {
					 statement.setString(1,Student.getTelephone());
					 statement.setString(2,Student.getPname());
					 statement.addBatch();
				}
				statement.executeBatch();
		
	}
}
