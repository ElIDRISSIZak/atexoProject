package com.atexo.exercice.business;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atexo.exercice.models.Card;
import com.atexo.exercice.models.Subject;

@Service
public class CardsSortingService {

	public CardsSortingService() {
	
	}
	public Subject sortCart(Subject exercice){
		String exerciceId =exercice.getExerciceId();;
		List<Card> cards =exercice.getCards();
		List<String> categoryOrder =exercice.getCategoryOrder();
		List<String> valueOrder =exercice.getValueOrder();
		CardsSortByCategoryAndValueComparator comparator =
				new CardsSortByCategoryAndValueComparator(categoryOrder,valueOrder);
		Collections.sort(cards,comparator);
		
		Subject answer  =new Subject();
		answer.setExerciceId(exerciceId);
		answer.setCategoryOrder(categoryOrder);
		answer.setValueOrder(valueOrder);
		answer.setCards(cards);
		return answer ;
	}
	
}
