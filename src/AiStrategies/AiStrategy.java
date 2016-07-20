package AiStrategies;

import main.java.Model.Player;
import main.java.Model.PlayerAction;
import main.java.Model.Table;

public abstract class AiStrategy {
	public abstract PlayerAction getAction(Player player,Table table);
}
