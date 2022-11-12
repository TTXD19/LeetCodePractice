package Java;

import java.util.Arrays;

public class RunningSumOf1DArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        new RunningSumOf1DArray().runningSumArray(nums);
    }

    /**
     * Author: Welsen
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private void runningSumArray(int[] nums) {
        for (int n = 0; n <= nums.length - 1; n++) {
            if (n != 0) {
                nums[n] += nums[n - 1];
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
