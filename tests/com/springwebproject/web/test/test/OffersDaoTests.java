package com.springwebproject.web.test.test;

import static org.junit.Assert.*;
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

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/springwebproject/config/dao-context.xml",
		"classpath:com/springwebproject/config/security-context.xml",
		"classpath:com/springwebproject/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OffersDaoTests {
	@Autowired
	private OffersDAO offersdao;
	@Autowired
	private DataSource datasource;
	@Before
	public void init(){
		
		JdbcTemplate jdbc = new JdbcTemplate(datasource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
		jdbc.execute("delete from authorities");
	}
	
	@Test
	public void testCreateOffers(){
		Offer offer = new Offer("chandar","chandar@gmail.com","TestOffer");
		assertTrue("Create users",offersdao.createOffer(offer));
		
	}
	
	@Test
	public void testGetOffers(){
		JdbcTemplate jdbc = new JdbcTemplate(datasource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
		jdbc.execute("delete from authorities");
		
		Offer offer = new Offer("chandar","chandar@gmail.com","TestOffer");
		assertTrue("Create users",offersdao.createOffer(offer));
		List<Offer> offers = offersdao.getOffers();
		
		assertEquals("Number of users should be same",1,offers.size());
		assertEquals("Created Offers and retreived Offers should be same",offer,offers.get(0));
	}
	
	
}
