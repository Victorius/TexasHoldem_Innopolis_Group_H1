package main.java.Model;

public class Bet {
	//fields
	private int circle;
	private Player player;
	private Integer amount;
	//C-tor
	public Bet(int circle,Player player,int amount)
	{
		this.circle = circle;
		this.player = player;
		this.amount = amount;
	}
	//Getters
	public int getCircle() {
		return circle;
	}
	public int getAmount() {
		return amount;
	}
	public Player getPlayer() {
		return player;
	}
	
	public int compareByValue(Bet o){
		return this.amount.compareTo(o.amount);
	}
}
