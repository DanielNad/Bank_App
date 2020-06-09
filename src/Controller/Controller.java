package Controller;
import Model.*;
import View.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//TODO: Create tables if there are no existing ones already
//TODO: Error handling - transferring to an invalid client id etc.
//TODO: Design patterns
//TODO: Constructor in client with only id

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
        viewApp.getSwitchAccountJButton().addActionListener(e -> this.switchAccount());
    }

    public void login(){
        String password = new String(viewApp.getPasswordTextField().getPassword());
        this.user = new User(viewApp.getUsernameEditText().getText(),password);
        if(isBanker())
        {
            if(!user.validateBankerUsernameAndPassword()) {
                viewApp.getInvalidUsername().setVisible(true);
                invalidThread(viewApp.getInvalidUsername());
            }
            else {
                this.banker = new Banker(user);
            }
        }
        else if (isManager())
        {
            if(!user.validateBankerUsernameAndPassword() && user.validateBankMangaer()) {
                viewApp.getInvalidUsername().setVisible(true);
                invalidThread(viewApp.getInvalidUsername());
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
                invalidThread(viewApp.getInvalidUsername());
            }
            else {
                this.client = new Client(user);
                viewApp.getMainClientPanel().setVisible(true);
                viewApp.getLoginPanel().setVisible(false);
                this.clearLoginPanel();
                account = client.getMyAccounts().getList().get(client.getClientId()+"-1");
                updateClientMainPanel();
                mainClientPanel();
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
                    this.invalidThread(viewApp.getErrorPanel());
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
                    this.invalidThread(viewApp.getErrorPanel());
                }
            }
        }
        else {
            viewApp.getErrorPanel().setText("Password does not match! Please try again");
            viewApp.getErrorPanel().setVisible(true);
            invalidThread(viewApp.getErrorPanel());
        }
    }

    public boolean isBanker(){
        return this.viewApp.getClassifyButton().isSelected();
    }

    public boolean isManager() {
        return this.viewApp.getManagerCheck().isSelected();
    }

    public void invalidThread(JLabel label){
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    label.setVisible(false);
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

    public void clearForgetPasswordPanel(){
        viewApp.getForgetPasswordField_1().setText("");
        viewApp.getForgetPasswordField_2().setText("");
        viewApp.getForgetUsernameTextField().setText("");
    }

    public void updateClientMainPanel(){
        viewApp.getClientHelloLabel().setText("Hello " + client.getFirstName() + "!");
        viewApp.getBalanceClientNumberJLabel().setText(account.getBalance()+"$");
        viewApp.getAccountIdNumberJLabel().setText(account.getAccountId());
        if(account instanceof ChildrenAccount) {
            viewApp.getAccountIdTypeNameJLabel().setText("Children Account");
            viewApp.getSeparator_Last().setVisible(false);
            viewApp.getAskForMoneyJButton().setVisible(true);
            viewApp.getSaveMoneyJButton().setVisible(false);
            viewApp.getBreakeSavingJButton().setVisible(false);
            viewApp.getSavingClientMainJPanel().setVisible(false);
            viewApp.getSavingClientMainNumberJLabel().setVisible(false);
        }
        else if(account instanceof ChildrenSaving){
            viewApp.getAccountIdTypeNameJLabel().setText("Children Saving");
            viewApp.getSeparator_Last().setVisible(true);
            viewApp.getAskForMoneyJButton().setVisible(false);
            viewApp.getSaveMoneyJButton().setVisible(true);
            viewApp.getBreakeSavingJButton().setVisible(true);
            viewApp.getSavingClientMainJPanel().setVisible(true);
            viewApp.getSavingClientMainNumberJLabel().setVisible(true);
            viewApp.getSavingClientMainNumberJLabel().setText(((ChildrenSaving) account).getSaved_money()+"");
        }
        else if(account instanceof Saving){
            viewApp.getAccountIdTypeNameJLabel().setText("Saving");
            viewApp.getSeparator_Last().setVisible(true);
            viewApp.getAskForMoneyJButton().setVisible(false);
            viewApp.getSaveMoneyJButton().setVisible(true);
            viewApp.getBreakeSavingJButton().setVisible(false);
            viewApp.getSavingClientMainJPanel().setVisible(true);
            viewApp.getSavingClientMainNumberJLabel().setVisible(true);
            viewApp.getSavingClientMainNumberJLabel().setText(((Saving) account).getSaved_money()+"");
        }
        else{
            viewApp.getAccountIdTypeNameJLabel().setText("Regular Account");
            viewApp.getSeparator_Last().setVisible(false);
            viewApp.getAskForMoneyJButton().setVisible(false);
            viewApp.getSaveMoneyJButton().setVisible(false);
            viewApp.getBreakeSavingJButton().setVisible(false);
            viewApp.getSavingClientMainJPanel().setVisible(false);
            viewApp.getSavingClientMainNumberJLabel().setVisible(false);
        }
        viewApp.getSelectAccountComboBox().removeAllItems();
        for (String s:client.getMyAccounts().getList().keySet()) {
                viewApp.getSelectAccountComboBox().addItem(s);
        }
    }

    public void switchAccount(){
        String selected_item = viewApp.getSelectAccountComboBox().getSelectedItem().toString();
        account = client.getMyAccounts().getList().get(selected_item);
        viewApp.getSelectAccountComboBox().removeAllItems();
        this.updateClientMainPanel();
    }

    public void mainClientPanel(){
        JFrame f = new JFrame();
        f.setSize(700, 300);
        viewApp.getBackButtonClientMainJPanel().addActionListener(e ->{
            this.clearLoginPanel();
            viewApp.getLoginPanel().setVisible(true);
            viewApp.getMainClientPanel().setVisible(false);
        } );
        viewApp.getDepositCashClientButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Deposit Money");
            JLabel label = new JLabel("Please enter desired amount to deposit:");
            JButton button = new JButton();
            JTextField jTextField = new JTextField();

            dialog.add(jTextField);
            dialog.add(label);
            dialog.add(button);

            dialog.setLayout(null);

            label.setVerticalAlignment(JLabel.TOP);
            label.setFont(label.getFont().deriveFont(20.0f));
            label.setBounds(10,10,500,30);

            button.setBounds(280,70,60,20);
            button.setText("OK");

            jTextField.setBounds(20,70,250,20);

            dialog.setLocationByPlatform(true);
            dialog.setLocationRelativeTo(viewApp.getMainClientPanel());
            dialog.setSize(500,200);
            dialog.setVisible(true);
            validateTextField(jTextField);

            button.addActionListener(e1 -> {
                if(!jTextField.getText().equals("")) {
                    client.depositMoney((Integer.parseInt(jTextField.getText())), account.getAccountId());
                    dialog.setVisible(false);
                    updateClientMainPanel();
                }
            });
        });
        viewApp.getWithdrawCashClientButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Withdraw Money");
            JLabel label_error = new JLabel("Were sorry, you do not have enough money.");
            JLabel label = new JLabel("Please enter desired amount to withdraw:");
            JButton button = new JButton();
            JTextField jTextField = new JTextField();

            dialog.add(jTextField);
            dialog.add(label);
            dialog.add(label_error);
            dialog.add(button);

            dialog.setLayout(null);

            label.setFont(label.getFont().deriveFont(20.0f));
            label.setBounds(10,10,500,30);

            label_error.setBounds(20,85,500,30);
            label_error.setForeground(Color.red);
            label_error.setVisible(false);

            button.setBounds(280,70,60,20);
            button.setText("OK");

            jTextField.setBounds(20,70,250,20);

            dialog.setLocationByPlatform(true);
            dialog.setLocationRelativeTo(viewApp.getMainClientPanel());
            dialog.setSize(500,200);
            dialog.setVisible(true);

            validateTextField(jTextField);

            button.addActionListener(e1 -> {
                if(!jTextField.getText().equals(""))
                {
                    if(client.withdrawCash((Integer.parseInt(jTextField.getText())),account.getAccountId())) {
                        dialog.setVisible(false);
                        updateClientMainPanel();
                    }
                    else{
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    }
                }
            });
        });
        viewApp.getTransferClientToClientButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Transfer money to another client");
            JLabel label_error = new JLabel("Were sorry, something went wrong...");
            JLabel label_head = new JLabel("Money Transfer:");
            JLabel label = new JLabel("Please enter desired amount to Transfer:");
            JLabel label_2 = new JLabel("Please enter a valid account ID to transfer:");
            JButton button = new JButton();
            JTextField jTextField = new JTextField();
            JTextField jTextField_To = new JTextField();

            dialog.add(label_head);
            dialog.add(label);
            dialog.add(label_2);
            dialog.add(label_error);
            dialog.add(jTextField);
            dialog.add(jTextField_To);
            dialog.add(button);
            dialog.setLayout(null);

            label_head.setFont(label.getFont().deriveFont(20.0f));
            label_head.setBounds(10,10,500,30);

            label.setBounds(20,50,500,30);

            jTextField.setBounds(20,85,500,30);

            label_2.setBounds(20,120,500,30);

            jTextField_To.setBounds(20,155,500,30);

            label_error.setForeground(Color.red);
            label_error.setBounds(20,190,500,30);
            label_error.setVisible(false);

            button.setText("OK");
            button.setBounds(235,220,100,30);

            dialog.setLocationByPlatform(true);
            dialog.setLocationRelativeTo(viewApp.getMainClientPanel());
            dialog.setSize(600,330);
            dialog.setVisible(true);

            validateTextField(jTextField);

                button.addActionListener(e1 -> {
                    if(!jTextField.getText().equals("") && !jTextField_To.getText().equals("")) {
                        if (client.transferMoney(Integer.parseInt(jTextField.getText()), account.getAccountId(), jTextField_To.getText())) {
                            dialog.setVisible(false);
                            updateClientMainPanel();
                        } else {
                            label_error.setVisible(true);
                            invalidThread(label_error);
                        }
                    }
                    else {
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    }
                });
        });
        viewApp.getNewClientAccountButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Create New Account");
            JLabel label = new JLabel("Request Accepted!");
            JButton button = new JButton();

            dialog.add(label);
            dialog.add(button);

            dialog.setLayout(null);

            label.setVerticalAlignment(JLabel.CENTER);
            label.setFont(label.getFont().deriveFont(20.0f));
            label.setBounds(10,10,500,30);

            button.setBounds(220,80,60,20);
            button.setText("OK");

            dialog.setLocationByPlatform(true);
            dialog.setLocationRelativeTo(viewApp.getMainClientPanel());
            dialog.setSize(500,200);
            dialog.setVisible(true);

            client.newAccount();

            account = client.getMyAccounts().searchAccount(client.getClientId() + "-" + client.getNumber_of_accounts());

            updateClientMainPanel();

            button.addActionListener(e1 -> {
                dialog.setVisible(false);
            });
        });
        viewApp.getNewChildrenAccountButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Create New Children Account");
            JLabel head = new JLabel("Create New Children Account:");
            JLabel name = new JLabel("Children name:");
            JLabel parent = new JLabel("Parent Account ID:");
            JLabel label = new JLabel("Request Accepted!");
            JTextField name_text = new JTextField();
            JComboBox comboBox = new JComboBox();
            JButton button = new JButton();

            for (String s:client.getMyAccounts().getList().keySet()) {
                comboBox.addItem(s);
            }

            dialog.add(head);
            dialog.add(name);
            dialog.add(parent);
            dialog.add(label);
            dialog.add(name_text);
            dialog.add(comboBox);
            dialog.add(button);

            dialog.setLayout(null);

            head.setVerticalAlignment(JLabel.CENTER);
            head.setFont(label.getFont().deriveFont(20.0f));
            head.setBounds(10,10,500,30);

            name.setBounds(20,45,500,20);

            name_text.setBounds(20,70,300,30);

            parent.setBounds(20,105,500,30);

            comboBox.setBounds(20,140,300,30);

            button.setBounds(140,180,60,20);

            button.setText("OK");

            dialog.setLocationByPlatform(true);
            dialog.setLocationRelativeTo(viewApp.getMainClientPanel());
            dialog.setSize(450,300);
            dialog.setVisible(true);

            final String s = comboBox.getSelectedItem().toString();

            button.addActionListener(e1 -> {
                if(!name_text.getText().equals("")) {
                    client.newChildrenAccount(name_text.getText(), s);
                    account = client.getMyAccounts().searchAccount(client.getClientId() + "-" + client.getNumber_of_accounts());
                    updateClientMainPanel();
                    dialog.setVisible(false);
                }
            });
        });
        viewApp.getAskForMoneyJButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Transfer Money to Children:");
            JLabel label = new JLabel("Please enter desired amount to transfer:");
            JLabel label_error = new JLabel("Were sorry, you do not have enough money.");
            JButton button = new JButton();
            JTextField jTextField = new JTextField();

            dialog.add(jTextField);
            dialog.add(label);
            dialog.add(label_error);
            dialog.add(button);

            dialog.setLayout(null);

            label.setVerticalAlignment(JLabel.TOP);
            label.setFont(label.getFont().deriveFont(20.0f));
            label.setBounds(10,10,500,30);

            jTextField.setBounds(20,45,250,30);

            label_error.setVisible(false);
            label_error.setForeground(Color.red);
            label_error.setBounds(20,80,500,30);

            button.setBounds(130,115,60,30);
            button.setText("OK");

            dialog.setLocationByPlatform(true);
            dialog.setLocationRelativeTo(viewApp.getMainClientPanel());
            dialog.setSize(500,200);
            dialog.setVisible(true);
            validateTextField(jTextField);

            button.addActionListener(e1 -> {
                if(!jTextField.getText().equals("")) {
                    if (((ChildrenAccount) account).askForMoney(client.getMyAccounts().searchAccount(((ChildrenAccount) account).getParentId()), Integer.parseInt(jTextField.getText().toString()))) {
                        dialog.setVisible(false);
                        updateClientMainPanel();
                    }
                    else {
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    }
                }
            });
        });
        viewApp.getSaveMoneyJButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Save Money");
            JLabel label_error = new JLabel("Were sorry, you do not have enough money.");
            JLabel label = new JLabel("Please enter desired amount to save:");
            JButton button = new JButton();
            JTextField jTextField = new JTextField();

            dialog.add(jTextField);
            dialog.add(label);
            dialog.add(label_error);
            dialog.add(button);

            dialog.setLayout(null);

            label.setFont(label.getFont().deriveFont(20.0f));
            label.setBounds(10,10,500,30);

            label_error.setBounds(20,85,500,30);
            label_error.setForeground(Color.red);
            label_error.setVisible(false);

            button.setBounds(280,70,60,20);
            button.setText("OK");

            jTextField.setBounds(20,70,250,20);

            dialog.setLocationByPlatform(true);
            dialog.setLocationRelativeTo(viewApp.getMainClientPanel());
            dialog.setSize(500,200);
            dialog.setVisible(true);

            validateTextField(jTextField);

            button.addActionListener(e1 -> {
                if(!jTextField.getText().equals(""))
                {
                    if(((Saving)account).saveMoney(Integer.parseInt(jTextField.getText()))) {
                        dialog.setVisible(false);
                        updateClientMainPanel();
                    }
                    else{
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    }

                }
            });
        });
        viewApp.getBreakeSavingJButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Breake Children's Saving:");
            JLabel label = new JLabel("Please enter account ID to transfer to:");
            JLabel label_error = new JLabel("Were sorry, the provided account is not a children's account.");
            JButton button = new JButton();
            JTextField jTextField = new JTextField();

            dialog.add(jTextField);
            dialog.add(label);
            dialog.add(label_error);
            dialog.add(button);

            dialog.setLayout(null);

            label.setVerticalAlignment(JLabel.TOP);
            label.setFont(label.getFont().deriveFont(20.0f));
            label.setBounds(10,10,500,30);

            jTextField.setBounds(20,45,250,30);

            label_error.setForeground(Color.red);
            label_error.setBounds(20,80,500,30);

            button.setBounds(130,115,60,30);
            button.setText("OK");

            dialog.setLocationByPlatform(true);
            dialog.setLocationRelativeTo(viewApp.getMainClientPanel());
            dialog.setSize(500,200);
            label_error.setVisible(false);
            dialog.setVisible(true);

            button.addActionListener(e1 -> {
                if(!jTextField.getText().equals("")) {

                    if(client.getMyAccounts().searchAccount(jTextField.getText()) instanceof ChildrenAccount) {
                        ((ChildrenSaving) account).brakeSaving((ChildrenAccount) (client.getMyAccounts().searchAccount(jTextField.getText())));
                            dialog.setVisible(false);
                            updateClientMainPanel();
                        }
                    else {
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    }
                }
            });
        });

    }

    public void validateTextField(JTextField jTextField){
        jTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = jTextField.getText();
                int l = value.length();
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == 8 ) {
                    jTextField.setEditable(true);

                } else {
                    jTextField.setEditable(false);

                }
            }
        });
    }
}
