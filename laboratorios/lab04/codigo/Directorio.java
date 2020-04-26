/**
 * @Gregorio Pérez Bernal & Luisa Toro Villegas
 * @abril 26,2020
 */
import java.util.*;
import java.io.*;
public class Directorio
{
    public ArbolDir directorio = new ArbolDir ();

    public void lecturaDatos(){
        try{
            File archivo = new File("ejemplito.txt");
            Scanner scan = new Scanner(archivo);
            Queue <String> listatemp = new LinkedList <>();
            while (scan.hasNextLine()) {
                listatemp.add(scan.nextLine());
            }
            scan.close();
            directorio.insertarRaiz(listatemp.poll());

            Queue <String> listatemptemp1 = new LinkedList<>();
            listatemptemp1 = listatemp;
            for (int i = 0; i< listatemp.size(); i++){
                String a = listatemptemp1.peek().substring(0,4);
                String b = null;
                if ((a == "├──") || (a == "└──"))
                    directorio.insertarHijos(listatemptemp1.poll(), directorio.root);
                else
                    b = listatemptemp1.poll();
            }
            
            Queue <String> listatemptemp2 = new LinkedList<>();
            listatemptemp2 = listatemp;
            for (int i = 0; i< listatemp.size(); i++){
                String a = listatemptemp2.peek().substring(0,6);
                String c = listatemptemp2.peek().substring(0,4);
                String b = null;
                if ((a == "│ ├──") || (a == "│ └── "))
                    directorio.insertarHijos(listatemptemp2.poll(), directorio.root.hijos.get(i));
                else
                    b = listatemptemp2.poll();
            }
        } catch (FileNotFoundException e){
        }
    }

    public void consultaArchivos() {}

    public void consultaArchivosYTamaño (){}

    public void consultaArchivosYAutor () {}

}
