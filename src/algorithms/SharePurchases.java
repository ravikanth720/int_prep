package algorithms;

import java.util.*;

public class SharePurchases {

    public static long analyzeInvestments(String s) {
        Map<Character, Integer> imp = new HashMap<>();
        imp.put('A', 0);
        imp.put('B', 0);
        imp.put('C', 0);

        int count = 0, i=0, j = 0, len = s.length();

        while (i < len - 2) {
            while (!isValid(imp) && i < len && j < len) {
                if (imp.containsKey(s.charAt(j))) {
                    imp.put(s.charAt(j), imp.get(s.charAt(j)) + 1);
                }
                j++;
            }

            count += 1 + len - j;

            if (imp.containsKey(s.charAt(i))) {
                imp.put(s.charAt(i), imp.get(s.charAt(i)) - 1);
            }

            i++;
        }

        return count;
    }

    public static boolean isValid(Map<Character, Integer> map) {
        int res = 1;

        for (Integer val: map.values()) {
            res *= val;
        }

        return res != 0;
    }

    public static void main(String[] args) {
        System.out.println(analyzeInvestments("ABBCZBAC"));
    }
}
