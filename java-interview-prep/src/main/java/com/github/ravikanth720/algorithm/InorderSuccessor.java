package  com.github.ravikanth720.algorithm;

class InorderSuccessor {
public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    return sol(root, null, p.val);
  }
  
  public TreeNode sol(TreeNode root, TreeNode parent, int val) {
    if (root == null) return null;

    if (root.val < val) {
      return sol(root.right, parent, val);
    } else if (root.val > val) {
      return sol(root.left, root, val);
    } else {
      return parent;
    }
  }
public void runit() {
      TreeNode root = new TreeNode(5);
      root.left = new TreeNode(3);
      root.right = new TreeNode(6);
      TreeNode p = new TreeNode(2);
      root.left.left = p;
      root.left.right = new TreeNode(4);
      root.left.left.left = new TreeNode(1);

      TreeNode result = inorderSuccessor(root, p);
      if (result != null) System.out.println(result.val);
 
}

  public static void main(String args[]) {
      
    InorderSuccessor io = new InorderSuccessor();
    io.runit();
 }
}
