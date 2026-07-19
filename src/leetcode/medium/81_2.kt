package leetcode.medium

/**
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 *
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
 * (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 *
 * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 * Constraints:
 * - 1 <= nums.length <= 5000
 * - -10^4 <= nums[i] <= 10^4
 * - nums is guaranteed to be rotated at some pivot.
 * - -10^4 <= target <= 10^4
 *
 * 시간 복잡도: O(log N)
 * 공간 복잡도: O(1)
 *
 * 재풀이 필요.
 */
class `81_2` {
    fun search(nums: IntArray, target: Int): Boolean {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (target == nums[mid]) {
                return true
            }

            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left += 1
                right -= 1
            } else if (nums[left] <= nums[mid]) {
                if (target in nums[left]..nums[mid]) {
                    right = mid
                } else {
                    left = mid + 1
                }
            } else {
                if (target in nums[mid]..nums[right]) {
                    left = mid
                } else {
                    right = mid - 1
                }
            }
        }
        return false
    }
}

fun main() {
    val solution = `81_2`()
    println(solution.search(intArrayOf(1, 2, 3, 4), 1)) // true
    println(solution.search(intArrayOf(2, 5, 6, 0, 0, 1, 2), 0)) // true
    println(solution.search(intArrayOf(2, 5, 6, 0, 0, 1, 2), 3)) // false
    println(solution.search(intArrayOf(1, 0, 1, 1, 1), 0)) // true
    println(solution.search(intArrayOf(1, 1, 1, 1, 1, 1, 2, 1), 2)) // true
    println(solution.search(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1), 2)) // true
}
