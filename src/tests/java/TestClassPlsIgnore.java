package tests.java;

import java.util.LinkedList;

import org.mftech.Model.AiPlayer;
import org.mftech.Model.Croupier;
import org.mftech.Model.HumanPlayer;
import org.mftech.Model.Player;
import org.mftech.Model.Table;
import org.mftech.Model.TableSettings;
import org.mftech.Model.Enumerations.RaisingPolicy;

import AiStrategies.BasicStrategy;
import AiStrategies.BluffStrategy;

public class TestClassPlsIgnore {
	public static void main(String[] args) {
        TableSettings settings = new TableSettings(4,5000,50, RaisingPolicy.NoLimits);
        Table table = new Table(settings);
        Croupier croupier = new Croupier(table);
        LinkedList<Player> players = new LinkedList<>();
        players.add(new HumanPlayer("Player", table));
        players.add(new AiPlayer(table,new BasicStrategy()));
        players.add(new AiPlayer(table,new BluffStrategy()));
        players.add(new AiPlayer(table,new BluffStrategy()));
        players.add(new AiPlayer(table,new BasicStrategy()));
        
        players.get(3).setCurrent(true);
        table.setPlayers(players);
        croupier.setInitialBlinds();
        croupier.getActions();
               
        
	}
}
