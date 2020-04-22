
/**
 * @GregorioPérezBernal & LuisaToroVillegas
 * @version (April 21st, 2020)
 */

import java.util.*;

public class GraphswithMatrix
{
    public int MatrixAdj[][];
    public int numVertices;
    
    
    
    public GraphswithMatrix (int a) {
        numVertices = a;
        MatrixAdj = new int [numVertices][numVertices];
    }

    public void addEdge (int a, int b, int weight){
        MatrixAdj [a][b] = weight;
    }

    public void removeEdge (int a, int b){
        MatrixAdj [a][b] = 0;
    }

    public boolean areConnected (int a, int b){
        if (MatrixAdj [a][b] !=0)
            return true;
        else
            return false;
    }

    public int getWeight (int a, int b){
        if (areConnected (a,b))
            return MatrixAdj [a][b];
        else 
            return 0;
    }

    public LinkedList<Integer> getSuccessors(int vertex){
        LinkedList<Integer> s = new LinkedList <Integer>();
        for (int i = 0; i< numVertices; i++){
            if (MatrixAdj [vertex][i] !=0)
                s.add(i);
        }
        return s;
    }
}