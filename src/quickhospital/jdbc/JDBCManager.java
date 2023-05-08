package quickhospital.jdbc;
import java.lang.System.Logger;
import java.sql.*;
import java.util.*;
import java.io.*;


import quickhospital.ifaces.DBManager;
import quickhospital.pojos.*;

public class JDBCManager implements DBManager{
	
	private static Connection c;
	private static Statement stmt;
	
	private static final String LOCATION = "./db/quickHospital.db";
	
	
	
    private static final String sqlAddCliente = "INSERT INTO Clientes(Nombre,Apellido,DNI, Email, NumTelefono, Password) VALUES (?,?,?,?,?,?);";
    private static final String sqlAddEmpleado = "INSERT INTO Empleado(Nombre,Apellido,Puesto,Sueldo,DNI) VALUES (?,?,?,?,?);";
    private static final String sqlAddAeropuerto = "INSERT INTO Aeropuertos(Nombre,Codigo) VALUES (?,?);";
    private static final String sqlAddCompañia = "INSERT INTO Compañias(Nombre,PaginaWeb,Pais, NumTelefono, Email) VALUES (?,?,?,?,?);";
	
	private PreparedStatement prepAddHospital;
	private PreparedStatement prepAddSpeciality;
	private PreparedStatement prepAddSymptoms;
	private PreparedStatement prepAddWaitingList;
	private PreparedStatement prepEliminarWaitingList;

	
	
	//final static DefaultValues defaultvalues= new DefaultValues();
	//final static Logger TERM = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	
	public void connect() {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/quickHospital.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void disconnect() {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startProgram(){
		connect();
		createTables();
	}
	
	
	
	public void createTables() {
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
	}
	
	
	
	
	public ArrayList<Symptom> getSymptoms(){ //get the symptoms from the db 
		String sql = "SELECT * FROM Symptoms;";
		ArrayList<Symptom> symptoms = new ArrayList<>();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {			
				int id = rs.getInt("Id");
				String nombre = rs.getString("Nombre");
				symptoms.add(new Symptom(id, nombre));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return symptoms;
	}
	
	public void showSymptoms(ArrayList<Symptom> symp) {
        for (int i = 0; i < symp.size(); i++) {
            Symptom s = symp.get(i);
            System.out.println( s.getId()+". "+s.getName());
        }
    }
	
	public String compareSymptoms(ArrayList<Integer> nums) {
	
		String sql="SELECT Symptoms_Id FROM Specialities_Symptoms"
				+ "WHERE Speciality_Id == 1 ";
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
	
	public void showHospitals(String sp) {
		String sql= "SELECT Id FROM Specialities WHERE Name=? ";
		PreparedStatement s;
		int id=0; 
		ArrayList<Integer> hospId= new ArrayList<>();
		try {
			s = c.prepareStatement(sql);
			s.setString(1, sp);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {	
				id=rs.getInt("Name");
			}
			sql= "SELECT Hospital_id FROM Hospitals_Specialities WHERE Speciality-id=?";
			s=c.prepareStatement(sql);
			s.setInt(1, id);
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				hospId.add(rs.getInt("Hospital_Id"));
			}
			ArrayList<String>hospName= new ArrayList<>();
			sql="SELECT Name FROM Hospital WHERE Id=?";
			for(int j=0; j<hospId.size(); j++) {
				s=c.prepareStatement(sql);
				s.setInt(1, hospId.get(j));
				rs=stmt.executeQuery(sql);
				while(rs.next()) {
					hospName.add(rs.getString("Name"));
				}
			}
			System.out.println("The hospitals with the corresponding speciality are:");
			for(int k=0; k<hospName.size(); k++) {
				int num=k+1;
				System.out.println(num +". "+ hospName.get(k));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	public void addToWaitingList(Integer hospId, Integer spId, Integer patId ) {
		String sql= "INSERT INTO WaitingList (Patient_Id, Hospital_Id, Speciality_Id) VALUES ('" + patId + "', '"+ hospId	+ "', '" + spId	+ "'); ";
		try {
			ResultSet rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
	