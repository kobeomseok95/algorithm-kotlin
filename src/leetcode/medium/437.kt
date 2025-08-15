package leetcode.medium

/**
 *  브루트 포스로 접근한다.
 *
 *  시간 복잡도 : O(n^2)
 *  공간 복잡도 : O(h) h -> 트리 높이
 */
class `437` {
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        if (root == null) {
            return 0
        }

        return pathSumFromRoot(root, targetSum.toLong()).toInt() +
            pathSum(root.left, targetSum) +
            pathSum(root.right, targetSum)
    }

    private fun pathSumFromRoot(root: TreeNode?, targetSum: Long): Long {
        if (root == null) {
            return 0
        }

        var count = 0L
        if (targetSum == root.`val`.toLong()) {
            count += 1
        }
        count += pathSumFromRoot(root.left, targetSum - root.`val`)
        count += pathSumFromRoot(root.right, targetSum - root.`val`)
        return count
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}
