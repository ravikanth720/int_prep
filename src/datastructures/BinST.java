import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class BinST {

  class Node {
    Node left, right;
    int val;

    Node(int val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return this.val + "";
    }
  }
  
  public List<Integer> kElementsBiggerThanT(Node root, int T, int k) {
    Deque<Node> stk = new LinkedList<Node> ();
    List<Integer> result = new ArrayList<Integer>();

    // Find T/ closest element < T.
    while(root != null) {
      if (root.val > T) {
        stk.push(root);
        root = root.left; 
      } else if (root.val < T) {
        root = root.right;
      } else if (root.right != null) {
        addInorder_kElementsBiggerThanT(root.right, result, T, k);
        break;
      }
    }

    // Find k smallest elements bigger than T
    while (!stk.isEmpty() && result.size() < k) {
      Node curr = stk.pop();
      result.add(curr.val);
      if (curr.right != null) addInorder_kElementsBiggerThanT(curr.right, result, T, k);
    }

    return result;
  }

  // Inoder traversal of Binary Tree and Add atmost k elements to res array in ascending order.
  private void addInorder_kElementsBiggerThanT(Node root, List<Integer> result, int T, int k) {
    if (root == null || result.size() >= k) return;
    addInorder_kElementsBiggerThanT(root.left, result, T, k);
    if (result.size() < k) result.add(root.val);
    addInorder_kElementsBiggerThanT(root.right, result, T, k);
  }

  public void run_kElementsBiggerThanT() {
    Node root = new Node(7);
    root.left = new Node(4);
    root.right = new Node(15);
    root.left.left = new Node(2);
    root.left.right = new Node(5);
    root.right.left = new Node(8);
    root.right.right = new Node(17);
    root.right.left.right = new Node(13);
    root.right.left.right.right = new Node(14);
    root.right.left.right.left = new Node(11);
    System.out.println(kElementsBiggerThanT(root, 7, 2));
  }

  public static void main(String[] args) {
    BinST b = new BinST();
    b.run_kElementsBiggerThanT();  
  }
}