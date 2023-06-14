package financeManagingSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class IncomeWindow implements ActionListener {

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new IncomeWindow();
			}
		});
	}

	JTextField tf_amount, tf_payer, tf_description;
	JLabel status;
	JComboBox jcb_category, jcb_paymentMode, jcb_paymentStatus;
	JFrame jfrm;

	JButton jb_save, jb_clear, jb_back, jbt_back;

	public IncomeWindow() {
		jfrm = new JFrame(" FinAdmin : INCOME MANAGER ");
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
		Font f2 = new Font(Font.MONOSPACED, Font.BOLD, 20); // calc screen
		Font f3 = new Font(Font.DIALOG, Font.BOLD, 12);
		Font f0 = new Font(Font.DIALOG, Font.BOLD, 16); // calc buttons
		Font btn = new Font(Font.MONOSPACED, Font.BOLD, 16); // for buttons

		// top back button
		Icon i_back = new ImageIcon("back.PNG");
		jbt_back = new JButton(i_back);
		jbt_back.setBounds(0, 0, 65, 64);
		jbt_back.setForeground(Color.white);
		jbt_back.setBackground(Color.black);
		jbt_back.addActionListener(this);
		jfrm.add(jbt_back);

		// heading Personal Expense
		JToggleButton head = new JToggleButton("INCOME MANAGER");
		head.setBounds(0, 0, 420, 65);
		head.setForeground(Color.white);
		head.setBackground(Color.black);
		head.setBorder(blueline);
		head.setFont(head2);
		jfrm.add(head);

		// amount
		JLabel jl_amount = new JLabel("Amount", JLabel.LEFT);
		jl_amount.setBounds(30, 120, 100, 40);
		jl_amount.setBackground(Color.white);
		jl_amount.setFont(f0);
		tf_amount = new JTextField(30);
		tf_amount.setBounds(140, 125, 200, 32);

		Icon i_amt = new ImageIcon("amount.PNG");
		JLabel icon_amt = new JLabel(i_amt);
		icon_amt.setBounds(355, 120, 50, 45);

		jfrm.add(jl_amount);
		jfrm.add(tf_amount);
		jfrm.add(icon_amt);

		// payee
		JLabel jl_payer = new JLabel("Payer", JLabel.LEFT);
		jl_payer.setBounds(30, 170, 100, 40);
		jl_payer.setBackground(Color.white);
		jl_payer.setFont(f0);
		tf_payer = new JTextField(20);
		tf_payer.setBounds(140, 175, 200, 32);
		Icon i_calc = new ImageIcon("calculator.jfif");
		JLabel icon_calculator = new JLabel(i_calc);
		icon_calculator.setBounds(355, 170, 50, 45);
		jfrm.add(icon_calculator);
		jfrm.add(jl_payer);
		jfrm.add(tf_payer);

		// category
		JLabel jl_category = new JLabel("Category", JLabel.LEFT);
		jl_category.setBounds(30, 220, 100, 40);
		jl_category.setBackground(Color.white);
		jl_category.setFont(f0);
		String categoryList[] = { "Income", "Salary", "Equities", "Personal Savings", "Rents adn Royalties",
				"Home Equity", "Part-time Work", "Pensions", "Annuities", "Social Security", "Account Transfer" };
		jcb_category = new JComboBox(categoryList);
		jcb_category.setBounds(180, 225, 160, 35);
		jfrm.add(jl_category);
		jfrm.add(jcb_category);
		Icon i_save = new ImageIcon("saving.jpg");
		JLabel icon_save = new JLabel(i_save);
		icon_save.setBounds(355, 220, 50, 45);
		jfrm.add(icon_save);

		// payment method
		JLabel jl_paymentMode = new JLabel("Payment Method", JLabel.LEFT);
		jl_paymentMode.setBounds(30, 270, 130, 40);
		jl_paymentMode.setBackground(Color.white);
		jl_paymentMode.setFont(f0);
		String paymentModeList[] = { "Cash", "Cheque", "Credit Card", "Debit", "Electronic Transfer" };
		jcb_paymentMode = new JComboBox(paymentModeList);
		jcb_paymentMode.setBounds(180, 275, 160, 35);
		jfrm.add(jl_paymentMode);
		jfrm.add(jcb_paymentMode);

		Icon i_card = new ImageIcon("card.jpg");
		JLabel icon_card = new JLabel(i_card);
		icon_card.setBounds(355, 270, 50, 45);
		jfrm.add(icon_card);

		// status - cleared, uncleared, reconciled, void
		JLabel jl_status = new JLabel("Status", JLabel.LEFT);
		jl_status.setBounds(30, 320, 100, 40);
		jl_status.setBackground(Color.white);
		jl_status.setFont(f0);
		jfrm.add(jl_status);
		String statusList[] = { "Cleared", "Uncleared", "Reconciled", "Void" };
		jcb_paymentStatus = new JComboBox(statusList);
		jcb_paymentStatus.setBounds(180, 322, 160, 35);
		jcb_paymentStatus.addItemListener(null);
		Icon i_tick = new ImageIcon("tick.jfif");
		JLabel icon_tick = new JLabel(i_tick);
		icon_tick.setBounds(355, 320, 50, 50);
		jfrm.add(icon_tick);
		jfrm.add(jcb_paymentStatus);

		// description
		JLabel jl_description = new JLabel("Description", JLabel.LEFT);
		jl_description.setBounds(30, 370, 120, 40);
		jl_description.setBackground(Color.white);
		jl_description.setFont(f0);
		tf_description = new JTextField(40);
		tf_description.setBounds(30, 405, 310, 32);

		Icon i_cam = new ImageIcon("camera.PNG");
		JButton icon_camera = new JButton(i_cam);
		icon_camera.setBounds(355, 385, 50, 50);

		jfrm.add(jl_description);
		jfrm.add(tf_description);
		jfrm.add(icon_camera);

		// buttons: back , clear and save
		jb_back = new JButton("BACK");
		jb_back.setBounds(30, 460, 90, 50);
		jb_back.setBorder(blueline);
		jb_back.setFont(btn);
		jb_back.setForeground(Color.white);
		jb_back.setBackground(Color.BLUE);
		jb_back.setActionCommand("back");
		jb_back.addActionListener(this);
		jfrm.add(jb_back);

		jb_clear = new JButton("CLEAR");
		jb_clear.setBounds(140, 460, 90, 50);
		jb_clear.setBorder(blueline);
		jb_clear.setFont(btn);
		jb_clear.setForeground(Color.white);
		jb_clear.setBackground(Color.BLUE);
		jb_clear.setActionCommand("clear");
		jb_clear.addActionListener(this);
		jfrm.add(jb_clear);

		jb_save = new JButton("SAVE");
		jb_save.setBounds(250, 460, 90, 50);
		jb_save.setBorder(blueline);
		jb_save.setFont(btn);
		jb_save.setForeground(Color.white);
		jb_save.setBackground(Color.BLUE);
		jb_save.setActionCommand("save");
		jb_save.addActionListener(this);
		jfrm.add(jb_save);

		status = new JLabel(" ");
		status.setBounds(150, 520, 100, 40);
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

		jbt_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				HomeWindow HomeWindow = new HomeWindow();
				HomeWindow.setVisible(true);
				status.setText(" ");
			}
		});

		jb_clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				tf_amount.setText("");
				tf_payer.setText("");
				tf_description.setText("");
				JOptionPane.showMessageDialog(jb_clear, "Record Cleared!");
				status.setText(" ");
			}
		});

		jb_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// try {
				try {
					String amt = tf_amount.getText();
					Float amount = Float.parseFloat(amt);
					String payer = tf_payer.getText();
					String desc = tf_description.getText();
					String category = (String) jcb_category.getSelectedItem();
					String payment = (String) jcb_paymentMode.getSelectedItem();
					String payStatus = (String) jcb_paymentStatus.getSelectedItem();
					String fileContent = amt + "\t" + payer + "\t" + category + "\t" + payment + "\t" + payStatus + "\t"
							+ desc;
//				}
//				catch(Exception e2)
//				{
//
//				}

					saveContents(fileContent);
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
							"vi18");
					Statement stm = con.createStatement();
					String query = "INSERT INTO Income_Record(ammount,payer,category,payment_method,payment_status,payment_description)values("
							+ amt + ",'" + payer + "','" + category + "','" + payment + "','" + payStatus + "','" + desc
							+ "')";

					// PreparedStatement ps=con.prepareStatement(query);
					// ps.setString(1,date);
					// ps.setFloat(2,amount);
					// ps.setString(3,payee);
					// ps.setString(4,category);
					// ps.setString(5,payment);
					// ps.setString(6,payStatus);
					// ps.setString(7,desc);

					int i = stm.executeUpdate(query);
					JOptionPane.showMessageDialog(jb_save, i + " Record has been added!");
					// ps.close();
					con.close();
				} catch (Exception e1) {
					e1.printStackTrace();
					// int i = stm.executeUpdate(query);
					// JOptionPane.showInputDialog(e1 + " invalid ammount!");
					JOptionPane.showMessageDialog(icon_save, " invalid ammount!");
					// ps.close();
					// con.close();
				}
				status.setText("Changes Saved!");
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

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
		jfrm.setVisible(true);
	}
}