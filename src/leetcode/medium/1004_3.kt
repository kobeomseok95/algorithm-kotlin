package leetcode.medium

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - nums[i] is either 0 or 1.
 * - 0 <= k <= nums.length
 *
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(1)
 */
class `1004_3` {
    fun longestOnes(nums: IntArray, k: Int): Int {
        var answer = 0
        var left = 0
        var zeroCount = 0
        nums.forEachIndexed { right, value ->
            if (value == 0) {
                zeroCount += 1
            }
            if (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount -= 1
                }
                left += 1
            }
            answer = maxOf(answer, right - left + 1)
        }
        return answer
    }
}

fun main() {
    val solution = `1004_3`()
    println(solution.longestOnes(nums = intArrayOf(1,1,1,0,0,0,1,1,1,1,0), k = 2)) // 6
    println(solution.longestOnes(nums = intArrayOf(1,1,1,0,0,0,1,1,1,1,1,1,0), k = 2)) // 8
    println(solution.longestOnes(nums = intArrayOf(0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1), k = 3)) // 10
    println(solution.longestOnes(nums = intArrayOf(1,1,0,0,1,1,1), k = 1)) // 4
    println(solution.longestOnes(nums = intArrayOf(1,1,0,0,1,1,1), k = 2)) // 7
}
