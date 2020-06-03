package Controller;

import Model.BankManager;
import Model.Client;
import Model.User;
import View.ViewApp;

public class Main {
    public static void main(String[] args) {
        ViewApp panel = new ViewApp();
        //Client test = new Client(new User("test","test"));
        //BankManager admin = new BankManager(new User("admin","admin"));
        //admin.createNewSaving(0,test);
        //admin.createChildrenSaving(0,test);
        //admin.createNewAccount(0,test);
        //Client sun = new Client("Shavit","Sun","Ramat Gan",100000,"sun","sunsun123",206920647);
        //sun.newAccount(1000);
        //sun.newAccount(1001);
        //sun.newAccount(1021);
        //test.newChildrenAccount(0,"test_junior","111111110-1");
        //System.out.println(test);
        //BankManager admin = new BankManager("admin","admin","admin",new User("admin","admin"),999999999);
        Controller app = new Controller(panel);
        panel.setVisible(true);
    }
}
