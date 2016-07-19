package main.java.Model;

import java.util.Random;

public class AiPlayer extends Player{
	private static String[] computerNames = {"Orbb","Klesk","Visor","Xaero","Sarge","Grunt","Doom","Ranger","Sorlag","Cormack","Crash","Anarki"};

	public AiPlayer(Table table) {
		super(getRandomAiName(), table);
	}

	@Override
	public PlayerAction getPlayerAction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static String getRandomAiName(){
		Random r = new Random();
		return computerNames[r.nextInt(computerNames.length - 1)];
	}

}
