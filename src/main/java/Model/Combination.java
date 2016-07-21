package main.java.Model;

import java.util.ArrayList;
import java.util.Collections;

import main.java.Model.Enumerations.CombinationType;

public class Combination implements Comparable<Combination>{
	private CombinationType combinationType;
	private ArrayList<Card> cardInCombination;
	
	public Combination(CombinationType aCombination, ArrayList<Card> aCards){
		this.combinationType=aCombination;
		this.cardInCombination = aCards;
	}
	public CombinationType getType(){
		return this.combinationType;
	}
	
	public ArrayList<Card> getCards(){
		return this.cardInCombination;
	}
	@Override
	public int compareTo(Combination o) {
		if(this.combinationType.compareTo(o.combinationType)==0){
			Card sp=null;
			Integer sum1 = 0;
			for(Card card: this.cardInCombination){
				sum1+=card.getValue().getValue();
			}
			Integer sum2 = 0;
			for(Card card: o.cardInCombination){
				sum2+=card.getValue().getValue();
			}
			return sum1.compareTo(sum2);
		}else{
			Integer sum1 = 0;
			for(Card card: this.cardInCombination){
				sum1+=card.getValue().getValue();
			}
			Integer sum2 = 0;
			for(Card card: o.cardInCombination){
				sum2+=card.getValue().getValue();
			}
			sum1+=this.combinationType.getValue();
			sum2+=o.combinationType.getValue();
			return sum1.compareTo(sum2);
		}
			
	}
	public Combination setCombinationType(CombinationType e){
		this.combinationType=e;
		return this;
	}
	/**
	 * Remove cards with same CardValue.
	 * @param cards - source
	 * @param a 
	 */
	private ArrayList<Card> removeCardsByValue(ArrayList<Card> cards, Card a){
//		for()
		return null;
	}
	
	@Override
	public String toString(){
		return this.combinationType.toString();
	}
}
