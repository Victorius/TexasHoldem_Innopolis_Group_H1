package tests.java;

import main.java.Model.AiPlayer;
import main.java.Model.Card;
import main.java.Model.Enumerations.CardType;
import main.java.Model.Enumerations.CardValue;
import main.java.Model.Enumerations.CombinationType;
import main.java.Model.Player;
import main.java.Model.Table;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by Anton on 19/07/16.
 */
public class CombinationTest {
    private Player player;
    private ArrayList<Card> tableCard = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private Table table;

    @Before
    public void setUp(){
    	table = new Table(null);
        player = new AiPlayer(table);
    }

    @Test
    public void testTwoPairs() {
        cards.add(new Card(CardType.Clubs, CardValue.Seven));
        cards.add(new Card(CardType.Diamonds, CardValue.Five));
        tableCard.add(new Card(CardType.Diamonds, CardValue.Two));
        tableCard.add(new Card(CardType.Hearts, CardValue.Jack));
        tableCard.add(new Card(CardType.Clubs, CardValue.Jack));
        tableCard.add(new Card(CardType.Spades, CardValue.Two));
        tableCard.add(new Card(CardType.Hearts, CardValue.Nine));
        table.setCards(tableCard);
        player.setTable(table);
        player.setCards(cards);

        assertEquals(player.getCombination().getType(), CombinationType.TwoPairs);
    }

    @Test
    public void testOnePair(){
        cards.add(new Card(CardType.Clubs, CardValue.Six));
        cards.add(new Card(CardType.Clubs, CardValue.Three));
        tableCard.add(new Card(CardType.Diamonds, CardValue.Two));
        tableCard.add(new Card(CardType.Hearts, CardValue.Jack));
        tableCard.add(new Card(CardType.Clubs, CardValue.Jack));
        tableCard.add(new Card(CardType.Diamonds, CardValue.Ace));
        tableCard.add(new Card(CardType.Hearts, CardValue.Nine));

        table.setCards(tableCard);
        player.setTable(table);
        player.setCards(cards);
        assertEquals(player.getCombination().getType(), CombinationType.Pair);
    }

//    @Test
//    public void testHighCard(){
//
//       // assertEquals(player.getCombination().getType(), CombinationType.HighCard);
//    }

    @Test
    public void testThreeOfKind(){
        cards.add(new Card(CardType.Clubs, CardValue.Four));
        cards.add(new Card(CardType.Clubs, CardValue.Four));
        tableCard.add(new Card(CardType.Diamonds, CardValue.Four));
        tableCard.add(new Card(CardType.Hearts, CardValue.Jack));
        tableCard.add(new Card(CardType.Clubs, CardValue.Eight));
        tableCard.add(new Card(CardType.Diamonds, CardValue.Ace));
        tableCard.add(new Card(CardType.Hearts, CardValue.Nine));

        table.setCards(tableCard);
        player.setTable(table);
        player.setCards(cards);
        assertEquals(player.getCombination().getType(), CombinationType.ThreeOfKind);
    }

    @Test
    public void testStraight(){
        cards.add(new Card(CardType.Clubs, CardValue.Two));
        cards.add(new Card(CardType.Clubs, CardValue.Three));
        tableCard.add(new Card(CardType.Diamonds, CardValue.Four));
        tableCard.add(new Card(CardType.Hearts, CardValue.Five));
        tableCard.add(new Card(CardType.Clubs, CardValue.Six));
        tableCard.add(new Card(CardType.Diamonds, CardValue.Ace));
        tableCard.add(new Card(CardType.Hearts, CardValue.Nine));

        table.setCards(tableCard);
        player.setTable(table);
        player.setCards(cards);

        assertEquals(player.getCombination().getType(), CombinationType.Straight);
    }

    @Test
    public void testFlash(){
        cards.add(new Card(CardType.Clubs, CardValue.Six));
        cards.add(new Card(CardType.Clubs, CardValue.Three));
        tableCard.add(new Card(CardType.Clubs, CardValue.Two));
        tableCard.add(new Card(CardType.Hearts, CardValue.King));
        tableCard.add(new Card(CardType.Clubs, CardValue.Jack));
        tableCard.add(new Card(CardType.Clubs, CardValue.Ace));
        tableCard.add(new Card(CardType.Hearts, CardValue.Nine));

        table.setCards(tableCard);
        player.setTable(table);
        player.setCards(cards);

        assertEquals(player.getCombination().getType(), CombinationType.Flash);
    }

    @Test
    public void testFullHouse(){
        cards.add(new Card(CardType.Diamonds, CardValue.King));
        cards.add(new Card(CardType.Clubs, CardValue.King));
        tableCard.add(new Card(CardType.Diamonds, CardValue.King));
        tableCard.add(new Card(CardType.Hearts, CardValue.Jack));
        tableCard.add(new Card(CardType.Clubs, CardValue.Jack));
        tableCard.add(new Card(CardType.Diamonds, CardValue.Ace));
        tableCard.add(new Card(CardType.Hearts, CardValue.Nine));

        table.setCards(tableCard);
        player.setTable(table);
        player.setCards(cards);

        assertEquals(player.getCombination().getType(), CombinationType.FullHouse);
    }

    @Test
    public void testQuads(){
        cards.add(new Card(CardType.Diamonds, CardValue.King));
        cards.add(new Card(CardType.Clubs, CardValue.King));
        tableCard.add(new Card(CardType.Diamonds, CardValue.King));
        tableCard.add(new Card(CardType.Hearts, CardValue.King));
        tableCard.add(new Card(CardType.Clubs, CardValue.Jack));
        tableCard.add(new Card(CardType.Diamonds, CardValue.Ace));
        tableCard.add(new Card(CardType.Hearts, CardValue.Nine));

        table.setCards(tableCard);
        player.setTable(table);
        player.setCards(cards);

        assertEquals(player.getCombination().getType(), CombinationType.Quads);
    }

    @Test
    public void testStraightFlash(){
        cards.add(new Card(CardType.Clubs, CardValue.Two));
        cards.add(new Card(CardType.Clubs, CardValue.Three));
        tableCard.add(new Card(CardType.Clubs, CardValue.Four));
        tableCard.add(new Card(CardType.Clubs, CardValue.Five));
        tableCard.add(new Card(CardType.Clubs, CardValue.Six));
        tableCard.add(new Card(CardType.Diamonds, CardValue.Ace));
        tableCard.add(new Card(CardType.Hearts, CardValue.Nine));

        table.setCards(tableCard);
        player.setTable(table);
        player.setCards(cards);

        assertEquals(player.getCombination().getType(), CombinationType.StraightFlash);
    }




}
