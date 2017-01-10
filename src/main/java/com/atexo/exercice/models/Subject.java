package com.atexo.exercice.models;

import java.util.List;

public class Subject {
	
	private String exerciceId;
	private List<Card> cards;
	private List<String> categoryOrder;
	private List<String> valueOrder;

	public String getExerciceId() {
		return exerciceId;
	}

	public void setExerciceId(String exerciceId) {
		this.exerciceId = exerciceId;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<String> getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(List<String> categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public List<String> getValueOrder() {
		return valueOrder;
	}

	public void setValueOrder(List<String> valueOrder) {
		this.valueOrder = valueOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		result = prime * result
				+ ((categoryOrder == null) ? 0 : categoryOrder.hashCode());
		result = prime * result
				+ ((exerciceId == null) ? 0 : exerciceId.hashCode());
		result = prime * result
				+ ((valueOrder == null) ? 0 : valueOrder.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		if (categoryOrder == null) {
			if (other.categoryOrder != null)
				return false;
		} else if (!categoryOrder.equals(other.categoryOrder))
			return false;
		if (exerciceId == null) {
			if (other.exerciceId != null)
				return false;
		} else if (!exerciceId.equals(other.exerciceId))
			return false;
		if (valueOrder == null) {
			if (other.valueOrder != null)
				return false;
		} else if (!valueOrder.equals(other.valueOrder))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subject [exerciceId=" + exerciceId + ", cards=" + cards
				+ ", categoryOrder=" + categoryOrder + ", valueOrder="
				+ valueOrder + "]";
	}
	
	
}
