package Model;

import Model.Enumerations.ActionType;
import Model.Enumerations.StageType;


public class HistoryBets {
	private final StageType type;
	private final int idPlayer;
	private final ActionType BetType;
	
	HistoryBets(StageType type, int idPlayer, ActionType BetType)
	{
		this.type=type;
		this.idPlayer=idPlayer;
		this.BetType=BetType;
		
	
	}
}


/*import Model.Enumerations.CardType;
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
	public CardType GetType()
	{
		return type;
	}
	
	public CardValue GetValue()
	{
		return value;
	}
}*/
