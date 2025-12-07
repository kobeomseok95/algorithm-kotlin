package leetcode.hard

/**
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(n)
 *
 * 첫 번째 순회를 통해 각 인덱스 별 왼쪽 기준의 최대 높이 / 오른쪽 기준 최대 높이를 미리 구한다
 * 두 번째 순회를 통해 각 인덱스 별 좌 / 우 기준 최소 높이에 인덱스에 해당하는 높이를 뺀 값이 트랩의 크기가 된다
 * brute force 접근에서 높이 계산을 미리 캐싱하여 접근함
 */
class `42_2` {
    fun trap(height: IntArray): Int {
        var answer = 0
        val leftMaxHeights = IntArray(height.size) { 0 }
        val rightMaxHeights = IntArray(height.size) { 0 }
        height.indices.forEach { left ->
            val right = height.lastIndex - left

            leftMaxHeights[left] = maxOf(
                leftMaxHeights.getOrElse(left - 1) { 0 },
                height[left]
            )
            rightMaxHeights[right] = maxOf(
                rightMaxHeights.getOrElse(right + 1) { 0 },
                height[right],
            )
        }
        height.indices.forEach { i ->
            answer += minOf(leftMaxHeights[i], rightMaxHeights[i]) - height[i]
        }
        return answer
    }
}
