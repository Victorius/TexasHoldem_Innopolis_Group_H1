package AiStrategies;

import java.util.List;

import main.java.Model.Bet;
import main.java.Model.Combination;
import main.java.Model.Player;
import main.java.Model.PlayerAction;
import main.java.Model.Table;
import main.java.Model.Enumerations.ActionType;
import main.java.Model.Enumerations.CombinationType;

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
		
		int bid = player.getBalance() < 50 ? player.getBalance() : 50;
		
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

	private int getMaxCircle(List<Bet> bets) {
		int res = 0;
		for(Bet b : bets){
			if(b.getCircle() > res)
			{
				res = b.getCircle();
			}
		}
		return res;
	}
	
	private Bet getMaxBet(List<Bet> bets,int circle){
		Bet max = bets.get(0);
		for(Bet b : bets){
			if(b.getCircle() == circle && b.getAmount() > max.getAmount()){
				max = b;
			}
		}
		return max;
	}

}
