package leetcode.easy

class `746` {
    fun minCostClimbingStairs(cost: IntArray): Int {
        val n = cost.size
        val dp = IntArray(n)
        dp[0] = cost[0]
        dp[1] = cost[1]
        (2 until n).forEach { i ->
            dp[i] = cost[i] + minOf(dp[i - 1], dp[i - 2])
        }
        return minOf(
            dp[n - 1],
            dp[n - 2],
        )
    }
}
