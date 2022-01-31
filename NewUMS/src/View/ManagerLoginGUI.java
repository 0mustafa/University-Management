package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Helper.Helper;
import Model.Manager;
import Model.Student;

public class ManagerLoginGUI extends JFrame {

	private JPanel contentPane;
	private DBConnection connection = new DBConnection();
	static Student st = new Student();
	private JTextField txt_manager_mail;
	private JPasswordField txt_manager_newPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerLoginGUI frame = new ManagerLoginGUI();
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
	public ManagerLoginGUI() {
		setTitle("Universite Yonetim Sistemi");
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1313, 962);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Öðrenci Giriþ");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\swap.png"));
		btnNewButton_1.setBackground(new Color(68, 49, 65));
		btnNewButton_1.setForeground(new Color(17, 17, 17));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btnNewButton_1.setForeground(new Color(30, 30, 30));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				btnNewButton_1.setForeground(new Color(17, 17, 17));
				
			}
		});
		btnNewButton_1.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
		btnNewButton_1.setBounds(692, 863, 115, 31);
		contentPane.add(btnNewButton_1);

		JLabel lbl_signin = new JLabel("Admin Giriþ");
		lbl_signin.setForeground(new Color(17, 17, 17));
		lbl_signin.setFont(new Font("Poppins Medium", Font.PLAIN, 35));
		lbl_signin.setBackground(new Color(255, 255, 224));
		lbl_signin.setBounds(782, 324, 225, 53);
		contentPane.add(lbl_signin);

		JButton btnNewButton = new JButton("Giriþ Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txt_manager_mail.getText().length() == 0 || txt_manager_newPass.getText().length() == 0) {
					Helper.showMessage("Lütfen tüm alanlarý doldurunuz!");
				} else {

					try {
						Connection con = connection.connDb();
						Statement st = con.createStatement();
						ResultSet myRs = st.executeQuery("SELECT * FROM admins");
						boolean kontrol = false;
						while (myRs.next()) {
							if (txt_manager_mail.getText().equals(myRs.getString("admin_mail"))
									&& txt_manager_newPass.getText().equals(myRs.getString("password"))) {
								kontrol = true;
								Manager manager = new Manager();
								manager.setId(myRs.getInt("id"));
								manager.setFirst_name(myRs.getString("first_name"));
								manager.setLast_name(myRs.getString("last_name"));
								manager.setIdentityNumber(myRs.getString("tc_no"));
								manager.setPassword(myRs.getString("password"));
								manager.setAdminMail(myRs.getString("admin_mail"));
								System.out.println("Hoþgeldiniz " + manager.getFirst_name());
								ManagerGUI managerGUI = new ManagerGUI(manager);
								managerGUI.setVisible(true);
								dispose();
							}
						}
						if (kontrol == false) {
							Helper.showMessage("Girdiðiniz bilgiler yanlýþtýr!");
							txt_manager_mail.setText("");
							txt_manager_newPass.setText("");
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnNewButton.setBackground(new Color(30, 30, 30));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnNewButton.setBackground(new Color(17, 17, 17));

			}
		});

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(
				"C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\logo 150px.png"));
		lblNewLabel_4.setBounds(236, 312, 150, 150);
		contentPane.add(lblNewLabel_4);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(17, 17, 17));
		btnNewButton.setForeground(new Color(68, 49, 65));
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(897, 577, 160, 60);
		contentPane.add(btnNewButton);

		txt_manager_newPass = new JPasswordField();
		txt_manager_newPass.setText("Þifre");
		txt_manager_newPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txt_manager_newPass.setForeground(new Color(40, 40, 40));
				if(txt_manager_newPass.getText().length() == 0)
					txt_manager_newPass.setText("Þifre");
			}
		});
		txt_manager_newPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				txt_manager_newPass.setForeground(new Color(17, 17, 17));
				txt_manager_newPass.setText("");

			}
		});
		txt_manager_newPass.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		txt_manager_newPass.setBackground(new Color(68, 49, 65));
		txt_manager_newPass.setForeground(new Color(40, 40, 40));
		txt_manager_newPass.setBorder(null);
		txt_manager_newPass.setBounds(782, 487, 419, 32);
		contentPane.add(txt_manager_newPass);

		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(
				new ImageIcon("C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\Line 1.png"));
		lblNewLabel_3_1.setBounds(782, 519, 419, 2);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(
				new ImageIcon("C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\Line 1.png"));
		lblNewLabel_3.setBounds(782, 437, 419, 2);
		contentPane.add(lblNewLabel_3);

		txt_manager_mail = new JTextField();
		txt_manager_mail.setText("E-posta");
		txt_manager_mail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txt_manager_mail.setForeground(new Color(40, 40, 40));
				if(txt_manager_mail.getText().length() == 0)
					txt_manager_mail.setText("E-posta");
			}
		});
		txt_manager_mail.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		txt_manager_mail.setBackground(new Color(68, 49, 65));
		txt_manager_mail.setForeground(new Color(40, 40, 40));
		txt_manager_mail.setBorder(null);
		txt_manager_mail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				txt_manager_mail.setForeground(new Color(17, 17, 17));
				txt_manager_mail.setText("");

			}
		});
		txt_manager_mail.setBounds(782, 405, 419, 32);
		contentPane.add(txt_manager_mail);
		txt_manager_mail.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Üniversite Yönetim Sistemi Giriþ");
		lblNewLabel_2.setForeground(new Color(68, 49, 65));
		lblNewLabel_2.setFont(new Font("Poppins Medium", Font.BOLD, 30));
		lblNewLabel_2.setBackground(new Color(255, 255, 224));
		lblNewLabel_2.setBounds(75, 494, 504, 46);
		contentPane.add(lblNewLabel_2);

