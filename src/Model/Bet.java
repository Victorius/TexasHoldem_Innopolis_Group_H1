package Model;

public class Bet {
	//fields
	private int circle;
	private Player player;
	private int amount;
	//C-tor
	public Bet(int circle,Player player,int amount)
	{
		this.circle = circle;
		this.player = player;
		this.amount = amount;
	}
	//Getters
	public int GetCircle() {
		return circle;
	}
	public int GetAmount() {
		return amount;
	}
	public Player GetPlayer() {
		return player;
	}
	
}
