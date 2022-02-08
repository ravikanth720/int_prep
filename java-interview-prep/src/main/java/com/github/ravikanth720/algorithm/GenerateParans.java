package com.github.ravikanth720.algorithm;

import java.util.ArrayList;
import java.util.List;

class GenerateParans {
  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    parensHelper(n, "", n, result);

    return result;
  }

  public void parensHelper(int n, String pars, int closings, List<String> result) {
    if (n == 0) {
      while(closings-- > 0) {
        pars += ")";
      }

      result.add(pars);
      return;
    }
 
    parensHelper(n-1, pars + "(", closings, result);
    parensHelper(n-1, pars + "()", closings - 1, result);
    // while(n < closings) {
    //   closings--;
    //   close += ")";
    //   parensHelper(n, pars + close, closings, result);
    // }
  }
  public static void main(String[] args) {
    GenerateParans g = new GenerateParans();
    System.out.println(g.generateParenthesis(3));
  }
}