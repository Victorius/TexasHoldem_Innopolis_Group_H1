package Model;

import Model.Enumerations.ActionType;
import Model.Enumerations.StageType;


public class HistoryBets {
	private final StageType type;
	private final int idPlayer;
	private final ActionType BetType;
	private final int amount;
	
	HistoryBets(StageType type, int idPlayer, ActionType BetType, int amount)
	{
		this.type=type;
		this.idPlayer=idPlayer;
		this.BetType=BetType;
		this.amount=amount;
		
	
	}
}


