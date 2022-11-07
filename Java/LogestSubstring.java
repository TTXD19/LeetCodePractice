package Java;

import java.util.HashMap;

/**
 * Title: Longest Substring Without Repeating Characters
 * 
 * LeetCode URL:
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
 */
public class LogestSubstring {

    private String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ ";

    /**
     * Author: Welsen
     * Leet Code Run Time: 476 ms
     * Memory: 144.8 MB
     */
    public int getLongestSub() {

        StringBuilder currentSub = new StringBuilder();
        StringBuilder logestSub = new StringBuilder();
        char[] splitString = s.toCharArray();

        if (s.contains(" ") && splitString.length == 1) {
            return 1;
        }

        if (splitString.length == 1) {
            return 1;
        }

        if (s.isEmpty()) {
            return 0;
        }

        for (int n = 0; n < splitString.length; n++) {
            currentSub = new StringBuilder();
            currentSub.append(splitString[n]);
            for (int i = n + 1; i <= splitString.length - 1; i++) {
                if (currentSub.toString().contains(String.valueOf(splitString[i]))) {
                    if (logestSub.isEmpty()) {
                        logestSub.append(currentSub.toString());
                    }
                    break;
                }
                currentSub.append(splitString[i]);
                if (currentSub.length() > logestSub.length()) {
                    logestSub.setLength(0);
                    logestSub.append(currentSub.toString());
                }
            }
        }
        return logestSub.toString().length();
    }

    /**
     * Author: LeetCode
     * Leet Code Run Time: 12 ms
     * Memory: 45.1 MB
     */

    public int getLognestSubStringQuick() {
        if (s.length() == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }
}
