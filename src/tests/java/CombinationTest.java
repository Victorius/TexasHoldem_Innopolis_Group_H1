package tests.java;

import org.junit.Before;
import org.junit.Test;
import org.mftech.AiStrategies.BasicStrategy;
import org.mftech.Model.AiPlayer;
import org.mftech.Model.Card;
import org.mftech.Model.Player;
import org.mftech.Model.Table;
import org.mftech.Model.TableSettings;
import org.mftech.Model.Enumerations.CardType;
import org.mftech.Model.Enumerations.CardValue;
import org.mftech.Model.Enumerations.CombinationType;
import org.mftech.Model.Enumerations.RaisingPolicy;

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
    	table = new Table(new TableSettings(2,1000,20,RaisingPolicy.NoLimits));
        player = new AiPlayer(table,new BasicStrategy());
        
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
        table.addCards(tableCard);
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

        table.addCards(tableCard);
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

        table.addCards(tableCard);
        player.setTable(table);
        player.setCards(cards);
        assertEquals(player.getCombination().getType(), CombinationType.ThreeOfKind);
    }

    @Test
    public void testStraight(){
        cards.add(new Card(CardType.Spades, CardValue.King));
        cards.add(new Card(CardType.Spades, CardValue.Queen));
        tableCard.add(new Card(CardType.Diamonds, CardValue.King));
        tableCard.add(new Card(CardType.Diamonds, CardValue.Ten));
        tableCard.add(new Card(CardType.Spades, CardValue.Nine));
        tableCard.add(new Card(CardType.Hearts, CardValue.Seven));
        tableCard.add(new Card(CardType.Clubs, CardValue.Jack));

        table.addCards(tableCard);
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

        table.addCards(tableCard);
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

        table.addCards(tableCard);
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

        table.addCards(tableCard);
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

        table.addCards(tableCard);
        player.setTable(table);
        player.setCards(cards);

        assertEquals(player.getCombination().getType(), CombinationType.StraightFlash);
    }




}
