package Java.easy;

import java.util.Arrays;

public class Easy_724_FindPivotIndex {

    public static int pivotIndex(int[] nums) {
        int sum = 0, leftsum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i <= nums.length - 1; i++) {
            if (leftsum == sum - nums[i] - leftsum) return i;
            leftsum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] testCase1 = new int[]{1, 7, 3, 6, 5, 6};
        int[] testCase2 = new int[]{1, 2, 3};
        int[] testCase3 = new int[]{2, 1, -1};
        int[] testCase4 = new int[]{-1, -1, -1, -1, -1, -1};
        System.out.println(pivotIndex(testCase1));
    }
}
