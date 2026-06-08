package leetcode.medium

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Constraints:
 * - 0 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */
class `128` {
    fun longestConsecutive(nums: IntArray): Int {
        val set = nums.toSet()
        var answer = 0
        set.forEach { num ->
            if (set.contains(num - 1).not()) {
                var checkNum = num
                while (set.contains(checkNum)) {
                    checkNum++
                }
                answer = maxOf(answer, checkNum - num)
            }
        }

        return answer
    }
}

fun main() {
    val solution = `128`()
    println(solution.longestConsecutive(intArrayOf(100, 4, 200, 1, 3, 2))) // 4
    println(solution.longestConsecutive(intArrayOf(0, 3, 7, 2, 5, 8, 4, 6, 0, 1))) // 9
    println(solution.longestConsecutive(intArrayOf(1, 0, 1, 2))) // 3
}
