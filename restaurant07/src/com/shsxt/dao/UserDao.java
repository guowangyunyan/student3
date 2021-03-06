package com.shsxt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shsxt.model.User;
import com.shsxt.util.DBUtil;
import com.shsxt.util.StringUtil;

public class UserDao {

	/**
	 * 通过邮箱从数据库得到用户对象
	 * 1、打开连接
	 * 2、写sql
	 * 3、预编译
	 * 4、传参数
	 * 5、执行查询
	 * 6、判断是否有值 赋值
	 * @param email
	 * @return
	 */
	public User queryUserByEmail(String email) {
		User user = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			// 打开连接
			connection = DBUtil.getConnection();
			// 编写sql
			String sql = "select * from user where email = ?";
			// 预编译
			preparedStatement = connection.prepareStatement(sql);
			// 传参数
			preparedStatement.setString(1, email);
			// 执行查询
			resultSet = preparedStatement.executeQuery();
			// 如果有值
			while (resultSet.next()) {
				// 赋值
				user = new User();
				user.setCreateDate(resultSet.getDate("createDate"));
				user.setEmail(resultSet.getString("email"));
				user.setIsValid(resultSet.getInt("isValid"));
				user.setPassword(resultSet.getString("password"));
				user.setUpdateDate(resultSet.getDate("updateDate"));
				user.setUserId(resultSet.getInt("userId"));
				user.setUserName(resultSet.getString("userName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return user;
	}

	/**
	 * 通过邮箱修改用户信息
	 * @param email
	 * @param userName
	 * @param newPassword
	 * @return
	 */
	public Integer updateUser(String email, String userName, String newPassword) {
		Integer code = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 打开连接
			connection = DBUtil.getConnection();
			String sql = "";
			if (StringUtil.isNotEmpty(newPassword)) {
				// 编写sql
				sql = "update user set userName = ?, password=?,updateDate=now() where email = ?";
				// 预编译
				preparedStatement = connection.prepareStatement(sql);
				// 传参数
				preparedStatement.setString(1, userName);
				preparedStatement.setString(2, newPassword);
				preparedStatement.setString(3, email);
			} else {
				sql = "update user set userName =?,updateDate=now() where email =?";
				// 预编译
				preparedStatement = connection.prepareStatement(sql);
				// 传参数
				preparedStatement.setString(1, userName);
				preparedStatement.setString(2, email);
			}
			
			// 执行修改
			int row = preparedStatement.executeUpdate();
			if (row > 0) { // 修改成功
				code = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, preparedStatement, connection);
		}
		
		return code;
	}


}
