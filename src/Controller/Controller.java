package Controller;
import Model.*;
import View.*;

import javax.swing.*;

public class Controller {
    private Banker banker;
    private Client client;
    private User user;
    private ViewApp viewApp;

    public Controller(ViewApp viewApp) {
        this.banker = null;
        this.client = null;
        this.user = null;
        this.viewApp = viewApp;
        JButton login_button = this.viewApp.getLoginButton();
        login_button.addActionListener(e -> {this.ClientLogin();});
    }

    public void ClientLogin(){
        if(!IsBanker())
        {
            String password = new String(viewApp.getPasswordTextField().getPassword());
            this.user = new User(viewApp.getUsernameEditText().getText(),password);
            this.client = new Client(user);
            System.out.println(client);

        }
    }

    public boolean IsBanker(){
        return this.viewApp.getClassifyButton().isSelected();
    }
}
