package Kotlin.codetemplete

import java.util.*

fun main() {
    // 建立範例二元樹：
    //       1
    //      / \
    //     2   3
    //    / \
    //   4   5
    val root = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(5)
        }
        right = TreeNode(3)
    }

    println("🔁 Recursive DFS")
    println("Preorder:")
    recursivePreorder(root)
    println("\nInorder:")
    recursiveInorder(root)
    println("\nPostorder:")
    recursivePostorder(root)

    println("\n\n🔁 Iterative DFS")
    println("Preorder:")
    iterativePreorder(root)
    println("\nInorder:")
    iterativeInorder(root)
    println("\nPostorder:")
    iterativePostorder(root)
}

//// ----- Recursive Versions -----

fun recursivePreorder(node: TreeNode?) {
    if (node == null) return
    print("${node.`val`} ")
    recursivePreorder(node.left)
    recursivePreorder(node.right)
}

fun recursiveInorder(node: TreeNode?) {
    if (node == null) return
    recursiveInorder(node.left)
    print("${node.`val`} ")
    recursiveInorder(node.right)
}

fun recursivePostorder(node: TreeNode?) {
    if (node == null) return
    recursivePostorder(node.left)
    recursivePostorder(node.right)
    print("${node.`val`} ")
}

//// ----- Iterative Versions -----

fun iterativePreorder(root: TreeNode?) {
    if (root == null) return
    val stack = Stack<TreeNode>()
    stack.push(root)

    while (stack.isNotEmpty()) {
        val node = stack.pop()
        print("${node.`val`} ")

        // 先 push 右子節點，這樣左節點先出來
        node.right?.let { stack.push(it) }
        node.left?.let { stack.push(it) }
    }
}

fun iterativeInorder(root: TreeNode?) {
    val stack = Stack<TreeNode>()
    var current = root

    while (stack.isNotEmpty() || current != null) {
        while (current != null) {
            stack.push(current)
            current = current.left
        }
        current = stack.pop()
        print("${current.`val`} ")
        current = current.right
    }
}

fun iterativePostorder(root: TreeNode?) {
    if (root == null) return
    val stack1 = Stack<TreeNode>()
    val stack2 = Stack<TreeNode>()
    stack1.push(root)

    while (stack1.isNotEmpty()) {
        val node = stack1.pop()
        stack2.push(node)
        node.left?.let { stack1.push(it) }
        node.right?.let { stack1.push(it) }
    }

    while (stack2.isNotEmpty()) {
        print("${stack2.pop().`val`} ")
    }
}


//
//⏱️ 時間與空間複雜度
//方法	                    時間複雜度	空間複雜度（平均 / 最壞）
//Pre/In/Post（Recursive）	O(n)	    O(h)（h 為樹高，最壞 O(n)）
//Preorder（Iterative）	    O(n)	    O(h)
//Inorder（Iterative）	    O(n)	    O(h)
//Postorder（Iterative）	    O(n)	    O(h)（使用兩個 stack）



