package Controller;
import Database.AccountRepository;
import Database.BankerRepository;
import Database.ClientRepository;
import Model.*;
import View.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

//TODO: Create tables if there are no existing ones already
//TODO: Error handling - transferring to an invalid client id etc.
//TODO: Design patterns

public class Controller {
    private Banker banker;
    private Client client;
    private Account account;
    private User user;
    private ClientRepository clientRepository;
    private BankerRepository bankerRepository;
    private AccountRepository accountRepository;
    private ViewApp viewApp;

    public Controller(ViewApp viewApp) {
        this.banker = null;
        this.client = null;
        this.user = null;
        this.clientRepository = new ClientRepository();
        this.bankerRepository = new BankerRepository();
        this.accountRepository = new AccountRepository();
        this.viewApp = viewApp;
        JButton login_button = this.viewApp.getLoginButton();
        login_button.addActionListener(e -> { this.login();});
        JButton forget_button = this.viewApp.getForgetPassword();
        forget_button.addActionListener(e -> {this.forgetPassword();});
        viewApp.getSwitchAccountJButton().addActionListener(e -> this.switchAccount());
    }

    public void login() {
        String password = new String(viewApp.getPasswordTextField().getPassword());
        this.user = new User(viewApp.getUsernameEditText().getText(),password);
        if(isBanker())
        {
            if(!bankerRepository.validateBankerUser(user)) {
                viewApp.getInvalidUsername().setVisible(true);
                invalidThread(viewApp.getInvalidUsername());
            }
            else {
                this.banker = bankerRepository.createBanker(bankerRepository.searchBankerUser(user));
                viewApp.getLoginPanel().setVisible(false);
                viewApp.getMainBankerPanel().setVisible(true);
                this.clearLoginPanel();
                this.mainBankerPanel();
            }
        }
        else if (isManager())
        {
            if(!bankerRepository.validateBankerUser(user) && !bankerRepository.validateBankMangaer(user.getUsername(),user.getPassword())) {
                viewApp.getInvalidUsername().setVisible(true);
                invalidThread(viewApp.getInvalidUsername());
            }
            else {
                this.banker = bankerRepository.createBankManager(bankerRepository.searchBankerUser(user));
                viewApp.getCreateNewBankerMainBankerJButton().setVisible(true);
                viewApp.getLoginPanel().setVisible(false);
                viewApp.getMainBankerPanel().setVisible(true);
                this.clearLoginPanel();
                this.mainBankerPanel();

            }
        }
        else
        {
            if(!clientRepository.validateClientUser(user)) {
                viewApp.getInvalidUsername().setVisible(true);
                invalidThread(viewApp.getInvalidUsername());
            }
            else {
                this.client = clientRepository.createClient(clientRepository.searchClientUser(user));
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
            this.clearForgetPasswordPanel();
            viewApp.getLoginPanel().setVisible(true);
            viewApp.getForgetPasswordPanel().setVisible(false);
        });

        viewApp.getLoginPanel().setVisible(false);
        viewApp.getForgetPasswordPanel().setVisible(true);
        JButton forget_button_on_panel = this.viewApp.getChangePasswordButuon();
        forget_button_on_panel.addActionListener(e -> {this.validateForgetPasswordPanel();});
        this.clearForgetPasswordPanel();
    }

