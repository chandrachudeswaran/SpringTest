package com.springwebproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.springwebproject.dao.User;
import com.springwebproject.dao.UserDao;

@Service("userservice")
public class UserService {
	
	private UserDao userdao;
	
	@Autowired
	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}
	
	public void createUser(User user){
		userdao.createUser(user);
		
	}
	
	public boolean exists(String username){
		return userdao.exists(username);
	}
	
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers(){
		
		return userdao.getAllUsers();
	}
	

}
