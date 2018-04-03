package com.htsc.htbcps.myDemo.dal.dao;

import com.htsc.htbcps.myDemo.dal.model.UserModel;

public interface UserModelMapper {
	
	UserModel selectByPrimaryKey(String userId);
	
}
