package Model;

public class Player extends CombinationCounter {

    //Fields
    private int balance;
    private String name;
    private boolean bigBlind;
    private boolean smallBlind;
    private Bet bet;

    //Constructor
    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
        bigBlind = false;
        smallBlind = false;
    }

    //Setters
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBet(Bet bet) {
        this.bet = bet;
    }
    public void setSmallBlind(boolean smallBlind) {
        this.smallBlind = smallBlind;
    }
    public void setBigBlind(boolean bigBlind) {
        this.bigBlind = bigBlind;
    }


    //Getters
    public Bet getBet() {
        return bet;
    }
    public int getBalance() {
        return balance;
    }
    public String getName() {
        return name;
    }
    public boolean isBigBlind() {
        return bigBlind;
    }
    public boolean isSmallBlind() {
        return smallBlind;
    }


    //to place bet
    public void makeBet(int amount) {
        if (amount > balance) {
            bet = new Bet(0, this, balance); // all in
            balance = 0;
        } else {

            balance -= amount;
            bet = new Bet(0, this, amount);
        }
    }

    //raise existing bet
    public void raiseBet(int amount) {
        if (bet != null) {
            if (amount > balance) {
                bet.raise(balance); // all in
                balance = 0;
            } else {
                bet.raise(amount);
                balance -= amount;
            }
        }
    }

    //clear placed bet
    public void clearBet() {
        bet = null;
    }

    public void clearBlinds() {
        smallBlind = false;
        bigBlind = false;
    }

    //adding amount(pot) to player's balance
    public void addToBalance(int amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" " + balance);
        if (smallBlind)
            sb.append(" S ");
        if (bigBlind)
            sb.append(" B ");

        return sb.toString();
    }
}
