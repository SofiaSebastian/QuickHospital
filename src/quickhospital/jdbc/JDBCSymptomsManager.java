package quickhospital.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import quickhospital.pojos.Symptom;

public class JDBCSymptomsManager {
	private JDBCManager manager;

	public JDBCSymptomsManager(JDBCManager manager) {
		this.manager = manager;
	}

	public ArrayList<Symptom> readSymptoms() { // read table Symptoms from db
		String sql = "SELECT * FROM Symptoms";
		ArrayList<Symptom> symptoms = new ArrayList<>();

		try {
			Statement stmt = manager.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				Symptom s = new Symptom(id, name);
				symptoms.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return symptoms;
	}

	public int getId(String name) {
		String sql = "SELECT Id FROM Symptoms WHERE Name = ?";
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
	
	public String compareSymptoms(ArrayList<Integer> nums) {
		
		String sql="SELECT Symptoms_Id FROM Specialities_Symptoms"
				+ "WHERE Speciality_Id == ? ";
		
		ArrayList<Integer>sId= new ArrayList<>();
		
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {	
				sId.add(rs.getInt("Symptoms_Id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		int count=0;
		for(int i=0; i<sId.size(); i++) {
			for(int j=0; j<nums.size(); j++) {
				if(sId.get(i)==nums.get(j)) {
					count++;
				}
			}
		}
		ArrayList<Integer> commonSymptoms= new ArrayList<>();
		commonSymptoms.add(count);
		
		sql="SELECT Symptoms_Id FROM Specialities_Symptoms"
				+ "WHERE Speciality_Id == 2 ";
		ArrayList<Integer>sId2= new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {	
				sId2.add(rs.getInt("Symptoms_Id"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		count=0;
		for(int i=0; i<sId.size(); i++) {
			for(int j=0; j<nums.size(); j++) {
				if(sId.get(i)==nums.get(j)) {
					count++;
				}
			}
		}
		commonSymptoms.add(count);
		
		sql="SELECT Symptoms_Id FROM Specialities_Symptoms"
				+ "WHERE Speciality_Id == 3 ";
		ArrayList<Integer>sId3= new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {	
				sId3.add(rs.getInt("Symptoms_Id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		count=0;
		for(int i=0; i<sId.size(); i++) {
			for(int j=0; j<nums.size(); j++) {
				if(sId.get(i)==nums.get(j)) {
					count++;
				}
			}
		}
		commonSymptoms.add(count);
		
		sql="SELECT Symptoms_Id FROM Specialities_Symptoms"
				+ "WHERE Speciality_Id == 4 ";
		ArrayList<Integer>sId4= new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {	
				sId4.add(rs.getInt("Symptoms_Id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		count=0;
		for(int i=0; i<sId.size(); i++) {
			for(int j=0; j<nums.size(); j++) {
				if(sId.get(i)==nums.get(j)) {
					count++;
				}
			}
		}
		commonSymptoms.add(count);
		
		sql="SELECT Symptoms_Id FROM Specialities_Symptoms"
				+ "WHERE Speciality_Id == 2 ";
		ArrayList<Integer>sId5= new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {	
				sId5.add(rs.getInt("Symptoms_Id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		count=0;
		for(int i=0; i<sId.size(); i++) {
			for(int j=0; j<nums.size(); j++) {
				if(sId.get(i)==nums.get(j)) {
					count++;
				}
			}
		}
		commonSymptoms.add(count);
		
		sql="SELECT Symptoms_Id FROM Specialities_Symptoms"
				+ "WHERE Speciality_Id == 6 ";
		ArrayList<Integer>sId6= new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {	
				sId6.add(rs.getInt("Symptoms_Id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		count=0;
		for(int i=0; i<sId.size(); i++) {
			for(int j=0; j<nums.size(); j++) {
				if(sId.get(i)==nums.get(j)) {
					count++;
				}
			}
		}
		commonSymptoms.add(count);
		
		int obj=Collections.max(commonSymptoms);
		int pos=commonSymptoms.indexOf(obj)+1;
		
		sql= "SELECT Name FROM Specialities WHERE Id=? ";
		PreparedStatement s;
		String name = null; 
		try {
			s = c.prepareStatement(sql);
			s.setInt(1, pos);
			ResultSet rs = stmt.executeQuery(sql);
			name=rs.getString("Name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;		
		
	}
}
