package Model;

import java.sql.*;
import java.util.Scanner;
import Database.*;

public class User
{

    private String username;
    private String password;
    private ResultSet rs = null;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String password){
        this.password=password;
    }

    public boolean validateClientUsernameAndPassword(){
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT * FROM client WHERE username = ? AND password = ?";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, this.username);
            preparedStmt.setString(2, this.password);
            rs = preparedStmt.executeQuery();
            if (!rs.next())
                return false;
            else
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean validateClientUsername(){
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT * FROM client WHERE username = ?";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, this.username);
            rs = preparedStmt.executeQuery();
            if (!rs.next())
                return false;
            else
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void setNewClientPassword(){
        Connection con = ConnectionManager.getConnection();
        String query = "UPDATE client SET password = ? WHERE username = ?";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(2, this.username);
            preparedStmt.setString(1, this.password);
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean validateBankerUsernameAndPassword(){
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT * FROM banker WHERE username = ? AND password = ?";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, this.username);
            preparedStmt.setString(2, this.password);
            rs = preparedStmt.executeQuery();
            if (!rs.next())
                return false;
            else
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean validateBankerUsername(){
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT * FROM banker WHERE username = ?";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, this.username);
            rs = preparedStmt.executeQuery();
            if (!rs.next())
                return false;
            else
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean validateBankMangaer(){
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT * FROM banker WHERE username = ? AND password = ?";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, this.username);
            preparedStmt.setString(2, this.password);
            rs = preparedStmt.executeQuery();
            rs.next();
            if (!rs.getBoolean("is_manager"))
                return false;
            else
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void setNewBankerPassword(){
        Connection con = ConnectionManager.getConnection();
        String query = "UPDATE banker SET password = ? WHERE username = ?";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(2, this.username);
            preparedStmt.setString(1, this.password);
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Model.User{" +
                "username='" + username + '\'' +
                ", password=" + password +
                '}';
    }
}
