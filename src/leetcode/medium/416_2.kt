package leetcode.medium

/**
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 * Constraints:
 * - 1 <= nums.length <= 200
 * - 1 <= nums[i] <= 100
 *
 * n: nums의 size / t: target 크기
 * 시간 복잡도: O(n * t)
 * 공간 복잡도: O(t)
 */
class `416_2` {
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 != 0) {
            return false
        }
        val goalNumber = sum / 2
        val dp = BooleanArray(goalNumber + 1).apply { this[0] = true }
        for (num in nums) {
            for (j in goalNumber downTo num) {
                if (dp[j - num]) {
                    dp[j] = true
                }
            }
            if (dp[goalNumber]) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val solution = `416_2`()
//    println(solution.canPartition(intArrayOf(1,2,5))) // false
    println(solution.canPartition(intArrayOf(1,5,11,5))) // true
//    println(solution.canPartition(intArrayOf(1,2,3,5))) // false
//    println(solution.canPartition(intArrayOf(1,5,6,5))) // false
//    println(solution.canPartition(intArrayOf(1,7,3,5))) // true
//    println(solution.canPartition(intArrayOf(1,7,2,3,6,1))) // true
}
