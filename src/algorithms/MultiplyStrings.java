package algorithms;

import java.util.Arrays;

class MultiplyStrings {
  public String multiply(String num1, String num2) {
    char[] n1 = num1.toCharArray();
    char[] n2 = num2.toCharArray();
    char[] resultArray = new char[n1.length + n2.length];
    int resultIdx = 0, resultIdxMax = 0;
    Arrays.fill(resultArray, '0');

    for(int i = n1.length -1; i >= 0; i--, resultIdx++) {
      int k = resultIdx;
      int carry = 0;
      for (int j = n2.length-1; j >= 0; j--) {
        int sum = carry + Character.getNumericValue(resultArray[k]) + (Character.getNumericValue(n1[i]) * Character.getNumericValue(n2[j]));
        resultArray[k] = (char)('0' + (sum%10));
        carry = sum/10;
        k++;
        resultIdxMax = resultIdxMax < k ? k : resultIdxMax;
      }

      if (carry > 0) {
        resultArray[k] = (char)(carry + resultArray[k] + '0');
      }
    }
    
    String result = new String(Arrays.copyOfRange(resultArray, 0, resultIdxMax));
    return new StringBuffer(result).reverse().toString();
  }

  int sum(char a, char b) {
    return Character.getNumericValue(a) + Character.getNumericValue(b);
  }

  public static void main(String[] args) {
    MultiplyStrings m = new MultiplyStrings();
    System.out.println(m.multiply("123", "456"));
  }
}

/**
 *  1  2
 *  1  1
 * --------
 *     1  2
 *  1  2
 * ----------
 *  1  3  2
 * 
 * 
 * 
 */