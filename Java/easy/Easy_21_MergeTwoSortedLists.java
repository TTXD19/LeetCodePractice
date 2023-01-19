package Java.easy;

public class Easy_21_MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 != null && list2 != null) {


            if (list1.val < list2.val) {
                if (list2.next == null) return list1.next = list2;
                list1.next = mergeTwoLists(list2, list1.next);
                return list1;
            } else {
                if (list1.next == null) return list2.next = list1;
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }
        return null;
    }
}

