package com.springwebproject.controller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springwebproject.dao.Offer;
import com.springwebproject.service.OffersService;

@Controller
public class HomeController {
	
	private static Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	private OffersService offerservice;
	@RequestMapping("/")
	public String showHome(Model model,Principal principal){
	
	List<Offer> offers=offerservice.getCurrentOffer();
	model.addAttribute("Offers",offers);
	
	boolean hasOffer= false;
	
	if(principal!=null){
		hasOffer=offerservice.hasOffer(principal.getName());
	}
	
	model.addAttribute("hasoffer", hasOffer);
	return "home";
}

	

}
