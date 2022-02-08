package com.github.ravikanth720.algorithm;

import java.util.HashMap;
import java.util.Map;

class AnagramSubstring {
  
  public boolean isAnagramSubstring(String s1, String s2){
    Map<Character, Integer> charFrequency1 = calculateCharFrequency(s1.toCharArray());
    Map<Character, Integer> charFrequency2 = calculateCharFrequency(s2.toCharArray());
    return charFrequency1.equals(charFrequency2);
  }
  
  public Map<Character, Integer> calculateCharFrequency(char[] charArray){
    Map<Character, Integer> charFrequency = null;
    if(charArray != null && charArray.length > 0) {
      charFrequency = new HashMap<Character, Integer>();
      for (int i = 0; i < charArray.length; i++) {
        if (charFrequency.containsKey(charArray[i])) {
          charFrequency.put(charArray[i], charFrequency.get(charArray[i]) + 1);
        } else {
          charFrequency.put(charArray[i], 1);
        }
      }
    }
    return charFrequency;
  }
  
  public static void main(String[] args){
    AnagramSubstring as = new AnagramSubstring();
    System.out.println(as.isAnagramSubstring("", "ravikanth"));
  }
}