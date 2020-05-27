import java.io.Serializable;

public class BankManager extends Banker implements Serializable {

    private static final long serialVersionUID = 1121847115883776623L;

    public BankManager(String last_name, String first_name, String address, User my_user, int banker_id) {
        super(last_name, first_name, address, my_user, banker_id);
    }

    public Boolean approve_loans(BusinessClient businessClient,int sum,int accountid){
        if(businessClient.get_income()<sum/5)
            return false;
        else
            businessClient.get_my_accounts().search_account(accountid).add_to_balance(sum);
        return true;

    }
}
