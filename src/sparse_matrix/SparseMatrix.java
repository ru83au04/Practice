package sparse_matrix;

import java.util.Arrays;
import java.util.Objects;

public class SparseMatrix {
    private Term[] matrix;

    public SparseMatrix(Term... terms){
        this.matrix = terms;
    }

    public Term[] getMatrix(){
        return matrix;
    }
    /*
        將二維矩陣轉換為儲存Term的一維矩陣
        Term的格式為(row, column, value)
     */
    public static Term[] getSparseMatrix(int[][] numberArray){
        Term[] newTerms = new Term[(numberArray[0].length * numberArray.length)];
        int k = 0;
        for(int i = 0; i < numberArray.length; i++){
            for(int j = 0; j < numberArray[i].length; j++){
                if(numberArray[i][j] != 0){
                    newTerms[k] = new Term(i, j, numberArray[i][j]);
                    k++;
                }
            }
        }
        Term[] result = new Term[(int)Arrays.stream(newTerms).filter(Objects::nonNull).count()];
        for(int i = 0; i < result.length; i++){
            result[i] = newTerms[i];
        }
        return result;
    }
    /*
        二維陣列的欄、列轉換
        在轉換前需要將二維陣列轉換為Term的一維陣列
     */
    static public Term[] trans(Term[] matrix){
        int[] rowSize = new int[100];              // 建立rowSize陣列，去記錄轉換後每個row上會有幾個元素
        int[] rowStarts = new int[100];            // 透過rowSize去計算每個row的首個元素的位置索引
        Term[] result = new Term[matrix.length];   // 轉換後的陣列
        for(Term t : matrix){
            rowSize[t.getColumn()]++;
        }
        rowStarts[0] = 0;
        for(int i = 1; i < rowStarts.length; i++){
            rowStarts[i] = rowStarts[i - 1] + rowSize[i - 1];
        }
        for(int i = 0; i < result.length; i++){
            Term temp = new Term(matrix[i].getColumn(), matrix[i].getRow(), matrix[i].getValue());
            result[rowStarts[matrix[i].getColumn()]] = temp;  // 將轉換後的Term透過rowSize紀錄的位置存入result陣列
            rowStarts[matrix[i].getColumn()]++;  // 移動rowSize的存入位置
        }
        return result;
    }

}
