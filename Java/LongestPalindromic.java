package Java;

public class LongestPalindromic {

    public static void main(String[] args) {

        String input = "eabcb";
        String input2 = "babad";
        String input3 = "cbbd";
        String input4 = "aacabdkacaa";
        String input5 = "aacabdaa";
        String input6 = "ac";
        String input7 = "aaaaa";
        String input8 = "abcba";
        String test = new LongestPalindromic().test(input8);
        System.out.println(test);
    }

    public String longestPalindrome(String s) {

        int leftPointer;
        int rightPointer;

        String longestString = "";
        String currentString = "";

        if (s.length() == 1) {
            return s;
        }

        for (int i = 0; i <= s.length() - 1; i++) {
            leftPointer = i;
            rightPointer = s.length();
            for (int runningRight = s.length() - 1; runningRight > 0; runningRight--) {
                if (leftPointer <= s.length() - 1 && s.charAt(leftPointer) == s.charAt(runningRight)) {
                    leftPointer++;
                    if (leftPointer > rightPointer) {
                        break;
                    }
                    if (runningRight - leftPointer == 1 || runningRight == leftPointer) {
                        if (rightPointer + 1 <= s.length() && s.charAt(runningRight) == s.charAt(runningRight + 1)) {
                            rightPointer++;
                        }
                        if (rightPointer + 1 <= s.length() && s.charAt(i) == s.charAt(rightPointer)) {
                            rightPointer++;
                        }
                        currentString = s.substring(i, rightPointer);
                        if (currentString.length() > longestString.length()) {
                            longestString = currentString;
                        }
                    }
                } else {
                    leftPointer = i;
                }
                rightPointer--;
            }
        }
        if (longestString.isEmpty()) {
            longestString = String.valueOf(s.charAt(0));
        }
        return longestString;
    }

    private String test(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            //find the longestPalindrome center at s[i] (odd length)
            String s1 = palindrome(s, i, i);
            //find the longestPalindrome center at s[i] and s[i + 1] (even length)
            String s2 = palindrome(s, i, i + 1);
            //update the res to the longest palindrome with s1, s2
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    private String palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) { //check palindrome
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }
}
