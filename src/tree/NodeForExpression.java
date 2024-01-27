package tree;

public class NodeForExpression {
    private char data;
    private NodeForExpression lChild;
    private NodeForExpression rChild;
    private boolean value;

    public NodeForExpression(char data, NodeForExpression lChild, NodeForExpression rChild){
        this.data = data;
        this.lChild = lChild;
        this.rChild = rChild;
        this.value = true;
    }
    public NodeForExpression(char data){
        this(data, null, null);
    }

    public void setData(char data) {
        this.data = data;
    }

    public void setlChild(NodeForExpression lChild) {
        this.lChild = lChild;
    }

    public void setrChild(NodeForExpression rChild) {
        this.rChild = rChild;
    }

    public char getData(){
        return data;
    }

    public NodeForExpression getlChild() {
        return lChild;
    }

    public NodeForExpression getrChild() {
        return rChild;
    }

    public boolean getValue(){
        return value;
    }

    public void setValue(boolean value){
        this.value = value;
    }
}
