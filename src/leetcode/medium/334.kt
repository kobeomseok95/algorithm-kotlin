package leetcode.medium

/**
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(1)
 */
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
