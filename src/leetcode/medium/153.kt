package leetcode.medium

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 * - [4,5,6,7,0,1,2] if it was rotated 4 times.
 * - [0,1,2,4,5,6,7] if it was rotated 7 times.
 *
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
 * [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 5000
 * - -5000 <= nums[i] <= 5000
 * - All the integers of nums are unique.
 * - nums is sorted and rotated between 1 and n times.
 *
 * 시간 복잡도: O(log n)
 * 공간 복잡도: O(1)
 */
class `153` {
    fun findMin(nums: IntArray): Int {
        var left = 0
        var right = nums.size - 1
        while (left < right) {
            val mid = (left + right) / 2
            if (nums[mid] > nums[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return nums[left]
    }
}

fun main() {
    val solution = `153`()
    println(solution.findMin(intArrayOf(3, 4, 5, 1, 2))) // 1
    println(solution.findMin(intArrayOf(4, 5, 6, 7, 0, 1, 2))) // 0
    println(solution.findMin(intArrayOf(11, 13, 15, 17))) // 11
}
