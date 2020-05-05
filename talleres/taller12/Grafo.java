/**
 * @GregorioPérezBernal & LuisaToroVillegas
 * @May 5th, 2020
 */
import java.io.*;
import javafx.util.Pair; 
import java.util.*;
public class Grafo
{
    public static List <List <Integer>> adjList = new ArrayList<>();

    public Grafo (List <Arista> edges){
        for (int i =0; i< edges.size(); i++)
            adjList.add(i, new ArrayList<>());

        for (Arista i: edges){
            int a = (int) i.Out;
            adjList.get(a).add(i.In);
        }
    }

    public void addEdge (List <Edge> edges, Edge edge) {
        edges.add(edge);
    }

    public void addVertex (List <Integer> vertices, int vertex){
        vertices.add(vertex);
    }

    public static boolean areConnected (int a, int b) {
        for (Integer s : adjList.get(a)){
            if (adjList.get(a).get(s) == b)
                return true;
        }
        
        return false;
    }

    public static LinkedList getSuccesors (int a) {
        LinkedList<Integer> s = new LinkedList <Integer>();
        
        for (Integer i : adjList.get(a)){
            s.add(adjList.get(a).get(i));
        }
        return s;
    }
}