    public void validateForgetPasswordPanel()  {
        String password1 = new String(viewApp.getForgetPasswordField_1().getPassword());
        String password2 = new String(viewApp.getForgetPasswordField_2().getPassword());
        if(password1.equals(password2) && (!password1.equals(""))) {
            user=new User(viewApp.getForgetUsernameTextField().getText(),password1);
            if (isBanker()||isManager()) {
                if(bankerRepository.validateBankerUsername(user.getUsername())) {
                    bankerRepository.setNewBankerPassword(user.getUsername(),user.getPassword());
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
                if(clientRepository.validateClientUsername(user.getUsername())) {
                    clientRepository.setNewClientPassword(user.getUsername(),user.getPassword());
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
                    accountRepository.updateAccount(account);
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
                        accountRepository.updateAccount(account);
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
                        if(accountRepository.validateAccountId(jTextField_To.getText())){
                            Account account1 = accountRepository.createAccount(accountRepository.searchAccountId(jTextField_To.getText()));
                            if (client.transferMoney(Integer.parseInt(jTextField.getText()), account.getAccountId(), account1)) {
                                accountRepository.updateAccount(account);
                                accountRepository.updateAccount(account1);
                                client=clientRepository.createClient(clientRepository.searchClientId(client.getClientId()));

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

            clientRepository.updateClient(client);

            account = client.getMyAccounts().searchAccount(client.getClientId() + "-" + client.getNumber_of_accounts());

            accountRepository.insertAccount(account,client.getClientId());

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
                    clientRepository.updateClient(client);
                    account = client.getMyAccounts().searchAccount(client.getClientId() + "-" + client.getNumber_of_accounts());
                    accountRepository.insertAccount(account,client.getClientId());
                    accountRepository.updateAccount(account);
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
                    Account account1 = client.getMyAccounts().searchAccount(((ChildrenAccount) account).getParentId());
                    if (((ChildrenAccount) account).askForMoney(account1, Integer.parseInt(jTextField.getText().toString()))) {
                        accountRepository.updateAccount(account);
                        accountRepository.updateAccount(account1);
                        client = clientRepository.createClient(clientRepository.searchClientId(client.getClientId()));
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
                        accountRepository.updateAccount(account);
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
                    Account account1 = client.getMyAccounts().searchAccount(jTextField.getText());
                    if(account1 instanceof ChildrenAccount) {
                        ((ChildrenSaving) account).brakeSaving((ChildrenAccount) (client.getMyAccounts().searchAccount(jTextField.getText())));
                            accountRepository.updateAccount(account1);
                            accountRepository.updateAccount(account);
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

    public void mainBankerPanel(){
        JFrame f = new JFrame();
        f.setSize(700, 700);
        viewApp.getBankerHelloJLabel().setText("Hello " + banker.getFirstName());
        viewApp.getBackButtonMainBankerJButton().addActionListener(e -> {
            this.clearLoginPanel();
            viewApp.getLoginPanel().setVisible(true);
            viewApp.getMainBankerPanel().setVisible(false);
        });
        viewApp.getSelectClientMainBankerJButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Select Client");
            JLabel label_error = new JLabel("Were sorry, you do not have enough money.");
            JLabel label = new JLabel("Please enter a valid Client ID:");
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
                    if(true) {
                        client = clientRepository.createClient(clientRepository.searchClientId(Integer.parseInt(jTextField.getText())));
                        account = client.getMyAccounts().searchAccount(client.getClientId()+"-1");
                        dialog.setVisible(false);
                        updateBankerPanel();
                    }
                    else{
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    }
                }
            });
        });
        viewApp.getSelectAccountsMainBankerJButton().addActionListener(e -> {switchAccountInBanker();});
        viewApp.getCreateNewClientMainBankerJButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Create New Client");
            JLabel label_error = new JLabel("Were sorry, something went wrong...");
            JLabel label_head = new JLabel("Create Client:");
            JLabel clientIdJL = new JLabel("Client ID:");
            JLabel fnameJL = new JLabel("First Name:");
            JLabel lnameJL = new JLabel("Last Name:");
            JLabel addressJL = new JLabel("Address:");
            JLabel incomeJL = new JLabel("Income:");
            JLabel usernameJL = new JLabel("Username:");
            JLabel passwordJL =new JLabel("Password:");

            JTextField clientIdJT = new JTextField();
            JTextField fnameJT = new JTextField();
            JTextField lnameJT = new JTextField();
            JTextField addressJT = new JTextField();
            JTextField incomeJT = new JTextField();
            JTextField usernameJT = new JTextField();
            JTextField passwordJT = new JTextField();

            JButton button = new JButton();
            dialog.add(label_head);
            dialog.add(label_error);
            dialog.add(clientIdJL);
            dialog.add(fnameJL);
            dialog.add(lnameJL);
            dialog.add(addressJL);
            dialog.add(incomeJL);
            dialog.add(usernameJL);
            dialog.add(passwordJL);
            dialog.add(clientIdJT);
            dialog.add(fnameJT);
            dialog.add(lnameJT);
            dialog.add(addressJT);
            dialog.add(incomeJT);
            dialog.add(usernameJT);
            dialog.add(passwordJT);
            dialog.add(button);
            dialog.setLayout(null);

            label_head.setFont(label_head.getFont().deriveFont(20.0f));
            label_head.setBounds(10,10,500,30);

            label_error.setForeground(Color.red);
            //label_error.setBounds(20,190,500,30);
            label_error.setVisible(false);

            clientIdJL.setBounds(20,50,80,25);
            clientIdJL.setHorizontalTextPosition(SwingConstants.CENTER);

            clientIdJT.setBounds(110,50,300,25);

            fnameJL.setBounds(20,90,80,25);
            fnameJL.setHorizontalTextPosition(SwingConstants.CENTER);

            fnameJT.setBounds(110,90,300,25);

            lnameJL.setBounds(20,130,80,25);
            lnameJL.setHorizontalTextPosition(SwingConstants.CENTER);

            lnameJT.setBounds(110,130,300,25);

            addressJL.setBounds(20,170,80,25);
            addressJL.setHorizontalTextPosition(SwingConstants.CENTER);

            addressJT.setBounds(110,170,300,25);

            incomeJL.setBounds(20,210,80,25);
            incomeJL.setHorizontalTextPosition(SwingConstants.CENTER);

            incomeJT.setBounds(110,210,300,25);

            usernameJL.setBounds(20,250,80,25);
            usernameJL.setHorizontalTextPosition(SwingConstants.CENTER);

            usernameJT.setBounds(110,250,300,25);

            passwordJL.setBounds(20,290,80,25);
            passwordJL.setHorizontalTextPosition(SwingConstants.CENTER);

            passwordJT.setBounds(110,290,300,25);

            label_error.setBounds(150,330,300,25);
            label_error.setHorizontalTextPosition(SwingConstants.CENTER);

            button.setBounds(210,370,80,30);
            button.setText("OK");


            dialog.setLocationByPlatform(true);
            dialog.setLocationRelativeTo(viewApp.getMainClientPanel());
            dialog.setSize(500,500);
            dialog.setVisible(true);

            validateTextField(clientIdJT);
            validateTextField(incomeJT);


            button.addActionListener(e1 -> {
                if(!fnameJT.getText().equals("") && !lnameJT.getText().equals("") && !addressJT.getText().equals("") && !usernameJT.getText().equals("") && !passwordJT.getText().equals("") && !incomeJT.getText().equals("") && !clientIdJT.getText().equals("")) {
                    if (clientRepository.validateClientUsername(usernameJT.getText()) || clientRepository.validateClientId(Integer.parseInt(clientIdJT.getText()))) {
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    } else {
                        client = banker.createNewClient(fnameJT.getText(), lnameJT.getText(), addressJT.getText(), Integer.parseInt(incomeJT.getText()), usernameJT.getText(), passwordJT.getText(), Integer.parseInt(clientIdJT.getText()));
                        account = client.newAccount();
                        clientRepository.insertClient(client);
                        accountRepository.insertAccount(account, client.getClientId());
                        dialog.setVisible(false);
                        updateBankerPanel();
                    }
                }
            });

        });
        viewApp.getCreateNewAccountMainBankerJButton().addActionListener(e -> {
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

            account = banker.createNewAccount(0,client);
            clientRepository.updateClient(client);
            accountRepository.updateAccount(account);
            updateBankerPanel();

            button.addActionListener(e1 -> {
                dialog.setVisible(false);
            });
        });
        viewApp.getCreateNewChildrenAccountMainBankerJButton().addActionListener(e ->{
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
                    account = banker.createNewChildrenAccount(0,name_text.getText(),client,client.getMyAccounts().searchAccount(s));
                    clientRepository.updateClient(client);
                    accountRepository.insertAccount(account,client.getClientId());
                    accountRepository.updateAccount(account);
                    updateBankerPanel();
                    dialog.setVisible(false);
                }
            });
        } );
        viewApp.getCreateNewSavingMainBankerJButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Create New Saving");
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

