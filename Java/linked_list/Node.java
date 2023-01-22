package Java.linked_list;

public class Node {
    public int value;
    public Node next;
    public Node head;

    public Node() {
    }

    private Node(int val) {
        this.value = val;
    }

    private Node(int val, int next) {
        this.value = val;
        this.next = new Node(next);
    }

    public void printList() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.value);
            currentNode = currentNode.next;
        }
    }

    public void insertFirst(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            return;
        }
        node.next = head;
        head = node;
    }

    public void insertLast(int value) {
        Node currentNode = head;
        Node newNode = new Node(value);
        newNode.next = null;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
    }
}
