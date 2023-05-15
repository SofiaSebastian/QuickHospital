package quickhospital.jdbc;
import java.lang.System.Logger;
import java.sql.*;
import java.time.LocalDate;
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
	
	public void startProgram(){// We read everything from db
		connect();
		City madrid= new City();
		readHospitalDB(madrid);
		readSpecialities();
		readSymptoms();
		readPatients();
		readDoctors();
		
	}
	
	
	public void readHospitalDB(City city) {//read table Hospitals from db
		String sql= "SELECT * FROM Hospitals";
		
		
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id= rs.getInt("Id");
				String name= rs.getString("Name");
				int capacity= rs.getInt("Capacity");
				String location =rs.getString("Location");
				Hospital h= new Hospital(id, name,capacity,location);
				city.addHospital(h);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void readSpecialities() {//read table Specialities from db
		String sql= "SELECT * FROM Specialities";
		try {
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()) {
				int id= rs.getInt("Id");
				String name= rs.getString("Name");
				Speciality sp = new Speciality(id, name);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void readSymptoms() { //read table Symptoms from db
		String sql= "SELECT * FROM Symptoms";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while( rs.next()) {
				int id= rs.getInt("Id");
				String name= rs.getString("Name");
				Symptom s= new Symptom(id, name);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void readPatients() {//read table Patients from db
		String sql= "SELECT * FROM Patients";
		try {
			ResultSet rs= stmt.executeQuery(sql);
			int id = rs.getInt("Id");
			String name= rs.getString("Name");
			Patient p = new Patient( id, name);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void readDoctors() { //read table Doctor from db
		String sql= "SELECT * FROM Doctors";
		try {
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("Id");
				String name= rs.getString("Name");
				Time aT= rs.getTime("ArrivalTime");
				Time dT= rs.getTime("DepartureTime");
				Doctor d = new Doctor(id, name, aT, dT);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void readHospSpec(City city) {//read table Hospitals_Specialities
		String sql= "SELECT * FROM Hospitals_Specialities";
		ArrayList<Integer> hospID= new ArrayList<>();
		ArrayList <Integer> spsID= new ArrayList<>();
		try {
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()) {
				int hId=rs.getInt("Hospital_Id");
				int spId= rs.getInt("Speciality_Id");
				city.addSpeciality(null, spId);
					
					
					
			}
			
			}
			
		}catch(SQLException e) {
			
		}
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
	