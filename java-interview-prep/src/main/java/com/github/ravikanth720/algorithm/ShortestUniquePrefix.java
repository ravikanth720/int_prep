package com.github.ravikanth720.algorithm;
import java.util.*;

class ShortestUniquePrefix {
  String shortetPrefix(List<String> bag, String input) {
    TNode trie = new TNode('^');
    for(String word: bag) {
      addToNode(trie, word);
    }

    StringBuilder result = new StringBuilder();
    for(Character c: input.toCharArray()) {
      TNode node = trie.children.get(c);
      if (node == null) return "";
      result.append(c);
      if (node.freq == 1) return result.toString();
      
      trie = node;
    }

    return result.toString();
  }

  void addToNode(TNode root, String word) {
    TNode node = root;
    for (int i = 0; i < word.length(); i++) {
      TNode curr = node.children.getOrDefault(word.charAt(i), new TNode(word.charAt(i)));
      curr.freq++;
      node.children.put(word.charAt(i), curr);
      node = curr;
    }

    node.isEnd = true;
  }

  public static void main(String[] args) {
    ShortestUniquePrefix s = new ShortestUniquePrefix();
    System.out.println(s.shortetPrefix(Arrays.asList("zebra", "dog", "duck", "dove", "dover"), "zebra"));
  }

  class TNode {
    char c;
    Map<Character, TNode> children;
    int freq;
    boolean isEnd;

    TNode(char c) {
      this.c = c;
      children = new HashMap<Character, TNode>();
    }
  }
}