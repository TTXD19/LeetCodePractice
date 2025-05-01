import Java.medium.Medium_227_BasicCalculator_II;
import Java.medium.Medium_39_Combination_Sum;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GroupMediumTests {

    @Test
    public void Medium_227_BasicCalculator_II_Test() {

        String test_case_1 = "3+2*2";
        String test_case_2 = "3+2*2+20";

        Medium_227_BasicCalculator_II test = new Medium_227_BasicCalculator_II();
        int result_1 = test.simpleCalculate(test_case_1);
        int result_2 = test.simpleCalculate(test_case_2);

        Assert.assertEquals(result_1, 7);
        Assert.assertEquals(result_2, 27);

    }

    @Test
    public void Medium_39_Combination_Sum_Test() {

        int[] test_case_1 = new int[]{2, 3, 5};

        Medium_39_Combination_Sum test = new Medium_39_Combination_Sum();
        List<List<Integer>> result = test.combinationSum(test_case_1, 8);

        System.out.println(result);
    }


}
