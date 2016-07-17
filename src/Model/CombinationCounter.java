package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.Enumerations.Combination;

public abstract class CombinationCounter {
	protected List<Card> cards = new ArrayList<Card>();
	protected Table aTable;
	protected Combination combination;
	
	public Combination getCombination(){
		List<Card> allCards = new ArrayList<Card>();
		allCards.addAll(this.aTable.getCards());
		allCards.addAll(this.cards);
		Collections.sort(allCards);
		
		return Combination.HighCard;
	}
	
}
