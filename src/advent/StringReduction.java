package advent;

import java.util.*;
public class StringReduction {

    public int reduction(String s) {
        int caseConvert = 'a' - 'A';
        Deque<Character> stk = new ArrayDeque<>();
        for (char c: s.toCharArray()) {
            if (!stk.isEmpty() && (stk.peek() == c - caseConvert || stk.peek() == c + caseConvert)) {
                stk.pop();
            } else {
                stk.push(c);
            }
        }

        return stk.size();
    }

    public static void main(String args[]) {
        StringReduction ac = new StringReduction();

        System.out.println("Reduction " + ac.reduction("dabAcCaCBAcCcaDA"));
    }
}