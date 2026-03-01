package leetcode.medium

/**
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(1)
 */
class `53` {
    fun maxSubArray(nums: IntArray): Int {
        var sum = 0
        var maxSum = Int.MIN_VALUE
        nums.forEach { num ->
            if (sum < 0) {
                sum = num
            } else {
                sum += num
            }
            maxSum = maxOf(maxSum, sum)
        }
        return maxSum
    }
}

fun main() {
    val solution = `53`()
    println(solution.maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4))) // 6
    println(solution.maxSubArray(intArrayOf(1))) // 1
    println(solution.maxSubArray(intArrayOf(5, 4, -1, 7, 8))) // 23
    println(solution.maxSubArray(intArrayOf(-1))) // -1
    println(solution.maxSubArray(intArrayOf(-2, -1))) // -1
}
