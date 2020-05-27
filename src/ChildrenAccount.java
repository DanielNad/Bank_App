import java.io.Serializable;

public class ChildrenAccount extends Account implements Serializable
{
    private String children_name;
    private Account parent_id;
    private static final long serialVersionUID = -5793515960388655278L;

    public ChildrenAccount(int balance,String children_name,Account dadid) {
        super(balance);
        this.children_name=children_name;
        this.parent_id=dadid;
    }

    public String get_children_name() {
        return children_name;
    }

    public Account get_parent_id() {
        return parent_id;
    }

    public void set_parent_id(Account dadid) {
        this.parent_id = dadid;
    }

    public void set_children_name(String children_name) {
        this.children_name = children_name;
    }

    public void ask_for_money(int sum){

        parent_id.add_to_balance(-sum);
        this.add_to_balance(sum);
    }
}
