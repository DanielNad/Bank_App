package Model;

import java.sql.*;

public class Client extends Person
{
    private int income;
    private Accountlist my_accounts;
    private User my_user;
    private int clientId;
    private int number_of_accounts;

    public Client(){super();}
    /*
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
     */
    //Desing Pattern - Builder
    public static ClientBuilder builder(){
        return new ClientBuilder();
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

    public void setMy_accounts(Accountlist my_accounts) {
        this.my_accounts = my_accounts;
    }

    public void setMy_user(User my_user) {
        this.my_user = my_user;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void depositMoney(int sum, String accountid) {
        Account account = this.getMyAccounts().searchAccount(accountid);
        account.addToBalance(sum);
    }

    public boolean withdrawCash(int sum, String accountid) {
        Account account=this.getMyAccounts().searchAccount(accountid);
        if(account.getBalance()>=sum) {
            account.addToBalance(-sum);
            return true;
        }
        return false;
    }

    public boolean transferMoney(int money, String from_id, Account to_id) {

        Account account1 = this.my_accounts.searchAccount(from_id);
        if(account1.getBalance()<money)
            return false;
        else {
            account1.addToBalance(-money);
            to_id.addToBalance(money);
        }
        return true;
    }

    public Account newAccount(){
        this.number_of_accounts++;
        Account account = new Account(0,this.clientId,this.number_of_accounts);
        this.getMyAccounts().getList().put(account.getAccountId(),account);
        return account;
    }

    public ChildrenAccount newChildrenAccount(String name, String parent_account_id){
        this.number_of_accounts++;
        ChildrenAccount account = new ChildrenAccount(0,name,this.clientId,parent_account_id,this.number_of_accounts);
        this.getMyAccounts().getList().put(account.getAccountId(),account);
        return account;
    }
}