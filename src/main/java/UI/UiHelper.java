package main.java.UI;

import java.util.ArrayList;
import java.util.List;

import main.java.Model.Card;
import main.java.Model.Player;
import main.java.Model.Table;
import main.java.Utility.ConsoleHelper;

public class UiHelper {
	public static void updateTableInfo(Table table){
		
		Player player = table.getPlayer();
		Player[] computers = table.getAiPlayers();
		ConsoleHelper.getInstance().clearConsole();
		String format = "Player(you): $%d\n"
				+ "Hand:%s (%s)\n"
				+ "-\n"
				+ "Table:%d\n"
				+ "Small blind:%d ,Blind: %d\n";
		ArrayList<Object> args = new ArrayList<Object>();
						args.add(player.getBalance());
						args.add(cardsToString(player.getHand()));
						args.add(player.getCombination());
						args.add(cardsToString(table.getCards()));
						args.add(table.getBetsAmount());
						args.add(table.getSettings().getBlindAmount());
						args.add(table.getSettings().getBlindAmount() * 2);
		for(Player ai : computers)
		{
			format += "Ai opponent: %d, lastAction: %s\n";
			args.add(ai.getBalance());
			args.add(ai.getLastAction());
		}
		System.out.printf(format,args);
	}
	
	private static String cardsToString(Card[] cards){
		StringBuilder sb = new StringBuilder();
		
		for(Card c : cards){
			sb.append(String.format("%s %s",c.getValue(),c.getType()));
		}
		
		return sb.toString();
	}
	
	private static String cardsToString(List<Card> cards){
		return cardsToString((Card[]) cards.toArray());
	}
}
