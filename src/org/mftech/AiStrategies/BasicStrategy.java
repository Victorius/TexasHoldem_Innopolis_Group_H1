package org.mftech.AiStrategies;

import java.util.List;

import org.mftech.Model.Bet;
import org.mftech.Model.Combination;
import org.mftech.Model.Player;
import org.mftech.Model.PlayerAction;
import org.mftech.Model.Table;
import org.mftech.Model.Enumerations.ActionType;
import org.mftech.Model.Enumerations.CombinationType;

public class BasicStrategy extends AiStrategy{

	@Override
	public PlayerAction getAction(Player player, Table table) {
		if(player.getBalance() == 0){
			if(canCheck(player, table)){
				return new PlayerAction(ActionType.CallCheck);
			}
			else{
				return new PlayerAction(ActionType.Fold);
			}
		}
		
		final int bid = player.getBalance() < 50 ? player.getBalance() : 50;
		
		Combination comb = player.getCombination();
		PlayerAction lastAction = player.getLastAction();
		
		if(comb.getType() != CombinationType.HighCard){
			if(canCheck(player, table) && lastAction.getType() == ActionType.Raise){
				return new PlayerAction(ActionType.CallCheck);
			}else if(canCheck(player, table) && lastAction.getType() == ActionType.CallCheck){
				return new PlayerAction(ActionType.CallCheck);
			}else if (!canCheck(player, table) && lastAction.getType() == ActionType.CallCheck){
				return new PlayerAction(ActionType.Fold);
			}else{
				return new PlayerAction(ActionType.Raise){{setAmount(bid);}};
			}
		}else{
			if(canCheck(player, table)){
				return new PlayerAction(ActionType.CallCheck);
			}else{
				return new PlayerAction(ActionType.Fold);
			}
		}
	}

	private boolean canCheck(Player player, Table table) {
		List<Bet> bets = table.getBets();
		int lastCircle = getMaxCircle(bets);
		Bet max = getMaxBet(bets, lastCircle);
		if(lastCircle == player.getLastBet().getCircle()){
			if(player.getLastBet() == max || player.getLastBet().getAmount() == max.getAmount()){
				return true;
			}else {
				return false;
			}
		}else{
			return false;
		}
	}
}
