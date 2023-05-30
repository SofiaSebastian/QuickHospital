package quickhospital.ui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import quickhospital.jpa.*;
import quickhospital.pojos.Role;
import quickhospital.pojos.User;
import quickhospital.jdbc.*;

public class Pruebas {

	public static void main(String args[]) {
		
		/*JPAUserManager m= new JPAUserManager();
		Role role =m.getRole("doctor");
		String email= "s@gmail";
		String passw= "123"; 
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(passw.getBytes());
			byte[] digest = md.digest();
			User u = new User(email, digest, role);
			role.addUSer(u);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int id=m.getIdfromDB(email, passw, role);
		System.out.println(id);*/
		
		JDBCManager manager= new JDBCManager();
		/*String name= "Sofia";
		int capacity = 100;
		String location= "Majuelo 1";
		String sql= "INSERT INTO Hospitals(Name, Location, Capacity) VALUES (?,?,?);";
		PreparedStatement s; 
		try {
			s= manager.getConnection().prepareStatement(sql);
			s.setString(1, name);
			s.setString(2, location);
			s.setInt(3, capacity);
			s.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		
		}*/
		String sql= "DELETE FROM Hospitals WHERE id= ?;";
		PreparedStatement s; 
		try {
			s=manager.getConnection().prepareStatement(sql);
			s.setInt(1, 8);
			s.execute();
		}catch(SQLException e ) {
			e.printStackTrace();
		}
	}
	
}
