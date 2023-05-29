package quickhospital.jdbc;

import java.sql.*;
import java.util.*;

import quickhospital.pojos.Patient;

public class JDBCPatientManager {

	private JDBCManager manager;
	private ArrayList<Patient> patients;

	public JDBCPatientManager(JDBCManager manager) {
		this.manager = manager;
	}

	public ArrayList<Patient> readPatients() {// read table Patients from db
		String sql = "SELECT * FROM Patients";

		try {
			Statement stmt = manager.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				Patient p = new Patient(id, name);
				patients.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}
	
	public void addPatient (String name) {
		String sql= "INSERT INTO Patients (Name) VALUES (?)";
		try {
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setString(1, name);
			p.executeUpdate();
			
		}catch(SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public int getId(String name) {
		String sql= "SELECT Id FROM Patients WHERE Name = ?";
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
