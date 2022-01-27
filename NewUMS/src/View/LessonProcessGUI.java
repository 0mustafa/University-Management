package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Model.Instructor;
import Model.Lesson;
import Model.LessonStudent;
import Model.Manager;
import Model.Student;

import javax.swing.JScrollPane;
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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;

public class LessonProcessGUI extends JFrame {

	private JPanel contentPane;
	static Manager manager = new Manager();
	static Lesson lesson = new Lesson();
	static LessonStudent lessonStudent = new LessonStudent();
	static Lesson selectedLesson = new Lesson();
	static Student selectedStudent = new Student();
	static Student student = new Student();
	private DefaultTableModel lessonModel = null;
	private Object[] lessonData = null;
	private DefaultTableModel lessonStudentModel = null;
	private DefaultTableModel lessonsOfStudentModel = null;
	private Object[] lessonStudentData = null;
	private Object[] lessonsOfStudentData = null;
	private JTextField txt_add_lesson;
	private JTextField txt_student_id;
	private JTable table_lesson;
	private JTable table_lesson_student;
	private JPopupMenu lessonPopup;
	private JPopupMenu lessonOfStudentPopup;
	private JTable table_lessons_of_student;
	private JTextField txt_search_lesson;
	private JTextField txt_search_student;
	//public String selectedLessonTableId = "0";
	//public String selectedLessonTableName = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LessonProcessGUI frame = new LessonProcessGUI(manager);
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
	public LessonProcessGUI(Manager manager) {
		setTitle("Ders Yonetimi");

		// ********** LESSON MODEL **********
		lessonModel = new DefaultTableModel();
		Object[] colLesson = new Object[2];
		colLesson[0] = "ID";
		colLesson[1] = "DERS AD";
		lessonModel.setColumnIdentifiers(colLesson);
		lessonData = new Object[2];
		for(Lesson ls : lesson.getAllLessons()) {
			lessonData[0] = ls.getLessonId();
			lessonData[1] = ls.getLessonName();
			lessonModel.addRow(lessonData);
		}
		
		// ********** LESSON STUDENT MODEL **********
		lessonStudentModel = new DefaultTableModel();
		Object[] colLessonStudent = new Object[7];
		colLessonStudent[0] = "ID";
		colLessonStudent[1] = "AD";
		colLessonStudent[2] = "SOYAD";
		colLessonStudent[3] = "OKUL NO";
		colLessonStudent[4] = "MAIL";
		colLessonStudent[5] = "FAKULTE";
		colLessonStudent[6] = "BOLUM";
		lessonStudentModel.setColumnIdentifiers(colLessonStudent);
		lessonStudentData = new Object[7];
		for(Student st : student.getAllStudents()) {
			lessonStudentData[0] = st.getId();
			lessonStudentData[1] = st.getFirst_name();
			lessonStudentData[2] = st.getLast_name();
			lessonStudentData[3] = st.getSchoolNumber();
			lessonStudentData[4] = st.getMail();
			lessonStudentData[5] = st.getFacultyName();
			lessonStudentData[6] = st.getDepartmentName();
			lessonStudentModel.addRow(lessonStudentData);
		}
		
		// ********** LESSONS OF STUDENT MODEL **********
		lessonsOfStudentModel = new DefaultTableModel();
		Object[] colLessonsOfStudent = new Object[2];
		colLessonsOfStudent[0] = "ID";
		colLessonsOfStudent[1] = "DERS AD";
		lessonsOfStudentModel.setColumnIdentifiers(colLessonsOfStudent);
		lessonsOfStudentData = new Object[2];

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1504, 809);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scroll_lesson = new JScrollPane();
		scroll_lesson.setBounds(38, 129, 513, 608);
		scroll_lesson.setBackground(Color.WHITE);
		contentPane.add(scroll_lesson);

		JLabel lblNewLabel_1_3_3 = new JLabel("Ders Ad");
		lblNewLabel_1_3_3.setBounds(602, 239, 95, 22);
		lblNewLabel_1_3_3.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(lblNewLabel_1_3_3);

		txt_add_lesson = new JTextField();
		txt_add_lesson.setBounds(602, 269, 261, 27);
		txt_add_lesson.setColumns(10);
		contentPane.add(txt_add_lesson);

