package leetcode.medium

import kotlin.math.abs

/**
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 * We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights x and y with x <= y. The result of this smash is:
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
 *
 * Constraints:
 * - 1 <= stones.length <= 30
 * - 1 <= stones[i] <= 100
 *
 * n: stones.length / s: stones's sum
 * 시간 복잡도: O(n * s)
 * 공간 복잡도: O(s)
 */
class `1049` {
    fun lastStoneWeightII(stones: IntArray): Int {
        val sum = stones.sum()
        val dp = BooleanArray((sum / 2) + 1).apply { this[0] = true }
        stones.forEach { stone ->
            (dp.lastIndex downTo stone).forEach { j ->
                dp[j] = dp[j] || dp[j - stone]
            }
        }
        val p = (dp.lastIndex downTo 0).first { dp[it] }
        return abs(sum - (2 * p))
    }
}

fun main() {
    val solution = `1049`()
    println(solution.lastStoneWeightII(intArrayOf(2,7,4,1,8,1))) // 1
    println(solution.lastStoneWeightII(intArrayOf(31,26,33,21,40))) // 5
    println(solution.lastStoneWeightII(intArrayOf(2,3,1,1))) // 1
}
