package main.java.Model;

import java.util.List;

import main.java.Interfaces.IPlayer;
import main.java.Model.Enumerations.ActionType;

public abstract class Player extends CombinationCounter implements IPlayer {

    //Fields
    protected int balance;
    protected String name;
    protected boolean isBigBlind;
    protected boolean isSmallBlind;
    protected PlayerAction lastAction = new PlayerAction(ActionType.None);

    //Constructor
    public Player(String name, Table table) {
        this.name = name;
        this.balance = table.getSettings().getStartingMoney();
        this.aTable = table;
        isBigBlind = false;
        isSmallBlind = false;
    }

    //Setters
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSmallBlind(boolean smallBlind) {
        this.isSmallBlind = smallBlind;
    }
    public void setBigBlind(boolean bigBlind) {
        this.isBigBlind = bigBlind;
    }


    public int getBalance() {
        return balance;
    }
    public String getName() {
        return name;
    }
    public boolean isBigBlind() {
        return isBigBlind;
    }
    public boolean isSmallBlind() {
        return isSmallBlind;
    }

    public void clearBlinds() {
        isSmallBlind = false;
        isBigBlind = false;
    }

    //adding amount(pot) to player's balance
    public void addToBalance(int amount) {
        balance += amount;
    }

	public List<Card> getHand() {
		return cards;
	}

	public PlayerAction getLastAction() {
		return lastAction;
	}

}
