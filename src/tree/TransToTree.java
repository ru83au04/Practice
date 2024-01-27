package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 將 boolean expression轉為binary tree
 */

public class TransToTree {

    private int index = 0;

    /**
     * 檢查輸入字元是否為比較符
     * @param c
     * @return 比較結果 boolean
     */
    private boolean isOperator(char c){
        return c == '&' || c == '|' || c == '!' || c == '(' || c == ')';
    }

    /**
     * 比較符在 Stack裡的優先權重
     * @param operator
     * @return 權重值 int
     */
    public int getIsp(char operator){
        return switch(operator){
            case '&', '|' -> 1;
            case '!' -> 2;
            default -> 0;
        };
    }

    /**
     * 比較符在 Stack外的優先權重
     * @param operator
     * @return 權重值 int
     */
    public int getIcp(char operator){
        return switch(operator){
            case '&', '|'-> 1;
            case '!' -> 2;
            case '(' -> 3;
            default -> 0;
        };
    }

    /**
     * 比較運算
     * @param operator
     * @param valueA
     * @param valueB
     * @return 比較的結果 boolean
     */
    public boolean compare(char operator, boolean valueA, boolean valueB){
        return switch(operator){
            case '&' -> valueA && valueB;
            case '|' -> valueA || valueB;
            default -> !valueB;
        };
    }

    /**
     * 直接將 boolean expression轉為二元樹
     * @param s
     * @return 建立二元樹的 root
     */
    public NodeForExpression buildTree(String s) {
        char[] postfix = s.toCharArray();           // 將輸入字串轉換為char[]
        Stack<NodeForExpression> st = new Stack<>();            // 建立Stack來暫存節點
        while (index < postfix.length && postfix[index] != ')'){        // 依序檢查元素，結束條件為跑完陣列或遇到')'
            if (postfix[index] == '(') {            // 如果遇到'('，以遞迴方式優先處理括號內元素
                index++;
                while (postfix[index] != ')') {         // 結束括弧內的元素處理
                    st.push(buildTree(s));
                }
                index++;
            } else if (!isOperator(postfix[index])) {           // 如果元素非比較符，建立節點後放入Stack
                NodeForExpression newNode = new NodeForExpression(postfix[index++]);
                st.push(newNode);
            } else {
                NodeForExpression operator = new NodeForExpression(postfix[index]);
                if(postfix[index] == '!'){          // 元素為NOT比較符則建立後一元素節點作為其右子節點
                    index++;
                    var rChild = new NodeForExpression(postfix[index]);
                    operator.setrChild(rChild);
                }
                st.push(operator);          // 將節點存入Stack
                index++;
            }
            if(st.size() == 3){         // 當Stack存滿三個元素，則取出三個元素建立子樹並存回Stack
                var rChild = st.pop();
                var operator = st.pop();
                var lChild = st.pop();
                operator.setrChild(rChild);
                operator.setlChild(lChild);
                st.push(operator);
            }
        }
        return st.pop();            // 回傳最後堆疊的節點
    }

    /**
     * 將 boolean expression轉為後綴表達式
     * @param s
     * @return 後綴表達式的 List
     */
    public List<Character> getPostfix(String s){
        char[] array = s.toCharArray();         // 將字串轉為字元陣列
        List<Character> result = new ArrayList<>();         // 建立儲存結果的List
        Stack<Character> operator = new Stack<>();          // 建立暫存比較符的Stack
        for(int i = 0; i < array.length; i++){          // 依序檢查字元陣列
            if(!isOperator(array[i])){          // 元素若非比較符則直接存入List
                result.add(array[i]);
            }
            else if(array[i] == ')'){           // 元素若為')'則取出所有Stack內的運算符並存入List
                while(operator.peek() != '('){           // 遇到'('表示括號內運算符都處理完畢
                    result.add(operator.pop());
                }
                operator.pop();         // 最後的Stack內只會剩下'('，將其取出不儲存
            }
            else{           // 若元素為比較符
                while(!operator.isEmpty() &&            //  如果Stack內不為空則檢查比較符的權重，選擇存入List及Stack的對象
                        getIsp(operator.peek()) >= getIcp(array[i])){
                    result.add(operator.pop());
                }
                operator.push(array[i]);
            }
        }
        while(!operator.isEmpty()){         // 跑完陣列所有元素後，將剩餘在Stack內的運算符依序加入List
            result.add(operator.pop());
        }
        return result;
    }

    /**
     * 使用後綴表達式建立二元樹
     * @param postfix
     * @return 建立二元樹的 root
     */
    public NodeForExpression buildNewTree(List<Character> postfix){
        int index = 0;
        Stack<NodeForExpression> st = new Stack<>();            // 建立Stack儲存節點
        while(index < postfix.size()){          // 遍歷List
            NodeForExpression node = new NodeForExpression(postfix.get(index));
            if (isOperator(postfix.get(index))) {
                if (postfix.get(index) == '!') {            // 元素為NOT比較符則取出Stack內一個節點作為其右子節點
                    node.setrChild(st.pop());

                } else {            // 元素若為其他比較符，則取出Stack內兩個節點作為左、右節點
                    node.setrChild(st.pop());
                    node.setlChild(st.pop());
                }
            }
            st.push(node);          // 將節點存入Stack
            index++;
        }
        return st.pop();            // 回傳二元樹的root
    }

    /**
     * 使用 postorder遍歷二元樹計算比較結果
     * @param root
     * @return
     */
    public boolean testResult(NodeForExpression root){
        boolean ans = true;
        if(root == null){
            return true;
        }
        boolean a = testResult(root.getlChild());
        boolean b = testResult(root.getrChild());
        if(isOperator(root.getData())){
            ans = compare(root.getData(), a, b);
        }
        return ans;
    }

    public void postorder(NodeForExpression root){
        if(root == null){
            return;
        }
        postorder(root.getlChild());
        postorder(root.getrChild());
        System.out.print(root.getData());
    }
    public void inorder(NodeForExpression root){
        if(root == null){
            return;
        }
        inorder(root.getlChild());
        System.out.print(root.getData());
        inorder(root.getrChild());
    }

}
