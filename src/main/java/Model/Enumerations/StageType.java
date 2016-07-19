package main.java.Model.Enumerations;

public enum StageType {
	PreFlop		(0),
	Flop	(1),
	Turn	(2),
	River	(3);
	
	private final int value;
	
		StageType(int value)
		{
			this.value = value;
		}
		StageType()
		{
			this.value = 0;
		}
	
	public int getValue()
	{
		return value;
	}
}
