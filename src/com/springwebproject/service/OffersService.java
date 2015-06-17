package com.springwebproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
	
	@Secured("{ROLE_USER,ROLE_ADMIN}")
	public boolean createOffer(Offer offer){
		return offersdao.createOffer(offer);
	}



	

}
