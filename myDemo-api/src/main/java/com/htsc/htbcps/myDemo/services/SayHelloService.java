package com.htsc.htbcps.myDemo.services;

import com.htsc.htbcps.myDemo.dto.User;

public interface SayHelloService {

	public String SayHello(String word);
	
	public User getUser(String userId);

}
