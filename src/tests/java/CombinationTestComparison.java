package tests.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mftech.Model.AiPlayer;
import org.mftech.Model.Card;
import org.mftech.Model.Combination;
import org.mftech.Model.Player;
import org.mftech.Model.Table;
import org.mftech.Model.TableSettings;
import org.mftech.Model.Enumerations.CardType;
import org.mftech.Model.Enumerations.CardValue;
import org.mftech.Model.Enumerations.CombinationType;
import org.mftech.Model.Enumerations.RaisingPolicy;

import AiStrategies.BasicStrategy;

public class CombinationTestComparison {
	private Player player;
    private ArrayList<Card> tableCard = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private Table table;

    @Before
    public void setUp(){
    	table = new Table(new TableSettings(2,1000,20,RaisingPolicy.NoLimits));
        player = new AiPlayer(table,new BasicStrategy());
        
    }
	@Test
	public void testPair() {
		ArrayList<Card> array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.King));
		array.add(new Card(CardType.Hearts,CardValue.King));
		array.add(new Card(CardType.Diamonds,CardValue.Queen));
		array.add(new Card(CardType.Spades,CardValue.Seven));
		array.add(new Card(CardType.Diamonds,CardValue.Four));		
		Combination a = new Combination(CombinationType.TwoPairs,array);
		array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.King));
		array.add(new Card(CardType.Hearts,CardValue.King));
		array.add(new Card(CardType.Diamonds,CardValue.Jack));
		array.add(new Card(CardType.Spades,CardValue.Seven));
		array.add(new Card(CardType.Diamonds,CardValue.Four));	
		Combination b = new Combination(CombinationType.Pair,array);
		
		assertEquals(a.compareTo(b), 1);
	}
	@Test
	public void testTwoPair() {
		ArrayList<Card> array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.King));
		array.add(new Card(CardType.Hearts,CardValue.King));
		array.add(new Card(CardType.Diamonds,CardValue.Queen));
		array.add(new Card(CardType.Spades,CardValue.Seven));
		array.add(new Card(CardType.Diamonds,CardValue.Seven));		
		Combination a = new Combination(CombinationType.TwoPairs,array);
		array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.King));
		array.add(new Card(CardType.Hearts,CardValue.King));
		array.add(new Card(CardType.Diamonds,CardValue.Jack));
		array.add(new Card(CardType.Spades,CardValue.Seven));
		array.add(new Card(CardType.Diamonds,CardValue.Seven));	
		Combination b = new Combination(CombinationType.TwoPairs,array);
		
		assertEquals(a.compareTo(b), 1);
	}
	@Test
	public void testThreeOfKind() {
		ArrayList<Card> array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.King));
		array.add(new Card(CardType.Hearts,CardValue.King));
		array.add(new Card(CardType.Clubs,CardValue.King));
		array.add(new Card(CardType.Spades,CardValue.Five));
		array.add(new Card(CardType.Diamonds,CardValue.Seven));		
		Combination a = new Combination(CombinationType.TwoPairs,array);
		array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.King));
		array.add(new Card(CardType.Hearts,CardValue.King));
		array.add(new Card(CardType.Diamonds,CardValue.Jack));
		array.add(new Card(CardType.Spades,CardValue.Five));
		array.add(new Card(CardType.Spades,CardValue.King));	
		Combination b = new Combination(CombinationType.TwoPairs,array);
		
		assertEquals(a.compareTo(b), -1);
	}
	@Test
	public void testFullHouseAndThreeOfKind() {
		ArrayList<Card> array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.King));
		array.add(new Card(CardType.Hearts,CardValue.King));
		array.add(new Card(CardType.Clubs,CardValue.King));
		array.add(new Card(CardType.Spades,CardValue.Five));
		array.add(new Card(CardType.Diamonds,CardValue.Five));		
		Combination a = new Combination(CombinationType.FullHouse,array);
		array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.King));
		array.add(new Card(CardType.Hearts,CardValue.King));
		array.add(new Card(CardType.Diamonds,CardValue.Jack));
		array.add(new Card(CardType.Spades,CardValue.Five));
		array.add(new Card(CardType.Spades,CardValue.King));	
		Combination b = new Combination(CombinationType.TwoPairs,array);
		
		assertEquals(a.compareTo(b), 1);
	}
	@Test
	public void testStraightFlash() {
		ArrayList<Card> array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.Ace));
		array.add(new Card(CardType.Diamonds,CardValue.King));
		array.add(new Card(CardType.Diamonds,CardValue.Queen));
		array.add(new Card(CardType.Diamonds,CardValue.Jack));
		array.add(new Card(CardType.Diamonds,CardValue.Ten));		
		Combination a = new Combination(CombinationType.StraightFlash,array);
		array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.King));
		array.add(new Card(CardType.Diamonds,CardValue.Queen));
		array.add(new Card(CardType.Diamonds,CardValue.Jack));
		array.add(new Card(CardType.Diamonds,CardValue.Ten));
		array.add(new Card(CardType.Diamonds,CardValue.Nine));	
		Combination b = new Combination(CombinationType.StraightFlash,array);
		
		assertEquals(a.compareTo(b), 1);
	}
	@Test
	public void testLowStraight() {
		ArrayList<Card> array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.Ace));
		array.add(new Card(CardType.Diamonds,CardValue.Five));
		array.add(new Card(CardType.Diamonds,CardValue.Four));
		array.add(new Card(CardType.Diamonds,CardValue.Three));
		array.add(new Card(CardType.Diamonds,CardValue.Two));		
		Combination a = new Combination(CombinationType.StraightFlash,array);
		array = new ArrayList<Card>();
		array.add(new Card(CardType.Diamonds,CardValue.King));
		array.add(new Card(CardType.Diamonds,CardValue.Queen));
		array.add(new Card(CardType.Clubs,CardValue.Jack));
		array.add(new Card(CardType.Diamonds,CardValue.Ten));
		array.add(new Card(CardType.Diamonds,CardValue.Nine));	
		Combination b = new Combination(CombinationType.Straight,array);
		
		assertEquals(a.compareTo(b), 1);
	}

}
