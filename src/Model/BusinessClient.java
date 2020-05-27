package Model;

public class BusinessClient extends Client
{
    //TODO: Sign up;
    //TODO: Forget password;
    //TODO: Invalid username;
    //Todo: SQL inheritance
    //Todo: Unique update method
    //TODO: SQL Constructor
    private String company_name;

    public BusinessClient(String last_name, String first_name, String address, int income, String username,String password, String company_name,int clientid) {
        super(last_name, first_name, address, income,username,password,clientid);
        this.company_name=company_name;
    }

    public boolean askForLoan(int sum, BankManager bankManager, int accountid){
        if(bankManager.approveLoans(this,sum,accountid))
            return true;
        else
            return false;
    }

    public String getCompanyName() {
        return company_name;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }
}
