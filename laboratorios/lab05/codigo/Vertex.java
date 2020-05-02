/**
 * @author GregorioPerezBernal & LuisaToroVillegas
 * @May 2nd, 2020
 */
public class Vertex
{
    public float ID;
    public double weight;
    public String name;
    public Vertex(float i, String n){
        ID = i;
        name = n;
    }
    public Vertex (float i, double w) {
        ID = i;
        weight = w;
    }
    public static Vertex StringToVertex (String a) {
        String [] str1 = a.split(" ", 4);
        String [] str2 = new String [2];
        str2 [0] = str1 [0];
        if (str1.length <4)
        str2 [1]= "desconocido";
        else 
        str2 [1] = str1[3];
        Vertex vertex = new Vertex (Float.valueOf(str2 [0]),str2[1]);
        return vertex;
    }
}
