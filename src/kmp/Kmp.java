package kmp;

/**
 * KMP檢查字串
 */

public class Kmp {

    /**
     * 建立比對陣列 lps
     * @param pattern
     * @return int[]
     */
    public static int[] phaseOne(String pattern){
        char[] patternArray = pattern.toCharArray();
        int m = patternArray.length;
        int len = 0;
        int i = 1;
        int[] lps = new int[m];
        for(int j = 0; j < lps.length; j++){        // 初始化lps陣列
            lps[j] = 0;
        }
        while(i < m){                               // 如果索引i及len位置的字元相等，則將i位置的lps設定為len+1
            if(patternArray[i] == patternArray[len]){
                len++;
                lps[i] = len;
                i++;
            }
            else{                                   // 如果索引i及len位置的字元不相等，則將len回溯直至i及len相等或len為零
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
        int[] lps = phaseOne(pattern);          // 建立比對陣列
        int i = 0;
        int j = 0;
        while(i < text.length()){               // 比對text及pattern
            if(j == 0 || pattern.charAt(j) == text.charAt(i)){      // 索引i、j字元相等則接續後面字元比對
                i++;
                j++;
            }
            if(j == pattern.length()){          // j已經走到底表示pattern所有字元都比對成功，並回傳text中pattern字串的起始位置
                System.out.println("Pattern is searched in Text");
                return i - j;
            }
            else if(i < text.length() && pattern.charAt(j) != text.charAt(i)){  // 索引i、j位置字元不相等
                if(j != 0){                     // 檢查j是否為首字元，非首字元則回溯至lps比對陣列的相對位置
                    j = lps[j - 1];
                }
                else{                           // 如果j為首字元直接接續比對下一個i位置的字元
                    i++;
                }
            }
        }
        System.out.println("Not found");
        return -1;
    }
}