            account = banker.createNewSaving(client);
            clientRepository.updateClient(client);
            accountRepository.insertAccount(account,client.getClientId());
            accountRepository.updateAccount(account);
            updateBankerPanel();

            button.addActionListener(e1 -> {
                dialog.setVisible(false);
            });
        });
        viewApp.getCreateNewChildrenSavingMainBankerJButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Create New Children Saving");
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

            account = banker.createChildrenSaving(client);
            clientRepository.updateClient(client);
            accountRepository.insertAccount(account,client.getClientId());
            accountRepository.updateAccount(account);
            updateBankerPanel();

            button.addActionListener(e1 -> {
                dialog.setVisible(false);
            });
        });
        viewApp.getEditClientInfoMainBankerJButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Create New Client");
            JLabel label_head = new JLabel("Edit Client Info::");
            JLabel fnameJL = new JLabel("First Name:");
            JLabel lnameJL = new JLabel("Last Name:");
            JLabel addressJL = new JLabel("Address:");
            JLabel incomeJL = new JLabel("Income:");
            JLabel passwordJL =new JLabel("Password:");
            JTextField fnameJT = new JTextField();
            JTextField lnameJT = new JTextField();
            JTextField addressJT = new JTextField();
            JTextField incomeJT = new JTextField();
            JTextField passwordJT = new JTextField();
            JButton button = new JButton();

            dialog.add(label_head);
            dialog.add(fnameJL);
            dialog.add(lnameJL);
            dialog.add(addressJL);
            dialog.add(incomeJL);
            dialog.add(passwordJL);
            dialog.add(fnameJT);
            dialog.add(lnameJT);
            dialog.add(addressJT);
            dialog.add(incomeJT);
            dialog.add(passwordJT);
            dialog.add(button);
            dialog.setLayout(null);

            label_head.setFont(label_head.getFont().deriveFont(20.0f));
            label_head.setBounds(10,10,500,30);

            fnameJL.setBounds(20,50,80,25);
            fnameJL.setHorizontalTextPosition(SwingConstants.CENTER);

            fnameJT.setBounds(110,50,300,25);

            lnameJL.setBounds(20,80,80,25);
            lnameJL.setHorizontalTextPosition(SwingConstants.CENTER);

            lnameJT.setBounds(110,80,300,25);

            addressJL.setBounds(20,110,80,25);
            addressJL.setHorizontalTextPosition(SwingConstants.CENTER);

            addressJT.setBounds(110,110,300,25);

            incomeJL.setBounds(20,140,80,25);
            incomeJL.setHorizontalTextPosition(SwingConstants.CENTER);

            incomeJT.setBounds(110,140,300,25);

            passwordJL.setBounds(20,170,80,25);
            passwordJL.setHorizontalTextPosition(SwingConstants.CENTER);

            passwordJT.setBounds(110,170,300,25);

            button.setBounds(210,200,80,30);
            button.setText("OK");


            dialog.setLocationByPlatform(true);
            dialog.setLocationRelativeTo(viewApp.getMainClientPanel());
            dialog.setSize(500,400);
            dialog.setVisible(true);

            validateTextField(incomeJT);

            button.addActionListener(e1 -> {
                if(!fnameJT.getText().equals("") && !lnameJT.getText().equals("") && !addressJT.getText().equals("") && !passwordJT.getText().equals("") && !incomeJT.getText().equals("")) {
                        banker.editClientInfo(client,fnameJT.getText(), lnameJT.getText(), addressJT.getText(), Integer.parseInt(incomeJT.getText()),passwordJT.getText());
                        clientRepository.updateClient(client);
                        dialog.setVisible(false);
                        updateBankerPanel();
                }
            });
        });
        viewApp.getTransferClientToClientMainBankerJButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Transfer money from client to client");
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
                    if(accountRepository.validateAccountId(jTextField_To.getText())){
                        Account account1 = accountRepository.createAccount(accountRepository.searchAccountId(jTextField_To.getText()));
                        if (banker.transferClientToClient(account,account1,Integer.parseInt(jTextField.getText()))) {
                            accountRepository.updateAccount(account);
                            accountRepository.updateAccount(account1);
                            client=clientRepository.createClient(clientRepository.searchClientId(client.getClientId()));
                            dialog.setVisible(false);
                            updateBankerPanel();
                        } else {
                            label_error.setVisible(true);
                            invalidThread(label_error);
                        }
                    }
                    else {
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    }
                }
            });
        });
        viewApp.getDepositMoneyMainBankerJButton().addActionListener(e -> {
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
                    banker.depositClientMoney(client,account,(Integer.parseInt(jTextField.getText())));
                    clientRepository.updateClient(client);
                    accountRepository.updateAccount(account);
                    dialog.setVisible(false);
                    updateBankerPanel();
                }
            });
        });
        viewApp.getWithdrawMoneyMainBankerJButton().addActionListener(e -> {
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
                    if(banker.withdrawClientCash(client,account,(Integer.parseInt(jTextField.getText())))) {
                        accountRepository.updateAccount(account);
                        dialog.setVisible(false);
                        updateBankerPanel();
                    }
                    else{
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    }
                }
            });
        });
        viewApp.getCreateNewBankerMainBankerJButton().addActionListener(e -> {
            JDialog dialog = new JDialog(f,"Create New Banker");
            JLabel label_error = new JLabel("Were sorry, something went wrong...");
            JLabel label_head = new JLabel("Create Banker:");
            JLabel bankerIdJL = new JLabel("Banker ID:");
            JLabel fnameJL = new JLabel("First Name:");
            JLabel lnameJL = new JLabel("Last Name:");
            JLabel addressJL = new JLabel("Address:");
            JLabel usernameJL = new JLabel("Username:");
            JLabel passwordJL =new JLabel("Password:");
            JTextField bankerIdJT = new JTextField();
            JTextField fnameJT = new JTextField();
            JTextField lnameJT = new JTextField();
            JTextField addressJT = new JTextField();
            JTextField usernameJT = new JTextField();
            JTextField passwordJT = new JTextField();

            JButton button = new JButton();
            dialog.add(label_head);
            dialog.add(label_error);
            dialog.add(bankerIdJL);
            dialog.add(fnameJL);
            dialog.add(lnameJL);
            dialog.add(addressJL);
            dialog.add(usernameJL);
            dialog.add(passwordJL);
            dialog.add(bankerIdJT);
            dialog.add(fnameJT);
            dialog.add(lnameJT);
            dialog.add(addressJT);
            dialog.add(usernameJT);
            dialog.add(passwordJT);
            dialog.add(button);
            dialog.setLayout(null);

            label_head.setFont(label_head.getFont().deriveFont(20.0f));
            label_head.setBounds(10,10,500,30);

            label_error.setForeground(Color.red);
            label_error.setVisible(false);

            bankerIdJL.setBounds(20,50,80,25);
            bankerIdJL.setHorizontalTextPosition(SwingConstants.CENTER);

            bankerIdJT.setBounds(110,50,300,25);

            fnameJL.setBounds(20,90,80,25);
            fnameJL.setHorizontalTextPosition(SwingConstants.CENTER);

            fnameJT.setBounds(110,90,300,25);

            lnameJL.setBounds(20,130,80,25);
            lnameJL.setHorizontalTextPosition(SwingConstants.CENTER);

            lnameJT.setBounds(110,130,300,25);

            addressJL.setBounds(20,170,80,25);
            addressJL.setHorizontalTextPosition(SwingConstants.CENTER);

            addressJT.setBounds(110,170,300,25);

            usernameJL.setBounds(20,200,80,25);
            usernameJL.setHorizontalTextPosition(SwingConstants.CENTER);

            usernameJT.setBounds(110,200,300,25);

            passwordJL.setBounds(20,250,80,25);
            passwordJL.setHorizontalTextPosition(SwingConstants.CENTER);

            passwordJT.setBounds(110,250,300,25);

            label_error.setBounds(150,250,300,25);
            label_error.setHorizontalTextPosition(SwingConstants.CENTER);

            button.setBounds(210,280,80,30);
            button.setText("OK");


            dialog.setLocationByPlatform(true);
            dialog.setLocationRelativeTo(viewApp.getMainClientPanel());
            dialog.setSize(500,475);
            dialog.setVisible(true);

            validateTextField(bankerIdJT);


            button.addActionListener(e1 -> {
                if(!fnameJT.getText().equals("") && !lnameJT.getText().equals("") && !addressJT.getText().equals("") && !usernameJT.getText().equals("") && !passwordJT.getText().equals("") && !bankerIdJT.getText().equals("")) {
                    if (bankerRepository.validateBankerUsername(usernameJT.getText()) || bankerRepository.validateBankerId(Integer.parseInt(bankerIdJT.getText()))) {
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    } else {
                        if(banker instanceof BankManager) {
                            Banker banker1 = ((BankManager) banker).createNewBanker(lnameJT.getText(), fnameJT.getText(), addressJT.getText(), usernameJT.getText(), passwordJT.getText(),Integer.parseInt(bankerIdJT.getText()));
                            bankerRepository.insertBanker(banker1);
                            dialog.setVisible(false);
                            updateBankerPanel();
                        }
                    }
                }
            });

        });
        viewApp.getAskForMoneyMainBankerJButton().addActionListener(e -> {
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
                    Account account1 = client.getMyAccounts().searchAccount(((ChildrenAccount) account).getParentId());
                    if (((ChildrenAccount) account).askForMoney(account1, Integer.parseInt(jTextField.getText().toString()))) {
                        accountRepository.updateAccount(account);
                        accountRepository.updateAccount(account1);
                        client = clientRepository.createClient(clientRepository.searchClientId(client.getClientId()));
                        dialog.setVisible(false);
                        updateBankerPanel();
                    }
                    else {
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    }
                }
            });
        });
        viewApp.getSaveMoneyMainBankerJButton().addActionListener(e -> {
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
                        accountRepository.updateAccount(account);
                        dialog.setVisible(false);
                        updateBankerPanel();
                    }
                    else{
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    }

                }
            });
        });
        viewApp.getBreakSavingMainBankerJButton().addActionListener(e -> {
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
                    Account account1 = client.getMyAccounts().searchAccount(jTextField.getText());
                    if(account1 instanceof ChildrenAccount) {
                        ((ChildrenSaving) account).brakeSaving((ChildrenAccount) (client.getMyAccounts().searchAccount(jTextField.getText())));
                        accountRepository.updateAccount(account1);
                        accountRepository.updateAccount(account);
                        dialog.setVisible(false);
                        updateBankerPanel();
                    }
                    else {
                        label_error.setVisible(true);
                        invalidThread(label_error);
                    }
                }
            });
        });
    }

    public void updateBankerPanel(){
        viewApp.getClientIdMainBankerJLabel().setVisible(true);
        viewApp.getClientIdMainBankerTextJLabel().setVisible(true);
        viewApp.getClientIdMainBankerTextJLabel().setText(client.getClientId()+"");
        viewApp.getClientNameMainBankerJLabel().setVisible(true);
        viewApp.getClientNameMainBankerTextJLabel().setVisible(true);
        viewApp.getClientNameMainBankerTextJLabel().setText(client.getFirstName() + " " + client.getLastName());
        viewApp.getClientAddressMainBankerJLabel().setVisible(true);
        viewApp.getClientAddressMainBankerTextJLabel().setVisible(true);
        viewApp.getClientAddressMainBankerTextJLabel().setText(client.getAddress());
        viewApp.getClientIncomeMainBankerJLabel().setVisible(true);
        viewApp.getClientIncomeMainBankerTextJLabel().setVisible(true);
        viewApp.getClientIncomeMainBankerTextJLabel().setText(client.getIncome() + "$");

        viewApp.getAccountTypeMainBankerJLabel().setVisible(true);
        viewApp.getAccountTypeMainBankerTextJLabel().setVisible(true);
        viewApp.getAccountIdMainBankerJLabel().setVisible(true);
        viewApp.getAccountIdMainBankerTextJLabel().setVisible(true);
        viewApp.getAccountIdMainBankerTextJLabel().setText(account.getAccountId());
        viewApp.getAccountBalanceMainBankerJLabel().setVisible(true);
        viewApp.getAccountBalanceMainBankerTextJLabel().setVisible(true);
        viewApp.getAccountBalanceMainBankerTextJLabel().setText(account.getBalance() + "$");

        viewApp.getSwitchAccountsIconMainBankerJLabel().setVisible(true);
        viewApp.getCreateNewAccountMainBankerJButton().setVisible(true);
        viewApp.getCreateNewChildrenAccountMainBankerJButton().setVisible(true);
        viewApp.getCreateNewSavingMainBankerJButton().setVisible(true);
        viewApp.getCreateNewChildrenSavingMainBankerJButton().setVisible(true);
        viewApp.getEditClientInfoMainBankerJButton().setVisible(true);
        viewApp.getTransferClientToClientMainBankerJButton().setVisible(true);
        viewApp.getDepositMoneyMainBankerJButton().setVisible(true);
        viewApp.getWithdrawMoneyMainBankerJButton().setVisible(true);
        viewApp.getSwitchAccountMainBankerJComboBox().setVisible(true);
        viewApp.getSelectAccountsMainBankerJButton().setVisible(true);
        viewApp.getSwitchAccountsMainBankerJLabel().setVisible(true);

        if(banker instanceof BankManager){
            viewApp.getCreateNewBankerMainBankerJButton().setVisible(true);

        }

        if(account instanceof ChildrenAccount) {
            viewApp.getAccountTypeMainBankerTextJLabel().setText("Children Account");
            viewApp.getAskForMoneyMainBankerJButton().setVisible(true);
            viewApp.getSaveMoneyMainBankerJButton().setVisible(false);
            viewApp.getBreakSavingMainBankerJButton().setVisible(false);
            viewApp.getAccountSavedMoneyMainBankerJLabel().setVisible(false);
            viewApp.getAccountSavedMoneyMainBankerTextJLabel().setVisible(false);
            }
            else if(account instanceof ChildrenSaving){
                viewApp.getAccountTypeMainBankerTextJLabel().setText("Children Saving");
                viewApp.getAskForMoneyMainBankerJButton().setVisible(false);
                viewApp.getSaveMoneyMainBankerJButton().setVisible(true);
                viewApp.getBreakSavingMainBankerJButton().setVisible(true);
                viewApp.getAccountSavedMoneyMainBankerJLabel().setVisible(true);
                viewApp.getAccountSavedMoneyMainBankerTextJLabel().setVisible(true);
                viewApp.getSavingClientMainNumberJLabel().setText(((ChildrenSaving) account).getSaved_money()+"$");
                viewApp.getAccountSavedMoneyMainBankerTextJLabel().setText(((ChildrenSaving) account).getSaved_money()+"$");
            }
            else if(account instanceof Saving){
                viewApp.getAccountTypeMainBankerTextJLabel().setText("Saving");
                viewApp.getAskForMoneyMainBankerJButton().setVisible(false);
                viewApp.getSaveMoneyMainBankerJButton().setVisible(true);
                viewApp.getBreakSavingMainBankerJButton().setVisible(false);
                viewApp.getAccountSavedMoneyMainBankerJLabel().setVisible(true);
                viewApp.getAccountSavedMoneyMainBankerTextJLabel().setVisible(true);
                viewApp.getSavingClientMainNumberJLabel().setText(((Saving) account).getSaved_money()+"$");
                viewApp.getAccountSavedMoneyMainBankerTextJLabel().setText(((ChildrenSaving) account).getSaved_money()+"$$");
            }
            else{
                viewApp.getAccountTypeMainBankerTextJLabel().setText("Regular Account");
                viewApp.getAskForMoneyMainBankerJButton().setVisible(false);
                viewApp.getSaveMoneyMainBankerJButton().setVisible(false);
                viewApp.getBreakSavingMainBankerJButton().setVisible(false);
                viewApp.getAccountSavedMoneyMainBankerJLabel().setVisible(false);
                viewApp.getAccountSavedMoneyMainBankerTextJLabel().setVisible(false);
            }
        viewApp.getSwitchAccountMainBankerJComboBox().removeAllItems();
        for (String s:client.getMyAccounts().getList().keySet()) {
            viewApp.getSwitchAccountMainBankerJComboBox().addItem(s);
        }
    }

    public void switchAccountInBanker() {
        String selected_item = viewApp.getSwitchAccountMainBankerJComboBox().getSelectedItem().toString();
        account = client.getMyAccounts().getList().get(selected_item);
        this.updateBankerPanel();

    }
}
