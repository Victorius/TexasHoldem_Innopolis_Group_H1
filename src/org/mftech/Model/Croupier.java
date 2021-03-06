package org.mftech.Model;

import java.util.ListIterator;

import org.mftech.Model.Enumerations.ActionType;
import org.mftech.UI.UiHelper;

import java.util.ArrayList;
import java.util.List;

public class Croupier {
    //Fields
    private final Table table;
    private int smallBlind = 0;
    private int bigBlind = 1;
    private int currentPlayer = 0;
    //Constructor
    public Croupier(Table table) {
        this.table = table;
    }

    //setting up blinds as game starts
    public void setInitialBlinds() {
    	table.getPlayers().get(smallBlind).setSmallBlind(true);
    	makeBet(table,table.getPlayers().get(smallBlind),0,table.getSettings().getBlindAmount());
    	table.getPlayers().get(bigBlind).setBigBlind(true);
    	makeBet(table,table.getPlayers().get(bigBlind),0,table.getSettings().getBlindAmount() * 2);
    }

    private void makeBet(Table t, Player p, int circle, int amount) {
    	Bet bet = new Bet(circle, p, amount);
    	t.addBetToList(bet);
    	p.setLastBet(bet);
    	p.addToBalance(- bet.getAmount());
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
    
    public void getPlayerAction(Player player){
    	player.getPlayerAction();	
    }
    
    //pay pot 50/50 to 2 players
    public void separateAndPayPot(List<Player> players) {
        for (Player pickedPlayer : players) {
        	pickedPlayer.addToBalance(table.getBetsAmount() / players.size());
        }
        table.clearBets();
    }
    
    public void getActionsStartingFrom(Player player){
    	int startingPosition = table.getPlayers().indexOf(player);
    	int circle = 0;
    	for(int i = startingPosition; i <= table.getPlayers().size(); i++){
    		if(i == table.getPlayers().size()){
    			i = 0;
    		}
    		else if(table.getPlayers().indexOf(player) == i){
    			System.out.println("CIRCLE #" + circle);
    			circle++;
    			//some method to call after circle is complete(checking if the next move is raise?)	
    		}
    		boolean loopBreak = false;
    		switch(table.getPlayers().get(i).getPlayerAction().getType()){

				case CallCheck:
					System.out.println("CHECK!");
					break;
				case Fold:
					System.out.println("FOLD!");
					removePlayer(table.getPlayers().get(i));
					if(table.getPlayers().size() == 1){
						payPotToPlayer(table.getPlayers().get(i));
						System.out.println("Player " + table.getPlayers().get(i).getName() + " won!");
						loopBreak = true;
					}
					i--;
					break;
				case Raise:
					System.out.println("RAISE!");
					break;
				default:
					break;
    		}
    		
    		if(loopBreak){
    			System.out.println("Game is finished");
    			break;
    		}
    	}
    }
    
    public void removePlayer(Player player){
    	table.getPlayers().remove(player);
    }

    public void StartGame() {
    	boolean gameEnds = false;
    	while(!gameEnds)
    	{
    		table.clearCards();
    		table.clearBets();
    		final ArrayList<Card> deck = Deck.getNewRandomDeck();
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
