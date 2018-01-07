package com.lyd.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ThreadLocalTool {
	 private static DataSource ds=new ComboPooledDataSource();
	 private static ThreadLocal<Connection>tl=new ThreadLocal<>();
	
	 public  static Connection getConByThread(){
		 	Connection con=tl.get();
		 
			 try {
				 if(con==null){
				con=ds.getConnection();
				tl.set(con);
				return con;
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			throw new RuntimeException();
			}
			 return con;
		 }
	 public static void startTransation(){
		 try {
			 
			getConByThread().setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public static void commitTransationAndClose(){
		
		DbUtils.commitAndCloseQuietly(tl.get());
		tl.remove();
		
	 }
	 public static void roolBack1AndClose(){
		 DbUtils.rollbackAndCloseQuietly(tl.get());
		 tl.remove();
	 }
	 public static void close(Connection con,Statement sta,ResultSet rs){
		   try {
			   if(con!=null){
			con.close();
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   try {
			   if(sta!=null){
			sta.close();
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   try {
			   if(rs!=null){
			rs.close();
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public static void close(Connection con,Statement sta){
		 close(con,sta,null);
	 }
	 public static DataSource getDataSource(){
		 return ds;
	 }
	 }

