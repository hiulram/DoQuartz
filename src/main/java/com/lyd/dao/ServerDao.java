package com.lyd.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lyd.pojo.Server;
import com.lyd.utils.ThreadLocalTool;

public class ServerDao {
	
	public Integer getTotalsOfServer() throws SQLException {
		
		QueryRunner queryRunner=new QueryRunner(ThreadLocalTool.getDataSource());
		String sql="SELECT COUNT(id) FROM servers ;";
		
		Long query = queryRunner.query(sql, new ScalarHandler<Long>());
		return query.intValue();
	}
	public Server getServerByIp(String ip) throws SQLException {
		
		QueryRunner queryRunner=new QueryRunner(ThreadLocalTool.getDataSource());
		
		String sql="select * from  servers where ip= ?";
		
		Server query = queryRunner.query(sql, new BeanHandler<>(Server.class),ip);
		
		return query;
	}
}