		JButton btn_add_lesson = new JButton("Ekle");
		btn_add_lesson.setBounds(602, 313, 261, 40);
		btn_add_lesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txt_add_lesson.getText().length() == 0) {
					Helper.showMessage("Ders eklemek icin dersin adini girmelisiniz!");
				} else {
					if (lesson.addLesson(txt_add_lesson.getText())) {
						Helper.showMessage("Ders sisteme eklendi!");
						updateLessonTable();
					} else {
						Helper.showMessage("Hata oluþtu!");
					}
					txt_add_lesson.setText("");

				}

			}
		});
		btn_add_lesson.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_add_lesson.setBackground(new Color(245, 245, 245));
		contentPane.add(btn_add_lesson);

		//Disable editable for table
		table_lesson = new JTable(lessonModel) {
			public boolean isCellEditable(int row, int column) {
				Object o = getValueAt(row,column);
				return false;
			}
		};
		table_lesson.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_lesson.setBackground(Color.WHITE);
		lessonPopup();
		scroll_lesson.setViewportView(table_lesson);
		
		table_lesson.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {

					String selectedLessonTableId = (table_lesson.getValueAt(table_lesson.getSelectedRow(), 0).toString()); // Secilen dersin idsini bulur
					String selectedLessonTableName = table_lesson.getValueAt(table_lesson.getSelectedRow(), 1).toString(); // Secilen dersin fakulte adini bulur
					selectedLesson.setLessonId(Integer.parseInt(selectedLessonTableId));
					selectedLesson.setLessonName(selectedLessonTableName);	
				

				} catch (Exception ex) {

				}

			}
		});

		JButton btn_set_student_to_lesson = new JButton("Derse Ata");
		btn_set_student_to_lesson.setBounds(602, 548, 261, 40);
		btn_set_student_to_lesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (selectedLesson.getLessonName() == null || txt_student_id.getText().length() == 0) {
					Helper.showMessage(
							"Lutfen bir ders seciniz ve ardýndan atamak istediginiz ogrencinin id numarasini giriniz!");
				} else {
					LessonStudent ls = new LessonStudent();
					Student st = Student.getFetch(Integer.parseInt(txt_student_id.getText()));
					if(LessonStudent.getLessonsListString(st.getId()).contains(selectedLesson.getLessonName())){
						Helper.showMessage("Öðrenci daha önce bu derse atanmýþ!");
					}
					else if (ls.addLessonStudent(selectedLesson.getLessonId(), st.getFirst_name(), st.getLast_name(), Integer.parseInt(txt_student_id.getText()))) {
						
						System.out.println(st.getFirst_name() + " " +st.getLast_name());
						Helper.showMessage("Ogrenci Derse atandi.");
						updateLessonsOfStudentTable();
						
						
					} else {
						Helper.showMessage("Hata olustu!");
					}
				}

			}
		});
		btn_set_student_to_lesson.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_set_student_to_lesson.setBackground(new Color(245, 245, 245));
		contentPane.add(btn_set_student_to_lesson);

		JLabel lblNewLabel_1_3_1_1 = new JLabel("Ogrenci ID");
		lblNewLabel_1_3_1_1.setBounds(602, 451, 173, 22);
		lblNewLabel_1_3_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(lblNewLabel_1_3_1_1);

		JScrollPane scroll_lesson_student = new JScrollPane();
		scroll_lesson_student.setBounds(916, 129, 513, 267);
		scroll_lesson_student.setBackground(Color.WHITE);
		contentPane.add(scroll_lesson_student);

		table_lesson_student = new JTable(lessonStudentModel) {
			public boolean isCellEditable(int row, int column) {
				Object o = getValueAt(row,column);
				return false;
			}
		};
		table_lesson_student.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_lesson_student.setBackground(Color.WHITE);
		scroll_lesson_student.setViewportView(table_lesson_student);
		
		// Tabloda seçilen öðrencinin dersleri listelenir.
		table_lesson_student.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					txt_student_id.setText(table_lesson_student.getValueAt(table_lesson_student.getSelectedRow(), 0).toString());
					String selectedStudentTableId = (table_lesson_student.getValueAt(table_lesson_student.getSelectedRow(), 0).toString()); // Secilen dersin idsini bulur
					selectedStudent.setId(Integer.parseInt(selectedStudentTableId));
					updateLessonsOfStudentTable();
				
				} catch (Exception ex) {

				}

			}
		});
		
		txt_student_id = new JTextField();
		txt_student_id.setBounds(602, 484, 261, 27);
		txt_student_id.setColumns(10);
		contentPane.add(txt_student_id);
		
		JLabel welcome = new JLabel("Hoþ Geldiniz, " + manager.getFirst_name().toUpperCase() + " "
				+ manager.getLast_name().toUpperCase());
		welcome.setBounds(38, 33, 403, 25);
		welcome.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(welcome);
		
		JButton btn_manager_logout = new JButton("Menuye Don");
		btn_manager_logout.setBounds(1285, 18, 144, 40);
		btn_manager_logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ManagerGUI managerGUI = new ManagerGUI(manager);
				dispose();
				managerGUI.setVisible(true);
			}
		});
		btn_manager_logout.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_manager_logout.setBackground(new Color(255, 192, 203));
		contentPane.add(btn_manager_logout);
		
		JScrollPane scroll_lessons_of_student = new JScrollPane();
		scroll_lessons_of_student.setBounds(916, 431, 513, 306);
		scroll_lessons_of_student.setBackground(Color.WHITE);
		contentPane.add(scroll_lessons_of_student);
		
		table_lessons_of_student = new JTable(lessonsOfStudentModel) {
			public boolean isCellEditable(int row, int column) {
				Object o = getValueAt(row,column);
				return false;
			}
		};
		table_lessons_of_student.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll_lessons_of_student.setViewportView(table_lessons_of_student);
		
		lessonOfStudentPopup();
		
		txt_search_lesson = new JTextField();
		txt_search_lesson.setForeground(Color.LIGHT_GRAY);
		txt_search_lesson.setText("Ders adi veya id giriniz");
		txt_search_lesson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txt_search_lesson.setForeground(Color.BLACK);
				txt_search_lesson.setText("");
				
			}
		});
		txt_search_lesson.setBounds(38, 90, 193, 28);
		contentPane.add(txt_search_lesson);
		txt_search_lesson.setColumns(10);
		
		JButton btn_search_lesson = new JButton("ARA");
		btn_search_lesson.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btn_search_lesson.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel clearModel = (DefaultTableModel) table_lesson.getModel();
				clearModel.setRowCount(0);
				
				

				for(Lesson ls : lesson.getAllLessons()) {
					if(ls.getLessonName().contains(txt_search_lesson.getText()) || String.valueOf(ls.getLessonId()).equals(txt_search_lesson.getText())){
						lessonData[0] = ls.getLessonId();
						lessonData[1] = ls.getLessonName();
						lessonModel.addRow(lessonData);
					}
				}
			}
		});
		btn_search_lesson.setBackground(Color.WHITE);
		btn_search_lesson.setBounds(235, 90, 66, 28);
		contentPane.add(btn_search_lesson);
		
		JButton btn_list_all_lessons = new JButton("TUM DERSLERI LISTELE");
		btn_list_all_lessons.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btn_list_all_lessons.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				txt_search_lesson.setForeground(Color.LIGHT_GRAY);
				txt_search_lesson.setText("Ders adi veya id giriniz");
				updateLessonTable();
				
			}
		});
		btn_list_all_lessons.setBackground(Color.WHITE);
		btn_list_all_lessons.setBounds(330, 90, 221, 28);
		contentPane.add(btn_list_all_lessons);
		
		txt_search_student = new JTextField();
		txt_search_student.setText("Ogrenci adi veya no giriniz");
		txt_search_student.setForeground(Color.LIGHT_GRAY);
		txt_search_student.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txt_search_student.setForeground(Color.BLACK);
				txt_search_student.setText("");
				
			}
		});
		txt_search_student.setColumns(10);
		txt_search_student.setBounds(916, 90, 193, 28);
		contentPane.add(txt_search_student);
		
		JButton btn_search_student = new JButton("ARA");
		btn_search_student.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel clearModel = (DefaultTableModel) table_lesson_student.getModel();
				clearModel.setRowCount(0);
				
				

				for(Student st : student.getAllStudents()) {
					if(st.getFirst_name().contains(txt_search_student.getText()) || st.getSchoolNumber().equals(txt_search_student.getText())){
						lessonStudentData[0] = st.getId();
						lessonStudentData[1] = st.getFirst_name();
						lessonStudentData[2] = st.getLast_name();
						lessonStudentData[3] = st.getSchoolNumber();
						lessonStudentData[4] = st.getMail();
						lessonStudentData[5] = st.getFacultyName();
						lessonStudentData[6] = st.getDepartmentName();
						lessonStudentModel.addRow(lessonStudentData);
					}
				}
			}
		});
		btn_search_student.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btn_search_student.setBackground(Color.WHITE);
		btn_search_student.setBounds(1113, 90, 66, 28);
		contentPane.add(btn_search_student);
		
		JButton btn_list_all_students = new JButton("TUM OGRENCILERI LISTELE");
		btn_list_all_students.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				txt_search_student.setForeground(Color.LIGHT_GRAY);
				txt_search_student.setText("Ogrenci adi veya no giriniz");
				updateLessonStudentTable();
				updateLessonsOfStudentTable();
				
			}
		});
		btn_list_all_students.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btn_list_all_students.setBackground(Color.WHITE);
		btn_list_all_students.setBounds(1208, 90, 221, 28);
		contentPane.add(btn_list_all_students);
		
	}
	
	public void updateLessonTable() {
		DefaultTableModel clearModel = (DefaultTableModel) table_lesson.getModel();
		clearModel.setRowCount(0);
		
		

		for(Lesson ls : lesson.getAllLessons()) {
			lessonData[0] = ls.getLessonId();
			lessonData[1] = ls.getLessonName();
			lessonModel.addRow(lessonData);
		}
	}

	public void updateLessonStudentTable() {
		DefaultTableModel clearModel = (DefaultTableModel) table_lesson_student.getModel();
		clearModel.setRowCount(0);

		for(Student st : student.getAllStudents()) {
			lessonStudentData[0] = st.getId();
			lessonStudentData[1] = st.getFirst_name();
			lessonStudentData[2] = st.getLast_name();
			lessonStudentData[3] = st.getSchoolNumber();
			lessonStudentData[4] = st.getMail();
			lessonStudentData[5] = st.getFacultyName();
			lessonStudentData[6] = st.getDepartmentName();
			lessonStudentModel.addRow(lessonStudentData);
		}
	}
	
	public void updateLessonsOfStudentTable() {
		DefaultTableModel clearModel = (DefaultTableModel) table_lessons_of_student.getModel();
		clearModel.setRowCount(0);
		
		lessonsOfStudentData = new Object[2];
		for (LessonStudent ls : LessonStudent.getLessonsListObject(selectedStudent.getId())) {
			lessonsOfStudentData[0] = ls.getLessonId();
			lessonsOfStudentData[1] = ls.getLessonName();
			lessonsOfStudentModel.addRow(lessonsOfStudentData);
		}
		
	}
	
	public void lessonOfStudentPopup() {
		lessonOfStudentPopup = new JPopupMenu();
		JMenuItem removeLesson = new JMenuItem("Sil");
		lessonOfStudentPopup.add(removeLesson);
		
		removeLesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer.parseInt(table_lessons_of_student.getValueAt(table_lessons_of_student.getSelectedRow(), 0).toString());
				if (Helper.showWarning("Dersi silmek istediginizden emin misiniz?") == 0) {
					if (lessonStudent.removeLessonStudent(selectedID, selectedStudent.getId())) {
						Helper.showMessage("Islem basarili!");

						updateLessonsOfStudentTable();
					} else
						Helper.showMessage("Hata olustu!");
				}

			}
		});
		
		table_lessons_of_student.setComponentPopupMenu(lessonOfStudentPopup); // Yukardaki popup menüyü tabloya atadýk.
		table_lessons_of_student.addMouseListener(new MouseAdapter() { // sag tikladigimizda hangi satýrda oldugumuzu bulduk
			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = table_lessons_of_student.rowAtPoint(point);
				table_lessons_of_student.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
		
	}
	
	public void lessonPopup() {
		lessonPopup = new JPopupMenu();
		JMenuItem updateLesson = new JMenuItem("Güncelle");
		JMenuItem removeLesson = new JMenuItem("Sil");
		lessonPopup.add(updateLesson);
		lessonPopup.add(removeLesson);

		updateLesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer.parseInt(table_lesson.getValueAt(table_lesson.getSelectedRow(), 0).toString());
				Lesson selectedLesson = lesson.getFetch(selectedID);
				LessonUpdateGUI lGUI = new LessonUpdateGUI(selectedLesson);
				lGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				lGUI.setVisible(true);
				lGUI.addWindowListener(new WindowAdapter() { // Açýlan pencereyi dinliyoruz

					@Override
					public void windowClosed(WindowEvent e) { // Açýlan pencere kapatýldýðýnda db'de veri güncellendiði
																// için tablomuzu da güncelliyoruz.
						updateLessonTable();

					}

				});

			}
		});

		removeLesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer.parseInt(table_lesson.getValueAt(table_lesson.getSelectedRow(), 0).toString());
				if (Helper.showWarning("Dersi silmek istediginizden emin misiniz?") == 0) {
					if (lesson.removeLesson(selectedID)) {
						Helper.showMessage("Islem basarili!");

						updateLessonTable();
					} else
						Helper.showMessage("Hata olustu!");
				}

			}
		});

		table_lesson.setComponentPopupMenu(lessonPopup); // Yukardaki popup menüyü tabloya atadýk.
		table_lesson.addMouseListener(new MouseAdapter() { // sag tikladigimizda hangi satýrda oldugumuzu bulduk

			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = table_lesson.rowAtPoint(point);
				table_lesson.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
	}
}
