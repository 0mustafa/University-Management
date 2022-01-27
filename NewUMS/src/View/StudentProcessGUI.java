package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

import Helper.Dictionary;
import Helper.Helper;
import Model.Department;
import Model.Faculty;
import Model.Instructor;
import Model.Manager;
import Model.Student;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

public class StudentProcessGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_add_student_first_name;
	private JTextField txt_add_student_last_name;
	private JTextField txt_add_student_tc;
	private JTextField txt_add_student_score;
	private JTextField txt_remove_student_id;
	private JTable table_student;
	static Manager manager = new Manager();
	static Student student = new Student();
	static Faculty faculty = new Faculty();
	static Department department = new Department();
	private DefaultTableModel studentModel = null;
	private Object[] studentData = null;
	private Dictionary dictionary;
	private int comboFacultyKey = 0;
	private int comboDepartmentKey = 0;
	private String comboFacultyValue;
	private String comboDepartmentValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentProcessGUI frame = new StudentProcessGUI(manager);
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
	public StudentProcessGUI(Manager manager) {

		// ********** STUDENT MODEL **********
		studentModel = new DefaultTableModel();
		Object[] colStudent = new Object[10];
		colStudent[0] = "ID";
		colStudent[1] = "Ad";
		colStudent[2] = "SOYAD";
		colStudent[3] = "SKOR";
		colStudent[4] = "NUMARA";
		colStudent[5] = "TC NO";
		colStudent[6] = "MAIL";
		colStudent[7] = "FAKULTE";
		colStudent[8] = "BOLUM";
		colStudent[9] = "DOGUM TARIHI";
		studentModel.setColumnIdentifiers(colStudent);
		studentData = new Object[10];
		for(Student st : student.getAllStudents()) {
			studentData[0] = st.getId();
			studentData[1] = st.getFirst_name();
			studentData[2] = st.getLast_name();
			studentData[3] = st.getScore();
			studentData[4] = st.getNumber();
			studentData[5] = st.getIdentityNumber();
			studentData[6] = st.getMail();
			studentData[7] = st.getFacultyName();
			studentData[8] = st.getDepartmentName();
			studentData[9] = st.getBirth();
			studentModel.addRow(studentData);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1504, 809);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.WHITE);
		scrollPane_1.setBounds(41, 106, 983, 627);
		contentPane.add(scrollPane_1);

		TableRowSorter studentSorter = new TableRowSorter(studentModel);
		table_student = new JTable(studentModel);
		table_student.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_student.setRowSorter(studentSorter);
		scrollPane_1.setViewportView(table_student);

		JLabel lblNewLabel_1_4 = new JLabel("Ad");
		lblNewLabel_1_4.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(1075, 111, 46, 14);
		contentPane.add(lblNewLabel_1_4);

		txt_add_student_first_name = new JTextField();
		txt_add_student_first_name.setColumns(10);
		txt_add_student_first_name.setBounds(1075, 132, 178, 27);
		contentPane.add(txt_add_student_first_name);

		JLabel lblNewLabel_1_1_1 = new JLabel("Soyad");
		lblNewLabel_1_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(1278, 106, 55, 23);
		contentPane.add(lblNewLabel_1_1_1);

		txt_add_student_last_name = new JTextField();
		txt_add_student_last_name.setColumns(10);
		txt_add_student_last_name.setBounds(1278, 131, 178, 27);
		contentPane.add(txt_add_student_last_name);

		JLabel lblNewLabel_1_2_4 = new JLabel("TC NO");
		lblNewLabel_1_2_4.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_2_4.setBounds(1075, 189, 59, 23);
		contentPane.add(lblNewLabel_1_2_4);

		txt_add_student_tc = new JTextField();
		txt_add_student_tc.setColumns(10);
		txt_add_student_tc.setBounds(1075, 213, 178, 27);
		contentPane.add(txt_add_student_tc);

		JLabel lblNewLabel_1_2_2_1 = new JLabel("Fakulte");
		lblNewLabel_1_2_2_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_2_2_1.setBounds(1075, 266, 68, 23);
		contentPane.add(lblNewLabel_1_2_2_1);

		JLabel lblNewLabel_1_2_3_1 = new JLabel("Bolum");
		lblNewLabel_1_2_3_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_2_3_1.setBounds(1075, 354, 61, 23);
		contentPane.add(lblNewLabel_1_2_3_1);

		JLabel lblNewLabel_1_2_2_1_1 = new JLabel("Puan");
		lblNewLabel_1_2_2_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_2_2_1_1.setBounds(1075, 448, 67, 22);
		contentPane.add(lblNewLabel_1_2_2_1_1);

		txt_add_student_score = new JTextField();
		txt_add_student_score.setColumns(10);
		txt_add_student_score.setBounds(1075, 473, 178, 27);
		contentPane.add(txt_add_student_score);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Dogum Tarihi");
		lblNewLabel_1_2_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_2_1_1.setBounds(1278, 188, 125, 23);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		JDateChooser txt_add_student_birth = new JDateChooser();
		txt_add_student_birth.setBounds(1278, 212, 178, 27);
		contentPane.add(txt_add_student_birth);
		
		JComboBox<Object> combo_faculty = new JComboBox<Object>();
		combo_faculty.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		combo_faculty.setBackground(Color.WHITE);
		combo_faculty.setBounds(1075, 296, 381, 35);
		contentPane.add(combo_faculty);
		combo_faculty.addItem("Seciniz");
		for(Faculty fc : faculty.getAllFaculties()) {
			combo_faculty.addItem(new Dictionary(fc.getFacultyId(), fc.getFacultyName()));
		}
		combo_faculty.addActionListener(e -> { // kombo menuden secim yapildiginda gerceklesecek eylem
			if(combo_faculty.getSelectedIndex() == 0) {
				comboFacultyKey = 0;
				comboFacultyValue = null;
			}else {
				JComboBox c = (JComboBox) e.getSource();
				dictionary = (Dictionary) c.getSelectedItem();
				System.out.println(dictionary.getKey() + " " + dictionary.getValue());
				comboFacultyKey = dictionary.getKey();
				comboFacultyValue = dictionary.getValue();
			}
		});
		
		JComboBox<Object> combo_department = new JComboBox<Object>();
		combo_department.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		combo_department.setBackground(Color.WHITE);
		combo_department.setBounds(1075, 385, 381, 35);
		contentPane.add(combo_department);
		combo_department.addItem("Seciniz");
		for(Department dp : department.getAllDepartments()) {
			combo_department.addItem(new Dictionary(dp.getId(), dp.getDepartmentName()));
			
		}
		combo_department.addActionListener(e -> { // kombo menuden secim yapildiginda gerceklesecek eylem
			if(combo_department.getSelectedIndex() == 0) {
				comboDepartmentKey = 0;
				comboDepartmentValue = null;
			}else {
				JComboBox c = (JComboBox) e.getSource();
				dictionary = (Dictionary) c.getSelectedItem();
				System.out.println(dictionary.getKey() + " " + dictionary.getValue());
				comboDepartmentKey = dictionary.getKey();
				comboDepartmentValue = dictionary.getValue();
			}
			
		});

		JButton btn_add_student = new JButton("Ekle");
		btn_add_student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txt_add_student_first_name.getText().length() == 0
						|| txt_add_student_last_name.getText().length() == 0
						|| txt_add_student_tc.getText().length() != 11
						|| txt_add_student_birth.getDate().toString().length() == 0
						|| comboFacultyValue == null
						|| comboDepartmentValue == null
						|| txt_add_student_score.getText().length() == 0) {
					Helper.showMessage("Lutfen tum alanlari doldurunuz.");
				} else {

					student.setFirst_name(txt_add_student_first_name.getText());
					student.setLast_name(txt_add_student_last_name.getText());
					student.setIdentityNumber(txt_add_student_tc.getText());
					student.setBirth(txt_add_student_birth.getDate().toString());
					student.setFacultyName(comboFacultyValue);
					student.setDepartmentName(comboDepartmentValue);
					student.setScore(Double.parseDouble(txt_add_student_score.getText()));
					student.setNumber(String.format("%2d%02d%02d%02d", LocalDate.now().getYear() % 100,
							comboFacultyKey,
							comboDepartmentKey, 
							student.getAllStudents().get(student.getAllStudents().size()-1).getId()+1));
					if (student.addStudent(student)) {
						updateStudentModel();
						Helper.showMessage("Ogrenci sisteme eklendi.");
					} else
						Helper.showMessage("Ogrenci sisteme eklenirken hata olustu!");

					txt_add_student_first_name.setText("");
					txt_add_student_last_name.setText("");
					txt_add_student_tc.setText("");
					txt_add_student_birth.setDate(null);
					txt_add_student_score.setText("");
					combo_faculty.setSelectedIndex(0);
					combo_department.setSelectedIndex(0);
				}

			}
		});
		btn_add_student.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_add_student.setBackground(new Color(245, 245, 245));
		btn_add_student.setBounds(1202, 540, 122, 40);
		contentPane.add(btn_add_student);

		JLabel lblNewLabel_2_1 = new JLabel("Ogrenci Id");
		lblNewLabel_2_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(1075, 605, 99, 22);
		contentPane.add(lblNewLabel_2_1);

		txt_remove_student_id = new JTextField();
		txt_remove_student_id.setColumns(10);
		txt_remove_student_id.setBounds(1075, 638, 318, 27);
		contentPane.add(txt_remove_student_id);

		JButton btn_remove_student = new JButton("Sil");
		btn_remove_student.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (txt_remove_student_id.getText().length() == 0)
					Helper.showMessage("Lutfen silmek istediginiz Ogrencinin tablodaki id numarasini giriniz.");
				else {
					if (Helper.showWarning("Ogrenciyi silmek istediginizden emin misiniz?") == 0) {
						if (student.removeStudent(Integer.parseInt(txt_remove_student_id.getText()))) {
							updateStudentModel();
							Helper.showMessage("Ogrenci silindi.");
						} else
							Helper.showMessage("bole bir Ogrenci bulunmuyor!");

						txt_remove_student_id.setText("");
					}
				}

			}
		});
		btn_remove_student.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_remove_student.setBackground(new Color(245, 245, 245));
		btn_remove_student.setBounds(1202, 693, 122, 40);
		contentPane.add(btn_remove_student);
		
		JLabel welcome = new JLabel("Hoþ Geldiniz, " + manager.getFirst_name().toUpperCase() + " "
				+ manager.getLast_name().toUpperCase());
		welcome.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		welcome.setBounds(41, 37, 403, 25);
		contentPane.add(welcome);
		
		JButton btn_manager_logout = new JButton("Menuye Don");
		btn_manager_logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ManagerGUI managerGUI = new ManagerGUI(manager);
				managerGUI.setVisible(true);
				dispose();
			}
		});
		btn_manager_logout.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_manager_logout.setBackground(new Color(255, 192, 203));
		btn_manager_logout.setBounds(1278, 22, 144, 40);
		contentPane.add(btn_manager_logout);
		
		table_student.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					txt_remove_student_id
							.setText(table_student.getValueAt(table_student.getSelectedRow(), 0).toString());

				} catch (Exception ex) {

				}

			}
		});

		table_student.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				if (e.getType() == TableModelEvent.UPDATE) {

					int selectID = Integer
							.parseInt(table_student.getValueAt(table_student.getSelectedRow(), 0).toString());
					String selectName = table_student.getValueAt(table_student.getSelectedRow(), 1).toString();
					String selectSurname = table_student.getValueAt(table_student.getSelectedRow(), 2).toString();
					Double selectScore = Double
							.parseDouble(table_student.getValueAt(table_student.getSelectedRow(), 3).toString());
					String selectNumber;
					try {

						selectNumber = table_student.getValueAt(table_student.getSelectedRow(), 4).toString();
					} catch (Exception e2) {
						selectNumber = "NULL";
					}
					String selectTcNo = table_student.getValueAt(table_student.getSelectedRow(), 5).toString();
					String selectMail = table_student.getValueAt(table_student.getSelectedRow(), 6).toString();
					String selectFaculty = table_student.getValueAt(table_student.getSelectedRow(), 7).toString();
					String selectDepartment = table_student.getValueAt(table_student.getSelectedRow(), 8).toString();
					String selectBirth = table_student.getValueAt(table_student.getSelectedRow(), 9).toString();

					if (student.updateStudent(selectID, selectName, selectSurname, selectScore, selectNumber,
							selectTcNo, selectMail, selectFaculty, selectDepartment, selectBirth)) {
						Helper.showMessage("Egitmen bilgileri guncellendi.");
					}

				}

			}
		});

	}
	
	public void updateStudentModel() {
		DefaultTableModel clearModel = (DefaultTableModel) table_student.getModel();
		clearModel.setRowCount(0);

		for(Student st : student.getAllStudents()) {
			studentData[0] = st.getId();
			studentData[1] = st.getFirst_name();
			studentData[2] = st.getLast_name();
			studentData[3] = st.getScore();
			studentData[4] = st.getNumber();
			studentData[5] = st.getIdentityNumber();
			studentData[6] = st.getMail();
			studentData[7] = st.getFacultyName();
			studentData[8] = st.getDepartmentName();
			studentData[9] = st.getBirth();
			studentModel.addRow(studentData);
		}
	}
}
