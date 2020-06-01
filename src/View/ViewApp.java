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

    public ViewApp() {
        setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 500);
        this.getContentPane().setBackground(new Color(229, 242, 255));
        this.getContentPane().setLayout(new CardLayout(0, 0));
       

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        LoginPanel.setBackground(new Color(135, 206, 235));
        
        getContentPane().add(LoginPanel, "name_673367547882700");
        LoginPanel.setLayout(null);
        
        JLabel LoginLabel = new JLabel("Login");
        LoginLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 35));
        LoginLabel.setBounds(410, 69, 108, 60);
        LoginPanel.add(LoginLabel);
        
        UsernameEditText = new JTextField();
        UsernameEditText.setBounds(388, 177, 174, 22);
        LoginPanel.add(UsernameEditText);
        UsernameEditText.setColumns(10);
        
        JLabel UsernameLabel = new JLabel("Username:");
        UsernameLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\user.png"));
        UsernameLabel.setBounds(293, 180, 83, 16);
        LoginPanel.add(UsernameLabel);
        
        JLabel PasswordLabel = new JLabel("Password:");
        PasswordLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\password.png"));
        PasswordLabel.setBounds(293, 229, 83, 16);
        LoginPanel.add(PasswordLabel);
        
        LoginButton = new JButton("Login");
        LoginButton.setSelectedIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\loginsmall.png"));
        LoginButton.setIcon(new ImageIcon("C:\\Users\\Administrator\\Documents\\Projects\\Bank_App\\pics\\loginsmall.png"));
        LoginButton.setBounds(388, 306, 174, 25);
        LoginPanel.add(LoginButton);
        
        PasswordTextField = new JPasswordField();
        PasswordTextField.setBounds(388, 226, 174, 22);
        LoginPanel.add(PasswordTextField);
        
        ClassifyButton = new JRadioButton("Banker");
        buttonGroup.add(ClassifyButton);
        ClassifyButton.setBounds(479, 255, 83, 20);
        LoginPanel.add(ClassifyButton);
        
        ManagerCheck = new JRadioButton("Manager");
        buttonGroup.add(ManagerCheck);
        ManagerCheck.setBounds(388, 255, 81, 20);
        LoginPanel.add(ManagerCheck);
        
        InvalidUsername = new JLabel("Invalid Username or Password! Please insert a valid information!");
        InvalidUsername.setForeground(Color.RED);
        InvalidUsername.setHorizontalAlignment(SwingConstants.TRAILING);
        InvalidUsername.setBounds(262, 134, 377, 30);
        LoginPanel.add(InvalidUsername);
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
}