/**
 * @GregorioPérezBernal & LuisaToroVillegas
 * @May 2nd, 2020
 */
import java.io.*;
import javafx.util.Pair; 
import java.util.*;
public class GraphCity
{
    public List <List <Vertex>> adjMatrix = new ArrayList<>();

    public GraphCity (List <Edge> edges){
        for (int i =0; i< edges.size(); i++)
            adjMatrix.add(i, new ArrayList<>());

        for (Edge i: edges){
            int a = (int) i.IDSalida;
            adjMatrix.get(a).add(new Vertex(i.IDLlegada,i.weight));
        }
    }

    public void addEdge (List <Edge> edges, Edge edge) {
        edges.add(edge);
    }

    public void addVertex (List <Vertex> vertices, Vertex vertex){
        vertices.add(vertex);
    }

    public boolean areConnected (Vertex a, Vertex b) {
        for (int i = 0; i < adjMatrix.size(); i++){
            if (adjMatrix.get(i) == a){
                for (int j = 0; j< adjMatrix.get(i).size(); j++)
                    if (adjMatrix.get(i).get(j) == b)
                        return true;
            }
        }
        return false;
    }

    public double getWeight (Vertex a, Vertex b){
        if (areConnected(a,b)){
            for (int i = 0; i < adjMatrix.size(); i++){
                if (adjMatrix.get(i) == a){
                    for (int j = 0; j< adjMatrix.get(i).size(); j++)
                        if (adjMatrix.get(i).get(j) == b)
                            return adjMatrix.get(i).get(j).weight;
                }
            } 
        }
        return -1;
    }

    public LinkedList getSuccesors (Vertex a) {
        LinkedList<Vertex> s = new LinkedList <Vertex>();
        for (int i = 0; i < adjMatrix.size(); i++){
            if (adjMatrix.get(i) == a){
                for (int j = 0; j< adjMatrix.get(i).size(); j++)
                    s.add(adjMatrix.get(i).get(j));
            }
        }
        return s;
    }
}
