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
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(n)
 */
class `714` {
    fun maxProfit(prices: IntArray, fee: Int): Int {
        val dp = Array(prices.size + 1) { IntArray(2) }
        dp[0][0] = -50_001
        (1..prices.size).forEach { i ->
             dp[i][0] = maxOf(
                 dp[i - 1][1] - prices[i - 1],
                 dp[i - 1][0],
             )
             dp[i][1] = maxOf(
                 dp[i - 1][0] + prices[i - 1] - fee,
                 dp[i - 1][1],
             )
        }
        return dp[prices.size][1]
    }
}

fun main() {
    val solution = `714`()
    println(solution.maxProfit(intArrayOf(1,3,2,8,4,9), 2)) // 8
    println(solution.maxProfit(intArrayOf(1,3,7,5,10,3), 3)) // 6
}
