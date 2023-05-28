package quickhospital.ui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import quickhospital.ifaces.DBManager;
import quickhospital.jdbc.JDBCDoctorManager;
import quickhospital.jdbc.JDBCHospitalManager;
import quickhospital.jdbc.JDBCManager;
import quickhospital.jdbc.JDBCSpecialityManager;
import quickhospital.jdbc.JDBCSymptomsManager;
import quickhospital.jpa.JPAUserManager;
import quickhospital.jdbc.JDBCPatientManager;
import quickhospital.pojos.*;
import quickhospital.utilities.Utils;

public class Main {
	
	private static ArrayList<Symptom> symptoms;
	private static ArrayList<Speciality> specialities;
	private static ArrayList<Hospital> hospitals;
	private static City Madrid;
	
	public static void main(String args[]) {
		JDBCManager manager = new JDBCManager();
		JDBCSymptomsManager sym = new JDBCSymptomsManager(manager);
		JDBCSpecialityManager spe = new JDBCSpecialityManager(manager);
		JDBCHospitalManager h = new JDBCHospitalManager(manager); 
		specialities = spe.readSpecialities();
		hospitals = h.readHospitalDB(); 
		
		
		//manager.executeSQLfile("/Users/jaimedemiguel/git/QuickHospital/src/quickhospital/db/dml_Hospitals.sql");
		//manager.startProgram();
		
		int option;
		String read;
		//Leer create tables y insert************
		do {
			firstMenu();
			option = firstMenu();
			
			switch(option) {
			case 1: //si es administrator 
			
				break;
			
			case 2: // si es paciente
				
				do {
					
					
					
					option = patientMenu();
					switch (option) {
					case 1:
						
						int numeros;
						ArrayList<Integer> symp_id = new ArrayList<>();
						ArrayList<Symptom> symptomsPatient;
						Speciality sp;
						
						showSymptoms();
	
						do {
							numeros = Utils.leerEntero("Type the numbers corresponding to the symptoms you have (To stop adding symptoms type '0'):");
							if(numeros < 0 || numeros > symptoms.size()) {				
								symp_id.add(numeros);								
							}else {
								System.out.println("Number not in the list");
							}
						} while (numeros != 0);
						
						symptomsPatient = idToSymptoms(symp_id);
						sp = compareSymptoms(symptomsPatient);	
						showHospitalsWithSelectedSpeciality(sp);
						int id = Utils.leerEntero("Select the id of the hospital you wish to attend: ");
						//ahora con esto conseguir el waiting list

						break;
					case 2:
						break;
	
					case 3:
						break;
					}
				} while (option != 0);
				break;
				
			case 3:
				do {
					option = administratorMenu();
					switch(option){
	                case 1: 
	                    addHospital();
	                    break;
	                case 2:
	                    deleteHospital();
	                    break;
	                case 3:
	                	addNewSpeciality();
	                	break;
	                case 4:
	                	addExistingSpecialityToHospital();
	                    break;
	                case 5:
	                    deleteSpeciality();
	                    break;                           
	            }
				}while (option != 0);
			}
		} while (option != 0);
		// HACER LOG IN CON JPA

		// UNA VEZ HECHO EL LOG IN

	}	
	//FUNCIONES DE HOSPITALS
	
	public static void showHospitalsWithSelectedSpeciality(Speciality sp) {
		for(int i = 0; i < hospitals.size(); i++) {
			if(hospitals.get(i).getSpecialities().contains(sp)) {
				System.out.println(hospitals.get(i));
			}
		}
	}
	
	
	//FUNCIONES DE SPECIALITIES
	
	public static void showSpecialities() {  
        for (int i = 0; i < specialities.size(); i++) {
            System.out.println(specialities.get(i));
        }
    }
	
	public static Speciality idToSpeciality(int id) {
		for(int i = 0; i < specialities.size(); i++) {
			if(specialities.get(i).getId() == id) {
				return specialities.get(i);
			}
		}
		
		return null;		
	}
	
	
	//FUNCIONES DE SYMPTOMS
	
	public static void showSymptoms() {  
        for (int i = 0; i < symptoms.size(); i++) {
            System.out.println(symptoms.get(i));
        }
    }
	
