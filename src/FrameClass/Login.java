package FrameClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import HibernateManager.ContactManager;
import JavaClass.Client;
import JavaClass.Contact;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	Choose choose;
	ShowContact showContact;
	Register register;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					Login frame = new Login();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserName = new JLabel("User name:");
		lblUserName.setBounds(88, 82, 100, 14);
		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(88, 128, 100, 14);
		contentPane.add(lblPassword);

		textField = new JTextField();
		textField.setBounds(189, 79, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(189, 122, 100, 20);
		contentPane.add(textField_1);


		//Login
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result;
				String serverURL=textField_2.getText();
				String username=textField.getText();
				String password=textField_1.getText();
				if(textField.getText().equals("") || textField_1.getText().equals("") 
						|| textField_2.getText().equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please insert the ServerURL,Username and Password for login!");
				}
				else{
					Client client=new Client(serverURL);
					result=client.ValidateUser(username, password);
					if(result.equals("not exist")){
						JOptionPane.showMessageDialog(contentPane, "Your username or pass is wrong");
					}
					else{
						String role;
						String [] results=result.split(" ");
						role=results[1];
						dispose();
						Choose choose = new Choose(serverURL);
						choose.setVisible(true);
						
					}

				}
			}
		});
		btnLogin.setBounds(158, 180, 89, 23);
		contentPane.add(btnLogin);



		//Register
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serverURL=textField_2.getText();
				if(textField_2.getText().equals("") ){
					JOptionPane.showMessageDialog(contentPane, "Please insert the ServerURL for registering!");
				}
				else{
					dispose();
					Register register = new Register(serverURL);
					register.setVisible(true);
				}

			}
		});
		btnRegister.setBounds(48, 221, 150, 23);
		contentPane.add(btnRegister);


		//Show Contacts
		JButton btnShowPhoneBook = new JButton("Show Phone Book");
		btnShowPhoneBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String serverURL=textField_2.getText();
				if(textField_2.getText().equals("") ){
					JOptionPane.showMessageDialog(contentPane, "Please insert the ServerURL for seeing hte phone Book!");

				}
				else{
					Client client=new Client(serverURL);
					String input=client.getContacts();
					ContactManager CC=new ContactManager();
					try{
						ShowContact showContact = new ShowContact(CC.ConvertJsonToContact(input));
						showContact.setVisible(true);
					}
					catch (SQLException e2) {
						e2.printStackTrace();
					}
				}

			}
		});
		btnShowPhoneBook.setBounds(251, 221, 150, 23);
		contentPane.add(btnShowPhoneBook);

		JLabel lblServerUrl = new JLabel("Server URL:");
		lblServerUrl.setBounds(48, 30, 80, 14);
		contentPane.add(lblServerUrl);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(131, 27, 260, 20);
		contentPane.add(textField_2);

	}


}
