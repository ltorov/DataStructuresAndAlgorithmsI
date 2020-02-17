/**
 * This class contains the code for the exercices in codingbat from recursion 1 and recursion 2.
 * @Gregorio Pérez Bernal
 * @Luisa Toro Villegas
 * @February 2020
 */
public class EjerciciosEnLinea
{   
    //Recursion 1 count 11
    public static int count11(String str) {
        if (str.length()<2) return 0;
        if ((str.substring(0,2)).equals("11")) return 1 + count11(str.substring(2));
        return count11(str.substring(1));
    }
    //Recursion 1 count abc
    public static int countAbc(String str) {
        if (str.length()<3) return 0;
        if (((str.substring(0,3)).equals("abc"))||((str.substring(0,3)).equals("aba"))) return 1 + countAbc(str.substring(1));
        return countAbc(str.substring(1));
    }
    //Recursion 1 countPairs
    public static  int countPairs(String str) {
        if (str.length() <3) return 0;
        if (str.charAt(0) == str.charAt(2)) return 1 +countPairs(str.substring(1));
        return countPairs(str.substring(1));
    }
    //Recursion 1 end x
    public static String endX(String str) {
        if (str.length()==0) return str;
        if (str.charAt (0) =='x') return endX(str.substring(1)) + 'x';
        return str.charAt(0) + endX(str.substring(1));
    }
    //Recursion 1 pairStar
    public static String pairStar(String str) {
        if (str.length()<=1) return str;
        if (str.charAt(0)==str.charAt(1)) return str.charAt(0)+"*"+pairStar(str.substring (1));
        return str.charAt(0)+pairStar(str.substring(1));
    }
    //Recursion 2 groupSum 6
    public boolean groupSum6(int start, int[] nums, int target) {
        if(start >= nums.length) return (target == 0);
        if (nums [start] != 6 && (groupSum6(start + 1, nums, target))) return true;
        if (groupSum6(start + 1, nums, target - nums[start])) return true;
        return false;
    }
    //Recursion 2 groupnoadj
    public boolean groupNoAdj(int start, int[] nums, int target) {
        if(target == 0) return true;
        if(start >= nums.length) return false;
        if (groupNoAdj(start + 2, nums, target - nums[start])) return true;
        return groupNoAdj(start + 1, nums, target);
    }
    //Recursion 2 groupSum5
    // We used the solution in github to guide ourselves
    //https://github.com/ozelentok/CodingBat-Solutions/blob/master/Java/Recursion-2.java
    public boolean groupSum5(int start, int[] nums, int target) {
        if(start >= nums.length) return (target == 0);
        if (nums [start]%5 ==0){
            if (start < nums.length -1 && nums [start+1]==1) return groupSum5(start + 2, nums, target-nums[start]);
            return groupSum5(start + 1, nums, target-nums[start]);
        }
        if (groupSum5(start + 1, nums, target - nums[start])) return true;
        return (groupSum5(start + 1, nums, target));
    }
    // Recursion 2 splitarray
    //https://github.com/ozelentok/CodingBat-Solutions/blob/master/Java/Recursion-2.java
    public boolean splitArray(int[] nums) {	
        return AUX(nums, 0, 0);
    }

    public boolean AUX(int[] nums, int n, int a)
    {
        if(n == nums.length) return (a == 0);
        if(AUX(nums, n + 1, a + nums[n])) return true;
        return AUX(nums, n + 1, a - nums[n]);
    }
    //Recursion 2 SplitOdd10
    //https://github.com/ozelentok/CodingBat-Solutions/blob/master/Java/Recursion-2.java
    public boolean splitOdd10(int[] nums){	
        return AUX(nums, 0, 0, 0);
    }

    public boolean AUX(int[] nums, int n, int a, int b)
    {
        if(n == nums.length) return (a % 2 == 1 && b % 10 == 0 || b % 2 == 1 && a % 10 == 0);
        if (AUX(nums, n + 1, a + nums[n], b)) return true;
        return AUX(nums, n + 1, a, b + nums[n]);
    }
}
