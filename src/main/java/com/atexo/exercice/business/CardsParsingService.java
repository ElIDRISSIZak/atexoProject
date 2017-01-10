package com.atexo.exercice.business;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.atexo.exercice.models.Card;
import com.atexo.exercice.models.Subject;

@Service
public class CardsParsingService {

	
	public CardsParsingService() {
		super();
	}
	
	public Subject getSubjectFromExercice(String content){
		JSONParser parser = new JSONParser();
		Subject subject =new Subject();
		String exerciceId =null;
		
		try {
			Object obj = parser.parse(content);
			JSONObject jsonObject = (JSONObject) obj;
			exerciceId = (String) jsonObject.get("exerciceId");
			JSONObject dataObject=(JSONObject) jsonObject.get("data");
			subject = getSubjectFromJson(dataObject);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		subject.setExerciceId(exerciceId);
		
		return subject ;
	}
	
	public Subject getSubjectFromAnswer(String content){
		Subject subject =new Subject();
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(content);
			JSONObject jsonObject = (JSONObject) obj;
			subject = getSubjectFromJson(jsonObject);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return subject ;
	}
	
	public Subject getSubjectFromJson(JSONObject dataObject){
		Subject subject =new Subject() ;
		List<Card> cards =new ArrayList<Card>();
		List<String> categoryOrder =new ArrayList<String>();
		List<String> valueOrder =new ArrayList<String>();
		//Parsing for cards 
		JSONArray cardsJSON = (JSONArray) dataObject.get("cards");
		Iterator<JSONObject> cardsIterator = cardsJSON.iterator();
		while (cardsIterator.hasNext()) {
			JSONObject cardObject =(JSONObject) cardsIterator.next();
			Card card =new Card();
			String category =(String)cardObject.get("category");
			String value =(String)cardObject.get("value");
			card.setCategory(category);
			card.setValue(value);
			cards.add(card);
		}
		//Parsing for categoryOrder
		JSONArray categoryOrderJSON = (JSONArray) dataObject.get("categoryOrder");
		Iterator<String> categoryIterator = categoryOrderJSON.iterator();
		while (categoryIterator.hasNext()) {
			String category=categoryIterator.next();
			categoryOrder.add(category);
		}
		//Parsing for valueOrder
		JSONArray valueOrdersJSON = (JSONArray) dataObject.get("valueOrder");
		Iterator<String> valueIterator = valueOrdersJSON.iterator();
		while (valueIterator.hasNext()) {
			String value=valueIterator.next();
			valueOrder.add(value);
		}
		subject.setCards(cards);
		subject.setCategoryOrder(categoryOrder);
		subject.setValueOrder(valueOrder);
		return subject;
	}
	public String getJsonFromSubject(Subject subject){
		JSONObject AnswerObject = new JSONObject();
		JSONArray cards = new JSONArray();
		JSONArray categoryOrder= new JSONArray();
		JSONArray valueOrder= new JSONArray();
		//making value Json :
		for(String value :subject.getValueOrder()){
			valueOrder.add(value);
		}
		//making category json
		for(String category :subject.getCategoryOrder()){
			categoryOrder.add(category);
		}
		//making cards json 
		for(Card card: subject.getCards()){
			JSONObject cardObject =new JSONObject() ;
			cardObject.put("category", card.getCategory());
			cardObject.put("value",card.getValue());
			cards.add(cardObject);                       
		}
		AnswerObject.put("cards",cards);
		AnswerObject.put("categoryOrder", categoryOrder);
		AnswerObject.put("valueOrder", valueOrder);
		return AnswerObject.toJSONString() ;
	}
}
