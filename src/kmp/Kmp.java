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

    public static int phaseTwo(String text, String pattern){
        int[] lps = phaseOne(pattern);
        int i = 0;
        int j = 0;
        while(i < text.length()){
            if(j == 0 || pattern.charAt(j) == text.charAt(i)){
                i++;
                j++;
            }
            if(j == pattern.length()){
                System.out.println("Pattern is searched in Text");
                return i - j;
            }
            else if(i < text.length() && pattern.charAt(j) != text.charAt(i)){
                if(j != 0){
                    j = lps[j - 1];
                }
                else{
                    i++;
                }
            }
        }
        System.out.println("Not found");
        return -1;
    }
}
