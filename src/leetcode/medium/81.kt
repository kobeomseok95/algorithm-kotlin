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
 * 시간 복잡도: O(log n)
 * 공간 복잡도: O(1)
 */
class `81` {
    fun search(nums: IntArray, target: Int): Boolean {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = (left + right) / 2
            if (target == nums[mid]) {
                return true
            }
            // 어느 부분이 정렬이 보장되었는지 알 수 없으므로 1칸 씩 옮겨서 재탐색
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left += 1
                right -= 1
                continue
            } else {
                if (nums[left] <= nums[mid]) { // left ~ mid 까지는 정렬되어있다.
                    if (target in (nums[left]..nums[mid])) { // 범위에 있다면
                        right = mid // 오른쪽은 탐색할 이유가 없다.
                    } else {
                        left = mid + 1 // 왼쪽을 탐색할 이유가 없다.
                    }
                } else { // mid ~ right 까지는 정렬되어있다.
                    if (target in (nums[mid]..nums[right])) { // 범위에 있다면
                        left = mid
                    } else {
                        right = mid - 1 // 오른쪽을 탐색할 이유가 없다.
                    }
                }
            }
        }
        return false
    }
}

fun main() {
    val solution = `81`()
    println(solution.search(intArrayOf(2, 5, 6, 0, 0, 1, 2), 0)) // true
    println(solution.search(intArrayOf(2, 5, 6, 0, 0, 1, 2), 3)) // false
    println(solution.search(intArrayOf(1, 0, 1, 1, 1), 0)) // true
    println(solution.search(intArrayOf(1, 1, 1, 1, 1, 1, 2, 1), 2)) // true
    println(solution.search(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1), 2)) // true
}
