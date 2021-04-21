package com.citi.trade.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.citi.trade.model.User;
import com.citi.trade.pojo.UserRegistration;

public interface UserService extends UserDetailsService{
	User save(UserRegistration registrationDto);
}
