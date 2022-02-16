package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Manager;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class ManagerSettingsGUI extends JFrame {

	private JPanel contentPane;
	static Manager manager = new Manager();
	public String kontrol = "profil";
	JButton instructor_settings;
	JButton instructor_settings_1;
	private String imagesPath = "C:\\Users\\bjkli\\git\\repository5\\University-Management\\NewUMS\\img\\";  //proje dosyasýnýn içindeki img klasörünün yolunu buraya giriniz!

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerSettingsGUI frame = new ManagerSettingsGUI(manager);
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
	public ManagerSettingsGUI(Manager manager) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 926, 784);
		contentPane = new JPanel();
		contentPane.setBackground(new  Color(68, 49, 65));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(imagesPath + "avatar.png"));
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_3_1.setBorder(null);
		lblNewLabel_3_1.setBounds(10, 42, 46, 70);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("<html>"+ manager.getFirst_name().toUpperCase() + " " + manager.getLast_name().toUpperCase() +"<br/>\r\n<p style=\"font-weight:normal;\">Yönetici</p>\r\n</html>");
		lblNewLabel_2_2.setForeground(new Color(112, 112, 112));
		lblNewLabel_2_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(57, 39, 147, 77);
		contentPane.add(lblNewLabel_2_2);
		
		instructor_settings_1 = new JButton("Þifre Ayarlarý");
		instructor_settings_1.setFocusPainted(false);
		instructor_settings_1.setBackground(new Color(17, 17, 17));
		instructor_settings_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				instructor_settings_1.setBackground(new Color(50, 50, 50));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				instructor_settings_1.setBackground(new Color(17, 17, 17));

			}
		});
		
		instructor_settings_1.setForeground(new Color(112, 112, 112));
		instructor_settings_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		instructor_settings_1.setBorder(null);
		instructor_settings_1.setBounds(0, 346, 211, 70);
		contentPane.add(instructor_settings_1);
		
		instructor_settings = new JButton("Profil Ayarlarý");
		instructor_settings.setFocusPainted(false);
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
		instructor_settings.setForeground(new Color(112, 112, 112));
		instructor_settings.setFont(new Font("Segoe UI", Font.BOLD, 12));
		instructor_settings.setBorder(null);
		instructor_settings.setBounds(0, 265, 211, 70);
		contentPane.add(instructor_settings);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(imagesPath + "leftMenuBackground.png"));
		lblNewLabel_2.setBounds(0, 0, 211, 745);
		contentPane.add(lblNewLabel_2);
		
		Manager loggedInManager = Manager.getFetch(manager.getId());
		
		JLabel lblNewLabel_1 = new JLabel("HESAP AYARLARI");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_1.setBounds(256, 39, 313, 34);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(221, 84, 679, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_3 = new JLabel("Profil Detaylarý");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Poppins Medium", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(299, 246, 270, 26);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Ad Soyad");
		lblNewLabel_4.setFont(new Font("Poppins Medium", Font.PLAIN, 18));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(317, 306, 110, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("TC NO");
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Poppins Medium", Font.PLAIN, 18));
		lblNewLabel_4_1.setBounds(317, 347, 81, 14);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Mail");
		lblNewLabel_4_2.setForeground(Color.WHITE);
		lblNewLabel_4_2.setFont(new Font("Poppins Medium", Font.PLAIN, 18));
		lblNewLabel_4_2.setBounds(317, 385, 56, 14);
		contentPane.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel(":");
		lblNewLabel_4_3.setForeground(Color.WHITE);
		lblNewLabel_4_3.setFont(new Font("Poppins Medium", Font.PLAIN, 18));
		lblNewLabel_4_3.setBounds(421, 306, 13, 14);
		contentPane.add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_4_3_1 = new JLabel(":");
		lblNewLabel_4_3_1.setForeground(Color.WHITE);
		lblNewLabel_4_3_1.setFont(new Font("Poppins Medium", Font.PLAIN, 18));
		lblNewLabel_4_3_1.setBounds(422, 347, 13, 14);
		contentPane.add(lblNewLabel_4_3_1);
		
		JLabel lblNewLabel_4_3_2 = new JLabel(":");
		lblNewLabel_4_3_2.setForeground(Color.WHITE);
		lblNewLabel_4_3_2.setFont(new Font("Poppins Medium", Font.PLAIN, 18));
		lblNewLabel_4_3_2.setBounds(421, 385, 13, 14);
		contentPane.add(lblNewLabel_4_3_2);
		
		JLabel lblNewLabel_4_4 = new JLabel(loggedInManager.getFirst_name() + " " + loggedInManager.getLast_name());
		lblNewLabel_4_4.setForeground(Color.WHITE);
		lblNewLabel_4_4.setFont(new Font("Poppins Light", Font.PLAIN, 14));
		lblNewLabel_4_4.setBounds(437, 306, 158, 14);
		contentPane.add(lblNewLabel_4_4);
		
		JLabel lblNewLabel_4_5 = new JLabel(loggedInManager.getIdentityNumber());
		lblNewLabel_4_5.setForeground(Color.WHITE);
		lblNewLabel_4_5.setFont(new Font("Poppins Light", Font.PLAIN, 14));
		lblNewLabel_4_5.setBounds(437, 347, 158, 14);
		contentPane.add(lblNewLabel_4_5);
		
		JLabel lblNewLabel_4_6 = new JLabel(loggedInManager.getAdminMail());
		lblNewLabel_4_6.setForeground(Color.WHITE);
		lblNewLabel_4_6.setFont(new Font("Poppins Light", Font.PLAIN, 14));
		lblNewLabel_4_6.setBounds(438, 385, 157, 14);
		contentPane.add(lblNewLabel_4_6);
		
		JButton btnNewButton = new JButton("Profili Düzenle");
		btnNewButton.setBorder(BorderFactory.createBevelBorder(0));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btnNewButton.setBackground(new Color(230, 203, 243));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				btnNewButton.setBackground(Color.WHITE);
				
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setForeground(new Color(68, 49, 65));
		btnNewButton.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(364, 436, 136, 45);
		contentPane.add(btnNewButton);
	}
}
