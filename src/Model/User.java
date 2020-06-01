package Model;

import java.sql.*;
import java.util.Scanner;
import Database.*;

public class User
{

    private String username;
    private String password;
    private ResultSet rs = null;
    Scanner in = new Scanner(System.in);

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

    public boolean ValidateClientUsernameAndPassword(){
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

    public boolean ValidateBankerUsernameAndPassword(){
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
    public boolean ValidateBankMangaer(){
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

    @Override
    public String toString() {
        return "Model.User{" +
                "username='" + username + '\'' +
                ", password=" + password +
                '}';
    }
}
