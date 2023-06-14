package financeManagingSystem;

//import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class RecordsWindow implements ActionListener {

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new RecordsWindow();
			}
		});
	}

	JTable jt_incomeRecords, jt_expenseRecords, table;
	JLabel status;
	JFrame jfrm;
	JButton jb_clear, jb_back, jb_expense, jb_income;
	JScrollPane scrollPane1, scrollPane2;
	String[] colName, data;

	DefaultTableModel model;

	public RecordsWindow() {

		jfrm = new JFrame(" FinAdmin : FINANCIAL RECORDS ");
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setSize(430, 680);
		jfrm.setLayout(null);
		jfrm.getContentPane().setBackground(Color.pink);

		JPanel panel = new JPanel();
		jfrm.add(panel);

		// border settings
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border blueline = BorderFactory.createLineBorder(Color.white);

		// font settings
		Font head1 = new Font(Font.SERIF, Font.BOLD, 23); // top head
		Font head2 = new Font(Font.DIALOG, Font.BOLD, 20); // head 2
		Font f2 = new Font(Font.MONOSPACED, Font.BOLD, 13); // calc screen
		Font f3 = new Font(Font.DIALOG, Font.BOLD, 12);
		Font f0 = new Font(Font.DIALOG, Font.BOLD, 16); // calc buttons
		Font btn = new Font(Font.MONOSPACED, Font.BOLD, 16); // for buttons

		// heading back button
		Icon i_back = new ImageIcon("back.PNG");
		jb_back = new JButton(i_back);
		jb_back.setBounds(0, 0, 65, 64);
		jb_back.setForeground(Color.white);
		jb_back.setBackground(Color.black);
		jb_back.addActionListener(this);
		jfrm.add(jb_back);

		// heading Personal Expense
		JToggleButton head = new JToggleButton("FINANCIAL RECORDS    ");
		head.setBounds(64, 0, 360, 65);
		head.setForeground(Color.white);
		head.setBackground(Color.black);
		head.setBorder(blueline);
		head.setFont(head2);
		jfrm.add(head);

		// table 1 : income records
		jt_incomeRecords = new JTable();
		jt_incomeRecords.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header1 = jt_incomeRecords.getTableHeader();
		header1.setBackground(Color.yellow);
		scrollPane1 = new JScrollPane(jt_incomeRecords);
		scrollPane1.setBounds(10, 130, 390, 300);

		// table 2 : expense records
		table = new JTable();
		JTableHeader header = table.getTableHeader();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		header.setBackground(Color.yellow);
		scrollPane2 = new JScrollPane(table);
		scrollPane2.setBounds(10, 130, 390, 300);

		// buttons: back , clear and save
		jb_expense = new JButton("Expenses");
		jb_expense.setBounds(100, 80, 90, 40);
		jb_expense.setBorder(blueline);
		jb_expense.setFont(f2);
		jb_expense.setForeground(Color.white);
		jb_expense.setBackground(Color.BLUE);
		jb_expense.setActionCommand("clear");
		jb_expense.addActionListener(this);
		jfrm.add(jb_expense);

		jb_income = new JButton("Income");
		jb_income.setBounds(240, 80, 90, 40);
		jb_income.setBorder(blueline);
		jb_income.setFont(f2);
		jb_income.setForeground(Color.white);
		jb_income.setBackground(Color.BLUE);
		jb_income.setActionCommand("clear");
		jb_income.addActionListener(this);
		jfrm.add(jb_income);

		status = new JLabel(" ");
		status.setBounds(100, 520, 300, 40);
		jfrm.add(status);
		jfrm.setVisible(true);

		jb_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				HomeWindow HomeWindow = new HomeWindow();
				HomeWindow.setVisible(true);
				status.setText(" ");
			}
		});

