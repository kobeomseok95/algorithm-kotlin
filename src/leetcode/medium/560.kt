package leetcode.medium

/**
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Constraints:
 * - 1 <= nums.length <= 2 * 10^4
 * - -1000 <= nums[i] <= 1000
 * - -10^7 <= k <= 10^7
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */
class `560` {
    fun subarraySum(nums: IntArray, k: Int): Int {
        var equalSubarraySum = 0
        var prefixSum = 0
        val sumMap = mutableMapOf<Int, Int>(0 to 1)
        nums.forEach { num ->
            prefixSum += num
            val goal = prefixSum - k
            equalSubarraySum += (sumMap[goal] ?: 0)
            sumMap[prefixSum] = (sumMap[prefixSum] ?: 0) + 1
        }
        return equalSubarraySum
    }
}

fun main() {
    val solution = `560`()
    println(solution.subarraySum(intArrayOf(1, 1, 1), 1)) // 3
    println(solution.subarraySum(intArrayOf(1, 1, 1), 2)) // 2
    println(solution.subarraySum(intArrayOf(1, -1, 0), 0)) // 3
    println(solution.subarraySum(intArrayOf(1, 2, 3), 3)) // 2
    println(solution.subarraySum(intArrayOf(1, 2, 3), 4)) // 0
}
