package Model;

public class BankManager extends Banker {
    //TODO: Create banker
    //TODO: improve approve loans
    //TODO: SQL Constructor
    public BankManager(String last_name, String first_name, String address, User my_user, int banker_id) {
        super(last_name, first_name, address, my_user, banker_id);
    }

    public Boolean approveLoans(BusinessClient businessClient, int sum, int accountid){
        if(businessClient.getIncome()<sum/5)
            return false;
        else
            businessClient.getMyAccounts().searchAccount(accountid).addToBalance(sum);
        return true;

    }
}
