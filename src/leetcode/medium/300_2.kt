package leetcode.medium

/**
 *
 * 시간 복잡도 : O(n^2)
 * 공간 복잡도 : O(n)
 */
class `300_2` {
    fun lengthOfLIS(nums: IntArray): Int {
        val n = nums.size
        val dp = IntArray(n) { 1 }
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (nums[i] > nums[j]) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
        }
        return dp.max()
    }
}