	public static ArrayList<Symptom> idToSymptoms(ArrayList<Integer> symptoms_id){
		ArrayList<Symptom> symptomsPatient = new ArrayList<>();
		
		for(int i = 0; i < symptoms_id.size(); i++) {
			for(int j = 0; j < symptoms.size(); j++) {
				if(symptoms_id.get(i) == symptoms.get(j).getId()) {
					symptomsPatient.add(symptoms.get(j));
				}
			}
		}
		
		return symptomsPatient;
		
	}
	
	public static Speciality compareSymptoms(ArrayList<Symptom> symptoms) { //llenar el getSymptoms de la specialidad
		
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
        
        int pos = 0;
        
        for(int i = 0; i < cont.length; i++){
            if(cont[i] == maximo){
                pos = i;
            }
        }
        
        return specialities.get(pos);
	}
	
	public static Speciality idtoSpeciality(int id) {
		for (int i = 0; i < specialities.size(); i++) {
			if (specialities.get(i).getId() == id) {
				return specialities.get(i);
			}
		}
		return null;
	}
	
	
	//FUNCIONES DEL ADMINISTRADOR
	
	public static void addHospital() {
		Integer id = Madrid.getHospitals().size()+1;
		String name = Utils.leerCadena("Insert hospital's name: ");
		Integer capacity = Utils.leerEntero("\"Insert hospital's capacity: ");
		String location = Utils.leerCadena("Insert hospital's location: ");
		Hospital h = new Hospital(id, name, capacity, location);
		Madrid.addHospital(h); 
		hospitals.add(h);
		//añadir tambien a la base de datos
	}
	
	public static void deleteHospital() {
		try{
            Madrid.showHospitals();
            int aux = Utils.leerEntero("Choose the hospital you want to delete: ");
            Madrid.deleteHospital(aux);
        }catch(IndexOutOfBoundsException ex){
            System.out.println(ex);
        }
		//quitarlo tambien de la base de datos
	}
	
	public static void addNewSpeciality() {
		String name = Utils.leerCadena("Insert speciality's name: ");  
		int id = specialities.size()+1;
        Speciality sp = new Speciality(id, name);
        specialities.add(sp);
        //añadir tambien a la base de datos
	}
	
	public static void addExistingSpecialityToHospital() { 
		try{
			Madrid.showHospitals();
            int aux = Utils.leerEntero("Choose the hospital you want to add the speciality: "); 
            if(Madrid.getHospitals().get(aux-1) != null){
                int id = Utils.leerEntero("Insert speciality's id: ");
                Speciality sp = idToSpeciality(id);
                Madrid.addSpeciality(sp, aux);
            }else{
                System.out.println("This hospital doesn't exist!");
            }
        }catch(IndexOutOfBoundsException ex){
            System.out.println(ex);
        }
	}
	
	
	public static void deleteSpeciality() {
		try{
			Madrid.showHospitals();
            int aux = Utils.leerEntero("Choose the hospital you want to delete the speciality: "); 
            if(Madrid.getHospitals().get(aux-1) != null){
                Madrid.showSpecialities(aux);
                int aux2 = Utils.leerEntero("Choose the speciality you want to delete: "); 
                int pos = Madrid.getHospitals().get(aux-1).specialityIdtoPosition(aux2);
                Madrid.deleteSpeciality(aux, pos);
            }else{
                System.out.println("This hospital doesn't exist!");
            }
        }catch(IndexOutOfBoundsException ex){
            System.out.println(ex);
        }
	}
	
	
	//MENUS
	
	public static int firstMenu() {
		
		int opcion = 0;
		
		do {
			System.out.println("1.Log in as a administrator:");
			System.out.println("2.Log in as a patient:");
			System.out.println("2.Log in as a doctor:");	
		}while(opcion > 3 || opcion < 0);
		
		return opcion;
	}

	public static int patientMenu() {
		int opcion = 0;
		
		do {
			System.out.println("1.Insert symptoms");
			System.out.println("2.Abandon Waiting List");
			System.out.println("3.Log out");
		}while(opcion > 3 || opcion < 0);
		//get the report of the waiting list
		return opcion;
	}

