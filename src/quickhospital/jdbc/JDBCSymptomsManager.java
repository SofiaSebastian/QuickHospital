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
		String sql = "SELECT * FROM Symptoms;";
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
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return symptoms;
	}

	public int getId(String name) {
		String sql = "SELECT Id FROM Symptoms WHERE Name = ?;";
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
