package view;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;



public class ViewApp extends JFrame {

	


	private JPanel homePage = new JPanel();
	private JTextField myTextField;

    public ViewApp() {
        setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 500);
        this.getContentPane().setBackground(new Color(229, 242, 255));
        this.getContentPane().setLayout(new CardLayout(0, 0));
       

        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        
        getContentPane().add(homePage, "name_673367547882700");
        homePage.setLayout(null);
        
        myTextField = new JTextField();
        myTextField.setBounds(206, 212, 116, 22);
        homePage.add(myTextField);
        myTextField.setColumns(10);
        

        

        

    }
    
	
	public JPanel getHomePage() {
		return homePage;
	}

	public JTextField getMyTextField() {
		return myTextField;
	}
    
    
}