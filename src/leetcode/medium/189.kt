package leetcode.medium

/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -2^31 <= nums[i] <= 2^31 - 1
 * - 0 <= k <= 10^5
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */
class `189` {
    fun rotate(nums: IntArray, k: Int): Unit {
        val rotateCount = k % nums.size
        val copiedNums = nums + nums
        nums.forEachIndexed { index, _ ->
            nums[index] = copiedNums[nums.size - rotateCount + index]
        }
    }
}

fun main() {
    val solution = `189`()

    val nums1 = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    solution.rotate(nums1, 3)
    println(nums1.joinToString()) // [5,6,7,1,2,3,4]

    val nums2 = intArrayOf(-1, -100, 3, 99)
    solution.rotate(nums2, 2)
    println(nums2.joinToString()) // [3,99,-1,-100]
}
