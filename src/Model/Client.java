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
        this.insertClient();
    }

    public Client(int clientId){
        this.clientId = clientId;
        Connection con = ConnectionManager.getConnection();
        try {
            String query = "SELECT * FROM client where id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,clientId);
            rs = preparedStmt.executeQuery();
            rs.next();
            this.setAddress(rs.getString("address"));
            this.setFirstName(rs.getString("fname"));
            this.setLastName(rs.getString("lname"));
            this.income = rs.getInt("income");
            this.number_of_accounts = rs.getInt("number_of_accounts");
            my_user = new User(rs.getString("username"),rs.getString("password"));
            this.retrieveAccounts();
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
    }

    public Client(User my_user) {
        this.my_user = my_user;
        Connection con = ConnectionManager.getConnection();
        try {
            String query = "SELECT * FROM client where username =? AND password = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(2,this.getMyUser().getPassword());
            preparedStmt.setString(1,this.getMyUser().getUsername());
            rs = preparedStmt.executeQuery();
            rs.next();
            this.clientId=rs.getInt("id");
            this.setAddress(rs.getNString("address"));
            this.setFirstName(rs.getNString("fname"));
            this.setLastName(rs.getNString("lname"));
            this.income = rs.getInt("income");
            this.number_of_accounts = rs.getInt("number_of_accounts");
            this.retrieveAccounts();
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
    }

     public void retrieveAccounts(){
        this.my_accounts = new Accountlist();
         Connection con = ConnectionManager.getConnection();
         try{
             String query = "SELECT * FROM account Where id = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1,this.clientId);
             rs = preparedStmt.executeQuery();
             while(rs.next())
             {
                 if(rs.getString("children_name") != null)
                     this.my_accounts.addAccountToList(new ChildrenAccount(rs.getInt("balance"),rs.getString("children_name"),rs.getString("parent_account_id"),rs.getString("account_id")));
                 else if(rs.getBoolean("children_saving"))
                     this.my_accounts.addAccountToList(new ChildrenSaving(rs.getInt("balance"),rs.getString("account_id")));
                 else if(rs.getString("saved_money") != null)
                     this.my_accounts.addAccountToList(new Saving(rs.getInt("balance"),rs.getString("account_id")));
                 else
                     this.my_accounts.addAccountToList(new Account(rs.getInt("balance"),rs.getString("account_id")));

             }
         }catch (SQLException throwables) {
             throwables.printStackTrace();
     }
    }

    public int getIncome() {
        return income;
    }

    public Accountlist getMyAccounts() {
        return my_accounts;
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
        this.updateClient();
    }

    public void setNumber_of_accounts(int number_of_accounts) {
        this.number_of_accounts = number_of_accounts;
        this.updateClient();
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder=new StringBuilder();
        for (String r:this.getMyAccounts().getList().keySet()) {
            stringBuilder.append("\n");
            stringBuilder.append("account="+this.getMyAccounts().getList().get(r));

        }

        return  "Model.Client{" +
                "income=" + income+" "+
        "clinetid="+clientId+" "+
        "pasword="+this.getMyUser().getPassword()+" "+
        "user name="+this.getMyUser().getPassword()+" "+stringBuilder;

    }

    @Override
    public boolean transferMoney(int money, String from_id, String to_id) {

        Account account1 = this.my_accounts.searchAccount(from_id);
        if(this.validateAccount(to_id)) {
            Account account2 = new Account(to_id);
            if(account1.getBalance()<money)
                return false;
            else{
                account1.addToBalance(-money);
                account2.addToBalance(money);
            }
            return true;
        }
        else
            return false;
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

    public void newAccount(){
        this.number_of_accounts++;
        Account account = new Account(0,this.clientId,this.number_of_accounts);
        this.updateClient();
        this.getMyAccounts().getList().put(account.getAccountId(),account);
    }

    public void newChildrenAccount(String name, String parent_account_id){
        this.number_of_accounts++;
        Account account = new ChildrenAccount(0,name,this.clientId,parent_account_id,this.number_of_accounts);
        this.updateClient();
        this.getMyAccounts().getList().put(account.getAccountId(),account);
        if(account instanceof ChildrenAccount)
        {
            ((ChildrenAccount) account).updateChildrenAccount();
        }
    }

    public void insertClient(){
        Connection con = ConnectionManager.getConnection();
        String query = "INSERT INTO client (id,income,password,address,fname,lname,username,number_of_accounts) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,this.clientId);
            preparedStmt.setInt(2,this.getIncome());
            preparedStmt.setString(3,this.getMyUser().getPassword());
            preparedStmt.setString(4,this.getAddress());
            preparedStmt.setString(5,this.getFirstName());
            preparedStmt.setString(6,this.getLastName());
            preparedStmt.setString(7,this.getMyUser().getUsername());
            preparedStmt.setInt(8,this.number_of_accounts);
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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

    public boolean validateAccount(String account_id){
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT * FROM account WHERE account_id = ?";
        try {
            ResultSet rs = null;
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, account_id);
            rs = preparedStmt.executeQuery();
            if (!rs.next())
                return false;
            else
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean validateClientId(int clientId){
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT * FROM client WHERE id = ?";
        try {
            ResultSet rs = null;
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, clientId);
            rs = preparedStmt.executeQuery();
            if (!rs.next())
                return false;
            else
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
