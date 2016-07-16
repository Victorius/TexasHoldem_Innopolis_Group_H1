package Model.Enumerations;

public enum ActionType {
	Fold		(0),
	Cheak	(1),
	Raise	(2);
	
	private final int action;
	
	ActionType(int action)
	{
		this.action = action;
	}
}
