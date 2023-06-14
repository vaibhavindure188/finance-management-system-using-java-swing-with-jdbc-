package financeManagingSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class HomeWindow implements ActionListener {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new HomeWindow();
			}
		});
	}

	JLabel lab1;
	ImageIcon i1;
	JLabel j1, txt;
	JFrame jfrm;

	JButton b1, b2, b3, b4, b5, b6;

	public HomeWindow() {
		jfrm = new JFrame("FinBud : YOUR FINANCE BUDDY");
		jfrm.setSize(430, 680);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.getContentPane().setBackground(new Color(128, 0, 64));
		jfrm.getContentPane().setLayout(null);

		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border whiteline = BorderFactory.createLineBorder(Color.white);

		// Adding fonts
		Font font1 = new Font(Font.SERIF, Font.BOLD, 20);
		Font btn = new Font(Font.MONOSPACED, Font.BOLD, 16); // for buttons
		Font headFont = new Font(Font.SERIF, Font.BOLD, 30);
		Font headFont2 = new Font(Font.DIALOG, Font.BOLD, 20);
		Font headFont3 = new Font(Font.MONOSPACED, Font.BOLD, 30);

		// heading image
		Icon i_head = new ImageIcon("buddy.PNG");
		JLabel icon_head = new JLabel(i_head);
		icon_head.setBounds(0, 0, 60, 60);
		icon_head.setBackground(Color.black);
		jfrm.getContentPane().add(icon_head);

		// Adding header
		JButton jbtn = new JButton("FNC");
		jbtn.setBounds(100, 0, 170, 60);
		jbtn.setBackground(Color.black);
		jbtn.setForeground(Color.WHITE);
		jbtn.setFont(headFont3);

		JButton btn_subHead = new JButton("Your Finance Manager");
		btn_subHead.setBounds(0, 60, 430, 55);
		btn_subHead.setBackground(Color.black);
		btn_subHead.setForeground(Color.WHITE);
		btn_subHead.setFont(headFont2);

		jfrm.getContentPane().add(jbtn);
		jfrm.getContentPane().add(btn_subHead);
//ADDING TEXT
//		ImageIcon i2 = new ImageIcon("emoji2.png");
		// txt = new JLabel("WHAT WOULD YOU LIKE TO DO ? ", i2, JLabel.LEFT);
		// txt.setBounds(100, 390, 400, 50);
		// jfrm.add(txt);

//adding graphics
		i1 = new ImageIcon("graphics3.png");
		lab1 = new JLabel(i1);
		lab1.setBounds(0, 130, 430, 250);
		lab1.setBackground(Color.white);
		lab1.setBorder(whiteline);
		jfrm.getContentPane().add(lab1);

//Creating buttons income, expense, reminder, notes
		b1 = new JButton("INCOME");
		b1.setBounds(50, 420, 105, 45);
		b1.setBorder(whiteline);
		b1.setBackground(Color.blue);
		b1.setForeground(Color.WHITE);
		b1.setFont(btn);

		b2 = new JButton("EXPENSE");
		b2.setBounds(240, 420, 105, 45);
		b2.setBorder(whiteline);
		b2.setBackground(Color.blue);
		b2.setForeground(Color.WHITE);
		b2.setFont(btn);

		b3 = new JButton("REMINDER");
		b3.setBounds(50, 480, 105, 45);
		b3.setBorder(whiteline);
		b3.setBackground(Color.pink);
		b3.setForeground(Color.WHITE);
		b3.setFont(btn);

		b4 = new JButton("NOTES");
		b4.setBounds(240, 480, 105, 45);
		b4.setBorder(whiteline);
		b4.setBackground(Color.pink);
		b4.setForeground(Color.WHITE);
		b4.setFont(btn);

		b5 = new JButton("RECORDS");
		b5.setBounds(50, 540, 105, 45);
		b5.setBorder(whiteline);
		b5.setBackground(Color.blue);
		b5.setForeground(Color.WHITE);
		b5.setFont(btn);

//Adding Buttons to the frame
		jfrm.getContentPane().add(b1);
		jfrm.getContentPane().add(b2);
		jfrm.getContentPane().add(b3);
		jfrm.getContentPane().add(b4);
		jfrm.getContentPane().add(b5);
//msg display
		j1 = new JLabel("");
		j1.setBounds(125, 600, 500, 50);
		jfrm.getContentPane().add(j1);

//Action Listener
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				IncomeWindow IncomeWindow = new IncomeWindow();
				IncomeWindow.setVisible(true);
				j1.setText("You pressed Income button");
			}
		});

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ExpenseWindow ExpenseWindow = new ExpenseWindow();
				ExpenseWindow.setVisible(true);
				j1.setText("You pressed Expense button");
			}
		});
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				rem rem = new rem();
				rem.setVisible(true);
				j1.setText("You pressed Reminder button");
			}
		});
		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				j1.setText("You pressed Notes button");
			}
		});

		jbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				j1.setText("~~~~~~~HOMEPAGE~~~~~~~~");
			}
		});
		b5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				RecordsWindow RecordsWindow = new RecordsWindow();
				RecordsWindow.setVisible(true);
				j1.setText("You pressed record button");
			}
		});

		jfrm.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		jfrm.setVisible(true);
	}

}
