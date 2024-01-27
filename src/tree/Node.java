package tree;

public class Node {
    private String data;
    private Node lChild;
    private Node rChild;
    public Node(String data, Node lChild, Node rChild){
        this.data = data;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    public String getData() {
        return data;
    }

    public Node getLChild() {
        return lChild;
    }

    public Node getRChild() {
        return rChild;
    }
    public void setLChild(Node lChild) {
        this.lChild = lChild;
    }

    public void setRChild(Node rChild) {
        this.rChild = rChild;
    }
}
