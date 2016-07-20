package main.java.Model;

import java.util.List;

import main.java.Interfaces.IPlayer;

public abstract class Player extends CombinationCounter implements IPlayer {

    //Fields
    private int balance;
    private String name;
    private boolean isBigBlind;
    private boolean isSmallBlind;
    private Bet bet;
    public PlayerAction action;

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
    public void setBet(Bet bet) {
        this.bet = bet;
    }
    public void setSmallBlind(boolean smallBlind) {
        this.isSmallBlind = smallBlind;
    }
    public void setBigBlind(boolean bigBlind) {
        this.isBigBlind = bigBlind;
    }


    //Getters
    public Bet getBet() {
        return bet;
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


    //to place bet
    public void makeBet(int amount) {
        if (amount > balance) {
            bet = new Bet(0, this, balance); // all in
            balance = 0;
        } else {

            balance -= amount;
            bet = new Bet(0, this, amount);
        }
    }

    //raise existing bet
    public void raiseBet(int amount) {
        if (bet != null) {
            if (amount > balance) {
                bet.raise(balance); // all in
                balance = 0;
            } else {
                bet.raise(amount);
                   balance -= amount;
            }
        }
    }
    
    public void setPlayerAction(PlayerAction action){
    	this.action = action;
    }

    //clear placed bet
    public void clearBet() {
        bet = null;
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
		return action;
	}
	
	@Override
	public PlayerAction getPlayerAction() {
		return action;
	}

}
