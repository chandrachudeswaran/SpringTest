package com.springwebproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springwebproject.dao.User;
import com.springwebproject.service.UserService;

@Controller
public class LoginController {
	
	private UserService userservice;
	private int count =0;
	@Autowired
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	@RequestMapping("/login")
	public String showHome(){
		
		return "login";
	}
	
	@RequestMapping("/denied")
	public String showDenied(){
		
		return "denied";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model){
		
		List<User> users=userservice.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedout(){
		
		return "loggedout";
	}
	
	@RequestMapping("/newaccount")
	public String showUserForm(Model model){
		
		model.addAttribute("user", new User());
		return "createuser";
	}
	
	@RequestMapping("/createuser")
	public String createUser(Model model,@Valid User user,BindingResult result){
		if(result.hasErrors()){
			return "createuser";
		}
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		
		
		
	if(userservice.exists(user.getUsername())){
		System.out.println("caught duplicate username");
		result.rejectValue("username", "DuplicateKey.user.username");
		
		return "createuser";
	}
		userservice.createUser(user);
		
		return "accountcreated";
	}

}
