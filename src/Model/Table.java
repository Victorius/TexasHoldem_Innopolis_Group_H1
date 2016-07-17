package Model;

import java.util.LinkedList;
import java.util.List;

public class Table {
	//fields
	private LinkedList<Player> players;
	private TableSettings settings;
	private List<Card> cards;
	private List<Bet> bets;
	private Croupier croupier;
	//Constructor
	public Table(TableSettings settings)
	{
		this.settings = settings;
	}
	//Fields
	public LinkedList<Player> getPlayers()
	{
		return players;
	}
	public void setPlayers(LinkedList<Player> players)
	{
		this.players = players;
	}
	public TableSettings getSettings()
	{
		return settings;
	}
	public List<Card> getCards()
	{
		return cards;
	}
	public void setCards(List<Card> cards) 
	{
		this.cards = cards;
	}
	public List<Bet> getBets()
	{
		return bets;
	}
	//returning total bets amount currently on table
	public int getBetsAmount()
	{
		int sum = 0;
		for(Bet b : bets)
		{
			sum += b.getAmount();
		}
		return sum;
	}
	public void setBets(List<Bet> bets)
	{
		this.bets = bets;
	}
	
	//Entrance to the game to start
	public void start() {
		croupier = new Croupier(this);//Croupier comes to table and shit is about to get crazy ladies and jentelmens
	}
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
	public Player[] getAiPlayers() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
