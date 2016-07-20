package main.java.Model;

import main.java.Model.Enumerations.ActionType;

public class PlayerAction {
	private ActionType type;
	private int amount;
	
	public PlayerAction(ActionType type){
		this.setType(type);
	}

	public ActionType getType() {
		return type;
	}

	private void setType(ActionType type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString(){
		return this.type.toString() + (amount == 0 ? "" : amount);
	}
}
