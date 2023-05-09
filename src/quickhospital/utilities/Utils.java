package quickhospital.utilities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
	
	public static boolean esInt(String s) {// para comprobar si lo que nos pasan por teclado es un int
        boolean isInt = false;
        try {
            int i = Integer.parseInt(s);
            isInt = true;
        } catch (NumberFormatException ex) {
            isInt = false;
        }
        return isInt;
    }

    public static int pasarInt(String s) {//pasar de string a int si previamente se ha comprobado que lo leido por teclado es un int
        int num = 0;
        try {
            num = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            System.out.println("No es int, tendrias que haberlo comprobado antes se te ha olvidado!");
        }
        return num;
    }
    
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
