package Model;

public class Banker extends Person
{
    private User my_user;
    private int banker_id;

    public Banker(String last_name, String first_name, String address, User my_user, int banker_id) {
        super(last_name, first_name, address);
        this.my_user=my_user;
        this.banker_id=banker_id;
    }

    public User getMyUser() { return my_user; }

    public int getBankerId() {
        return banker_id;
    }

    public Client createNewClient(String first_name, String last_name, String address, int income, String username, String password, int id){
       return Client.builder().clientId(id).address(address).fname(first_name).lname(last_name).user(username,password).numberOfAccount(0).myAccounts().build();
    }

    public Account createNewAccount(int balance, Client client){
        client.setNumber_of_accounts(client.getNumber_of_accounts()+1);
        Account account = new Account (balance,client.getClientId(),client.getNumber_of_accounts());
        client.getMyAccounts().addAccountToList(account);
        return account;
    }

    public ChildrenAccount createNewChildrenAccount(int balance, String children_name,Client client, Account parent_id ){
        client.setNumber_of_accounts(client.getNumber_of_accounts()+1);
        ChildrenAccount children_account = new ChildrenAccount(balance,children_name,client.getClientId(),parent_id.getAccountId(),client.getNumber_of_accounts());
        client.getMyAccounts().addAccountToList(children_account);
        return children_account;
    }

    public Saving createNewSaving(Client client){
        client.setNumber_of_accounts(client.getNumber_of_accounts()+1);
        Saving saving = new Saving(client.getClientId(),client.getNumber_of_accounts());
        client.getMyAccounts().addAccountToList(saving);
        return saving;
    }

    public ChildrenSaving createChildrenSaving(Client client){
        client.setNumber_of_accounts(client.getNumber_of_accounts()+1);
        ChildrenSaving children_saving = new ChildrenSaving(client.getClientId(),client.getNumber_of_accounts());
        client.getMyAccounts().addAccountToList(children_saving);
        return children_saving;
    }

    public void editClientInfo(Client client, String first_name, String last_name, String address, int income, String password){
        client.setFirstName(first_name);
        client.setLastName(last_name);
        client.setAddress(address);
        client.setIncome(income);
        client.getMyUser().changePassword(password);
    }

    public boolean transferClientToClient(Account fromAccount, Account toAccount,int money) {
        if(fromAccount.getBalance()<money)
            return false;
        else{
            fromAccount.addToBalance(-money);
            toAccount.addToBalance(money);
        }
        return true;
    }

    public void depositClientMoney(Client client, Account account,int sum){
        if(account!=null)
        {
            account.addToBalance(sum);
        }
    }

    public boolean withdrawClientCash(Client client, Account account,int sum){
        if(account!=null)
        {
            if(account.getBalance()>=sum) {
                account.addToBalance(-sum);
                return true;
            }
            else
                return false;
        }
        return false;
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