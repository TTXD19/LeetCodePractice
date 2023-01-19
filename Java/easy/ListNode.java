package Java.easy;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {

    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void printList(){
        System.out.println(val);
        printList(next);
    }

    private void printList(ListNode listNode){
        if (listNode != null){
            System.out.println(listNode.val);
            printList(listNode.next);
        }
    }
}
