package main.java.Model;

import main.java.Model.Enumerations.ActionType;
import main.java.UI.UiHelper;
import main.java.Utility.ConsoleHelper;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, Table table) {
		super(name, table);
	}

	@Override
	public PlayerAction getPlayerAction() {
		UiHelper.updateTableInfo(aTable);
		System.out.printf("Its your turn!\n R - Raise\nC - Call\\Check\nF - Fold\n");
		String answer;
		while(true){
			answer = ConsoleHelper.getInstance().readString();
			switch (answer.toLowerCase()) {
			case "r":
				int amount = 0;
				while(true){
					System.out.printf("Raise on what amount ?\n");
					amount = ConsoleHelper.getInstance().readInt();
					if(amount > 0){
						PlayerAction result =  new PlayerAction(ActionType.Raise);
						result.setAmount(amount);
						return result;
					}
				}
			case "c":
				return new PlayerAction(ActionType.CallCheck);
			case "f":
				return new PlayerAction(ActionType.Fold);
			default:
				System.out.printf("Its your turn!\n R - Raise\nC - Call\\Check\nF - Fold\n");
				break;
			}
		}
	}

}
