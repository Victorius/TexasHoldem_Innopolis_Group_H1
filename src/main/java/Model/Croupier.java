package main.java.Model;

import java.util.ListIterator;

import java.util.ArrayList;
import java.util.List;

import main.java.UI.UiHelper;

public class Croupier {
	// Fields
	private final Table table;
	private int smallBlind = 0;
	private int bigBlind = 1;
	
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
	public boolean getActions() {
		addPlayersInGame();
		int circle = 0;
		ListIterator<Player> listIterator = table.getPlayers().listIterator();
		boolean running = true;
		while (running) {
			Player player = listIterator.next();
			if (player.isIngame()) {
				
				if (player.isCurrent() && table.getPlayers().indexOf(player) != 0) {
					circle++;
				}
				if (player == table.getPlayers().getLast()) {
					listIterator = table.getPlayers().listIterator();
				}

				switch (player.getPlayerAction().getType()) {
				case CallCheck:
					//match highest bet ?
					break;
				case Fold:
					removePlayer(player);
					break;
				case Raise:
					deselectCurrentPlayers();
					player.setCurrent(true);
					takeMoney(player, circle, 500); // 500??
					listIterator = table.getPlayers().listIterator();
					break;
				default:
					break;
				}
			}
		}
		return false; //?
	}

	public void takeMoney(Player player, int circle, int amount) {
		if (amount < player.getBalance()) {
			player.setBalance(player.getBalance() - amount);
			Bet bet = new Bet(circle, player, amount);
			table.addBetToList(bet);
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
			table.clearBets();
			moveBlinds();
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
				spreadPotNotShow();
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
			if (RaisingIteration()) {
				spreadPotNotShow();
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
				spreadPotNotShow();
				continue;
			}
			spreadPotNotShow();
		}

	}

	private void spreadPotNotShow() {
		int pot = table.getBetsAmount();
		Player p;
		p.addToBalance(pot);
		System.out.println(String.format("Player %s has won pot %d in blind!",p.getName(),pot));
	}

	private boolean RaisingIteration() {
		return false;
	}
}
