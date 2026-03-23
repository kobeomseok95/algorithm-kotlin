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
 * 공간 복잡도: O(1)
 */
class `238_2` {
    fun productExceptSelf(nums: IntArray): IntArray {
        val answer = IntArray(nums.size)
        nums.forEachIndexed { index, num ->
            answer[index] = answer.getOrNull(index - 1)
                ?.let { it * num }
                ?: num
        }
        var suffixAcc = 1
        (nums.lastIndex downTo 0).forEach { i ->
            val prefixAcc = answer.getOrNull(i - 1) ?: 1
            answer[i] = prefixAcc * suffixAcc
            suffixAcc *= nums[i]
        }
        return answer
    }
}

fun main() {
    val solution = `238_2`()
    println(solution.productExceptSelf(intArrayOf(1, 2, 3, 4)).contentToString()) // [24, 12, 8, 6]
    println(solution.productExceptSelf(intArrayOf(-1, 1, 0, -3, 3)).contentToString()) // [0, 0, 9, 0, 0]
    println(solution.productExceptSelf(intArrayOf(0, 0, 1)).contentToString()) // [0, 0, 0]
}
