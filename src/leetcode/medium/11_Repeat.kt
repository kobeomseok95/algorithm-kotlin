package leetcode.medium

/**
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 *
 * Constraints:
 * - n == height.length
 * - 2 <= n <= 10^5
 * - 0 <= height[i] <= 10^4
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(1)
 */
class `11_Repeat` {
    fun maxArea(height: IntArray): Int {
        var answer = 0
        var left = 0
        var right = height.size - 1
        while (left < right) {
            val leftHeight = height[left]
            val rightHeight = height[right]
            answer = maxOf(answer, getArea(left, right, leftHeight, rightHeight))
            if (leftHeight <= rightHeight) {
                left += 1
            } else {
                right -= 1
            }
        }
        return answer
    }

    private fun getArea(
        left: Int,
        right: Int,
        leftHeight: Int,
        rightHeight: Int,
    ): Int {
        return (right - left) * minOf(leftHeight, rightHeight)
    }
}

fun main() {
    val solution = `11_Repeat`()
    println(solution.maxArea(intArrayOf(1,8,6,2,5,4,8,3,7))) // 49
    println(solution.maxArea(intArrayOf(1,1))) // 1
    println(solution.maxArea(intArrayOf(1,7,8,4,3,6))) // 24
    println(solution.maxArea(intArrayOf(5,1,2,1,0,2))) // 10
    println(solution.maxArea(intArrayOf(1,2,3,1000,9))) // 9
}
