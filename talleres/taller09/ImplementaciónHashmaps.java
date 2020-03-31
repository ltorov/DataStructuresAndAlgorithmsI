// @author GregorioPerezBernal & LuisaToroVillegas
import java.util.*;
public class ImplementaciónHashmaps
{
    public static HashMap<String, String> Empresas = new HashMap<String, String>();

    public static void main (String [] args) {
        Asignar("Google", "Estados Unidos");
        Asignar("La locura", "Colombia");
        Asignar("Nokia", "Finlandia");
        Asignar("Sony", "Japón");
        ObtenerPais("Google");
        ObtenerPais("Motorola"); //Como no está asignado, manda un mensaje de error y retorna nulo.
    }

    public static void Asignar (String empresa, String pais) {
        Empresas.put(empresa,pais);
    }

    public static String ObtenerPais (String empresa){
        if (Empresas.get(empresa)==null) 
            System.out.print("La empresa no tiene un pais asignado");
        return Empresas.get(empresa);
    }
}
