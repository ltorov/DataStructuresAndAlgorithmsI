/**
 * This class contains the code for the exercices corresponding to lab 1 in the course Estructuras de datos y algoritmos I en Universidad EAFIT.
 * @Gregorio Pérez Bernal
 * @Luisa Toro Villegas
 * @February 2020
 */
import java.lang.*;
import java.util.*;
public class Laboratory01
{
    public static void main (String [] args){
        System.out.println ("Enter the size of the container (n)");
        Scanner scan = new Scanner (System.in);
        int a = scan.nextInt();
        for (int i = 0; i<=a; i++){
            long inicio = System.currentTimeMillis();
            Rect(i);
            long finall = System.currentTimeMillis();
            System.out.println (i + " " + (finall- inicio));
        }
    }

    public static int Sequence(String a, String b){
        if (a.length()==0 || b.length()==0) return 0;
        for (int i=0; i<a.length(); i++){
            if (a.length()==0 || b.length()==0) return 0;
            if (a.charAt(i) == b.charAt(0)) return 1 + Sequence(a, b.substring(1));
        }
        return Math.max(Sequence(a, b.substring(1)),Sequence(a.substring(1), b));
    }
    // We noticed that the pattern that the 2x1 rectangles organized in the 2xn box follows the same sequence that Fibonacci.
    public static int Rect (int n) {
        if (n<=2) return n;
        return Rect(n-1) + Rect (n-2);
    }

}