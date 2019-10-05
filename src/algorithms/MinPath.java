
public class MinPath {
	
	public int minBst(Node root){
		int minPath = Integer.MAX_VALUE;
		if(root == null){
			return 0;
		}
		minPath = findMinPath(root, 0, minPath);
		
		return minPath;
	}
	
	
	private int findMinPath(Node root, int path, int minPath) {
		
		if(root == null){
			if(path < minPath){
				minPath = path;
			}
		}
		else{
			minPath = findMinPath(root.left, path+root.data, minPath);
			minPath = findMinPath(root.right, path+root.data, minPath);
		}
		
		return minPath;
	}
	
private int findMinPath1(Node root, int path, int minPath) {
		
		/*if(root == null){
			if(path < minPath){
				minPath = path;
			}
		}
		else{*/
			if(root.left == null && root.right == null){
				path = path + root.data;
				if(path < minPath){
					minPath = path;
				}
			}
			
			if(root.left != null)
				minPath = findMinPath(root.left, path+root.data, minPath);
			
			if(root.right != null)
				minPath = findMinPath(root.right, path+root.data, minPath);
			
			
		//}
		
		return minPath;
	}

	public static void main(String[] args) {
		MinPath m = new MinPath();
		m.test();
	}
	private void test() {
		
		Node root = new Node(2);
		root.left = new Node(4);
		root.right = new Node(3);
		root.left.left = new Node(2);
		root.left.right = new Node(5);
		root.right.right = new Node(5);
		
		System.out.println(minBst(root));
		
	}
	class Node{
		int data;
		Node right;
		Node left;
		public Node(int val){
			data = val;
			right = null;
			left = null;
		}
	}
}
