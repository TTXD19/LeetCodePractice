package Java.linked_list;

public class Node {
    public int value;
    public Node next;
    public Node head;

    public Node() {
    }

    public Node(int val) {
        this.value = val;
        this.next = null;
    }

    public void printListTest(){
        Node currentNode = this;
        while (currentNode != null){
            System.out.println(currentNode.value);
            currentNode = currentNode.next;
        }
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
