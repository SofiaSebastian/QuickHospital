package quickhospital.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLInsert {

	public static void main(String args[]) {
		//tendremos que hacer un bucle dependiendo de lo que queramos insertar
		
		//Insert hospital
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:/Users/sofia/Desktop/HospitalPrueba.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");


			// Get the hospital info from the command prompt
			System.out.println("Please, input the hospital info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = reader.readLine();
			System.out.print("Capacity: ");
			String capacity = reader.readLine();
			System.out.println("Address: ");
			String location = reader.readLine();
			System.out.println("Administrator id: ");
			String admin = reader.readLine();
			//Convertir los strings a int para meterlos en la base de datos 

			// Insert new record: begin
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO hospital (name,capacity,location,administrator) "
					+ "VALUES ('" + name + "', '"+ capacity	+ "', '" + location	+ "', '" + admin+ "');";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Hospital info processed");
			System.out.println("Records inserted.");
			// Insert new record: end

			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Insert speciality
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:/Users/sofia/Desktop/HospitalPrueba.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Get the speciality info from the command prompt
			System.out.println("Please, input the speciality info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = reader.readLine();
			
			
			// Insert new record: begin
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO speciality (name) "
					+ "VALUES ('" + name + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Speciality info processed");
			System.out.println("Records inserted.");
			// Insert new record: end

			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Insert doctor
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:/Users/sofia/Desktop/HospitalPrueba.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Get the doctor info from the command prompt
			System.out.println("Please, input the doctor info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = reader.readLine();
			System.out.println("Speciality id:");
			Integer sp_id= reader.read();
			System.out.println("Hospital id:");
			Integer hosp_id= reader.read();
			
	
			// Insert new record: begin
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO speciality (name, speciality_id, hospital_id) "
					+ "VALUES ('" + name + "', '"+ sp_id	+ "', '" + hosp_id	+ "');";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Doctor info processed");
			System.out.println("Records inserted.");
			// Insert new record: end

			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Insert patient
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:/Users/sofia/Desktop/HospitalPrueba.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Get the patient info from the command prompt
			System.out.println("Please, input the patient info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = reader.readLine();
			
			
	
			// Insert new record: begin
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO speciality (name) "
					+ "VALUES ('" + name + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Patient info processed");
			System.out.println("Records inserted.");
			// Insert new record: end

			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Insert symptom
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:/Users/sofia/Desktop/HospitalPrueba.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Get the symptom info from the command prompt
			System.out.println("Please, input the symptom info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = reader.readLine();
			
			
	
			// Insert new record: begin
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO speciality (name, speciality_id, hospital_id) "
					+ "VALUES ('" + name + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Doctor info processed");
			System.out.println("Records inserted.");
			// Insert new record: end

			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Pensar como hacer el insert de la waitinglist
		
	}
}