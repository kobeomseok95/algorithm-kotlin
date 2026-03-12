package leetcode.medium

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 2000].
 * - -1000 <= Node.val <= 1000
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(n)
 */
class `102_2` {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) {
            return emptyList()
        }

        val result = mutableListOf<List<Int>>()
        var currentLevels = mutableListOf(root)
        while (currentLevels.isNotEmpty()) {
            val nextLevels = mutableListOf<TreeNode>()
            result.add(currentLevels.map { it.`val` }.toList())
            for (current in currentLevels) {
                current.left?.let { nextLevels.add(it) }
                current.right?.let { nextLevels.add(it) }
            }
            currentLevels = nextLevels
        }

        return result
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `102_2`()

    // Example 1: root = [3,9,20,null,null,15,7]
    val root1 = `102_2`.TreeNode(3).apply {
        left = `102_2`.TreeNode(9)
        right = `102_2`.TreeNode(20).apply {
            left = `102_2`.TreeNode(15)
            right = `102_2`.TreeNode(7)
        }
    }
    println(solution.levelOrder(root1)) // [[3],[9,20],[15,7]]

    // Example 2: root = [1]
    val root2 = `102_2`.TreeNode(1)
    println(solution.levelOrder(root2)) // [[1]]

    // Example 3: root = []
    println(solution.levelOrder(null)) // []

    // Example 4: root = [1,2,3,4,5,6,7]
    val root4 = `102_2`.TreeNode(1).apply {
        left = `102_2`.TreeNode(2).apply {
            left = `102_2`.TreeNode(4)
            right = `102_2`.TreeNode(5)
        }
        right = `102_2`.TreeNode(3).apply {
            left = `102_2`.TreeNode(6)
            right = `102_2`.TreeNode(7)
        }
    }
    println(solution.levelOrder(root4)) // [[1],[2,3],[4,5,6,7]]
}
