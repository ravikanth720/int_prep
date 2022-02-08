package com.github.ravikanth720.algorithm;

import java.util.*;
import java.util.regex.Pattern;

public class StrangeSort {

    public static List<String> strangeSort(List<Integer> mapping, List<String> nums) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < mapping.size(); i++) {
            numMap.put(mapping.get(i), i);
        }

        nums.sort((left, right) -> {
            Long leftVal = getLongVal(left, numMap), rightVal = getLongVal(right, numMap);
            return leftVal.compareTo(rightVal);
        });

        return nums;
    }

    private static Long getLongVal(String num, Map<Integer, Integer> numMap) {
        long val = 0;
        int sign = 1;
        for(int i = 0; i < num.length(); i++) {
            if (i == 0 && num.charAt(i) == '-') {
                sign = -1;
            } else {
                val = val * 10 + numMap.get(num.charAt(i) - '0');
            }
        }

        val *= sign;

        return val;
    }

    public static List<String> strangeSort2(List<Integer> mapping, List<String> nums) {
        HashMap<Integer, Integer> mapNum = new HashMap<>();
        HashMap<String, String> maps = new HashMap<>();
        List<String> res = new ArrayList<>();
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^0]+(\\d)+");
        for(int i=0;i<mapping.size();i++) {
            mapNum.put(mapping.get(i), i);
//            System.out.println(mapping.get(i) + " " + i);
        }

        for(int i=0;i<nums.size();i++) {
            String s = nums.get(i);
            StringBuilder st = new StringBuilder();
            for(int j=0;j<s.length();j++) {

                st.append(mapNum.get(Integer.parseInt(String.valueOf(s.charAt(j)))));
            }
//            System.out.println(st.toString());
            res.add(st.toString());
            maps.put(st.toString(), s);

        }
        Collections.sort(res, ( o1, o2) -> {
                int start = 0;
                for(int i=0;i<o1.length();i++) {
                    if(o1.charAt(i) == '0')
                        start++;
                    else
                        break;
                }
                String s1 = o1.substring(start);
                start = 0;
                for(int i=0;i<o2.length();i++) {
                    if(o2.charAt(i) == '0')
                        start++;
                    else
                        break;
                }
                String s2 = o2.substring(start);
                if(s1.length() != s2.length()) {
                    return s1.length() - s2.length();
                }
                for(int i=0;i<s1.length();i++) {
                    if(s1.charAt(i) != s2.charAt(i)) {
                        int a = Integer.parseInt(String.valueOf(s1.charAt(i)));
                        int b = Integer.parseInt(String.valueOf(s2.charAt(i)));
                        return a - b;
                    }
                }
                return 0;
            });


        for(int i=0;i<res.size();i++){
            result.add(maps.get(res.get(i)));
        }
        return result;
    }

    public static void main(String args[]) {
        List<String> result = StrangeSort.strangeSort(Arrays.asList(2,1,4,8,6,3,0,9,7,5), Arrays.asList("12", "02", "224", "4", "-023", "65", "83", "50"));
        result.forEach(System.out::println);
    }
}
