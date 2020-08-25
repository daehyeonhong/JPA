package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe", user = "hr", password = "hr";
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(Statement statement, Connection connection) {
		if (statement != null) {
			try {
				if (!statement.isClosed()) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				statement = null;
			}
		}

		if (connection != null) {
			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection = null;
			}
		}
	}

	public static void close(ResultSet resultSet, Statement statement, Connection connection) {
		if (resultSet != null) {
			try {
				if (!resultSet.isClosed()) {
					resultSet.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				resultSet = null;
			}
		}

		if (statement != null) {
			try {
				if (!statement.isClosed()) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				statement = null;
			}
		}

		if (connection != null) {
			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection = null;
			}
		}
	}

}