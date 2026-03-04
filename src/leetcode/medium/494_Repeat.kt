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
class `494_Repeat` {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        return dfs(nums, target)
    }

    private fun dfs(
        nums: IntArray,
        target: Int,
        current: CurrentResult = CurrentResult.init(),
    ): Int {
        if (current.index >= nums.size) {
            return if (current.sum == target) {
                1
            } else {
                0
            }
        }

        return dfs(
            nums = nums,
            target = target,
            current = CurrentResult(
                index = current.index + 1,
                sum = current.sum + nums[current.index],
            ),
        ) + dfs(
            nums = nums,
            target = target,
            current = CurrentResult(
                index = current.index + 1,
                sum = current.sum - nums[current.index],
            ),
        )
    }

    private data class CurrentResult(
        val index: Int,
        val sum: Int,
    ) {
        companion object {
            fun init(): CurrentResult {
                return CurrentResult(0, 0)
            }
        }
    }
}

fun main() {
    val solution = `494_Repeat`()
    println(solution.findTargetSumWays(intArrayOf(1,1,1,1,1), 3)) // 5
    println(solution.findTargetSumWays(intArrayOf(2,4,5,1), 2)) // 2
    println(solution.findTargetSumWays(intArrayOf(1), 1)) // 1
    println(solution.findTargetSumWays(intArrayOf(1), 10)) // 0
    println(solution.findTargetSumWays(intArrayOf(7,9,3,8,0,2,4,8,3,9), 0)) // 0
    println(solution.findTargetSumWays(intArrayOf(1,1,1,1), -1000)) // 0
    println(solution.findTargetSumWays(intArrayOf(100,100), -400)) // 0
}
