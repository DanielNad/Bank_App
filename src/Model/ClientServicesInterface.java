package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ClientServicesInterface {
    public Client createClient (ResultSet resultSet);
    public ResultSet searchClientId(int clientId) ;
    public ResultSet searchClientUser(User user) ;
    public void retrieveAccounts(Client client) ;
    public boolean validateClientId(int clientId) ;
    public boolean validateClientUser(User user);
    public boolean validateClientUsername(String username);
    public void setNewClientPassword(String username,String newPassword);
    public void insertClient(Client client);
    public void updateClient(Client client);
    public void deleteClient(Client client);
    public void deleteAllClients();
}
