package leetcode.medium

/**
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(1)
 */
class `687` {
    fun longestUnivaluePath(root: TreeNode?): Int {
        var max = 0

        fun dfs(root: TreeNode?): Int {
            if (root == null) {
                return 0
            }

            val left = dfs(root.left)
            val right = dfs(root.right)

            val leftPath = if (root.left != null && root.`val` == root.left?.`val`) {
                left + 1
            } else {
                0
            }

            val rightPath = if (root.right != null && root.`val` == root.right?.`val`) {
                right + 1
            } else {
                0
            }
            max = maxOf(max, leftPath + rightPath)
            return maxOf(leftPath, rightPath)
        }

        dfs(root)
        return max
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}
