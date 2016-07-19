package main.java.Interfaces;

import main.java.Model.Combination;
import main.java.Model.PlayerAction;

public interface IPlayer {
	Combination getCombination();
	PlayerAction getPlayerAction();
}
