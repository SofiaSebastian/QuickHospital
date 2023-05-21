package quickhospital.jdbc;
import java.lang.System.Logger;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.io.*;


import quickhospital.ifaces.DBManager;
import quickhospital.pojos.*;
import quickhospital.ui.Main;

public class JDBCManager implements DBManager{
	
	private Connection c = null;
	private ArrayList<Speciality> specialities;



	
	//final static DefaultValues defaultvalues= new DefaultValues();
	//final static Logger TERM = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public JDBCManager() {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/quickHospital.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			this.executeSQLfile("/Users/jaimedemiguel/git/QuickHospital/src/quickhospital/db/ddl.sql");
			//start program
			this.insertSQLfile("/Users/jaimedemiguel/git/QuickHospital/src/quickhospital/db/dml_Hospitals.sql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public Connection getConnection() {
		return c;
	}
	
	public void disconnect() {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void executeSQLfile (String fileName) {
		 try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	            String linea;
	            Statement stmt;
	            while ((linea = br.readLine()) != null) {
	                // Ejecutar el comando SQL en DB Browser
	            	stmt = c.createStatement();
	             	stmt.executeUpdate(linea);
	            }
		 }catch (IOException e) {
	            e.printStackTrace();
	     } catch (Exception e) {
	            e.printStackTrace();
	     }
	        
	}
	
	public void insertSQLfile (String fileName) {
		 try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	            String linea;
	            PreparedStatement p;
	            while ((linea = br.readLine()) != null) {
	                // Ejecutar el comando SQL en DB Browser
	            	p = c.prepareStatement(linea);
	            	p.executeUpdate();
	            }
		 }catch (IOException e) {
	            e.printStackTrace();
	     } catch (Exception e) {
	            e.printStackTrace();
	     }
	        
	}
	

	public void showSymptoms(ArrayList<Symptom> symp) {
        for (int i = 0; i < symp.size(); i++) {
            Symptom s = symp.get(i);
            System.out.println( s.getId()+". "+s.getName());
        }
    }
	
	/*public String compareSymptoms(ArrayList<Integer> nums) {
	
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
	}*/
	
	public void addToWaitingList(Integer hospId, Integer spId, Integer patId ) {
		String sql= "INSERT INTO WaitingList (Patient_Id, Hospital_Id, Speciality_Id) VALUES ('" + patId + "', '"+ hospId	+ "', '" + spId	+ "'); ";

		try {
			Statement stmt= getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}



	@Override
	public ArrayList<Symptom> getSymptoms() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void showHospitals(String sp) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String compareSymptoms(ArrayList<Integer> nums) {
		// TODO Auto-generated method stub
		return null;
	}
}
	