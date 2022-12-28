package Java;

import java.util.Arrays;

public class Easy_1480_RunningSumof1dArray {

    public static int[] runningSum(int[] nums) {
        for (int i = 0; i <= nums.length - 1; i++) {
            if (i > 0) {
                nums[i] += nums[i - 1];
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] testCase1 = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(runningSum(testCase1)));
    }

}
