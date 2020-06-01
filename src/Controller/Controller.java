package Controller;
import Model.*;
import View.*;
import javax.swing.*;

//TODO: Forgot Password
//TODO: Create tables if there are no existing ones already
//TODO: Error handling - transferring to an invalid client id etc.

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
        login_button.addActionListener(e -> {this.Login();});
    }

    public void Login(){
        String password = new String(viewApp.getPasswordTextField().getPassword());
        this.user = new User(viewApp.getUsernameEditText().getText(),password);
        if(IsBanker())
        {
            if(!user.ValidateBankerUsernameAndPassword()) {
                viewApp.getInvalidUsername().setVisible(true);
            }
            else {
                this.banker = new Banker(user);
                System.out.println(client);
            }
        }
        else if (IsManager())
        {
            if(!user.ValidateBankerUsernameAndPassword() && user.ValidateBankMangaer()) {
                viewApp.getInvalidUsername().setVisible(true);
            }
            else {
                this.banker = new BankManager(user);
                if(this.banker instanceof BankManager)
                {
                    System.out.println((BankManager)banker);
                }
            }
        }
        else
        {
            if(!user.ValidateClientUsernameAndPassword()) {
                viewApp.getInvalidUsername().setVisible(true);
            }
            else {
                this.client = new Client(user);
                System.out.println(client);
            }
        }

    }

    public boolean IsBanker(){
        return this.viewApp.getClassifyButton().isSelected();
    }

    public boolean IsManager()
    {
        return this.viewApp.getManagerCheck().isSelected();
    }
}
