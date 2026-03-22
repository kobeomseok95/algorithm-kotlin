package leetcode.medium

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Constraints:
 * - 1 <= coins.length <= 12
 * - 1 <= coins[i] <= 2^31 - 1
 * - 0 <= amount <= 10^4
 *
 * N: coins.length, M: amount
 * 시간 복잡도: O(N * M)
 * 공간 복잡도: O(M)
 */
class `322` {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1).apply {
            (1..amount).forEach { this[it] = MAX }
        }
        coins.forEach { coin ->
            (coin..amount).forEach { i ->
                dp[i] = minOf(dp[i - coin] + 1, dp[i])
            }
        }
        return dp[amount].takeIf { it != MAX } ?: -1
    }

    companion object {
        private const val MAX = 10_001
    }
}

fun main() {
    val solution = `322`()
    println(solution.coinChange(intArrayOf(1, 2, 5), 11)) // 3
    println(solution.coinChange(intArrayOf(1, 2, 5), 3)) // 2
    println(solution.coinChange(intArrayOf(2), 3)) // -1
    println(solution.coinChange(intArrayOf(1), 0)) // 0
}
