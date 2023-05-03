package quickhospital.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import quickhospital.utilities.Exceptions;

public class UserInterface {
	
	public static void firstMenu() {
		System.out.println("1.Log in as a doctor:");
		System.out.println("2.Log in as a patient:");
	}
	
	public static void patientMenu() {
		System.out.println("1.Appointment");
		System.out.println("2.Check my position in the Waiting List");
		System.out.println("3.Abandom Waiting List");
		System.out.println("4.Log out");
		System.out.println("TYPE AN OPTION:");
		
	}
	
	public static void doctorMenu() {
		System.out.println("1.Add timetable");
		System.out.println("2.Log out");
		System.out.println("TYPE AN OPTION:");
	}
	
	
	public static void main(String args[]) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int option=7;
		String read;
		do {
            firstMenu();
			try {
				read = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				read=null;
			}
            if(Exceptions.esInt(read)){
              option=Exceptions.pasarInt(read);
            }
        } while (option < 1 || option > 2);
		//HACER LOG IN CON JPA
		
		//UNA VEZ HECHO EL LOG IN
		
		//SI ES PACIENTE
		do {
			patientMenu();
          
			try {
				read = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				read=null;
			}
            if(Exceptions.esInt(read)){
              option=Exceptions.pasarInt(read);
            }
		}while(option<1 || option>4);
		
		switch(option) {
			case 1:
				System.out.println("Type the numbers corresponding to the symptoms you have:");
				System.out.println("(To stop adding symptoms type 'x')");
				
		
		}
			
	}

}
