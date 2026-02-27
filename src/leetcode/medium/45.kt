package leetcode.medium

/**
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at
 * index i, you can jump to any index (i + j) where:
 * - 0 <= j <= nums[i] and
 * - i + j < n
 *
 * Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach
 * index n - 1.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^4
 * - 0 <= nums[i] <= 1000
 * - It's guaranteed that you can reach nums[n - 1].
 *
 * 시간 복잡도: O(n^2)
 * 공간 복잡도: O(1)
 */
class `45` {
    fun jump(nums: IntArray): Int {
        var answer = 0
        var index = 0
        val goal = nums.lastIndex
        while (index < goal) {
            val value = nums[index]
            if (index + value >= goal) {
                index += value
            } else {
                var maxValue = -1
                var jumpedIndex = index + 1
                for (nextIndex in (index + 1)..(index + value)) {
                    if (maxValue < nextIndex + nums[nextIndex]) {
                        maxValue = nextIndex + nums[nextIndex]
                        jumpedIndex = nextIndex
                    }
                }
                index = jumpedIndex
            }
            answer += 1
        }
        return answer
    }
}

fun main() {
    val solution = `45`()
    println(solution.jump(intArrayOf(2, 3, 1, 1, 4))) // 2
    println(solution.jump(intArrayOf(2, 3, 0, 1, 4))) // 2
    println(solution.jump(intArrayOf(3, 4, 0, 3, 0, 0, 1))) // 2
    println(solution.jump(intArrayOf(2, 3, 1, 1, 4))) // 2
}