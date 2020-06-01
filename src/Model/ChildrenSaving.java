package Model;

public class ChildrenSaving extends Saving
{
    //TODO: SQL update

    private int children_saving_id;

    public ChildrenSaving(int balance, int saved_money, int children_account_id) {
        super(balance, saved_money);
        this.children_saving_id=children_account_id;
    }

    public void brakeSaving(Account account){
        account.addToBalance(this.getBalance());
        this.setBalance(0);
    }
}
