package leetcode.medium

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 300
 * - nums[i] is either 0, 1, or 2.
 *
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(1)
 */
class `75` {
    fun sortColors(nums: IntArray): Unit {
        var oneIndex = 0
        var zeroIndex = 0
        for (i in 0 until nums.size) {
            val temp = nums[i]
            nums[i] = 2

            if (temp < 2) {
                nums[oneIndex++] = 1
            }
            if (temp < 1) {
                nums[zeroIndex++] = 0
            }
        }
    }
}

fun main() {
    val solution = `75`()
    val nums1 = intArrayOf(2, 0, 2, 1, 1, 0)
    solution.sortColors(nums1)
    println(nums1.contentToString()) // [0, 0, 1, 1, 2, 2]

    val nums2 = intArrayOf(2, 0, 1)
    solution.sortColors(nums2)
    println(nums2.contentToString()) // [0, 1, 2]
}
