package main.java.Model;

import java.util.ListIterator;

import main.java.Model.Enumerations.ActionType;
import main.java.UI.UiHelper;

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
        if (table.getPlayers().size() >= 3) {
            smallBlind = 0;
            bigBlind = 1;
        }
        assignBlinds();
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
        assignBlinds();
    }

    //assigning blinds to players
    private void assignBlinds() {
        for (int i = 0; i < table.getPlayers().size(); i++) {
        	table.getPlayers().get(i).clearBlinds();
        }
        
        Player bigBlindPlayer = table.getPlayers().get(bigBlind);
        Player smallBlindPlayer = table.getPlayers().get(smallBlind);

        smallBlindPlayer.setSmallBlind(true);
        smallBlindPlayer.makeBet(table.getSettings().getBlindAmount());

        bigBlindPlayer.setBigBlind(true);
        bigBlindPlayer.makeBet(table.getSettings().getBlindAmount() * 2);
    }

    //collecting all bets from players - to the table
    public void collectBetsFromPlayers() {
        int pot = 0;
        for (Player player : table.getPlayers()) {
            if (player.getBet() != null) {
                pot += player.getBet().getAmount();
                table.addBetToList(player.getBet());
                player.clearBet();
                table.setPot(pot);
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
    
    public void getPlayerAction(Player player){
    	player.getPlayerAction();	
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
				case Call:
					System.out.println("CALL!");
					break;
				case Check:
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
					table.getPlayers().get(i).raiseBet(100); //100 for testing
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
		UiHelper.updateTableInfo(table);
	}
}
