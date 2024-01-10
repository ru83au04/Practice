package kmp;

public class Kmp {
    public static int[] phaseOne(String pattern){
        char[] patternArray = pattern.toCharArray();
        int m = patternArray.length;
        int len = 0;
        int i = 1;
        int[] lps = new int[m];
        for(int j = 0; j < lps.length; j++){
            lps[j] = 0;
        }
        while(i < m){
            if(patternArray[i] == patternArray[len]){
                len++;
                lps[i] = len;
                i++;
            }
            else{
                if(len != 0){
                    len = lps[len - 1];
                }
                else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
