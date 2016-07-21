package tests.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import AiStrategies.BasicStrategy;
import main.java.Model.AiPlayer;
import main.java.Model.Card;
import main.java.Model.Player;
import main.java.Model.Table;
import main.java.Model.TableSettings;
import main.java.Model.Enumerations.RaisingPolicy;

public class AITest {
	private AiPlayer player;
    private ArrayList<Card> tableCard = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private Table table;
    private Integer money = 1000;
    private Integer smallblind   = 20;

    @Before
    public void setUp(){
    	table = new Table(new TableSettings(2,money,smallblind,RaisingPolicy.NoLimits));
        player = new AiPlayer(table,new BasicStrategy());
        
    }
	@Test
	public void testIncome() {
		int income = 500;
		player.addToBalance(income);
		assertEquals(player.getBalance(),money+income);
	}
	@Test
	public void test() {
	
//		player.addToBalance(income);
//		assertEquals(player.getBalance(),money+income);
	}

}
