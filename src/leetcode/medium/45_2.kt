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
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(1)
 */
class `45_2` {
    fun jump(nums: IntArray): Int {
        var jumps = 0
        var currentIndex = 0
        var farthestIndex = 0
        nums.forEachIndexed { index, value ->
            if (currentIndex >= nums.lastIndex) {
                return jumps
            }

            farthestIndex = maxOf(farthestIndex, index + value)
            if (currentIndex <= index) {
                currentIndex = farthestIndex
                jumps++
            }
        }
        return jumps
    }
}

fun main() {
    val solution = `45_2`()
    println(solution.jump(intArrayOf(0))) // 0
    println(solution.jump(intArrayOf(2, 3, 1, 1, 4))) // 2
    println(solution.jump(intArrayOf(2, 3, 0, 1, 4))) // 2
    println(solution.jump(intArrayOf(3, 4, 0, 3, 0, 0, 1))) // 2
    println(solution.jump(intArrayOf(2, 3, 1, 1, 4))) // 2
}
