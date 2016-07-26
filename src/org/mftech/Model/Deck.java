package org.mftech.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.mftech.Model.Enumerations.CardType;
import org.mftech.Model.Enumerations.CardValue;

public class Deck {
	public static ArrayList<Card> getNewRandomDeck(){
		ArrayList<Card> deck = new ArrayList<>();
		deck.add(new Card(CardType.Clubs, CardValue.Two));
		deck.add(new Card(CardType.Clubs, CardValue.Three));
		deck.add(new Card(CardType.Clubs, CardValue.Four));
		deck.add(new Card(CardType.Clubs, CardValue.Five));
		deck.add(new Card(CardType.Clubs, CardValue.Six));
		deck.add(new Card(CardType.Clubs, CardValue.Seven));
		deck.add(new Card(CardType.Clubs, CardValue.Eight));
		deck.add(new Card(CardType.Clubs, CardValue.Nine));
		deck.add(new Card(CardType.Clubs, CardValue.Ten));
		deck.add(new Card(CardType.Clubs, CardValue.Jack));
		deck.add(new Card(CardType.Clubs, CardValue.Queen));
		deck.add(new Card(CardType.Clubs, CardValue.King));
		deck.add(new Card(CardType.Clubs, CardValue.Ace));
		deck.add(new Card(CardType.Diamonds, CardValue.Two));
		deck.add(new Card(CardType.Diamonds, CardValue.Three));
		deck.add(new Card(CardType.Diamonds, CardValue.Four));
		deck.add(new Card(CardType.Diamonds, CardValue.Five));
		deck.add(new Card(CardType.Diamonds, CardValue.Six));
		deck.add(new Card(CardType.Diamonds, CardValue.Seven));
		deck.add(new Card(CardType.Diamonds, CardValue.Eight));
		deck.add(new Card(CardType.Diamonds, CardValue.Nine));
		deck.add(new Card(CardType.Diamonds, CardValue.Ten));
		deck.add(new Card(CardType.Diamonds, CardValue.Jack));
		deck.add(new Card(CardType.Diamonds, CardValue.Queen));
		deck.add(new Card(CardType.Diamonds, CardValue.King));
		deck.add(new Card(CardType.Diamonds, CardValue.Ace));
		deck.add(new Card(CardType.Hearts, CardValue.Two));
		deck.add(new Card(CardType.Hearts, CardValue.Three));
		deck.add(new Card(CardType.Hearts, CardValue.Four));
		deck.add(new Card(CardType.Hearts, CardValue.Five));
		deck.add(new Card(CardType.Hearts, CardValue.Six));
		deck.add(new Card(CardType.Hearts, CardValue.Seven));
		deck.add(new Card(CardType.Hearts, CardValue.Eight));
		deck.add(new Card(CardType.Hearts, CardValue.Nine));
		deck.add(new Card(CardType.Hearts, CardValue.Ten));
		deck.add(new Card(CardType.Hearts, CardValue.Jack));
		deck.add(new Card(CardType.Hearts, CardValue.Queen));
		deck.add(new Card(CardType.Hearts, CardValue.King));
		deck.add(new Card(CardType.Hearts, CardValue.Ace));
		deck.add(new Card(CardType.Spades, CardValue.Two));
		deck.add(new Card(CardType.Spades, CardValue.Three));
		deck.add(new Card(CardType.Spades, CardValue.Four));
		deck.add(new Card(CardType.Spades, CardValue.Five));
		deck.add(new Card(CardType.Spades, CardValue.Six));
		deck.add(new Card(CardType.Spades, CardValue.Seven));
		deck.add(new Card(CardType.Spades, CardValue.Eight));
		deck.add(new Card(CardType.Spades, CardValue.Nine));
		deck.add(new Card(CardType.Spades, CardValue.Ten));
		deck.add(new Card(CardType.Spades, CardValue.Jack));
		deck.add(new Card(CardType.Spades, CardValue.Queen));
		deck.add(new Card(CardType.Spades, CardValue.King));
		deck.add(new Card(CardType.Spades, CardValue.Ace));//THE ACE OF SPADES THE ACE OF SPADES
		Collections.shuffle(deck, new Random(System.nanoTime()));
			
		return deck;
	}
}
