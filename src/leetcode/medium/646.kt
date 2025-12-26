package leetcode.medium

/**
 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 * Return the length longest chain which can be formed.
 * You do not need to use up all the given intervals. You can select pairs in any order.
 *
 * Constraints:
 * n == pairs.length
 * 1 <= n <= 1000
 * -1000 <= lefti < righti <= 1000
 *
 * 최대 pair 갯수를 뽑기 위해서는 pairs에 대해 정렬을 하고나서 최대 길이를 구하는 것이 유리하다.
 *
 *  시간 복잡도 : O(n^2)
 *  공간 복잡도 : O(n)
 */
class `646` {
    fun findLongestChain(pairs: Array<IntArray>): Int {
        pairs.sortWith(compareBy({ it[1] }, { it[0] }))
        val n = pairs.size
        val dp = IntArray(n) { 1 }
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
        }
        return dp.max()
    }
}

fun main() {
    val solution = `646`()
    println(solution.findLongestChain(arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(3,4)))) // 2
    println(solution.findLongestChain(arrayOf(intArrayOf(1,2), intArrayOf(7,8), intArrayOf(4,5)))) // 3
    println(solution.findLongestChain(arrayOf(intArrayOf(1,2), intArrayOf(1,2), intArrayOf(1,2)))) // 1
    println(solution.findLongestChain(arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(2,3)))) // 1
}
