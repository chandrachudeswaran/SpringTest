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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
		
		return jdbc.query("select * from offers", new RowMapper<Offer>(){

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Offer offer =  new Offer();
				offer.setId(rs.getInt("id"));
				offer.setEmail(rs.getString("email"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				return offer;
			}
			
		});
}
	
	public boolean createOffer(Offer offer){
		BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(offer);
		
		return jdbc1.update("insert into offers (name,email,text) values(:name,:email,:text)", bean)==1;
		
	}
	
	
	public Offer getOffer(int id){
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		return jdbc1.queryForObject("select * from offers where id= :id", params, new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer o = new Offer();
				
				o.setId(rs.getInt("id"));
				o.setName(rs.getString("name"));
				o.setEmail(rs.getString("email"));
				o.setText(rs.getString("text"));
				return o;
			}
		});
	}
}