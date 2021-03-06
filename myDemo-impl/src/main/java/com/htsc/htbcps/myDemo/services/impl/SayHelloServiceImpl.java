package com.htsc.htbcps.myDemo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.htsc.htbcps.myDemo.annotation.Log;
import com.htsc.htbcps.myDemo.common.Result;
import com.htsc.htbcps.myDemo.dal.dao.UserModelMapper;
import com.htsc.htbcps.myDemo.services.SayHelloService;
import com.htsc.htbcps.myDemo.dal.model.UserModel;
import com.htsc.htbcps.myDemo.dto.AreaInfoDto;
import com.htsc.htbcps.myDemo.dto.User;

public class SayHelloServiceImpl implements SayHelloService {
	
    @Autowired
    private SayHelloService sayHelloService;
    
	@Autowired
	private UserModelMapper userModelMapper;

	@Override
	@Log
	public String SayHello(String word) {
	    System.out.println("----我是cn.larry.spring.service.DemoService.run()----3");
		return "hello " + word;
	}

	@Override
	@Log
	public User getUser(String userId) {
	    System.out.println("----测试1----------");
	    User user = sayHelloService.getUserModel(userId);
	    System.out.println("---------测试3------------------");
//		String json="{\"resultcode\":\"200\",\"reason\":\"Return Successd!\",\"result\":{\"province\":\"北京\",\"city\":\"\",\"areacode\":\"010\",\"zip\":\"100000\",\"company\":\"联通\",\"card\":\"\"},\"error_code\":0}";
//		Result<JSON> result=JSONObject.parseObject(json,Result.class);  
//		AreaInfoDto areaInfoDto =JSONObject.toJavaObject(result.getResult(),AreaInfoDto.class); 
	       
		return user;
	}
	
	@Override
	@Log
	public User getUserModel(String userId) {
	    System.out.println("---------测试2------------------");
	    UserModel userModel = userModelMapper.selectByPrimaryKey(userId);
	    User user = new User();
        user.setUserId(userModel.getUserId());
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
	    return user;
	}

}
