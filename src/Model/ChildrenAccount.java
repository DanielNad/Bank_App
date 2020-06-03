package Model;

import Database.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChildrenAccount extends Account
{
    private String children_name;
    private String parent_account_id;

    public ChildrenAccount(int balance,String children_name,int client_id,String parent_account_id,int number_of_accounts) {
        super(balance,client_id,number_of_accounts);
        this.children_name=children_name;
        this.parent_account_id = parent_account_id;
        this.updateChildrenAccount();
    }

    public ChildrenAccount(int balance,String children_name,String parent_account_id,String account_id){
        super(balance,account_id);
        this.children_name = children_name;
        this.parent_account_id = parent_account_id;
        this.updateChildrenAccount();
    }

    public String getChildrenName() {
        return children_name;
    }

    public String getParentId() {
        return parent_account_id;
    }

    public void setParentId(Account dadid) {
        this.parent_account_id = dadid.getAccountId();
        this.updateChildrenAccount();
    }

    public void setChildrenName(String children_name) {
        this.children_name = children_name;
        this.updateChildrenAccount();
    }

    public void askForMoney(Account parent_account,int sum){
        parent_account.addToBalance(-sum);
        parent_account.updateAccount();
        this.addToBalance(sum);
        this.updateAccount();
    }

    public void updateChildrenAccount(){
        Connection con = ConnectionManager.getConnection();
        try {
            String query = "UPDATE account SET children_name = ?, parent_account_id = ? WHERE account_id = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1,this.children_name);
            preparedStmt.setString(2,this.parent_account_id);
            preparedStmt.setString(3,this.getAccountId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ChildrenAccount{" +
                "children_name='" + children_name + '\'' +
                ", parent_account_id=" + parent_account_id +
                '}';
    }
}
