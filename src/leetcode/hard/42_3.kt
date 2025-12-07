package leetcode.hard

/**
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(1)
 *
 * Two Pointer 접근법
 *
 * 핵심 원리
 * - 특정 위치에 고이는 물 = min(왼쪽 최대 높이, 오른쪽 최대 높이) - 현재 높이
 * - 양쪽 끝에서 시작해 포인터를 안쪽으로 이동하며, 각 방향의 최대 높이를 갱신한다.
 *
 * 왜 동작하는가:
 * - 낮은 쪽은 물이 고이는 높이의 상한선이 된다.
 * - 예: leftMax=2, rightMax=5 → min은 무조건 2이므로 오른쪽 최대를 정확히 몰라도 계산 가능
 * - 따라서 낮은 쪽의 물을 계산하고, 해당 포인터를 이동시킨다.
 */
class `42_3` {
    fun trap(height: IntArray): Int {
        var answer = 0
        var left = 0
        var leftMaxHeight = height[left]
        var right = height.lastIndex
        var rightMaxHeight = height[right]
        while (left < right) {
            if (leftMaxHeight < rightMaxHeight) {
                answer += minOf(leftMaxHeight, rightMaxHeight) - height[left]
                left += 1
                leftMaxHeight = maxOf(leftMaxHeight, height[left])
            } else {
                answer += minOf(leftMaxHeight, rightMaxHeight) - height[right]
                right -= 1
                rightMaxHeight = maxOf(rightMaxHeight, height[right])
            }
        }
        return answer
    }
}
