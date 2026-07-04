package leetcode.medium

/**
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 *
 * A subarray is a contiguous part of an array.
 *
 * Constraints:
 * - 1 <= nums.length <= 3 * 10^4
 * - -10^4 <= nums[i] <= 10^4
 * - 2 <= k <= 10^4
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(min(N, K))
 */
class `974` {
    fun subarraysDivByK(nums: IntArray, k: Int): Int {
        var divisibleCounts = 0
        var prefixSum = 0
        val remainderMap = mutableMapOf(0 to 1)
        nums.forEach { num ->
            prefixSum += num
            val remainder = (prefixSum % k).resolveMinus(k)
            divisibleCounts += remainderMap.getOrDefault(remainder, 0)
            remainderMap[remainder] = (remainderMap[remainder] ?: 0) + 1
        }
        return divisibleCounts
    }

    private fun Int.resolveMinus(k: Int): Int = if (this < 0) this + k else this
}

fun main() {
    val solution = `974`()
//    println(solution.subarraysDivByK(intArrayOf(-1, 2, 9), 2)) // 2
    println(solution.subarraysDivByK(intArrayOf(2, -2, 2, -4), 6)) // 2
//    println(solution.subarraysDivByK(intArrayOf(5), 9)) // 0
//    println(solution.subarraysDivByK(intArrayOf(4, 5, 0, -2, -3, 1), 5)) // 7
}
