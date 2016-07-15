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
	public LinkedList<Player> GetPlayers()
	{
		return players;
	}
	public void SetPlayers(LinkedList<Player> players)
	{
		this.players = players;
	}
	public TableSettings GetSettings()
	{
		return settings;
	}
	public List<Card> GetCards()
	{
		return cards;
	}
	public void SetCards(List<Card> cards) 
	{
		this.cards = cards;
	}
	public List<Bet> GetBets()
	{
		return bets;
	}
	public void SetBets(List<Bet> bets)
	{
		this.bets = bets;
	}
	
}
