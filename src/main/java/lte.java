package main.java;

import java.util.ArrayList;

import main.java.Model.Card;
import main.java.Model.Combination;
import main.java.Model.Enumerations.CardType;
import main.java.Model.Enumerations.CardValue;
import main.java.Model.Enumerations.CombinationType;

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
