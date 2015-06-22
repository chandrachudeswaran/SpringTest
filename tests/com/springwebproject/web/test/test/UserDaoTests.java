package com.springwebproject.web.test.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

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
	
	private User user1= new User("chandar" ,"chandar","chandar@gmail.com","viji",true,"ROLE_USER");
	private User user2 = new User("ramsankar","ramsankar","ram@gmail.com","viji",true,"ROLE_USER");
	private User user3= new User("admin","admin","admin@admin.com","admin",true,"ROLE_ADMIN");
	private User user4= new User("vivek","vivek","vivek@gmail.com","chitra",true,"ROLE_USER");
	
	@Before
	public void init(){
		JdbcTemplate jdbc= new JdbcTemplate(datasource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
		
		
	}
	
	@Test
	public void testCreateRetrieveUsers(){
		usersdao.createUser(user1);
		List<User> users1 = usersdao.getAllUsers();
		assertEquals("Number of users should be 1",1,users1.size());
		assertEquals("Retrieved user should be identical",user1,users1.get(0));
		
		usersdao.createUser(user2);
		usersdao.createUser(user3);
		usersdao.createUser(user4);
		
		List<User> users2 = usersdao.getAllUsers();
		assertEquals("Number of users should be 4",4,users2.size());
		
	}
	
	
	
	@Test
	public void testCreateUser(){
		User user = new User("chandar","Chandra","chandar@gmail.com","viji",true,"user");
		usersdao.createUser(user);
		
		
		List<User> users = usersdao.getAllUsers();
	assertEquals("Number of users should be 1",1,users.size());
	
	
	assertTrue("users should exist",usersdao.exists(user.getUsername()));
	
	
	assertEquals("Retreived user should be identical to created user" ,user,users.get(0));
	}

}
