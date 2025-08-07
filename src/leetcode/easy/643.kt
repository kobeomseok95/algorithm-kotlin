package leetcode.easy

/**
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(1)
 */
class `643` {
    fun findMaxAverage(nums: IntArray, k: Int): Double {
        var answer = nums.sliceArray(0 until k).sum()
        var current = answer
        (1..nums.size - k).forEach { i ->
            current = current - nums[i - 1] + nums[i + k - 1]
            answer = maxOf(current, answer)
        }
        return answer.div(k.toDouble())
    }
}
