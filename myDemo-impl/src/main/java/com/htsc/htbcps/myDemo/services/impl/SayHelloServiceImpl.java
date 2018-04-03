package com.htsc.htbcps.myDemo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.htsc.htbcps.myDemo.dal.dao.UserModelMapper;
import com.htsc.htbcps.myDemo.services.SayHelloService;
import com.htsc.htbcps.myDemo.dal.model.UserModel;
import com.htsc.htbcps.myDemo.dto.User;

public class SayHelloServiceImpl implements SayHelloService {
	
	@Autowired
	private UserModelMapper userModelMapper;

	@Override
	public String SayHello(String word) {
		return "hello " + word;
	}

	@Override
	public User getUser(String userId) {
		UserModel userModel = userModelMapper.selectByPrimaryKey(userId);
		User user = new User();
		user.setUserId(userModel.getUserId());
		user.setUsername(userModel.getUsername());
		user.setPassword(userModel.getPassword());
		return user;
	}

}
