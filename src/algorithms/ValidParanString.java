package algorithms;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;
import java.util.TreeSet;

class ValidParanString {
  public boolean checkValidStirng(String s) {
    TreeSet<Integer> stars = new TreeSet<>();
    Deque<Integer> openParans = new ArrayDeque<>();

    for(int i=0; i<s.length(); i++) {
      if (s.charAt(i) == '(') {
        openParans.push(i);
      } else if (s.charAt(i) == '*') {
        stars.add(i);
      } else {
        if (openParans.isEmpty()) {
          Integer star = stars.lower(i);
          if (star == null) {
            return false;
          } else {
            stars.remove(star);
          }
        } else {
          openParans.pop();
        }
      }
    }

    while(!openParans.isEmpty()) {
      int open = openParans.pop();
      Integer star = stars.higher(open);

      if (star == null)
        return false;
      else {
        stars.remove(star);
      }
    }

    return true;
  }

  public static void main(String[] args) {
    ValidParanString v = new ValidParanString();
    System.out.print(v.checkValidStirng("(((()(*)"));
  }
}