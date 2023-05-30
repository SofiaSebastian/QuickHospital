package quickhospital.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import quickhospital.pojos.Doctor;
import quickhospital.pojos.Hospital;

public class JDBCDoctorManager {
	
	private JDBCManager manager;
	
	public JDBCDoctorManager(JDBCManager manager) {
		this.manager = manager;
	}
	
	public ArrayList<Doctor> readDoctors() { //read table Doctor from db
		String sql= "SELECT * FROM Doctors";
		ArrayList <Doctor> doctors= new ArrayList<>();
		try {
			Statement stmt= manager.getConnection().createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("Id");
				String name= rs.getString("Name");
				int spid= rs.getInt("Speciality_id");
				int hospid= rs.getInt("Hospital_id");
				Doctor d = new Doctor(id, name, spid, hospid);
				doctors.add(d);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return doctors;
	}
	
	public void newDoctor (String name, int hospitalid, int specialityid) {
		String sql= "INSERT INTO Doctors (Name, Hospital_Id, Speciality_Id) VALUES (?,?,?)";
		try {
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setString(1, name);
			p.setInt(2, hospitalid);
			p.setInt(3, specialityid);
			p.executeUpdate();
			
		}catch(SQLException e ) {
			e.printStackTrace();
		}
	}
	
	
	public int getId(String name) {
		String sql= "SELECT Id FROM Doctors WHERE Name = ?";
		PreparedStatement s;
		int id=0;
		try {
			s= manager.getConnection().prepareStatement(sql);
			s.setString(1, name);
			ResultSet rs= s.executeQuery();
			id=rs.getInt("Id");
			rs.close();
			s.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	
}
