package leetcode.medium

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 * - The left subtree of a node contains only nodes with keys strictly less than the node's key.
 * - The right subtree of a node contains only nodes with keys strictly greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -2^31 <= Node.val <= 2^31 - 1
 *
 * N: The number of nodes
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */
class `98` {
    fun isValidBST(
        root: TreeNode?,
        min: Long = Int.MIN_VALUE.toLong() - 1,
        max: Long = Int.MAX_VALUE.toLong() + 1,
    ): Boolean {
        if (root == null) {
            return true
        }
        if (root.`val` !in (min + 1)..<max) {
            return false
        }

        return isValidBST(
            root = root.left,
            min = min,
            max = root.`val`.toLong(),
        ) && isValidBST(
            root = root.right,
            min = root.`val`.toLong(),
            max = max,
        )
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `98`()

    // Example 1: [2,1,3]
    val root1 = `98`.TreeNode(2).apply {
        left = `98`.TreeNode(1)
        right = `98`.TreeNode(3)
    }
    println(solution.isValidBST(root1)) // true

    // Example 2: [5,1,4,null,null,3,6]
    val root2 = `98`.TreeNode(5).apply {
        left = `98`.TreeNode(1)
        right = `98`.TreeNode(4).apply {
            left = `98`.TreeNode(3)
            right = `98`.TreeNode(6)
        }
    }
    println(solution.isValidBST(root2)) // false

    // Example 3: [5,2,8,1,3,7,9]
    val root3 = `98`.TreeNode(5).apply {
        left = `98`.TreeNode(2).apply {
            left = `98`.TreeNode(1)
            right = `98`.TreeNode(3)
        }
        right = `98`.TreeNode(8).apply {
            left = `98`.TreeNode(7)
            right = `98`.TreeNode(9)
        }
    }
    println(solution.isValidBST(root3)) // true

    // Example 4: [5,2,8,1,3,4,9]
    val root4 = `98`.TreeNode(5).apply {
        left = `98`.TreeNode(2).apply {
            left = `98`.TreeNode(1)
            right = `98`.TreeNode(3)
        }
        right = `98`.TreeNode(8).apply {
            left = `98`.TreeNode(4)
            right = `98`.TreeNode(9)
        }
    }
    println(solution.isValidBST(root4)) // false

    val root5 = `98`.TreeNode(2).apply {
        left = `98`.TreeNode(2)
        right = `98`.TreeNode(2)
    }
    println(solution.isValidBST(root5)) // false

    val root6 = `98`.TreeNode(2147483647)
    println(solution.isValidBST(root6)) // true
}
