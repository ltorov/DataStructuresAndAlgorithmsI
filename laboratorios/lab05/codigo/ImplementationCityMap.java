/**
 * @author GregorioPerezBernal & LuisaToroVillegas
 * @May 2nd, 2020
 */
import java.util.*;
import java.io.*;
public class ImplementationCityMap
{
    public List <Edge> edges = new ArrayList <>();
    public List <Vertex> vertices = new ArrayList <>();
    public GraphCity mapa = new GraphCity (edges);
    public void LecturaDatos () throws Exception{
        try {
            String thisLine = null;

            FileReader fr = new FileReader("medellin_colombia-grande.txt");    
            BufferedReader br = new BufferedReader(fr);
            if (!(thisLine = br.readLine()).contains("Vertice")){
                while (!(thisLine = br.readLine ()).equals("")){
                    vertices.add (Vertex.StringToVertex(thisLine));
                }
            }

            if (!(thisLine = br.readLine()).contains("Arcos . ")){
                while (((thisLine = br.readLine ()) != null)||!(thisLine = br.readLine ()).equals("")){
                    edges.add(Edge.StringToEdge(thisLine));
                }
            }
            br.close();    
            fr.close();    
        }
        catch (FileNotFoundException e){
        }
    }
}