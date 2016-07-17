package Model;

import java.util.ArrayList;

import Model.Enumerations.CombinationType;

public class Combination {
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
}
