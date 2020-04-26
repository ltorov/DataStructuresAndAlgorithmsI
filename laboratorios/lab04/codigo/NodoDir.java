
/**
 * @Gregorio Pérez Bernal & Luisa Toro Villegas
 * @abril 26,2020
 */
import java.util.*;
import java.io.*;
public class NodoDir
{
    public String autor;
    public String tamaño;
    public String nombre;
    public ArrayList <NodoDir> hijos;
    
    public NodoDir(String a, String t, String n){
        autor = a;
        tamaño = t;
        nombre = n;
    }
    
    public ArrayList Hijos (){
    hijos = new ArrayList <NodoDir>();
    return hijos;
    }
}
