import java.io.Serializable;

public class Account implements Serializable
{

   private int balance;
   private int account_id;
   private static int new_id;//Maybe not static?

   public Account(int balance) {
      this.balance = balance;
      this.account_id = new_id;
      new_id++;
   }

   public Account(int balance, int account_id) {
      this.balance = balance;
      this.account_id = account_id;
   }

   public int get_balance() {
      return balance;
   }

   public void set_balance(int balance) {
      this.balance = balance;
   }

   public int get_account_id() {
      return account_id;
   }

   public void add_to_balance(int money){
      this.balance+=money;
   }

   public void set_account_id(int account_id) {
      this.account_id = account_id;
   }//Delete?

   @Override
   public String toString() {
      return "Account{" +
              "balance=" + balance +
              ", account_id=" + account_id +
              '}';
   }
}
