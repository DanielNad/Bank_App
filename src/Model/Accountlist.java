package Model;

import java.util.HashMap;

public class Accountlist
{
    private final HashMap<String,Account> list;

    public Accountlist() {
        this.list=new HashMap<String, Account>();
    }

    public HashMap<String,Account> getList() {
        return list;
    }

    public void addAccountToList(Account account){
        this.list.put(account.getAccountId(),account);
    }

    public Account searchAccount(String accountid) { return list.get(accountid); }
}
