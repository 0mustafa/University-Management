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

import Helper.Dictionary;
import Helper.Helper;
import Model.Department;
import Model.Faculty;
import Model.Instructor;
import Model.Manager;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

public class InstructorProcessGUI extends JFrame {

	private JPanel contentPane;
	static Manager manager = new Manager();
	static Instructor instructor = new Instructor();
	static Faculty faculty = new Faculty();
	static Department department = new Department();
	private JTextField txt_add_instructor_first_name;
	private JTextField txt_add_instructor_last_name;
	private JTextField txt_add_instructor_tc;
	private JTextField txt_add_instructor_degree;
	private JTextField txt_instructor_id;
	private JTable table_instructor;
	private DefaultTableModel instructorModel = null;
	private Object[] instructorData = null;
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
					InstructorProcessGUI frame = new InstructorProcessGUI(manager);
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
	public InstructorProcessGUI(Manager manager) {
		
		// ********** INSTRUCTOR Model **********
		instructorModel = new DefaultTableModel();
		Object[] colInstructor = new Object[8];
		colInstructor[0] = "ID";
		colInstructor[1] = "Ad";
		colInstructor[2] = "SOYAD";
		colInstructor[3] = "UNVAN";
		colInstructor[4] = "TC NO";
		colInstructor[5] = "MAIL";
		colInstructor[6] = "FAKULTE";
		colInstructor[7] = "BOLUM";
		instructorModel.setColumnIdentifiers(colInstructor);
		instructorData = new Object[8];
		for(Instructor ins : instructor.getAllInstructors()) {
			instructorData[0] = ins.getId();
			instructorData[1] = ins.getFirst_name();
			instructorData[2] = ins.getLast_name();
			instructorData[3] = ins.getDegree();
			instructorData[4] = ins.getIdentityNumber();
			instructorData[5] = ins.getMail();
			instructorData[6] = ins.getFacultyName();
			instructorData[7] = ins.getDepartmentName();
			instructorModel.addRow(instructorData);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1504, 809);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl = new JLabel("Ad");
		lbl.setBounds(1083, 109, 46, 14);
		lbl.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(lbl);
		
		JLabel lbl_1 = new JLabel("Soyad");
		lbl_1.setBounds(1291, 105, 55, 23);
		lbl_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(lbl_1);
		
		txt_add_instructor_first_name = new JTextField();
		txt_add_instructor_first_name.setBounds(1083, 130, 178, 27);
		txt_add_instructor_first_name.setColumns(10);
		contentPane.add(txt_add_instructor_first_name);
		
		txt_add_instructor_last_name = new JTextField();
		txt_add_instructor_last_name.setBounds(1291, 130, 178, 27);
		txt_add_instructor_last_name.setColumns(10);
		contentPane.add(txt_add_instructor_last_name);
		
		JLabel lbl_2 = new JLabel("TC NO");
		lbl_2.setBounds(1083, 197, 59, 23);
		lbl_2.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(lbl_2);
		
		JLabel lbl_3 = new JLabel("Unvan");
		lbl_3.setBounds(1291, 197, 59, 23);
		lbl_3.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(lbl_3);
		
		txt_add_instructor_tc = new JTextField();
		txt_add_instructor_tc.setBounds(1083, 221, 178, 27);
		txt_add_instructor_tc.setColumns(10);
		contentPane.add(txt_add_instructor_tc);
		
		txt_add_instructor_degree = new JTextField();
		txt_add_instructor_degree.setBounds(1291, 221, 178, 27);
		txt_add_instructor_degree.setColumns(10);
		contentPane.add(txt_add_instructor_degree);
		
		JLabel lbl_4 = new JLabel("Fakulte");
		lbl_4.setBounds(1083, 291, 68, 23);
		lbl_4.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(lbl_4);
		
		JLabel lbl_5 = new JLabel("Bolum");
		lbl_5.setBounds(1291, 291, 61, 23);
		lbl_5.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(lbl_5);
		
		JComboBox<Object> combo_faculty = new JComboBox<Object>();
		combo_faculty.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		combo_faculty.setBackground(Color.WHITE);
		combo_faculty.setBounds(1083, 316, 178, 35);
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
		combo_department.setBounds(1291, 316, 178, 35);
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
		
		JButton btn_add_instructor = new JButton("Ekle");
		btn_add_instructor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (lbl.getText().length() == 0
						|| txt_add_instructor_first_name.getText().length() == 0
						|| txt_add_instructor_last_name.getText().length() == 0
						|| txt_add_instructor_tc.getText().length() < 10
						|| txt_add_instructor_tc.getText().length() == 0
						|| txt_add_instructor_degree.getText().length() == 0
						|| comboFacultyValue == null
						|| comboDepartmentValue == null) {
					Helper.showMessage("Lütfen tüm alanlarý doldurunuz.");
				} else {
					instructor.setFirst_name(txt_add_instructor_first_name.getText());
					instructor.setLast_name(txt_add_instructor_last_name.getText());
					instructor.setIdentityNumber(txt_add_instructor_tc.getText());
					instructor.setDegree(txt_add_instructor_degree.getText());
					instructor.setFacultyName(comboFacultyValue);
					instructor.setDepartmentName(comboDepartmentValue);
					if (instructor.addInstructor(instructor)) {
						Helper.showMessage("Eðitmen sisteme eklendi.");
						updateInstructorModel();
					} else
						Helper.showMessage("Eðitmen sisteme eklenirken hata oluþtu!");

					txt_add_instructor_first_name.setText("");
					txt_add_instructor_last_name.setText("");
					txt_add_instructor_tc.setText("");
					txt_add_instructor_degree.setText("");
					combo_faculty.setSelectedIndex(0);
					combo_department.setSelectedIndex(0);
				}

			}
		});
		btn_add_instructor.setBounds(1215, 408, 122, 40);
		btn_add_instructor.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		btn_add_instructor.setBackground(new Color(245, 245, 245));
		contentPane.add(btn_add_instructor);
		
		JLabel lbl_6 = new JLabel("Egitmen ID");
		lbl_6.setBounds(1117, 584, 144, 23);
		lbl_6.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(lbl_6);
		
		txt_instructor_id = new JTextField();
		txt_instructor_id.setBounds(1117, 617, 318, 27);
		txt_instructor_id.setColumns(10);
		contentPane.add(txt_instructor_id);
		
		JButton btn_remove_instructor = new JButton("Sil");
		btn_remove_instructor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (txt_instructor_id.getText().length() == 0)
					Helper.showMessage("Lütfen silmek istediðiniz eðitmenin tablodaki id numarasýný giriniz.");
				else {
					if (Helper.showWarning("Eðitmeni silmek istediðinizden emin misiniz?") == 0) {
						if (instructor.removeInstructor(Integer.parseInt(txt_instructor_id.getText()))) {
							Helper.showMessage("Eðitmen silindi.");
							updateInstructorModel();
						} else
							Helper.showMessage("böyle bir eðitmen bulunmuyor!");

						txt_instructor_id.setText("");
					}
				}

			}
		});
		btn_remove_instructor.setBounds(1215, 696, 122, 40);
		btn_remove_instructor.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		btn_remove_instructor.setBackground(new Color(245, 245, 245));
		contentPane.add(btn_remove_instructor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(42, 109, 983, 627);
		contentPane.add(scrollPane);
		
		table_instructor = new JTable(instructorModel);
		table_instructor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table_instructor);
	
		// Tabloda seçilen satýrý textbox'a iþler
		table_instructor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					txt_instructor_id.setText(table_instructor.getValueAt(table_instructor.getSelectedRow(), 0).toString());

				} catch (Exception ex) {

				}

			}
		});
		
		// UPDATE INSTRUCTOR TABLE'S VALUES
		table_instructor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				if (e.getType() == TableModelEvent.UPDATE) {

					int selectID = Integer
							.parseInt(table_instructor.getValueAt(table_instructor.getSelectedRow(), 0).toString());
					String selectName = table_instructor.getValueAt(table_instructor.getSelectedRow(), 1).toString();
					String selectSurname = table_instructor.getValueAt(table_instructor.getSelectedRow(), 2).toString();
					String selectDegree = table_instructor.getValueAt(table_instructor.getSelectedRow(), 3).toString();
					String selectTcNo = table_instructor.getValueAt(table_instructor.getSelectedRow(), 4).toString();
					String selectMail = table_instructor.getValueAt(table_instructor.getSelectedRow(), 5).toString();
					String selectFaculty = table_instructor.getValueAt(table_instructor.getSelectedRow(), 6).toString();
					String selectDepartment = table_instructor.getValueAt(table_instructor.getSelectedRow(), 7)
							.toString();

					if (instructor.updateInstructor(selectID, selectName, selectSurname, selectDegree, selectTcNo,
							selectMail, selectFaculty, selectDepartment)) {
						Helper.showMessage("Eðitmen bilgileri güncellendi.");
					}

				}

			}
		});

		
		JLabel welcome = new JLabel("Hoþ Geldiniz, " + manager.getFirst_name().toUpperCase() + " "
				+ manager.getLast_name().toUpperCase());
		welcome.setBounds(42, 38, 403, 25);
		welcome.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		contentPane.add(welcome);
		
		JButton btn_manager_logout = new JButton("Menuye Don");
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
		btn_manager_logout.setBounds(1291, 32, 144, 40);
		contentPane.add(btn_manager_logout);
		
	}
	
	// UPDATE TABLE
	public void updateInstructorModel() {
		DefaultTableModel clearModel = (DefaultTableModel) table_instructor.getModel();
		clearModel.setRowCount(0);

		for(Instructor ins : instructor.getAllInstructors()) {
			instructorData[0] = ins.getId();
			instructorData[1] = ins.getFirst_name();
			instructorData[2] = ins.getLast_name();
			instructorData[3] = ins.getDegree();
			instructorData[4] = ins.getIdentityNumber();
			instructorData[5] = ins.getMail();
			instructorData[6] = ins.getFacultyName();
			instructorData[7] = ins.getDepartmentName();
			instructorModel.addRow(instructorData);
		}
	}
}
