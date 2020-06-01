package Model;

import Database.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Banker extends Person
{
    //Todo: delete showclientInfo - should be a GUI function

    private User my_user;
    private int banker_id;
    private ResultSet rs = null;

    public Banker(String last_name, String first_name, String address, User my_user, int banker_id) {
        super(last_name, first_name, address);
        this.my_user=my_user;
        this.banker_id=banker_id;
        this.InsertBanker();
    }

    public Banker(User my_user) {
        this.my_user = my_user;
        Connection con = ConnectionManager.getConnection();
        try {
            String query = "SELECT * FROM banker where username = ? AND password = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(2,this.getMyUser().getPassword());
            preparedStmt.setString(1,this.getMyUser().getUsername());
            rs = preparedStmt.executeQuery();
            rs.next();
            this.banker_id=rs.getInt("banker_id");
            this.setAddress(rs.getNString("address"));
            this.setFirstName(rs.getNString("fname"));
            this.setLastName(rs.getNString("lname"));
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
    }

    public User getMyUser() { return my_user; }

    public int getBankerId() {
        return banker_id;
    }

    public void showClientInfo(int client_id){ }

    public boolean transferClientToClient(Client client1, int fromAccount, int toAccount, Client client2, int money) {
        Account account1=client1.getMyAccounts().searchAccount(fromAccount);
        Account account2=client2.getMyAccounts().searchAccount(toAccount);
        if(account1.getBalance()<money)
            return false;
        else{
            account1.addToBalance(-money);
            account2.addToBalance(money);
        }
        account1.updateAccount();
        account2.updateAccount();
        return true;
    }

    public void depositClientMoney(int sum, Client client, int accountid){

        Account account = client.getMyAccounts().searchAccount(accountid);
        if(account!=null)
        {
            account.addToBalance(sum);
            account.updateAccount();
        }
    }

    public boolean withdrawClientCash(int sum, Client client, int accountid){

        Account account = client.getMyAccounts().searchAccount(accountid);
        if(account!=null)
        {
            if(account.getBalance()>=sum) {
                account.addToBalance(sum);
                account.updateAccount();
                return true;
            }
            else
                return false;
        }
        return false;
    }

    public void createNewClient(String first_name, String last_name, String address, int income, String username, String password, Accountlist accountlist, int id){

       Client client = new Client(last_name,first_name,address,income,username,password,id);
       client.insertClient();
    }

    public void createAccount(int balance, Client client){

        Account account = new Account (balance);
        account.insertAccount(client.getClientId());
        client.getMyAccounts().addAccountToList(account);
    }

    public void editClientInfo(Client client, String first_name, String last_name, String address, int income, String password){

        client.setFirstName(first_name);
        client.setLastName(last_name);
        client.setAddress(address);
        client.setIncome(income);
        client.getMyUser().changePassword(password);
        client.updateClient();
    }

    public void InsertBanker(){
        Connection con = ConnectionManager.getConnection();
        String query = "INSERT INTO banker (banker_id,password,address,fname,lname,username) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,this.getBankerId());
            preparedStmt.setString(2,this.getMyUser().getPassword());
            preparedStmt.setString(3,this.getAddress());
            preparedStmt.setString(4,this.getFirstName());
            preparedStmt.setString(5,this.getLastName());
            preparedStmt.setString(6,this.getMyUser().getUsername());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void UpdateBanker() {
        Connection con = ConnectionManager.getConnection();
        try {
            String query = "UPDATE banker SET banker_id = ?, password = ?, address = ?,fname = ?,lname = ?,username = ? WHERE banker_id = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,this.getBankerId());
            preparedStmt.setString(2,this.getMyUser().getPassword());
            preparedStmt.setString(3,this.getAddress());
            preparedStmt.setString(4,this.getFirstName());
            preparedStmt.setString(5,this.getLastName());
            preparedStmt.setString(6,this.getMyUser().getUsername());
            preparedStmt.setInt(7,this.getBankerId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void DeleteBanker(){
        Connection con = ConnectionManager.getConnection();
        try{
            String query = "DELETE FROM banker Where banker_id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,this.banker_id);
            preparedStmt.executeUpdate();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Model.Banker{" +
                "my_user=" + my_user +
                ", banker_id=" + banker_id +
                " first name="+this.getFirstName()+
                " last name="+this.getLastName()+
                '}';
    }

}