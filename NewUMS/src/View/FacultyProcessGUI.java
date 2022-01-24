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

import Helper.Dictionary;
import Helper.Helper;
import Model.Department;
import Model.Faculty;
import Model.Instructor;

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
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class FacultyProcessGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_faculty_name;
	private JTextField txt_department_name;
	private JTable table_faculty;
	private JTable table_department;
	static Faculty faculty = new Faculty();
	static Department department = new Department();
	static Instructor instructor = new Instructor();
	private DefaultTableModel facultyModel = null;
	private DefaultTableModel departmentModel = null;
	private Object[] facultyData = null;
	private Object[] departmentData = null;
	private JPopupMenu facultyPopup;
	private JPopupMenu departmentPopup;
	public String selectedFacultyTableId = "0";
	public String selectedFacultyTableName = null;
	private Dictionary dictionary;
	private int comboKey = 0;
	private String comboValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyProcessGUI frame = new FacultyProcessGUI(instructor);
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
	public FacultyProcessGUI(Instructor instructor) {
		setTitle("Fakulte Yonetimi");
		
		// ********** FACULTY Model **********
		facultyModel = new DefaultTableModel();
		Object[] colFaculty = new Object[2];
		colFaculty[0] = "ID";
		colFaculty[1] = "FAKULTE AD";
		facultyModel.setColumnIdentifiers(colFaculty);
		facultyData = new Object[2];
		for(Faculty fc : faculty.getAllFaculties()) {
			facultyData[0] = fc.getFacultyId();
			facultyData[1] = fc.getFacultyName();
			facultyModel.addRow(facultyData);
		}
		
		// ********** DEPARTMENT MODEL **********
		departmentModel = new DefaultTableModel();
		Object[] colDepartment = new Object[3];
		colDepartment[0] = "ID";
		colDepartment[1] = "BOLUM AD";
		colDepartment[2] = "FAKULTE AD";
		departmentModel.setColumnIdentifiers(colDepartment);
		departmentData = new Object[3];
		for(Department dp : department.getAllDepartments()) {
			departmentData[0] = dp.getId();
			departmentData[1] = dp.getDepartmentName();
			departmentData[2] = dp.getFaculty().getFacultyName();
			departmentModel.addRow(departmentData);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1504, 809);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scroll_faculty = new JScrollPane();
		scroll_faculty.setBackground(Color.WHITE);
		scroll_faculty.setBounds(43, 86, 513, 647);
		contentPane.add(scroll_faculty);
		
		// POPUP MENU
		facultyPopup = new JPopupMenu();
		JMenuItem updateFaculty = new JMenuItem("Güncelle");
		JMenuItem removeFaculty = new JMenuItem("Sil");
		facultyPopup.add(updateFaculty);
		facultyPopup.add(removeFaculty);

		updateFaculty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer
						.parseInt(table_faculty.getValueAt(table_faculty.getSelectedRow(), 0).toString());
				Faculty selectedFaculty = faculty.getFetch(selectedID);
				FacultyUpdateGUI uGUI = new FacultyUpdateGUI(selectedFaculty);
				uGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				uGUI.setVisible(true);
				uGUI.addWindowListener(new WindowAdapter() { // Açýlan pencereyi dinliyoruz

					@Override
					public void windowClosed(WindowEvent e) { // Açýlan pencere kapatýldýðýnda db'de veri güncellendiði
																// için tablomuzu da güncelliyoruz.
						updateDepartmentTable();
						updateFacultyModel();

					}

				});

			}
		});

		removeFaculty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer
						.parseInt(table_faculty.getValueAt(table_faculty.getSelectedRow(), 0).toString());
				if (Helper.showWarning("Fakülteyi silmek istediðinizden emin misiniz?") == 0) {
					if (faculty.removFaculty(selectedID)) {
						Helper.showMessage("Ýþlem baþarýlý!");

						updateDepartmentTable();
						updateFacultyModel();
					} else
						Helper.showMessage("Hata oluþtu!");
				}

			}
		});
		
		table_faculty = new JTable(facultyModel) {
			public boolean isCellEditable(int row, int column) {
				Object o = getValueAt(row,column);
				return false;
			}
		};
		table_faculty.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_faculty.setComponentPopupMenu(facultyPopup); // Yukardaki popup menüyü tabloya atadýk.
		table_faculty.addMouseListener(new MouseAdapter() { // sað týkladýðýmýzda hangi satýrda olduðumuzu bulduk

			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = table_faculty.rowAtPoint(point);
				table_faculty.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
		scroll_faculty.setViewportView(table_faculty);
		
		JLabel lblNewLabel_1_3 = new JLabel("Fakulte Ad");
		lblNewLabel_1_3.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(607, 86, 98, 23);
		contentPane.add(lblNewLabel_1_3);
		
		txt_faculty_name = new JTextField();
		txt_faculty_name.setColumns(10);
		txt_faculty_name.setBounds(607, 116, 261, 27);
		contentPane.add(txt_faculty_name);
		
		JButton btn_add_faculty = new JButton("Ekle");
		btn_add_faculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txt_faculty_name.getText().length() == 0) {
					Helper.showMessage("Lütfen tüm alanlarý doldurunuz!");
				} else {
					if (faculty.addFaculty(txt_faculty_name.getText())) {
						updateFacultyModel();
						updateDepartmentTable();
						Helper.showMessage("Fakülte sisteme eklendi.");
					} else
						Helper.showMessage("Fakülte sisteme eklenemedi, tekrar deneyiniz.");

					txt_faculty_name.setText("");
				}

			}
		});
		btn_add_faculty.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_add_faculty.setBackground(new Color(245, 245, 245));
		btn_add_faculty.setBounds(607, 160, 71, 31);
		contentPane.add(btn_add_faculty);
		
		// DEPARTMENT POPUP
		departmentPopup();
		
		JScrollPane scroll_department = new JScrollPane();
		scroll_department.setBackground(Color.WHITE);
		scroll_department.setBounds(918, 86, 513, 647);
		contentPane.add(scroll_department);
		
		table_department = new JTable(departmentModel) {
			public boolean isCellEditable(int row, int column) {
				Object o = getValueAt(row,column);
				return false;
			}
		};
		table_department.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_department.setComponentPopupMenu(departmentPopup); // Yukardaki popup menüyü tabloya atadýk.
		table_department.addMouseListener(new MouseAdapter() { // sag tikladigimizda hangi satýrda oldugumuzu bulduk

			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = table_department.rowAtPoint(point);
				table_department.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
		scroll_department.setViewportView(table_department);
		
		ListSelectionModel model = table_faculty.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {

					selectedFacultyTableId = table_faculty.getValueAt(table_faculty.getSelectedRow(), 0).toString(); // Secilen fakultenin idsini bulur
																														
					selectedFacultyTableName = table_faculty.getValueAt(table_faculty.getSelectedRow(), 1).toString(); // Secilen fakultenin fakulte adini bulur
																														
					updateDepartmentTable();

				} catch (Exception ex) {

				}

			}
		});
		
		JComboBox<Object> combo_department = new JComboBox<Object>();
		combo_department.setBackground(Color.WHITE);
		combo_department.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		combo_department.setBounds(607, 527, 261, 40);
		for(Department dp : department.getAllDepartments()) {
			if(dp.getFaculty().getFacultyName() == null)
				combo_department.addItem(new Dictionary(dp.getId(), dp.getDepartmentName()));
		}
		
		combo_department.addActionListener(e -> { // kombo menuden secim yapildiginda gerceklesecek eylem

			Department dep = new Department();
			JComboBox c = (JComboBox) e.getSource();
			dictionary = (Dictionary) c.getSelectedItem();
			System.out.println(dictionary.getKey() + " " + dictionary.getValue());
			comboKey = dictionary.getKey();
			comboValue = dictionary.getValue();

		});
		
		contentPane.add(combo_department);
		
		// Seçilen fakülteye seçilen departman atanýyor
		JButton btn_set_department_to_faculty = new JButton("Fakulteye Ata");
		btn_set_department_to_faculty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedFacultyTableName == null || comboKey == 0) {
					Helper.showMessage("Fakülte ve bölüm seçmeniz gerekli!");
				} else {
					Department dp = new Department();
					Faculty fc = new Faculty();
					dp = dp.getFetch(comboKey);
					dp.setFaculty(fc.getFetch(selectedFacultyTableName));
					if (dp.updateDepartment(dp)) {
						Helper.showMessage("Bilgiler güncellendi!");
						// combo_department.remove(combo_department.getSelectedIndex());

					} else
						Helper.showMessage("Hata oluþtu!");
					updateDepartmentTable();
					departmentPopup();
				}

			}
		});
		btn_set_department_to_faculty.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_set_department_to_faculty.setBackground(new Color(245, 245, 245));
		btn_set_department_to_faculty.setBounds(607, 591, 157, 31);
		contentPane.add(btn_set_department_to_faculty);
		
		JButton btn_list_departments = new JButton("Bolumleri Listele");
		btn_list_departments.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedFacultyTableId = "0";
				updateDepartmentTable();
				departmentPopup();
			}
		});
		btn_list_departments.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_list_departments.setBackground(new Color(245, 245, 245));
		btn_list_departments.setBounds(607, 684, 261, 40);
		contentPane.add(btn_list_departments);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Atanabilir Bolumler");
		lblNewLabel_1_3_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(607, 494, 177, 23);
		contentPane.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Bolum Ad");
		lblNewLabel_1_3_2.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_3_2.setBounds(607, 329, 91, 23);
		contentPane.add(lblNewLabel_1_3_2);
		
		txt_department_name = new JTextField();
		txt_department_name.setColumns(10);
		txt_department_name.setBounds(607, 359, 261, 27);
		contentPane.add(txt_department_name);
		
		JButton btn_add_department = new JButton("Ekle");
		btn_add_department.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txt_department_name.getText().length() == 0) {
					Helper.showMessage("Lütfen eklemek istediðiniz bolumun adini giriniz!");
				} else {
					Department dep = new Department();
					boolean kontrol = dep.addDepartment(txt_department_name.getText());
					if (kontrol) {
						dep = dep.getAllDepartments().get(dep.getAllDepartments().size() - 1);
						Helper.showMessage("Bolum sisteme eklendi!");
						txt_department_name.setText("");
						combo_department.addItem(new Dictionary(dep.getId(), dep.getDepartmentName()));
						updateDepartmentTable();
					} else {
						Helper.showMessage("Bolum sisteme eklenemedi!");
					}

				}
				departmentPopup();
			}
		});
		btn_add_department.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_add_department.setBackground(new Color(245, 245, 245));
		btn_add_department.setBounds(607, 403, 71, 31);
		contentPane.add(btn_add_department);
		
		JLabel welcome = new JLabel("Hoþ Geldiniz, " + instructor.getFirst_name().toUpperCase() + " "
				+ instructor.getLast_name().toUpperCase());
		welcome.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		welcome.setBounds(43, 31, 403, 25);
		contentPane.add(welcome);
		
		JButton btn_manager_logout = new JButton("Menuye Don");
		btn_manager_logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ManagerGUI managerGUI = new ManagerGUI(instructor);
				dispose();
				managerGUI.setVisible(true);
			}
		});
		btn_manager_logout.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_manager_logout.setBackground(new Color(255, 192, 203));
		btn_manager_logout.setBounds(1287, 16, 144, 40);
		contentPane.add(btn_manager_logout);
	}
	
	// fakülteye týklandýðýnda bölümleri listelenir
	public void updateDepartmentTable() {
		DefaultTableModel clearModel = (DefaultTableModel) table_department.getModel();
		clearModel.setRowCount(0);
		
		for(Department dp : department.getAllDepartments()) {
			if(Integer.parseInt(selectedFacultyTableId) != 0) {
				if(dp.getFaculty().getFacultyId() == Integer.parseInt(selectedFacultyTableId)) {
					departmentData[0] = dp.getId();
					departmentData[1] = dp.getDepartmentName();
					departmentData[2] = dp.getFaculty().getFacultyName();
					departmentModel.addRow(departmentData);
				}
			}else {
				departmentData[0] = dp.getId();
				departmentData[1] = dp.getDepartmentName();
				departmentData[2] = dp.getFaculty().getFacultyName();
				departmentModel.addRow(departmentData);
			}
		}
		
		table_department = new JTable(departmentModel);
		departmentPopup();
	}

	public void updateFacultyModel() {
		DefaultTableModel clearModel = (DefaultTableModel) table_faculty.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < faculty.getAllFaculties().size(); i++) {
			facultyData[0] = faculty.getAllFaculties().get(i).getFacultyId();
			facultyData[1] = faculty.getAllFaculties().get(i).getFacultyName();
			facultyModel.addRow(facultyData);
		}
	}
	
	public void departmentPopup() {
		departmentPopup = new JPopupMenu();
		JMenuItem updateDepartment = new JMenuItem("Güncelle");
		JMenuItem removeDepartment = new JMenuItem("Sil");
		departmentPopup.add(updateDepartment);
		departmentPopup.add(removeDepartment);

		updateDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer
						.parseInt(table_department.getValueAt(table_department.getSelectedRow(), 0).toString());
				Department selectedDepartment = department.getFetch(selectedID);
				DepartmentUpdateGUI dGUI = new DepartmentUpdateGUI(selectedDepartment);
				dGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dGUI.setVisible(true);
				dGUI.addWindowListener(new WindowAdapter() { // Açýlan pencereyi dinliyoruz

					@Override
					public void windowClosed(WindowEvent e) { // Açýlan pencere kapatýldýðýnda db'de veri güncellendiði
																// için tablomuzu da güncelliyoruz.
						updateDepartmentTable();
						updateFacultyModel();

					}

				});

			}
		});

		removeDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer
						.parseInt(table_department.getValueAt(table_department.getSelectedRow(), 0).toString());
				if (Helper.showWarning("Departmani silmek istediginizden emin misiniz?") == 0) {
					if (department.removeDepartment(selectedID)) {
						Helper.showMessage("Islem basarili!");

						updateDepartmentTable();
						updateFacultyModel();
					} else
						Helper.showMessage("Hata olustu!");
				}

			}
		});

	}
}
