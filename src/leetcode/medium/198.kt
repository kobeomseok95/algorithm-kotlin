package leetcode.medium

/**
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(n)
 */
class `198` {
    fun rob(nums: IntArray): Int {
        if (nums.size <= 2) {
            return maxOf(nums.firstOrNull() ?: 0, nums.lastOrNull() ?: 0)
        }
        val dp = IntArray(nums.size)
        dp[0] = maxOf(0, nums[0])
        dp[1] = maxOf(dp[0], nums[1])
        (2 until nums.size).forEach { i ->
            dp[i] = maxOf(dp[i - 1], dp[i - 2] + nums[i])
        }
        return dp.last()
    }
}
