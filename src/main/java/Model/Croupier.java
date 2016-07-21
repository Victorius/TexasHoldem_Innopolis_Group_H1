package main.java.Model;

import java.util.ListIterator;


import main.java.Model.Enumerations.ActionType;
import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;
import main.java.UI.UiHelper;

public class Croupier {
	// Fields
	private final Table table;
	private int smallBlind = 0;
	private int bigBlind = 1;
	private int circle = 0;

	// Constructor
	public Croupier(Table table) {
		this.table = table;
	}

	// setting up blinds as game starts
	public void setInitialBlinds() {
		table.getPlayers().get(smallBlind).setSmallBlind(true);
		takeMoney(table.getPlayers().get(smallBlind), 0, table.getSettings().getBlindAmount());
		table.getPlayers().get(bigBlind).setBigBlind(true);
		takeMoney(table.getPlayers().get(bigBlind), 0, table.getSettings().getBlindAmount() * 2);
	}

	// move blinds clockwise
	public void moveBlinds() {
		if (table.getPlayers().size() >= 3) { // more than 3 players
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
		} else { // less than 3 players
			if (bigBlind == 0) {
				bigBlind = 1;
				smallBlind = 0;
			} else {
				bigBlind = 0;
				smallBlind = 1;
			}
		}
	}

	// return bet to player
	// true/false
	// beacon
	// while
	public Player getActions() {
		addPlayersInGame();
		setInitialBlinds();
		ListIterator<Player> listIterator = table.getPlayers().listIterator();
		boolean running = true;
		while (running) {
			Player player = listIterator.next();
			if (!player.isCurrent()) {
				while (!player.isCurrent()) {
					if (table.getPlayers().getLast().equals(player)) {
						listIterator = table.getPlayers().listIterator();
					}
					player = listIterator.next();
					
				}
			}
			if (player.isIngame()) {
				if (player.isCurrent() && table.getPlayers().indexOf(player) != 0) {
					circle++;
				}
				if (player == table.getPlayers().getLast()) {
					listIterator = table.getPlayers().listIterator();
				}
				switch (player.getPlayerAction().getType()) {
				case CallCheck:
					// match highest bet ?
					
					break;
				case Fold:
					removePlayer(player);
					if (table.getPlayers().size() == 1) {
						return table.getPlayers().get(0);
					}
					break;
				case Raise:
					deselectCurrentPlayers();
					player.setCurrent(true);
					takeMoney(player, circle, player.getLastAction().getAmount());
					listIterator = table.getPlayers().listIterator();
					break;
				default:
					break;
				}
			}
		}
		return null;
	}

	public void takeMoney(Player player, int circle, int amount) {
		if (amount < player.getBalance()) {
			player.setBalance(player.getBalance() - amount);
			Bet bet = new Bet(circle, player, amount);
			table.addBetToList(bet);
			player.setLastBet(bet);
		}

	}

	private void addPlayersInGame() {
		for (Player player : table.getPlayers()) {
			player.setIngame(true);
		}
	}

	private void deselectCurrentPlayers() {
		for (Player player : table.getPlayers()) {
			if (player.isCurrent()) {
				player.setCurrent(false);
			}
		}
	}

	public void removePlayer(Player player) {
		table.getPlayers().remove(player);
	}

	public void StartGame() {
		boolean gameEnds = false;
		while (!gameEnds) {
			table.clearCards();
			final ArrayList<Card> deck = Deck.getNewRandomDeck();
			// drawing table info
			UiHelper.updateTableInfo(table);
			// settings blinds
			setInitialBlinds();
			UiHelper.updateTableInfo(table);
			// draw 2 cards each player
			for (Player p : table.getPlayers()) {
				p.setCards(new ArrayList<Card>() {
					{
						add(deck.remove(0));
						add(deck.remove(0));
					}
				});
			}
			UiHelper.updateTableInfo(table);
			// bet circle
			if (RaisingIteration()) {
				// raise methond without showing
				continue;
			}
			// preflop this table
			table.addCards(new ArrayList<Card>() {
				{
					add(deck.remove(0));
					add(deck.remove(0));
					add(deck.remove(0));
				}
			});
			UiHelper.updateTableInfo(table);
			// one more iteration
			if (

			RaisingIteration()) {
				// raise methond without showing
				continue;
			} // flop
			table.addCards(new ArrayList<Card>() {
				{
					add(deck.remove(0));
				}
			});
			UiHelper.updateTableInfo(table);
			// one more iteration
			if (RaisingIteration()) {
				// raise methond without showing
				continue;
			} // shit its too late now to remember last phase of the game
			table.addCards(new ArrayList<Card>() {
				{
					add(deck.remove(0));
				}
			});
			UiHelper.updateTableInfo(table);
			// last one
			if (RaisingIteration()) {
				// raise methond without showing
				continue;
			}
			// raise method to shouw cards
		}

	}

	private boolean RaisingIteration() {
		return false;
	}
}
