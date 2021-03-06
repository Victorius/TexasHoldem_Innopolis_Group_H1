package tests.java;

import org.junit.Before;
import org.junit.Test;
import org.mftech.AiStrategies.BasicStrategy;
import org.mftech.Model.AiPlayer;
import org.mftech.Model.Croupier;
import org.mftech.Model.Player;
import org.mftech.Model.Table;
import org.mftech.Model.TableSettings;
import org.mftech.Model.Enumerations.RaisingPolicy;

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
}
