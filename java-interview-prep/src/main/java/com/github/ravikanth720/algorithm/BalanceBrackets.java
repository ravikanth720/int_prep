package com.github.ravikanth720.algorithm;
import java.util.Stack;

public class BalanceBrackets {
	public boolean isBalanced(String input){
		int count = 0;
		Stack<Character> bracket = new Stack<Character>();
		for(int i = 0; i < input.length(); i++) {
			System.out.println(input.charAt(i));
			System.out.println("b = " + bracket);
			if(input.charAt(i) == '(' || input.charAt(i) == '[' || input.charAt(i) == '{') {
				bracket.push(input.charAt(i));
			} else if (input.charAt(i) == ')' || input.charAt(i) == ']' || input.charAt(i) == '}') {
				if (bracket.isEmpty()) {
					System.out.println("now = " + bracket);
					System.out.println(input.charAt(i));
					return false;
				} else {
					switch(input.charAt(i)) {
						case ')':
						 return bracket.pop() == '(';
						case ']':
						 return bracket.pop() == '[';
						case '{':
						 return bracket.pop() == '{';
					}
			}
			}
		}
		
		return true;
	}
	
	public static void main(String args[] ){
		BalanceBrackets b = new BalanceBrackets();
		System.out.println(b.isBalanced("([{}])"));
	}
}
