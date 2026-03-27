package leetcode.medium

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly left rotated at an unknown index k
 * (1 <= k < nums.length) such that the resulting array is
 * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Constraints:
 * - 1 <= nums.length <= 5000
 * - -10^4 <= nums[i] <= 10^4
 * - All values of nums are unique.
 * - nums is an ascending array that is possibly rotated.
 * - -10^4 <= target <= 10^4
 *
 * 시간 복잡도: O(log n)
 * 공간 복잡도: O(1)
 */
class `33` {
    fun search(nums: IntArray, target: Int): Int {
        val pivot = nums.getPivot()
        return nums.binarySearch(0, pivot - 1, target)
            .takeIf { it != -1 }
            ?: nums.binarySearch(pivot, nums.size - 1, target)
    }

    private fun IntArray.getPivot(): Int {
        var left = 0
        var right = this.size - 1
        while (left < right) {
            val mid = (left + right) / 2
            if (this[mid] > this[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }

    private fun IntArray.binarySearch(from: Int, toInclusive: Int, target: Int): Int {
        var left = from
        var right = toInclusive
        while (left <= right) {
            val mid = (left + right) / 2
            when {
                target == this[mid] -> return mid
                target > this[mid] -> left = mid + 1
                target < this[mid] -> right = mid - 1
            }
        }
        return -1
    }
}

fun main() {
    val solution = `33`()
    println(solution.search(intArrayOf(3, 0, 2), 0)) // 1
    println(solution.search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0)) // 4
    println(solution.search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3)) // -1
    println(solution.search(intArrayOf(4, 5, 6, 7, 8, 9, 0, 1, 2), 1)) // 7
    println(solution.search(intArrayOf(1), 1)) // 0
    println(solution.search(intArrayOf(1), 0)) // -1
}
