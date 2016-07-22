package tests.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import AiStrategies.BluffStrategy;
import main.java.Model.AiPlayer;
import main.java.Model.Card;
import main.java.Model.Table;
import main.java.Model.TableSettings;
import main.java.Model.Enumerations.CardType;
import main.java.Model.Enumerations.CardValue;
import main.java.Model.Enumerations.RaisingPolicy;

public class AIBluffStrategyTest {
	ArrayList<AiPlayer> a;
	Table table;
	@Before
	public void setUp() throws Exception {
		table = new Table(new TableSettings(3,1000,40,RaisingPolicy.NoLimits));
		a = new ArrayList<AiPlayer>();
		a.add(new AiPlayer(table,new BluffStrategy()));
		a.add(new AiPlayer(table,new BluffStrategy()));
		a.add(new AiPlayer(table,new BluffStrategy()));
		ArrayList<Card> tableCard = new ArrayList<Card>();
		ArrayList<Card> card2ai = new ArrayList<Card>();
		card2ai.add(new Card(CardType.Clubs, CardValue.Ace));
		card2ai.add(new Card(CardType.Hearts, CardValue.Ace));
		a.get(0).setCards(card2ai);
		card2ai = new ArrayList<Card>();
		card2ai.add(new Card(CardType.Hearts, CardValue.King));
		card2ai.add(new Card(CardType.Hearts, CardValue.Three));
		a.get(1).setCards(card2ai);
		card2ai = new ArrayList<Card>();
		card2ai.add(new Card(CardType.Clubs, CardValue.Six));
		card2ai.add(new Card(CardType.Clubs, CardValue.Three));
//		a.get(2).set
	    tableCard.add(new Card(CardType.Diamonds, CardValue.Three));
	    tableCard.add(new Card(CardType.Hearts, CardValue.Five));
	    tableCard.add(new Card(CardType.Clubs, CardValue.Jack));
	    tableCard.add(new Card(CardType.Diamonds, CardValue.Queen));
	    tableCard.add(new Card(CardType.Hearts, CardValue.Nine));
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
