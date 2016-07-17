package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Table {

    //Fields
    private LinkedList<Player> players = new LinkedList<Player>();
    private TableSettings settings;
    private List<Card> cards;
    private List<Bet> bets;
    private Croupier croupier;
    private int pot;

    //Constructor
    public Table(TableSettings settings) {
        this.settings = settings;
        bets = new ArrayList<>();
        pot = 0;
    }

    //Getters
    public LinkedList<Player> getPlayers() {
        return players;
    }
    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
    }
    public TableSettings getSettings() {
        return settings;
    }
    public List<Card> getCards() {
        return cards;
    }
    public int getPot() {
        return pot;
    }

    //Setters
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    public List<Bet> getBets() {
        return bets;
    }
    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
    public void setPot(int pot) {
        this.pot = pot;
    }

    //Entrance to the game to start
    public void start() {
    	init();
        croupier = new Croupier(this);//Croupier comes to table and shit is about to get crazy ladies and jentelmens
    }

    public void addBetToList(Bet bet) {
        bets.add(bet);
    }
    
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
	public Player[] getAiPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getBetsAmount() {
		int sum = 0;
		for(Bet b : bets){
			sum += b.getAmount();
		}
		return sum;
	}
	
	private void init(){
		players.add(new Player("Player", settings.getStartingMoney()));
		for(int i = 0; i < settings.getNumberOfPlayers() - 1;i++)
		{
			players.add(new Player("Ai "+(i+1),settings.getStartingMoney()));
		}
	}

}
