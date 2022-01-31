package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Manager extends Person{

	private String adminMail;
	
	static Connection conn = connection.connDb();
	static Statement state = null;
	static ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	
	public Manager() {
	}
	
	public String getAdminMail() {
		return adminMail;
	}



	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}
	
	
	
	public boolean updateManager(int id, String name, String surname, String tcNo, String mail) {

		String query = "UPDATE admins SET first_name = ?, last_name = ?, tc_no = ?, admin_mail = ? WHERE id = ?"; 
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, tcNo);
			preparedStatement.setString(4, mail);
			preparedStatement.setInt(5, id);
			if (preparedStatement.executeUpdate() == 0)
				kontrol = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kontrol;
	}
	
	public static Manager getFetch(int managerId) {
		
		String query = "SELECT * FROM admins WHERE id = " +"'" + managerId + "'";
		Manager fc = new Manager();
		try {
			state = conn.createStatement();
			rs = state.executeQuery(query);
			while(rs.next()) {
				fc.setId(rs.getInt("id"));
				fc.setFirst_name(rs.getString("first_name"));
				fc.setLast_name(rs.getString("last_name"));
				fc.setAdminMail(rs.getString("admin_mail"));
				fc.setIdentityNumber(rs.getString("tc_no"));
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fc;
	}
	
	
	
	
	
}
