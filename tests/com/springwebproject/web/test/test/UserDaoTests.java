package com.springwebproject.web.test.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springwebproject.dao.User;
import com.springwebproject.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/springwebproject/config/dao-context.xml",
		"classpath:com/springwebproject/config/security-context.xml",
		"classpath:com/springwebproject/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {
	
	@Autowired
	private UserDao usersdao;
	@Autowired
	private DataSource datasource;
	
	@Before
	public void init(){
		JdbcTemplate jdbc= new JdbcTemplate(datasource);
		
		jdbc.execute("delete from users");
		jdbc.execute("delete from authorities");
		
	}
	@Test
	public void testCreateUser(){
		User user = new User("chandar","chandar@gmail.com","viji",true,"user");
		assertTrue("created",usersdao.createUser(user));
		
		
		List<User> users = usersdao.getAllUsers();
	assertEquals("Number of users should be 1",1,users.size());
	
	
	assertTrue("users should exist",usersdao.exists(user.getUsername()));
	
	
	assertEquals("Retreived user should be identical to created user" ,user,users.get(0));
	}

}
