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
	
	protected boolean canCheck(Player player, Table table) {
		List<Bet> bets = table.getBets();
		int lastCircle = getMaxCircle(bets) - 1;
		Bet max = getMaxBet(bets, lastCircle+1);
		Bet last = player.getLastBet();
		if(last == null){
			return true;
		}
		if(lastCircle == last.getCircle()){
			if(player.getLastBet().equals(max) || player.getLastBet().getAmount() == max.getAmount()){
				return true;
			}else {
				return false;
			}
		}else{
			return false;
		}
	}
}
