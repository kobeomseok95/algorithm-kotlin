package leetcode.medium

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
 * N: stones.length, S: stones.sum
 * 시간 복잡도: O(N * S / 2)
 * 공간 복잡도: O(S)
 */
class `1049_Repeat` {
    fun lastStoneWeightII(stones: IntArray): Int {
        val sum = stones.sum()
        val target = sum / 2
        val dp = IntArray(target + 1)
        stones.forEach { weight ->
            (dp.lastIndex downTo weight).forEach { i ->
                dp[i] = maxOf(dp[i], dp[i - weight] + weight)
            }
        }
        return sum - (dp.last() * 2)
    }
}

fun main() {
    val solution = `1049_Repeat`()
    println(solution.lastStoneWeightII(intArrayOf(2,7,4,1,8,1))) // 1
    println(solution.lastStoneWeightII(intArrayOf(31,26,33,21,40))) // 5
    println(solution.lastStoneWeightII(intArrayOf(2,3,1,1))) // 1
    println(solution.lastStoneWeightII(intArrayOf(2,7,4,1))) // 0
}
