public interface DefaultClient
{
    public boolean transfer_money(BClient client,int money,int acountidfrom,int accountidto);
    public void deposit_money(int sum,int accountid);
    public boolean withdraw_cash(int sum, int accountid);
}
