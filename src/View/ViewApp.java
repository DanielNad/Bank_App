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
    private JPanel mainBankerPanel;
    private JButton switchAccountJLabel;

    public ViewApp() {
    	setBackground(new Color(255, 255, 255));
        setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 500);
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
        LoginLabel.setBounds(213, 62, 108, 60);
        LoginPanel.add(LoginLabel);
        
        UsernameEditText = new JTextField();
        UsernameEditText.setBounds(191, 170, 174, 22);
        LoginPanel.add(UsernameEditText);
        UsernameEditText.setColumns(10);
        
        JLabel UsernameLabel = new JLabel("Username:");
        UsernameLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\user.png"));
        UsernameLabel.setBounds(96, 173, 83, 16);
        LoginPanel.add(UsernameLabel);
        
        JLabel PasswordLabel = new JLabel("Password:");
        PasswordLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\password.png"));
        PasswordLabel.setBounds(96, 222, 83, 16);
        LoginPanel.add(PasswordLabel);
        
        LoginButton = new JButton("Login");
        LoginButton.setSelectedIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\loginsmall.png"));
        LoginButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\loginsmall.png"));
        LoginButton.setBounds(191, 338, 174, 25);
        LoginPanel.add(LoginButton);
        
        PasswordTextField = new JPasswordField();
        PasswordTextField.setBounds(191, 219, 174, 22);
        LoginPanel.add(PasswordTextField);
        
        ClassifyButton = new JRadioButton("Banker");
        buttonGroup.add(ClassifyButton);
        ClassifyButton.setBounds(276, 260, 83, 20);
        LoginPanel.add(ClassifyButton);
        
        ManagerCheck = new JRadioButton("Manager");
        buttonGroup.add(ManagerCheck);
        ManagerCheck.setBounds(191, 260, 81, 20);
        LoginPanel.add(ManagerCheck);
        
        InvalidUsername = new JLabel("Invalid Username or Password! Please insert a valid information!");
        InvalidUsername.setForeground(Color.RED);
        InvalidUsername.setHorizontalAlignment(SwingConstants.TRAILING);
        InvalidUsername.setBounds(65, 127, 377, 30);
        LoginPanel.add(InvalidUsername);
        
        ForgetPassword = new JButton("Forgot Password?");
        ForgetPassword.setBorder(BorderFactory.createEmptyBorder());
        ForgetPassword.setContentAreaFilled(false);
        ForgetPassword.setForeground(Color.BLUE);
        ForgetPassword.setBounds(191, 300, 174, 25);
        LoginPanel.add(ForgetPassword);
        
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(0, 250, 154));
        rightPanel.setBounds(464, 0, 530, 465);
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


        /////////////////////////////////////////////// Forget Password /////////////////////////////////////////////////


        ForgetPasswordPanel = new JPanel();
        ForgetPasswordPanel.setVisible(false);
        getContentPane().add(ForgetPasswordPanel, "name_189507056231900");
        ForgetPasswordPanel.setLayout(null);
        
        ForgetUsernameTextField = new JTextField();
        ForgetUsernameTextField.setBounds(443, 157, 163, 22);
        ForgetPasswordPanel.add(ForgetUsernameTextField);
        ForgetUsernameTextField.setColumns(10);
        
        ForgetPasswordField_1 = new JPasswordField();
        ForgetPasswordField_1.setBounds(443, 192, 163, 22);
        ForgetPasswordPanel.add(ForgetPasswordField_1);
        
        ForgetPasswordField_2 = new JPasswordField();
        ForgetPasswordField_2.setBounds(443, 227, 163, 22);
        ForgetPasswordPanel.add(ForgetPasswordField_2);
        
        JLabel ForgetUsernameJPanel = new JLabel("Username");
        ForgetUsernameJPanel.setHorizontalAlignment(SwingConstants.CENTER);
        ForgetUsernameJPanel.setBounds(290, 157, 127, 22);
        ForgetPasswordPanel.add(ForgetUsernameJPanel);
        
        JLabel NewPassword = new JLabel("New Password");
        NewPassword.setHorizontalAlignment(SwingConstants.CENTER);
        NewPassword.setBounds(290, 192, 127, 22);
        ForgetPasswordPanel.add(NewPassword);
        
        JLabel ReenterPassword = new JLabel("Re-Enter Password");
        ReenterPassword.setHorizontalAlignment(SwingConstants.CENTER);
        ReenterPassword.setBounds(290, 227, 127, 22);
        ForgetPasswordPanel.add(ReenterPassword);
        
        changePasswordButuon = new JButton("Change Password");
        changePasswordButuon.setBounds(443, 262, 163, 25);
        ForgetPasswordPanel.add(changePasswordButuon);
        
        JLabel lblNewLabel = new JLabel("Forget Password");
        lblNewLabel.setForeground(new Color(144, 238, 144));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
        lblNewLabel.setBounds(409, 83, 212, 35);
        ForgetPasswordPanel.add(lblNewLabel);
        
        ErrorPanel = new JLabel("Username does not exist!");
        ErrorPanel.setForeground(new Color(255, 0, 0));
        ErrorPanel.setHorizontalAlignment(SwingConstants.CENTER);
        ErrorPanel.setBounds(335, 128, 374, 16);
        ErrorPanel.setVisible(false);
        ForgetPasswordPanel.add(ErrorPanel);
        
        backButtonForgetPassword = new JButton("");
        backButtonForgetPassword.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\return.png"));
        backButtonForgetPassword.setBorder(BorderFactory.createEmptyBorder());
        backButtonForgetPassword.setContentAreaFilled(false);
        backButtonForgetPassword.setBounds(51, 0, 114, 71);
        ForgetPasswordPanel.add(backButtonForgetPassword);


        /////////////////////////////////////////////// Main Client Panel /////////////////////////////////////////////////


        mainClientPanel = new JPanel();
        getContentPane().add(mainClientPanel, "name_199217415361000");
        mainClientPanel.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 250, 154));
        panel.setBounds(731, 0, 263, 465);
        mainClientPanel.add(panel);
        panel.setLayout(null);
        
        clientHelloLabel = new JLabel("");
        clientHelloLabel.setForeground(new Color(244, 164, 96));
        clientHelloLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 26));
        clientHelloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientHelloLabel.setBounds(0, 13, 275, 46);
        panel.add(clientHelloLabel);
        
        depositCashClientButton = new JButton("");
        depositCashClientButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\withdraw.png"));
        depositCashClientButton.setBorder(BorderFactory.createEmptyBorder());
        depositCashClientButton.setContentAreaFilled(false);
        depositCashClientButton.setBounds(166, 106, 97, 73);
        panel.add(depositCashClientButton);
        
        withdrawCashClientButton = new JButton("");
        withdrawCashClientButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\saving-money.png"));
        withdrawCashClientButton.setBorder(BorderFactory.createEmptyBorder());
        withdrawCashClientButton.setContentAreaFilled(false);
        withdrawCashClientButton.setBounds(166, 178, 97, 73);
        panel.add(withdrawCashClientButton);
        
        transferClientToClientButton = new JButton("");
        transferClientToClientButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\save.png"));
        transferClientToClientButton.setBorder(BorderFactory.createEmptyBorder());
        transferClientToClientButton.setContentAreaFilled(false);
        transferClientToClientButton.setBounds(166, 250, 97, 73);
        panel.add(transferClientToClientButton);
        
        newClientAccountButton = new JButton("");
        newClientAccountButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\pig (4).png"));
        newClientAccountButton.setBorder(BorderFactory.createEmptyBorder());
        newClientAccountButton.setContentAreaFilled(false);
        newClientAccountButton.setBounds(166, 322, 97, 73);
        panel.add(newClientAccountButton);
        
        newChildrenAccountButton = new JButton("");
        newChildrenAccountButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\pig (3).png"));
        newChildrenAccountButton.setBorder(BorderFactory.createEmptyBorder());
        newChildrenAccountButton.setContentAreaFilled(false);
        newChildrenAccountButton.setBounds(166, 392, 97, 73);
        panel.add(newChildrenAccountButton);
        
        depositCashClientJLabel = new JLabel("Deposit Money");
        depositCashClientJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
        depositCashClientJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositCashClientJLabel.setBounds(12, 106, 150, 73);
        panel.add(depositCashClientJLabel);
        
        withdrawCashClientJLabel = new JLabel("Withdraw Money");
        withdrawCashClientJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawCashClientJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
        withdrawCashClientJLabel.setBounds(12, 183, 150, 68);
        panel.add(withdrawCashClientJLabel);
        
        transferClientToClientJLabel = new JLabel("Transfer Money");
        transferClientToClientJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        transferClientToClientJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
        transferClientToClientJLabel.setBounds(12, 250, 150, 73);
        panel.add(transferClientToClientJLabel);
        
        newAccountJLabel = new JLabel("New Account");
        newAccountJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        newAccountJLabel.setForeground(new Color(0, 0, 0));
        newAccountJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
        newAccountJLabel.setBounds(12, 322, 150, 73);
        panel.add(newAccountJLabel);
        
        newChildrenAccountJLabel = new JLabel("New Children Account");
        newChildrenAccountJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
        newChildrenAccountJLabel.setBounds(12, 392, 150, 73);
        panel.add(newChildrenAccountJLabel);
        
        balanceClientJLabel = new JLabel("Balance:");
        balanceClientJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceClientJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 40));
        balanceClientJLabel.setBounds(57, 136, 165, 56);
        mainClientPanel.add(balanceClientJLabel);
        
        balanceClientNumberJLabel = new JLabel("");
        balanceClientNumberJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 40));
        balanceClientNumberJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceClientNumberJLabel.setBounds(234, 136, 191, 56);
        mainClientPanel.add(balanceClientNumberJLabel);
        
        accountIdNumberJLabel = new JLabel("");
        accountIdNumberJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountIdNumberJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 40));
        accountIdNumberJLabel.setBounds(380, 24, 324, 56);
        mainClientPanel.add(accountIdNumberJLabel);
        
        accountIdClientJLabel = new JLabel("Account ID:");
        accountIdClientJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountIdClientJLabel.setFont(new Font("Kristen ITC", Font.PLAIN, 40));
        accountIdClientJLabel.setBounds(114, 24, 254, 56);
        mainClientPanel.add(accountIdClientJLabel);
        
        selectAccountComboBox = new JComboBox();
        selectAccountComboBox.setToolTipText("Select Account");
        selectAccountComboBox.setBounds(439, 233, 263, 31);
        mainClientPanel.add(selectAccountComboBox);

        switchAccountJLabel = new JButton("Switch Account");
        switchAccountJLabel.setBounds(291, 233, 135, 25);
        mainClientPanel.add(switchAccountJLabel);


        /////////////////////////////////////////////// Main Banker Panel /////////////////////////////////////////////////



        mainBankerPanel = new JPanel();
        getContentPane().add(mainBankerPanel, "name_199232667698700");
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
        return mainBankerPanel;
    }

    public void setMainBankerPanel(JPanel mainBankerPanel) {
        this.mainBankerPanel = mainBankerPanel;
    }

    public JButton getSwitchAccountJLabel() {
        return switchAccountJLabel;
    }

    public void setSwitchAccountJLabel(JButton switchAccountJLabel) {
        this.switchAccountJLabel = switchAccountJLabel;
    }
}