//		jb_expense.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent ae) {
//				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
//				model2.setRowCount(0);
//				scrollPane1.setVisible(false);
//				scrollPane2.setVisible(true);
//				jfrm.add(scrollPane2);
//				try {
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
//							"vi18");
//					Statement st = con.createStatement();
//					String query = "SELECT * FROM Expense_Record";
//					ResultSet rs = st.executeQuery(query);
//					ResultSetMetaData rsmd = rs.getMetaData();
//					DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//					int cols = rsmd.getColumnCount();
//					colName = new String[cols];
//					for (int i = 0; i < cols; i++) {
//						colName[i] = rsmd.getColumnName(i + 1);
//					}
//					model.setColumnIdentifiers(colName);
//					String id;
//					String amount;
//					String payee, category, payment_method, payment_status, payment_description;
//					while (rs.next()) {
//						id = rs.getString(1);
//						amount = rs.getString(2);
//						payee = rs.getString(3);
//						category = rs.getString(4);
//						payment_method = rs.getString(5);
//						payment_status = rs.getString(6);
//						payment_description = rs.getString(7);
//						String[] row = { id, amount, payee, category, payment_method, payment_status,
//								payment_description };
//
//						model.addRow(row);
//					}
//					st.close();
//					con.close();
//
//				} catch (Exception e) {
//
//					e.printStackTrace();
//				}
//				status.setText("Displaying all the expenses made");
//
//			}
//		});
//
//		jb_income.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent ae) {
//				DefaultTableModel model1 = (DefaultTableModel) jt_incomeRecords.getModel();
//				model1.setRowCount(0);
//
//				scrollPane2.setVisible(false);
//				scrollPane1.setVisible(true);
//				jfrm.add(scrollPane1);
//				try {
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
//							"vi18");
//					Statement st = con.createStatement();
//					String query = "SELECT * FROM Income_Record";
//					ResultSet rs = st.executeQuery(query);
//					ResultSetMetaData rsmd = rs.getMetaData();
//					DefaultTableModel model = (DefaultTableModel) jt_incomeRecords.getModel();
//
//					int cols = rsmd.getColumnCount();
//					colName = new String[cols];
//					for (int i = 0; i < cols; i++) {
//						colName[i] = rsmd.getColumnName(i + 1);
//					}
//					model.setColumnIdentifiers(colName);
//					String id;
//					String amount;
//					String payer, category, payment_method, payment_status, payment_description;
//					while (rs.next()) {
//						id = rs.getString(1);
//						amount = rs.getString(2);
//						payer = rs.getString(3);
//						category = rs.getString(4);
//						payment_method = rs.getString(5);
//						payment_status = rs.getString(6);
//						payment_description = rs.getString(7);
//						String[] row = { id, amount, payer, category, payment_method, payment_status,
//								payment_description };
//
//						model.addRow(row);
//					}
//					st.close();
//					con.close();
//
//				} catch (Exception e) {
//
//					e.printStackTrace();
//				}
//				status.setText("Displaying the income made");
//			}
//		});
//
//		jb_clear.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent ae) {
//				JOptionPane.showMessageDialog(jb_clear, "Record Cleared!");
//				status.setText(" ");
//			}
//		});
//
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void setVisible(boolean b) {
//		// TODO Auto-generated method stub
//		jfrm.setVisible(true);
//	}
//}
//package financeManagingSystem;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.Statement;
//
//import javax.swing.BorderFactory;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JToggleButton;
//import javax.swing.SwingUtilities;
//import javax.swing.border.Border;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
////import javax.swing.JButton.addActionListener;
//
//public class RecordsWindow implements ActionListener {
//
//	public static void main(String args[]) {
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				new RecordsWindow();
//			}
//		});
//	}
//
//	JTable jt_incomeRecords, jt_expenseRecords, table;
//	JLabel status;
//	JFrame jfrm;
//	JButton jb_clear, jb_back, jb_expense, jb_income, jb_reminder;
//	JScrollPane scrollPane1, scrollPane2;
//	String[] colName, data;
//
//	DefaultTableModel model;
//	JButton btnNewButton;
//
//	public RecordsWindow() {
//
//		jfrm = new JFrame(" FinBud : FINANCIAL RECORDS ");
//		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jfrm.setSize(430, 680);
//		jfrm.getContentPane().setLayout(null);
//		jfrm.getContentPane().setBackground(Color.pink);
//
//		JPanel panel = new JPanel();
//		jfrm.getContentPane().add(panel);
//
//		Border blackline = BorderFactory.createLineBorder(Color.black);
//		Border blueline = BorderFactory.createLineBorder(Color.white);
//
//		Font head1 = new Font(Font.SERIF, Font.BOLD, 23); // top head
//		Font head2 = new Font(Font.DIALOG, Font.BOLD, 20); // head 2
//		Font f2 = new Font(Font.MONOSPACED, Font.BOLD, 13); // calc screen
//		Font f3 = new Font(Font.DIALOG, Font.BOLD, 12);
//		Font f0 = new Font(Font.DIALOG, Font.BOLD, 16); // calc buttons
//		Font btn = new Font(Font.MONOSPACED, Font.BOLD, 16); // for buttons
//
//		Icon i_back = new ImageIcon("back.PNG");
//		jb_back = new JButton(i_back);
//		jb_back.setBounds(0, 0, 65, 64);
//		jb_back.setForeground(Color.white);
//		jb_back.setBackground(Color.black);
//		jb_back.addActionListener(this);
//		jfrm.getContentPane().add(jb_back);
//
//		JToggleButton head = new JToggleButton("FINANCIAL RECORDS    ");
//		head.setBounds(64, 0, 360, 65);
//		head.setForeground(Color.white);
//		head.setBackground(Color.black);
//		head.setBorder(blueline);
//		head.setFont(head2);
//		jfrm.getContentPane().add(head);
//
//		jt_incomeRecords = new JTable();
//		jt_incomeRecords.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		JTableHeader header1 = jt_incomeRecords.getTableHeader();
//		header1.setBackground(Color.yellow);
//		scrollPane1 = new JScrollPane(jt_incomeRecords);
//		scrollPane1.setBounds(10, 130, 390, 300);
//
//		table = new JTable();
//		JTableHeader header = table.getTableHeader();
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		header.setBackground(Color.yellow);
//		scrollPane2 = new JScrollPane(table);
//		scrollPane2.setBounds(10, 130, 390, 300);
//
//		jb_expense = new JButton("Expenses");
//		jb_expense.setBounds(32, 80, 90, 40);
//		jb_expense.setBorder(blueline);
//		jb_expense.setFont(f2);
//		jb_expense.setForeground(Color.white);
//		jb_expense.setBackground(Color.BLUE);
//		jb_expense.setActionCommand("clear");
//		jb_expense.addActionListener(this);
//		jfrm.getContentPane().add(jb_expense);
//
//		jb_income = new JButton("Income");
//		jb_income.setBounds(152, 80, 90, 40);
//		jb_income.setBorder(blueline);
//		jb_income.setFont(f2);
//		jb_income.setForeground(Color.white);
//		jb_income.setBackground(Color.BLUE);
//		jb_income.setActionCommand("clear");
//		jb_income.addActionListener(this);
//		jfrm.getContentPane().add(jb_income);
//
//		status = new JLabel(" ");
//		status.setBounds(100, 520, 300, 40);
//		jfrm.getContentPane().add(status);
//
//		btnNewButton = new JButton("Reminders");
//		btnNewButton.setBounds(263, 81, 103, 40);
//
//		btnNewButton.setFont(f2);
//		btnNewButton.setForeground(Color.white);
//		btnNewButton.setBackground(Color.BLUE);
//		btnNewButton.setActionCommand("clear");
//		btnNewButton.addActionListener(this);
//		jfrm.getContentPane().add(btnNewButton);
//
//		jb_back.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent ae) {
//				HomeWindow HomeWindow = new HomeWindow();
//				HomeWindow.setVisible(true);
//				status.setText(" ");
//			}
//		});
//
//		btnNewButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent ae) {
//				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
//				model2.setRowCount(0);
//				scrollPane1.setVisible(false);
//				scrollPane2.setVisible(true);
//				jfrm.getContentPane().add(scrollPane2);
//				try {
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
//							"vi18");
//					Statement st = con.createStatement();
//					String query = "SELECT * FROM imp_reminder";
//					ResultSet rs = st.executeQuery(query);
//					ResultSetMetaData rsmd = rs.getMetaData();
//					DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//					int cols = rsmd.getColumnCount();
//					colName = new String[cols];
//					for (int i = 0; i < cols; i++) {
//						colName[i] = rsmd.getColumnName(i + 1);
//					}
//					model.setColumnIdentifiers(colName);
//
//					String amount;
//					String desc;
//					while (rs.next()) {
//
//						amount = rs.getString(1);
//						desc = rs.getString(2);
//
//						String[] row = { amount, desc };
//
//						model.addRow(row);
//					}
//					st.close();
//					con.close();
//
//				} catch (Exception e) {
//
//					e.printStackTrace();
//				}
//				status.setText("Displaying all the reminder made");
//
//			}
//		});
//
		jb_expense.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				model2.setRowCount(0);
				scrollPane1.setVisible(false);
				scrollPane2.setVisible(true);
				jfrm.getContentPane().add(scrollPane2);
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
							"vi18");
					Statement st = con.createStatement();
					String query = "SELECT * FROM Expense_Record";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();

					int cols = rsmd.getColumnCount();
					colName = new String[cols];
					for (int i = 0; i < cols; i++) {
						colName[i] = rsmd.getColumnName(i + 1);
					}
					model.setColumnIdentifiers(colName);
					String id;
					String amount;
					String payee, category, payment_method, payment_status, payment_description;
					while (rs.next()) {

						amount = rs.getString(1);
						payee = rs.getString(2);
						category = rs.getString(3);
						payment_method = rs.getString(4);
						payment_status = rs.getString(5);
						payment_description = rs.getString(6);
						String[] row = { amount, payee, category, payment_method, payment_status, payment_description };

						model.addRow(row);
					}
					st.close();
					con.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				status.setText("Displaying all the expenses made");

			}
		});

		jb_income.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				DefaultTableModel model1 = (DefaultTableModel) jt_incomeRecords.getModel();
				model1.setRowCount(0);

				scrollPane2.setVisible(false);
				scrollPane1.setVisible(true);
				jfrm.getContentPane().add(scrollPane1);
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
							"vi18");
					Statement st = con.createStatement();
					String query = "SELECT * FROM Income_Record";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) jt_incomeRecords.getModel();

					int cols = rsmd.getColumnCount();
					colName = new String[cols];
					for (int i = 0; i < cols; i++) {
						colName[i] = rsmd.getColumnName(i + 1);
					}
					model.setColumnIdentifiers(colName);

					String amount;
					String payer, category, payment_method, payment_status, payment_description;
					while (rs.next()) {

						amount = rs.getString(1);
						payer = rs.getString(2);
						category = rs.getString(3);
						payment_method = rs.getString(4);
						payment_status = rs.getString(5);
						payment_description = rs.getString(6);
						String[] row = { amount, payer, category, payment_method, payment_status, payment_description };

						model.addRow(row);
					}
					st.close();
					con.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				status.setText("Displaying the income made");
			}
		});

		jb_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(jb_clear, "Record Cleared!");
				status.setText(" ");
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void setVisible(boolean b) {
		jfrm.setVisible(true);
	}
}