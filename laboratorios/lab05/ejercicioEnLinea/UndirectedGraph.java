/**
 * @GregorioPérezBernal & LuisaToroVillegas
 * @May 2nd, 2020
 */
import java.io.*; 
import java.util.*;
public class UndirectedGraph
{
    public static List <List <Node>> adjList = new ArrayList<>();
    public UndirectedGraph (Connections [] connections, int nodes) {
        for (int i = 0; i< nodes; i++)
            adjList.add(i, new ArrayList<>());

        for (int i = 0; i< connections.length; i++){
            int a = connections [i].Out;
            int b = connections [i].In;
            adjList.get(a).add(new Node(b));
            adjList.get(b).add(new Node(a));
        }
    }
    
    public static boolean areConnected (Node a, Node b) {
        for (int i = 0; i < adjList.size(); i++){
            if (adjList.get(i) == a){
                for (int j = 0; j< adjList.get(i).size(); j++)
                    if (adjList.get(i).get(j) == b)
                        return true;
            }
        }
        return false;
    }

    public static LinkedList getSuccesors (Node a) {
        LinkedList<Node> s = new LinkedList <Node>();
        for (int i = 0; i < adjList.size(); i++){
            if (adjList.get(i) == a){
                for (int j = 0; j< adjList.get(i).size(); j++)
                    s.add(adjList.get(i).get(j));
            }
        }
        return s;
    }
}
