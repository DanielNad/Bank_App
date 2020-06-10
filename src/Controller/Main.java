package Controller;
import Database.*;
import Model.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ClientRepository clientRepository = new ClientRepository();

        Client Daniel = new Client("Nadav","Daniel","Bat-Yam",0,"daniel","1",205635170);

        clientRepository.insertClient(Daniel);

        //BankManager admin = new BankManager("admin","admin","admin",new User("admin","admin"),999999999);
        //Saving sav = new Saving(sun.getClientId(),sun.getNumber_of_accounts());
        //ChildrenSaving savch = new ChildrenSaving(sun.getClientId(),sun.getNumber_of_accounts());

        //ViewApp panel = new ViewApp();
        //Controller app = new Controller(panel);
        //panel.setVisible(true);
    }
}
