package Model;

import java.util.HashMap;

public class Accountlist
{
    private HashMap<Integer,Account> list;

    public Accountlist() {
        this.list=new HashMap<Integer, Account>();
    }

    public HashMap<Integer,Account> getList() {
        return list;
    }

    public void addAccountToList(Account account){
        this.list.put(account.getAccountId(),account);
    }

    public Account searchAccount(int accountid) { return list.get(accountid); }
}
