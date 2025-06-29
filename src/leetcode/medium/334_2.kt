package leetcode.medium

/**
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(n)
 */
class `334_2` {
    fun increasingTriplet(nums: IntArray): Boolean {
        val n  = nums.size
        if (n < 3) {
            return false
        }
        val leftMin = IntArray(n)
        leftMin[0] = nums[0]
        (1 until n).forEach { i ->
            leftMin[i] = minOf(leftMin[i - 1], nums[i])
        }
        var rightMax = nums[n - 1]
        for (i in n - 2 downTo 1) {
            rightMax = maxOf(rightMax, nums[i + 1])
            if (leftMin[i - 1] < nums[i] && nums[i] < rightMax) {
                return true
            }
        }
        return false
    }
}
