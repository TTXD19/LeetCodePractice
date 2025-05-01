package Kotlin

import java.util.Stack
import kotlin.math.abs

class BinaryTrees {
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        return 1 + maxOf(maxDepth(root.left), maxDepth(root.right))
    }

    fun DFSSolution(root: TreeNode?): List<Int> {
        val stack = Stack<TreeNode>()
        val list = mutableListOf<Int>()

        if (root != null) {
            stack.push(root)

        }
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            list.add(node.`val`)
            if (node.right != null) stack.push(node.right)
            if (node.left != null) stack.push(node.left)
        }

        return list
    }

    fun isBalanced(root: TreeNode?): Boolean {
        fun checkHeight(root: TreeNode?): Int {
            if (root == null) return 0

            val leftHeight = checkHeight(root.left)
            if (leftHeight == -1) return -1
            val rightHeight = checkHeight(root.right)
            if (rightHeight == -1) return -1

            if (abs(leftHeight - rightHeight) > 1) return -1
            return 1 + maxOf(leftHeight, rightHeight)
        }
        return checkHeight(root) != -1
    }

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        val stackP = Stack<TreeNode?>()
        val stackQ = Stack<TreeNode?>()
        stackP.push(p)
        stackQ.push(q)
        while (stackP.isNotEmpty() && stackQ.isNotEmpty()) {
            val nodeP = stackP.pop()
            val nodeQ = stackQ.pop()
            if (nodeP == null && nodeQ == null) {
                continue
            }
            if (nodeP == null || nodeQ == null) {
                return false
            }
            if (nodeP.`val` != nodeQ.`val`) {
                return false
            }
            stackP.push(nodeP.right)
            stackP.push(nodeP.left)
            stackQ.push(nodeQ.right)
            stackQ.push(nodeQ.left)
        }
        return stackP.isEmpty() && stackQ.isEmpty()
    }


}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}