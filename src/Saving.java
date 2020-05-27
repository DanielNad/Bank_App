import java.io.Serializable;

public class Saving extends Account implements Serializable
{
    private int saved_money;
    private static final long serialVersionUID = -7934241204447390420L;

    public Saving(int balance,int saved_money) {
        super(balance);
        this.saved_money=saved_money;
    }

    public void save_money(int sum){
        this.saved_money+=sum;

    }
}
