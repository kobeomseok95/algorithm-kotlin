package leetcode.medium

class `1679` {
    fun maxOperations(nums: IntArray, k: Int): Int {
        nums.sort()
        var operationCounts = 0
        var left = 0
        var right = nums.size - 1
        while (left < right) {
            val sum = nums[left] + nums[right]
            if (sum < k) {
                left += 1
            } else if (sum > k) {
                right -= 1
            } else {
                operationCounts += 1
                left += 1
                right -= 1
            }
        }
        return operationCounts
    }
}
