package Java;

import java.util.HashMap;

/**
 * Title: Longest Substring Without Repeating Characters
 * LeetCode URL:
 * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/">...</a>
 */
public class LongestSubstring {

    String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ ";

    /**
     * Author: Welsen
     * Leet Code Run Time: 476 ms
     * Memory: 144.8 MB
     */
    public int getLongestSub() {

        StringBuilder currentSub;
        StringBuilder longestSub = new StringBuilder();
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
                    if (longestSub.toString().isEmpty()) {
                        longestSub.append(currentSub);
                    }
                    break;
                }
                currentSub.append(splitString[i]);
                if (currentSub.length() > longestSub.length()) {
                    longestSub.setLength(0);
                    longestSub.append(currentSub);
                }
            }
        }
        return longestSub.toString().length();
    }

    /**
     * Author: LeetCode
     * Leet Code Run Time: 12 ms
     * Memory: 45.1 MB
     */

    public int getLongestSubStringQuick() {
        if (s.length() == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<>();
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
