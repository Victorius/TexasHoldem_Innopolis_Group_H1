package tests.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mftech.Model.AiPlayer;
import org.mftech.Model.Bet;
import org.mftech.Model.Card;
import org.mftech.Model.Table;
import org.mftech.Model.TableSettings;
import org.mftech.Model.Enumerations.ActionType;
import org.mftech.Model.Enumerations.CardType;
import org.mftech.Model.Enumerations.CardValue;
import org.mftech.Model.Enumerations.RaisingPolicy;

import AiStrategies.BluffStrategy;

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
		card2ai.add(new Card(CardType.Clubs, CardValue.Two));
		a.get(2).setCards(card2ai);
	    tableCard.add(new Card(CardType.Diamonds, CardValue.Three));
	    tableCard.add(new Card(CardType.Hearts, CardValue.Five));
	    tableCard.add(new Card(CardType.Clubs, CardValue.Jack));
	    tableCard.add(new Card(CardType.Diamonds, CardValue.Queen));
	    tableCard.add(new Card(CardType.Hearts, CardValue.Nine));
	    
	}

	@Test
	public void testRandomAnswerCallCheckRandom() {
		int circle = 0;
		int smallBlind=40;
		Bet c1a1 = new Bet(circle,a.get(0),smallBlind);
		Bet c1a2 = new Bet(circle,a.get(1),smallBlind*2);
		a.get(0).setLastBet(c1a1);
		a.get(1).setLastBet(c1a2);
		table.addBetToList(c1a1);
		table.addBetToList(c1a2);
		
		assertEquals(true, a.get(2).getPlayerAction().getType()==ActionType.CallCheck ||  a.get(2).getPlayerAction().getType()==ActionType.Raise);
	}
	
	@Test
	public void testAllInOrDie() {
		int circle = 0;
		int smallBlind=40;
		Bet c1a1 = new Bet(circle,a.get(0),smallBlind);
		Bet c1a2 = new Bet(circle,a.get(1),smallBlind*2);
		Bet c1a3 = new Bet(circle,a.get(2),smallBlind*2);
		a.get(0).setLastBet(c1a1);
		a.get(1).setLastBet(c1a2);
		a.get(2).setLastBet(c1a3);
		table.addBetToList(c1a1);
		table.addBetToList(c1a2);
		table.addBetToList(c1a3);
		c1a1 = new Bet(circle+1,a.get(0),smallBlind);
		a.get(0).setLastBet(c1a1);
		table.addBetToList(c1a1);
		
		assertEquals(true, a.get(1).getPlayerAction().getType()==ActionType.Fold || a.get(1).getPlayerAction().getType()==ActionType.Raise);
	}

}