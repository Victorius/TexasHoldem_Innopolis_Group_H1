package tests.java;

import java.util.LinkedList;

import AiStrategies.BasicStrategy;
import AiStrategies.BluffStrategy;
import main.java.Model.AiPlayer;
import main.java.Model.Croupier;
import main.java.Model.HumanPlayer;
import main.java.Model.Player;
import main.java.Model.Table;
import main.java.Model.TableSettings;
import main.java.Model.Enumerations.RaisingPolicy;

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
