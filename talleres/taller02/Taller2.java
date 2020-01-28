class Taller2 {
    //q<p
    public static int gcd(int p, int q) {
        if(p%q == 0)
            return q;
        else
            return gcd(q,p%q);
    }

    public static boolean SumaGrupo(int start, int[] nums, int target) {
        if (start >= nums.length)
            return (target==0);
        else
            return SumaGrupo (start +1, nums, target-nums[start])|| SumaGrupo (start+1,nums,target);
    }

    public static void combinationsAux (String base, String s) {
        if (s.length() == 0)
            System.out.println (base);
        else{
             combinationsAux (base, s.substring (1));
             combinationsAux (base + s.substring (0,1),s.substring (1));
        }
    }
}
