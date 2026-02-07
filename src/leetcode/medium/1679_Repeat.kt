package leetcode.medium

/**
 * You are given an integer array nums and an integer k.
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 * Return the maximum number of operations you can perform on the array.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^9
 * - 1 <= k <= 10^9
 *
 * 시간 복잡도: O(nlog(n))
 * 공간 복잡도: O(1)
 */
class `1679_Repeat` {
    fun maxOperations(nums: IntArray, k: Int): Int {
        var answer = 0
        nums.sort()
        var left = 0
        var right = nums.lastIndex
        while (left < right) {
            when {
                nums[left] + nums[right] == k -> {
                    answer += 1
                    left += 1
                    right -= 1
                }
                nums[left] + nums[right] > k -> right -= 1
                nums[left] + nums[right] < k -> left += 1
            }
        }
        return answer
    }
}

fun main() {
    val solution = `1679_Repeat`()
    println(solution.maxOperations(intArrayOf(1,2,3,4), 5)) // 2
    println(solution.maxOperations(intArrayOf(3,1,3,4,3), 6)) // 1
}
