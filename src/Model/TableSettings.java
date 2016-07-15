package Model;

import Model.Enumerations.RaisingPolicy;

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
	public int GetNumberOfPlayers()
	{
		return numberOfPlayers;
	}
	public int GetStartingMoney()
	{
		return startingMoney;
	}
	public int GetBlindAmount()
	{
		return blindAmount;
	}
	public void SetBlindAmount (int value)
	{
		blindAmount = value;
	}
	public RaisingPolicy GetRaisingPolicy()
	{
		return policy;
	}
	
}
