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
		card.add(new Card(CardType.Hearts, CardValue.Five));
		tableCard.add(new Card(CardType.Hearts, CardValue.Four));
		tableCard.add(new Card(CardType.Hearts, CardValue.Seven));
		tableCard.add(new Card(CardType.Hearts, CardValue.Six));
		tableCard.add(new Card(CardType.Hearts, CardValue.Eight));
		tableCard.add(new Card(CardType.Hearts, CardValue.Nine));
		Table table = new Table(null);
		table.setCards(tableCard);
		pla.setTable(table);
		pla.setCards(card);
		Combination twopair = pla.getCombination();
		System.out.println(twopair.getType());
		for(Card i:twopair.getCards())
			System.out.println(i.getType()+" "+i.getValue());

	}

}
