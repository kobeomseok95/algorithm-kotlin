package leetcode.medium

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 *
 * Constraints:
 *  1 <= target <= 10^9
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^4
 *
 * subarray 로 뽑힌 배열이 target 값보다 이상인 경우의 최소 subarray 길이를 구하는 문제
 * 누적합을 활용하여 풀이
 *
 * 시간 복잡도 : O(n * log(n))
 * 공간 복잡도 : O(n)
 */
class `209_2` {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var answer = Int.MAX_VALUE
        val prefixSums = LongArray(nums.size + 1) { 0 }
        for (i in nums.indices) {
            prefixSums[i + 1] = prefixSums[i] + nums[i]
        }
        for (i in prefixSums.indices) {
            val targetSum = prefixSums[i] + target
            var left = i + 1
            var right = nums.size
            while (left < right) {
                val mid = (left + right) / 2

                if (prefixSums[mid] >= targetSum) {
                    right = mid
                } else {
                    left = mid + 1
                }
            }

            if (left <= nums.size && prefixSums[left] >= targetSum) {
                answer = minOf(answer, left - i)
            }
        }

        return answer.takeIf { it != Int.MAX_VALUE } ?: 0
    }
}

fun main() {
    val solution = `209_2`()
    println(solution.minSubArrayLen(11, intArrayOf(1,2,3,4,5))) // 3
    println(solution.minSubArrayLen(7, intArrayOf(2,3,1,2,4,3))) // 2
    println(solution.minSubArrayLen(4, intArrayOf(1,4,4))) // 1
    println(solution.minSubArrayLen(11, intArrayOf(1,1,1,1,1,1,1,1))) // 0
}
