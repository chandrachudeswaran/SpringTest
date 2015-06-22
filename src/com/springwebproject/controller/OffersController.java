package com.springwebproject.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springwebproject.dao.Offer;
import com.springwebproject.service.OffersService;

@Controller
public class OffersController {
	
	private OffersService offerservice;
	
	@Autowired
	public void setOfferservice(OffersService offerservice) {
		this.offerservice = offerservice;
	}

	@RequestMapping("/offers")
	public String currentOffers(Model model){
		
		//offerservice.throwTestException();
		List<Offer> offers=offerservice.getCurrentOffer();
		model.addAttribute("Offers",offers);
		
		return "showoffers";
	}
	
	@RequestMapping("/createoffers")
	public String createOffer(Model model,Principal principal){
		Offer offer=null;
		
		if(principal !=null){
			String username = principal.getName();
			
			 offer = offerservice.getOffer(username);
		}
		
		if(offer == null){
			offer = new Offer(); 
		}
		model.addAttribute("offer", offer);
		return "createoffer";
	}
	@RequestMapping("/doCreate")
	public String doCreate(Model model,@Valid Offer offer,BindingResult result,Principal principal,@RequestParam(value="delete",required=false) String delete){
		
		if(result.hasErrors()){
			System.out.println("form does not validate");
			
			List<ObjectError> errors=result.getAllErrors();
			
			for(ObjectError e:errors){
				System.out.println(e.getDefaultMessage());
			}
			
			return "createoffer";
		}
		
		if(delete==null){
			String username = principal.getName();
			offer.getUser().setUsername(username);
			offerservice.saveorUpdateOffer(offer);
			return "offercreated";
		}
		
		else{
		
			offerservice.delete(offer.getId());
			return "offerdeleted";
		}
		
		
		
	}
}
