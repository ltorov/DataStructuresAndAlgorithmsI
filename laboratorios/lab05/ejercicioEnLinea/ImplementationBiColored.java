/**
 * @GregorioPérezBernal & LuisaToroVillegas
 * @May 2nd, 2020
 */
import java.io.*; 
import java.util.*;
public class ImplementationBiColored
{
    public static Connections [] connections;
    public static Node [] nodes;
    public static boolean [] explo;
    public static boolean [] colour;
    public static UndirectedGraph graph;

    public static void main (String [] args) {
        LecturaDeDatos();

        graph = new UndirectedGraph (connections, nodes.length);
        
        for (int i = 0; i< graph.adjList.get(nodes[0].number).size(); i++){
            System.out.println(graph.adjList.get(nodes[0].number).get(i).number);
        }

        boolean a = DFSearch(graph, nodes[0], explo, colour);

        if (a) 
            System.out.println("BICOLORABLE");
        else
            System.out.println("NOT BICOLORABLE");
    }

    public static void LecturaDeDatos () {
        int breakk = 1;
        int [] nodesAux;
        int numNodes = 0;

        while (breakk != 0) {
            Scanner scan = new Scanner (System.in);
            numNodes = scan.nextInt();
            Scanner scan1 = new Scanner (System.in);
            int numConnections = scan1.nextInt ();
            connections = new Connections [numConnections];

            for (int i = 0; i < numConnections; i++) {
                Scanner scan2 = new Scanner(System.in);
                String a = scan2.nextLine();
                int out = Character.getNumericValue(a.charAt(0));
                int in = Character.getNumericValue(a.charAt(1));
                Connections connection = new Connections (out,in);
                connections [i] = connection;
            }

            Scanner scan3 = new Scanner(System.in);
            breakk = scan3.nextInt();
        }
        nodes = new Node [numNodes];

        for (int i = 0; i< numNodes; i++){
            Node nodo = new Node (0);
            nodes [i] = nodo;
        }

        nodesAux = new int [connections.length*2];

        for (int i = 0; i< connections.length; i++){
            nodesAux[i] = connections[i].Out;
            nodesAux[connections.length+i] = connections[i].In;
        }

        nodes[0].number = nodesAux[0];

        for (int i = 1; i<nodes.length; i++){
            for (int j = 0; j < i; j++)
                if (nodesAux[i] != nodes [j].number) nodes[i].number = nodesAux[i];
        }
        explo = new boolean [nodes.length];
        colour = new boolean [nodes.length];
        for (int i = 0; i < nodes.length ; i++) {
            explo [i] = false;
            colour [i] = false;
        }
        explo [0] = true;
    }

    public static boolean DFSearch (UndirectedGraph graph, Node node, boolean [] exploration, boolean [] color) {
            if (graph.adjList.contains(node.number)){
                System.out.println("hola");
            for (Node nodo: graph.adjList.get(node.number)){
                
                
                if(!exploration[nodo.number]){
                    exploration[nodo.number] = true;
                    color[nodo.number] = !color [node.number];
                    if (DFSearch(graph, nodo, exploration, color) == false) return false;

                }
                else if (color[node.number] == color [nodo.number]) return false;
            }
        
        }
        return true;
    }
}
