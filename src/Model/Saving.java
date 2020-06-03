package Model;

import Database.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Saving extends Account
{
    private int saved_money;

    public Saving(int balance,int client_id,int number_of_accounts) {
        super(balance,client_id,number_of_accounts);
        this.saved_money=0;
        this.updateSaving();
    }

    public Saving(int balance,String account_id){
        super(balance,account_id);
        this.saved_money = 0;
        this.updateSaving();
    }

    public int getSaved_money() {
        return saved_money;
    }

    public void setSaved_money(int saved_money) {
        this.saved_money = saved_money;
        this.updateSaving();
    }

    public void saveMoney(int sum){
        this.saved_money+=sum;
        this.updateSaving();
    }

    public void updateSaving(){
    Connection con = ConnectionManager.getConnection();
    try {
        String query = "UPDATE account SET saved_money = ? WHERE account_id = ?;";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1,this.saved_money);
        preparedStmt.setString(2,this.getAccountId());
        preparedStmt.executeUpdate();
        preparedStmt.close();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
}
}
