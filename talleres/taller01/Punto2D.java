import java.util.*;
public class Punto2D
{
    private int x,y;
    public Punto2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int GetX(){
      return x;
    }
    public int GetY(){
      return y;
    }
    public double Radio (){
      return Math.sqrt(x*x + y*y);
    }
    public double Angulo(){
      double a = y;
      double b = x;
      return Math.toDegrees(Math.atan(a/b));
    }
    public double Dist2ptos(int x1, int y1){
      int a = x - x1;
      int b = y - y1;
      
      return Math.sqrt(a*a + b*b);
    }
}
