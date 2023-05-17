package quickhospital.ui;

import java.security.MessageDigest;

import quickhospital.ifaces.DBManager;
import quickhospital.ifaces.UserManager;
import quickhospital.utilities.Utils;
import quickhospital.pojos.*;
import quickhospital.jpa.JPAUserManager;
import java.security.NoSuchAlgorithmException;
import quickhospital.jdbc.*;
public class MenuJPA {

	public static DBManager dbManager;
	public static UserManager userManager;

	public static void welcome(City madrid, JDBCManager manager) {
		System.out.println("\n\n\n\n\n\n\n\nWelcome!");
		int option;
		do {
			System.out.println("What do you want to do? ");
			System.out.println("\n\t1.Register (new accounts)");
			System.out.println("\n\t2.Login (already existing or admin)");
			System.out.println("\n\t0.Exit");
			option = Utils.leerEntero("Type an option");
			switch (option) {
			case 1:
				newUser(madrid,manager);
				break;
			case 2:
				login();
				break;
			case 0:
				dbManager.disconnect();
				userManager.disconnect();
				System.exit(0);
			}
		} while (option != 0);

	}

	private static void newUser(City madrid,JDBCManager manager) {
		
		JPAUserManager um = new JPAUserManager();
		JDBCDoctorManager dm = new JDBCDoctorManager(manager);
		//JDBCPatientManager pm = new JDBCPatientManager()
		int option = Utils.leerEntero("Type an option");
		switch (option) {
		case 1:{ 
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
				um.newUser(u); //aqui se sube a la db
				dm.newDoctor(name,hospitalid,specialityid);
				int id= dm.getId(name);
				Doctor doc = new Doctor(id,name);
				//madrid.registerDoctor(name,hospitalid,specialityid, id);
				um.disconnect();
		    }catch (NoSuchAlgorithmException ex) {
			    System.out.println(ex.getMessage());
		    }
		} break;
		
		case 2:{
			try {
				//register patient
				Role role = um.getRole("patient");
				Patient patient = new Patient();
				//pedir atributos de doctor (setters)
				Integer id= Utils.leerEntero("Introduce ID (only numbers)"); 
				String mail = Utils.leerCadena("Introduce email");
				String password = Utils.leerCadena("introduce password:");
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] digest = md.digest();
				User u = new User(id,mail, digest,role);
				role.addUSer(u);
				um.newUser(u); //aqui se sube a la db
				//pm.addPatient(patient
				um.disconnect(); 
				
			}catch (NoSuchAlgorithmException ex) {
				System.out.println(ex.getMessage());
			}
		}break;
		
		case 0:{
			return;
		}
		}
	}
}
		


// String string = Utils.leerString("Introduce paciente o doctor");
// if (string.equalsIgnoreCase("doctor"){
// 
	    
		
	
	


