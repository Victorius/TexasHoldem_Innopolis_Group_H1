/*
 * Card class is a basic representation of a gaming card. One of core classes 
*/
package main.java.Model;

import main.java.Model.Enumerations.CardType;
import main.java.Model.Enumerations.CardValue;

public class Card implements Comparable
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
	public int compareTo(Object o) {
		if(o instanceof Card){
			return this.value.compareTo(((Card)o).value);			
		}
		return 0;
	}
}
