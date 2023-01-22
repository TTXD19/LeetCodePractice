package Java.test;

import Java.easy.Easy_205_IsomorphicStrings;
import Java.linked_list.Easy_206_ReverseLinkedList;
import Java.linked_list.Easy_21_MergeTwoSortedLists;
import Java.easy.Easy_392_IsSubsequence;
import Java.linked_list.Node;
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

    @Test
    public void Easy_392_IsSubsequence_Test() {
        // Given
        Pair<String, String> testCase1 = new Pair<>("abc", "ahbgdc");
        Pair<String, String> testCase2 = new Pair<>("axc", "ahbgdc");
        Pair<String, String> testCase3 = new Pair<>("acb", "ahbgdc");
        Pair<String, String> testCase4 = new Pair<>("rjufvjafbxnbgriwgokdgqdqewn", "mjmqqjrmzkvhxlyruonekhhofpzzslupzojfuoztvzmmqvmlhgqxehojfowtrinbatjujaxekbcydldglkbxsqbbnrkhfdnpfbuaktupfftiljwpgglkjqunvithzlzpgikixqeuimmtbiskemplcvljqgvlzvnqxgedxqnznddkiujwhdefziydtquoudzxstpjjitmiimbjfgfjikkjycwgnpdxpeppsturjwkgnifinccvqzwlbmgpdaodzptyrjjkbqmgdrftfbwgimsmjpknuqtijrsnwvtytqqvookinzmkkkrkgwafohflvuedssukjgipgmypakhlckvizmqvycvbxhlljzejcaijqnfgobuhuiahtmxfzoplmmjfxtggwwxliplntkfuxjcnzcqsaagahbbneugiocexcfpszzomumfqpaiydssmihdoewahoswhlnpctjmkyufsvjlrflfiktndubnymenlmpyrhjxfdcq");
        Pair<String, String> testCase5 = new Pair<>("ab", "baab");
        Easy_392_IsSubsequence testSubject = new Easy_392_IsSubsequence();

        // When
        boolean testResult_1 = testSubject.isSubsequence(testCase1.getFirst(), testCase1.getSecond());
        boolean testResult_2 = testSubject.isSubsequence(testCase2.getFirst(), testCase2.getSecond());
        boolean testResult_3 = testSubject.isSubsequence(testCase3.getFirst(), testCase3.getSecond());
        boolean testResult_4 = testSubject.isSubsequence(testCase4.getFirst(), testCase4.getSecond());
        boolean testResult_5 = testSubject.isSubsequence(testCase5.getFirst(), testCase5.getSecond());

        // Then
        Assert.assertTrue(testResult_1);
        Assert.assertFalse(testResult_2);
        Assert.assertFalse(testResult_3);
        Assert.assertFalse(testResult_4);
        Assert.assertTrue(testResult_5);
    }

    @Test
    public void Easy_21_MergeTwoSortedLists() {
        Easy_21_MergeTwoSortedLists easy21MergeTwoSortedLists = new Easy_21_MergeTwoSortedLists();

        Node list1 = new Node();
        list1.insertFirst(1);
        list1.insertLast(2);
        list1.insertLast(4);
        list1.printList();
        Node list2 = new Node();
        list2.insertFirst(1);
        list2.insertLast(3);
        list2.insertLast(4);
        list2.printList();

//        Node result = easy21MergeTwoSortedLists.mergeTwoLists(list1, list2);
//        result.printList();

    }

    @Test
    public void Easy_206_MergeTwoSortedLists() {
        Easy_206_ReverseLinkedList easy206ReverseLinkedList = new Easy_206_ReverseLinkedList();

        Node list1 = new Node();
        list1.insertFirst(1);
        list1.insertLast(2);
        list1.insertLast(4);

        Node result = easy206ReverseLinkedList.reverseList(list1);
        result.printList();
    }

}