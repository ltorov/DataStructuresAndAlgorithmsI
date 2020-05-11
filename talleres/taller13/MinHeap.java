/**
 * Taller 13. Implemrntación de un montículo de menor a mayor.
 * Basado en https://www.youtube.com/watch?v=t0Cq6tVNRBA&t=185s
 *
 * @Gregorio Pérez Bernal & Luisa Toro Villegas
 * @Mayo 11, 2020
 */

import java.util.*;
import java.io.*;
public class MinHeap
{
    //vamos a usar un arreglo dinámico.
    public int capacidad = 10;
    public int size = 0;
    public int [] heap = new int [capacidad];

    //metodos de los heaps
    public int peek (){
        if (size == 0) throw new IllegalStateException();
        return heap [0];
    }
    
    public int poll (){
        if (size == 0) throw new IllegalStateException();
        int elem = heap[0];
        heap [0] = heap [size-1];
        size --;
        ajustarAbajo();
        return elem;
    }
    
    public void add (int a) {
        arraylistCapacidad();
        heap [size] = a;
        size++;
        ajustarArriba();
    }
    //metodos auxiliares
    public int getHijoIzquierdoIndice (int indicePadre){
        return 2 * indicePadre + 2;
    }

    public int getHijoDerechoIndice (int indicePadre){
        return 2 * indicePadre + 2;
    }

    public int getPadreIndice (int indiceHijo){
        return (indiceHijo-1)/2;
    }

    public boolean existeHijoIzquierdo (int indicePadre) {
        return (getHijoIzquierdoIndice (indicePadre) < size);
    }

    public boolean existeHijoDerecho (int indicePadre) {
        return (getHijoDerechoIndice (indicePadre) < size);
    }

    public boolean existePadre (int indiceHijo) {
        return (getPadreIndice (indiceHijo) >= 0);
    }

    public int getHijoIzquierdo (int indicePadre) {
        if (existeHijoIzquierdo(indicePadre))
            return heap [getHijoIzquierdoIndice(indicePadre)];
        return -1;
    }

    public int getHijoDerecho (int indicePadre) {
        if (existeHijoDerecho(indicePadre))
            return heap [getHijoDerechoIndice(indicePadre)];
        return -1;
    }

    public int getPadre (int indiceHijo) {
        if (existePadre(indiceHijo))
            return heap [getPadreIndice(indiceHijo)];
        return -1;
    }

    public void arraylistCapacidad () {
        if (size == capacidad) {
            heap = Arrays.copyOf(heap, capacidad*2);
            capacidad *=2;
        }
    }

    public void intercambiar (int i1, int i2) {
        int a = heap [i1];
        heap [i1] = heap[i2];
        heap [i2] = a;
    }
    
    public void ajustarAbajo(){
        int i = 0;
        while(existeHijoIzquierdo(i)){
            int iHijoMenor = getHijoIzquierdoIndice(i);
            if (existeHijoDerecho(i)&& getHijoDerechoIndice(i) < getHijoIzquierdoIndice(i))
                iHijoMenor = getHijoDerechoIndice (i);
            if (heap[i]< heap[iHijoMenor]) break;
            else {
                intercambiar (i, iHijoMenor);
            }
            i = iHijoMenor;
        }
    }
    
    public void ajustarArriba(){
      int i = size -1;
      while (existePadre(i) && getPadre(i)> heap[i]){
          intercambiar(getPadreIndice (i), i);
          i = getPadreIndice(i);
      }
    }
}
