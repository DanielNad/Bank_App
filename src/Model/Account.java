package Model;

import Database.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Account
{
   private int balance;
   private int account_id;
   private static int new_id;

   public Account(int balance) {
      this.balance = balance;
      this.account_id = new_id;
      new_id++;
      this.updateAccount();
   }

   public Account(int balance, int account_id) {
      this.balance = balance;
      this.account_id = account_id;
   }

   public int getBalance() {
      return balance;
   }

   public void setBalance(int balance) {
      this.balance = balance;
   }

   public int getAccountId() {
      return account_id;
   }

   public void addToBalance(int money){
      this.balance+=money;
   }

   public void setAccountId(int account_id) {
      this.account_id = account_id;
   }

   public void insertAccount(int client_id){
      Connection con = ConnectionManager.getConnection();
      String query = "INSERT INTO account (account_id,id,balance) VALUES (?,?,?)";
      try {
         PreparedStatement preparedStmt = con.prepareStatement(query);
         preparedStmt = con.prepareStatement(query);
         preparedStmt.setInt(1,account_id);
         preparedStmt.setInt(2,client_id);
         preparedStmt.setInt(3,this.balance);
         preparedStmt.executeUpdate();
         preparedStmt.close();
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
   }

   public void updateAccount() {
      Connection con = ConnectionManager.getConnection();
      try {
         String query = "UPDATE account SET account_id = ?, id = ?, balance = ?";
         PreparedStatement preparedStmt = con.prepareStatement(query);
         preparedStmt.setInt(1,this.account_id);
         preparedStmt.setInt(2,this.balance);
         preparedStmt.executeUpdate();
         preparedStmt.close();
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
   }

   public void deleteAccount(){
      Connection con = ConnectionManager.getConnection();
      try{
         String query = "DELETE FROM account Where account_id = ?";
         PreparedStatement preparedStmt = con.prepareStatement(query);
         preparedStmt.setInt(1,this.account_id);
         preparedStmt.executeUpdate();
      }catch(SQLException throwables){
         throwables.printStackTrace();
      }
   }

   @Override
   public String toString() {
      return "Account:" + account_id + "Balance =" + balance;
   }
}
