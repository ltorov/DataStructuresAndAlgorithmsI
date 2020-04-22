/**
 * @GregorioPérezBernal & LuisaToroVillegas
 * @version (April 21st, 2020)
 */
import java.io.*;
import javafx.util.Pair; 
import java.util.*;
public class GraphsWithLinkedLists
{
    public LinkedList <Pair <Integer, Integer>> [] grafo;
    public int numVertices;

    public GraphsWithLinkedLists (int a) {
        numVertices = a;
        grafo = new LinkedList [numVertices];
    }

    public void addEdge (int a, int b, int weight){
        grafo[a].add(new Pair(b,weight)) ; 
    }

    public boolean areConnected (int a, int b){
        for (int i = 0; i<= grafo[a].size(); i++){
            int c = grafo[a].get(i).getKey();
            if (c==b)
                return true;
        }
        return false;
    }

    public int getWeight (int a, int b){
        for (int i = 0; i<= grafo[a].size(); i++){
            int c = grafo[a].get(i).getKey();
            if (c==b)
                return grafo[a].get(i).getValue();
        }
        return 0;
    }
    
    public LinkedList<Integer> getSuccessors(int vertex){
        LinkedList<Integer> s = new LinkedList <Integer>();
        for (int i = 0; i< numVertices; i++){
            if (getWeight(vertex, i) !=0)
                s.add(i);
        }
        return s;
    }
}
