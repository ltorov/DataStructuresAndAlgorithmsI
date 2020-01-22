public class Contador
{
    private int x;
    public Contador(int x)
    {
      this.x=x;
    }
    public int GetX(){
      return x;
    }
    public int acum(int numV){
      for(int i=0; i<numV; i++){
        x++;
        }
        return x;
    }
}
