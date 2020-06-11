package Database;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository implements AccountServicesInterface {
    private ResultSet resultSet;

    @Override
    public Account createAccount(ResultSet resultSet) {
        Account account = null;
        try {
            account = new Account(
                    resultSet.getInt("balance"),
                    resultSet.getString("account_id")
            );
            return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ChildrenAccount createChildrenAccount(ResultSet resultSet) {
        ChildrenAccount account = null;
        try {
            account = new ChildrenAccount(
                    resultSet.getInt("balance"),
                    resultSet.getString("children_name"),
                    resultSet.getString("parent_account_id"),
                    resultSet.getString("account_id")
            );
            return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ChildrenSaving createChildrenSaving(ResultSet resultSet) {
        ChildrenSaving account = null;
        try {
            account = new ChildrenSaving(
                    resultSet.getInt("balance"),
                    resultSet.getString("account_id")
            );
            return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Saving createSaving(ResultSet resultSet) {
        Saving account = null;
        try {
            account = new Saving(
                    resultSet.getInt("balance"),
                    resultSet.getString("account_id")
            );
            return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet searchAccountId(String accountId) {
        String query = "SELECT * FROM account WHERE account_id = '" + accountId + "'";
        resultSet = ConnectionManager.getInstance().executeQuery(query);
        try {
            resultSet.next();
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean validateAccountId(String accountId) {
        String query = "SELECT * FROM account WHERE account_id = '" + accountId + "'";
        resultSet = ConnectionManager.getInstance().executeQuery(query);
        try {
            if (!resultSet.next())
                return false;
            else
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void insertAccount(Account account,int clientId) {
        String query = "INSERT INTO account(account_id,balance,id)" +
                " VALUES ('" + account.getAccountId() + "'," + account.getBalance() +
                "," + clientId + ")";
        ConnectionManager.getInstance().executeUpdate(query);
    }

    @Override
    public void updateAccount(Account account) {
        String query = "UPDATE account SET balance = " + account.getBalance();
        String query1 = " WHERE account_id = '" + account.getAccountId() + "'";
        if(account instanceof ChildrenAccount){
            String query2 = query + ",parent_account_id = '" + ((ChildrenAccount) account).getParentId() + "',children_name = '" + ((ChildrenAccount) account).getChildrenName() + "'" + query1;
            ConnectionManager.getInstance().executeUpdate(query2);
        }
        else if(account instanceof ChildrenSaving){
            String query2 = query + ",children_saving = 1, saved_money = " + ((ChildrenSaving) account).getSaved_money() + query1;
            ConnectionManager.getInstance().executeUpdate(query2);
        }
        else if(account instanceof Saving){
            String query2 = query + ",saved_money = " + ((Saving) account).getSaved_money() + query1;
            ConnectionManager.getInstance().executeUpdate(query2);
        }
        else {
            ConnectionManager.getInstance().executeUpdate(query + query1);
        }
    }

    @Override
    public void deleteAccount(Account account) {
        String query = "DELETE FROM account Where account_id = '" + account.getAccountId() + "'";
        ConnectionManager.getInstance().executeUpdate(query);
    }

    @Override
    public void deleteAllAccount() {
        String query = "DELETE FROM account";
        ConnectionManager.getInstance().executeUpdate(query);
    }
}
