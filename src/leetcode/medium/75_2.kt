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
 * Dutch National Flag 방식으로 풀이
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(1)
 */
class `75_2` {
    fun sortColors(nums: IntArray): Unit {
        var zeroIndex = 0
        var twoIndex = nums.lastIndex
        var index = 0
        while (index <= twoIndex) {
            if (nums[index] == 2) {
                nums.swap(index, twoIndex)
                twoIndex--
            } else if (nums[index] == 0) {
                nums.swap(index, zeroIndex)
                zeroIndex++
                index++
            } else {
                index++
            }
        }
    }

    private fun IntArray.swap(i1: Int, i2: Int) {
        val temp = this[i1]
        this[i1] = this[i2]
        this[i2] = temp
    }
}

fun main() {
    val solution = `75_2`()
    val nums1 = intArrayOf(2, 0, 2, 1, 1, 0)
    solution.sortColors(nums1)
    println(nums1.contentToString()) // [0, 0, 1, 1, 2, 2]

    val nums2 = intArrayOf(2, 0, 1)
    solution.sortColors(nums2)
    println(nums2.contentToString()) // [0, 1, 2]
}
