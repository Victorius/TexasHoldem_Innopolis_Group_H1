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
    protected Bet lastBet;
    protected boolean current;
    protected boolean ingame;
    protected int circleAllin = -1;
    
    //Constructor
    public Player(String name, Table table) {
        this.name = name;
        this.balance = table.getSettings().getStartingMoney();
        this.aTable = table;
        isBigBlind = false;
        isSmallBlind = false; 
        current = false;
        ingame = false;
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
	
	public void setLastBet(Bet b){
		lastBet =b ;
	}
	
	public Bet getLastBet(){
		return lastBet;
	}
	
	public boolean isCurrent() {
		return current;
	}
	
	public void setCurrent(boolean current) {
		this.current = current;
	}

	public boolean isIngame() {
		return ingame;
	}
	
	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}

	public void setCircleAllin(int circleAllin) {
		this.circleAllin = circleAllin;
	}
	
	public int getCircleAllin() {
		return circleAllin;
	}

	public void clearHand() {
		cards.clear();
	}

	public void clearLastAction() {
		lastAction = new PlayerAction(ActionType.None);
	}

	public void setLastAction(PlayerAction action) {
		this.lastAction = action;
	}
}
