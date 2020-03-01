/**
 * This class contains the excercises done for codingbat Array 2 and Array 3.
 *
 * Gregorio Pérez Bernal 
 * Luisa Toro Villegas
 * 
 * February 2020
 * 
 * It is worth mentioning that "https://github.com/ozelentok/CodingBat-Solutions/blob/master/Java/Array-3.java" was used as a reference to understand some of the excercices
 */
public class Ejercicios_en_Linea
{
    // countEvens (Array2) 
    public int countEvens(int[] nums) {
        int count = 0;
        for(int i = 0 ; i< nums.length ; i++){
            if (nums[i]%2 == 0) count ++;
        }
        return count;  
    }
    //bigDiff (Array2)
    public int bigDiff(int[] nums)
    {
        int max = nums[0];
        int min = nums[0];
        for(int i = 1; i < nums.length; i++)
        {
            if(nums[i] > max)
                max = nums[i];
            else if(nums[i] < min)
                min = nums[i];
        }
        return (max-min);
    }
    //sum13 (Array2)
    public int sum13(int[] nums) {

        if (nums.length == 0) return 0;
        int suma = 0;
        for (int i = 0; i < nums.length -1 ; i++){
            if(nums[i]== 13){
                nums[i] = 0; 
                nums[i+1]=0;

            }
            suma += nums[i];

        }
        if(nums[nums.length-1] != 13)
            suma += nums[nums.length-1];
        return suma;
    }
    //has22 (Array2)
    public boolean has22(int[] nums) {
        boolean a = false;
        for (int i = 0; i< nums.length -1; i++){

            if ((nums[i]==2 && nums[i+1]==2))
                a = true;

        }
        return a;
    }
    //lucky13 (Array2)
    public boolean lucky13(int[] nums) {
        boolean a = true;
        for (int i = 0; i<nums.length;i++){
            if (nums[i] == 1 || nums[i] == 3)
                a = false;

        }
        return a;
    }
    //sum28 (Array2)
    public boolean sum28(int[] nums) {
        int suma = 0;
        for(int i = 0; i<nums.length; i++){
            if (nums[i] == 2)
                suma += 2;
        }
        return suma == 8;

    }
    //more14 (Array2)
    public boolean more14(int[] nums) {
        int count1= 0;
        int count4 = 0;

        for(int i = 0;i<nums.length;i++){
            if (nums[i] == 1)
                count1++;
            if(nums[i] == 4)
                count4++;
        }
        return count1>count4;
    }
    //fizzArray (Array2)
    public int[] fizzArray(int n) {
        int [] a = new int[n];

        for (int i =n -1 ; i>0; i--){
            a[i] = i;  

        }
        return a;
    }
    //only14 (Array2)
    public boolean only14(int[] nums) {
        boolean a = true;
        for(int i = 0; i<nums.length ; i++){
            if (nums[i]!=4 && nums[i]!=1)
                a = false;

        }
        return a;
    }
    //fizzArray2 (Array2)
    public String[] fizzArray2(int n) {
        String [] a = new String[n];

        for (int i =n -1 ; i>=0; i--){
            a[i] = Integer.toString(i);  

        }
        return a;
    }
    //no14 (Array2)
    public boolean no14(int[] nums) {
        boolean a = true;
        boolean b = true;
        for(int i = 0; i<nums.length; i++){
            if(nums[i]==1)
                a = false;
            if(nums[i]==4)
                b = false;
        }
        return a || b;
    }
    // maxSpan (Array3)
    public int maxSpan(int[] nums) {
        int span = 0;
        for(int i = 0; i<nums.length;i++){
            for(int j = 0; j<nums.length;j++){
                if(nums[i]==nums[j])
                    if(j-i+1 > span)
                        span = j-i+1;
            }
        }
        return span;
    }
    //fix34 (Array3)
    public int[] fix34(int[] nums) 
    {
        int j = 1;
        for(int i = 0; i < nums.length - 1; i++)
        {
            if(nums[i] == 3 && nums[i+1] != 4)
            {
                for(; nums[j] != 4; j++);
                nums[j] = nums[i+1];
                nums[i+1] = 4;
            }
        }
        return nums;
    }
    //fix45 (Array3)
    public int[] fix45(int[] nums)
    {
        int j = 0;
        for(int i = 0; i < nums.length - 1; i++)
        {
            if(nums[i] == 4 && nums[i+1] != 5)
            {
                for(; !(nums[j] == 5 && (j == 0 || j > 0 && nums[j-1] != 4)); j++);
                nums[j] = nums[i+1];
                nums[i+1] = 5;
            }
        }
        return nums;
    }
    //canBalance (Array3) Complexity O(n)
 public boolean canBalance(int[] nums)
{
	int izq = 0;
	int der;
	for(int i = 0; i < nums.length - 1; i++)
		izq += nums[i];
	der = nums[nums.length-1];
	for(int i = nums.length - 2; i > 0; i--)
	{
		if(izq == der)
			return true;
		izq -= nums[i];
		der += nums[i];
	}
	return (izq == der);
}

    //squareUp (Array3)
    public int[] squareUp(int n)
    {
        int[] arr = new int[n*n];
        int square;
        for(int i = 1; i <= n; i++)
        {
            square = n * i - 1;
            for(int j = 1; j <= i; j++, square--)
                arr[square] = j;
        }
        return arr;
    }

}
