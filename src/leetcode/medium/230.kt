package leetcode.medium

import java.util.PriorityQueue

/**
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Constraints:
 * - The number of nodes in the tree is n.
 * - 1 <= k <= n <= 10^4
 * - 0 <= Node.val <= 10^4
 *
 * 시간 복잡도: O(NlogN)
 * 공간 복잡도: O(N)
 */
class `230` {
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        root ?: return 0

        val priorityQueue = PriorityQueue<Int> { a, b -> a - b }
        fun dfs(root: TreeNode?) {
            root ?: return
            priorityQueue.add(root.`val`)
            dfs(root.left)
            dfs(root.right)
        }
        dfs(root)

        var count = k
        var answer = 0
        while (count-- > 0) {
            answer = priorityQueue.poll()
        }
        return answer
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `230`()

    // Example 1: root = [3,1,4,null,2], k = 1 → 1
    val root1 = `230`.TreeNode(3).apply {
        left = `230`.TreeNode(1).apply {
            right = `230`.TreeNode(2)
        }
        right = `230`.TreeNode(4)
    }
    println(solution.kthSmallest(root1, 1)) // 1

    // Example 2: root = [5,3,6,2,4,null,null,1], k = 3 → 3
    val root2 = `230`.TreeNode(5).apply {
        left = `230`.TreeNode(3).apply {
            left = `230`.TreeNode(2).apply {
                left = `230`.TreeNode(1)
            }
            right = `230`.TreeNode(4)
        }
        right = `230`.TreeNode(6)
    }
    println(solution.kthSmallest(root2, 3)) // 3
}
