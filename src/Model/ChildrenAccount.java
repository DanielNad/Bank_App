package Model;

public class ChildrenAccount extends Account
{
    //TODO: SQL inheritance
    //TODO: Unique update method
    //TODO: SQL Constructor
    private String children_name;
    private Account parent_id;

    public ChildrenAccount(int balance,String children_name,Account dadid) {
        super(balance);
        this.children_name=children_name;
        this.parent_id=dadid;
    }

    public String getChildrenName() {
        return children_name;
    }

    public Account getParentId() {
        return parent_id;
    }

    public void setParentId(Account dadid) {
        this.parent_id = dadid;
    }

    public void setChildrenName(String children_name) {
        this.children_name = children_name;
    }

    public void askForMoney(int sum){

        parent_id.addToBalance(-sum);
        this.addToBalance(sum);
    }
}
