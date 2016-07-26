package org.mftech.Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import AiStrategies.BasicStrategy;
import AiStrategies.BluffStrategy;

public class Table {

    //Fields
    private LinkedList<Player> players = new LinkedList<Player>();
    private TableSettings settings;
    private List<Card> cards = new ArrayList<Card>();
    private List<Bet> bets = new ArrayList<Bet>();

    //Constructor
    public Table(TableSettings settings) {
        this.settings = settings;
        bets = new ArrayList<>();
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

    //Setters
    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }
    public List<Bet> getBets() {
        return bets;
    }
    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
    public void addBetToList(Bet bet) {
        bets.add(bet);
    }
    
	public Player getPlayer() {
		return players.getFirst();
	}
	public List<Player> getAiPlayers() {
		List<Player> result = new ArrayList<Player>();
		for(Player p : players){
			if(p.getName() != "Player"){
				result.add(p);
			}
		}
		return result;
	}

	public Integer getBetsAmount() {
		int sum = 0;
		for(Bet b : bets){
			sum += b.getAmount();
		}
		return sum;
	}
	
	public void init(){
		players.add(new HumanPlayer("Player",this));
		for(int i = 0; i < settings.getNumberOfPlayers() - 1;i++)
		{
			Random r = new Random();
			players.add(new AiPlayer(this,r.nextBoolean() ? new BasicStrategy() : new BluffStrategy()));
		}
	}

	public void clearBets() {
		bets.clear();
		for(Player p : players){
			p.setLastBet(null);
			p.clearLastAction();
			p.setCircleAllin(-1);
		}
	}
	
	public void clearLastAction(){
		for(Player p : players){
			p.setLastBet(null);
			p.clearLastAction();
		}
	}

	public void clearCards() {
		cards.clear();
		for(Player p : players){
			p.clearHand();
		}
	}

	public void removePlayer(Player a) {
		players.remove(a);
	}

}
