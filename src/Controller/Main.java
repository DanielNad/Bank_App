package Controller;

import Model.BankManager;
import Model.Client;
import Model.User;
import View.ViewApp;

public class Main {
    public static void main(String[] args) {
        ViewApp panel = new ViewApp();
        Client Sun = new Client(new User("sunshavit","123456"));
        System.out.println(Sun);
        //BankManager admin = new BankManager("admin","admin","admin",new User("admin","admin"),999999999);
        //Controller app = new Controller(panel);
        //panel.setVisible(true);
    }
}
