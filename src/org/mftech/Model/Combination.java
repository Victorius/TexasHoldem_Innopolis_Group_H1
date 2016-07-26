package org.mftech.Model;

import java.util.ArrayList;
import java.util.Collections;

import org.mftech.Model.Enumerations.CombinationType;

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
			switch(combinationType){
				case NoCombination:
					return 0;
				case TwoPairs:					
					for(int k =0;k<2;k++){
						sp=null;
						for(int i=0;i<this.cardInCombination.size()-1;i++){
							if(this.cardInCombination.get(i).compareTo(this.cardInCombination.get(i+1))==0){
								sp=this.cardInCombination.get(i);
										break;
							}
						}
						this.cardInCombination.remove(sp);
						o.cardInCombination.remove(sp);
					}						
				case Quads:
				case ThreeOfKind:
					sp=null;
					for(int i=0;i<this.cardInCombination.size()-1;i++){
						if(this.cardInCombination.get(i).compareTo(this.cardInCombination.get(i+1))==0){
							sp=this.cardInCombination.get(i);
									break;
						}
					}
					this.cardInCombination.remove(sp);
					o.cardInCombination.remove(sp);
				case Pair:
					for(int k=0;k<2;k++){
						sp=null;
						for(int i=0;i<this.cardInCombination.size()-1;i++){
							if(this.cardInCombination.get(i).compareTo(this.cardInCombination.get(i+1))==0){
								sp=this.cardInCombination.get(i);
										break;
							}
						}
						this.cardInCombination.remove(sp);
						o.cardInCombination.remove(sp);
					}
					Collections.sort(this.cardInCombination);
					Collections.sort(o.cardInCombination);
				int min= Math.min(this.cardInCombination.size(), o.cardInCombination.size());
				for(int i=min-1;i>0;i--){
					if(this.cardInCombination.get(i).compareTo(o.cardInCombination.get(i))!=0)
						return this.cardInCombination.get(i).compareTo(o.cardInCombination.get(i));
				}
			}
			return 0;
		}else
			return this.combinationType.compareTo(o.combinationType);
//		return this.combinationType.compareTo(o.combinationType);
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
