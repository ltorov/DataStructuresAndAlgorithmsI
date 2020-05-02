/**
 * @author GregorioPerezBernal & LuisaToroVillegas
 * @May 2nd, 2020
 */
import java.util.*;
import java.io.*;
public class Edge
{
    public float IDSalida;
    public float IDLlegada;
    public double weight;
    public String name;
    public Edge(float is, float il, double w, String n){
        IDSalida = is;
        IDLlegada = il;
        weight = w;
        name = n;
    }

    public static Edge StringToEdge (String a) {
        String [] str1 = a.split(" ",4);
        String [] str2 = new String [4];
        str2 [0] = str1 [0];
        if (str1.length <4)
            str2 [3]= "desconocido";
        else 
            str2 [3] = str1[3];

        float ID1 = -1;
        if (str2 [0] != null && str2[0].length() > 0) {
            try {
                ID1 = Float.parseFloat(str2[0]);
            } catch(Exception e) {
            }
        }
        
        float ID2 = -1;
        if (str2 [1] != null && str2[1].length() > 0) {
            try {
                ID2 = Float.parseFloat(str2[1]);
            } catch(Exception e) {
            }
        }
        
        double weight = -1;
        if (str2 [2] != null && str2[2].length() > 0) {
            try {
                weight = Double.parseDouble(str2[2]);
            } catch(Exception e) {
            }
        }
        
        Edge edge = new Edge (ID1, ID2, weight, str2[1]);
        return edge;

    }
}