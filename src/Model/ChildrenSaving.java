package Model;

public class ChildrenSaving extends Saving
{
    private int children_account_id;

    public ChildrenSaving(int balance, int saved_money, int children_account_id) {
        super(balance, saved_money);
        this.children_account_id=children_account_id;
    }

    public void brakeSaving(Account account){
        account.addToBalance(this.getBalance());
        this.setBalance(0);
    }
}
