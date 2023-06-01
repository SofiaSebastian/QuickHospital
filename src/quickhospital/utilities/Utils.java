package quickhospital.utilities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
	
    public static int leerEntero (String string){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int intLeido;
        String stringLeida;
        System.out.println(string);
        while (true){
            try{
                stringLeida = br.readLine();
                intLeido = Integer.parseInt(stringLeida);
                return intLeido;
            }catch (IOException ioe){
                System.out.println("Error while reading ");
            }catch (NumberFormatException nfe){
                System.out.println("Not an int");
            }
        }
    }
    
    public static String leerCadena(String string){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String stringLeida;
        System.out.println(string);
        while (true){
            try{
                stringLeida = br.readLine();
                return stringLeida;
            }catch (IOException ioe){
                System.out.println("Error al leer de teclado");
            }
        }
    }
    
    public static LocalDate leerFecha(String string){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String stringLeida;
        System.out.println(string);
        while (true){
            try{                
                stringLeida = br.readLine();
                LocalDate fecha = LocalDate.parse(stringLeida, formatter);
                return fecha;
            }catch (IOException | java.time.format.DateTimeParseException e){
                System.out.println("La fecha introducida no es correcta");
            }
        }    
    }
    
    public static int readSymptoms (String string){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int intLeido;
        String stringLeida;
        System.out.println(string);
        while (true){
            try{
                stringLeida = br.readLine();
                intLeido = Integer.parseInt(stringLeida);
                return intLeido;
            }catch (IOException ioe){
                System.out.println("Error while reading ");
            }catch (NumberFormatException nfe){
                System.out.println("Not an int");
            }
        }
    }
}
