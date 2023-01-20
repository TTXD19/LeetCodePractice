import Java.medium.Medium_227_BasicCalculator_II;
import org.junit.Assert;
import org.junit.Test;

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


}
