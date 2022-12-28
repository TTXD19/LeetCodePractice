package Java.Test;

import Java.Easy_205_IsomorphicStrings;
import kotlin.Pair;
import org.junit.Assert;
import org.junit.Test;

public class GroupEasyTests {

    @Test
    public void Easy_205_IsomorphicStrings_Test() {
        // Given
        Pair<String, String> testCase1 = new Pair<>("egg", "add");
        Pair<String, String> testCase2 = new Pair<>("foo", "bar");
        Pair<String, String> testCase3 = new Pair<>("paper", "title");
        Pair<String, String> testCase4 = new Pair<>("badc", "baba");
        Easy_205_IsomorphicStrings testSubject = new Easy_205_IsomorphicStrings();

        // When
        boolean testResult_1 = testSubject.isIsomorphic(testCase1.getFirst(), testCase1.getSecond());
        boolean testResult_2 = testSubject.isIsomorphic(testCase2.getFirst(), testCase2.getSecond());
        boolean testResult_3 = testSubject.isIsomorphic(testCase3.getFirst(), testCase3.getSecond());
        boolean testResult_4 = testSubject.isIsomorphic(testCase4.getFirst(), testCase4.getSecond());

        // Then
        Assert.assertTrue(testResult_1);
        Assert.assertFalse(testResult_2);
        Assert.assertTrue(testResult_3);
        Assert.assertFalse(testResult_4);
    }

}