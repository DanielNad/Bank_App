import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Accountlist implements Serializable
{
    private static final long serialVersionUID = -2373067309185267727L;
    private HashMap<Integer,Account> list;

    public Accountlist() {
        this.list=new HashMap<Integer, Account>();
    }

    public HashMap<Integer,Account> get_list() {
        return list;
    }

    public void add_account_to_list(Account account){
        this.list.put(account.get_account_id(),account);
    }

    public Account search_account(int accountid) { return list.get(accountid); }
}
