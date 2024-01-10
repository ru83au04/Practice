package polynomial;

public class Term {
    private float coef;
    private int exp;

    public Term(float coef, int exp){
        this.coef = coef;
        this.exp = exp;
    }

    public float getCoef(){
        return coef;
    }
    public int getExp(){
        return exp;
    }
    public void setCoef(float coef){
        this.coef = coef;
    }
    public void setExp(int exp){
        this.exp = exp;
    }

    @Override
    public String toString(){
        return coef + "(" + exp + ") ";
    }
}

