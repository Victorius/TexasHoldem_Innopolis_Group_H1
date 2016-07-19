package main.java.Model;

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
		UiHelper.updateTableInfo(table);
	}
}
