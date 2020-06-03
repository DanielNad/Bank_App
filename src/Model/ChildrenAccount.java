package Model;

import Database.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChildrenAccount extends Account
{
    private String children_name;
    private int parent_account_id;

    public ChildrenAccount(int balance,String children_name,int parent_account) {
        super(balance);
        this.children_name=children_name;
        this.parent_account_id = parent_account;
        this.updateChildrenAccount();
    }

    public ChildrenAccount(int balance,String children_name,int parent_account_id,int account_id){
        super(balance,account_id);
        this.children_name = children_name;
        this.parent_account_id = parent_account_id;
        this.updateChildrenAccount();
    }

    public String getChildrenName() {
        return children_name;
    }

    public int getParentId() {
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
            preparedStmt.setInt(2,this.parent_account_id);
            preparedStmt.setInt(3,this.getAccountId());
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
