package leetcode.medium

/**
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 * Constraints:
 * - 1 <= nums.length <= 200
 * - 1 <= nums[i] <= 100
 *
 * N: nums.length / M: sum
 * 시간 복잡도: O(N * (M / 2))
 * 공간 복잡도: O(M / 2)
 */
class `416_3` {
    fun canPartition(nums: IntArray): Boolean {
        val target = getTargetOrNull(nums) ?: return false
        val dp = BooleanArray(target + 1).apply { this[0] = true }
        nums.forEach { num ->
            (target downTo num).forEach { i ->
                dp[i] = dp[i] || dp[i - num]
            }
            if (dp[target]) {
                return true
            }
        }
        return dp[target]
    }

    private fun getTargetOrNull(nums: IntArray): Int? {
        val (sum, max) = nums.fold(0 to 0) { (currentSum, currentMax), num ->
            currentSum + num to maxOf(currentMax, num)
        }
        if (sum % 2 != 0) {
            return null
        }
        return (sum / 2).takeIf { it >= max }
    }
}

fun main() {
    val solution = `416_3`()
    println(solution.canPartition(intArrayOf(2, 2, 3, 5))) // false
    println(solution.canPartition(intArrayOf(1, 2, 5))) // false
    println(solution.canPartition(intArrayOf(1, 5, 11, 5))) // true
    println(solution.canPartition(intArrayOf(2, 3, 2, 1))) // true
    println(solution.canPartition(intArrayOf(1, 2, 3, 5))) // false
}
