package View;
import javax.swing.*;
import java.awt.*;


public class ViewApp extends JFrame {

    private JPanel LoginPanel = new JPanel();
    private JTextField UsernameEditText;
    private JPasswordField PasswordTextField;
    private JButton LoginButton;
    private JRadioButton ClassifyButton;
    private JLabel InvalidUsername;
    private JRadioButton ManagerCheck;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JButton ForgetPassword;
    private JPanel ForgetPasswordPanel;
    private JTextField ForgetUsernameTextField;
    private JPasswordField ForgetPasswordField_1;
    private JPasswordField ForgetPasswordField_2;
    private JButton changePasswordButuon;
    private JLabel ErrorPanel;
    private JButton backButtonForgetPassword;
    private JPanel rightPanel;
    private JLabel PiggyBank;
    private JLabel Logo;
    private JButton withdrawCashClientButton;
    private JButton transferClientToClientButton;
    private JButton newClientAccountButton;
    private JButton newChildrenAccountButton;
    private JLabel depositCashClientJLabel;
    private JLabel withdrawCashClientJLabel;
    private JLabel transferClientToClientJLabel;
    private JLabel newAccountJLabel;
    private JLabel newChildrenAccountJLabel;
    private JLabel balanceClientJLabel;
    private JLabel balanceClientNumberJLabel;
    private JLabel accountIdNumberJLabel;
    private JLabel accountIdClientJLabel;
    private JComboBox selectAccountComboBox;
    private JLabel clientHelloLabel;
    private JPanel mainClientPanel;
    private JButton depositCashClientButton;
    private JPanel mainBankerJPanel;
    private JLabel switchIconJLabel;
    private JLabel accountTypeClientJLabel;
    private JLabel accountIdTypeNameJLabel;
    private JButton backButtonClientMainJPanel;
    private JButton switchAccountJButton;
    private JButton askForMoneyJButton;
    private JButton breakeSavingJButton;
    private JButton saveMoneyJButton;
    private JLabel savingClientMainJPanel;
    private JLabel savingClientMainNumberJLabel;
    private JLabel rightsJLabel;
    private JLabel rightsJLabelMainClientJPanel;
    private JSeparator separator_Last;
    private JPanel rightMainBankerJPanel;
    private JLabel bankerHelloJLabel;

    public ViewApp() {
        setBackground(new Color(255, 255, 255));
        setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 573);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.getContentPane().setLayout(new CardLayout(0, 0));


        /////////////////////////////////////////////// Login /////////////////////////////////////////////////


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        LoginPanel.setBackground(UIManager.getColor("Button.background"));

