package Controller;
import Model.*;
import View.*;
import javax.swing.*;

//TODO: Create tables if there are no existing ones already
//TODO: Error handling - transferring to an invalid client id etc.
//TODO: Design patterns

public class Controller {
    private Banker banker;
    private Client client;
    private User user;
    private Account account;
    private ViewApp viewApp;

    public Controller(ViewApp viewApp) {
        this.banker = null;
        this.client = null;
        this.user = null;
        this.viewApp = viewApp;
        JButton login_button = this.viewApp.getLoginButton();
        login_button.addActionListener(e -> {this.login();});
        JButton forget_button = this.viewApp.getForgetPassword();
        forget_button.addActionListener(e -> {this.forgetPassword();});
        viewApp.getSwitchAccountJLabel().addActionListener(e ->switchAccount());
    }

    public void login(){
        String password = new String(viewApp.getPasswordTextField().getPassword());
        this.user = new User(viewApp.getUsernameEditText().getText(),password);
        if(isBanker())
        {
            if(!user.validateBankerUsernameAndPassword()) {
                viewApp.getInvalidUsername().setVisible(true);
                invalidThreadLogin();
            }
            else {
                this.banker = new Banker(user);
                System.out.println(client);
            }
        }
        else if (isManager())
        {
            if(!user.validateBankerUsernameAndPassword() && user.validateBankMangaer()) {
                viewApp.getInvalidUsername().setVisible(true);
                invalidThreadLogin();
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
            if(!user.validateClientUsernameAndPassword()) {
                viewApp.getInvalidUsername().setVisible(true);
                invalidThreadLogin();
            }
            else {
                this.client = new Client(user);
                viewApp.getMainClientPanel().setVisible(true);
                viewApp.getLoginPanel().setVisible(false);
                this.clearLoginPanel();
                account = client.getMyAccounts().getList().get(client.getClientId()+"-1");
                updateClientMainPanel();
            }
        }

    }

    public void forgetPassword() {
        JButton back_forget_panel = this.viewApp.getBackButtonForgetPassword();
        back_forget_panel.addActionListener(e -> {
            this.clearLoginPanel();
            viewApp.getLoginPanel().setVisible(true);
            viewApp.getForgetPasswordPanel().setVisible(false);
        });

        viewApp.getLoginPanel().setVisible(false);
        viewApp.getForgetPasswordPanel().setVisible(true);
        JButton forget_button_on_panel = this.viewApp.getChangePasswordButuon();
        forget_button_on_panel.addActionListener(e -> {
            this.validateForgetPasswordPanel();
        });
    }

    public void validateForgetPasswordPanel(){
        String password1 = new String(viewApp.getForgetPasswordField_1().getPassword());
        String password2 = new String(viewApp.getForgetPasswordField_2().getPassword());
        if(password1.equals(password2)) {
            User user = new User(viewApp.getForgetUsernameTextField().getText(),password1);
            if (isBanker()||isManager()) {
                if(user.validateBankerUsername()) {
                    user.setNewBankerPassword();
                    this.clearLoginPanel();
                    viewApp.getLoginPanel().setVisible(true);
                    viewApp.getForgetPasswordPanel().setVisible(false);
                }
                else
                {
                    viewApp.getErrorPanel().setText("Username does not exist!");
                    viewApp.getErrorPanel().setVisible(true);
                    this.invalidThreadForgetPassword();
                }
            }
            else{
                if(user.validateClientUsername()) {
                    user.setNewClientPassword();
                    this.clearLoginPanel();
                    viewApp.getLoginPanel().setVisible(true);
                    viewApp.getForgetPasswordPanel().setVisible(false);
                }
                else {
                    viewApp.getErrorPanel().setText("Username does not exist!");
                    viewApp.getErrorPanel().setVisible(true);
                    this.invalidThreadForgetPassword();
                }
            }
        }
        else {
            viewApp.getErrorPanel().setText("Password does not match! Please try again");
            viewApp.getErrorPanel().setVisible(true);
            invalidThreadForgetPassword();
        }
    }

    public boolean isBanker(){
        return this.viewApp.getClassifyButton().isSelected();
    }

    public boolean isManager() {
        return this.viewApp.getManagerCheck().isSelected();
    }

    public void invalidThreadLogin(){
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    viewApp.getInvalidUsername().setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    public void invalidThreadForgetPassword(){
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    viewApp.getErrorPanel().setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    public void clearLoginPanel(){
        viewApp.getUsernameEditText().setText("");
        viewApp.getPasswordTextField().setText("");
        viewApp.getButtonGroup().clearSelection();
    }

    public void clearForgetPaswordPanel(){
        viewApp.getForgetPasswordField_1().setText("");
        viewApp.getForgetPasswordField_2().setText("");
        viewApp.getForgetUsernameTextField().setText("");
    }

    public void updateClientMainPanel(){
        viewApp.getClientHelloLabel().setText("Hello " + client.getFirstName());
        viewApp.getBalanceClientNumberJLabel().setText(account.getBalance()+"$");
        viewApp.getAccountIdNumberJLabel().setText(account.getAccountId());
        for (String s:client.getMyAccounts().getList().keySet()) {
            viewApp.getSelectAccountComboBox().addItem(s);
        }
    }

    public void switchAccount(){
        String selected_item=viewApp.getSelectAccountComboBox().getSelectedItem().toString();
        account = client.getMyAccounts().getList().get(selected_item);
        viewApp.getSelectAccountComboBox().removeAllItems();
        this.updateClientMainPanel();
    }
}
