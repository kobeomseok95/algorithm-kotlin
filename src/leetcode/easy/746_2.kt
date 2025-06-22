package leetcode.easy

class `746_2` {
    fun minCostClimbingStairs(cost: IntArray): Int {
        var prev1 = cost[0]
        var prev2 = cost[1]

        (2 until cost.size).forEach { i ->
            val result = cost[i] + minOf(prev1, prev2)
            prev1 = prev2
            prev2 = result
        }

        return minOf(prev1, prev2)
    }
}
