package com.atexo.exercice.business;

import java.util.Comparator;
import java.util.List;

import com.atexo.exercice.models.Card;

public class CardsSortByCategoryAndValueComparator implements Comparator<Card> {

	private List<String> categoryOrder;
	private List<String> valueOrder;
	

	
	
	

	public CardsSortByCategoryAndValueComparator(List<String> categoryOrder,List<String> valueOrder) {
		this.categoryOrder = categoryOrder;
		this.valueOrder = valueOrder;
	}



	public void setCategoryOrder(List<String> categoryOrder) {
		this.categoryOrder = categoryOrder;
	}



	public void setValueOrder(List<String> valueOrder) {
		this.valueOrder = valueOrder;
	}

	public int compare(Card card1 ,Card card2) {
		//comparing categories of the two cards 
		//Integer categoryOrderSize =categoryOrder.size();//categoryOrderSize-
		String category1 =card1.getCategory();
		String category2 =card2.getCategory();
		Integer orderOfCategory1 =categoryOrder.indexOf(category1);
		Integer orderOfCategory2 =categoryOrder.indexOf(category2);
		
		if(!orderOfCategory1.equals(orderOfCategory2)){
			return orderOfCategory1.compareTo(orderOfCategory2);
		}else{
			//Integer valueOrderSize =valueOrder.size();valueOrderSize-
			String value01 =card1.getValue();
			String value02 =card2.getValue();
			Integer orderOfValue1=valueOrder.indexOf(value01);
			Integer orderOfValue2=valueOrder.indexOf(value02);
			return orderOfValue1.compareTo(orderOfValue2);
		}
	}

}
