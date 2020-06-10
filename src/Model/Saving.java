package Model;

public class Saving extends Account
{
    private int saved_money;

    public Saving(int client_id,int number_of_accounts) {
        super(0,client_id,number_of_accounts);
        this.saved_money=0;
    }

    public Saving(int balance, String account_id){
        super(balance,account_id);
        this.saved_money = 0;
    }

    public int getSaved_money() {
        return saved_money;
    }

    public void setSaved_money(int saved_money) {
        this.saved_money = saved_money;
    }

    public boolean saveMoney(int sum){
        if(this.getBalance() < sum)
            return false;
        else{
            this.saved_money+=sum;
            this.setBalance(this.getBalance()-sum);
            return true;
        }
    }
}
