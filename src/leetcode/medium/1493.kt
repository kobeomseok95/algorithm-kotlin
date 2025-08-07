package leetcode.medium

/**
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(1)
 */
class `1493` {
    fun longestSubarray(nums: IntArray): Int {
        var answer = 0
        var left = 0
        var zeroCount = 0

        for (right in nums.indices) {
            if (nums[right] == 0) {
                zeroCount += 1
            }

            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount -= 1
                }
                left += 1
            }

            answer = maxOf(answer, right - left)
        }

        return answer
    }
}
