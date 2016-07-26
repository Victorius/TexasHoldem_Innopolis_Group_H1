package org.mftech.Model.Enumerations;

public enum CombinationType {
	NoCombination (0),
	HighCard (100),
	Pair (500),
	TwoPairs (1000),
	ThreeOfKind (1500),
	Straight (2500),
	Flash (3000),
	FullHouse (4000),
	Quads (5000),
	StraightFlash (10000);
	
	private int priority;
	
	CombinationType(int priority){
		this.priority=priority;
	}
	public int getValue(){
		return priority;
	}
	
	
	
}
