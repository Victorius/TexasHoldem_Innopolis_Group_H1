package org.mftech.Model;

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
		lastAction =  strat.getAction(this, aTable);
		return lastAction;
	}
	
	private static String getRandomAiName(){
		Random r = new Random();
		return computerNames[r.nextInt(computerNames.length - 1)];
	}

}
