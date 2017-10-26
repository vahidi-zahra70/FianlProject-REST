package FrameClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import HibernateManager.ContactManager;
import JavaClass.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Choose extends JFrame {

	private JPanel contentPane;
	AddContact addContact;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Choose frame = new Choose();
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
	public Choose(String serverURL) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Welcome");
		
		
		
		//Add Contact
		JButton btnNewButton = new JButton("Add new Contact");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddContact addContact = new AddContact(serverURL);
				addContact.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(55, 108, 150, 23);
		contentPane.add(btnNewButton);
		
		
		//See phone Book
		JButton btnSeePhoneBook = new JButton("See Phone Book");
		btnSeePhoneBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		btnSeePhoneBook.setBounds(242, 108, 150, 23);
		contentPane.add(btnSeePhoneBook);
	}
}
