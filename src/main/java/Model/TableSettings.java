package main.java.Model;

import main.java.Model.Enumerations.RaisingPolicy;

public class TableSettings {
	//Field
	private int numberOfPlayers;
	private int startingMoney;
	private int blindAmount;
	private RaisingPolicy policy;
	//Constructor
	public TableSettings(int numberOfPlayers,int startingMoney,int blindAmount,RaisingPolicy policy)
	{
		this.numberOfPlayers = numberOfPlayers;
		this.startingMoney = startingMoney;
		this.blindAmount = blindAmount;
		this.policy = policy;
	}
	//Getters/Setters
	public int getNumberOfPlayers()
	{
		return numberOfPlayers;
	}
	public int getStartingMoney()
	{
		return startingMoney;
	}
	public int getBlindAmount()
	{
		return blindAmount;
	}
	public void setBlindAmount (int value)
	{
		blindAmount = value;
	}
	public RaisingPolicy getRaisingPolicy()
	{
		return policy;
	}
	
}
