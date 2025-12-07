package leetcode.hard

/**
 * 시간 복잡도: O(n^2)
 * 공간 복잡도: O(1)
 *
 * brute force 접근 방식으로 풀이한다.
 */
class `42` {
    fun trap(height: IntArray): Int {
        var answer = 0
        height.indices.forEach { i ->
            var leftMax = 0
            var rightMax = 0
            (0..i).forEach { j ->
                leftMax = maxOf(leftMax, height[j])
            }
            (i until height.size).forEach { j ->
                rightMax = maxOf(rightMax, height[j])
            }
            answer += minOf(leftMax, rightMax) - height[i]
        }
        return answer
    }
}
