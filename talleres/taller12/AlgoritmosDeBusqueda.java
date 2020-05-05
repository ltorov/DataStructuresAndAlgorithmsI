/**
 * @GregorioPérezBernal & LuisaToroVillegas
 * @May 5th, 2020
 */

import java.io.*;
import javafx.util.Pair; 
import java.util.*;

public class AlgoritmosDeBusqueda
{
    public static Grafo grafo;

    public static boolean DFS (Grafo grafo, int a, int b) {
        boolean []visitados = new boolean [grafo.adjList.size()];

        return DFSAux (grafo, a, b, visitados);
    }

    public static boolean DFSAux (Grafo grafo, int a, int b, boolean [] visitados){
        visitados [a] = true;
        if (a == b) return true;
        else {
            for (Integer s : grafo.adjList.get(a)){
                if (!visitados[s]) 
                    if (DFSAux(grafo, s, b, visitados)) return true;
            }
        }
        return false;
    }

    public static boolean BDS (Grafo grafo, int a, int b) {
        boolean visitados [] = new boolean [grafo.adjList.size()];
        LinkedList <Integer> queue = new LinkedList <Integer> ();
        visitados[a] = true;
        queue.add(a);
        Iterator<Integer> i; 
        while (queue.size()!=0) {
            a = queue.poll();
            int j;
            i = grafo.adjList.get(a).listIterator ();
            while (i.hasNext()) {
                j = i.next();
                if (j == b)return true;
                if (!visitados[j]){
                    visitados [j] = true;
                    queue.add(j);
                }
            }
        }
        return false;
    }
}