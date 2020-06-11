package Controller;

import Model.Account;
import View.ViewApp;

public class Main {
    public static void main(String[] args){
        ViewApp panel = new ViewApp();
        Controller controller = new Controller(panel);
        panel.setVisible(true);
    }
}
