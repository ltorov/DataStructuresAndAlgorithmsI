// @author GregorioPerezBernal & LuisaToroVillegas
import java.util.*;
public class TecladoRoto{
    public LinkedList <Character> lista = new LinkedList <>();
    public void Teclado (String texto) {
        int contador = 0;
        boolean bool = false;
        for (int i = 0; i<texto.length(); i++){
            char charac = texto.charAt(i);
            if (charac == '[' && i< texto.length () -1){
                bool = true;
                i++;
                contador = 0;
            }
            if (charac == ']' && i< texto.length()-1){
                bool = false;
                i++;
            }
            if (bool) {
                lista.add(contador, texto.charAt(i));
                contador ++;
            } else {
                lista.add(texto.charAt(i));
            }
        }
        LinkedList <Character> nuevalista = new LinkedList<>();
        int j = 0;
        while (lista.size()>0){
            char charac = lista.remove (j);
            if (charac != '[' && charac != ']') nuevalista.add(charac);
        }
        for (char charac: nuevalista) {
            System.out.print (charac);
        }
    }
}