        getContentPane().add(LoginPanel, "name_673367547882700");
        LoginPanel.setLayout(null);
        JLabel LoginLabel = new JLabel("Login");
        LoginLabel.setForeground(new Color(144, 238, 144));
        LoginLabel.setBackground(new Color(144, 238, 144));
        LoginLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 35));
        LoginLabel.setBounds(187, 83, 108, 60);
        LoginPanel.add(LoginLabel);

        UsernameEditText = new JTextField();
        UsernameEditText.setBounds(165, 191, 174, 22);
        LoginPanel.add(UsernameEditText);
        UsernameEditText.setColumns(10);

        JLabel UsernameLabel = new JLabel("Username:");
        UsernameLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\user.png"));
        UsernameLabel.setBounds(70, 194, 83, 16);
        LoginPanel.add(UsernameLabel);

        JLabel PasswordLabel = new JLabel("Password:");
        PasswordLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\password.png"));
        PasswordLabel.setBounds(70, 243, 83, 16);
        LoginPanel.add(PasswordLabel);

        LoginButton = new JButton("Login");
        LoginButton.setSelectedIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\loginsmall.png"));
        LoginButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\loginsmall.png"));
        LoginButton.setBounds(165, 359, 174, 25);
        LoginPanel.add(LoginButton);

        PasswordTextField = new JPasswordField();
        PasswordTextField.setBounds(165, 240, 174, 22);
        LoginPanel.add(PasswordTextField);

        ClassifyButton = new JRadioButton("Banker");
        buttonGroup.add(ClassifyButton);
        ClassifyButton.setBounds(250, 281, 83, 20);
        LoginPanel.add(ClassifyButton);

        ManagerCheck = new JRadioButton("Manager");
        buttonGroup.add(ManagerCheck);
        ManagerCheck.setBounds(165, 281, 81, 20);
        LoginPanel.add(ManagerCheck);

        InvalidUsername = new JLabel("Invalid Username or Password! Please insert a valid information!");
        InvalidUsername.setForeground(Color.RED);
        InvalidUsername.setHorizontalAlignment(SwingConstants.TRAILING);
        InvalidUsername.setBounds(39, 148, 377, 30);
        LoginPanel.add(InvalidUsername);

        ForgetPassword = new JButton("Forgot Password?");
        ForgetPassword.setBorder(BorderFactory.createEmptyBorder());
        ForgetPassword.setContentAreaFilled(false);
        ForgetPassword.setForeground(Color.BLUE);
        ForgetPassword.setBounds(165, 321, 174, 25);
        LoginPanel.add(ForgetPassword);

        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(0, 250, 154));
        rightPanel.setBounds(464, 0, 530, 538);
        LoginPanel.add(rightPanel);
        rightPanel.setLayout(null);

        PiggyBank = new JLabel("Piggy Bank");
        PiggyBank.setForeground(new Color(255, 127, 80));
        PiggyBank.setFont(new Font("Kristen ITC", Font.PLAIN, 40));
        PiggyBank.setHorizontalAlignment(SwingConstants.CENTER);
        PiggyBank.setBounds(150, 48, 265, 116);
        rightPanel.add(PiggyBank);

        Logo = new JLabel("");
        Logo.setHorizontalAlignment(SwingConstants.CENTER);
        Logo.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\pig (1).png"));
        Logo.setBounds(12, 145, 506, 320);
        rightPanel.add(Logo);
        
        rightsJLabel = new JLabel("\u00A9 All rights reserved to Piggy Bank");
        rightsJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightsJLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 15));
        rightsJLabel.setBounds(121, 397, 261, 21);
        LoginPanel.add(rightsJLabel);


        /////////////////////////////////////////////// Forget Password /////////////////////////////////////////////////


        ForgetPasswordPanel = new JPanel();
        ForgetPasswordPanel.setVisible(false);
        getContentPane().add(ForgetPasswordPanel, "name_189507056231900");
        ForgetPasswordPanel.setLayout(null);

        ForgetUsernameTextField = new JTextField();
        ForgetUsernameTextField.setBounds(412, 174, 163, 22);
        ForgetPasswordPanel.add(ForgetUsernameTextField);
        ForgetUsernameTextField.setColumns(10);

        ForgetPasswordField_1 = new JPasswordField();
        ForgetPasswordField_1.setBounds(412, 209, 163, 22);
        ForgetPasswordPanel.add(ForgetPasswordField_1);

        ForgetPasswordField_2 = new JPasswordField();
        ForgetPasswordField_2.setBounds(412, 244, 163, 22);
        ForgetPasswordPanel.add(ForgetPasswordField_2);

        JLabel ForgetUsernameJPanel = new JLabel("Username");
        ForgetUsernameJPanel.setHorizontalAlignment(SwingConstants.CENTER);
        ForgetUsernameJPanel.setBounds(259, 174, 127, 22);
        ForgetPasswordPanel.add(ForgetUsernameJPanel);

        JLabel NewPassword = new JLabel("New Password");
        NewPassword.setHorizontalAlignment(SwingConstants.CENTER);
        NewPassword.setBounds(259, 209, 127, 22);
        ForgetPasswordPanel.add(NewPassword);

        JLabel ReenterPassword = new JLabel("Re-Enter Password");
        ReenterPassword.setHorizontalAlignment(SwingConstants.CENTER);
        ReenterPassword.setBounds(259, 244, 127, 22);
        ForgetPasswordPanel.add(ReenterPassword);

        changePasswordButuon = new JButton("Change Password");
        changePasswordButuon.setBounds(412, 279, 163, 25);
        ForgetPasswordPanel.add(changePasswordButuon);

        JLabel lblNewLabel = new JLabel("Forget Password");
        lblNewLabel.setForeground(new Color(144, 238, 144));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
        lblNewLabel.setBounds(378, 100, 212, 35);
        ForgetPasswordPanel.add(lblNewLabel);

        ErrorPanel = new JLabel("Username does not exist!");
        ErrorPanel.setForeground(new Color(255, 0, 0));
        ErrorPanel.setHorizontalAlignment(SwingConstants.CENTER);
        ErrorPanel.setBounds(304, 145, 374, 16);
        ErrorPanel.setVisible(false);
        ForgetPasswordPanel.add(ErrorPanel);

        backButtonForgetPassword = new JButton("");
        backButtonForgetPassword.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\return.png"));
        backButtonForgetPassword.setBorder(BorderFactory.createEmptyBorder());
        backButtonForgetPassword.setContentAreaFilled(false);
        backButtonForgetPassword.setBounds(12, 13, 114, 71);
        ForgetPasswordPanel.add(backButtonForgetPassword);


        /////////////////////////////////////////////// Main Client Panel /////////////////////////////////////////////////


        mainClientPanel = new JPanel();
        getContentPane().add(mainClientPanel, "name_199217415361000");
        mainClientPanel.setLayout(null);

        JPanel rightMainClientJPanel = new JPanel();
        rightMainClientJPanel.setBackground(new Color(0, 250, 154));
        rightMainClientJPanel.setBounds(731, 0, 263, 538);
        mainClientPanel.add(rightMainClientJPanel);
        rightMainClientJPanel.setLayout(null);

        clientHelloLabel = new JLabel("");
        clientHelloLabel.setForeground(new Color(255, 127, 80));
        clientHelloLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 26));
        clientHelloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientHelloLabel.setBounds(0, 0, 263, 90);
        rightMainClientJPanel.add(clientHelloLabel);

        depositCashClientButton = new JButton("Deposit Money");
        depositCashClientButton.setBounds(10, 120, 157, 30);
        rightMainClientJPanel.add(depositCashClientButton);

        withdrawCashClientButton = new JButton("Withdraw Money");
        withdrawCashClientButton.setBounds(100, 210, 157, 30);
        rightMainClientJPanel.add(withdrawCashClientButton);

        transferClientToClientButton = new JButton("Transfer Money");
        transferClientToClientButton.setBounds(10, 300, 157, 30);
        rightMainClientJPanel.add(transferClientToClientButton);

        newClientAccountButton = new JButton("New Account");
        newClientAccountButton.setBounds(100, 390, 157, 30);
        rightMainClientJPanel.add(newClientAccountButton);

        newChildrenAccountButton = new JButton("New Children Account");
        newChildrenAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        newChildrenAccountButton.setBounds(10, 477, 157, 30);
        rightMainClientJPanel.add(newChildrenAccountButton);

        depositCashClientJLabel = new JLabel("");
        depositCashClientJLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\withdraw.png"));
        depositCashClientJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
        depositCashClientJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositCashClientJLabel.setBounds(177, 105, 65, 60);
        rightMainClientJPanel.add(depositCashClientJLabel);

        withdrawCashClientJLabel = new JLabel("");
        withdrawCashClientJLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\saving-money.png"));
        withdrawCashClientJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawCashClientJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
        withdrawCashClientJLabel.setBounds(10, 192, 65, 66);
        rightMainClientJPanel.add(withdrawCashClientJLabel);

        transferClientToClientJLabel = new JLabel("");
        transferClientToClientJLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\save.png"));
        transferClientToClientJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        transferClientToClientJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
        transferClientToClientJLabel.setBounds(177, 282, 65, 66);
        rightMainClientJPanel.add(transferClientToClientJLabel);

        newAccountJLabel = new JLabel("");
        newAccountJLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\pig (4).png"));
        newAccountJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        newAccountJLabel.setForeground(new Color(0, 0, 0));
        newAccountJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
        newAccountJLabel.setBounds(10, 372, 65, 65);
        rightMainClientJPanel.add(newAccountJLabel);

        newChildrenAccountJLabel = new JLabel("");
        newChildrenAccountJLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\pig (3).png"));
        newChildrenAccountJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
        newChildrenAccountJLabel.setBounds(177, 462, 65, 66);
        rightMainClientJPanel.add(newChildrenAccountJLabel);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(Color.LIGHT_GRAY);
        separator_1.setBackground(Color.LIGHT_GRAY);
        separator_1.setBounds(0, 90, 263, 2);
        rightMainClientJPanel.add(separator_1);

        JSeparator separator_2 = new JSeparator();
        separator_2.setForeground(Color.LIGHT_GRAY);
        separator_2.setBackground(Color.LIGHT_GRAY);
        separator_2.setBounds(0, 180, 263, 2);
        rightMainClientJPanel.add(separator_2);

        JSeparator separator_3 = new JSeparator();
        separator_3.setForeground(Color.LIGHT_GRAY);
        separator_3.setBackground(Color.LIGHT_GRAY);
        separator_3.setBounds(0, 270, 263, 2);
        rightMainClientJPanel.add(separator_3);

        JSeparator separator_4 = new JSeparator();
        separator_4.setForeground(Color.LIGHT_GRAY);
        separator_4.setBackground(Color.LIGHT_GRAY);
        separator_4.setBounds(0, 360, 263, 2);
        rightMainClientJPanel.add(separator_4);

        JSeparator separator_5 = new JSeparator();
        separator_5.setForeground(Color.LIGHT_GRAY);
        separator_5.setBackground(Color.LIGHT_GRAY);
        separator_5.setBounds(0, 450, 263, 2);
        rightMainClientJPanel.add(separator_5);

        balanceClientJLabel = new JLabel("Balance:");
        balanceClientJLabel.setHorizontalAlignment(SwingConstants.LEFT);
        balanceClientJLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 39));
        balanceClientJLabel.setBounds(12, 197, 165, 56);
        mainClientPanel.add(balanceClientJLabel);

        balanceClientNumberJLabel = new JLabel("");
        balanceClientNumberJLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 39));
        balanceClientNumberJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        balanceClientNumberJLabel.setBounds(170, 197, 549, 56);
        mainClientPanel.add(balanceClientNumberJLabel);

        accountIdNumberJLabel = new JLabel("");
        accountIdNumberJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        accountIdNumberJLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 39));
        accountIdNumberJLabel.setBounds(230, 107, 489, 56);
        mainClientPanel.add(accountIdNumberJLabel);

        accountIdClientJLabel = new JLabel("Account ID:");
        accountIdClientJLabel.setHorizontalAlignment(SwingConstants.LEFT);
        accountIdClientJLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 39));
        accountIdClientJLabel.setBounds(10, 107, 208, 56);
        mainClientPanel.add(accountIdClientJLabel);

        selectAccountComboBox = new JComboBox();
        selectAccountComboBox.setToolTipText("Select Account");
        selectAccountComboBox.setBounds(517, 49, 202, 30);
        mainClientPanel.add(selectAccountComboBox);

        backButtonClientMainJPanel = new JButton("");
        backButtonClientMainJPanel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\return.png"));
        backButtonClientMainJPanel.setContentAreaFilled(false);
        backButtonClientMainJPanel.setBorder(BorderFactory.createEmptyBorder());
        backButtonClientMainJPanel.setBounds(0, 0, 85, 65);
        mainClientPanel.add(backButtonClientMainJPanel);

        JLabel switchAccountJLabel = new JLabel("Switch Accounts");
        switchAccountJLabel.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 19));
        switchAccountJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        switchAccountJLabel.setBounds(557, 9, 162, 27);
        mainClientPanel.add(switchAccountJLabel);

        switchIconJLabel = new JLabel("");
        switchIconJLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\switch.jpeg"));
        switchIconJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        switchIconJLabel.setBounds(521, 12, 24, 24);
        mainClientPanel.add(switchIconJLabel);

        accountTypeClientJLabel = new JLabel("Account Type:");
        accountTypeClientJLabel.setHorizontalAlignment(SwingConstants.LEFT);
        accountTypeClientJLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 39));
        accountTypeClientJLabel.setBounds(12, 287, 295, 56);
        mainClientPanel.add(accountTypeClientJLabel);

        accountIdTypeNameJLabel = new JLabel("");
        accountIdTypeNameJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        accountIdTypeNameJLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 39));
        accountIdTypeNameJLabel.setBounds(264, 287, 455, 56);
        mainClientPanel.add(accountIdTypeNameJLabel);
        
        switchAccountJButton = new JButton("");
        switchAccountJButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\ok.png"));
        switchAccountJButton.setBounds(484, 49, 25, 31);
        switchAccountJButton.setBorder(BorderFactory.createEmptyBorder());
        switchAccountJButton.setContentAreaFilled(false);
        mainClientPanel.add(switchAccountJButton);
        
        askForMoneyJButton = new JButton("Ask For Money");
        askForMoneyJButton.setBounds(355, 49, 117, 30);
        mainClientPanel.add(askForMoneyJButton);
        
        breakeSavingJButton = new JButton("Break Saving");
        breakeSavingJButton.setBounds(226, 49, 117, 30);
        mainClientPanel.add(breakeSavingJButton);
        
        saveMoneyJButton = new JButton("Save Money");
        saveMoneyJButton.setBounds(97, 49, 117, 30);
        mainClientPanel.add(saveMoneyJButton);
        
        savingClientMainJPanel = new JLabel("Saving:");
        savingClientMainJPanel.setVisible(false);
        savingClientMainJPanel.setHorizontalAlignment(SwingConstants.LEFT);
        savingClientMainJPanel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 39));
        savingClientMainJPanel.setBounds(12, 377, 136, 56);
        mainClientPanel.add(savingClientMainJPanel);
        
        savingClientMainNumberJLabel = new JLabel("");
        savingClientMainNumberJLabel.setVisible(false);
        savingClientMainNumberJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        savingClientMainNumberJLabel.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 39));
        savingClientMainNumberJLabel.setBounds(160, 377, 559, 56);
        mainClientPanel.add(savingClientMainNumberJLabel);

        JSeparator separator_7 = new JSeparator();
        separator_7.setForeground(Color.LIGHT_GRAY);
        separator_7.setBackground(Color.LIGHT_GRAY);
        separator_7.setBounds(0, 180, 731, 2);
        mainClientPanel.add(separator_7);

        JSeparator separator_8 = new JSeparator();
        separator_8.setForeground(Color.LIGHT_GRAY);
        separator_8.setBackground(Color.LIGHT_GRAY);
        separator_8.setBounds(0, 270, 731, 5);
        mainClientPanel.add(separator_8);

        JSeparator separator_6 = new JSeparator();
        separator_6.setForeground(Color.LIGHT_GRAY);
        separator_6.setBackground(Color.LIGHT_GRAY);
        separator_6.setBounds(0, 90, 731, 5);
        mainClientPanel.add(separator_6);

        JSeparator separator_9 = new JSeparator();
        separator_9.setForeground(Color.LIGHT_GRAY);
        separator_9.setBackground(Color.LIGHT_GRAY);
        separator_9.setBounds(0, 360, 731, 5);
        mainClientPanel.add(separator_9);
        
        rightsJLabelMainClientJPanel = new JLabel("\u00A9 All rights reserved to Piggy Bank");
        rightsJLabelMainClientJPanel.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 15));
        rightsJLabelMainClientJPanel.setBounds(243, 504, 261, 21);
        mainClientPanel.add(rightsJLabelMainClientJPanel);
        
        separator_Last = new JSeparator();
        separator_Last.setForeground(Color.LIGHT_GRAY);
        separator_Last.setBackground(Color.LIGHT_GRAY);
        separator_Last.setBounds(0, 450, 731, 2);
        mainClientPanel.add(separator_Last);

        /////////////////////////////////////////////// Main Banker Panel /////////////////////////////////////////////////


        mainBankerJPanel = new JPanel();
        getContentPane().add(mainBankerJPanel, "name_199232667698700");
        mainBankerJPanel.setLayout(null);
        
        rightMainBankerJPanel = new JPanel();
        rightMainBankerJPanel.setBackground(new Color(0, 250, 154));
        rightMainBankerJPanel.setBounds(468, 0, 526, 538);
        mainBankerJPanel.add(rightMainBankerJPanel);
        rightMainBankerJPanel.setLayout(null);
        
        bankerHelloJLabel = new JLabel("");
        bankerHelloJLabel.setBounds(0, 0, 526, 90);
        bankerHelloJLabel.setForeground(new Color(255, 127, 80));
        bankerHelloJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 26));
        rightMainBankerJPanel.add(bankerHelloJLabel);
        InvalidUsername.setVisible(false);
    }

    public JPanel getLoginPanel() {
        return LoginPanel;
    }

    public void setLoginPanel(JPanel loginPanel) {
        LoginPanel = loginPanel;
    }

    public JTextField getUsernameEditText() {
        return UsernameEditText;
    }

    public void setUsernameEditText(JTextField usernameEditText) {
        UsernameEditText = usernameEditText;
    }

    public JPasswordField getPasswordTextField() {
        return PasswordTextField;
    }

    public void setPasswordTextField(JPasswordField passwordTextField) {
        PasswordTextField = passwordTextField;
    }

    public JButton getLoginButton() {
        return LoginButton;
    }

    public void setLoginButton(JButton loginButton) {
        LoginButton = loginButton;
    }

    public JRadioButton getClassifyButton() {
        return ClassifyButton;
    }

    public void setClassifyButton(JRadioButton classifyButton) {
        ClassifyButton = classifyButton;
    }

    public JLabel getInvalidUsername() {
        return InvalidUsername;
    }

    public void setInvalidUsername(JLabel invalidUsername) {
        InvalidUsername = invalidUsername;
    }

    public JRadioButton getManagerCheck() {
        return ManagerCheck;
    }

    public void setManagerCheck(JRadioButton managerCheck) {
        ManagerCheck = managerCheck;
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public JButton getForgetPassword() {
        return ForgetPassword;
    }

    public void setForgetPassword(JButton forgetPassword) {
        ForgetPassword = forgetPassword;
    }

    public JPanel getForgetPasswordPanel() {
        return ForgetPasswordPanel;
    }

    public void setForgetPasswordPanel(JPanel forgetPasswordPanel) {
        ForgetPasswordPanel = forgetPasswordPanel;
    }

    public JTextField getForgetUsernameTextField() {
        return ForgetUsernameTextField;
    }

    public void setForgetUsernameTextField(JTextField forgetUsernameTextField) {
        ForgetUsernameTextField = forgetUsernameTextField;
    }

    public JPasswordField getForgetPasswordField_1() {
        return ForgetPasswordField_1;
    }

    public void setForgetPasswordField_1(JPasswordField forgetPasswordField_1) {
        ForgetPasswordField_1 = forgetPasswordField_1;
    }

    public JPasswordField getForgetPasswordField_2() {
        return ForgetPasswordField_2;
    }

    public void setForgetPasswordField_2(JPasswordField forgetPasswordField_2) {
        ForgetPasswordField_2 = forgetPasswordField_2;
    }

    public JButton getChangePasswordButuon() {
        return changePasswordButuon;
    }

    public void setChangePasswordButuon(JButton changePasswordButuon) {
        this.changePasswordButuon = changePasswordButuon;
    }

    public JLabel getErrorPanel() {
        return ErrorPanel;
    }

    public void setErrorPanel(JLabel errorPanel) {
        ErrorPanel = errorPanel;
    }

    public JButton getBackButtonForgetPassword() {
        return backButtonForgetPassword;
    }

    public void setBackButtonForgetPassword(JButton backButtonForgetPassword) {
        this.backButtonForgetPassword = backButtonForgetPassword;
    }

    public JButton getWithdrawCashClientButton() {
        return withdrawCashClientButton;
    }

    public void setWithdrawCashClientButton(JButton withdrawCashClientButton) {
        this.withdrawCashClientButton = withdrawCashClientButton;
    }

    public JButton getTransferClientToClientButton() {
        return transferClientToClientButton;
    }

    public void setTransferClientToClientButton(JButton transferClientToClientButton) {
        this.transferClientToClientButton = transferClientToClientButton;
    }

    public JButton getNewClientAccountButton() {
        return newClientAccountButton;
    }

    public void setNewClientAccountButton(JButton newClientAccountButton) {
        this.newClientAccountButton = newClientAccountButton;
    }

    public JButton getNewChildrenAccountButton() {
        return newChildrenAccountButton;
    }

    public void setNewChildrenAccountButton(JButton newChildrenAccountButton) {
        this.newChildrenAccountButton = newChildrenAccountButton;
    }

    public JLabel getBalanceClientNumberJLabel() {
        return balanceClientNumberJLabel;
    }

    public void setBalanceClientNumberJLabel(JLabel balanceClientNumberJLabel) {
        this.balanceClientNumberJLabel = balanceClientNumberJLabel;
    }

    public JLabel getAccountIdNumberJLabel() {
        return accountIdNumberJLabel;
    }

    public void setAccountIdNumberJLabel(JLabel accountIdNumberJLabel) {
        this.accountIdNumberJLabel = accountIdNumberJLabel;
    }

    public JComboBox getSelectAccountComboBox() {
        return selectAccountComboBox;
    }

    public void setSelectAccountComboBox(JComboBox selectAccountComboBox) {
        this.selectAccountComboBox = selectAccountComboBox;
    }

    public JLabel getClientHelloLabel() {
        return clientHelloLabel;
    }

    public void setClientHelloLabel(JLabel clientHelloLabel) {
        this.clientHelloLabel = clientHelloLabel;
    }

    public JPanel getMainClientPanel() {
        return mainClientPanel;
    }

    public void setMainClientPanel(JPanel mainClientPanel) {
        this.mainClientPanel = mainClientPanel;
    }

    public JButton getDepositCashClientButton() {
        return depositCashClientButton;
    }

    public void setDepositCashClientButton(JButton depositCashClientButton) {
        this.depositCashClientButton = depositCashClientButton;
    }

    public JPanel getMainBankerPanel() {
        return mainBankerJPanel;
    }

    public void setMainBankerPanel(JPanel mainBankerPanel) {
        this.mainBankerJPanel = mainBankerPanel;
    }

    public JButton getBackButtonClientMainJPanel() {
        return backButtonClientMainJPanel;
    }

    public void setBackButtonClientMainJPanel(JButton backButtonClientMainJPanel) {
        this.backButtonClientMainJPanel = backButtonClientMainJPanel;
    }

    public JButton getSwitchAccountJButton() {
        return switchAccountJButton;
    }

    public void setSwitchAccountJButton(JButton switchAccountJButton) {
        this.switchAccountJButton = switchAccountJButton;
    }

    public JLabel getAccountIdTypeNameJLabel() {
        return accountIdTypeNameJLabel;
    }

    public void setAccountIdTypeNameJLabel(JLabel accountIdTypeNameJLabel) {
        this.accountIdTypeNameJLabel = accountIdTypeNameJLabel;
    }

    public JButton getAskForMoneyJButton() {
        return askForMoneyJButton;
    }

    public void setAskForMoneyJButton(JButton askForMoneyJButton) {
        this.askForMoneyJButton = askForMoneyJButton;
    }

    public JButton getBreakeSavingJButton() {
        return breakeSavingJButton;
    }

    public void setBreakeSavingJButton(JButton breakeSavingJButton) {
        this.breakeSavingJButton = breakeSavingJButton;
    }

    public JButton getSaveMoneyJButton() {
        return saveMoneyJButton;
    }

    public void setSaveMoneyJButton(JButton saveMoneyJButton) {
        this.saveMoneyJButton = saveMoneyJButton;
    }

    public JLabel getSavingClientMainNumberJLabel() {
        return savingClientMainNumberJLabel;
    }

    public void setSavingClientMainNumberJLabel(JLabel savingClientMainNumberJLabel) {
        this.savingClientMainNumberJLabel = savingClientMainNumberJLabel;
    }

    public JLabel getSavingClientMainJPanel() {
        return savingClientMainJPanel;
    }

    public void setSavingClientMainJPanel(JLabel savingClientMainJPanel) {
        this.savingClientMainJPanel = savingClientMainJPanel;
    }

    public JSeparator getSeparator_Last() {
        return separator_Last;
    }

    public void setSeparator_Last(JSeparator separator_Last) {
        this.separator_Last = separator_Last;
    }
}