	public static int doctorMenu() {
		
		int opcion = 0;
		
		do {
			System.out.println("1.Change timetable");
			System.out.println("2.Log out");
			opcion = Utils.leerEntero("Introduzca una opcion (0 para salir): ");
		}while(opcion > 2 || opcion < 0);
		
		return opcion;
	}
	
	public static int administratorMenu(){
        
        int opcion = 0;
        
        do{
            System.out.println("");
            System.out.println("Menu");
            System.out.println("1. Add Hospital");
            System.out.println("2. Delete Hospital");
            System.out.println("3. Add new Speciality");
            System.out.println("4. Add existing Speciality to a Hospital");
            System.out.println("5. Delete Speciality");

            opcion = Utils.leerEntero("Introduzca una opcion (0 para salir): ");
            
        }while(opcion > 5 || opcion < 0);
    
        return opcion;    
    }
	public void patientLogIn (JDBCManager manager) {
		
		System.out.println("1. Log in");
		System.out.println("2. Register new account");
		int option = Utils.leerEntero("Choose an option:");
		switch (option) {
		case 1 :{
			JPAUserManager um = new JPAUserManager();
			String mail = Utils.leerCadena("Introduce your email");
			String password = Utils.leerCadena("Introduce password");
			User u = um.checkPassword(mail, password);
			if (u != null) {
				
				do {
					option = patientMenu();
					switch (option) {
					case 1:{	
						int numeros;
						ArrayList<Integer> symp_id = new ArrayList<>();
						ArrayList<Symptom> symptomsPatient;
						Speciality sp;
						showSymptoms();
						do {
							numeros = Utils.leerEntero("Type the numbers corresponding to the symptoms you have (To stop adding symptoms type '0'):");
							if(numeros < 0 || numeros > symptoms.size()) {				
								symp_id.add(numeros);								
							}else {
								System.out.println("Number not in the list");
							}
						} while (numeros != 0);
						
						symptomsPatient = idToSymptoms(symp_id);
						sp = compareSymptoms(symptomsPatient);	
						showHospitalsWithSelectedSpeciality(sp);
						int id = Utils.leerEntero("Select the id of the hospital you wish to attend: ");
						//ahora con esto conseguir el waiting list

						break;
					}
					case 2:{
						break;
					}
	
					case 3:{
						um.disconnect();
						break;
					}
				}} while (option <0 || option >3);
		}else {
			System.out.println ("Incorrect password/username or this account does not exist");
		}
			break;
			
		}
		case 2: {
			try {
			JPAUserManager um = new JPAUserManager();
			JDBCPatientManager pm = new JDBCPatientManager(manager);
			Role role = um.getRole("patient");
			Patient patient = new Patient();
            String name = Utils.leerCadena("Introduce your name: ");
            patient.setName(name);
			String email = Utils.leerCadena("Introduce email: ");
			String password = Utils.leerCadena("introduce password:");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			User u = new User(email, digest,role);
			role.addUSer(u);
			u.setRole(role);
			um.newUser(u); //aqui se sube a la db
			pm.addPatient(patient.getName());
			um.disconnect(); 
			}catch(NoSuchAlgorithmException ex) {
				System.out.println(ex.getMessage());
			}
			break;	
			}
		}
	}
	
public void doctorLogIn (JDBCManager manager, City madrid) {
	
	System.out.println("1. Log in");
	System.out.println("2. Register new account");
	int option = Utils.leerEntero("Choose an option:");
	switch (option){
	case 1:{
		JPAUserManager um = new JPAUserManager();
		String mail = Utils.leerCadena("Introduce your email");
		String password = Utils.leerCadena("Introduce password");
		User u = um.checkPassword(mail, password);
		if(u != null) {
			//aqui va el menu del doctor
		}else {
			System.out.println("Incorrect password/username or this account does not exist");
		}
		break;
	}
	case 2:{
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
			um.newUser(u);  
			dm.newDoctor(name,hospitalid,specialityid);
			int id= dm.getId(name);
			Doctor doc = new Doctor(id, name);
			madrid.registerDoctor(doc, hospitalid, specialityid);
			um.disconnect();
	    }catch (NoSuchAlgorithmException ex) {
		    System.out.println(ex.getMessage());
	    }
		break;
	}
	}
}




}

