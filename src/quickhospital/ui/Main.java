package quickhospital.ui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.lang.*;
import quickhospital.ifaces.*;
import quickhospital.jdbc.*;
import quickhospital.jpa.*;
import quickhospital.pojos.*;
import quickhospital.utilities.*;

public class Main {

	private static ArrayList<Symptom> symptoms;
	private static ArrayList<Speciality> specialities;
	private static ArrayList<Hospital> hospitals;
	private static ArrayList<Doctor> doctors;
	private static ArrayList<Patient> patients;
	private static ArrayList<WaitingList> waitingLists;

	public static void main(String args[]) {
		JDBCManager manager = new JDBCManager();
		JDBCSymptomsManager sym = new JDBCSymptomsManager(manager);
		JDBCSpecialityManager spe = new JDBCSpecialityManager(manager);
		JDBCHospitalManager hm = new JDBCHospitalManager(manager);
		JDBCPatientManager pm = new JDBCPatientManager(manager);
		JDBCDoctorManager dm = new JDBCDoctorManager(manager);
		JDBCWaitingListManager wm = new JDBCWaitingListManager(manager);
		JPAUserManager um= new JPAUserManager();
		specialities = spe.readSpecialities();
		hospitals = hm.readHospitalDB();
		symptoms = sym.readSymptoms();
		hm.readHospSpec();
		spe.readSpecSymp();
		patients = pm.readPatients();
		doctors = dm.readDoctors();
		waitingLists = wm.readWaitingLists();
		

		int option;

		do {
			option = firstMenu();

			switch (option) {
			case 1: // Doctor
				do {
					option = patientLogInMenu();
					switch (option) {
					case 1:
						Doctor d = doctorLogIn(manager);
						ArrayList<WaitingList> w = waitingListForDoctor(d);
						if (d != null) {
							do {
								option = doctorMenu();
								switch (option) {
								case 1:
									if(w != null) {
										showWaitingList(w);
									 }else {
										 System.out.println("THERE ARE NO PATIENTS IN THE WAITING LIST!!");
									 }
									break;
								case 2:
									if(w != null) {
										showWaitingList(w);
										int id = Utils.leerEntero("Select the id of the patient whose symptoms you want to see: ");
										Patient pat = idToPatient(id);
										showPatientSymptoms(pat);
									 }else {
										 System.out.println("THERE ARE NO PATIENTS IN THE WAITING LIST!!");
									 }
									break;
								case 3:
									// Log out
									manager.disconnect();
									um.disconnect();
									// salir del jpa
									System.out.println("BYE, THANKS FOR USING QUICKHOSPITAL!!");
									System.exit(0);
									break;
								}

							} while (option!=0);
						}else {
							System.out.println("USERNAME/PASSWORD INCORRECT OR USER NOT REGISTERED");
						}
						break;
					case 2:
						doctorRegister(manager);
						break;
					}
				} while (option!=0);
				break;

			case 2: // Patient
				do {
					option = patientLogInMenu();
					switch (option) {
					case 1:
						Patient p = patientLogIn(manager);
						if (p != null) {
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
										if (numeros >= 1 && numeros < symptoms.size()) {
											symp_id.add(numeros);
										} else if(numeros != 0){
											System.out.println("THIS NUMBER IS NOT ON THE LIST");
										}
									} while (numeros != 0);
									symptomsPatient = idToSymptoms(symp_id);
									p.setSymptoms(symptomsPatient); 
									sp = compareSymptoms(symptomsPatient);
									System.out.println(sp.getName());
									showHospitalsWithSelectedSpeciality(sp);
									int id = Utils.leerEntero("Select the id of the hospital you wish to attend: ");
									boolean is=false;
									do {
										id = Utils.leerEntero("Select the id of the hospital you wish to attend: ");
										for(int i = 0; i < hospitals.size(); i++) {
											if(id == hospitals.get(i).getId()) {
												is=true;
											}
										}
									}while(!is); 
									appointment(id, sp.getId(), p,wm);
									break;
								case 2:
									abbandonWaitingList(p);
									break;
								case 3:
									manager.disconnect();
									um.disconnect();
									// salir del jpa
									System.out.println("BYE, THANKS FOR USING QUICKHOSPITAL!!");
									System.exit(0);
									break;
								}
							} while (option != 0);
						}else {
							System.out.println("USERNAME/PASSWORD INCORRECT OR USER NOT REGISTERED");
						}
						break;
					case 2:
						patientRegister(manager);
						break;
					}					
				} while (option != 0);
				break;

