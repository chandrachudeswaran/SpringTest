package com.springwebproject.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("offersdao")
public class OffersDAO {
	
	private JdbcTemplate jdbc;
	
	private NamedParameterJdbcTemplate jdbc1;
	
	@Autowired
	public void setJdbc1(DataSource jdbc1) {
		this.jdbc1 =  new NamedParameterJdbcTemplate(jdbc1);
	}


	@Autowired
	public void setJdbc(DataSource jdbc) {
		this.jdbc = new JdbcTemplate(jdbc);
	}


	public List<Offer> getOffers(){
		
		return jdbc.query("select * from offers,users where offers.username=users.username and users.enabled=true", new OfferRowMapper());
}
	
	public List<Offer> getOffers(String username){
		
		return jdbc1.query("select * from offers,users where offers.username=users.username and users.enabled=true and offers.username=:username",new MapSqlParameterSource("username",username), new OfferRowMapper());
}
	
	
	public boolean update(Offer offer){
		BeanPropertySqlParameterSource params= new BeanPropertySqlParameterSource(offer);
		return jdbc1.update("update offers set text =:text where id=:id",params)==1;
	}
	
	
	public boolean createOffer(Offer offer){
		BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(offer);
		
		return jdbc1.update("insert into offers (username,text) values(:username,:text)", bean)==1;
		
	}
	
	
	public boolean delete(int id){
		MapSqlParameterSource params = new MapSqlParameterSource("id",id);
		return jdbc1.update("delete from offers where id=:id",params)==1;
	}
	
	
	public Offer getOffer(int id){
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		return jdbc1.queryForObject("select * from offers,users where id= :id and offers.username=users.username and users.enabled=true", params, new OfferRowMapper());
	}
}