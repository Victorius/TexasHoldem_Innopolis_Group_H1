package Model.Enumerations;

public enum Combination {
	HighCard (100),
	Pair (150),
	TwoPairs (200),
	ThreeOfKind (300),
	Straight (400),
	Flash (500),
	FullHouse (600),
	Quads (700),
	StraightFlash (800)
	;
	
	private int priority;
	Combination(int priority){
		this.priority=priority;
	}
	
}
