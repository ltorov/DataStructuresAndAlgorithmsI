import java.util.*;
import java.util.LinkedList;
import javafx.util.Pair;

public class UnaTablaDeHash
{
    public LinkedList <Pair<String,Integer>>[] tabla = new LinkedList [10];

    public int funcionHash(String k){
        return ((int) k.charAt(0)) % 10;
    }

    public void put(String k, int v){
        Pair <String, Integer> value = new Pair <String, Integer> (k, v);
        tabla[funcionHash(k)].add(value);
    }

    public int get(String k) throws Exception{
        int a = -1;
        for (int i = 0; i< (tabla[funcionHash(k)]).size(); i++) {
            Pair<String, Integer> parejaenI = tabla[funcionHash(k)].get(i);
            if (parejaenI.getKey()==k)
                a = parejaenI.getValue();
        }
        if (a == -1) 
            throw new Exception("The String inputted isn't in the hashmap");
        else 
            return a;
    } 
}


