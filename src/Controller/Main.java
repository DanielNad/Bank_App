package Controller;

import Model.Client;
import Model.User;
import View.ViewApp;

public class Main {
    public static void main(String[] args) {
        ViewApp panel = new ViewApp();
        Controller app = new Controller(panel);
        panel.setVisible(true);
    }
}
