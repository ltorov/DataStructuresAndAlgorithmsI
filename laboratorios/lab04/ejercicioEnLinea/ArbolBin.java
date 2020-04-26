/**
 * Write a description of class ArbolBin here.
 *
 * @Gregorio Perez Bernal and Luisa Toro Villegas
 * @version (a version number or a date)
 */
public class ArbolBin
{
    public Nodo root;

    private boolean buscarAux(Nodo node, int n) {
        if(node == null){
            return false;
        }
        if(node.data == n){
            return true;
        }
        if(n < node.data){
            return buscarAux(node.left,n); 
        }
        return buscarAux(node.right,n);

    }

    public boolean buscar(int n) {
        return buscarAux(root, n);
    }

    public void insertar (int n){
        if (root== null) root= new Nodo(n);
        else insertar (root, n);
    }
   
    public void insertar (Nodo nodo, int n){
        if (nodo.data == n) return;
        if (n < nodo.data){
            if (nodo.left != null) insertar(nodo.left, n);
            else nodo.left= new Nodo(n);
        }
        if (n > nodo.data){
            if (nodo.right != null) insertar(nodo.right, n);
            else nodo.right= new Nodo(n);
        }

    }
}