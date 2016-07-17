package Model;

public class Player extends CombinationCounter{
	//fields
	private int balance;
	
	public int getBalance() {
		return balance;
	}

	public Card[] getHand() {
		return (Card[]) cards.toArray();
	}

	public Object getLastAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
