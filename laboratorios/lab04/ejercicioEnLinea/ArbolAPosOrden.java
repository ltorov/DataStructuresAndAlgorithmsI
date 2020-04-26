
/**
 * @Gregorio Pérez Bernal & Luisa Toro Villegas
 * @abril 26,2020
 */
import java.util.*;
import java.io.*;

public class ArbolAPosOrden
{
    public static void main (String args []){
            ArbolBin arbol = new ArbolBin ();
            System.out.println("Insertar nodos uno a uno");
            System.out.println("Cuando desee acabar, inserte -1");
            int b = 0;
            while (b != -1) {
            Scanner scan = new Scanner(System.in);
            int a = scan.nextInt();
            b = a;
            arbol.insertar(a);
            }
            
            posOrden(arbol.root);
        
    }

    public static void posOrden (Nodo nodo){
        if (nodo!=null){
            posOrden (nodo.left);
            posOrden(nodo.right);
            System.out.println(nodo.data);
        }
    }
}
