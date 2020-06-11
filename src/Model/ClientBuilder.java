package Model;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

public class ClientBuilder {
    private int income;
    private Accountlist my_accounts;
    private User my_user;
    private int clientId;
    private int number_of_accounts;
    private String last_name;
    private String first_name;
    private String address;

    public ClientBuilder income(int income) {
        this.income = income;
        return this;
    }

    public ClientBuilder myAccounts() {
        this.my_accounts= new Accountlist();
        return this;
    }

    public ClientBuilder user(String username,String password) {
        this.my_user=new User(username,password);
        return this;
    }

    public ClientBuilder clientId(int clientId) {
        this.clientId=clientId;
        return this;
    }

    public ClientBuilder numberOfAccount(int number_of_accounts) {
        this.number_of_accounts=number_of_accounts;
        return this;
    }

    public ClientBuilder address(String address) {
        this.address=address;
        return this;
    }

    public ClientBuilder fname(String first_name) {
        this.first_name = first_name;
        return this;
    }
    
    public ClientBuilder lname(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public Client build(){
        Client client=new Client();
        client.setIncome(this.income);
        client.setNumber_of_accounts(number_of_accounts);
        client.setAddress(this.address);
        client.setFirstName(this.first_name);
        client.setLastName(this.last_name);
        client.setClientId(this.clientId);
        client.setMy_user(this.my_user);
        client.setMy_accounts(this.my_accounts);
        return client;
    }
}
