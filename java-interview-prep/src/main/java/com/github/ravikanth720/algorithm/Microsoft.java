package com.github.ravikanth720.algorithm;

public class Microsoft {

    public String cropMessage(String message, int K) {
        String[] words = message.split(" ");
        StringBuilder sb = new StringBuilder();
        // Initializing -1 becuase of an extra space
        int len = -1;

        for (String word: words) {
            len += word.length() + 1;

            if (len <= K) {
                // Append space before a new word
                if (sb.length() != 0) sb.append(" ");
                sb.append(word);
            }
        }

        return sb.toString();
    }

    public int semiAlterSubstr(String S) {
        int count = 1, len = 1, maxLen = 1;

        for(int i=1; i<S.length(); i++) {
            if (S.charAt(i) == S.charAt(i-1)) {
                count++;
                if (count == 3) {
                    count--;
                    len = 1;
                }
            } else {
                count = 1;
            }

            len++;
            maxLen = Math.max(len, maxLen);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Microsoft m = new Microsoft();
        System.out.println(m.cropMessage("I am not th same as you are thinking", 11));
        System.out.println(m.semiAlterSubstr("baaabbabbb"));
    }
}
