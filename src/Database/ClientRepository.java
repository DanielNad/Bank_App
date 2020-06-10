package Database;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepository implements ClientServicesInterface {

    private ResultSet resultSet;

    @Override
    public Client createClient(ResultSet resultSet) {
        Client client = null;
        try {
            client = new Client(
                    resultSet.getString("lname"),
                    resultSet.getString("fname"),
                    resultSet.getString("address"),
                    resultSet.getInt("income"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getInt("id"),
                    resultSet.getInt("number_of_accounts")
            );
            this.retrieveAccounts(client);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;
    }

    @Override
    public ResultSet searchClientId(int clientId)  {
        String query = "SELECT * FROM client where id = " + clientId;
        try {
            resultSet = ConnectionManager.getInstance().executeQuery(query);
            resultSet.next();
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet searchClientUser(User user) {
        String query = "SELECT * FROM client where username = '" + user.getUsername() + "' AND password = '" + user.getPassword() + "'";
        try {
            resultSet = ConnectionManager.getInstance().executeQuery(query);
            resultSet.next();
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void retrieveAccounts(Client client)  {
        String query = "SELECT * FROM account Where id = " + client.getClientId();
        resultSet = ConnectionManager.getInstance().executeQuery(query);
        {
            try {
                while(resultSet.next()) {
                    if (resultSet.getString("children_name") != null) {
                        client.getMyAccounts().addAccountToList(new ChildrenAccount(
                                resultSet.getInt("balance"),
                                resultSet.getString("children_name"),
                                resultSet.getString("parent_account_id"),
                                resultSet.getString("account_id")));
                    } else if (resultSet.getBoolean("children_saving")) {
                        client.getMyAccounts().addAccountToList(new ChildrenSaving(
                                resultSet.getInt("balance"),
                                resultSet.getString("account_id")));
                    } else if (resultSet.getString("saved_money") != null) {
                        client.getMyAccounts().addAccountToList(new Saving(
                                resultSet.getInt("balance"),
                                resultSet.getString("account_id")));
                    } else {
                        client.getMyAccounts().addAccountToList(new Account(
                                resultSet.getInt("balance"),
                                resultSet.getString("account_id")));
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public boolean validateClientId(int clientId)  {
        String query = "SELECT * FROM client WHERE id = " + clientId;
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
    public boolean validateClientUser(User user)  {
        String query = "SELECT * FROM client WHERE username = '" + user.getUsername() + "' AND password = '" + user.getPassword() + "'" ;
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
    public boolean validateClientUsername(String username)  {
        String query = "SELECT * FROM client WHERE username = '" + username + "'";
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
    public void setNewClientPassword(String username,String newPassword) {
        String query = "UPDATE client SET password = '" + newPassword + "' WHERE username = '" + username + "'";
        ConnectionManager.getInstance().executeUpdate(query);
    }

    @Override
    public void insertClient(Client client)  {
        String query = "INSERT INTO client(id,income,password,address,fname,lname,username,number_of_accounts)" +
                " VALUES (" + client.getClientId() + "," + client.getIncome() + ",'" + client.getMyUser().getPassword() +
                "','" + client.getAddress() + "','" + client.getFirstName() + "','" + client.getLastName() + "','" +
                client.getMyUser().getUsername() + "'," + client.getNumber_of_accounts() + ")";
        ConnectionManager.getInstance().executeUpdate(query);
    }

    @Override
    public void updateClient(Client client) {
        String query = "UPDATE client SET id = " + client.getClientId() + ",income = " + client.getIncome() + ",password = '"
                + client.getMyUser().getPassword() + "',address = '" + client.getAddress() + "',fname = '" + client.getFirstName() +
                "',lname = '" + client.getLastName() + "',username = '" + client.getMyUser().getUsername() +
                "',number_of_accounts = " + client.getNumber_of_accounts() + " WHERE id = " + client.getClientId();
        ConnectionManager.getInstance().executeUpdate(query);
    }

    @Override
    public void deleteClient(Client client) {
        String query = "DELETE FROM client WHERE id = " + client.getClientId();
        ConnectionManager.getInstance().executeUpdate(query);
    }

    @Override
    public void deleteAllClients() {
        String query = "DELETE FROM client";
        ConnectionManager.getInstance().executeUpdate(query);
    }
}
