package Model;

import Database.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChildrenSaving extends Saving
{
    public ChildrenSaving(int balance) {
        super(balance);
        this.updateSaving();
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
            String query = "UPDATE account SET children_saving = ? WHERE account_id = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setBoolean(1,true);
            preparedStmt.setInt(2,this.getAccountId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
