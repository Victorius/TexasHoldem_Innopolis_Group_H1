package AiStrategies;

import java.util.Random;

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
				Random r = new Random();
				if(r.nextBoolean())
					return new PlayerAction(ActionType.Raise){{setAmount(bid);}};
				else
					return new PlayerAction(ActionType.Fold);			 
			}
		}else{
			if(canCheck(player, table)){
				return new PlayerAction(ActionType.CallCheck);
			}else{
				return new PlayerAction(ActionType.Fold);
			}
		}
	}
}
