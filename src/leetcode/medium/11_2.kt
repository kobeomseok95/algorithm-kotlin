package leetcode.medium

/**
 * 시간 복잡도: O(n^2)
 * 공간 복잡도: O(1)
 *
 * brute force 방식 접근
 */
class `11_2` {
    fun maxArea(height: IntArray): Int {
        var maxArea = 0
        for (i in height.indices) {
            for (j in i + 1 until height.size) {
                maxArea = maxOf(
                    maxArea,
                    (j - i) * minOf(height[i], height[j]),
                )
            }
        }
        return maxArea
    }
}
