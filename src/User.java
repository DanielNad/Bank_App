import java.io.Serializable;

public class User implements Serializable
{
    private String username;
    private int password;
    private static final long serialVersionUID = 2482310345878312090L;

    public User(String username, int password) {
        this.username = username;
        this.password = password;
    }

    public String get_username() {
        return username;
    }

    public int get_password() {
        return password;
    }

    public void change_password(int password){
        this.password=password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password=" + password +
                '}';
    }
}
