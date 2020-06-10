package Database;

import Model.BankManager;
import Model.Banker;
import Model.BankerServicesInteface;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankerRepository implements BankerServicesInteface {

    private ResultSet resultSet;

    @Override
    public Banker createBanker(ResultSet resultSet)  {
        Banker banker = null;
        try {
            banker = new Banker(
                    resultSet.getString("lname"),
                    resultSet.getString("fname"),
                    resultSet.getString("address"),
                    new User(resultSet.getString("username"),resultSet.getString("password")),
                    resultSet.getInt("banker_id")
            );
            return banker;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public BankManager createBankManager(ResultSet resultSet)  {
        BankManager banker = null;
        try {
            banker = new BankManager(
                    resultSet.getString("lname"),
                    resultSet.getString("fname"),
                    resultSet.getString("address"),
                    new User(resultSet.getString("username"),resultSet.getString("password")),
                    resultSet.getInt("banker_id")
            );
            return banker;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet searchBankerUser(User user)  {
        String query = "SELECT * FROM banker WHERE username = '" + user.getUsername() + "' AND password = '" +
                user.getPassword() + "'";
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
    public boolean validateBankerUser(User user)  {
        String query = "SELECT * FROM banker WHERE username = '" + user.getUsername() + "' AND password = '" + user.getPassword() + "'" ;
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
    public boolean validateBankerId(int bankerId)  {
        String query = "SELECT * FROM banker WHERE banker_id = " + bankerId;
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
    public boolean validateBankerUsername(String username)  {
        String query = "SELECT * FROM banker WHERE username = '" + username + "'";
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
    public boolean validateBankMangaer(String username, String password)  {
        String query = "SELECT * FROM banker WHERE username = '" + username + "' AND password = '" + password + "'";
        resultSet = ConnectionManager.getInstance().executeQuery(query);
        try {
            resultSet.next();
            if (!resultSet.getBoolean("is_manager"))
                return false;
            else
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void setNewBankerPassword(String username, String password)  {
        String query = "UPDATE banker SET password = '" + password + "' WHERE username = '" + username + "'";
    }

    @Override
    public void insertBanker(Banker banker)  {
        String query = "INSERT INTO banker(banker_id,address,fname,lname,password,username)" +
                " VALUES (" + banker.getBankerId() + ",'" + banker.getAddress() +
                "','" + banker.getFirstName() + "','" + banker.getLastName() + "','" + banker.getMyUser().getPassword() + "','" +
                banker.getMyUser().getUsername() + "'" + ")";
        ConnectionManager.getInstance().executeUpdate(query);
    }

    @Override
    public void updateBanker(Banker banker) {
        String query = "UPDATE banker SET banker_id = " + banker.getBankerId() + ",address = '" + banker.getBankerId() + "',fname = '"
                + banker.getFirstName() + "',lname = '" + banker.getLastName() + "',password = '" + banker.getMyUser().getPassword() +
                 "',username = '" + banker.getMyUser().getUsername() + "'";
        String query1 = " WHERE banker_id = '" + banker.getBankerId() + "'";
        if (banker instanceof BankManager) {
            String query2 = query + ",is_manager = 1 " + query1;
            ConnectionManager.getInstance().executeUpdate(query2);
        }
        ConnectionManager.getInstance().executeUpdate(query + query1);
    }

    @Override
    public void deleteBanker(Banker banker) {
        String query = "DELETE FROM banker Where id = " + banker.getBankerId();
        ConnectionManager.getInstance().executeUpdate(query);
    }

    @Override
    public void deleteAllBanker() {
        String query = "DELETE FROM banker";
        ConnectionManager.getInstance().executeUpdate(query);
    }
}
