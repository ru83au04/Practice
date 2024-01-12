import kmp.Kmp;
import sparse_matrix.SparseMatrix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String text = "Noah is on the train";
        String pattern = "the";
        int result = Kmp.phaseTwo(text, pattern);
        System.out.println(result);
    }
}