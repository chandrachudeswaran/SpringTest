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

import com.springwebproject.dao.Offer;
import com.springwebproject.dao.OffersDAO;
import com.springwebproject.dao.User;
import com.springwebproject.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/springwebproject/config/dao-context.xml",
		"classpath:com/springwebproject/config/security-context.xml",
		"classpath:com/springwebproject/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OffersDaoTests {
	
	@Autowired
	private UserDao usersdao;
	@Autowired
	private OffersDAO offersdao;
	@Autowired
	private DataSource datasource;
	@Before
	public void init(){
		
		JdbcTemplate jdbc = new JdbcTemplate(datasource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
		
	}
	
	@Test
	public void testCreateOffers(){
		User user = new User("chandar","Chandra","chandar@gmail.com","viji",true,"user");
		usersdao.createUser(user);
		
		Offer offer = new Offer(user,"TestOffer");
		assertTrue("Create users",offersdao.createOffer(offer));
		
	}
	
	@Test
	public void testGetOffers(){
	
		User user = new User("chandar","Chandra","chandar@gmail.com","viji",true,"user");
		usersdao.createUser(user);
		Offer offer = new Offer(user,"TestOffer");
		assertTrue("Create users",offersdao.createOffer(offer));
		List<Offer> offers = offersdao.getOffers();
		
		assertEquals("Number of users should be same",1,offers.size());
		assertEquals("Created Offers and retreived Offers should be same",offer,offers.get(0));
	}
	
	@Test
	public void testGetOffersUsername(){
		
		User user = new User("chandar","Chandra","chandar@gmail.com","viji",true,"user");
		usersdao.createUser(user);
		Offer offer = new Offer(user,"TestOffer");
		assertTrue("Create users",offersdao.createOffer(offer));
		
		List<Offer> offer1 = offersdao.getOffers(user.getUsername());
		assertEquals("TestCase3",offer,offer1.get(0));
	}
	
	
}
