package Model;

public class Account
{
   private int balance;
   private final String account_id;

   public Account(int balance,int client_id,int number_of_accounts) {
      this.balance = balance;
      this.account_id = client_id + "-" + number_of_accounts ;
   }

   public Account(int balance, String account_id) {
      this.balance = balance;
      this.account_id = account_id;
   }

   public int getBalance() {
      return balance;
   }

   public void setBalance(int balance) {
      this.balance = balance;
   }

   public String getAccountId() {
      return account_id;
   }

   public void addToBalance(int money){
      this.balance+=money;
   }

   @Override
   public String toString() {
      return "Account:" + account_id + "Balance =" + balance;
   }
}
