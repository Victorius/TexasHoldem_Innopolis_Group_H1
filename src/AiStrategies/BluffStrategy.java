package AiStrategies;
import java.util.Random;
import main.java.Model.Player;
import main.java.Model.PlayerAction;
import main.java.Model.Table;
import main.java.Model.Enumerations.ActionType;

public class BluffStrategy extends AiStrategy{

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
		Random r = new Random();
		if(canCheck(player, table)){
			if(r.nextBoolean()){
				return new PlayerAction(ActionType.Raise){{setAmount(bid);}};
			}else{
				return new PlayerAction(ActionType.CallCheck);				
			}
		}else{
			if(r.nextBoolean()){
				return new PlayerAction(ActionType.Raise){{setAmount(bid);}};
			}else{
				return new PlayerAction(ActionType.Fold);				
			}
		}
	}
}
