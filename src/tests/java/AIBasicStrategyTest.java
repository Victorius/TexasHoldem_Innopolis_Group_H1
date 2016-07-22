package tests.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import AiStrategies.BasicStrategy;
import main.java.Model.AiPlayer;
import main.java.Model.Bet;
import main.java.Model.Card;
import main.java.Model.Player;
import main.java.Model.Table;
import main.java.Model.TableSettings;
import main.java.Model.Enumerations.ActionType;
import main.java.Model.Enumerations.CardType;
import main.java.Model.Enumerations.CardValue;
import main.java.Model.Enumerations.RaisingPolicy;

public class AIBasicStrategyTest {
	private AiPlayer player;
	private AiPlayer ai;
	private AiPlayer ai2;
    private ArrayList<Card> tableCard = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private Table table;
    private Integer money = 1000;
    private Integer smallblind   = 20;

    @Before
    public void setUp(){
    	table = new Table(new TableSettings(3,money,smallblind,RaisingPolicy.NoLimits));
        player = new AiPlayer(table,new BasicStrategy());
        int circle = 0;
		ai = new AiPlayer(table,new BasicStrategy());
		ai2 = new AiPlayer(table,new BasicStrategy());
		ai.setLastBet(new Bet(circle,ai,smallblind*2));
		table.addBetToList(ai.getLastBet());
		ai2.setLastBet(new Bet(circle,ai,smallblind*2));
		table.addBetToList(ai2.getLastBet());
		player.setLastBet(new Bet(circle,ai,smallblind*2));
		table.addBetToList(player.getLastBet());
		ArrayList<Card> card2ai = new ArrayList<Card>();
		card2ai.add(new Card(CardType.Clubs, CardValue.Ace));
		card2ai.add(new Card(CardType.Hearts, CardValue.Ace));
		ai.setCards(card2ai);
		card2ai = new ArrayList<Card>();
		card2ai.add(new Card(CardType.Hearts, CardValue.King));
		card2ai.add(new Card(CardType.Hearts, CardValue.Three));
		ai2.setCards(card2ai);
		cards.add(new Card(CardType.Clubs, CardValue.Six));
	    cards.add(new Card(CardType.Clubs, CardValue.Three));
	    tableCard.add(new Card(CardType.Diamonds, CardValue.Three));
	    tableCard.add(new Card(CardType.Hearts, CardValue.Five));
	    tableCard.add(new Card(CardType.Clubs, CardValue.Jack));
	    tableCard.add(new Card(CardType.Diamonds, CardValue.Queen));
	    tableCard.add(new Card(CardType.Hearts, CardValue.Nine));

	    table.addCards(tableCard);
        player.setCards(cards);
    }
	@Test
	public void testIncome() {
		int income = 500;
		player.addToBalance(income);
		assertEquals(player.getBalance(),money+income);
	}
	@Test
	public void testRaise() {		
		
		assertEquals(ActionType.Raise,ai.getPlayerAction().getType());
		assertEquals(ActionType.Raise,ai2.getPlayerAction().getType());
		assertEquals(ActionType.Raise,player.getPlayerAction().getType());
	}
	
	@Test
	public void testFold(){
		ai.setBalance(0);
		table.addBetToList(new Bet(1,player,100));
		assertEquals(ActionType.Fold,ai.getPlayerAction().getType());
		assertEquals(ActionType.Raise,ai2.getPlayerAction().getType());
		assertEquals(ActionType.Raise,player.getPlayerAction().getType());
	}
	@Test
	public void testCallCheck(){
		ai.setBalance(0);
		ArrayList<Card> card2ai = new ArrayList<Card>();
		card2ai.add(new Card(CardType.Clubs, CardValue.Two));
		card2ai.add(new Card(CardType.Hearts, CardValue.Ace));
		ai.setCards(card2ai);
		assertEquals(ActionType.CallCheck,ai.getPlayerAction().getType());
		assertEquals(ActionType.Raise,ai2.getPlayerAction().getType());
		assertEquals(ActionType.Raise,player.getPlayerAction().getType());

	}
	
	@Test
	public void testFoldWithMoney(){
		ai.setBalance(100);		
		player.setLastBet(new Bet(1,player,100));
		ai.setLastBet(new Bet(1,ai,40));
		ArrayList<Card> card2ai = new ArrayList<Card>();
		card2ai.add(new Card(CardType.Clubs, CardValue.Two));
		card2ai.add(new Card(CardType.Hearts, CardValue.Ace));
		ai.setCards(card2ai);
		table.addBetToList(player.getLastBet());
		table.addBetToList(ai.getLastBet());
		assertEquals(ActionType.Fold,ai.getPlayerAction().getType());
		assertEquals(ActionType.Raise,ai2.getPlayerAction().getType());
		assertEquals(ActionType.Raise,player.getPlayerAction().getType());
	}
	

}
