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
 *
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(1)
 */
class `209` {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var answer = Int.MAX_VALUE
        var left = 0
        var right = 0
        var sum = 0
        while (left < nums.size && right < nums.size) {
            while (right < nums.size && sum < target) {
                sum += nums[right]
                right += 1
            }

            while (sum >= target && left < right) {
                answer = minOf(answer, right - left)
                sum -= nums[left]
                left += 1
            }
        }

        return answer.takeIf { it != Int.MAX_VALUE } ?: 0
    }
}

fun main() {
    val solution = `209`()
    println(solution.minSubArrayLen(7, intArrayOf(2,3,1,2,4,3))) // 2
    println(solution.minSubArrayLen(4, intArrayOf(1,4,4))) // 1
    println(solution.minSubArrayLen(11, intArrayOf(1,1,1,1,1,1,1,1))) // 0
}
