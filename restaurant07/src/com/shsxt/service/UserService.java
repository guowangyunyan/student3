package com.shsxt.service;

import com.shsxt.dao.UserDao;
import com.shsxt.model.User;
import com.shsxt.model.vo.ResultInfo;
import com.shsxt.util.Md5Util;
import com.shsxt.util.StringUtil;

public class UserService {
	
	private UserDao userDao = new UserDao();

	/**
	 * 用户登录
	 * @param email
	 * @param password
	 * @return
	 */
	public ResultInfo userLogin(String email, String password) {
		ResultInfo resultInfo = new ResultInfo();
		// 参数的非空判断
		if (StringUtil.isEmpty(email)) {
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("用户邮箱不能为空！");
			return resultInfo;
		}
		if (StringUtil.isEmpty(password)) {
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("用户密码不能为空！");
			return resultInfo;
		}
		// 通过邮箱从数据库获取用户对象
		User user = userDao.queryUserByEmail(email);
		// 判断用户对象是否为空
		if (user == null) {
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("用户不存在！");
			return resultInfo;
		}
		// 得到数据库的用户密码
		String pwd = user.getPassword();
		// 判断密码是否相等
		if (!Md5Util.encode(password).equals(pwd)) {
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("用户密码不正确！");
			return resultInfo;
		}
		
		resultInfo.setResultCode(1);
		resultInfo.setResultMessage("Success");
		resultInfo.setResultObject(user);
		
		return resultInfo;
	}

	/**
	 * 修改用户信息
	 * @param email
	 * @param userName
	 * @param newPassword
	 * @return
	 */
	public ResultInfo updateUser(String email, String userName, String newPassword) {
		ResultInfo resultInfo = new ResultInfo();
		// 参数的非空判断
		if (StringUtil.isEmpty(email)) {
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("用户邮箱不能为空！");
			return resultInfo;
		}
		if (StringUtil.isEmpty(userName)) {
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("用户名称不能为空！");
			return resultInfo;
		}
		// 去数据库查询用户对象是否存在
		User user = userDao.queryUserByEmail(email);
		// 判断是否为空
		if (user == null) {
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("用户不存在！");
			return resultInfo;
		}
		// 把密码加密
		if (StringUtil.isNotEmpty(newPassword)) {
			newPassword = Md5Util.encode(newPassword);;
		}
		// 修改用户信息
		Integer code = userDao.updateUser(email,userName,newPassword);
		if (code != 1) { // 如果不成功
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("修改失败！");
			return resultInfo;
		}
		
		// 给User对象赋值
		user.setUserName(userName);
		user.setPassword(newPassword);
		resultInfo.setResultCode(1);
		resultInfo.setResultMessage("Success!");
		resultInfo.setResultObject(user);
		
		return resultInfo;
	}

}
