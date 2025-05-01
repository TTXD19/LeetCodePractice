package Kotlin


fun main() {

    val node = Node(7)
    val node1 = Node(13)
    val node2 = Node(11)
    val node3 = Node(10)
    val node4 = Node(1)

    node.next = node1
    node.random = null

    node1.next = node2
    node1.random = node

    node2.next = node3
    node3.random = node4
}

class LinkedList {
    fun copyRandomList(node: Node?): Node? {
        if (node == null) return null
        var currentNode = node
        val oldNodeMap = mutableMapOf<Node, Node>()
        while (currentNode != null) {
            oldNodeMap[currentNode] = Node(currentNode.`val`)
            currentNode = currentNode.next
        }
        currentNode = node
        while (currentNode != null) {
            val newNode = oldNodeMap[currentNode]
            newNode?.next = oldNodeMap[currentNode.next]
            newNode?.random = oldNodeMap[currentNode.random]
            currentNode = currentNode.next
        }
        return oldNodeMap[node]
    }

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null
        val dummyNode = ListNode(0)
        dummyNode.next = head
        var startPointer: ListNode? = dummyNode
        var secondPointer: ListNode? = dummyNode

        for (i in 0..n) {
            secondPointer = secondPointer?.next
        }

        while (secondPointer != null) {
            secondPointer = secondPointer.next
            startPointer = startPointer?.next
        }

        startPointer?.next = startPointer?.next?.next

        return dummyNode.next
    }

    fun reverseList(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var curr = head

        while (curr != null) {
            val nextNode = curr.next
            curr.next = prev
            prev = curr
            curr = nextNode
        }

        return prev
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

        var p1: ListNode? = l1
        var p2: ListNode? = l2
        val dummyNode = ListNode(0)
        var current: ListNode? = dummyNode
        var carry = 0

        while (p1 != null || p2 != null) {
            val x = p1?.`val` ?: 0
            val y = p2?.`val` ?: 0
            val sum = carry + x + y

            carry = sum / 10
            current?.next = ListNode(sum % 10)
            current = current?.next

            if (p1 != null) p1 = p1.next
            if (p2 != null) p2 = p2.next
        }

        if (carry > 0){
            current?.next = ListNode(carry)
        }

        return dummyNode.next
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Node(var `val`: Int) {
    var next: Node? = null
    var random: Node? = null
}