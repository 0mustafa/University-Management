package View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.*;
import Model.Student;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private DBConnection connection = new DBConnection();
	static Student st = new Student();
	private JTextField txt_student_mail;
	private JPasswordField txt_student_newPass;
	private String imagesPath = "C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\";  //proje dosyasýnýn içindeki img klasörünün yolunu buraya giriniz!

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("Universite Yonetim Sistemi");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1313, 962);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Admin Giriþ");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ManagerLoginGUI managerLogin = new ManagerLoginGUI();
				managerLogin.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(imagesPath + "swap.png"));
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
		
		JLabel lbl_signin = new JLabel("Öðrenci Giriþ");
		lbl_signin.setForeground(new Color(17, 17, 17));
		lbl_signin.setFont(new Font("Poppins Medium", Font.PLAIN, 35));
		lbl_signin.setBackground(new Color(255, 255, 224));
		lbl_signin.setBounds(782, 324, 225, 53);
		contentPane.add(lbl_signin);
		
		JButton btnNewButton = new JButton("Giriþ Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(txt_student_mail.getText().length() == 0 || txt_student_newPass.getText().length() == 0) {
					Helper.showMessage("Lütfen tüm alanlarý doldurunuz!");
				}else {
					
					try {
						
						List<Student> filteredStudent = st.getAllStudents().stream().filter(f->f.getNumber().equals(txt_student_mail.getText()) && f.getIdentityNumber().equals(txt_student_newPass.getText())).collect(Collectors.toList());
						Connection con = connection.connDb();
						Statement st = con.createStatement();
						ResultSet myRs = st.executeQuery("SELECT * FROM students");
						boolean kontrol = false;
						while(myRs.next()) {
							if(txt_student_mail.getText().equals(myRs.getString("school_number")) && txt_student_newPass.getText().equals(myRs.getString("tc_no"))) {
								kontrol = true;
								Student student = filteredStudent.get(0);
								System.out.println("Hosgeldiniz " + student.getFirst_name().toUpperCase());
								StudentGUI studentGUI = new StudentGUI(student);
								studentGUI.setVisible(true);
								dispose();
							}
						}
						if(kontrol == false) {
							Helper.showMessage("Girdiðiniz bilgiler yanlýþtýr!");
							txt_student_mail.setText("");
							txt_student_newPass.setText("");
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
		lblNewLabel_4.setIcon(new ImageIcon(imagesPath + "logo 150px.png"));
		lblNewLabel_4.setBounds(236, 312, 150, 150);
		contentPane.add(lblNewLabel_4);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(17, 17, 17));
		btnNewButton.setForeground(new Color(68, 49, 65));
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(897, 577, 160, 60);
		contentPane.add(btnNewButton);
		
		
		
		txt_student_newPass = new JPasswordField();
		txt_student_newPass.setText("Þifre");
		txt_student_newPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txt_student_newPass.setForeground(new Color(40, 40, 40));
				if(txt_student_newPass.getText().length() == 0)
					txt_student_newPass.setText("Þifre");
			}
		});
		txt_student_newPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txt_student_newPass.setForeground(new Color(17, 17, 17));
				txt_student_newPass.setText("");
				
			}
		});
		txt_student_newPass.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		txt_student_newPass.setBackground(new Color(68, 49, 65));
		txt_student_newPass.setForeground(new Color(40, 40, 40));
		txt_student_newPass.setBorder(null);
		txt_student_newPass.setBounds(782, 487, 419, 32);
		contentPane.add(txt_student_newPass);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(imagesPath + "Line 1.png"));
		lblNewLabel_3_1.setBounds(782, 519, 419, 2);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(imagesPath + "Line 1.png"));
		lblNewLabel_3.setBounds(782, 437, 419, 2);
		contentPane.add(lblNewLabel_3);
		
		
		
		txt_student_mail = new JTextField();
		txt_student_mail.setText("Öðrenci numaranýzý giriniz.");
		txt_student_mail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txt_student_mail.setForeground(new Color(40, 40, 40));
				if(txt_student_mail.getText().length() == 0)
					txt_student_mail.setText("Öðrenci numaranýzý giriniz.");
			}
		});
		txt_student_mail.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		txt_student_mail.setBackground(new Color(68, 49, 65));
		txt_student_mail.setForeground(new Color(40, 40, 40));
		txt_student_mail.setBorder(null);
		txt_student_mail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txt_student_mail.setForeground(new Color(17, 17, 17));
				txt_student_mail.setText("");
				
			}
		});
		txt_student_mail.setBounds(782, 405, 419, 32);
		contentPane.add(txt_student_mail);
		txt_student_mail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Üniversite Yönetim Sistemi Giriþ");
		lblNewLabel_2.setForeground(new Color(68, 49, 65));
		lblNewLabel_2.setFont(new Font("Poppins Medium", Font.BOLD, 30));
		lblNewLabel_2.setBackground(new Color(255, 255, 224));
		lblNewLabel_2.setBounds(75, 494, 504, 46);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(imagesPath + "LEFT.png"));
		lblNewLabel.setBounds(0, 0, 650, 930);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(imagesPath + "RIGHT.png"));
		lblNewLabel_1.setBounds(647, 0, 650, 930);
		contentPane.add(lblNewLabel_1);
		
	}
}
