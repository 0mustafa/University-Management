package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;

import Model.Student;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class StudentGUI extends JFrame {

	static Student student = new Student();

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel studentModel = null;
	private Object[] studentData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGUI frame = new StudentGUI(student);
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
	public StudentGUI(Student student) {
		setTitle("Ogrenci Paneli");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 691);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"Hos Geldiniz " + student.getFirst_name().toUpperCase() + " " + student.getLast_name().toUpperCase());
		lblNewLabel.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel.setBounds(36, 36, 345, 23);
		contentPane.add(lblNewLabel);

		JButton btn_manager_logout = new JButton("Cikis Yap");
		btn_manager_logout.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_manager_logout.setForeground(Color.BLACK);
		btn_manager_logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();

			}
		});
		btn_manager_logout.setBackground(new Color(255, 192, 203));
		btn_manager_logout.setBounds(860, 36, 110, 44);
		contentPane.add(btn_manager_logout);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 290, 934, 339);
		contentPane.add(scrollPane);

		studentModel = new DefaultTableModel();
		Object[] colStudent = new Object[2];
		colStudent[0] = "ID";
		colStudent[1]= "DERS ADI";
		studentModel.setColumnIdentifiers(colStudent);
		studentData = new Object[2];
		for(int i=0;i<student.getStudentsLessons(student).size(); i++) {
			studentData[0] = student.getStudentsLessons(student).get(i).getLessonId();
			studentData[1] = student.getStudentsLessons(student).get(i).getLessonName();;
			studentModel.addRow(studentData);
		}
		

		table = new JTable(studentModel);
		setBackground(Color.WHITE);
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setText("AD:");
		lblNewLabel_1.setFont(new Font("Montserrat Medium", Font.BOLD, 18));
		lblNewLabel_1.setBounds(36, 111, 110, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel();
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setText("SOYAD:");
		lblNewLabel_1_1.setFont(new Font("Montserrat Medium", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(36, 136, 110, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel();
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setText("MAIL:");
		lblNewLabel_1_1_1.setFont(new Font("Montserrat Medium", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(36, 161, 110, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel();
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setText("TC NO:");
		lblNewLabel_1_1_1_1.setFont(new Font("Montserrat Medium", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(36, 186, 110, 23);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel();
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setText("FAKULTE:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Montserrat Medium", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1.setBounds(36, 211, 110, 23);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel();
		lblNewLabel_1_1_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_2.setText("BOLUM:");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Montserrat Medium", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_2.setBounds(36, 236, 110, 23);
		contentPane.add(lblNewLabel_1_1_1_1_2);
		
		JLabel lblNewLabel_1_2 = new JLabel(student.getFirst_name());
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(159, 111, 222, 23);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel(student.getLast_name());
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_3.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(159, 136, 222, 23);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel(student.getMail());
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_4.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(159, 161, 222, 23);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel(student.getIdentityNumber());
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_5.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		lblNewLabel_1_5.setBounds(159, 186, 222, 23);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel(student.getFacultyName());
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_6.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		lblNewLabel_1_6.setBounds(159, 211, 222, 23);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel(student.getDepartmentName());
		lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_7.setFont(new Font("Montserrat Medium", Font.PLAIN, 16));
		lblNewLabel_1_7.setBounds(159, 236, 222, 23);
		contentPane.add(lblNewLabel_1_7);
	}
}
