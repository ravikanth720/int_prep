package com.github.ravikanth720.algorithm;

public class LinkedList {

	public Node circularInsert(int val, Node root) {
         // base case
		if (root == null) {
			Node n = new Node(val);
			n.next = n;
			return n;
		}
        
		Node prev = root;
		Node curr = root.next;
        
		// there is only one node
		if (curr == prev) {
			Node ret = new Node(val);
			curr.next = ret;
			ret.next = prev;
			return ret;
		}

		while (curr != root) {
			
			if(prev.data > curr.data){
				if(prev.data <= val || curr.data >= val){
					Node ret = new Node(val);
					prev.next = ret;
					ret.next = curr;
					return ret;
				}else {
					System.out.println("else");
					prev = curr;
					curr = curr.next;
				}
			}		
			else if (prev.data <= curr.data){
				if ((prev.data < val && curr.data > val) || prev.data == val
						|| curr.data == val) {
					System.out.println("entered with prev = "+prev.data+" curr = "+curr.data);
					Node ret = new Node(val);
					prev.next = ret;
					ret.next = curr;
					
					return ret;
					
				} else {
					System.out.println("else");
					prev = curr;
					curr = curr.next;
				}
			}
		}
		
		Node ret = new Node(val);
		prev.next = ret;
		ret.next = curr;
		
		return ret;
	}

	public class Node {
		int data;
		Node next;

		Node(int v) {
			data = v;
			next = null;
		}
	}


	 public static class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }


	public ListNode deleteDuplicates(ListNode head) {
		ListNode root = new ListNode(0);
		root.next = head;
		ListNode curr = head, prev = root;

		while (curr != null) {
			ListNode next = curr.next;
			if (next != null) {
				if (curr.val == next.val) {
					while (next != null && curr.val == next.val) {
						curr = next;
					}

					prev.next = curr;
				} else {
					prev = curr;
					curr = curr.next;
				}
			} else {
				return root.next;
			}
		}

		return root.next;
	}
	
	public void test(){
		Node root = new Node(1);
		Node n = root.next = new Node(2);
		n.next = root;
		//Node n = root.next.next = new Node(3);
		//root.next.next.next = new Node(4);
		//n.next = root;
		
		Node ret = circularInsert(3, n);
		
		Node it = ret;
		System.out.println();
		do{
			System.out.print(it.data+" ");
			it = it.next;
		}while(it != ret);
	}

	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next.next = new ListNode(5);
		l.deleteDuplicates(head);
	}

}
