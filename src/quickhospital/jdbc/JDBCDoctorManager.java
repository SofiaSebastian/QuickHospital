package quickhospital.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import quickhospital.pojos.Doctor;

public class JDBCDoctorManager {
	private static Statement stmt;

	
	public void newDoctor (Doctor d, int hospitalid, int specialityid) {
		String name=d.getName();
		String sql= "INSERT INTO Doctors (Name, Hospital_Id, Speciality_Id) VALUES ('"+name+ "','"+hospitalid+ "','"+specialityid+"');";
		try {
			ResultSet rs= stmt.executeQuery(sql);
		}catch(SQLException e ) {
			e.printStackTrace();
		}
	}
}
