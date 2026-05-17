package leetcode.medium

/**
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Constraints:
 * - The number of nodes in the tree is n.
 * - 1 <= k <= n <= 10^4
 * - 0 <= Node.val <= 10^4
 *
 * H: 트리 높이, k: k번째 순서
 * 시간 복잡도: O()
 * 공간 복잡도: O()
 */
class `230_2` {
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        var traversalCount = k
        var answer = -1
        fun inorderTraversal(root: TreeNode?) {
            root ?: return
            if (traversalCount < 0) {
                return
            }
            inorderTraversal(root.left)
            traversalCount--
            if (traversalCount == 0) {
                answer = root.`val`
                return
            }
            inorderTraversal(root.right)
        }
        inorderTraversal(root)
        return answer
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `230_2`()

    // Example 1: root = [3,1,4,null,2], k = 1 → 1
    val root1 = `230_2`.TreeNode(3).apply {
        left = `230_2`.TreeNode(1).apply {
            right = `230_2`.TreeNode(2)
        }
        right = `230_2`.TreeNode(4)
    }
    println(solution.kthSmallest(root1, 1)) // 1

    // Example 2: root = [5,3,6,2,4,null,null,1], k = 3 → 3
    val root2 = `230_2`.TreeNode(5).apply {
        left = `230_2`.TreeNode(3).apply {
            left = `230_2`.TreeNode(2).apply {
                left = `230_2`.TreeNode(1)
            }
            right = `230_2`.TreeNode(4)
        }
        right = `230_2`.TreeNode(6)
    }
    println(solution.kthSmallest(root2, 3)) // 3
}
