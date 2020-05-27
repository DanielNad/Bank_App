package Model;

public class Saving extends Account
{
    //TODO: SQL inheritance
    //TODO: Unique update method
    //TODO: SQL Constructor
    private int saved_money;

    public Saving(int balance,int saved_money) {
        super(balance);
        this.saved_money=saved_money;
    }

    public void saveMoney(int sum){
        this.saved_money+=sum;

    }
}
