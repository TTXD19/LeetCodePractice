package Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Easy_13_RomanToInteger {

    public static int romanToInt(String s) {

        /**
         * Special Cases
         * IV = 4
         * IX = 9
         * XL = 40
         * XC = 90
         * CD = 400
         * CM = 900
         */

        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('I', 1);
        hashMap.put('V', 5);
        hashMap.put('X', 10);
        hashMap.put('L', 50);
        hashMap.put('C', 100);
        hashMap.put('D', 500);
        hashMap.put('M', 1000);

        ArrayList<String> specialCases = new ArrayList<>();
        specialCases.add("IV");
        specialCases.add("IX");
        specialCases.add("XL");
        specialCases.add("XC");
        specialCases.add("CD");
        specialCases.add("CM");

        char[] values = s.toCharArray();
        int currentNumber = 0;
        for (int i = 0; i <= values.length - 1; i++) {
            if (i == 0) {
                currentNumber = hashMap.get(values[i]) + currentNumber;
            }
            String combinedCase = String.valueOf(values[i]) + values[i + 1];
            if (specialCases.contains(combinedCase)) {

            }
        }

        return currentNumber;
    }

    public static void main(String[] args) {
        String testCase_1 = "III";

        System.out.println(romanToInt(testCase_1));
    }
}
