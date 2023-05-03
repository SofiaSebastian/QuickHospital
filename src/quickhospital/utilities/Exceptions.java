package quickhospital.utilities;

public class Exceptions {
	
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
}
