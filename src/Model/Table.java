package Model;

import java.util.LinkedList;
import java.util.List;

public class Table {
	//fields
	private LinkedList<Player> players;
	private TableSettings settings;
	private List<Card> cards;
	private List<Bet> bets;
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
	public void setBets(List<Bet> bets)
	{
		this.bets = bets;
	}
	
}
