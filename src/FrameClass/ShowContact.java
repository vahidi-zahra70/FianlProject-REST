package FrameClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class ShowContact extends JFrame {

	private JPanel contentPane;
	JTable table_2;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ShowContact frame = new ShowContact();
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
	public ShowContact(Object[][] data) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//Table of Contacts
		String[] columns = new String[] {
			"ID",	"Name", "Family","Home Phone", "Cell Phone","Email"  };
		
		
		model = new DefaultTableModel(data,columns);
		table_2 = new JTable(model);
		table_2.setBounds(10, 10, 175, 200);

		//size of Contact table
		table_2.getColumnModel().getColumn(0).setPreferredWidth(400);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(400);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(400);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(700);
		table_2.getColumnModel().getColumn(4).setPreferredWidth(700);
		table_2.getColumnModel().getColumn(5).setPreferredWidth(700);
	

		JScrollPane	pane = new JScrollPane(table_2);
		pane.setBorder(BorderFactory.createTitledBorder ("Contacts"));
		pane.setSize(260, 550);
		pane.setLocation(73, 21);
		contentPane.add(pane);
	}

}
