package tree;

/**
 * 手動建立一棵二元樹
 */
public class NoahsBinaryTree {
    private Node nodeI = new Node("I", null, null);
    private Node nodeH = new Node("H", null, null);
    private Node nodeG = new Node("G", null, null);
    private Node nodeF = new Node("F", null, null);
    private Node nodeE = new Node("E", null, nodeI);
    private Node nodeD = new Node("D", nodeG, nodeH);
    private Node nodeC = new Node("C", nodeE, nodeF);
    private Node nodeB = new Node("B", nodeD, null);
    public Node nodeA = new Node("A", nodeB, nodeC);

    /**
     * 中序遍歷二元樹
     * @param root
     */
    public static void inorder(Node root){
        if(root == null){
            return;
        }
        inorder(root.getLChild());
        System.out.print(root.getData());
        inorder(root.getRChild());
    }

    /**
     * 前序遍歷二元樹
     * @param root
     */
    public static void preorder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.getData());
        preorder(root.getLChild());
        preorder(root.getRChild());
    }

    /**
     * 後續遍歷二元樹
     * @param root
     */
    public static void postorder(Node root){
        if(root == null){
            return;
        }
        postorder(root.getLChild());
        postorder(root.getRChild());
        System.out.print(root.getData());
    }

    /**
     * 使用前序遍歷方式複製二元樹
     * @param root
     * @return 複製後二元樹的 root
     */
    public static Node preCopy(Node root){
        if(root != null){
            Node temp = new Node(root.getData() + "pre,", null, null);
            System.out.print(temp.getData());
            temp.setLChild(preCopy(root.getLChild()));
            temp.setRChild(preCopy(root.getRChild()));
            return temp;
        }
        else{
            return null;
        }
    }

    /**
     * 使用後序遍歷方式複製二元樹
     * @param root
     * @return 複製後二元樹的 root
     */
    public static Node postCopy(Node root){
        if(root != null){
            Node newL = postCopy(root.getLChild());
            Node newR = postCopy(root.getRChild());
            Node temp = new Node(root.getData() + "post,", newL, newR);
            System.out.print(temp.getData());
            return temp;
        }
        else{
            return null;
        }
    }

}
