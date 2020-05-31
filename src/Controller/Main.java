package Controller;

import Model.Client;
import Model.User;
import View.ViewApp;
//TODO: Create tables if there are no existing ones already
//TODO: Error handling - transferring to an invalid client id etc.
public class Main {
    public static void main(String[] args) {
        ViewApp panel = new ViewApp();
        Controller app = new Controller(panel);
        panel.setVisible(true);
    }
}
