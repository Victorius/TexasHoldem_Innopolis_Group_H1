package org.mftech.UI;

import java.util.List;

import org.mftech.Model.Card;
import org.mftech.Model.Player;
import org.mftech.Model.Table;

public class UiHelper {
	public static void updateTableInfo(Table table){
		
		Player player = table.getPlayer();
		List<Player> computers = table.getAiPlayers();
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
		System.out.printf("Player(you): $%d\n"
				+ "Hand:%s (%s)\n"
				+ "-\n"
				+ "Table:%s ,pot: %d\n"
				+ "Small blind:%d ,Blind: %d\n",
				player.getBalance(),
				cardsToString(player.getHand()),player.getCombination().toString(),
				cardsToString(table.getCards()),table.getBetsAmount(),
				table.getSettings().getBlindAmount(),table.getSettings().getBlindAmount() * 2);
		for(Player ai : computers)
		{
			System.out.printf("%s: %d, lastAction: %s\n",ai.getName(), ai.getBalance(),ai.getLastAction().toString());
		}			
	}
	
	private static String cardsToString(List<Card> cards){
		if(cards.size() == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		
		for(Card c : cards){
			sb.append(c+" ");
		}
		
		return sb.toString();
	}

}
