package Java.linked_list;

public class LinkedListPlayGround {

    public static void main(String[] args) {

        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node.next = node2;
        node2.next = node3;

        node.printListTest();

    }

}
