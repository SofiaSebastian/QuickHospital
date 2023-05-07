package quickhospital.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import quickhospital.utilities.Exceptions;
import java.util.ArrayList;

import quickhospital.ifaces.DBManager;
import quickhospital.jdbc.*;
import quickhospital.pojos.*;
import java.util.*;

public class UserInterface {

	private static DBManager db = new JDBCManager();

	public static void firstMenu() {
		System.out.println("1.Log in as a doctor:");
		System.out.println("2.Log in as a patient:");
	}

	public static void patientMenu() {
		System.out.println("1.Appointment");
		System.out.println("2.Abandom Waiting List");
		System.out.println("3.Log out");
		//get the report of the waiting list
	}

	public static void doctorMenu() {
		System.out.println("1.Change timetable");
		System.out.println("2.Log out");
	}

	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 7;
		String read;
		//Leer create tables y insert************
		do {
			firstMenu();
			option = Exceptions.leerEntero("TYPE AN OPTION:");
		} while (option < 1 || option > 2);
		// HACER LOG IN CON JPA

		// UNA VEZ HECHO EL LOG IN

		// SI ES PACIENTE
		do {
			patientMenu();
			option = Exceptions.leerEntero("TYPE AN OPTION:");

		} while (option < 1 || option > 3);

		switch (option) {
		case 1:
			ArrayList<Symptom> s = new ArrayList<>();
			s = db.getSymptoms();
			db.showSymptoms(s);
			System.out.println("Type the numbers corresponding to the symptoms you have:");
			System.out.println("(To stop adding symptoms type 'x')");
			ArrayList<Integer> symp_id = new ArrayList<>();

			try {
				do {
					read = br.readLine();
					int i;
					if (Exceptions.esInt(read)) {
						i = Exceptions.pasarInt(read);
						if (i >= 1 && i <= s.size()) {
							symp_id.add(i);
						} else {
							System.out.println("Number not in the list");
						}
					}
				} while (!read.equalsIgnoreCase("x"));
				String sp=db.compareSymptoms(symp_id);
				System.out.println("The corresponding specialy is "+ sp);
				db.showHospitals(sp);
				option = Exceptions.leerEntero("TYPE THE NUMBER CORRESPONDING TO THE HOSPITAL TO BE ADDED TO THE WAITING LIST:");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		case 2:

		case 3:
		}

	}

}
