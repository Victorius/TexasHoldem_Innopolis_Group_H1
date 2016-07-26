package org.mftech.Interfaces;

import org.mftech.Model.Combination;
import org.mftech.Model.PlayerAction;

public interface IPlayer {
	Combination getCombination();
	PlayerAction getPlayerAction();
}
