package leetcode.medium

/**
 * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
 *
 * A good subarray is a subarray where:
 * - its length is at least two, and
 * - the sum of the elements of the subarray is a multiple of k.
 *
 * Note that:
 * - A subarray is a contiguous part of the array.
 * - An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 0 <= nums[i] <= 10^9
 * - 0 <= sum(nums[i]) <= 2^31 - 1
 * - 1 <= k <= 2^31 - 1
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(min(N, K))
 */
class `523_2` {
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        val remainderToIndexMap = hashMapOf(0 to -1)
        var acc = 0
        for (index in nums.indices) {
            val value = nums[index]
            acc += value
            val remainder = acc % k
            if (remainderToIndexMap[remainder] == null) {
                remainderToIndexMap[remainder] = index
            }
            if (index - remainderToIndexMap[remainder]!! >= 2) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val solution = `523_2`()
    println(solution.checkSubarraySum(intArrayOf(0, 0), 1)) // true
    println(solution.checkSubarraySum(intArrayOf(1, 0), 2)) // false
    println(solution.checkSubarraySum(intArrayOf(5, 0, 0, 0), 3)) // true
    println(solution.checkSubarraySum(intArrayOf(2, 4, 5, 8), 9)) // true
    println(solution.checkSubarraySum(intArrayOf(23, 2, 4, 6, 6), 7)) // true
    println(solution.checkSubarraySum(intArrayOf(23, 2, 4, 6, 7), 6)) // true
    println(solution.checkSubarraySum(intArrayOf(23, 2, 6, 4, 7), 6)) // true
    println(solution.checkSubarraySum(intArrayOf(23, 2, 6, 4, 7), 13)) // false
}
