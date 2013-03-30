
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JButton;


import javax.swing.SpringLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;


public class test1 extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test1 frame = new test1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test1() {
		getContentPane().setBackground(new Color(176, 224, 230));
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		setSize(460,300);
		
		JLabel lblUsername = new JLabel("Username");
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, 57, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.SOUTH, lblUsername, -20, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 57, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 196, SpringLayout.NORTH, getContentPane());
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 159, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 44, SpringLayout.EAST, lblUsername);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 179, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, 165, SpringLayout.EAST, lblUsername);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		springLayout.putConstraint(SpringLayout.EAST, btnLogin, 387, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnLogin, 210, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 313, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 159, SpringLayout.NORTH, getContentPane());
		getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, -3, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, passwordField, 17, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 121, SpringLayout.WEST, textField);
		getContentPane().add(passwordField);
		
	}
}
