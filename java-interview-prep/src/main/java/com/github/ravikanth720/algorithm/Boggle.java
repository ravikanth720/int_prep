package com.github.ravikanth720.algorithm;
/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class Boggle {
	public static void main (String[] args) {
		Boggle g = new Boggle();
		System.out.println(
    		g.findWords(
    		    Arrays.asList(
    		        Arrays.asList('G','I','Z'),
    		        Arrays.asList('U','E','K'),
    		        Arrays.asList('Q','S','E')
    		    ),
    		    Arrays.asList("GEEKS", "FOR", "QUIZ", "GO"))
		);
	}
	
	public List<String> findWords(List<List<Character>> board, List<String> dict) {
	    List<String> result = new ArrayList<String>();
	    for (String word: dict) {
    	    for (int i=0; i<board.size(); i++) {
    	        for (int j=0; j<board.get(0).size(); j++) {
    	            if (board.get(i).get(j) == word.charAt(0)) {
                    System.out.println(word + " i = " + i + " j = " + j);
    	              if(dfs(board, i, j, 0, word))  result.add(word);
    	            }
    	        }
    	    }
	    }
	    return result;
	}
	
	public boolean dfs(List<List<Character>> board, int i, int j, int curr, String word) {
          if (curr == word.length()-1) return true;
          
          boolean result = false;
	        
	        if (i > 0 && board.get(i-1).get(j) == word.charAt(curr+1)) result = dfs(board, i-1, j, curr+1, word);
	        if (j > 0 && board.get(i).get(j-1) == word.charAt(curr+1)) result = result || dfs(board, i, j-1, curr+1, word);
	        if (i < board.size()-1 && board.get(i+1).get(j) == word.charAt(curr+1)) result = result || dfs(board, i+1, j, curr+1, word);
          if (j < board.get(0).size()-1 && board.get(i).get(j+1) == word.charAt(curr+1)) result = result || dfs(board, i, j+1, curr+1, word);
          if (i > 0 && j > 0 && board.get(i-1).get(j-1) == word.charAt(curr+1)) result = dfs(board, i-1, j-1, curr+1, word);
	        if (i < board.size()-1 && j < board.get(0).size()-1 && board.get(i+1).get(j+1) == word.charAt(curr+1)) result = result || dfs(board, i+1, j+1, curr+1, word);
	        if (i < board.size()-1 && j > 0 && board.get(i+1).get(j-1) == word.charAt(curr+1)) result = result || dfs(board, i+1, j-1, curr+1, word);
	        if (j < board.get(0).size()-1 && i > 0 && board.get(i-1).get(j+1) == word.charAt(curr+1)) result = result || dfs(board, i-1, j+1, curr+1, word);
	        
	        return result;
	}
}