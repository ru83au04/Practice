package polynomial;


public class Polynomial{
    public static Term[] termArray = new Term[100];
    public static int free = 0; // 陣列中還未使用空間的起始位置
    private int start, finish, length;
    /*
        將多項式的每個項目以Term的形式輸入
        在建立多項式時會將多項式存入靜態陣列termArray中
     */
    public Polynomial(Term... terms){
        this.start = free;
        for(Term t : terms){
            if(t != null) {  // 以防存入的陣列中的null
                termArray[free] = t;
                free++;
            }
        }
        this.finish = free - 1;
        this.length = finish - start + 1;
    }

    public int getStart(){
        return start;
    }
    public int getFinish(){
        return finish;
    }
    public int getLength(){
        return length;
    }

    /**
     * 將兩個陣列相加
     * @param poly
     * @return Polynomial
     */
    public Polynomial mergePoly(Polynomial poly){
        Term[] sum = new Term[getLength() + poly.getLength()]; // 儲存相加結果的陣列，陣列大小為兩個多項式的項目數量總和
        int i  = 0;
        int start = getStart();
        int finish = getFinish();
        int polyStart = poly.getStart();
        int polyFinish = poly.getFinish();
        /*
            依序同時檢查兩個多項式各項系數與指數
            當任何一個多項式已經檢查完畢就結束迴圈
         */
        while(start <= finish && polyStart <= polyFinish){
            if(termArray[start].getExp() > termArray[polyStart].getExp()){  // 指數小的項目存入sum陣列，並將移動至下一項目
                sum[i] = termArray[start];
                i++;
                start++;
            }
            if(termArray[start].getExp() < termArray[polyStart].getExp()){  // 同上
                sum[i] = termArray[polyStart];
                i++;
                polyStart++;
            }
            if(termArray[start].getExp() == termArray[polyStart].getExp()){  // 指數部分相同則將細數相加並建立新的Term存入sum
                float cCoef = termArray[start].getCoef() + termArray[polyStart].getCoef();
                Term sumTerm = new Term(cCoef, termArray[start].getExp());
                sum[i] = sumTerm;
                i++;
                start++;
                polyStart++;
            }
        }
        while(start <= finish){  // 將未檢查完的多項事剩餘項目存入sum
            sum[i] = termArray[start];
            i++;
            start++;
        }
        while(polyStart <= polyFinish){
            sum[i] = termArray[polyStart];
            i++;
            polyStart++;
        }
        return new Polynomial(sum);
    }
}
