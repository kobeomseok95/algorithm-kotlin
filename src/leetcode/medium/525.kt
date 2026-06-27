package leetcode.medium

/**
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - nums[i] is either 0 or 1.
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */
class `525` {
    fun findMaxLength(nums: IntArray): Int {
        // key: 누적합 값, value: index
        val countMap = mutableMapOf<Int, Int>()
        var count = 0
        var maxLength = 0
        nums.forEachIndexed { index, num ->
            count += num.takeIf { it == 1 } ?: -1
            if (countMap[count] == null) {
                countMap[count] = index
            } else {
                maxLength = maxLength.coerceAtLeast(index - countMap[count]!!)
            }
        }

        return maxLength
    }
}

fun main() {
    val solution = `525`()
    println(solution.findMaxLength(intArrayOf(0, 0, 1, 0, 0))) // 2
    println(solution.findMaxLength(intArrayOf(0, 1))) // 2
    println(solution.findMaxLength(intArrayOf(0, 1, 0))) // 2
    println(solution.findMaxLength(intArrayOf(0, 1, 1, 1, 1, 1, 0, 0, 0))) // 6
}
