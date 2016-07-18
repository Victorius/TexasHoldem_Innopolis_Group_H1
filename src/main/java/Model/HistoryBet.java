package main.java.Model;

import main.java.Model.Enumerations.ActionType;
import main.java.Model.Enumerations.StageType;

public class HistoryBet {
	public final StageType type;
	public final Player nPlayer;
	public final ActionType BetType;
	public final int amount;
	public Boolean StatusFin;
	
	HistoryBet(StageType type, Player nPlayer, ActionType BetType, int amount)
	{
		this.type=type;
		this.nPlayer=nPlayer;
		this.BetType=BetType;
		this.amount=amount;
		this.StatusFin =true;
	}
	HistoryBet()
	{
		this.type=null;
		this.nPlayer=null;
		this.BetType=null;
		this.amount=(Integer) null;
		this.StatusFin = false;

	}
	public int getKittyCircle(StageType input_type){
		return 1;
	}
}
