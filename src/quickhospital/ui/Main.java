package quickhospital.ui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Statement;
import java.util.ArrayList;

import quickhospital.jdbc.JDBCDoctorManager;
import quickhospital.jdbc.JDBCManager;
import quickhospital.jdbc.JDBCSpecialityManager;
import quickhospital.jdbc.JDBCSymptomsManager;
import quickhospital.jpa.JPAUserManager;
import quickhospital.pojos.*;
import quickhospital.utilities.Utils;

public class Main {
	
	private static ArrayList<Symptom> symptoms;
	private static ArrayList<Speciality> specialities;
	
	public static void main(String args[]) {
		JDBCManager manager = new JDBCManager();
		JDBCSymptomsManager sym = new JDBCSymptomsManager(manager);
		JDBCSpecialityManager spe = new JDBCSpecialityManager(manager);
		symptoms = sym.readSymptoms();
		specialities = spe.readSpecialities();
		
		
		
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
	
	
	public void showSymptoms() {  
        for (int i = 0; i < symptoms.size(); i++) {
            Symptom s = symptoms.get(i);
            System.out.println( s.getId()+". "+s.getName());
        }
    }
	
	public Speciality compareSymptoms(ArrayList<Symptom> symptoms) { //llenar el getSymptoms de la specialidad
		
		int tam = symptoms.size();
		int[] cont = new int[tam];
		
		for(int k = 0; k < tam; k++) {
			cont[k] = 0;
		}
		
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < specialities.size();) {
				if(specialities.get(j).getSymptoms().contains(symptoms.get(i))) {
					cont[j] += 1;
				}				
			}
		}
		
		int maximo = cont[0]; 
        
        for (int i = 1; i < cont.length; i++) {
            if (cont[i] > maximo) {
                maximo = cont[i];
            }
        }
        
        return specialities.get(maximo);
	}
	
	public static Speciality idtoSpeciality(int id) {
		for (int i = 0; i < specialities.size(); i++) {
			if (specialities.get(i).getId() == id) {
				return specialities.get(i);
			}
		}
		return null;
	}

}
