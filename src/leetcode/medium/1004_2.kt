package leetcode.medium

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - nums[i] is either 0 or 1.
 * - 0 <= k <= nums.length
 *
 * n: nums.size
 * 시간 복잡도 : O(n * k) -> 최대 100_000 * 100_000 -> 시간 초과 발생
 * 공간 복잡도 : O(n * k) -> 최대 100_000 * 100_000 -> 공간 초과 발생
 */
class `1004_2` {
    fun longestOnes(nums: IntArray, k: Int): Int {
        val dp = Array(k + 1) { IntArray(nums.size + 1) }
        (1..nums.size).forEach { j ->
            if (nums[j - 1] == 1) {
                dp[0][j] = dp[0][j - 1] + 1
            }
        }

        (1..k).forEach { i ->
            (1..nums.size).forEach { j ->
                if (nums[j - 1] == 1) {
                    dp[i][j] = dp[i][j - 1] + 1
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                }
            }
        }
        return dp[k].max()
    }
}

fun main() {
    val solution = `1004_2`()
    println(solution.longestOnes(nums = intArrayOf(1,1,1,0,0,0,1,1,1,1,0), k = 2)) // 6
    println(solution.longestOnes(nums = intArrayOf(0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1), k = 3)) // 10
    println(solution.longestOnes(nums = intArrayOf(1,1,0,0,1,1,1), k = 1)) // 4
    println(solution.longestOnes(nums = intArrayOf(1,1,0,0,1,1,1), k = 2)) // 7
}
