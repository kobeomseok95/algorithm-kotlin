package leetcode.medium

/**
 * given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * the product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * you must write an algorithm that runs in o(n) time and without using the division operation.
 *
 * Constraints:
 * - 2 <= nums.length <= 10^5
 * - -30 <= nums[i] <= 30
 * - The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 *
 * N: nums.length
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */
class `238` {
    fun productExceptSelf(nums: IntArray): IntArray {
        val prefix = IntArray(nums.size)
        val suffix = IntArray(nums.size)
        (0 until nums.size).forEach { prefixIndex ->
            val suffixIndex = nums.lastIndex - prefixIndex

            prefix[prefixIndex] = prefix.getOrNull(prefixIndex - 1)
                ?.let { it * nums[prefixIndex] }
                ?: nums.first()
            suffix[suffixIndex] = suffix.getOrNull(suffixIndex + 1)
                ?.let { it * nums[suffixIndex] }
                ?: nums.last()
        }
        val answer = IntArray(nums.size)
        (0 until answer.size).forEach { i ->
            answer[i] = (prefix.getOrNull(i - 1) ?: 1) * (suffix.getOrNull(i + 1) ?: 1)
        }
        return answer
    }
}

fun main() {
    val solution = `238`()
    println(solution.productExceptSelf(intArrayOf(1, 2, 3, 4)).contentToString()) // [24, 12, 8, 6]
    println(solution.productExceptSelf(intArrayOf(-1, 1, 0, -3, 3)).contentToString()) // [0, 0, 9, 0, 0]
    println(solution.productExceptSelf(intArrayOf(0, 0, 1)).contentToString()) // [0, 0, 0]
}
