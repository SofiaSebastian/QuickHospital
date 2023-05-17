package quickhospital.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import quickhospital.pojos.Speciality;

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

}
