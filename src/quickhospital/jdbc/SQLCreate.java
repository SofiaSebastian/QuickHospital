package quickhospital.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLCreate {

	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:/Users/sofia/Desktop/HospitalPrueba.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Create tables: begin
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE doctor "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " arrivalTime  DATETIME	 NOT NULL,"
			   		   + " departureTime  DATETIME	 NOT NULL,"
			   		   + "speciality_id INTEGER REFERENCES speciality(id) ON UPDATE CASCADE ON DELETE SET NULL"
			   		   + "hospital_id   INTEGER REFERENCES hospital(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE hospital "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " capacity  INTEGER  NOT NULL, "
					   + " location  TEXT    NOT NULL, "
					   + " administrator   INTEGER  NOT NULL UNIQUE)";
			stmt2.executeUpdate(sql2);
			stmt2.close();
			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE patient "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " speciality_id INTEGER REFERENCES speciality(id) ON UPDATE CASCADE ON DELETE SET NULL, "
					   + " symptoms_id INTEGER REFERENCES symptom(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE speciality "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " hospital_id INTEGER REFERENCES hospital(id) ON UPDATE CASCADE ON DELETE SET NULL, "
					   + " symptoms_id INTEGER REFERENCES symptom(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt4.executeUpdate(sql4);
			stmt4.close();
			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE symptoms "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL) ";
			stmt5.executeUpdate(sql5);
			stmt5.close();
			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE waitingList "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " date     DATETIME     NOT NULL DEFAULT CURRENT DATETIME, "
					   + " status   BOOLEAN  , "
					   + " speciality_id INTEGER REFERENCES speciality(id) ON UPDATE CASCADE ON DELETE SET NULL, "
					   + " patient_id INTEGER REFERENCES patient(id) ON UPDATE CASCADE ON DELETE SET NULL)";
			stmt6.executeUpdate(sql6);
			stmt6.close();
			System.out.println("Tables created.");
			// Create table: end
			
			// - Set initial values for the Primary Keys
			// - Don't try to understand this until JPA is explained
			// This is usually not needed, since the initial values
			// are set when the first row is inserted, but since we
			// are using JPA and JDBC in the same project, and JPA
			// needs an initial value, we do this.
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('doctor', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('hospital', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('patient', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('speciality', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('symptoms', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('waitingList', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*public void createTables() {
	try{
		Statement stmt1 = c.createStatement();
		String sql1= "CREATE TABLE IF NOT EXISTS Hospitals"
			+"(Id   INTEGER  PRIMARY KEY AUTOINCREMENT,"
			+"Name     TEXT     NOT NULL, "
			+"Capacity  INTEGER  NOT NULL, "
			+"Location  TEXT    NOT NULL);)";
	stmt1.executeUpdate(sql1);
	stmt1.close();

Statement stmt2 = c.createStatement();
String sql2= "CREATE TABLE IF NOT EXISTS Specialities"
		+ "(Id   INTEGER  PRIMARY KEY AUTOINCREMENT,"
		+ "Name     TEXT     NOT NULL);";
stmt2.executeUpdate(sql2);
stmt2.close();

Statement stmt3 = c.createStatement();
String sql3="CREATE TABLE IF NOT EXISTS Doctors"
		+ "(Id   INTEGER  PRIMARY KEY AUTOINCREMENT,"
		+ "Name     TEXT     NOT NULL,"
		+ "ArrivalTime  DATETIME  NOT NULL, "
		+ "DepartureTime  DATETIME    NOT NULL,"
		+ "Hospital_Id INTEGER REFERENCES Hospitals(Id) ON UPDATE CASCADE ON DELETE SET NULL,"
		+ "Speciality_Id INTEGER REFERENCES Specialities(Id) ON UPDATE CASCADE ON DELETE SET NULL);";
stmt3.executeUpdate(sql3);
stmt3.close();

Statement stmt4 = c.createStatement();
String sql4="CREATE TABLE IF NOT EXISTS Patients"
		+ "(Id   INTEGER  PRIMARY KEY AUTOINCREMENT,"
		+ "Name     TEXT     NOT NULL,"
		+ "Speciality_Id INTEGER REFERENCES Specialities(Id) ON UPDATE CASCADE ON DELETE SET NULL);";
stmt4.executeUpdate(sql4);
stmt4.close();

Statement stmt5 = c.createStatement();
String sql5="CREATE TABLE IF NOT EXISTS Symptoms"
		+ "(Id   INTEGER  PRIMARY KEY AUTOINCREMENT,"
		+ "Name     TEXT     NOT NULL);";
stmt5.executeUpdate(sql5);
stmt5.close();

Statement stmt6 = c.createStatement();
String sql6="CREATE TABLE IF NOT EXISTS WaitingLists"
		+ "(Id   INTEGER  PRIMARY KEY AUTOINCREMENT,"
		+ "Date  DATETIME  NOT NULL, "
		+ "Status  BOOLEAN  NOT NULL,"
		+ "Patient_Id INTEGER REFERENCES Patients(Id) ON UPDATE CASCADE ON DELETE SET NULL,"
		+ "Speciality_Id INTEGER REFERENCES Specialities(Id) ON UPDATE CASCADE ON DELETE SET NULL,"
		+ "Hospital_Id INTEGER REFERENCES Hospitals(Id) ON UPDATE CASCADE ON DELETE SET NULL);";
stmt6.executeUpdate(sql6);
stmt6.close();

Statement stmt7 = c.createStatement();
String sql7="CREATE TABLE IF NOT EXISTS Hospitals_Specialities"
		+ "(Hospital_Id INTEGER REFERENCES Hospitals(Id) ON UPDATE CASCADE ON DELETE SET NULL,"
		+ "Speciality_Id INTEGER REFERENCES Specialities(Id) ON UPDATE CASCADE ON DELETE SET NULL,"
		+ "PRIMARY KEY (Hospital_Id,Speciality_Id));";
stmt7.executeUpdate(sql7);
stmt7.close();
								
Statement stmt8 = c.createStatement();
String sql8="CREATE TABLE IF NOT EXISTS Specialities_Symptoms"
		+ "(Symptoms_Id INTEGER REFERENCES Symptoms(Id) ON UPDATE CASCADE ON DELETE SET NULL,"
		+ "Speciality_Id INTEGER REFERENCES Specialities(Id) ON UPDATE CASCADE ON DELETE SET NULL,"
		+ "PRIMARY KEY (Speciality_Id,Symptoms_Id));";
stmt8.executeUpdate(sql8);
stmt8.close();

Statement stmt9 = c.createStatement();
String sql9="CREATE TABLE IF NOT EXISTS Patients_Symptoms"
		+ "(Symptoms_Id INTEGER REFERENCES Symptoms(Id) ON UPDATE CASCADE ON DELETE SET NULL,"
		+ "Patient_Id INTEGER REFERENCES Patients(Id) ON UPDATE CASCADE ON DELETE SET NULL,"
		+ "PRIMARY KEY (Patient_Id,Symptoms_Id));";
stmt9.executeUpdate(sql9);
stmt9.close();
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/
}