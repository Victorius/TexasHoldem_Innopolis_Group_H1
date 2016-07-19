import java.util.ArrayList;

import Model.Card;
import Model.Combination;
import Model.Player;
import Model.Table;
import Model.Enumerations.CardType;
import Model.Enumerations.CardValue;

public class test4test {

	public static void main(String[] args) {
		Player pla = new Player("Rak", 100);
		ArrayList<Card> tableCard = new ArrayList<Card>();
		ArrayList<Card> card = new ArrayList<Card>();
		card.add(new Card(CardType.Clubs, CardValue.Seven));
		card.add(new Card(CardType.Diamonds, CardValue.Five));
		tableCard.add(new Card(CardType.Diamonds, CardValue.Two));
		tableCard.add(new Card(CardType.Hearts, CardValue.Jack));
		tableCard.add(new Card(CardType.Clubs, CardValue.Jack));
		tableCard.add(new Card(CardType.Spades, CardValue.Two));
		tableCard.add(new Card(CardType.Hearts, CardValue.Ace));
		Table table = new Table(null);
		table.setCards(tableCard);
		pla.setTable(table);
		pla.setCards(card);
		Combination twopair = pla.getCombination();
		for(Card i:twopair.getCards())
		System.out.println(i.getType()+" "+i.getValue());

	}

}
