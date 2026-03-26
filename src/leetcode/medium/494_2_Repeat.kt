package leetcode.medium

/**
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 *
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 * Constraints:
 * - 1 <= nums.length <= 20
 * - 0 <= nums[i] <= 1000
 * - 0 <= sum(nums[i]) <= 1000
 * - -1000 <= target <= 1000
 *
 * 시간 복잡도 : O(N)
 * 공간 복잡도 : O(N)
 */
class `494_2_Repeat` {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val goalNumber = getGoalNumberOrNull(nums, target) ?: return 0
        val dp = IntArray(goalNumber + 1)
            .apply { this[0] = 1 }
        nums.forEach { num ->
            (goalNumber downTo num).forEach { i ->
                dp[i] = dp[i - num] + dp[i]
            }
        }
        return dp[goalNumber]
    }

    private fun getGoalNumberOrNull(nums: IntArray, target: Int): Int? {
        val sum = nums.sum()
        if (sum + target < 0) {
            return null
        }
        if ((sum + target) % 2 != 0) {
            return null
        }
        return (sum + target) / 2
    }
}

fun main() {
    val solution = `494_2_Repeat`()
    println(solution.findTargetSumWays(intArrayOf(0,0,1), 1)) // 1
    println(solution.findTargetSumWays(intArrayOf(1,1,1,1,1), 3)) // 5
    println(solution.findTargetSumWays(intArrayOf(2,3,4,1), 0)) // 2
    println(solution.findTargetSumWays(intArrayOf(2,4,5,1), 2)) // 2
    println(solution.findTargetSumWays(intArrayOf(1), 1)) // 1
    println(solution.findTargetSumWays(intArrayOf(1), 10)) // 0
    println(solution.findTargetSumWays(intArrayOf(7,9,3,8,0,2,4,8,3,9), 0)) // 0
    println(solution.findTargetSumWays(intArrayOf(1,1,1,1), -1000)) // 0
    println(solution.findTargetSumWays(intArrayOf(100,100), -400)) // 0
}
