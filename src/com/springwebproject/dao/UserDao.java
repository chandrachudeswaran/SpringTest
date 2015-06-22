package com.springwebproject.dao;

import java.util.List;


import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Component("userdao")
public class UserDao {
	
	private NamedParameterJdbcTemplate jdbc;
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setJdbc(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void createUser(User user){
		
		user.setPassword(passwordencoder.encode(user.getPassword()));
		session().save(user);
		
		
	}
	
	public boolean exists(String username){

		return jdbc.queryForObject("select count(*) from users where username= :username", new MapSqlParameterSource("username" ,username), Integer.class) >0;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers(){
		
		return session().createQuery("from User").list();
		
			
	}

}
