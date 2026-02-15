package leetcode.medium

/**
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 0 <= nums[i] <= 400
 *
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(1)
 */
class `198_Repeat` {
    fun rob(nums: IntArray): Int {
        var prev2 = 0
        var prev1 = nums[0]
        (1 until nums.size).forEach { i ->
            val current = maxOf(prev1, prev2 + nums[i])
            prev2 = prev1
            prev1 = current
        }
        return prev1
    }
}

fun main() {
    val solution = `198_Repeat`()
    println(solution.rob(intArrayOf(1,2,3,1))) // 4
    println(solution.rob(intArrayOf(2,7,9,3,1))) // 12
    println(solution.rob(intArrayOf(1))) // 1
    println(solution.rob(intArrayOf(2,1,1,2))) // 4
}
