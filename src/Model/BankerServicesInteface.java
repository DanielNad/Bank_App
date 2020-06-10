package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BankerServicesInteface {
    public Banker createBanker (ResultSet resultSet) ;
    public BankManager createBankManager (ResultSet resultSet) ;
    public ResultSet searchBankerUser(User user) ;
    public boolean validateBankerId(int bankerId) ;
    public boolean validateBankerUser(User user) ;
    public boolean validateBankerUsername(String username);
    public boolean validateBankMangaer(String username,String password) ;
    public void setNewBankerPassword(String username,String password) ;
    public void insertBanker(Banker banker) ;
    public void updateBanker(Banker banker);
    public void deleteBanker(Banker banker);
    public void deleteAllBanker();
}
