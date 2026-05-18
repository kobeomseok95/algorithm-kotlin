package leetcode.medium

import java.util.Stack

/**
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Constraints:
 * - The number of nodes in the tree is n.
 * - 1 <= k <= n <= 10^4
 * - 0 <= Node.val <= 10^4
 *
 * 시간 복잡도: O(H + k)
 * 공간 복잡도: O(H)
 */
class `230_3` {
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        val stack = Stack<TreeNode>()
        pushNode(stack, root)
        repeat(k - 1) {
            pushNode(stack, stack.pop().right)
        }
        return stack.pop().`val`
    }

    private fun pushNode(stack: Stack<TreeNode>, root: TreeNode?) {
        var node = root
        while (node != null) {
            stack.push(node)
            node = node.left
        }
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `230_3`()

    // Example 1: root = [3,1,4,null,2], k = 1 → 1
    val root1 = `230_3`.TreeNode(3).apply {
        left = `230_3`.TreeNode(1).apply {
            right = `230_3`.TreeNode(2)
        }
        right = `230_3`.TreeNode(4)
    }
    println(solution.kthSmallest(root1, 1)) // 1

    // Example 2: root = [5,3,6,2,4,null,null,1], k = 3 → 3
    val root2 = `230_3`.TreeNode(5).apply {
        left = `230_3`.TreeNode(3).apply {
            left = `230_3`.TreeNode(2).apply {
                left = `230_3`.TreeNode(1)
            }
            right = `230_3`.TreeNode(4)
        }
        right = `230_3`.TreeNode(6)
    }
    println(solution.kthSmallest(root2, 3)) // 3

    val root3 = `230_3`.TreeNode(3).apply {
        left = `230_3`.TreeNode(1)
        right = `230_3`.TreeNode(5).apply {
            left = `230_3`.TreeNode(4)
            right = `230_3`.TreeNode(6)
        }
    }
    println(solution.kthSmallest(root3, 3)) // 4
}
