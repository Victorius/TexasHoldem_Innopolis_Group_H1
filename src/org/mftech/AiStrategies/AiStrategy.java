package org.mftech.AiStrategies;

import java.util.List;

import org.mftech.Model.Bet;
import org.mftech.Model.Player;
import org.mftech.Model.PlayerAction;
import org.mftech.Model.Table;

public abstract class AiStrategy {
	public abstract PlayerAction getAction(Player player,Table table);
	
	protected int getMaxCircle(List<Bet> bets) {
		int res = 0;
		for(Bet b : bets){
			if(b.getCircle() > res)
			{
				res = b.getCircle();
			}
		}
		return res;
	}
	
	protected Bet getMaxBet(List<Bet> bets,int circle){
		Bet max = bets.get(0);
		for(Bet b : bets){
			if(b.getCircle() == circle && b.getAmount() > max.getAmount()){
				max = b;
			}
		}
		return max;
	}
}
