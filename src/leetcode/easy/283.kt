package leetcode.easy

class `283` {
    fun moveZeroes(nums: IntArray): IntArray {
        var zeroIndex = 0
        for (i in nums.indices) {
            if (nums[i] != 0) {
                nums[zeroIndex] = nums[i]
                zeroIndex += 1
            }
        }
        while (zeroIndex < nums.size) {
            nums[zeroIndex] = 0
            zeroIndex += 1
        }
        return nums
    }
}
