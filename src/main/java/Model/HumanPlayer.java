package main.java.Model;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, int balance) {
		super(name, balance);
	}

	@Override
	public PlayerAction getPlayerAction() {
		return null;
	}

}
