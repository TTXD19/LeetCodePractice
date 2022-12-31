package Java.easy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Easy_66_PlusOne {

    public static int[] plusOne(int[] digits) {
        List<Integer> list = new ArrayList<>(Arrays.stream(digits).boxed().toList());
        boolean nextShouldAdd = true;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (nextShouldAdd){
                list.set(i, list.get(i) + 1);
                nextShouldAdd = false;
            }
            if (list.get(i) == 10) {
                list.set(i, 0);
                nextShouldAdd = true;
            }
            if (nextShouldAdd){
                if (i - 1 < 0) {
                    list.add(0, 1);
                }
            }
        }
        return convertToIntArray(list);
    }

    public static int[] convertToIntArray(List<Integer> list){
        int[] returnList = new int[list.size()];
        for (int i = 0; i <= list.size() - 1; i++) {
            returnList[i] = list.get(i);
        }
        return returnList;
    }

    private static double convertToNumber(int[] digits) {
        double multiply = 1;
        double currentNumber = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            double digit = Double.parseDouble(String.valueOf(digits[i]));
            currentNumber = currentNumber + (digit * multiply);
            multiply = multiply * 10;
        }
        return currentNumber;
    }

    public static void main(String[] args) {
        int[] testCase_1 = new int[]{1, 2, 3};
        int[] testCase_2 = new int[]{4, 3, 2, 1};
        int[] testCase_3 = new int[]{9};
        int[] testCase_4 = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] testCase_5 = new int[]{9, 9};

        System.out.println(Arrays.toString(plusOne(testCase_5)));
    }
}
