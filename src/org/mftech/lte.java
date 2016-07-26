package org.mftech;

import java.util.ArrayList;

import org.mftech.Model.Card;
import org.mftech.Model.Combination;
import org.mftech.Model.Enumerations.CardType;
import org.mftech.Model.Enumerations.CardValue;
import org.mftech.Model.Enumerations.CombinationType;

public class lte {

	public static void main(String[] args) {
		ArrayList<Card> a = new ArrayList<Card>();
		a.add(new Card(CardType.Diamonds,CardValue.Ace));
		a.add(new Card(CardType.Clubs,CardValue.Ace));
		a.add(new Card(CardType.Diamonds,CardValue.Jack));
		a.add(new Card(CardType.Clubs,CardValue.Jack));
		a.add(new Card(CardType.Diamonds,CardValue.King));
		Combination s = new Combination(CombinationType.TwoPairs,a);
		a = new ArrayList<Card>();
		a.add(new Card(CardType.Diamonds,CardValue.Ace));
		a.add(new Card(CardType.Clubs,CardValue.Ace));
		a.add(new Card(CardType.Diamonds,CardValue.Jack));
		a.add(new Card(CardType.Clubs,CardValue.Jack));
		a.add(new Card(CardType.Diamonds,CardValue.Seven));
		Combination s2 = new Combination(CombinationType.TwoPairs,a);
		System.out.println(s.compareTo(s2));
	}

}
