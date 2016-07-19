package main.java.Model;

import main.java.Interfaces.IPlayer;

public class Player extends CombinationCounter implements IPlayer {

    //Fields
    private int balance;
    private String name;
    private boolean isBigBlind;
    private boolean isSmallBlind;
    private Bet bet;

    //Constructor
    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
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

	public Card[] getHand() {
		return (Card[]) cards.toArray();
	}

	public Object getLastAction() {
		return bet;
	}

}
