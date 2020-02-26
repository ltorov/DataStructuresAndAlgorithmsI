
/**
 *La complejidad de agregar n elementos es de n^2.
 */
public class MyArrayList
{
    public int size;
    public int capacity = 10;
    public int elements[]; 
    public MyArrayList () {
        size = 0;
        elements = new int [capacity];
    }

    public int get(int index) throws Exception{
        if (index>= size) throw new Exception("Index out of bound exception index = " + index);
        return elements [index];
    }

    public void set (int index, int a) throws Exception{
        if (index > size) throw new Exception("Index out of bound exception index = " + index);
        if (index<elements.length){
            elements [index] = a;
            if (index>=size) size ++;
        } else {
            int[] elementstemp = new int [elements.length*2];
            for (int i =0; i< elements.length ; i++){
                elementstemp [i] = elements[i];
            }
            elementstemp [index] = a;
            size++;
            elements = new int [elements.length*2];
            for (int i = 0; i<elements.length* 2 ; i++){
                elements[i] = elementstemp [i];
            }

        }
    }

    public void add (int a){
        if (size<elements.length){
            elements [size] = a;
            size ++;
        } else {
            int[] elementstemp = new int [elements.length*2];
            for (int i =0; i< elements.length; i++){
                elementstemp [i] = elements[i];
            }
            elementstemp [size] = a;
            size++;
            elements = new int [elements.length*2];
            for (int i = 0; i<elements.length * 2 ; i++){
                elements[i] = elementstemp [i];
            }
        }
    }

    public int size (){
        return size;
    }

    public void remove (int index) throws Exception{
        if (index>=size) throw new Exception ("Index out of bound exception index = " + index);
        for (int i = index; i <size; i++){
            elements [i]  = elements [i+1];
        }
        size = size - 1;
    }

    public void add (int index, int a) throws Exception{
        if (index > size+1) throw new Exception ("Index out of bound exception index = " + index);
        if (index < elements.length){
            size++;
            for (int i = size; i>=index; i--){
                elements [i+1] = elements [i];
            }
            elements [index] = a;
        } else {
            int[] elementstemp = new int [elements.length*2];
            for (int i = 0; i< index; i++){
                elementstemp [i] = elements[i];
            }
            for (int i = index; i<elements.length; i++){
                elementstemp [i+1] = elements [i];
            }
            elementstemp [index] = a;
            size++;
            elements = new int [elements.length];
            for (int i = 0; i<elements.length ; i++){
                elements[i] = elementstemp [i];
            }
        }
    }

    public boolean contains (int a ) {
        boolean b = false;
        for (int i = 0; i<size; i++) {
            if (elements[i] == a) {
                b = true;
                break;
            } 
        }
        return b;
    }
}
