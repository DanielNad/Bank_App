package Model;

import java.sql.*;
import java.util.Scanner;
import Database.*;

public class Client extends Person implements DefaultClient
{
    private int income;
    private Accountlist my_accounts;
    private User my_user;
    private int clientId;
    private int number_of_accounts;
    private ResultSet rs = null;

    public Client(String last_name, String first_name, String address, int income, String username, String password, int clientId) {
        super(last_name, first_name, address);
        this.income=income;
        this.my_accounts=new Accountlist();
        this.my_user=new User(username,password);
        this.clientId=clientId;
        this.number_of_accounts = 0;
    }

    public Client(String last_name, String first_name, String address, int income, String username, String password, int clientId,int number_of_accounts) {
        super(last_name, first_name, address);
        this.income=income;
        this.my_accounts=new Accountlist();
        this.my_user=new User(username,password);
        this.clientId=clientId;
        this.number_of_accounts = number_of_accounts;
    }

    public Accountlist getMyAccounts() {
        return my_accounts;
    }

    public int getIncome() {
        return income;
    }

    public User getMyUser() {
        return my_user;
    }

    public int getClientId() {
        return clientId;
    }

    public int getNumber_of_accounts() {
        return number_of_accounts;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setNumber_of_accounts(int number_of_accounts) {
        this.number_of_accounts = number_of_accounts;
    }

    @Override
    public void depositMoney(int sum, String accountid) {
        Account account = this.getMyAccounts().searchAccount(accountid);
        account.addToBalance(sum);
    }

    @Override
    public boolean withdrawCash(int sum, String accountid) {
        Account account=this.getMyAccounts().searchAccount(accountid);
        if(account.getBalance()>=sum) {
            account.addToBalance(-sum);
            return true;
        }
        return false;
    }

    @Override
    public boolean transferMoney(int money, String from_id, String to_id) {

        Account account1 = this.my_accounts.searchAccount(from_id);
        if(false) {
            /*
            Account account2 = new Account(to_id);
            if(account1.getBalance()<money)
                return false;
            else{
                account1.addToBalance(-money);
                account2.addToBalance(money);
            }
             */
            return true;
        }
        else
            return false;
    }

    public void newAccount(){
        this.number_of_accounts++;
        Account account = new Account(0,this.clientId,this.number_of_accounts);
        this.getMyAccounts().getList().put(account.getAccountId(),account);
    }

    public void newChildrenAccount(String name, String parent_account_id){
        this.number_of_accounts++;
        Account account = new ChildrenAccount(0,name,this.clientId,parent_account_id,this.number_of_accounts);
        this.getMyAccounts().getList().put(account.getAccountId(),account);
    }
    /*
    public void updateClient() {
        Connection con = ConnectionManager.getConnection();
        try {
            String query = "UPDATE client SET id = ?,income = ?, password = ?, address = ?,fname = ?,lname = ?,username = ?,number_of_accounts = ? WHERE id = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,this.getClientId());
            preparedStmt.setInt(2,this.getIncome());
            preparedStmt.setString(3,this.getMyUser().getPassword());
            preparedStmt.setString(4,this.getAddress());
            preparedStmt.setString(5,this.getFirstName());
            preparedStmt.setString(6,this.getLastName());
            preparedStmt.setString(7,this.getMyUser().getUsername());
            preparedStmt.setInt(8,this.getNumber_of_accounts());
            preparedStmt.setInt(9,this.getClientId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void deleteClient(){
        Connection con = ConnectionManager.getConnection();
        try{
            String query = "DELETE FROM client Where id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,this.clientId);
            preparedStmt.executeUpdate();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }
 */
}
