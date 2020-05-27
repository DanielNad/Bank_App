package Model;

public interface DefaultClient
{
    boolean transferMoney(Client client, int money, int acountidfrom, int accountidto);
    void depositMoney(int sum, int accountid);
    boolean withdrawCash(int sum, int accountid);
}
