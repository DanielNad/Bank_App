package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
    
    //Design Pattern - Singelton.
    private static final ConnectionManager instance = new ConnectionManager();
    private static Connection con;

    private static String url = "jdbc:mysql://localhost:3306/bank_schema?autoReconnect=true&useSSL=false";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "Daniel24";

    private ConnectionManager(){}

    public static ConnectionManager getInstance(){
        return instance;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(con == null) {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, username, password);
        }
        return con;
    }

    public ResultSet executeQuery(String query)  {
        try {
            return getConnection().createStatement().executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean executeUpdate(String query){
        try {
            getConnection().createStatement().executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}