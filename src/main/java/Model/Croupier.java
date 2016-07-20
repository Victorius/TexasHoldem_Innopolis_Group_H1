package main.java.Model;

import java.util.ArrayList;

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
        if (table.getPlayers().size() >= 3) {
            smallBlind = 0;
            bigBlind = 1;
        }
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
                pickedPlayer.addToBalance(table.getPot());
            }
        }
        table.setPot(0);
    }

    //pay pot 50/50 to 2 players
    public void separateAndPayPot(Player player1, Player player2) {
        for (Player pickedPlayer : table.getPlayers()) {
            if (pickedPlayer == player1) {
                pickedPlayer.addToBalance(table.getPot() / 2);
            }
            if (pickedPlayer == player2) {
                pickedPlayer.addToBalance(table.getPot() / 2);
            }
        }
        table.setPot(0);
    }
    
    public void StartGame() {
    	ArrayList<Card> deck = Deck.getNewRandomDeck();
		//drawing table info
		UiHelper.updateTableInfo(table);
		//settings blinds
		setInitialBlinds();
		//draw 2 cards each player
		for(Player p : table.getPlayers()){
			p.setCards(new ArrayList<Card>() {{add(deck.remove(0));add(deck.remove(0));}});
		}
		//bet circle
		RaisingIteration();
		//preflop this table 
		table.addCards(new ArrayList<Card>() {{add(deck.remove(0));add(deck.remove(0));add(deck.remove(0));}});
		//one more iteration
		RaisingIteration();
		//flop
		table.addCards(new ArrayList<Card>() {{add(deck.remove(0));}});
		//one more iteration
		RaisingIteration();
		//shit its too late now to remember last phase of the game
		table.addCards(new ArrayList<Card>() {{add(deck.remove(0));}});
		//last one
		RaisingIteration();
		
	}

	private void RaisingIteration() {
		// TODO Auto-generated method stub
		
	}
}
