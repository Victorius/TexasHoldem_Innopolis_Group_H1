package tests.java;

import main.java.Model.Croupier;
import main.java.Model.Enumerations.RaisingPolicy;
import main.java.Model.Player;
import main.java.Model.Table;
import main.java.Model.TableSettings;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;


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

        players.add(new Player("John", 10000));
        players.add(new Player("Smith", 10000));
        players.add(new Player("Bob", 10000));
        players.add(new Player("Rob", 10000));


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
    public void testCollectBetsFromPlayers(){
        table.getPlayers().get(0).makeBet(700);
        table.getPlayers().get(1).makeBet(7000);
        table.getPlayers().get(2).makeBet(70);
        table.getPlayers().get(3).makeBet(900);

        croupier.collectBetsFromPlayers();
        assertEquals(table.getPot(), 8670);
    }

    @Test
    public void testPayPotToPlayer(){
        table.getPlayers().get(0).makeBet(1000);
        table.getPlayers().get(1).makeBet(2000);

        croupier.collectBetsFromPlayers();
        croupier.payPotToPlayer(table.getPlayers().get(3));
        assertEquals(table.getPlayers().get(3).getBalance(),13000);
    }

}
