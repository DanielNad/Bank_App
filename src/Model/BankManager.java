package Model;

import Database.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankManager extends Banker {
    //TODO: improve approve loans

    public BankManager(String last_name, String first_name, String address, User my_user, int banker_id) {
        super(last_name, first_name, address, my_user, banker_id);
        this.updateBankManager();
    }

    public BankManager(User user) {
        super(user);
    }

    public void createBanker(String last_name, String first_name, String address, User my_user, int banker_id) {
        new Banker(last_name,first_name,address,my_user,banker_id);
    }

    @Override
    public String toString() {
        return "BankManager { } ";
    }

    public Boolean approveLoans(BusinessClient businessClient, int sum, int accountid){
        if(businessClient.getIncome()<sum/5)
            return false;
        else
            businessClient.getMyAccounts().searchAccount(accountid).addToBalance(sum);
        return true;

    }

    public void updateBankManager() {
        Connection con = ConnectionManager.getConnection();
        try {
            String query = "UPDATE banker SET is_manager = ? WHERE banker_id = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setBoolean(1,true);
            preparedStmt.setInt(2,this.getBankerId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
