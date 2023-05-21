package quickhospital.utilities;

import quickhospital.pojos.*;

public class Menu {
	private static final City Madrid = new City();
	
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
                	break;
                case 4:
                    //addSpeciality();
                    break;
                case 5:
                    deleteSpeciality();
                    break;                           
            }
        }while(opcion != 0);
    }
	
	public static void addHospital() {
		Integer id = Madrid.getHospitals().size()+1;
		String name = Utils.leerCadena("Insert hospital's name: ");
		Integer capacity = Utils.leerEntero("\"Insert hospital's capacity: ");
		String location = Utils.leerCadena("Insert hospital's location: ");
		Madrid.addHospital(id, name, capacity, location);
	}
	
	public static void deleteHospital() {
		try{
            Madrid.showHospitals();
            int aux = Utils.leerEntero("Choose the hospital you want to delete: ");
            Madrid.deleteHospital(aux);
        }catch(IndexOutOfBoundsException ex){
            System.out.println(ex);
        }
	}
	
	public static void addExistingSpecialityToHospital() { //cambiar esto
		try{
			//sacar el id de db
			Madrid.showHospitals();
            int aux = Utils.leerEntero("Choose the hospital you want to add the speciality: "); 
            if(Madrid.getHospitals().get(aux-1) != null){
                String name = Utils.leerCadena("Insert speciality's name: ");            
                Speciality sp = new Speciality(id, name);
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
	
	public static int menuAdministrador(){
        
        int opcion = 0;
        
        do{
            System.out.println("");
            System.out.println("Menu");
            System.out.println("1. Add Hospital");
            System.out.println("2. Delete Hospital");
            System.out.println("3. Add new Speciality");
            System.out.println("3. Add existing Speciality to a Hospital");
            System.out.println("4. Delete Speciality");

            opcion = Utils.leerEntero("Introduzca una opcion (0 para salir): ");
            
        }while(opcion > 5 || opcion < 0);
    
        return opcion;    
    }    
}
