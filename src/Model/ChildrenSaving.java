package Model;

import Database.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChildrenSaving extends Saving
{
    private int children_saving_id;

    public ChildrenSaving(int balance, int saved_money, int children_account_id) {
        super(balance, saved_money);
        this.children_saving_id=children_account_id;
        this.updateChildrenSaving();
    }

    public void brakeSaving(Account account){
        account.addToBalance(this.getBalance());
        this.updateAccount();
        this.setSaved_money(0);
        this.updateSaving();
    }

    public void updateChildrenSaving(){
        Connection con = ConnectionManager.getConnection();
        try {
            String query = "UPDATE account SET children_saving_id = ? WHERE account_id = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,this.children_saving_id);
            preparedStmt.setInt(2,this.getAccountId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
