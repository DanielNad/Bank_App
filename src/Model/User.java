package Model;

import java.sql.*;

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

    @Override
    public String toString() {
        return "Model.User{" +
                "username='" + username + '\'' +
                ", password=" + password +
                '}';
    }
}
