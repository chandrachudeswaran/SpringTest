package com.springwebproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
		
		return jdbc.query("select * from offers,users where offers.username=users.username and users.enabled=true", new RowMapper<Offer>(){

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				
				User user = new User();
				
				user.setAuthority(rs.getString("authority"));
				user.setEmail(rs.getString("email"));
				user.setEnabled(true);
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				
				Offer offer =  new Offer();
				offer.setId(rs.getInt("id"));
				offer.setText(rs.getString("text"));
				offer.setUser(user);
				return offer;
			}
			
		});
}
	
	
	public boolean update(Offer offer){
		BeanPropertySqlParameterSource params= new BeanPropertySqlParameterSource(offer);
		return jdbc.update("update offers set text =:text where id=:id",params)==1;
	}
	
	
	public boolean createOffer(Offer offer){
		BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(offer);
		
		return jdbc1.update("insert into offers (username,text) values(:username,:text)", bean)==1;
		
	}
	
	
	public boolean delete(int id){
		MapSqlParameterSource params = new MapSqlParameterSource("id",id);
		return jdbc.update("delete from offers where id=:id",params)==1;
	}
	
	
	public Offer getOffer(int id){
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		return jdbc1.queryForObject("select * from offers,users where id= :id and offers.username=users.username and users.enabled=true", params, new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				
				user.setAuthority(rs.getString("authority"));
				user.setEmail(rs.getString("email"));
				user.setEnabled(true);
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				
				Offer offer =  new Offer();
				offer.setId(rs.getInt("id"));
				offer.setText(rs.getString("text"));
				offer.setUser(user);
				return offer;
			}
		});
	}
}