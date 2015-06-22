package com.springwebproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springwebproject.dao.Offer;
import com.springwebproject.dao.OffersDAO;

@Service("offersservice")
public class OffersService {
	private OffersDAO offersdao;
	
	@Autowired
	public void setOffersdao(OffersDAO offersdao) {
		this.offersdao = offersdao;
	}



	public List<Offer> getCurrentOffer(){
		return offersdao.getOffers();
	}
	
	//@Secured("{ROLE_USER,ROLE_ADMIN}")
	public boolean createOffer(Offer offer){
		return offersdao.createOffer(offer);
	}



	public boolean hasOffer(String name) {
		if(name==null) {
			return false;
		}
		
		List<Offer> offers = offersdao.getOffers();
		
		if(offers.size()==0){
			return false;
		}
		return true;
	}



	public Offer getOffer(String username) {
		
		if(username==null){
			return null;
		}
		List<Offer> offers = offersdao.getOffers();
		
		if(offers.size()== 0){
			return null;
		}
		return offers.get(0);
	}



	public void saveorUpdateOffer(Offer offer) {
		
		if(offer.getId()!=0){
			offersdao.update(offer);
		}
		else{
			offersdao.createOffer(offer);
		}
		
	}



	public void delete(int id) {
		offersdao.delete(id);
		
	}



	

}
