package com.shsxt.test;


import org.junit.Test;

import com.shsxt.dao.UserDao;
import com.shsxt.model.User;

public class UserTest {

	@Test
	public void test() {
		UserDao userDao = new UserDao();
		User user = userDao.queryUserByEmail("lisa@sxt.com");
		System.out.println(user);
	}

}
