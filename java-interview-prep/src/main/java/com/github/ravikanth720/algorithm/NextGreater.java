package com.github.ravikanth720.algorithm;

import java.util.Arrays;

/**
 * 12 -> 21
 * 121 -> 211
 * 123453 -> 
 * 
 */


class NextGreater {
  public int findNextGreater(int n) throws NumberFormatException {
    String number = n + "";
    char[] numArray = number.toCharArray();
    int len = number.length();

    for (int i = len-2; i >= 0; i--) {
      for (int j = len-1; j > i; j--) {
        if (numArray[j] > numArray[i]) {
          char c = numArray[i];
          numArray[i] = numArray[j];
          numArray[j] = c;

          // sort the rest of the array
          Arrays.sort(numArray, i+1, len);
          
          long result = Long.parseLong(new String(numArray));
          return result > Integer.MAX_VALUE ? -1 : (int)result;
        }
      }
    }

    return  -1;
  }

  public static void main(String[] args) {
    NextGreater n = new NextGreater();
    System.out.println(n.findNextGreater(12222333));
  }
}