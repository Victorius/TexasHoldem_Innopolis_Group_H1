package Model;

import java.util.LinkedList;

public class Croupier {
    //Fields
    private Table table;
    private int smallBlind;
    private int bigBlind;
    private int blindAmount;
    private LinkedList<Player> players;

    //Constructor
    public Croupier(Table table) {
        this.table = table;
        smallBlind = 0;
        bigBlind = 1;
        players = table.getPlayers();
        setInitialBlinds();
        blindAmount = table.getSettings().getBlindAmount();
    }

    //Getters
    public Table getTable() {
        return table;
    }
    public int getSmallBlind() {
        return smallBlind;
    }
    public int getBigBlind() {
        return bigBlind;
    }
    public int getBlindAmount() {
        return blindAmount;
    }
    public LinkedList<Player> getPlayers() {
        return players;
    }

    //Setters
    public void setTable(Table table) {
        this.table = table;
    }
    public void setSmallBlind(int smallBlind) {
        this.smallBlind = smallBlind;
    }
    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }
    public void setBlindAmount(int blindAmount) {
        this.blindAmount = blindAmount;
    }
    public void setPlayers(LinkedList<Player> players) {
        this.players = players;
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
        if (players.size() >= 3) { //more than 3 players
            if (bigBlind == players.size() - 1) {
                bigBlind = 0;
                smallBlind = players.size() - 1;
            } else if (smallBlind == players.size() - 1) {
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
        for (int i = 0; i < players.size(); i++) {
            players.get(i).clearBlinds();
        }
        Player bigBlindPlayer = players.get(bigBlind);
        Player smallBlindPlayer = players.get(smallBlind);

        smallBlindPlayer.setSmallBlind(true);
        smallBlindPlayer.makeBet(blindAmount);

        bigBlindPlayer.setBigBlind(true);
        bigBlindPlayer.makeBet(blindAmount * 2);
    }

    //collecting all bets from players - to the table
    public void collectBetsFromPlayers() {
        int pot = 0;
        for (Player player : players) {
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
        for (Player pickedPlayer : players) {
            if (pickedPlayer == player) {
                pickedPlayer.addToBalance(table.getPot());
            }
        }
        table.setPot(0);
    }

    //pay pot 50/50 to 2 players
    public void separateAndPayPot(Player player1, Player player2) {
        for (Player pickedPlayer : players) {
            if (pickedPlayer == player1) {
                pickedPlayer.addToBalance(table.getPot() / 2);
            }
            if (pickedPlayer == player2) {
                pickedPlayer.addToBalance(table.getPot() / 2);
            }
        }
        table.setPot(0);
    }

}
