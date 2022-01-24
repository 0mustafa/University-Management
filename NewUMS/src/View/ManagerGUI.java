package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.tools.DiagnosticCollector;

import com.toedter.calendar.JDateChooser;

import Helper.*;
import Model.Department;
import Model.Faculty;
import Model.Instructor;
import Model.Lesson;
import Model.LessonStudent;
import Model.Manager;
import Model.Student;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JLayeredPane;
import javax.swing.SpringLayout;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JInternalFrame;
import java.awt.Component;
import java.awt.ScrollPane;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

public class ManagerGUI extends JFrame {

	private JPanel contentPane;
	static Instructor instructor2 = new Instructor();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerGUI frame = new ManagerGUI(instructor2);
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
	public ManagerGUI(Instructor instructor) {
		setResizable(false);

		setTitle("Yönetici Paneli");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1504, 933);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\bjkli\\OneDrive\\Masa\u00FCst\u00FC\\avatar3.png"));
		lblNewLabel_1.setBounds(90, 130, 60, 58);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Hoþ Geldiniz, " + instructor.getFirst_name().toUpperCase() + " "
				+ instructor.getLast_name().toUpperCase());
		lblNewLabel.setEnabled(false);
		lblNewLabel.setBounds(90, 35, 403, 25);
		lblNewLabel.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);

		JButton btn_manager_logout = new JButton("Çýkýþ Yap");
		btn_manager_logout.setBounds(1286, 31, 111, 40);
		btn_manager_logout.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_manager_logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();

			}
		});
		btn_manager_logout.setBackground(new Color(255, 192, 203));
		contentPane.add(btn_manager_logout);
				
		JButton instructor_settings = new JButton("Egitmen Yonetimi");
		instructor_settings.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 11));
		instructor_settings.setBorder(BorderFactory.createEmptyBorder());
		instructor_settings.setBounds(94, 279, 136, 58);
		contentPane.add(instructor_settings);
		instructor_settings.setForeground(Color.BLACK);
		instructor_settings.setBackground(Color.WHITE);

		JButton student_settings = new JButton("Ogrenci Yonetimi");
		student_settings.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 11));
		student_settings.setBorder(BorderFactory.createEmptyBorder());
		student_settings.setBounds(94, 375, 136, 58);
		contentPane.add(student_settings);
		student_settings.setBackground(Color.WHITE);
		student_settings.setForeground(Color.BLACK);

		JButton lesson_settings = new JButton("Ders Yonetimi");
		lesson_settings.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 11));
		lesson_settings.setBorder(BorderFactory.createEmptyBorder());
		lesson_settings.setBounds(94, 469, 136, 58);
		contentPane.add(lesson_settings);
		lesson_settings.setBackground(Color.WHITE);
		lesson_settings.setForeground(Color.BLACK);

		JButton faculty_settings = new JButton("Fakulte Yonetimi");
		faculty_settings.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 11));
		faculty_settings.setBorder(BorderFactory.createEmptyBorder());
		faculty_settings.setBounds(94, 566, 136, 58);
		contentPane.add(faculty_settings);
		faculty_settings.setBackground(Color.WHITE);
		faculty_settings.setForeground(Color.BLACK);
		
				JLabel lblNewLabel_2 = new JLabel(instructor.getFirst_name().toUpperCase() + " " + instructor.getLast_name().toUpperCase());
				lblNewLabel_2.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 11));
				lblNewLabel_2.setBounds(160, 143, 216, 20);
				contentPane.add(lblNewLabel_2);
				
				JLabel lblNewLabel_3 = new JLabel("Yonetici");
				lblNewLabel_3.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
				lblNewLabel_3.setBounds(160, 163, 46, 14);
				contentPane.add(lblNewLabel_3);
				
				JLabel lblNewLabel_4 = new JLabel("");
				lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\bjkli\\OneDrive\\Masa\u00FCst\u00FC\\Rectangle 1s.png"));
				lblNewLabel_4.setBounds(80, 91, 333, 163);
				contentPane.add(lblNewLabel_4);
				
				JButton btnNewButton = new JButton("Hesap Ayarlari");
				btnNewButton.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 11));
				btnNewButton.setBorder(BorderFactory.createEmptyBorder());
				btnNewButton.setBackground(Color.WHITE);
				btnNewButton.setBounds(94, 761, 136, 33);
				contentPane.add(btnNewButton);
				
				JLabel lblNewLabel_5 = new JLabel("");
				lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\bjkli\\OneDrive\\Masa\u00FCst\u00FC\\Rectangle 6.png"));
				lblNewLabel_5.setBounds(440, 82, 966, 728);
				contentPane.add(lblNewLabel_5);
		faculty_settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FacultyProcessGUI facultyProcess = new FacultyProcessGUI(instructor);
				facultyProcess.setVisible(true);
				dispose();

			}
		});
		lesson_settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				LessonProcessGUI lessonProcess = new LessonProcessGUI(instructor);
				lessonProcess.setVisible(true);
				dispose();

			}
		});
		student_settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				StudentProcessGUI studentProcess = new StudentProcessGUI(instructor);
				studentProcess.setVisible(true);
				dispose();

			}
		});
		instructor_settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				InstructorProcessGUI instructorProcess = new InstructorProcessGUI(instructor);
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