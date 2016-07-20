package main.java.Model;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, Table table) {
		super(name, table);
	}

	@Override
	public PlayerAction getPlayerAction() {
		return action;
	}

}
