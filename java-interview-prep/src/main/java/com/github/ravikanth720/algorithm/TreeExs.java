package com.github.ravikanth720.algorithm;

public class TreeExs {
	public boolean isSubTree(Node root, Node s) {

		if (root == null) {
			return false;
		}
		if (s == null) {
			return true;
		}

		if (matchTrees(root, s))
			return true;

		return isSubTree(root.left, s) || isSubTree(root.right, s);
	}

	public boolean matchTrees(Node s1, Node s2) {

		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1 == null || s2 == null) {
			return false;
		}

		return ((s1.data == s2.data) && matchTrees(s1.left, s2.left) && matchTrees(
				s1.right, s2.right));

	}

	class Node {
		int data;
		Node left;
		Node right;

		Node(int val) {
			data = val;
			left = right = null;
		}
	}
	
	public static void main(String args[]){
		
	}
}
