package Java.linked_list;

public class Easy_206_ReverseLinkedList {


    public Node reverseList(Node head) {
        /* iterative solution */
        Node newHead = null;
        while (head != null) {
            Node next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

}
