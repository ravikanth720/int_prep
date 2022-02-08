package com.github.ravikanth720.algorithm;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class CandyCrush {
  public List<Integer> shortest(List<Integer> nums, Map<Integer, List<Integer>> dp) {
    int len = nums.size(), repeat = 1;
    List<List<Integer>> children = new ArrayList<List<Integer>>();
    List<Integer> result = new ArrayList<Integer>();
    
    // Terminating conditions
    if (len < 3) return nums;
    if (dp.containsKey(nums.hashCode())) return dp.get(nums.hashCode());

    for (int i =0, j= i+1; j < len; j++) {
      if (nums.get(i) == nums.get(j)) {
        repeat++;
      } else  {
        if (repeat >= 3) {
          ArrayList<Integer> child = new ArrayList<Integer>(nums);
          child.subList(i, j).clear();
          children.add(child);
          repeat = 1;
        }
        i = j;
      }
    }

    if (children.isEmpty()) {
      dp.put(nums.hashCode(), nums);
      return nums;
    };

    // Recur
    for(List<Integer> child: children) {
      List<Integer> subResult = shortest(child, dp);
      result = (result.size() > subResult.size()) ? subResult : result;
    }

    // Memoize
    dp.put(nums.hashCode(), result);

    return result;
  } 

  public static void main(String[] args) {
    CandyCrush c = new CandyCrush();
    System.out.println(
      c.shortest(Arrays.asList(1,2,2,2,3,3,3,2,1), new HashMap<Integer, List<Integer>>())
      );
  }
}