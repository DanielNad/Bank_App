import java.io.Serializable;
import java.sql.*;
import java.sql.*;
public class BClient extends Person implements DefaultClient, Serializable
{
    private int income;
    private Accountlist my_accounts;
    private User my_user;
    private int clientId;
    private Statement stmt = null;
    private ResultSet rs = null;

    public BClient(String last_name, String first_name, String address,int income,String username,int pas,int clientId) {
        super(last_name, first_name, address);
        this.income=income;
        this.my_accounts=new Accountlist();
        this.my_user=new User(username,pas);
        this.clientId=clientId;
    }

    public BClient(User my_user) {
        this.my_user = my_user;
        Connection con = ConnectionManager.getConnection();
        try {
            String query = "SELECT * FROM client where username =? AND password = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(2,this.get_my_user().get_password());
            preparedStmt.setString(1,this.get_my_user().get_username());
            rs = preparedStmt.executeQuery();
            rs.next();
            this.clientId=rs.getInt("id");
            this.set_address(rs.getNString("address"));
            this.set_first_name(rs.getNString("fname"));
            this.set_last_name(rs.getNString("lname"));
            this.income = rs.getInt("income");
            this.retrieve_accounts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

     public void retrieve_accounts(){
        this.my_accounts = new Accountlist();
         Connection con = ConnectionManager.getConnection();
         try{
             String query = "SELECT * FROM account Where id = ?";
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1,this.clientId);
             rs = preparedStmt.executeQuery();
             while(rs.next())
             {
                 this.my_accounts.add_account_to_list(new Account(rs.getInt("balance"),rs.getInt("account_id")));
             }
         }catch (SQLException throwables) {
             throwables.printStackTrace();
     }
    }

    public int get_income() {
        return income;
    }

    public Accountlist get_my_accounts() {
        return my_accounts;
    }

    public User get_my_user() {
        return my_user;
    }

    public int get_client_Id() {
        return clientId;
    }

    public void set_income(int income) {
        this.income = income;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder=new StringBuilder();
        for (int r:this.get_my_accounts().get_list().keySet()) {
            stringBuilder.append("\n");
            stringBuilder.append("account="+this.get_my_accounts().get_list().get(r));

        }

        return  "BClient{" +
                "income=" + income+" "+
        "clinetid="+clientId+" "+
        "pasword="+this.get_my_user().get_password()+" "+
        "user name="+this.get_my_user().get_password()+" "+stringBuilder;

    }

    @Override
    public boolean transfer_money(BClient client, int money, int from_id, int to_id) {

        Account account1=this.my_accounts.search_account(from_id);
        Account account2=client.my_accounts.search_account(to_id);
        if(account1.get_balance()<money)
            return false;
        else{
            account1.add_to_balance(-money);
            account2.add_to_balance(money);
        }
        return true;
    }

    @Override
    public void deposit_money(int sum,int accountid) {
        Account account=this.get_my_accounts().search_account(accountid);
        account.add_to_balance(sum);
    }

    @Override
    public boolean withdraw_cash(int sum, int accountid) {
        Account account=this.get_my_accounts().search_account(accountid);
        if(account.get_balance()>=sum) {
            account.add_to_balance(sum);
            return true;
        }
            return false;
    }

    public void new_account(int balance){
        Account account=new Account(balance);
        this.get_my_accounts().get_list().put(account.get_account_id(),account);
    }

    public void new_children_account(int balance,String name,Account dadid){
        Account account=new ChildrenAccount(balance,name,dadid);
        this.get_my_accounts().get_list().put(account.get_account_id(),account);
    }

    public void update_client() {
        Connection con = ConnectionManager.getConnection();
        int id = this.get_client_Id();
        int income = this.get_income();
        int password = this.get_my_user().get_password();
        String address = this.get_address();
        String fname = this.get_first_name();
        String lname = this.get_last_name();
        String username = this.get_my_user().get_username();

        try {
            String query = "UPDATE client SET id = ?,income = ?, password = ?, address = ?,fname = ?,lname = ?,username = ? WHERE id = ?;";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,id);
            preparedStmt.setInt(2,income);
            preparedStmt.setInt(3,password);
            preparedStmt.setString(4,address);
            preparedStmt.setString(5,fname);
            preparedStmt.setString(6,lname);
            preparedStmt.setString(7,username);
            preparedStmt.setInt(8,id);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete_client(){
        Connection con = ConnectionManager.getConnection();
        int id = this.get_client_Id();
        try{
            String query = "DELETE FROM client Where id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,id);
            preparedStmt.executeUpdate();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

    }
}
