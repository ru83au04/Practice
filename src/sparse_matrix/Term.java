package sparse_matrix;

public class Term {
    private int row, column, value;

    public Term(int row, int column, int value){
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public int getValue() {
        return value;
    }

    @Override
    public String toString(){
        return "(%d, %d, %d)".formatted(row, column, value);
    }
}
