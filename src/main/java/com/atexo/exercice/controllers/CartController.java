package com.atexo.exercice.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atexo.exercice.business.CardsParsingService;
import com.atexo.exercice.business.CardsSortingService;
import com.atexo.exercice.models.Subject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
@Controller
public class CartController {
	
	@Autowired
	CardsParsingService cardsParsers;

	@Autowired
	CardsSortingService cardsSortingService ;
	
	
	@RequestMapping(value="index.html",method=RequestMethod.GET)
	public String getAppPage(Model model,HttpSession session){
		session.setAttribute("exercice",null);
		session.setAttribute("solution",null);
		session.setAttribute("solutionToShow", null);
		session.setAttribute("exerciceToShow",null);
		session.setAttribute("status",null);
		return "carts";
	}
	
	@RequestMapping(value="/getExercice",method=RequestMethod.POST)
	public String getExercice(Model model,HttpSession session){
		String content =null;
		 try {
			ClientConfig clientConfig = new DefaultClientConfig();
			Client client = Client.create(clientConfig);
			WebResource webResource = client.resource("https://recrutement.local-trust.com/test/cards/56715f72975a74a0579d0447");
			ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
			 if (response.getStatus() != 200) {
			                throw new RuntimeException("Failed : HTTP error code : "
			                        + response.getStatus());
			            }
			            content = response.getEntity(String.class);
			            
			        } catch (Exception e) {
			 
			            e.printStackTrace();

			        }
		Subject exercice=cardsParsers.getSubjectFromExercice(content);
		Subject solution =cardsSortingService.sortCart(exercice);
		session.setAttribute("solution", solution);
		session.setAttribute("exercice",exercice);
		session.setAttribute("solutionToShow", cardsParsers.getJsonFromSubject(solution));
		session.setAttribute("exerciceToShow",content);
		session.setAttribute("status",null);
		return "carts";
	}
	@RequestMapping(value="/solveAndSendSolution",method=RequestMethod.POST)
	public String solveAndSend(Model model,HttpSession session){
		Subject solution =(Subject) session.getAttribute("solution");
		String solutionToSend =cardsParsers.getJsonFromSubject(solution);
		try {
		ClientConfig clientConfig = new DefaultClientConfig();
		Client client = Client.create(clientConfig);
		WebResource webResource = client.resource("https://recrutement.local-trust.com/test/"+solution.getExerciceId());
		ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class,solutionToSend);
		session.setAttribute("status",response.getStatus()); 
		if (response.getStatus() != 200) {	
		                throw new RuntimeException("Failed : HTTP error code : "
		                        + response.getStatus());
		 }else{
			 
		 }   
    } catch (Exception e) {

        e.printStackTrace();

    }
		
		return "carts";
	}
	
	
}
