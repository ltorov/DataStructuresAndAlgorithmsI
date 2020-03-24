import java.util.*;
/**
 * Write a description of class Taller8 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Taller8
{
    public static int polaca  (String string){
        String[] prefixStr = string.split(" "); 
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0;i<prefixStr.length;i++){
            if(prefixStr[i].equals("+")){
                stack.push(stack.pop()+ stack.pop() );
            }
            else if(prefixStr[i].equals("*")){
                stack.push(stack.pop()*stack.pop());
            }
            else if(prefixStr[i].equals("-")){
                stack.push( stack.pop() -stack.pop());
            }
            else if(prefixStr[i].equals("/")){
                stack.push(stack.pop() / stack.pop());
            }
            else{
                stack.push(Integer.parseInt(prefixStr[i]));
            }
        }
        return stack.pop();
    }
    public static void Neveras(Queue<String> Almacen, Queue<String> Ordenes){
       while(Almacen.poll()!=null  && Ordenes.poll()!= null){
         String orden = Ordenes.poll();
         int numneveras = Integer.parseInt(orden.substring(1,2));
         for(int i = 0; i<numneveras;i++){
             System.out.println(orden + Almacen.poll());
            }        
        }
    }
  

    public Stack Invertir (Stack <Integer> s1){
        Stack <Integer> s2 = new Stack <> ();
        if (s2.isEmpty ()){
            while (!s1.isEmpty())
                s2.push(s1.pop());
        }
        return s2;
    }

}
