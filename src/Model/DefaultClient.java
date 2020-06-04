package Model;

public interface DefaultClient
{
    boolean transferMoney(int money, String acountidfrom, String accountidto);
    void depositMoney(int sum, String accountid);
    boolean withdrawCash(int sum, String accountid);
}
