package main.java.Model;

import java.util.ArrayList;
import java.util.List;

import main.java.UI.UiHelper;

public class Croupier {
    //Fields
    private final Table table;
    private int smallBlind = 0;
    private int bigBlind = 1;

    //Constructor
    public Croupier(Table table) {
        this.table = table;
    }

    //setting up blinds as game starts
    public void setInitialBlinds() {
    	table.getPlayers().get(smallBlind).setSmallBlind(true);
    	table.addBetToList(new Bet(0, table.getPlayers().get(smallBlind), table.getSettings().getBlindAmount()));
    	table.getPlayers().get(bigBlind).setBigBlind(true);
    	table.addBetToList(new Bet(0, table.getPlayers().get(bigBlind), table.getSettings().getBlindAmount() * 2));
    }

    //move blinds clockwise
    public void moveBlinds() {
        if (table.getPlayers().size() >= 3) { //more than 3 players
            if (bigBlind == table.getPlayers().size() - 1) {
                bigBlind = 0;
                smallBlind = table.getPlayers().size() - 1;
            } else if (smallBlind == table.getPlayers().size() - 1) {
                bigBlind = 1;
                smallBlind = 0;
            } else {
                bigBlind++;
                smallBlind++;
            }
        } else { //less than 3 players
            if (bigBlind == 0) {
                bigBlind = 1;
                smallBlind = 0;
            } else {
                bigBlind = 0;
                smallBlind = 1;
            }
        }
    }

    //pay pot to one player
    public void payPotToPlayer(Player player) {
        for (Player pickedPlayer : table.getPlayers()) {
            if (pickedPlayer == player) {
                pickedPlayer.addToBalance(table.getBetsAmount());
            }
        }
        table.clearBets();
    }

    //pay pot 50/50 to 2 players
    public void separateAndPayPot(List<Player> players) {
        for (Player pickedPlayer : players) {
        	pickedPlayer.addToBalance(table.getBetsAmount() / players.size());
        }
        table.clearBets();
    }
    
    public void StartGame() {
    	boolean gameEnds = false;
    	while(!gameEnds)
    	{
    		table.clearCards();
    		ArrayList<Card> deck = Deck.getNewRandomDeck();
    		//drawing table info
    		UiHelper.updateTableInfo(table);
    		//settings blinds
    		setInitialBlinds();
    		UiHelper.updateTableInfo(table);
    		//draw 2 cards each player
    		for(Player p : table.getPlayers()){
    			p.setCards(new ArrayList<Card>() {{add(deck.remove(0));add(deck.remove(0));}});
    		}
    		UiHelper.updateTableInfo(table);
    		//bet circle
    		if(RaisingIteration()){
    			//raise methond without showing
    			continue;
    		}
    		//preflop this table 
    		table.addCards(new ArrayList<Card>() {{add(deck.remove(0));add(deck.remove(0));add(deck.remove(0));}});
    		UiHelper.updateTableInfo(table);
    		//one more iteration
    		if(RaisingIteration()){
    			//raise methond without showing
    			continue;
    		}//flop
    		table.addCards(new ArrayList<Card>() {{add(deck.remove(0));}});
    		UiHelper.updateTableInfo(table);
    		//one more iteration
    		if(RaisingIteration()){
    			//raise methond without showing
    			continue;
    		}//shit its too late now to remember last phase of the game
    		table.addCards(new ArrayList<Card>() {{add(deck.remove(0));}});
    		UiHelper.updateTableInfo(table);
    		//last one
    		if(RaisingIteration()){
    			//raise methond without showing
    			continue;
    		}
    		//raise method to shouw cards
    	}
	}

	private boolean RaisingIteration() {
		return false;
	}
}
