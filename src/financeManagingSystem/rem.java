package financeManagingSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class rem implements ActionListener {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					rem window = new rem();
					window.reminder.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JFrame reminder;
	private JTextField txtEnterAmmount;
	private JTextField txtEnterDescrHere;

	/**
	 * Create the application.
	 */
	public rem() {
		initialize();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		reminder = new JFrame();
		reminder.setTitle("Reminders");
		reminder.setBackground(new Color(128, 0, 64));
		reminder.setBounds(100, 100, 450, 300);
		reminder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		reminder.getContentPane().setLayout(null);

		Font head1 = new Font(Font.SERIF, Font.BOLD, 23); // top head
		Font head2 = new Font(Font.DIALOG, Font.BOLD, 20); // head 2
		Font f2 = new Font(Font.MONOSPACED, Font.BOLD, 20); // calc screen
		Font f3 = new Font(Font.DIALOG, Font.BOLD, 12);
		Font f0 = new Font(Font.DIALOG, Font.BOLD, 16); // calc buttons
		Font btn = new Font(Font.MONOSPACED, Font.BOLD, 16); // for button

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 265, 42);
		reminder.getContentPane().add(panel);

		JLabel ammount = new JLabel("Ammount");
		ammount.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(ammount);
		ammount.setBounds(00, 200, 120, 40);

		txtEnterAmmount = new JTextField();
		panel.add(txtEnterAmmount);
		txtEnterAmmount.setColumns(10);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(10, 199, 85, 21);
		reminder.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBounds(160, 199, 85, 21);
		reminder.getContentPane().add(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 60, 267, 42);
		reminder.getContentPane().add(panel_1);

		JLabel lblNewLabel = new JLabel("Description");
		panel_1.add(lblNewLabel);

		txtEnterDescrHere = new JTextField();
		panel_1.add(txtEnterDescrHere);
		txtEnterDescrHere.setColumns(10);

		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.setBounds(294, 199, 85, 21);
		reminder.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		reminder.getContentPane().add(btnNewButton_2);

		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				HomeWindow HomeWindow = new HomeWindow();
				HomeWindow.setVisible(true);

			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

//							String amt=tf_amount.getText();
//							Float amount=Float.parseFloat(amt);
//							String payer=tf_payer.getText();
//							String desc=tf_description.getText();
//							String category=(String) jcb_category.getSelectedItem();
//							String payment=(String) jcb_paymentMode.getSelectedItem();
//							String payStatus=(String) jcb_paymentStatus.getSelectedItem();
//							String fileContent=amt+"\t"+payer+"\t"+category+"\t"+payment+"\t"+payStatus+"\t"+desc;
				try {
					String amt = txtEnterAmmount.getText();
					Float amount = Float.parseFloat(amt);
					String desc = txtEnterDescrHere.getText();
					String fileContent = amt + "\t" + desc;

					saveContents(fileContent);
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
							"vi18");
					Statement stm = con.createStatement();
					String query = "INSERT INTO imp_reminder(price,description)values(" + amt + ",'" + desc + "')";

					// PreparedStatement ps=con.prepareStatement(query);
					// ps.setString(1,date);
					// ps.setFloat(2,amount);
					// ps.setString(3,payee);
					// ps.setString(4,category);
					// ps.setString(5,payment);
					// ps.setString(6,payStatus);
					// ps.setString(7,desc);

					int i = stm.executeUpdate(query);
					JOptionPane.showMessageDialog(btnNewButton, i + " Record has been added!");
					// ps.close();
					con.close();
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(reminder, " invalid ammount!");
				}

			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String amt = txtEnterAmmount.getText();
				String desc = txtEnterDescrHere.getText();
				String fileContent = amt + "\t" + desc;

				try {
					saveContents(fileContent);
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
							"vi18");
					Statement stm = con.createStatement();
					String query = "delete from imp_reminder where '" + desc + "' = description ";

					// PreparedStatement ps=con.prepareStatement(query);
					// ps.setString(1,date);
					// ps.setFloat(2,amount);
					// ps.setString(3,payee);
					// ps.setString(4,category);
					// ps.setString(5,payment);
					// ps.setString(6,payStatus);
					// ps.setString(7,desc);

					int i = stm.executeUpdate(query);
					JOptionPane.showMessageDialog(btnNewButton, i + " Record has been deleted!");
					// ps.close();
					con.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

	}

	public void saveContents(String fileContent) throws Exception {
		FileOutputStream outputStream = null;
		try {

			outputStream = new FileOutputStream("expense_sheet.txt");

			byte[] strToBytes = fileContent.getBytes();

			outputStream.write(strToBytes);
		}

		catch (IOException e) {
			System.out.print(e.getMessage());
		} finally {

			if (outputStream != null) {

				try {
					outputStream.close();
				}

				catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		reminder.setVisible(true);
	}

}
