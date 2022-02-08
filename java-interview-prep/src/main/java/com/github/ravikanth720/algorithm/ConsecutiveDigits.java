package com.github.ravikanth720.algorithm;
import java.util.*;
/**
 * ConsecutiveDigits
 */
public class ConsecutiveDigits {

  // public List<Integer> generateNumbers(int N, int K) {
  // }

  public void helper(int soFar, int k, int n, List<Integer> res) {
    if (n == 0) {
      res.add(soFar);
      return;
    }

    int prev = soFar % 10;
    if (prev - k >= 0) {
      helper((soFar * 10) + (prev - k), k, n-1, res);
    }

    if (prev + k <= 9) {
      helper((soFar * 10) + (prev + k), k, n-1, res);
    }
  }

  // private List<Integer> crossProduct(List<Integer> prefix, List<List<Integer>> suffixes) {
  //   for (List<Integer> suffix: suffixes) {

  //   }
  // }

  public static void main(String[] args) {
    ConsecutiveDigits c = new ConsecutiveDigits();
    //System.out.println(c.generateNumbers(3, 7));
  }
}