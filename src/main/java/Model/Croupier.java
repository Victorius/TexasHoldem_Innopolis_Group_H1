package main.java.Model;

import java.util.ListIterator;

import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState;
import main.java.Model.Enumerations.ActionType;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import main.java.Model.Enumerations.CombinationType;
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
		addPlayersInGame();
		setInitialBlinds();
	}

	// setting up blinds as game starts
	public void setInitialBlinds() {
		table.getPlayers().get(smallBlind).setSmallBlind(true);
		takeMoney(table.getPlayers().get(smallBlind), 0, table.getSettings().getBlindAmount());
		table.getPlayers().get(bigBlind).setBigBlind(true);
		takeMoney(table.getPlayers().get(bigBlind), 0, table.getSettings().getBlindAmount() * 2);
		table.getPlayers().get(2).setCurrent(true);
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
		circle = 0;
		ListIterator<Player> listIterator = table.getPlayers().listIterator();
		boolean running = true;
		boolean currentStill = false;
		while (running) {
			Player player;
			if (listIterator.hasNext()) {
				player = listIterator.next();
			} else {
				listIterator = table.getPlayers().listIterator();
				player = listIterator.next();
			}
			if (!player.isCurrent() && !currentStill) {
				while (!player.isCurrent()) {
					if (table.getPlayers().getLast().equals(player)) {
						listIterator = table.getPlayers().listIterator();
					}
					player = listIterator.next();
				}
			}
			if (player.isCurrent() && player.getLastAction().getType() == ActionType.Raise) {
				return null;
			}
			if (player.isIngame()) {
				if (table.getPlayers().getLast().equals(player)) {
					listIterator = table.getPlayers().listIterator();
				}
				switch (player.getPlayerAction().getType()) {
				case CallCheck:
					takeMoney(player, circle, getHighestBet().getAmount());
					currentStill = true;
					break;
				case Fold:
					player.setIngame(false);
					currentStill = true;
					if (isWon())
						return getWinner();
					break;
				case Raise:
					deselectCurrentPlayers();
					player.setCurrent(true);
					takeMoney(player, circle, player.getLastAction().getAmount() + getHighestBet().getAmount());
					currentStill = true;
					circle++;
					break;
				default:
					break;
				}
			}
		}
		return null;
	}

	private Bet getHighestBet() {
		Bet max = table.getBets().get(0);
		for (Bet b : table.getBets()) {
			if (b.getCircle() == circle && b.getAmount() > max.getAmount()) {
				max = b;
			}
		}
		return max;
	}

	public void takeMoney(Player player, int circle, int amount) {
		if (amount < player.getBalance()) {
			player.setBalance(player.getBalance() - amount);
			Bet bet = new Bet(circle, player, amount);
			table.addBetToList(bet);
			player.setLastBet(bet);
		} else {
			Bet bet = new Bet(circle, player, player.getBalance());
			table.addBetToList(bet);
			player.setLastBet(bet);
			player.setCircleAllin(circle);
		}
	}

	private Player getWinner() {
		for (Player player : table.getPlayers()) {
			if (player.isIngame()) {
				addPlayersInGame();
				return player;
			}
		}
		return null;
	}

	private boolean isWon() {
		int counter = 0;
		for (Player player : table.getPlayers()) {
			if (player.isIngame()) {
				counter++;
			}
		}
		if (counter == 1)
			return true;
		else
			return false;
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
			Player pl = null;
			pl = getActions();
			if (pl != null) {
				spreadPotNotShow(pl);
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
			pl = getActions();
			if (pl != null) {
				spreadPotNotShow(pl);
				continue;
			} // flop
			table.addCards(new ArrayList<Card>() {
				{
					add(deck.remove(0));
				}
			});
			UiHelper.updateTableInfo(table);
			// one more iteration
			pl = getActions();
			if (pl != null) {
				spreadPotNotShow(pl);
				continue;
			} // shit its too late now to remember last phase of the game
			table.addCards(new ArrayList<Card>() {
				{
					add(deck.remove(0));
				}
			});
			UiHelper.updateTableInfo(table);
			// last one
			pl = getActions();
			if (pl != null) {
				spreadPotNotShow(pl);
				continue;
			} 
			if(spreadPotAndShow()){
				System.out.println("The game is over");
			}
			moveBlinds();
		}
	}
	
	private void removeLoosers(){
		for(Player p : table.getPlayers()){
			if(p.getBalance() <= 0){
				table.removePlayer(p);
			}
		}
	}

	private boolean spreadPotAndShow() {
		List<Bet> bets = table.getBets();
		int lastCircle = bets.get(bets.size() - 1).getCircle();
		while (lastCircle > -1) {
			List<Bet> thisCircleBets = new ArrayList<Bet>();
			int thisPot = 0;
			for (Bet b : bets) {
				if (b.getCircle() == lastCircle) {
					thisCircleBets.add(b);
					thisPot += b.getAmount();
				}
			}
			List<Player> thisCirclePlayers = getTopPlayers(lastCircle);
			for (Player p : thisCirclePlayers) {
				p.addToBalance(thisPot / thisCirclePlayers.size());
				if (p.getCircleAllin() == lastCircle) {
					p.setIngame(false);
				}
				System.out.printf("Player %s win %d with combination %s", p.getName(),
						thisPot / thisCirclePlayers.size(), p.getCombination());
			}
			lastCircle--;
		}
		
		int c =0;
		Player winner = null;
		for(Player p :table.getPlayers()){
			if(p.getBalance() > 0){
				c++;
				winner = p;
			}
		}
		
		if(c==1){
			System.out.printf(String.format("Player %s has won the table!!!!",winner.getName()));
			return true;
		}else{
			removeLoosers();
			return false;
			
		}
	}

	public List<Player> getTopPlayers(int circle) {
		List<Player> result = new ArrayList<Player>();
		Combination top = new Combination(CombinationType.NoCombination, new ArrayList<Card>());
		for (Player p : table.getPlayers()) {
			if (p.isIngame() && p.getCombination().compareTo(top) == 1
					&& (p.getCircleAllin() == -1 || p.getCircleAllin() == circle)) {
				top = p.getCombination();
			}
		}
		for (Player p : table.getPlayers()) {
			if (p.isIngame() && p.getCombination().compareTo(top) == 0) {
				result.add(p);
			}
		}
		return result;
	}

	private void spreadPotNotShow(Player p) {
		int pot = table.getBetsAmount();
		p.addToBalance(pot);
		removeLoosers();
		System.out.println(String.format("Player %s has won pot %d in blind!",p.getName(),pot));
	}

}
