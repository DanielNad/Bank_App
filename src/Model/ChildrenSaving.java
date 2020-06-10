package Model;

public class ChildrenSaving extends Saving
{
    public ChildrenSaving(int client_id,int number_of_accounts) {
        super(client_id,number_of_accounts);
    }

    public ChildrenSaving(int balance,String account_id){
        super(balance,account_id);
    }

    public void brakeSaving(ChildrenAccount account){
        account.addToBalance(this.getSaved_money());
        this.setSaved_money(0);
    }
}

