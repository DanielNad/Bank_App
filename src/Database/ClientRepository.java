package Database;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepository implements ClientServicesInterface {

    ResultSet  resultSet;

    @Override
    public Client createClient(ResultSet resultSet) throws SQLException {
        Client client = new Client(
                resultSet.getString("lname"),
                resultSet.getString("fname"),
                resultSet.getString("address"),
                resultSet.getInt("income"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getInt("id"),
                resultSet.getInt("number_of_accounts")
        );
        return client;
    }

    @Override
    public ResultSet searchClientId(int clientId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM client where id = " + clientId;
        resultSet = ConnectionManager.getInstance().executeQuery(query);
        resultSet.next();
        return resultSet;
    }

    @Override
    public ResultSet searchClientUser(User user) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM client where username = '" + user.getUsername() + "' AND password = '" + user.getPassword() + "'";
        resultSet = ConnectionManager.getInstance().executeQuery(query);
        resultSet.next();
        return resultSet;
    }

    @Override
    public void retrieveAccounts(Client client) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM account Where id = " + client.getClientId();
        resultSet = ConnectionManager.getInstance().executeQuery(query);
        while(resultSet.next())
        {
            if(resultSet.getString("children_name") != null){
                client.getMyAccounts().addAccountToList(new ChildrenAccount(
                        resultSet.getInt("balance"),
                        resultSet.getString("children_name"),
                        resultSet.getString("parent_account_id"),
                        resultSet.getString("account_id")));
            }
            else if(resultSet.getBoolean("children_saving")){
                client.getMyAccounts().addAccountToList(new ChildrenSaving(
                        resultSet.getInt("balance"),
                        resultSet.getString("account_id")));
            }
            else if(resultSet.getString("saved_money") != null){
                client.getMyAccounts().addAccountToList(new Saving(
                        resultSet.getInt("balance"),
                        resultSet.getString("account_id")));
            }
            else{
                client.getMyAccounts().addAccountToList(new Account(
                        resultSet.getInt("balance"),
                        resultSet.getString("account_id")));
            }
        }
    }

    @Override
    public boolean validateClientId(int clientId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM client WHERE id = " + clientId;
        resultSet = ConnectionManager.getInstance().executeQuery(query);
        if (!resultSet.next())
            return false;
        else
            return true;
    }

    @Override
    public void insertClient(Client client) throws SQLException, ClassNotFoundException {
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
                "',number_of_accounts = " + client.getNumber_of_accounts();
        ConnectionManager.getInstance().executeUpdate(query);
    }

    @Override
    public void deleteClient(Client client) {
        String query = "Delete"
    }

    @Override
    public void dropClientTable() {

    }
}
