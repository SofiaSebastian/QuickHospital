package quickhospital.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import quickhospital.pojos.City;
import quickhospital.pojos.Hospital;
import quickhospital.pojos.Speciality;
import quickhospital.ui.Main;

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
	
	public void readHospSpec() {//read table Hospitals_Specialities
		String sql= "SELECT * FROM Hospitals_Specialities";
		ArrayList<Integer> hospID= new ArrayList<>();
		ArrayList <Integer> spsID= new ArrayList<>();
	
		try {
			Statement stmt= manager.getConnection().createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()) {
				int hId=rs.getInt("Hospital_Id");
				int spId= rs.getInt("Speciality_Id");
				Speciality sp = Main.idToSpeciality(spId);	
				Hospital h = Main.idToHospital(hId);
				Main.addSpeciality(sp, h);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	public int getId(String name) {//get the id of a Hospital from its name
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
