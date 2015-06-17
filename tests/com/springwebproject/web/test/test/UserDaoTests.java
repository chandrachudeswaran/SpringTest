package com.springwebproject.web.test.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/springwebproject/config/dao-context.xml",
		"classpath:com/springwebproject/config/security-context.xml",
		"classpath:com/springwebproject/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {
	
	@Test
	public void testCreateUser(){
		
		assertEquals("Dummy",1,1);
	}

}
