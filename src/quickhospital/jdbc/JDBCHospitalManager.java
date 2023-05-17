package quickhospital.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import quickhospital.pojos.City;
import quickhospital.pojos.Hospital;

public class JDBCHospitalManager {

	private JDBCManager manager;
	
	
	public JDBCHospitalManager(JDBCManager manager) {
		this.manager = manager;
	}

	public ArrayList<Hospital> readHospitalDB() {// read table Hospitals from db
		String sql = "SELECT * FROM Hospitals";
		ArrayList<Hospital> hospitals = new ArrayList<>();
		try {
			
			Statement stmt = manager.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				int capacity = rs.getInt("Capacity");
				String location = rs.getString("Location");
				Hospital h = new Hospital(id, name, capacity, location);
				hospitals.add(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hospitals;
	}

	public int getId(String name) {
		String sql = "SELECT Id FROM Hospitals WHERE Name = ?";
		PreparedStatement s;
		int id = 0;
		try {
			s = manager.getConnection().prepareStatement(sql);
			s.setString(1, name);
			ResultSet rs = s.executeQuery();
			id = rs.getInt("Id");
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}
