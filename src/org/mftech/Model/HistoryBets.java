package org.mftech.Model;

import java.util.ArrayList;
import java.util.List;

import org.mftech.Model.Enumerations.ActionType;
import org.mftech.Model.Enumerations.StageType;

//List<HistoryBets> history = new ArrayList<HistoryBets>();
public class HistoryBets {

	private List<HistoryBet> history;
	public int CurrentIndex;
	public int LastFindIndex;
	
	HistoryBets()
	{
		history = new ArrayList<HistoryBet>();
		CurrentIndex =-1;
		LastFindIndex=-1;
	}
	
	public void setHistoryBet(StageType type, Player nPlayer, ActionType BetType, int amount)
	{
		//history
		HistoryBet e = new HistoryBet(type, nPlayer, BetType, amount);
		HistoryBet lastBetbyPlayer = new HistoryBet();
		
		
		// May be error
		lastBetbyPlayer = this.getHistoryBetByPlayer(nPlayer,type);
			if(lastBetbyPlayer!=null)
			{
				int index = this.getIndexHistoryBetByPlayer(nPlayer,type);
				this.history.get(index).StatusFin = false;
			}
		///////////////////
			
		history.add(e);
	}
	public int getIndexHistoryBetByPlayer(Player pl, StageType var_st)
	{
		int indexSearch=0;
		for(int i=0;i<history.size();i++)
		{
			var_st.getValue();
			if(history.get(i).nPlayer.equals(pl) && history.get(i).type.equals(var_st))
				indexSearch=i;
		}
		return indexSearch;
	}
	public HistoryBet getHistoryBetByPlayer(Player pl, StageType var_st)
	{
		HistoryBet hb =new HistoryBet();		
		hb = history.get(this.getIndexHistoryBetByPlayer(pl,var_st));
		return hb;

	}
	
	public int getAmountRound(Player pl)
	{
		int amount =0;
		for(int i=0;i<history.size();i++)
		{
			if(history.get(i).nPlayer.equals(pl) && history.get(i).StatusFin.equals(true))
				amount += history.get(i).amount;	
		}
		return amount;
		
	}
	
	
}


