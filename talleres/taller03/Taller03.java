import javax.crypto.*;
import java.nio.*;
import java.util.*;
public class Taller03 {
    public static void torres(int n, int origen, int intermedio, int destino) {
        if (n==1){
            System.out.println ("Disco 1 pasa de la torre " + origen + " a la torre " + destino);

        }
        else{
            torres(n-1, origen, destino, intermedio);
            System.out.println ("Disco " + n + " pasa de la torre " + origen + " a la torre " + destino);
            torres(n-1, intermedio, origen, destino);
        }
    }

    public static void perm (String s) {
        perm ("", s);
    }

    public static void perm(String base, String s){
        int a = s.length ();
        if (a == 0) 
            System.out.println (base);        
        else {
            for(int i = 0; i < a ;i++){
                perm ( base + s.charAt(i), s.substring (0,i)+s.substring (i+1));
            }
        }
    }
}
