package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import Model.Instructor;
import Model.Manager;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class ManagerGUI extends JFrame {

	private JPanel contentPane;
	static Manager manager2 = new Manager();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerGUI frame = new ManagerGUI(manager2);
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
	public ManagerGUI(Manager manager) {
		setResizable(false);

		setTitle("Yönetici Paneli");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1504, 933);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

				
		JButton instructor_settings = new JButton("Egitmen Yonetimi");
		instructor_settings.setFocusPainted(false);
		instructor_settings.setForeground(new Color(112, 112, 112));
		instructor_settings.setBounds(0, 231, 258, 70);
		instructor_settings.setBackground(new Color(17, 17, 17));
		instructor_settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				instructor_settings.setBackground(new Color(50, 50, 50));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				instructor_settings.setBackground(new Color(17, 17, 17));
				
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel("Üniversite Yönetim Sistemine Hoþ Geldiniz.");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_5.setBounds(413, 260, 967, 70);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\avatar.png"));
		lblNewLabel_3.setBounds(20, 56, 46, 70);
		lblNewLabel_3.setBorder(null);
		contentPane.add(lblNewLabel_3);
		instructor_settings.setFont(new Font("Segoe UI", Font.BOLD, 12));
		instructor_settings.setBorder(null);
		contentPane.add(instructor_settings);
		

		JButton student_settings = new JButton("Ogrenci Yonetimi");
		student_settings.setFocusPainted(false);
		student_settings.setForeground(new Color(112, 112, 112));
		student_settings.setBounds(0, 312, 258, 70);
		student_settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				student_settings.setBackground(new Color(50, 50, 50));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				student_settings.setBackground(new Color(17, 17, 17));
				
			}
		});
		student_settings.setFont(new Font("Segoe UI", Font.BOLD, 12));
		student_settings.setBorder(null);
		contentPane.add(student_settings);
		student_settings.setBackground(new Color(17, 17, 17));
		

		JButton lesson_settings = new JButton("Ders Yonetimi");
		lesson_settings.setFocusPainted(false);
		lesson_settings.setForeground(new Color(112, 112, 112));
		lesson_settings.setBounds(0, 393, 258, 70);
		lesson_settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				lesson_settings.setBackground(new Color(50, 50, 50));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				lesson_settings.setBackground(new Color(17, 17, 17));
				
			}
		});
		lesson_settings.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lesson_settings.setBorder(null);
		contentPane.add(lesson_settings);
		lesson_settings.setBackground(new Color(17, 17, 17));

		JButton faculty_settings = new JButton("Fakulte Yonetimi");
		faculty_settings.setFocusPainted(false);
		faculty_settings.setForeground(new Color(112, 112, 112));
		faculty_settings.setBounds(0, 474, 258, 70);
		faculty_settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				faculty_settings.setBackground(new Color(50, 50, 50));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				faculty_settings.setBackground(new Color(17, 17, 17));
				
			}
		});
		faculty_settings.setFont(new Font("Segoe UI", Font.BOLD, 12));
		faculty_settings.setBorder(null);
		contentPane.add(faculty_settings);
		faculty_settings.setBackground(new Color(17, 17, 17));

		JLabel lblNewLabel_2 = new JLabel("<html>"+ manager.getFirst_name().toUpperCase() + " " + manager.getLast_name().toUpperCase() +"<br/>\r\n<p style=\"font-weight:normal;\">Yönetici</p>\r\n</html>");
		lblNewLabel_2.setForeground(new Color(112, 112, 112));
		lblNewLabel_2.setBounds(67, 53, 177, 77);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		contentPane.add(lblNewLabel_2);
		
		JButton faculty_settings_2 = new JButton("Çýkýþ Yap");
		faculty_settings_2.setFocusPainted(false);
		faculty_settings_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
				
			}
		});
		faculty_settings_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				faculty_settings_2.setBackground(new Color(50, 50, 50));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				faculty_settings_2.setBackground(new Color(17, 17, 17));
				
			}
		});
		faculty_settings_2.setIcon(new ImageIcon("C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\Iconly-Light-Logout.png"));
		faculty_settings_2.setForeground(new Color(112, 112, 112));
		faculty_settings_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		faculty_settings_2.setBorder(null);
		faculty_settings_2.setBackground(new Color(17, 17, 17));
		faculty_settings_2.setBounds(0, 735, 258, 50);
		contentPane.add(faculty_settings_2);
		
		JButton faculty_settings_1 = new JButton("Ayarlar");
		faculty_settings_1.setFocusPainted(false);
		faculty_settings_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ManagerSettingsGUI managerSettings = new ManagerSettingsGUI(manager);
				managerSettings.setVisible(true);
				
			}
		});
		faculty_settings_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				faculty_settings_1.setBackground(new Color(50, 50, 50));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				faculty_settings_1.setBackground(new Color(17, 17, 17));
				
			}
		});
		faculty_settings_1.setIcon(new ImageIcon("C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\Iconly-Light-Setting.png"));
		faculty_settings_1.setForeground(new Color(112, 112, 112));
		faculty_settings_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		faculty_settings_1.setBorder(null);
		faculty_settings_1.setBackground(new Color(17, 17, 17));
		faculty_settings_1.setBounds(0, 688, 258, 50);
		contentPane.add(faculty_settings_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\leftMenuBackground.png"));
		lblNewLabel_1.setBounds(0, 0, 258, 894);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\logo 150px.png"));
		lblNewLabel.setBounds(390, 127, 144, 122);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\Rectangle 6.png"));
		lblNewLabel_4.setBounds(248, 0, 1240, 440);
		contentPane.add(lblNewLabel_4);

		faculty_settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FacultyProcessGUI facultyProcess = new FacultyProcessGUI(manager);
				facultyProcess.setVisible(true);
				dispose();

			}
		});
		lesson_settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				LessonProcessGUI lessonProcess = new LessonProcessGUI(manager);
				lessonProcess.setVisible(true);
				dispose();

			}
		});
		student_settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				StudentProcessGUI studentProcess = new StudentProcessGUI(manager);
				studentProcess.setVisible(true);
				dispose();

			}
		});
		instructor_settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				InstructorProcessGUI instructorProcess = new InstructorProcessGUI(manager);
				instructorProcess.setVisible(true);
				dispose();

			}
		});

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
}