//		JButton btn_manager_login = new JButton("Giri\u015F Yap");
//		btn_manager_login.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (txt_manager_mail.getText().length() == 0 || txt_manager_password.getText().length() == 0) {
//					Helper.showMessage("Lütfen tüm alanlarý doldurunuz!");
//				} else {
//
//					try {
//						Connection con = connection.connDb();
//						Statement st = con.createStatement();
//						ResultSet myRs = st.executeQuery("SELECT * FROM admins");
//						boolean kontrol = false;
//						while (myRs.next()) {
//							if (txt_manager_mail.getText().equals(myRs.getString("admin_mail"))
//									&& txt_manager_password.getText().equals(myRs.getString("password"))) {
//								kontrol = true;
//								Manager manager = new Manager();
//								manager.setId(myRs.getInt("id"));
//								manager.setFirst_name(myRs.getString("first_name"));
//								manager.setLast_name(myRs.getString("last_name"));
//								manager.setIdentityNumber(myRs.getString("tc_no"));
//								manager.setPassword(myRs.getString("password"));
//								manager.setAdminMail(myRs.getString("admin_mail"));
//								System.out.println("Hoþgeldiniz " + manager.getFirst_name());
//								ManagerGUI managerGUI = new ManagerGUI(manager);
//								managerGUI.setVisible(true);
//								dispose();
//							}
//						}
//						if (kontrol == false) {
//							Helper.showMessage("Girdiðiniz bilgiler yanlýþtýr!");
//							txt_manager_mail.setText("");
//							txt_manager_password.setText("");
//						}
//
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//
//			}
//		});
//		btn_manager_login.setBackground(Color.WHITE);
//		btn_manager_login.setBounds(73, 147, 348, 45);
//		manager_login.add(btn_manager_login);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon("C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\LEFT.png"));
		lblNewLabel.setBounds(0, 0, 650, 930);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(
				new ImageIcon("C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\RIGHT.png"));
		lblNewLabel_1.setBounds(647, 0, 650, 930);
		contentPane.add(lblNewLabel_1);
	}

}
