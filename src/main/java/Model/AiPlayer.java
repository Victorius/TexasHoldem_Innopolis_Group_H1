package main.java.Model;

import java.util.Random;

import AiStrategies.AiStrategy;

public class AiPlayer extends Player{
	private static String[] computerNames = {"Orbb","Klesk","Visor","Xaero","Sarge","Grunt","Doom","Ranger","Sorlag","Cormack","Crash","Anarki"};
	private AiStrategy strat;

	public AiPlayer(Table table,AiStrategy strategy) {
		super(getRandomAiName(), table);
		this.strat = strategy; 
	}

	@Override
	public PlayerAction getPlayerAction() {
		return strat.getAction(this, aTable);
	}
	
	private static String getRandomAiName(){
		Random r = new Random();
		return computerNames[r.nextInt(computerNames.length - 1)];
	}

}
