package leetcode.medium

/**
 *  시간 복잡도 ; O(n)
 *  공간 복잡도 : O(1)
 */
class `1004` {
    fun longestOnes(nums: IntArray, k: Int): Int {
        var left = 0
        var zeroCount = 0
        var answer = 0
        nums.forEachIndexed { right, value ->
            if (value == 0) {
                zeroCount += 1
            }
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount -= 1
                }
                left += 1
            }

            answer = maxOf(answer, right - left + 1)
        }
        return answer
    }
}
