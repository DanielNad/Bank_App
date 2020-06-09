package Controller;

import Model.*;
import View.ViewApp;

public class Main {
    public static void main(String[] args) {
        ViewApp panel = new ViewApp();
        //Client sun = new Client("Shavit","Sun","Ramat Gan",100000,"sun","sunsun123",206920647);
        //BankManager admin = new BankManager("admin","admin","admin",new User("admin","admin"),999999999);
        //Saving sav = new Saving(sun.getClientId(),sun.getNumber_of_accounts());
        //ChildrenSaving savch = new ChildrenSaving(sun.getClientId(),sun.getNumber_of_accounts());
        Controller app = new Controller(panel);
        panel.setVisible(true);
    }
}
