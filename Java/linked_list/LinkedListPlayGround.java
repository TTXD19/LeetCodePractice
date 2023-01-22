package Java.linked_list;

public class LinkedListPlayGround {

    public static void main(String[] args) {

        Node node = new Node();
        node.insertFirst(5);
        node.insertFirst(10);
        node.insertFirst(12);
        node.insertFirst(15);

        node.insertLast(2);

        node.printList();
    }

}
