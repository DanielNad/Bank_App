package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ClientServicesInterface {
    public Client createClient (ResultSet resultSet) throws SQLException, ClassNotFoundException;
    public ResultSet searchClientId(int clientId) throws SQLException, ClassNotFoundException;
    public ResultSet searchClientUser(User user) throws SQLException, ClassNotFoundException;
    public void retrieveAccounts(Client client) throws SQLException, ClassNotFoundException;
    public boolean validateClientId(int clientId) throws SQLException, ClassNotFoundException;
    public void insertClient(Client client) throws SQLException, ClassNotFoundException;
    public void updateClient(Client client);
    public void deleteClient(Client client);
    public void dropClientTable();
}
