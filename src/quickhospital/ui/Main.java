package quickhospital.ui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Statement;

import quickhospital.jdbc.JDBCDoctorManager;
import quickhospital.jdbc.JDBCManager;
import quickhospital.jpa.JPAUserManager;
import quickhospital.pojos.*;
import quickhospital.utilities.Utils;

public class Main {
	
	public static void main(String args[]) {
		
		JDBCManager manager = new JDBCManager();
		//manager.executeSQLfile("/Users/jaimedemiguel/git/QuickHospital/src/quickhospital/db/dml_Hospitals.sql");
		//manager.startProgram();
		
	}
	
	public void registerDoctor(City madrid, JDBCManager manager) {
		JPAUserManager um = new JPAUserManager();
		JDBCDoctorManager dm = new JDBCDoctorManager(manager);
		
		try{
			//register doctor
			Role role = um.getRole("doctor");
			
			//pedir atributos de doctor (setters)
			String name= Utils.leerCadena("Introduce your name");
			madrid.showHospitals();
			int hospitalid = Utils.leerEntero("Introduce the number of hospital you work in");
			madrid.showSpecialities(hospitalid);
			int specialityid = Utils.leerEntero("Introduce the number of your speciality");
			
			//USER
			String mail = Utils.leerCadena("Introduce email");
			String password = Utils.leerCadena("introduce password:");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			User u = new User(mail, digest,role);
			role.addUSer(u);
			um.newUser(u);       //aqui se sube a la db
			dm.newDoctor(name,hospitalid,specialityid);
			int id= dm.getId(name);
			Doctor doc = new Doctor(id, name);
			//madrid.registerDoctor(doc, hospitalid, specialityid);
			um.disconnect();
	    }catch (NoSuchAlgorithmException ex) {
		    System.out.println(ex.getMessage());
	    }

	}

}
