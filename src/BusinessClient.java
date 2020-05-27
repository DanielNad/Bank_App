import java.io.Serializable;

public class BusinessClient extends BClient implements Serializable
{
    private String company_name;
    private static final long serialVersionUID = 4914033394478924400L;

    public BusinessClient(String last_name, String first_name, String address, int income, String username,int pas, String company_name,int clientid) {
        super(last_name, first_name, address, income,username,pas,clientid);
        this.company_name=company_name;
    }

    public boolean ask_for_loan(int sum,BankManager bankManager,int accountid){
        if(bankManager.approve_loans(this,sum,accountid))
            return true;
        else
            return false;
    }

    public String get_company_name() {
        return company_name;
    }

    public void set_company_name(String company_name) {
        this.company_name = company_name;
    }
}