			case 3:// Administrator
				do {
					
					option = administratorMenu();
					switch (option) {
					case 1:
						addHospital(hm);
						break;
					case 2:
						deleteHospital(hm);
						break;
					case 3:
						addNewSpeciality(spe);
						break;
					case 4:
						addExistingSpecialityToHospital(hm);
						break;
					case 5:
						deleteSpeciality(hm);
						break;
					case 6:
						manager.disconnect();
						// salir del jpa
						System.out.println("BYE, THANKS FOR USING QUICKHOSPITAL!!");
						System.exit(0);
						break;

					}
				} while (option != 0);
			}
		} while (option != 0);
		

	}

	public static void registerDoctor(Doctor doctor, int id, int id2) { 
		int pos = hospitals.get(id - 1).specialityIdtoPosition(id2);
		hospitals.get(id - 1).getSpecialities().get(pos).getDoctors().add(doctor);
	}

	// FUNCIONES WAITING LIST

	public static void appointment(int h_id, int sp_id, Patient p, JDBCWaitingListManager wm ) {
		WaitingList w = new WaitingList(h_id, sp_id);
		ArrayList<LocalTime>times= new ArrayList<>();
		for(int i=0; i<waitingLists.size();i++) {
			if(w.getDate().equals(waitingLists.get(i).getDate())&& w.getHosp_Id().equals(waitingLists.get(i).getHosp_Id())&& w.getSp_Id().equals(waitingLists.get(i).getSp_Id())) {
				times.add(waitingLists.get(i).getTime());

			}
		}
		if(times.isEmpty()) {
			w.setPatients(p);
			String timeString = "08:00:00";
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	        LocalTime parsedTime = LocalTime.parse(timeString, formatter);
			w.setTime(parsedTime);
		}else {
			w.setPatients(p);
			w.setTime(times.get(times.size()-1).plusMinutes(30));
		}
		
		System.out.println("Your appointment is at: " + w.getDate() + " " + w.getTime());
		wm.addWaitingList(w);
		
	}
	

	public static void abbandonWaitingList(Patient p) {
		int pos = 0;
		for (int i = 0; i < waitingLists.size(); i++) {
			if (waitingLists.get(i).getPatients()==p) {
				waitingLists.remove(i);
				pos=i;
			} else {
				System.out.println("You were not in a waiting list");
			}
		}
		for(int i=pos; i<waitingLists.size(); i++) {
			LocalTime time= waitingLists.get(i).getTime();
			waitingLists.get(i).setTime(time.minusMinutes(30));
		}
		
	}
	
	

	public static ArrayList<WaitingList> waitingListForDoctor(Doctor d) {
		ArrayList<WaitingList> waitings= new ArrayList<>();
		for (int i = 0; i < waitingLists.size(); i++) {
			if ((waitingLists.get(i).getHosp_Id() == d.getHosp_id()) && (waitingLists.get(i).getSp_Id() == d.getSpeciality_id())) {
				waitings.add(waitingLists.get(i));
			}
		}
		return waitings;
	}

	public static void showWaitingList(ArrayList<WaitingList> waitings ) {
		for (int i = 0; i < waitings.size(); i++) {
			System.out.println(waitings.get(i));
		}
	}

	
	// FUNCIONES DE HOSPITALS

	public static void showHospitals() {
		for (int i = 0; i < hospitals.size(); i++) {
			System.out.println(hospitals.get(i));
		}
	}

	public static void showHospitalsWithSelectedSpeciality(Speciality sp) {
		for (int i = 0; i < hospitals.size(); i++) {
			if (hospitals.get(i).getSpecialities().contains(sp)) {
				System.out.println(hospitals.get(i));
			}
		}
	}

	public static Hospital idToHospital(int id) {
		for (int i = 0; i < hospitals.size(); i++) {
			if (hospitals.get(i).getId() == id) {
				return hospitals.get(i);
			}
		}

		return null;
	}

	public static void addSpeciality(Speciality s, Hospital h) {// add speciality to hospital while reading table
																// Hospital Specialities
		for (int i = 0; i < hospitals.size(); i++) {
			if (hospitals.get(i).equals(h)) {
				hospitals.get(i).addSpeciality(s);
			}
		}
	}

	
	// FUNCIONES DE SPECIALITIES

	public static void showSpecialities() {
		for (int i = 0; i < specialities.size(); i++) {
			System.out.println(specialities.get(i));
		}
	}

	public static void showSpecialitiesFromAHospital(int id) {
		for (int i = 0; i < hospitals.get(id - 1).getSpecialities().size(); i++) {
			System.out.println(hospitals.get(id - 1).getSpecialities().get(i).getId() + " " + hospitals.get(id - 1).getSpecialities().get(i).getName());
		}
	}

	public static Speciality idToSpeciality(int id) {
		for (int i = 0; i < specialities.size(); i++) {
			if (specialities.get(i).getId() == id) {
				return specialities.get(i);
			}
		}

		return null;
	}

	public static void addSymptom(Symptom s, Speciality sp) {
		for (int i = 0; i < specialities.size(); i++) {
			if (specialities.get(i).equals(sp)) {
				specialities.get(i).addSymptom(s);
			}
		}
	}

	
	// FUNCIONES DE SYMPTOMS

	public static void showPatientSymptoms(Patient p) {
		for (int i = 0; i < p.getSymptoms().size(); i++) {
			System.out.println(p.getSymptoms().get(i).getName());
		}
	}

	public static void showSymptoms() {
		for (int i = 0; i < symptoms.size(); i++) {
			System.out.println(symptoms.get(i));
		}
	}

	public static ArrayList<Symptom> idToSymptoms(ArrayList<Integer> symptoms_id) {
		ArrayList<Symptom> symptomsPatient = new ArrayList<>();

		for (int i = 0; i < symptoms_id.size(); i++) {
			for (int j = 0; j < symptoms.size(); j++) {
				if (symptoms_id.get(i) == symptoms.get(j).getId()) {
					symptomsPatient.add(symptoms.get(j));
				}
			}
		}

		return symptomsPatient;

	}

	public static Speciality compareSymptoms(ArrayList<Symptom> symptoms) { 

		int[] cont = new int[specialities.size()];

		for (int k = 0; k < specialities.size(); k++) {
			cont[k] = 0;
		}

		for (int i = 0; i < symptoms.size(); i++) {
			for (int j = 0; j < specialities.size(); j++) {
				if (specialities.get(j).getSymptoms().contains(symptoms.get(i))) {
					cont[j]++;
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

		for (int i = 0; i < cont.length; i++) {
			if (cont[i] == maximo) {
				pos = i;
			}
		}

		return specialities.get(pos);
	}

	public static Symptom idtoSymptom(int id) {
		for (int i = 0; i < symptoms.size(); i++) {
			if (symptoms.get(i).getId() == id) {
				return symptoms.get(i);
			}
		}
		return null;
	}

	
	// FUNCIONES DEL ADMINISTRADOR

	public static void addHospital(JDBCHospitalManager hm) {
		Integer id = hospitals.size() + 1;
		String name = Utils.leerCadena("Insert hospital's name: ");
		Integer capacity = Utils.leerEntero("\"Insert hospital's capacity: ");
		String location = Utils.leerCadena("Insert hospital's location: ");
		Hospital h = new Hospital(id, name, capacity, location);
		hospitals.add(h);
		hm.addHospital(h);
	}

	public static void deleteHospital(JDBCHospitalManager hm ) {
		try {
			showHospitals();
			int aux = Utils.leerEntero("Choose the hospital you want to delete: ");
			hospitals.set(aux-1, null);
			//hospitals.remove(aux - 1);
			hm.deleteHospital(aux);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}
	}

	public static void addNewSpeciality(JDBCSpecialityManager sm) {
		String name = Utils.leerCadena("Insert speciality's name: ");
		int id = specialities.size() + 1;
		Speciality sp = new Speciality(id, name);
		specialities.add(sp);
		sm.addSpeciality(sp);
	}

	public static void addExistingSpecialityToHospital(JDBCHospitalManager hm) {
		try {
			showHospitals();
			int aux = Utils.leerEntero("Choose the hospital you want to add the speciality: ");
			if (hospitals.get(aux - 1) != null) {
				int id = Utils.leerEntero("Insert speciality's id: ");
				Speciality sp = idToSpeciality(id);
				hospitals.get(aux - 1).getSpecialities().add(sp);
				hm.addSpecialityToHospital(aux, id);
			} else {
				System.out.println("This hospital doesn't exist!");
			}
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}
	}

	public static void deleteSpeciality(JDBCHospitalManager hm) {
		try {
			showHospitals();
			int aux = Utils.leerEntero("Choose the hospital you want to delete the speciality: ");
			if (hospitals.get(aux - 1) != null) {
				showSpecialitiesFromAHospital(aux);
				int aux2 = Utils.leerEntero("Choose the speciality you want to delete: ");
				int pos = hospitals.get(aux - 1).specialityIdtoPosition(aux2);
				hospitals.get(aux-1).getSpecialities().set(pos, null);
				//hospitals.get(aux - 1).getSpecialities().remove(pos);
				hm.deleteSpeciality(aux, aux2);
			} else {
				System.out.println("This hospital doesn't exist!");
			}
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex);
		}
	}

	
	// FUNCIONES DE PATIENT
	
	public static Patient patientLogIn(JDBCManager manager) {
		JPAUserManager um = new JPAUserManager();
		JDBCPatientManager pm = new JDBCPatientManager(manager);
		String mail = Utils.leerCadena("Introduce your email");
		String password = Utils.leerCadena("Introduce password");
		User u = um.checkPassword(mail, password);
		Patient p = null;
		if(u != null && u.getRole().getId() == 2) {
			int id= pm.emailToId(mail);
			p = idToPatient(id);//FUNCION ID TO PATIENT ---> vale asi o hago pm.idToPatient(u.getId())
			return p;
		}else {
			return null;
		}
	}
	
	public static void patientRegister(JDBCManager manager) {
		try {
			JPAUserManager um = new JPAUserManager();
			JDBCPatientManager pm = new JDBCPatientManager(manager);
			Role role = um.getRole(2);
			String name = Utils.leerCadena("Introduce your name: ");
			String email = Utils.leerCadena("Introduce email: ");
			String password = Utils.leerCadena("introduce password:");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			User u = new User(email, digest, role);
			um.newUser(u);
			pm.addPatient(name,email);
			int id = pm.emailToId(email);
			Patient patient = new Patient(id, name, email);
			patients.add(patient);
			//role.addUSer(u);
			//u.setRole(role);
			 // aqui se sube a la db
			um.disconnect();
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static Patient idToPatient(int id) {
		for (int i = 0; i < patients.size(); i++) {
			if (patients.get(i).getId() == id) {
				return patients.get(i);
			}
		}
		return null;
	}
	
	
	// FUNCIONES DE DOCTOR
	
	public static Doctor doctorLogIn(JDBCManager manager) {
		JPAUserManager um = new JPAUserManager();
		JDBCDoctorManager dm = new JDBCDoctorManager(manager);
		String mail = Utils.leerCadena("Introduce your email");
		String password = Utils.leerCadena("Introduce password");
		User u = um.checkPassword(mail, password);
		Doctor d = null;
		if(u != null && u.getRole().getId() == 1) {
			int id=dm.emailToId(mail);
			d = idToDoctor(id);//FUNCION ID TO DOCTOR ****** asi o la busco en la db
			return d;
		}else {
			return null;
		}
	}
	
	public static void doctorRegister(JDBCManager manager) {
		JPAUserManager um = new JPAUserManager();
		JDBCDoctorManager dm = new JDBCDoctorManager(manager);
		int hospitalid;
		int specialityid;
		try {
			// register doctor
			Role role= um.getRole(1);
			// pedir atributos de doctor (setters)
			String name = Utils.leerCadena("Introduce your name");
			showHospitals();
			do {
				hospitalid = Utils.leerEntero("Introduce the number of hospital you work in");
			}while(hospitalid < 1 || hospitalid >= hospitals.size());
			showSpecialitiesFromAHospital(hospitalid);
			boolean is =false;
			do {
				specialityid = Utils.leerEntero("Introduce the number of your speciality");
				for(int i = 0; i < hospitals.get(hospitalid-1).getSpecialities().size() && specialityid < specialities.size(); i++ ) {
					if(specialityid == hospitals.get(hospitalid-1).getSpecialities().get(i).getId()) {
						is=true;
					}
				}
			}while(!is);
			String mail = Utils.leerCadena("Introduce email");
			String password = Utils.leerCadena("introduce password:");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			User u = new User(mail, digest, role);
			//role.addUSer(u);
			um.newUser(u);
			dm.newDoctor(name, hospitalid, specialityid, mail);
			int id= dm.emailToId(mail);
			Doctor doc = new Doctor(id, name, hospitalid, specialityid, mail);
			doctors.add(doc);
			registerDoctor(doc, hospitalid, specialityid);
			um.disconnect();
			// USER
			
			
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static Doctor idToDoctor(int id) {
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).getId() == id) {
				return doctors.get(i);
			}
		}
		return null;
	}
	

	// DATES

	public static LocalDate stringToDate(String d) {
		LocalDate date = LocalDate.parse(d, DateTimeFormatter.ofPattern("yyyy-MM-d"));
		return date;

	}

	public static LocalTime stringToTime(String t) {
		LocalTime time = LocalTime.parse(t, DateTimeFormatter.ofPattern("H:mm:ss.SSSSSS"));
		return time;
	}

	
	// MENUS

	public static int firstMenu() {

		int option = 0;

		do {
			System.out.println("1.I am a doctor:");
			System.out.println("2.I am a patient:");
			System.out.println("3.I am an administrator :");
			option = Utils.leerEntero("Introduzca una opcion (0 para salir): ");
		} while (option > 3 || option < 0);

		return option;
	}

	public static int patientMenu() {
		int option = 0;

		do {
			System.out.println("1.Insert symptoms");
			System.out.println("2.Abandon Waiting List"); 
			System.out.println("3.Log out");
			option = Utils.leerEntero("Introduzca una opcion (0 para salir): ");
		} while (option > 3 || option < 0);

		// get the report of the waiting list
		return option;
	}
	
	public static int patientLogInMenu() {
		int option = 0;
		
		do {
			System.out.println("1. Log in");
			System.out.println("2. Register new account");
			System.out.println("3. Exit");
			option = Utils.leerEntero("Introduzca una opcion (0 para salir): ");
		} while (option > 3 || option < 0);

		return option;
	}

	public static int doctorMenu() {

		int option = 0;

		do {
			System.out.println("1.View waitingList(List of patients of the day:");
			System.out.println("2.View patient symptoms:");
			System.out.println("3.Log out");
			option = Utils.leerEntero("Introduzca una opcion (0 para salir): ");
		} while (option > 3 || option < 0);

		return option;
	}

	public static int doctorMenu2() { // esto sobra
		int option = 0;
		do {
			System.out.println("You can view patient symptoms");
			System.out.println("1. Search patient by name:");
			System.out.println("2. Search patient by id:");
			option = Utils.leerEntero("Introduzca una opcion (0 para salir): ");
		} while (option < 1 || option > 3);

		return option;

	}

	public static int administratorMenu() {

		int option = 0;

		do {
			System.out.println("");
			System.out.println("Menu");
			System.out.println("1. Add Hospital");
			System.out.println("2. Delete Hospital");
			System.out.println("3. Add new Speciality");
			System.out.println("4. Add existing Speciality to a Hospital");
			System.out.println("5. Delete Speciality from an Hospital");
			System.out.println("6. Log out");

			option = Utils.leerEntero("Introduce an option (0 to exit): ");

		} while (option > 6 || option < 1);

		return option;
	}

}
