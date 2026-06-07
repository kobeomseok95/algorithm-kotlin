package leetcode.medium

/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 5000].
 * - -1000 <= Node.val <= 1000
 * - -1000 <= targetSum <= 1000
 *
 * N: nodes.length, H: Tree Height
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(H)
 */
class `113` {
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        val paths = mutableListOf<List<Int>>()

        fun backtrack(
            node: TreeNode?,
            targetSum: Int,
            currentSum: Int = 0,
            edges: MutableList<Int> = mutableListOf(),
        ) {
            if (node?.left == null && node?.right == null) {
                if (currentSum == targetSum) {
                    paths.add(edges.toList())
                }
                return
            }

            node.left?.let { left ->
                edges.add(left.`val`)
                backtrack(left, targetSum, currentSum + (left.`val`), edges)
                edges.removeLast()
            }
            node.right?.let { right ->
                edges.add(right.`val`)
                backtrack(right, targetSum, currentSum + right.`val`, edges)
                edges.removeLast()
            }
        }

        root?.let { root ->
            backtrack(root, targetSum, root.`val`, mutableListOf(root.`val`))
        }
        return paths
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `113`()

    // Example 1: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
    val root1 = `113`.TreeNode(5).apply {
        left = `113`.TreeNode(4).apply {
            left = `113`.TreeNode(11).apply {
                left = `113`.TreeNode(7)
                right = `113`.TreeNode(2)
            }
        }
        right = `113`.TreeNode(8).apply {
            left = `113`.TreeNode(13)
            right = `113`.TreeNode(4).apply {
                left = `113`.TreeNode(5)
                right = `113`.TreeNode(1)
            }
        }
    }
    println(solution.pathSum(root1, 22)) // [[5,4,11,2],[5,8,4,5]]

    // Example 2: root = [1,2,3], targetSum = 5
    val root2 = `113`.TreeNode(1).apply {
        left = `113`.TreeNode(2)
        right = `113`.TreeNode(3)
    }
    println(solution.pathSum(root2, 5)) // []

    // Example 3: root = [1,2], targetSum = 0
    val root3 = `113`.TreeNode(1).apply {
        left = `113`.TreeNode(2)
    }
    println(solution.pathSum(root3, 0)) // []

    // Example 4: root = [-2,null,-3], targetSum = -5
    val root4 = `113`.TreeNode(-2).apply {
        right = `113`.TreeNode(-3)
    }
    println(solution.pathSum(root4, -5)) // [[-2,-3]]
}
