package com.github.ravikanth720.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

class ExpressiveWords {
  public int expressiveWords(String S, String[] words) {
      int result = 0;
      for (String word: words) {
        if (check(S, word)) {
          result++;
        }
      }

      return result;
  }

  public boolean check(String s, String word) {
    int slen = s.length(), wlen = word.length();
    int i=0, j=0, count = 0;
    while (i < slen) {
      if (j < wlen && s.charAt(i) == word.charAt(j)) {
        i++;
        j++;
        count++;
      } else {
        while (j > 0 && i < slen && s.charAt(i) == word.charAt(j-1)) {
          i++;
          count++;
        }

        if (count < 3) return false;
        count = 0;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    ExpressiveWords e = new ExpressiveWords();
    System.out.println(e.expressiveWords("heelllloooo", new String[]{"hi", "hello", "helo"}));
  }
}
/**
 * 
 * heelllloooo
 * hi hello helo
 * 
"zzzzzyyyyy"
["zzyy","zy","zyy"]
 */