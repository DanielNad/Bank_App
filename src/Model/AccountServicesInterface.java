package Model;

import java.sql.ResultSet;

public interface AccountServicesInterface {
    Account createAccount(ResultSet resultSet) ;
    ChildrenAccount createChildrenAccount(ResultSet resultSet) ;
    ChildrenSaving createChildrenSaving(ResultSet resultSet) ;
    Saving createSaving(ResultSet resultSet);
    ResultSet searchAccountId(String accountId) ;
    boolean validateAccountId(String accountId) ;
    void insertAccount(Account account, int clientId) ;
    void updateAccount(Account account);
    void deleteAccount(Account account);
    void deleteAllAccount();
}
