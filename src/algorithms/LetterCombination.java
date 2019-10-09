package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LetterCombination {
  final static Map<Character, List<Character>> LETTER_COMBINATIONS = createLetterMap();

  private static Map<Character, List<Character>> createLetterMap() {
    Map<Character, List<Character>> lc = new HashMap<>();
    lc.put('2', Arrays.asList('a', 'b', 'c'));
    lc.put('3', Arrays.asList('d', 'e', 'f'));
    lc.put('4', Arrays.asList('g', 'h', 'i'));
    lc.put('5', Arrays.asList('j', 'k', 'l'));
    lc.put('6', Arrays.asList('m', 'n', 'o'));
    lc.put('7', Arrays.asList('p', 'q', 'r', 's'));
    lc.put('8', Arrays.asList('t', 'u', 'v'));
    lc.put('9', Arrays.asList('w', 'x', 'y', 'z'));

    return lc;
  }
  
  public List<String> letterCombinations(String digits) {
    char[] digitsArray = digits.toCharArray();
    List<String> result = new ArrayList<>();
    getCombinations(digitsArray, 0, "", result);

    return result;
  }

  public void getCombinations(char[] digits, int i, String s, List<String> result) {
    for (Character c: LETTER_COMBINATIONS.get(digits[i])) {
      if (i < digits.length-1) {
        getCombinations(digits, i+1, s + c, result);
      } else {
        result.add(s + c);
      }
    }
  }

  public static void main(String[] args) {
    LetterCombination l = new LetterCombination();
    System.out.println(l.letterCombinations("23"));
  }
}