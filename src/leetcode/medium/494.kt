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
 * 시간 복잡도 : O(2^n)
 * 공간 복잡도 : O(n)
 */
class `494` {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        return dfs(nums, target)
    }

    private fun dfs(
        nums: IntArray,
        target: Int,
        currentIndex: Int = 0,
        currentValue: Int = 0,
    ): Int {
        if (currentIndex >= nums.size) {
            return if (currentValue == target) {
                1
            } else {
                0
            }
        }
        return dfs(
            nums = nums,
            target = target,
            currentIndex = currentIndex + 1,
            currentValue = currentValue + nums[currentIndex],
        ) + dfs(
            nums = nums,
            target = target,
            currentIndex = currentIndex + 1,
            currentValue = currentValue - nums[currentIndex],
        )
    }
}

fun main() {
    val solution = `494`()
    println(solution.findTargetSumWays(intArrayOf(1,1,1,1,1), 3)) // 5
    println(solution.findTargetSumWays(intArrayOf(1), 1)) // 1
    println(solution.findTargetSumWays(intArrayOf(1), 10)) // 0
}
