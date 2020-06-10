package Model;

import Database.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    /*
    public void updateChildrenSaving(){
        Connection con = ConnectionManager.getConnection();
        try {
            String query = "UPDATE account SET children_saving = ? WHERE account_id = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setBoolean(1,true);
            preparedStmt.setString(2,this.getAccountId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
     */
}

