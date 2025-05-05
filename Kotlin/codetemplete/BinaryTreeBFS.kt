package Kotlin.codetemplete

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

    println("🔁 BFS (Level Order Traversal):")
    bfs(root)
}

// 層序遍歷（BFS）：使用 Queue
fun bfs(root: TreeNode?) {
    if (root == null) return

    val queue = ArrayDeque<TreeNode>()
    queue.addLast(root)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        println(current.`val`)    // 訪問目前節點

        current.left?.let { queue.addLast(it) }   // 將左子節點加入佇列
        current.right?.let { queue.addLast(it) }  // 將右子節點加入佇列
    }
}
