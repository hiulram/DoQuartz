package com.lyd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lyd.pojo.Person;
import com.lyd.utils.ThreadLocalTool;

public class PersonDao {

	public void insertByBatch() throws SQLException {
		Connection con = ThreadLocalTool.getConByThread();
		String sql = "insert into person(pname,telephone) values(?,?)";

		PreparedStatement statement = con.prepareStatement(sql);

		for (int i = 0; i < 10000; i++) {
			if (i % 3 == 0) {
				statement.setObject(1, "Young");
				statement.setObject(2, "15720600886");
			}
			if (i % 3 == 1) {
				statement.setObject(1, "LiYong");
				statement.setObject(2, "15013030131");
			}
			if (i % 3 == 2) {
				statement.setObject(1, "CaoYuanJia");
				statement.setObject(2, "13726232477");
			}
			statement.addBatch();
		}
		statement.executeBatch();

	}

	public List<Person> getALL() {
		QueryRunner query = new QueryRunner(ThreadLocalTool.getDataSource());
		String sql = "select * from  person";
		List<Person> allPserson = null;
		try {
			allPserson = query.query(sql, new BeanListHandler<>(Person.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return allPserson;
	}
}
