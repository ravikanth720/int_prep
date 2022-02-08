package com.github.ravikanth720.algorithm;
import java.util.*;
class TwoSum {
  public String findTwoSum(int[] arr, int sum) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer> ();
    int[] res = new int[2];
    for(int i=0; i<arr.length; i++) {
      if(map.containsKey(arr[i])) {
        res[0] = map.get(arr[i]);
        res[1] = i;
      } else {
        map.put(sum - arr[i], i);
      }
    }
    return Arrays.toString(res);
  }
  
  public static void main(String args[]) {
    TwoSum ts = new TwoSum();
    System.out.println(ts.findTwoSum(new int[]{1,2,3,4,5,6}, 9));
  }
}