package quickhospital.utilities;

import quickhospital.pojos.*;

public class Menu {
	private static final City Madrid = new City();
    private static int aux;
    private static int aux2;
	
	public static void main(String[] args) {
        int opcion;

        do{
            opcion = menuAdministrador();
            
            switch(opcion){
                case 1: 
                    addHospital();
                    break;
                case 2:
                    deleteHospital();
                    break;
                case 3:
                    addSpeciality();
                    break;
                case 4:
                    deleteSpeciality();
                    break;
                case 5:
                    addSymptoms();
                    break;
                case 6:
                    deleteSymptoms();
                    break;
                           
            }
        }while(opcion != 0);
    }
	
	public static void addHospital() {
		Integer id = Madrid.getHospitals().size()+1;
		String name = Utils.leerCadena("Insert hospital's name: ");
		Integer capacity = Utils.leerEntero("\"Insert hospital's capacity: ");
		String location = Utils.leerCadena("Insert hospital's location: ");
		Integer administrator = Utils.leerEntero("Insert hospital's administrator id: ");
		Madrid.addHospital(id, name, capacity, location, administrator);
	}
	
	public static void deleteHospital() {
		try{
            Madrid.showHospitals();
            aux = Utils.leerEntero("Choose the hospital you want to delete: ");
            Madrid.deleteHospital(aux);
        }catch(IndexOutOfBoundsException ex){
            System.out.println(ex);
        }
	}
	
	public static void addSpeciality() {
		try{
			Madrid.showHospitals();
            aux = Utils.leerEntero("Choose the hospital you want to add the speciality: "); 
            if(Madrid.getHospitals().get(aux-1) != null){
                Integer id = Madrid.getHospitals().get(aux-1).getSpecialities().size()+1;
                String name = Utils.leerCadena("Insert speciality's name: ");
                Speciality sp = new Speciality();
                Madrid.addSpecialitytoHospital(sp, aux);
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
            aux = Utils.leerEntero("Choose the hospital you want to delete the speciality: "); 
            if(Madrid.getHospitals().get(aux-1) != null){
                Madrid.showSpecialityfromHospital(aux);
                aux2 = Utils.leerEntero("Choose the speciality you want to delete: "); 
                Madrid.deleteSpecialityfromHospital(aux, aux2);
            }else{
                System.out.println("This hospital doesn't exist!");
            }
        }catch(IndexOutOfBoundsException ex){
            System.out.println(ex);
        }
	}
	
	public static void addSymptoms() {
		Integer id = Madrid.getHospitals().get(aux-1).getSpecialities().get(0).getSymptoms().size()+1;
        String name = Utils.leerCadena("Insert symptoms's name: ");
        Symptom sym = new Symptom(id, name);
        Madrid.showSpecialityfromHospital(aux);
        aux2 = Utils.leerEntero("Choose the speciality you want to add the symptom to: ");
        for(int i = 0; i < Madrid.getHospitals().size(); i++) {
    		Madrid.getHospitals().get(i).getSpecialities().get(aux2-1).addSymptom(sym);
        }
	}
	
	public static void deleteSymptoms() {
		Madrid.showSpecialityfromHospital(aux);
        aux2 = Utils.leerEntero("Choose the speciality you want to delete the symptom from: ");
		//Madrid.showSymptoms;
		aux = Utils.leerEntero("Choose the symptom you want to delete from the speciality: ");
        for(int i = 0; i < Madrid.getHospitals().size(); i++) {
    		Madrid.getHospitals().get(i).getSpecialities().get(aux2-1).deleteSymptom(aux);	
        }
	}
	
	
	public static int menuAdministrador(){
        
        int opcion = 0;
        
        do{
            System.out.println("");
            System.out.println("Menu");
            System.out.println("1. Add Hospital");
            System.out.println("2. Delete Hospital");
            System.out.println("3. Add Speciality");
            System.out.println("4. Delete Speciality");
            System.out.println("5. Add Symptoms");
            System.out.println("6. Delete Symptoms");

            opcion = Utils.leerEntero("Introduzca una opcion (0 para salir): ");
            
        }while(opcion > 6 || opcion < 0);
    
        return opcion;    
    }    
}
