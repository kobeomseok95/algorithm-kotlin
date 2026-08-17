package leetcode.medium

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - nums[i] is either 0 or 1.
 * - 0 <= k <= nums.length
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1)
 */
class `1004_4` {
    fun longestOnes(nums: IntArray, k: Int): Int {
        var length = 0
        var left = 0
        var changedOneCount = 0
        for (right in nums.indices) {
            if (nums[right] == 0) {
                changedOneCount++
            }
            if (changedOneCount > k) {
                if (nums[left] == 0) {
                    changedOneCount--
                }
                left++
            }
            length = maxOf(length, (right - left + 1))
        }
        return length
    }
}

fun main() {
    val solution = `1004_4`()
    println(solution.longestOnes(intArrayOf(1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0), 2)) // 6
    println(solution.longestOnes(intArrayOf(0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1), 3)) // 10
}
