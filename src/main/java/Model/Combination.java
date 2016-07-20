package main.java.Model;

import java.util.ArrayList;

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
		return this.combinationType.compareTo(o.combinationType);
	}
}
