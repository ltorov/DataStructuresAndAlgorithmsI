import java.util.*;public class Punto1_3
{
    public static void Neveras(Queue<String> Almacen, Queue<String> Ordenes){
       while(Almacen.poll()!=null  && Ordenes.poll()!= null){
         String orden = Ordenes.poll();
         int numneveras = Integer.parseInt(orden.substring(1,2));
         for(int i = 0; i<numneveras;i++){
             System.out.println(orden + Almacen.poll());
            }        
        }
    }
}
