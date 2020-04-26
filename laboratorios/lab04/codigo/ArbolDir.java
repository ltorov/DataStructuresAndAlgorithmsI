
/**
 *  @Gregorio Pérez Bernal & Luisa Toro Villegas
 * @abril 26,2020
 */
import java.util.*;
import java.io.*;
public class ArbolDir
{
    public NodoDir root;

    private boolean buscarNombreAux(NodoDir node, String n) {
        if(node == null || node.hijos == null){
            return false;
        }
        if(node.nombre == n){
            return true;
        }
        for (int i = 0; i< node.hijos.size(); i++){
            return buscarNombreAux(node.hijos.get(i), n );
        }
        return false;
    }

    private boolean buscarTamañoAux(NodoDir node, String n) {
        if(node == null || node.hijos == null){
            return false;
        }
        if(node.tamaño == n){
            return true;
        }
        for (int i = 0; i< node.hijos.size(); i++){
            return buscarTamañoAux(node.hijos.get(i), n );
        }
        return false;
    }

    private boolean buscarAutorAux(NodoDir node, String n) {
        if(node == null || node.hijos == null){
            return false;
        }
        if(node.autor == n){
            return true;
        }
        for (int i = 0; i< node.hijos.size(); i++){
            return buscarAutorAux(node.hijos.get(i), n );
        }
        return false;
    }

    public void buscarNombre(NodoDir node, String n) {
        if (buscarNombreAux(root,n)){
            if (root.nombre == n)
                System.out.println( root.nombre);
            if (root.hijos != null){
                for (int i = 0; i< root.hijos.size(); i++){
                    buscarNombre(root.hijos.get(i), n);
                }
            }
        }
        else{
            System.out.println("No such file or directory.");
        }
    }

    public void buscarTamaño(NodoDir node, String n) {
        if (buscarTamañoAux(root,n)){
            if (root.tamaño == n)
                System.out.println(root.tamaño);
            if (root.hijos != null){
                for (int i = 0; i< root.hijos.size(); i++){
                    buscarTamaño(root.hijos.get(i), n);
                }
            }
        }
        else{
            System.out.println( "No such file or directory.");
        }
    }

    public void buscarAutor(NodoDir node, String n) {
        if (buscarAutorAux(root,n)){
            if (root.autor == n)
                System.out.println( root.autor);
            if (root.hijos != null){
                for (int i = 0; i< root.hijos.size(); i++){
                    buscarAutor(root.hijos.get(i), n);
                }
            }
        }
        else{
            System.out.println( "No such file or directory.");
        }
    }

    public void insertarRaiz (String linea){
        String a1 = linea.substring ( linea.indexOf ("[")+1);
        String a = a1.substring(0, a1.indexOf(" "));
        
        String t = a1.substring(a1.indexOf(" ")+1, a1.indexOf("]"));
        
        String n1 = linea.substring (linea.indexOf("]"));
        String n = n1.substring (n1.indexOf(" "));
        
        root = new NodoDir(a,t,n);
    }

    public void insertarHijos (String linea, NodoDir papa){
        String a1 = linea.substring ( linea.indexOf ("[")+1);
        String a = a1.substring(0, a1.indexOf(" "));
        
        String t = a1.substring(a1.indexOf(" ")+1, a1.indexOf("]"));
        
        String n1 = linea.substring (linea.indexOf("]"));
        String n = n1.substring (n1.indexOf(" "));
        
        NodoDir estenodo = new NodoDir (a,t,n);
        papa.hijos.add(estenodo);
    }
}