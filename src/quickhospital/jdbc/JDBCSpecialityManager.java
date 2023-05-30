package quickhospital.jdbc;

import java.sql.*;
import java.util.*;
import quickhospital.pojos.*;
import quickhospital.ui.*;

public class JDBCSpecialityManager {
	private JDBCManager manager;

	public JDBCSpecialityManager(JDBCManager manager) {
		this.manager = manager;
	}

	public ArrayList<Speciality> readSpecialities() {// read table Specialities from db

		String sql = "SELECT * FROM Specialities";
		ArrayList<Speciality> specialities = new ArrayList<>();

		try {
			Statement stmt = manager.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				Speciality sp = new Speciality(id, name);
				specialities.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return specialities;
	}
	
	public void readSpecSymp() {//read table Specialities_Symptoms
		String sql= "SELECT * FROM Specialities_Symptoms";
		ArrayList<Integer> symId= new ArrayList<>();
		ArrayList <Integer> spsID= new ArrayList<>();
	
		try {
			Statement stmt= manager.getConnection().createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()) {
				int spId= rs.getInt("Speciality_Id");
				int syId=rs.getInt("Symptoms_id");
				Speciality sp = Main.idToSpeciality(spId);	
				Symptom s= Main.idtoSymptom(syId);
				Main.addSymptom(s, sp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId(String name) {
		String sql = "SELECT Id FROM Specialities WHERE Name = ?";
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
	
	public void addSpeciality(Speciality sp) {
		String sql= "INSERT INTO Specialities(Name) VALUES(?);";
		String name= sp.getName();
		PreparedStatement s; 
		try {
			s=manager.getConnection().prepareStatement(sql);
			s.setString(1, name);
			s.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
