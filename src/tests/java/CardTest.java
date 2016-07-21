package tests.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.Model.Card;
import main.java.Model.Enumerations.CardType;
import main.java.Model.Enumerations.CardValue;

public class CardTest {
	
		
	@Test
	public void testGreater() {
		Card a = new Card(CardType.Diamonds,CardValue.Ace);
		Card b = new Card(CardType.Diamonds,CardValue.King);
		assertEquals(a.compareTo(b),1);
	}
	@Test
	public void testLess() {
		Card a = new Card(CardType.Diamonds,CardValue.Queen);
		Card b = new Card(CardType.Diamonds,CardValue.King);
		assertEquals(a.compareTo(b),-1);
	}
	@Test
	public void testEquals() {
		Card a = new Card(CardType.Hearts,CardValue.King);
		Card b = new Card(CardType.Diamonds,CardValue.King);
		assertEquals(a.compareTo(b),0);
	}

}
