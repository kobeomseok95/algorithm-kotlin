package leetcode.hard

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 *
 * Constraints:
 * - n == height.length
 * - 1 <= n <= 2 * 10^4
 * - 0 <= height[i] <= 10^5
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1)
 */
class `42_Repeat` {
    fun trap(height: IntArray): Int {
        var answer = 0
        var left = 0
        var leftMaxHeight = height[left]
        var right = height.lastIndex
        var rightMaxHeight = height[right]
        while (left < right) {
            val minOfHeight = minOf(leftMaxHeight, rightMaxHeight)
            if (leftMaxHeight < rightMaxHeight) {
                answer += minOfHeight - height[left]
                left++
                leftMaxHeight = maxOf(leftMaxHeight, height[left])
            } else {
                answer += minOfHeight - height[right]
                right--
                rightMaxHeight = maxOf(rightMaxHeight, height[right])
            }
        }
        return answer
    }
}

fun main() {
    val solution = `42_Repeat`()
    println(solution.trap(intArrayOf(0,7,1,4,6))) // 7
    println(solution.trap(intArrayOf(0, 1))) // 0
    println(solution.trap(intArrayOf(0, 1, 2, 3, 2, 3))) // 1
    println(solution.trap(intArrayOf(4, 2, 0, 3, 2, 5))) // 9
    println(solution.trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1))) // 6
}
