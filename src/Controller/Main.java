package Controller;
import Database.BankerRepository;
import Database.ClientRepository;
import Model.BankManager;
import Model.User;
import View.ViewApp;

public class Main {
    public static void main(String[] args){
        ViewApp panel = new ViewApp();
        Controller controller = new Controller(panel);
        panel.setVisible(true);
    }
}
