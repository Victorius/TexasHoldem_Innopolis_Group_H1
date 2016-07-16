/*
 * Card class is a basic representation of a gaming card. One of core classes 
*/
package Model;

import Model.Enumerations.CardType;
import Model.Enumerations.CardValue;

public class Card 
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
	public cardType GetType()
	{
		return type;
	}
	
	public cardValue GetValue()
	{
		return value;
	}
}
