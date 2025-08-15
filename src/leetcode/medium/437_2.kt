package leetcode.medium

/**
 *  누적합 + 백트래킹을 통한 계산 효율화 버전
 *
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(n)
 */
class `437_2` {
    fun pathSum(
        root: TreeNode?,
        targetSum: Int,
        currentSum: Long = 0,
        cache: MutableMap<Long, Int> = mutableMapOf(0L to 1),
    ): Int {
        if (root == null) {
            return 0
        }
        var count = 0
        val currentSumValue = currentSum + root.`val`
        count += cache.getOrDefault(currentSumValue - targetSum, 0)

        cache[currentSumValue] = (cache[currentSumValue] ?: 0) + 1
        count += pathSum(root.left, targetSum, currentSumValue, cache)
        count += pathSum(root.right, targetSum, currentSumValue, cache)
        cache[currentSumValue] = (cache[currentSumValue] ?: 0) - 1

        return count
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}
