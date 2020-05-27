import jdk.nashorn.internal.parser.JSONParser;

import java.io.Serializable;

public class Banker extends Person implements Serializable
{
    private static final long serialVersionUID = -3866938147684393552L;
    private User my_user;
    private int banker_id;

    public Banker(String last_name, String first_name, String address, User my_user, int banker_id) {
        super(last_name, first_name, address);
        this.my_user=my_user;
        this.banker_id=banker_id;
    }

    public User get_my_user() { return my_user; }

    public int get_banker_id() {
        return banker_id;
    }

    public void show_client_info(BClient client){ System.out.println(client);}

    public boolean transfer_client_to_client(BClient client1,int fromAccount,int toAccount,BClient client2,int money) {
        Account account=client1.get_my_accounts().search_account(fromAccount);
        Account account2=client2.get_my_accounts().search_account(toAccount);
        if(account.get_balance()<money)
            return false;
        else{
            account.add_to_balance(-money);
            account2.add_to_balance(money);
        }
        return true;
    }

    public void deposit_client_money(int sum,BClient client,int accountid){

        Account account=client.get_my_accounts().search_account(accountid);
        if(account!=null)
        {
            account.add_to_balance(sum);
        }

    }

    public boolean withdraw_client_cash(int sum,BClient client,int accountid){

        Account account=client.get_my_accounts().search_account(accountid);
        if(account!=null)
        {
            if(account.get_balance()>=sum) {
                account.add_to_balance(sum);
                return true;
            }
            else
                return false;
        }
        return false;
    }

    public void create_new_client(String first_name,String last_name,String address,int income,String username,int password,Accountlist accountlist,int id){

        new BClient(last_name,first_name,address,income,username,password,id);
    }

    public void create_account(int balance,BClient client){

        client.get_my_accounts().add_account_to_list(new Account(balance));
    }

    public void edit_client_info(BClient client,String first_name,String last_name,String address,int income,int password){


        client.set_first_name(first_name);
        client.set_last_name(last_name);
        client.set_address(address);
        client.set_income(income);
        client.get_my_user().change_password(password);

    }

    @Override
    public String toString() {
        return "Banker{" +
                "my_user=" + my_user +
                ", banker_id=" + banker_id +
                " first name="+this.get_first_name()+
                " last name="+this.get_last_name()+
                '}';
    }

}
