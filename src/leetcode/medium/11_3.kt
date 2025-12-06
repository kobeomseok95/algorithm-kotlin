package leetcode.medium

/**
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(1)
 *
 * 투 포인터 방식으로 접근
 */
class `11_3` {
    fun maxArea(height: IntArray): Int {
        var maxArea = 0
        var left = 0
        var right = height.size - 1
        while (left < right) {
            maxArea = maxOf(maxArea, getArea(left, right, height))
            if (height[left] < height[right]) {
                left += 1
            } else {
                right -= 1
            }
        }

        return maxArea
    }

    private fun getArea(left: Int, right: Int, height: IntArray): Int {
        return (right - left) * minOf(height[left], height[right])
    }
}
