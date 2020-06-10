package Model;

public class ChildrenAccount extends Account
{
    private String children_name;
    private String parent_account_id;

    public ChildrenAccount(int balance,String children_name,int client_id,String parent_account_id,int number_of_accounts) {
        super(balance,client_id,number_of_accounts);
        this.children_name=children_name;
        this.parent_account_id = parent_account_id;
    }

    public ChildrenAccount(int balance,String children_name,String parent_account_id,String account_id){
        super(balance,account_id);
        this.children_name = children_name;
        this.parent_account_id = parent_account_id;
    }

    public String getChildrenName() {
        return children_name;
    }

    public String getParentId() {
        return parent_account_id;
    }

    public void setParentId(Account dadid) {
        this.parent_account_id = dadid.getAccountId();
    }

    public void setChildrenName(String children_name) {
        this.children_name = children_name;
    }

    public boolean askForMoney(Account parent_account,int sum){
        if(parent_account.getBalance() < sum){
            return false;
        }
        else{
            parent_account.addToBalance(-sum);
            this.addToBalance(sum);
        }
        return true;
    }

    @Override
    public String toString() {
        return "ChildrenAccount{" +
                "children_name='" + children_name + '\'' +
                ", parent_account_id=" + parent_account_id +
                '}';
    }
}
