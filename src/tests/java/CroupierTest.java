package tests.java;

import main.java.Model.AiPlayer;
import main.java.Model.Croupier;
import main.java.Model.Enumerations.RaisingPolicy;
import main.java.Model.Player;
import main.java.Model.Table;
import main.java.Model.TableSettings;
import org.junit.Before;
import org.junit.Test;

import AiStrategies.BasicStrategy;

import static org.junit.Assert.*;
import java.util.LinkedList;


/**
 * Created by antant on 19/07/16.
 */
public class CroupierTest {
    private Table table;
    private Croupier croupier;

    @Before
    public void setUp(){
        TableSettings settings = new TableSettings(4,5000,50, RaisingPolicy.NoLimits);
        table = new Table(settings);

        LinkedList<Player> players = new LinkedList<>();

        players.add(new AiPlayer(table,new BasicStrategy()));
        players.add(new AiPlayer(table,new BasicStrategy()));
        players.add(new AiPlayer(table,new BasicStrategy()));
        players.add(new AiPlayer(table,new BasicStrategy()));


        table.setPlayers(players);
        croupier = new Croupier(table);
        croupier.setInitialBlinds();
    }

    @Test
    public void testInitialBlindsSetup() {
        assertEquals(table.getPlayers().get(0).isSmallBlind(), true);
        assertEquals(table.getPlayers().get(1).isBigBlind(), true);
    }

    @Test
    public void testBlindMove(){
        croupier.moveBlinds();
        assertEquals(table.getPlayers().get(1).isSmallBlind(), true);
        assertEquals(table.getPlayers().get(2).isBigBlind(), true);
    }

    @Test
    public void testLastBigBlindMove(){
        croupier.moveBlinds();
        croupier.moveBlinds();
        croupier.moveBlinds();
        assertEquals(table.getPlayers().get(0).isBigBlind(), true);
        assertEquals(table.getPlayers().get(3).isSmallBlind(), true);
    }
    
    @Test
    public void testRemovePlayer(){
    	Player a=null;
		table.getPlayers().get(2).addToBalance(-table.getPlayers().get(2).getBalance());
        for(int i=0;i<table.getPlayers().size();i++){
        	if(table.getPlayers().get(i).getBalance()==0)
        		a=table.getPlayers().get(i);
        }
        this.croupier.removePlayer(a);
        assertEquals(table.getPlayers().size(), 3);
    }
}
