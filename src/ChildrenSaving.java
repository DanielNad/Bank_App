import java.io.Serializable;

public class ChildrenSaving extends Saving implements Serializable
{
    private int children_account_id;

    private static final long serialVersionUID = -3575090144030082479L;

    public ChildrenSaving(int balance, int saved_money, int children_account_id) {
        super(balance, saved_money);
        this.children_account_id=children_account_id;
    }

    public void brake_saving(Account account){
        account.add_to_balance(this.get_balance());
        this.set_balance(0);
    }
}
