/*
 * Card class is a basic representation of a gaming card. One of core classes 
*/
package org.mftech.Model;

import org.mftech.Model.Enumerations.CardType;
import org.mftech.Model.Enumerations.CardValue;

public class Card implements Comparable<Card>
{
	//Fields
	private final CardType type;
	private final CardValue value;

	//Constructor
	public Card(CardType type,CardValue value)
	{
		this.type = type;
		this.value = value;
	}

	//Getters
	public CardType getType()
	{
		return type;
	}
	public CardValue getValue()
	{
		return value;
	}

	@Override
	public int compareTo(Card o) {
		if(o instanceof Card){
			return this.value.compareTo(o.value);			
		}
		return 0;
	}
	
	public int equals(Card o) {
		if(o instanceof Card){
			return this.value.compareTo(o.value);			
		}
		return 0;
	}
	public String toString(){
		String result = this.value+" of "+this.type;
		return result;
	}
}
