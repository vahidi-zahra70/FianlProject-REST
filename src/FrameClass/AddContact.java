package FrameClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JavaClass.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;

public class AddContact extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					AddContact frame = new AddContact();
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
	public AddContact(String serverURL) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Save Contacts");

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(79, 33, 80, 22);
		contentPane.add(lblName);

		JLabel lblFamily = new JLabel("Family:");
		lblFamily.setBounds(79, 78, 80, 14);
		contentPane.add(lblFamily);

		JLabel lblHomePhone = new JLabel("Home Phone:");
		lblHomePhone.setBounds(79, 127, 80, 14);
		contentPane.add(lblHomePhone);

		JLabel lblCellPhone = new JLabel("Cell Phone:");
		lblCellPhone.setBounds(79, 174, 80, 14);
		contentPane.add(lblCellPhone);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(79, 220, 80, 14);
		contentPane.add(lblEmail);

		textField = new JTextField();
		textField.setBounds(178, 33, 150, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(178, 78, 150, 22);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(178, 127, 150, 22);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(178, 174, 150, 22);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(178, 220, 150, 22);
		contentPane.add(textField_4);


		//Save a new contact
		JButton btnSaveContact = new JButton("Save the Contact");
		btnSaveContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String name=textField.getText();
					String family=textField_1.getText();
					long homephone=Long.parseLong(textField_2.getText());
					long cellphone=Long.parseLong(textField_3.getText());
					String email=textField_4.getText();
					
					
					if(textField.getText().equals("") || textField_1.getText().equals("")
							||textField_2.getText().equals("") || textField_3.getText().equals("")
							|| textField_4.getText().equals("")){
						JOptionPane.showMessageDialog(contentPane, "Please insert  all the fields for adding a new Contact!");
					}
					else{
						Client client=new Client(serverURL);
						client.saveContact(name, family, homephone, cellphone, email);
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
					}
				}
				catch(NumberFormatException e2){
					System.out.println(e2.getMessage());
					//e2.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, "Please insert"
							+ "  numbers as phone!");
				}
			}
		});
		btnSaveContact.setBounds(158, 272, 150, 30);
		contentPane.add(btnSaveContact);
	}
}
