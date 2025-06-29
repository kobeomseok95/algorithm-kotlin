package leetcode.medium

class `334` {
    fun increasingTriplet(nums: IntArray): Boolean {
        var first = Int.MAX_VALUE
        var second = Int.MAX_VALUE
        nums.forEach { num ->
            when {
                num <= first -> first = num
                num <= second -> second = num
                else -> return true
            }
        }
        return false
    }
}
