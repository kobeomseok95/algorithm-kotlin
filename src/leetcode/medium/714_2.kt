package leetcode.medium

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 *
 * Note:
 * You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * The transaction fee is only charged once for each stock purchase and sale.
 *
 * Constraints:
 * - 1 <= prices.length <= 5 * 10^4
 * - 1 <= prices[i] < 5 * 10^4
 * - 0 <= fee < 5 * 10^4
 *
 * 시간 복잡도 : O(N)
 * 공간 복잡도 : O(N)
 */
class `714_2` {
    fun maxProfit(prices: IntArray, fee: Int): Int {
        val lastIndex = prices.lastIndex
        val dp = Array(2) { IntArray(prices.size) }
        dp[0][0] = -(prices[0] + fee)
        for (i in 1 until prices.size) {
            dp[0][i] = maxOf(dp[0][i - 1], dp[1][i - 1] - (prices[i] + fee))
            dp[1][i] = maxOf(dp[0][i - 1] + prices[i], dp[1][i - 1])
        }
        return maxOf(dp[0][lastIndex], dp[1][lastIndex])
    }
}

fun main() {
    val solution = `714_2`()
    println(solution.maxProfit(intArrayOf(1,3,2,8,4,9), 2)) // 8
    println(solution.maxProfit(intArrayOf(1,3,7,5,10,3), 3)) // 6
}
