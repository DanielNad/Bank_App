package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AccountServicesInterface {
    public Account createAccount (ResultSet resultSet) ;
    public ChildrenAccount createChildrenAccount(ResultSet resultSet) ;
    public ChildrenSaving createChildrenSaving(ResultSet resultSet) ;
    public Saving createSaving(ResultSet resultSet);
    public ResultSet searchAccountId(String accountId) ;
    public boolean validateAccountId(String accountId) ;
    public void insertAccount(Account account,int clientId) ;
    public void updateAccount(Account account);
    public void deleteAccount(Account account);
    public void deleteAllAccount();
}
