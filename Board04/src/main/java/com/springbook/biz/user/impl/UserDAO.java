package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.stereotype.Repository;
import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

/*@Repository("userDAO")*/
public class UserDAO {

	/* JDBC */
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private final String USER_GET = "SELECT*FROM users WHERE id=?and password=?";

	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			System.out.println("==> JDBC로 getUser()기능 처리");
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(USER_GET);
			preparedStatement.setString(1, vo.getId());
			preparedStatement.setString(2, vo.getPassword());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new UserVO();
				user.setId(resultSet.getString("id"));
				user.setPassword(resultSet.getString("password"));
				user.setName(resultSet.getString("name"));
				user.setRole(resultSet.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return user;
	}

}